package org.openinfinity.web.controller;

import org.apache.log4j.Logger;
import org.openinfinity.core.annotation.AuditTrail;
import org.openinfinity.core.annotation.Log;
import org.openinfinity.core.aspect.ArgumentStrategy;
import org.openinfinity.core.exception.AbstractCoreException;
import org.openinfinity.core.exception.ApplicationException;
import org.openinfinity.core.exception.BusinessViolationException;
import org.openinfinity.core.exception.SystemException;
import org.openinfinity.domain.entity.Catalogue;
import org.openinfinity.domain.entity.ShoppingList;
import org.openinfinity.domain.service.ProductService;
import org.openinfinity.domain.service.ShoppingListService;
import org.openinfinity.web.model.ShoppingListModel;
import org.openinfinity.web.support.SerializerUtil;
import org.openinfinity.web.support.ServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.*;


@Controller
@RequestMapping(value = "/manager/shoppinglist")
public class ShoppingListController {

    private static final Logger logger = Logger.getLogger(ShoppingListController.class);


    @Autowired
    private ShoppingListService shoppingListService;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    private Validator validator;


    //@ResponseStatus annotation is not working, bug in Spring?
    //@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Domain service threw an exception.")
    @Log
    @ExceptionHandler({SystemException.class, ApplicationException.class, BusinessViolationException.class})
    //public @ResponseBody Map<String, ? extends Object> exceptionOccurred(AbstractCoreException abstractCoreException, HttpServletResponse response) {
    public void exceptionOccurred(AbstractCoreException abstractCoreException, HttpServletResponse response, Locale locale) {
        ShoppingListModel shoppingListModel = new ShoppingListModel();
        if (abstractCoreException.isErrorLevelExceptionMessagesIncluded()) {
            Collection<String> localizedErrorMessages = getLocalizedExceptionMessages(abstractCoreException.getErrorLevelExceptionIds(), locale);
            shoppingListModel.addErrorStatuses("errorLevelExceptions", localizedErrorMessages);
        }
        if (abstractCoreException.isWarningLevelExceptionMessagesIncluded())  {
            Collection<String> localizedErrorMessages = getLocalizedExceptionMessages(abstractCoreException.getWarningLevelExceptionIds(), locale);
            shoppingListModel.addErrorStatuses("warningLevelExceptions", localizedErrorMessages);
        }
        if (abstractCoreException.isInformativeLevelExceptionMessagesIncluded()) {
            Collection<String> localizedErrorMessages = getLocalizedExceptionMessages(abstractCoreException.getInformativeLevelExceptionIds(), locale);
            shoppingListModel.addErrorStatuses("informativeLevelExceptions", localizedErrorMessages);
        }
        //return productModel.getErrorStatuses();
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        SerializerUtil.jsonSerialize(ServletUtil.getWriter(response), shoppingListModel.getErrorStatuses());
    }


    private Collection<String> getLocalizedExceptionMessages(Collection<String> localizedExceptionIds, Locale locale) {
        Collection<String> localizedErrorMessages = new ArrayList<String>();
        for (String uniqueId : localizedExceptionIds) {
            String message = applicationContext.getMessage(uniqueId, null, locale);
            localizedErrorMessages.add(message);
        }
        return localizedErrorMessages;
    }



    @Log
    @AuditTrail(argumentStrategy=ArgumentStrategy.ALL)
    @RequestMapping(method = RequestMethod.GET)
    public String ManageShoppingLists(Model model){
        List<ShoppingList> shoppingLists = new ArrayList<ShoppingList>(shoppingListService.loadAll());

        model.addAttribute("shoppinglists", shoppingLists);
        model.addAttribute(new ShoppingListModel());

        return "shoppinglist/myshoplist";
    }


    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") String itemId, Model model){
        shoppingListService.delete(shoppingListService.loadById(itemId));

        model.addAttribute(new ShoppingListModel());
        return "redirect:/manager/shoppinglist";
    }


    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") String itemId, Model model){

         model.addAttribute("productlist", shoppingListService.listAllProductsInShoppingList(shoppingListService.loadById(itemId)));
         model.addAttribute("name",shoppingListService.loadById(itemId).getName());

        return "shoppinglist/view";
    }


    @Log
    @AuditTrail(argumentStrategy=ArgumentStrategy.ALL)
    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute ShoppingListModel shoppingListModel) {

        logger.error("asdf!!!!!!!!!!!!!!");

        shoppingListService.create(shoppingListModel.getShoppingList());
        return "redirect:/manager/shoppinglist";
    }

    private Map<String, String> getValidationMessages(Set<ConstraintViolation<Catalogue>> failures) {
        Map<String, String> failureMessages = new HashMap<String, String>();
        for (ConstraintViolation<Catalogue> failure : failures) {
            failureMessages.put(failure.getPropertyPath().toString(), failure.getMessage());
        }
        return failureMessages;
    }


}