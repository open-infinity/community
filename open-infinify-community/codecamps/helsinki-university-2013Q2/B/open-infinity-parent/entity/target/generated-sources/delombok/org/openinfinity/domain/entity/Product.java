// Generated by delombok at Wed May 15 12:49:58 EEST 2013
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
import lombok.NonNull;
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
public class Product implements RepositoryItem {
	@Id
	@NotScript
	private String id;
	@NotScript
	@NotNull
	@NonNull
	private String name;
	@NotScript
	@NotNull
	@NonNull
	private String company;
	@NotScript
	@NotNull
	@NonNull
	private String description;
	@NumberFormat
	@DecimalMin("0.00")
	@NotNull
	@NonNull
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
	
	@java.lang.Override
	@java.lang.SuppressWarnings("all")
	public boolean equals(final java.lang.Object o) {
		if (o == this) return true;
		if (!(o instanceof Product)) return false;
		final Product other = (Product)o;
		if (!other.canEqual((java.lang.Object)this)) return false;
		if (this.getId() == null ? other.getId() != null : !this.getId().equals((java.lang.Object)other.getId())) return false;
		if (this.getName() == null ? other.getName() != null : !this.getName().equals((java.lang.Object)other.getName())) return false;
		if (this.getCompany() == null ? other.getCompany() != null : !this.getCompany().equals((java.lang.Object)other.getCompany())) return false;
		if (this.getDescription() == null ? other.getDescription() != null : !this.getDescription().equals((java.lang.Object)other.getDescription())) return false;
		if (this.getPrice() == null ? other.getPrice() != null : !this.getPrice().equals((java.lang.Object)other.getPrice())) return false;
		return true;
	}
	
	@java.lang.SuppressWarnings("all")
	public boolean canEqual(final java.lang.Object other) {
		return other instanceof Product;
	}
	
	@java.lang.Override
	@java.lang.SuppressWarnings("all")
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = result * PRIME + (this.getId() == null ? 0 : this.getId().hashCode());
		result = result * PRIME + (this.getName() == null ? 0 : this.getName().hashCode());
		result = result * PRIME + (this.getCompany() == null ? 0 : this.getCompany().hashCode());
		result = result * PRIME + (this.getDescription() == null ? 0 : this.getDescription().hashCode());
		result = result * PRIME + (this.getPrice() == null ? 0 : this.getPrice().hashCode());
		return result;
	}
	
	@java.lang.Override
	@java.lang.SuppressWarnings("all")
	public java.lang.String toString() {
		return "Product(id=" + this.getId() + ", name=" + this.getName() + ", company=" + this.getCompany() + ", description=" + this.getDescription() + ", price=" + this.getPrice() + ")";
	}
	
	@java.lang.SuppressWarnings("all")
	public Product() {
	}
	
	@java.beans.ConstructorProperties({"name", "company", "description", "price"})
	@java.lang.SuppressWarnings("all")
	public Product(@NonNull final String name, @NonNull final String company, @NonNull final String description, @NonNull final BigDecimal price) {
		if (name == null) throw new java.lang.NullPointerException("name");
		if (company == null) throw new java.lang.NullPointerException("company");
		if (description == null) throw new java.lang.NullPointerException("description");
		if (price == null) throw new java.lang.NullPointerException("price");
		this.name = name;
		this.company = company;
		this.description = description;
		this.price = price;
	}
}