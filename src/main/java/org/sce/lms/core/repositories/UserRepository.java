package org.sce.lms.core.repositories;

import org.sce.lms.core.model.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> { }
