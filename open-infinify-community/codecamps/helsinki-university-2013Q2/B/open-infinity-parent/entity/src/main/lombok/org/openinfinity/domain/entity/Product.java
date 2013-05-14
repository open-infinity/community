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

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.openinfinity.core.annotation.NotScript;
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
public class Product {

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
	
}
