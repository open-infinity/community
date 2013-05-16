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

import java.math.BigInteger;
import java.util.Collection;

import org.openinfinity.domain.entity.Product;

/**
 * Product service interface.
 *
 * @author Ilkka Leinonen
 */
public interface ProductService {

    public static final String UNIQUE_EXCEPTION_PRODUCT_ALREADY_EXISTS = "localized.exception.product.already.exists";
    public static final String PRODUCT_NOT_FOUND = "localized.exception.product.not.found";

    public Product create(Product product);

    public void update(Product product);

    public Collection<Product> loadAll();

    public Product loadById(BigInteger id);

    public void delete(Product product);
}
