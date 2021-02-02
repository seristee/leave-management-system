package org.sce.lms.core.model.user.dao;

import org.sce.lms.core.dao.CoreAbastractDao;
import org.sce.lms.core.model.user.model.User;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl extends CoreAbastractDao implements UserDao {

    @Override
    public void save(User user) {
        saveOrUpdateEntity(user);
//        mergeEntity(user);
    }

    @Override
    public User findById(Long id) {
        return (User) getEntityById(User.class, id);
    }

    @Override
    public User findBySSo(String username) {
        return (User) getEntityByCriteria(User.class, "username", username);
    }
}
