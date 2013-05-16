/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openinfinity.domain.service;

import java.util.Collection;
import org.openinfinity.domain.entity.Role;

/**
 *
 * @author jknihtil
 */
public interface RoleService {
    Collection<Role> findAll();
}
