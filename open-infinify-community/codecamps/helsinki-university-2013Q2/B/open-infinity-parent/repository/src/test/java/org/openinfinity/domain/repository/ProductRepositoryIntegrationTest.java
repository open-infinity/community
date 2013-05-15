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
package org.openinfinity.domain.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openinfinity.domain.entity.Product;
import org.openinfinity.domain.service.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Product Repository integration tests.
 * 
 * @Author Ilkka Leinonen 
 * 
 */
@ContextConfiguration(locations={"classpath*:**/test-repository-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductRepositoryIntegrationTest {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductSpecification productionSpecification;
	
	@Before
	public void setUp() throws Exception {}

	@After
	public void tearDown() throws Exception {}

    @Test @Ignore
    public void givenKnownProductWhenStoringToBackendRepositoryThenProductMustBeFoundWithId() {
        Product expected = createProduct();
        String id = productRepository.create(expected);
        Product actual = productRepository.loadById(id);
        assertProduct(actual, expected);
    }

    @Test @Ignore
    public void givenKnownProductWhenStoringToBackendRepositoryThenProductMustBeFoundWithName() {
        Product expected = createProduct();
        productRepository.create(expected);
        Product actual = new ArrayList<Product>(productRepository.loadByName(expected.getName())).get(1);
        assertProduct(actual, expected);
    }

    private void assertProduct(Product actual, Product expected) {
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getCompany(), actual.getCompany());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getPrice(), actual.getPrice());
        assertNotNull(actual.getId());
    }


    private Product createProduct() {
		Product expected = new Product();
		expected.setCompany("TestComponany");
		expected.setDescription("Test Description");
		expected.setName("Test Name");
		expected.setPrice(new BigDecimal("12.3"));
		return expected;
	}
	
//	@Test @Ignore
//	public void testFail() {
//		fail("Not yet implemented");
//	}	

}
