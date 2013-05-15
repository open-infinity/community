package org.openinfinity.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import org.openinfinity.core.annotation.AuditTrail;
import org.openinfinity.core.annotation.Log;
import org.openinfinity.core.aspect.ArgumentStrategy;
import org.openinfinity.core.exception.AbstractCoreException;
import org.openinfinity.core.exception.ApplicationException;
import org.openinfinity.core.exception.BusinessViolationException;
import org.openinfinity.core.exception.SystemException;
import org.openinfinity.domain.entity.Catalogue;
import org.openinfinity.domain.service.CatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/manager")
public class CatalogueController {

    @Autowired
    private CatalogueService catalogueService;

    @Autowired
    ApplicationContext applicationContext;


    @Log
    @ExceptionHandler({SystemException.class, ApplicationException.class, BusinessViolationException.class})
    public void exceptionOccurred(AbstractCoreException abstractCoreException, HttpServletResponse response, Locale locale){

    }

    @Log
    @AuditTrail(argumentStrategy=ArgumentStrategy.ALL)
    @RequestMapping(value = "/allCatalogues")
    public String ListAllCatalogues(Model model){

        return "catalogue/listAll";
    }


    @Log
    @AuditTrail(argumentStrategy=ArgumentStrategy.ALL)
    @RequestMapping(value = "/catalogue")
    public String ManageCatalogues(Model model){

        Catalogue c = new Catalogue();
        catalogueService.create(c);


        List<Catalogue> catalogs = new ArrayList<Catalogue>(catalogueService.loadAll());

        model.addAttribute("catalogs", catalogs);

        return "catalogue/listAll";
    }


}