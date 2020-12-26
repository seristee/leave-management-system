package org.sce.lms.core.services;


import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.sce.lms.core.dao.CoreAbastractDao;
import org.sce.lms.core.model.user.dao.UserDao;
import org.sce.lms.core.model.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
@Transactional
public class UserServiceImpl extends CoreAbastractDao implements UserService {

    @Autowired
    private UserDao dao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.setSessionFactory(sessionFactory);
    }

    @Override
    public void save(User user) {
        if (!user.getPassword().equals("")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            dao.save(user);
        }
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }
}

