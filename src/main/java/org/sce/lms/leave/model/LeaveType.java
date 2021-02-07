package org.sce.lms.leave.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.sce.lms.core.model.ActivatableEntity;
import org.sce.lms.core.model.ActivatableNamedConstantEntity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Table(name = "lms_leave_type")
@ToString
@AttributeOverride(name = "id", column = @Column(name = "leave_type_id"))
public class LeaveType extends ActivatableNamedConstantEntity {
}
