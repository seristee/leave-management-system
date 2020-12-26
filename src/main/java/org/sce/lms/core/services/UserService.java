package org.sce.lms.core.services;

import org.hibernate.SessionFactory;
import org.sce.lms.core.model.user.model.User;

public interface UserService {

    void setSessionFactory(SessionFactory sessionFactory);

    void save(User user);

    User findById(Long id);

    User findByUsername(String username);
}
