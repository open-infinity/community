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

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
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
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
 * Product service implementation with specification.
 * 
 * @author Joosa Kurvinen
 */
@Service
public class CRUDServiceImpl<T extends RepositoryItem, T_REPO extends CRUDRepository<T>> implements CRUDService<T> {

	@Autowired
	private ProductSpecification productSpecification;

	private CRUDRepository<T> crudRepository;

    public CRUDServiceImpl() {
    }

    public CRUDServiceImpl(T_REPO repository) {
        this.crudRepository = repository;
    }


    @Override
    @Transactional
    public String create(T item) {
        crudRepository.create(item);
        return item.getId();
    }

    @Override
    @Transactional
    public void update(T item) {
        if(item.getId()==null || crudRepository.loadById(item.getId())==null){ //if product does not yet exist in repository
            ExceptionUtil.throwApplicationException("Trying to update item that does not exist in repository");
        }
        else crudRepository.update(item);
    }

    @Override
    @Transactional
    public Collection<T> loadAll() {
        return crudRepository.loadAll();
    }

    @Override
    @Transactional
    public T loadById(String id) {
        return crudRepository.loadById(id);
    }

    @Override
    @Transactional
    public void delete(T item) {
        crudRepository.delete(item);
    }


}
