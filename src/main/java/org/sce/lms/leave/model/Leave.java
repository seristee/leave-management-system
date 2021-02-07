package org.sce.lms.leave.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.sce.lms.core.model.ModifiableEntity;
import org.sce.lms.core.model.user.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Table(name = "lms_leave_form")
@ToString
@AttributeOverride(name = "id", column = @Column(name = "leave_id"))
public class Leave extends ModifiableEntity {

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @OneToOne
    @JoinColumn(name = "leave_type_id")
    private LeaveType leaveType;

    @Column(name = "remarks", columnDefinition = "text")
    private String remarks;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;
}
