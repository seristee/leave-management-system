package org.sce.lms.leave.repository;

import org.sce.lms.leave.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRepository extends JpaRepository<Leave, Long> {
}
