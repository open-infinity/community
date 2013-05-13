/*
 * Copyright (c) 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openinfinity.web.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;

import org.codehaus.jackson.map.ObjectMapper;
import org.openinfinity.core.annotation.AuditTrail;
import org.openinfinity.core.annotation.Log;
import org.openinfinity.core.aspect.ArgumentStrategy;
import org.openinfinity.core.exception.AbstractCoreException;
import org.openinfinity.core.exception.ApplicationException;
import org.openinfinity.core.exception.BusinessViolationException;
import org.openinfinity.core.exception.SystemException;
import org.openinfinity.domain.entity.Product;
import org.openinfinity.domain.service.ProductService;
import org.openinfinity.web.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * Product controller for m-v-c binding and service orchestration.
 * 
 * @author Ilkka Leinonen
 */
@Controller
@RequestMapping(value = "/productModel")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private Validator validator;
	
	//TODO: FIX ME
	@Log
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({SystemException.class, ApplicationException.class, BusinessViolationException.class})
	public void exceptionOccurred(Throwable throwable, HttpServletResponse response) {
	//public @ResponseBody Map<String, ? extends Object> exceptionOccurred(Throwable throwable, HttpServletResponse response) {
	//public ModelAndView exceptionOccurred(Throwable throwable) {
		ModelAndView modelAndView = new ModelAndView("product/editProduct");
		ProductModel productModel = new ProductModel();
		if (throwable instanceof AbstractCoreException) {
			AbstractCoreException abstractCoreException = (AbstractCoreException)throwable;
			if (abstractCoreException.isErrorLevelExceptionMessagesIncluded()) {
				modelAndView.addObject("errorLevelExceptions", abstractCoreException.getErrorLevelExceptionIds());
				productModel.addErrorStatuses("errorLevelExceptions", abstractCoreException.getErrorLevelExceptionIds());
			}
			if (abstractCoreException.isWarningLevelExceptionMessagesIncluded()) {
				modelAndView.addObject("warningLevelExceptions", abstractCoreException.getWarningLevelExceptionIds());
				productModel.addErrorStatuses("errorLevelExceptions", abstractCoreException.getWarningLevelExceptionIds());
			}
			if (abstractCoreException.isInformativeLevelExceptionMessagesIncluded()) {
				//modelAndView.addObject("informativeLevelExceptions", abstractCoreException.getInformativeLevelExceptionIds());
				productModel.addErrorStatuses("errorLevelExceptions", abstractCoreException.getInformativeLevelExceptionIds());
			}
		}
		//if (throwable.getCause() != null && throwable.getCause().getMessage() != null)
			//modelAndView.addObject("nonstandard.message", throwable.getCause().getMessage());
		modelAndView.addObject("productModel", productModel);
		//return modelAndView.getModel();
		//return productModel.getErrorStatuses();
		//return modelAndView;
		try {
			jsonSerialize(response.getWriter(), productModel.getErrorStatuses());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void jsonSerialize(Writer writer, Object serializable) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(writer, serializable);
		} catch (Throwable t) {
		 t.printStackTrace();	
		} 
	}
	
	@Log
	@AuditTrail(argumentStrategy=ArgumentStrategy.ALL)
	@RequestMapping(method = RequestMethod.GET)
	public String createNewProduct(Model model) {
		model.addAttribute(new ProductModel());
		return "product/editProduct";
	}
	
	@Log
	@AuditTrail(argumentStrategy=ArgumentStrategy.ALL) 
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Map<String, ? extends Object> create(@Valid @RequestBody ProductModel productModel, HttpServletResponse response) {
		Set<ConstraintViolation<Product>> failures = validator.validate(productModel.getProduct());
		if (!failures.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return getValidationMessages(failures);
		} else {
			String id = productService.create(productModel.getProduct());
			return new ModelMap("id", id);
		}
	}
	
	private Map<String, String> getValidationMessages(Set<ConstraintViolation<Product>> failures) {
		Map<String, String> failureMessages = new HashMap<String, String>();
		for (ConstraintViolation<Product> failure : failures) {
			failureMessages.put(failure.getPropertyPath().toString(), failure.getMessage());
		}
		return failureMessages;
	}
	
}
