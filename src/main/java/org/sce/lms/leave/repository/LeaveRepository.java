package org.sce.lms.leave.repository;

import org.sce.lms.leave.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRepository extends JpaRepository<Leave, Long> {
    List<Leave> findLeaveByUser(Long id);
}
