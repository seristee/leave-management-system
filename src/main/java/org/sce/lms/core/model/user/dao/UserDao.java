package org.sce.lms.core.model.user.dao;

import org.sce.lms.core.model.user.model.User;

public interface UserDao {
    void save(User user);

    public User findById(Long id);

    User findBySSo(String username);
}
