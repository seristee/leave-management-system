package org.sce.lms.leave.repository;

import org.sce.lms.leave.model.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveTypeRepository extends JpaRepository<LeaveType, Long> { }
