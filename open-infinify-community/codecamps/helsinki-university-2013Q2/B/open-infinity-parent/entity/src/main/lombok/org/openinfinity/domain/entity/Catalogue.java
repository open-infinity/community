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

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.openinfinity.core.annotation.NotScript;
import org.openinfinity.domain.repository.CRUDRepository;
import org.openinfinity.domain.repository.RepositoryItem;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Catalogue entity.
 * 
 * @author Joosa Kurvinen
 */
@Document
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Catalogue implements RepositoryItem {
	
	@Id @NotScript
	private String id;

    @NotScript @NotNull @NonNull @Size(min = 1, max=100)
    private String name;

    //@OneToMany(mappedBy = "catalogue", cascade = {CascadeType.ALL})
	private Collection<Product> products = Collections.checkedCollection(new ArrayList<Product>(), Product.class);

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void removeProduct(Product product) {
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

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
