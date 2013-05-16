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
import org.openinfinity.domain.entity.ShoppingList;
import org.openinfinity.domain.repository.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Product service implementation with specification.
 * 
 * @author Ilkka Leinonen
 */
@Service
public class ShoppingListServiceImpl extends CRUDServiceImpl<ShoppingList, ShoppingListRepository> implements ShoppingListService {

    @Autowired
    private ShoppingListRepository shoppingListRepository;


    public ShoppingListServiceImpl(){

    }

    @Autowired
    public ShoppingListServiceImpl(ShoppingListRepository shoppingListRepository) {
        super(shoppingListRepository);
    }

    @Override
    @Transactional
    public void addProductToShoppingList(Product product, ShoppingList shoppingList) {
        shoppingList.addProduct(product);
        shoppingListRepository.update(shoppingList);
    }

    @Override
    @Transactional
    public void removeProductFromShoppingList(Product product, ShoppingList shoppingList) {
        shoppingList.removeProduct(product);
        shoppingListRepository.update(shoppingList);
    }

    @Override
    @Transactional
    public Collection<Product> listAllProductsInShoppingList(ShoppingList shoppingList) {
        return(shoppingList.getProducts());

    }
}
