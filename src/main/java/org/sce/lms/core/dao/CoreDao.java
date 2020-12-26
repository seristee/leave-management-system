package org.sce.lms.core.dao;

import java.util.List;

public interface CoreDao extends GlobalDao{
    void saveObject(Object object);

    void saveUpdateObject(Object object);

    void deleteObject(Object object, Long id);

    long saveRetId(Object object);

    void mergeObject(Object object);

    List<?> getAllObjects(Class<?> entity);

    Object getObjectById(Class<?> entity, Long id);

    Object getObjectByCriteria(Class<?> entity, String dbField, Object value);

    List<?> getAllObjectsByCriteria(Class<?> entity, String dbField, Object value);

    Object checkObject(Class<?> entity, String dbField, Object value);
}
