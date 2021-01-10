package org.sce.lms.core.model;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ActivatableNamedEntity extends ActivatableEntity{
//    @NotEmpty(message = "{validation.field.required}")
    private String name;
}
