package org.sce.lms.core.repositories;

import org.sce.lms.core.model.user.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Authority, Long> {
}
