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
package org.openinfinity.web.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import lombok.NonNull;
import org.apache.log4j.Logger;
import org.openinfinity.core.annotation.NotScript;
import org.openinfinity.domain.entity.Product;
import org.openinfinity.domain.service.CatalogueService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Product model for exposing product domain to view. 
 * 
 * @author Ilkka Leinonen
 * @author Joosa Kurvinen
 */
public class ProductModel extends ProductWrapper {

    private static final Logger logger = Logger.getLogger(ProductModel.class);

    private Product product;

//  @NotNull
    @Pattern(regexp = "(\\w)+", message = "Invalid id: you need to create catalogue first")
    @Size(min = 3, message = "Invalid id: you need to create catalogue first")
    private String catalogueId;

	private Map<String, Collection<String>> errorStatuses = new HashMap<String, Collection<String>>();

    @Autowired
    private CatalogueService catalogueService;

	public void addErrorStatuses(String level, Collection<String> ids) {
		errorStatuses.put(level, ids);
	}
	
	public Map<String, Collection<String>> getErrorStatuses() {
		return errorStatuses;
	}

	public void setErrorStatuses(Map<String, Collection<String>> errorStatuses) {
		this.errorStatuses = errorStatuses;
	}

	@Override
	public String toString() {
		return "ProductModel [product=" + product + ", errorStatuses="
				+ errorStatuses + ", toString()=" + super.toString() + "]";
	}
	
	public Product getProduct() {
		Product product = new Product();
		product.setCompany(super.getCompany());
		product.setDescription(super.getDescription());
		//product.setId(super.getId());
		product.setName(super.getName());
		product.setPrice(super.getPrice());
        catalogueId = super.getCatalogueId();


        logger.error(
                "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
        );
        logger.error(super.getId());
        logger.error(catalogueId);

		return product;
	}

    public String getCatalogueId() {
        return catalogueId;
    }
}
