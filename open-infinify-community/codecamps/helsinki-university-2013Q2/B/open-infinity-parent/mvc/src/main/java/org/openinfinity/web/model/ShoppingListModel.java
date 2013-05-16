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

import org.openinfinity.domain.entity.Catalogue;
import org.openinfinity.domain.entity.ShoppingList;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Product model for exposing product domain to view. 
 * 
 * @author Ilkka Leinonen
 */
public class ShoppingListModel extends ShoppingList {

	private Map<String, Collection<String>> errorStatuses = new HashMap<String, Collection<String>>();
	
	public void addErrorStatuses(String level, Collection<String> ids) {
		errorStatuses.put(level, ids);
	}
	
	public Map<String, Collection<String>> getErrorStatuses() {
		return errorStatuses;
	}

	public void setErrorStatuses(Map<String, Collection<String>> errorStatuses) {
		this.errorStatuses = errorStatuses;
	}

	public ShoppingList getShoppingList() {
		ShoppingList shoppingList = new ShoppingList();
        shoppingList.setName(super.getName());
		return shoppingList;
	}

}
