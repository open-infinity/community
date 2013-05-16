/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openinfinity.domain.repository;

import java.util.List;
import org.openinfinity.domain.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author jknihtil
 */
public interface UserRepository extends MongoRepository<User, String> {
    List<User> findByUserName(String userName);
}
