package org.openinfinity.domain.service;

import org.openinfinity.core.annotation.AuditTrail;
import org.openinfinity.core.annotation.Log;
import org.openinfinity.core.exception.ExceptionLevel;
import org.openinfinity.core.util.ExceptionUtil;
import org.openinfinity.domain.entity.Product;
import org.openinfinity.domain.repository.RepositoryItem;

import java.util.Collection;

/**
 * @author Joosa Kurvinen
 */
public interface CRUDService<T extends RepositoryItem> {

    public String create(T item);

    public void update(T item);

    public Collection<T> loadAll();

    public T loadById(String id);

    public void delete (T item);


}
