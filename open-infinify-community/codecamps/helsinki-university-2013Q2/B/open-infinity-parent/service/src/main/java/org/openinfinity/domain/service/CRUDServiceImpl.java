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

import java.util.Collection;

import org.openinfinity.core.annotation.AuditTrail;
import org.openinfinity.core.annotation.Log;
import org.openinfinity.core.exception.ExceptionLevel;
import org.openinfinity.core.util.ExceptionUtil;
import org.openinfinity.domain.entity.Product;
import org.openinfinity.domain.repository.CRUDRepository;
import org.openinfinity.domain.repository.ProductRepository;
import org.openinfinity.domain.repository.RepositoryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Product service implementation with specification.
 * 
 * @author Ilkka Leinonen
 */
@Service
public class CRUDServiceImpl<T extends RepositoryItem> implements CRUDService<T> {

	@Autowired
	private ProductSpecification productSpecification;
	
	@Autowired
	private CRUDRepository crudRepository;


    @Override
    public String create(T item) {
        crudRepository.create(item);
        return item.getId();
    }

    @Override
    public void update(T item) {
        if(item.getId()==null || crudRepository.loadById(item.getId())==null){ //if product does not yet exist in repository
            ExceptionUtil.throwApplicationException("Trying to update item that does not exist in repository");
        }
    }

    @Override
    public Collection<T> loadAll() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public T loadById(String id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(T item) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getItemAlreadyExistsUniqueException() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
