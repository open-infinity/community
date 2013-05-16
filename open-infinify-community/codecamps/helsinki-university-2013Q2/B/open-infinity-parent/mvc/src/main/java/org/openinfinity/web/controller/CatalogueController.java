package org.openinfinity.web.controller;

import java.math.BigDecimal;
import java.util.*;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;

import org.openinfinity.core.annotation.AuditTrail;
import org.openinfinity.core.annotation.Log;
import org.openinfinity.core.aspect.ArgumentStrategy;
import org.openinfinity.core.exception.AbstractCoreException;
import org.openinfinity.core.exception.ApplicationException;
import org.openinfinity.core.exception.BusinessViolationException;
import org.openinfinity.core.exception.SystemException;
import org.openinfinity.domain.entity.Catalogue;
import org.openinfinity.domain.entity.Product;
import org.openinfinity.domain.service.CatalogueService;
import org.openinfinity.domain.service.ProductService;
import org.openinfinity.web.model.CatalogueModel;
import org.openinfinity.web.model.ProductModel;
import org.openinfinity.web.support.SerializerUtil;
import org.openinfinity.web.support.ServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value = "/manager")
public class CatalogueController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CatalogueService catalogueService;

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
        CatalogueModel catalogueModel = new CatalogueModel();
        if (abstractCoreException.isErrorLevelExceptionMessagesIncluded()) {
            Collection<String> localizedErrorMessages = getLocalizedExceptionMessages(abstractCoreException.getErrorLevelExceptionIds(), locale);
            catalogueModel.addErrorStatuses("errorLevelExceptions", localizedErrorMessages);
        }
        if (abstractCoreException.isWarningLevelExceptionMessagesIncluded())  {
            Collection<String> localizedErrorMessages = getLocalizedExceptionMessages(abstractCoreException.getWarningLevelExceptionIds(), locale);
            catalogueModel.addErrorStatuses("warningLevelExceptions", localizedErrorMessages);
        }
        if (abstractCoreException.isInformativeLevelExceptionMessagesIncluded()) {
            Collection<String> localizedErrorMessages = getLocalizedExceptionMessages(abstractCoreException.getInformativeLevelExceptionIds(), locale);
            catalogueModel.addErrorStatuses("informativeLevelExceptions", localizedErrorMessages);
        }
        //return productModel.getErrorStatuses();
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        SerializerUtil.jsonSerialize(ServletUtil.getWriter(response), catalogueModel.getErrorStatuses());
    }


    private Collection<String> getLocalizedExceptionMessages(Collection<String> localizedExceptionIds, Locale locale) {
        Collection<String> localizedErrorMessages = new ArrayList<String>();
        for (String uniqueId : localizedExceptionIds) {
            String message = applicationContext.getMessage(uniqueId, null, locale);
            localizedErrorMessages.add(message);
        }
        return localizedErrorMessages;
    }



    /*@Log
    @AuditTrail(argumentStrategy=ArgumentStrategy.ALL)
    @RequestMapping(value = "/allCatalogues")
    public String ListAllCatalogues(Model model){

        return "catalogue/listAll";
    } */


    @Log
    @AuditTrail(argumentStrategy=ArgumentStrategy.ALL)
    @RequestMapping(value = "catalogue")
    public String ManageCatalogues(Model model){
        Product testProduct = new Product();
        testProduct.setName("testinimi");
        testProduct.setCompany("company");
        testProduct.setPrice(BigDecimal.ONE);
        testProduct.setDescription("blaablaa");
        productService.create(testProduct);

        //List<Product> catalogs = new ArrayList<Product>(productService.loadAll());
        List<Catalogue> catalogs = new ArrayList<Catalogue>(catalogueService.loadAll());

        model.addAttribute("catalogs", catalogs);
        model.addAttribute(new CatalogueModel());

        return "catalogue/listAll";
    }


    @RequestMapping(value = "catalogue/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") String itemId, Model model){
        catalogueService.delete(catalogueService.loadById(itemId));

        model.addAttribute(new CatalogueModel());
        return "redirect:/manager/catalogue";
    }

    /*@RequestMapping(value = "catalogue", method = RequestMethod.POST)
    public String create(@ModelAttribute CatalogueModel catalogueModel, Model model){
        catalogueService.create(catalogueModel.getCatalogue());
        model.addAttribute(new CatalogueModel());
        return "redirect:/manager/cataloguemm";
    } */

    @RequestMapping(value = "catalogue/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") String itemId, Model model){

         model.addAttribute("productlist", catalogueService.listAllProductsInCatalogue(catalogueService.loadById(itemId)));
         model.addAttribute("name",itemId);

        return "catalogue/view";
    }


   /*
    @Log
    @AuditTrail(argumentStrategy=ArgumentStrategy.ALL)
    @RequestMapping(value = "catalogue", method = RequestMethod.POST)
    public @ResponseBody Map<String, ? extends Object> create(@Valid @ModelAttribute CatalogueModel catalogueModel, HttpServletResponse response) {
       // Set<ConstraintViolation<Catalogue>> failures = validator.validate(catalogueModel.getCatalogue());
       // if (failures.isEmpty()) {


            String id = catalogueService.create(catalogueModel.getCatalogue());

            return new ModelMap("id", id);
        //} else {
         //   response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
         //   return getValidationMessages(failures);
        //}
    }
     */

    @Log
    @AuditTrail(argumentStrategy=ArgumentStrategy.ALL)
    @RequestMapping(value = "catalogue", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute CatalogueModel catalogueModel, HttpServletResponse response) {
        // Set<ConstraintViolation<Catalogue>> failures = validator.validate(catalogueModel.getCatalogue());
        // if (failures.isEmpty()) {


        String id = catalogueService.create(catalogueModel.getCatalogue());

        return "redirect:/manager/catalogue";
        //} else {
        //   response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        //   return getValidationMessages(failures);
        //}
    }

    private Map<String, String> getValidationMessages(Set<ConstraintViolation<Catalogue>> failures) {
        Map<String, String> failureMessages = new HashMap<String, String>();
        for (ConstraintViolation<Catalogue> failure : failures) {
            failureMessages.put(failure.getPropertyPath().toString(), failure.getMessage());
        }
        return failureMessages;
    }


}