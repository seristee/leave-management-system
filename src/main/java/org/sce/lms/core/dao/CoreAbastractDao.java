package org.sce.lms.core.dao;

import javassist.bytecode.stackmap.TypeData;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.List;

public abstract class CoreAbastractDao {
    protected Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private EntityManager entityManager;

    /**
     *
     * @return
     */
    public Session getSession(){
        return entityManager.unwrap(Session.class);
    }

    /**
     *
     * @param entity
     * @param id
     * @Description deletes the entity from the database
     */
    public void deleteEntity(Class<?> entity, Long id){
        Object object = getSession().load(entity, id);
        if(object != null) {
            getSession().delete(entity);
        }
    }

    // Todo set objects active value to false for soft delete
    public Object softDeleteEntity(String className, Long id, String identifier, Object identifierValue) throws ClassNotFoundException, NoSuchFieldException {
        Class clazz = Class.forName(className);
        Field field = clazz.getField(identifier);
        if(field != null){
            Object object = getEntityById(clazz,id);

        }
        return null;
    }

    /**
     *
     * @param entity
     * @param id
     */
    public void updateEntity(Class<?> entity, Long id) {
        Object object = getEntityById(entity, id);
        if (object != null) {
            getSession().update(entity);
        }
    }

    public void mergeEntity(Object object) {
        getSession().evict(object);
        getSession().merge(object);
        getSession().flush();
    }

    public Object getEntityById(Class<?> entity, Long id) {
        return getSession().get(entity, id);
    }

    public long saveEntityReturnId(Object object) {
        getSession().evict(object);
        return (long) getSession().save(object);
    }

    public void saveOrUpdateEntity(Object entity) {
        getSession().evict(entity);
        getSession().saveOrUpdate(entity);
    }

    public void saveEntity(Object object) {
        getSession().saveOrUpdate(object);
    }

    public <T> List<T> getAllEntitiesByCriteria(Class<T> entity, String field, Object param) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<?> criteria = builder.createQuery(entity);
        Root<?> root = criteria.from(entity);
        criteria.where(builder.equal(root.get(field), param));
        return (List<T>) getSession().createQuery(criteria).getResultList();
    }

    public <T> List<T> getAllEntities(Class<T> entity) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<?> criteria = builder.createQuery(entity);
        Root<?> root = criteria.from(entity);
        criteria.where(builder.equal(root.get("active"), true));
        return (List<T>) getSession().createQuery(criteria).getResultList();
    }

    public <T> List<T> getAllEntitiesLimit(Class<T> entity, int limit, String order) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<?> criteria = builder.createQuery(entity);
        Root<?> root = criteria.from(entity);
        criteria.where(builder.equal(root.get("active"), true));
        criteria.orderBy(builder.asc(root.get(order)));
        return (List<T>) getSession().createQuery(criteria).setMaxResults(limit).getResultList();
    }

    public <T> List<T> getOrderedEntities(Class<T> entity, String orderByField) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<?> criteria = builder.createQuery(entity);
        Root<?> root = criteria.from(entity);
        criteria.where(builder.equal(root.get("active"), true));
        criteria.orderBy(builder.asc(root.get(orderByField)));
        return (List<T>) getSession().createQuery(criteria).getResultList();
    }

    public Boolean checkIfEntityExists(Class<?> entity, String identifierKey, Object identifierValue)
            throws HibernateException {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<?> criteria = builder.createQuery(entity);
        Root<?> root = criteria.from(entity);
        criteria.where(builder.equal(root.get("active"), true));
        criteria.where(builder.equal(root.get(identifierKey), identifierValue));
        return getSession().createQuery(criteria).getSingleResult() != null;
    }

    public void persist(Object entity) {
        getSession().persist(entity);
    }

    public <T> Object getEntityByCriteria(Class<T> entity, String dbField, Object value) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<?> criteria = builder.createQuery(entity);
        Root<?> root = criteria.from(entity);
        Predicate predicate = builder.and(builder.equal(root.get(dbField), value),
                builder.equal(root.get("active"), true));
        criteria.where(predicate);
        try {
            return getSession().createQuery(criteria).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<?> getAllEntitiesBySQL(String sql) {
        return getSession().createSQLQuery(sql).list();
    }
}
