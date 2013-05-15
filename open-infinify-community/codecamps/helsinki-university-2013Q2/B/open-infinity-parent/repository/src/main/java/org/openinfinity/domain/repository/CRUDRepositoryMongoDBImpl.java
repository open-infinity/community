
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
 */package org.openinfinity.domain.repository;

import org.openinfinity.core.annotation.AuditTrail;
import org.openinfinity.core.annotation.Log;
import org.openinfinity.domain.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Product repository implementation.
 * 
 * @author Ilkka Leinonen
 */
@Repository
public class CRUDRepositoryMongoDBImpl<T extends RepositoryItem> implements CRUDRepository<T> {

	@Autowired
	MongoTemplate mongoTemplate;

    @Override
    public String create(RepositoryItem item) {
        mongoTemplate.insert(item);
        return item.getId();
    }

    @Override
    public void update(RepositoryItem item) {
        mongoTemplate.save(item);
    }

    @Override
    public Collection loadAll() {
        return mongoTemplate.findAll(getGenericClassType());
    }

    @Override
    public T loadById(String id) {
        return mongoTemplate.findById(id, getGenericClassType());
    }

    @Override
    public void delete(RepositoryItem item) {
        mongoTemplate.remove(item);
    }

    private Class<T> getGenericClassType(){
        return (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), CRUDRepositoryMongoDBImpl.class);
    }
}
