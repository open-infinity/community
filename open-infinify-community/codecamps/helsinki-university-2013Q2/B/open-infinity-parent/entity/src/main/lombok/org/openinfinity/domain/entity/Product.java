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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.openinfinity.core.annotation.NotScript;
import org.openinfinity.domain.repository.RepositoryItem;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.NumberFormat;

/**
 * Product entity.
 * 
 * @author Ilkka Leinonen
 */
@Document
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Product implements RepositoryItem{

	@Id @NotScript
	private String id;

	@NotScript @NotNull @NonNull
	private String name;
	
	@NotScript @NotNull @NonNull
	private String company;
	
	@NotScript @NotNull @NonNull
	private String description;
	
	@NumberFormat @DecimalMin("0.00") @NotNull @NonNull
	private BigDecimal price;

    //@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    //private Catalogue catalogue;

    //@ManyToOne()
    private List<ShoppingList> shoppingLists = new ArrayList<ShoppingList>();

//	private Product(String name, String company, String description, BigDecimal price) {
//		this.name = name;
//		this.company = company;
//		this.description = description;
//		this.price = price;
//	}
//	
//	public static Product newInstance(String name, String company, String description, BigDecimal price) {
//		return new Product(name, company, description, price);
//	}


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


}
