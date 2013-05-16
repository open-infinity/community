/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openinfinity.domain.repository;

import org.openinfinity.domain.entity.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author jknihtil
 */
public interface RoleRepository extends MongoRepository<Role, String> {
    
}
