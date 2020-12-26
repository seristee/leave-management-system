package org.sce.lms.core.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CoreDaoImpl extends CoreAbastractDao implements CoreDao{
    @Override
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }

    @Override
    public void saveObject(Object object) {
        saveEntity(object);
    }

    @Override
    public void saveUpdateObject(Object object) {
        saveOrUpdateEntity(object);
    }

    @Override
    public void deleteObject(Object object, Long id) {
        deleteEntity((Class<?>) object, id);
    }

    @Override
    public long saveRetId(Object object) {
        return saveEntityReturnId(object);
    }

    @Override
    public void mergeObject(Object object) {
        mergeEntity(object);
    }

    @Override
    public List<?> getAllObjects(Class<?> entity) {
        return getAllEntities(entity);
    }

    @Override
    public Object getObjectById(Class<?> entity, Long id) {
        return getEntityById(entity, id);
    }

    @Override
    public Object getObjectByCriteria(Class<?> entity, String dbField, Object value) {
        return getEntityByCriteria(entity, dbField, value);
    }

    @Override
    public List<?> getAllObjectsByCriteria(Class<?> entity, String dbField, Object value) {
        return getAllEntitiesByCriteria(entity, dbField, value);
    }

    @Override
    public Object checkObject(Class<?> entity, String dbField, Object value) {
        return checkIfEntityExists(entity, dbField, value);
    }
}
