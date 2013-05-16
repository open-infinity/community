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
package org.openinfinity.domain.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import lombok.Data;

import lombok.NonNull;
import org.openinfinity.core.annotation.NotScript;
import org.openinfinity.domain.repository.RepositoryItem;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

/**
 * Shopping list entity.
 * 
 * @author Joosa Kurvinen
 */
@Document
@Data
public class ShoppingList implements RepositoryItem {
	
	@Id @NotScript
	private String id;

    @NotScript @NonNull @NotNull
    private String name;

	private Collection<Product> products = Collections.checkedCollection(new ArrayList<Product>(), Product.class);

    public ShoppingList() {
    }

    public void addProduct(Product product) {
		this.products.add(product);
	}

    public void removeProduct(Product product){
        this.products.remove(product);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
