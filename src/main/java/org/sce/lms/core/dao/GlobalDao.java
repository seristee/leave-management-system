package org.sce.lms.core.dao;

import org.hibernate.SessionFactory;

public interface GlobalDao {
    void setSessionFactory(SessionFactory sessionFactory);
}
