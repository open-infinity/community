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

import org.openinfinity.domain.entity.Catalogue;
import org.openinfinity.domain.entity.Product;
import org.openinfinity.domain.repository.CatalogueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Product service implementation with specification.
 * 
 * @author Joosa Kurvinen
 */
@Service
public class CatalogueServiceImpl extends CRUDServiceImpl<Catalogue, CatalogueRepository> implements CatalogueService{

    @Autowired
    private CatalogueRepository catalogueRepository;

    @Autowired
    private ProductService productService;

    public CatalogueServiceImpl(){

    }

    @Autowired
    public CatalogueServiceImpl(CatalogueRepository catalogueRepository) {
        super(catalogueRepository);
    }

    @Override
    public void delete(Catalogue item) {
        for(Product product : item.getProducts()){
            productService.delete(product);
        }
        catalogueRepository.delete(item);
    }

    @Override
    @Transactional
    public void addProductToCatalogue(Product product, Catalogue catalogue) {
        catalogue.addProduct(product);
        catalogueRepository.update(catalogue);
    }

    @Override
    @Transactional
    public void removeProductFromCatalogue(Product product, Catalogue catalogue) {
        catalogue.removeProduct(product);
        catalogueRepository.update(catalogue);
    }

    @Override
    public List<Product> listAllProductsInCatalogue(Catalogue catalogue) {
        return(new ArrayList<Product>(catalogue.getProducts()));

    }
}
