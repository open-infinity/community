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

import java.math.BigInteger;
import java.util.Collection;
import org.bson.types.ObjectId;

import org.openinfinity.core.annotation.AuditTrail;
import org.openinfinity.core.annotation.Log;
import org.openinfinity.core.exception.ExceptionLevel;
import org.openinfinity.core.util.ExceptionUtil;
import org.openinfinity.domain.entity.Product;
import org.openinfinity.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Product service implementation with specification.
 *
 * @author Ilkka Leinonen
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductSpecification productSpecification;
    @Autowired
    private ProductRepository productRepository;

    @Log
    @AuditTrail
    public Product create(Product product) {
        Collection<Product> products = productRepository.findByName(product.getName());
        if (productSpecification.isNotEligibleForCreation(product, products)) {
            ExceptionUtil.throwBusinessViolationException(
                    "Product already exists: " + product.getName(),
                    ExceptionLevel.INFORMATIVE,
                    ProductService.UNIQUE_EXCEPTION_PRODUCT_ALREADY_EXISTS);
        }
        productRepository.save(product);
        return product;
    }

    @Log
    @AuditTrail
    public void update(Product product) {
        Product toBeUpdated = productRepository.findOne(product.getId());
        if (toBeUpdated == null) {
            ExceptionUtil.throwBusinessViolationException(
                    "Product already exists: " + product.getName(),
                    ExceptionLevel.INFORMATIVE,
                    ProductService.PRODUCT_NOT_FOUND);
        }
        updateDetails(toBeUpdated, product);
        productRepository.save(toBeUpdated);
    }

    private void updateDetails(Product old, Product updated) {
        updated.setCompany(old.getCompany());
        updated.setDescription(old.getDescription());
        updated.setName(old.getName());
        updated.setPrice(old.getPrice());
    }

    @Log
    @AuditTrail
    public Collection<Product> loadAll() {
        return productRepository.findAll();
    }

    @Log
    @AuditTrail
    public Product loadById(BigInteger id) {
        return productRepository.findOne(id);
    }

    @Log
    @AuditTrail
    public void delete(Product product) {
        productRepository.delete(product);
    }
}
