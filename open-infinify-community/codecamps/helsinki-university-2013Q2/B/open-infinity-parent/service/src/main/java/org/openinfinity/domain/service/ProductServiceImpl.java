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
package org.openinfinity.domain.service;

import org.openinfinity.core.annotation.AuditTrail;
import org.openinfinity.core.annotation.Log;
import org.openinfinity.core.exception.ExceptionLevel;
import org.openinfinity.core.util.ExceptionUtil;
import org.openinfinity.domain.entity.Catalogue;
import org.openinfinity.domain.entity.Product;
import org.openinfinity.domain.repository.CatalogueRepository;
import org.openinfinity.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Product service implementation with specification.
 * 
 * @author Joosa Kurvinen
 */
@Service
public class ProductServiceImpl extends CRUDServiceImpl<Product, ProductRepository> implements ProductService{

	@Autowired
	private ProductSpecification productSpecification;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CatalogueService catalogueService;

    public ProductServiceImpl(){

    }

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        super(productRepository);
    }



    @Override
    @Log
    @AuditTrail
    @Transactional
    public String create(Product product) {
        Collection<Product> products = productRepository.loadByName(product.getName());
        if (productSpecification.isNotEligibleForCreation(product, products)) {
            ExceptionUtil.throwBusinessViolationException(
                    "Product already exists: " + product.getName(),
                    ExceptionLevel.INFORMATIVE,
                    ProductService.UNIQUE_EXCEPTION_PRODUCT_ALREADY_EXISTS);
        }
        productRepository.create(product);
        return product.getId();
    }



    @Override
    @Transactional
    public void delete(Product product) {
        for(Catalogue c : catalogueService.loadAll()){
            if(c.getProducts().contains(product)){
                c.removeProduct(product);
                catalogueService.update(c);
                break;
            }
        }
        productRepository.delete(product);
    }


}
