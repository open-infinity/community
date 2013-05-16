/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openinfinity.web.controller;

import org.openinfinity.core.annotation.AuditTrail;
import org.openinfinity.core.annotation.Log;
import org.openinfinity.core.aspect.ArgumentStrategy;
import org.openinfinity.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Jarno Knihtilä
 */
@Controller
@RequestMapping(value = "/catalogue")
public class CatalogueController {

    @Autowired
    private ProductService productService;

    @Log
    @AuditTrail(argumentStrategy = ArgumentStrategy.ALL)
    @RequestMapping(method = RequestMethod.GET)
    public String products(Model model) {
        model.addAttribute("products", productService.loadAll());
        return "catalogue/catalogue";
    }
}
