package org.sce.lms.core.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class ModifiableEntity extends ActivatableEntity{

    @Column(name = "modified_date")
    protected LocalDateTime modifiedDate;
    @Column(name = "created_date")
    protected LocalDateTime createdDate = LocalDateTime.now();
}
