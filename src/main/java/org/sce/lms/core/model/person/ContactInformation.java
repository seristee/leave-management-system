package org.sce.lms.core.model.person;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.sce.lms.core.model.ActivatableEntity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Table(name = "lms_contact_information")
@AttributeOverride(name = "id", column = @Column(name = "contact_information_id"))
public class ContactInformation extends ActivatableEntity {
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "cell_phone_number")
    private String otherContactNumber;
    @Email(message = "{validation.email}")
    @NotEmpty(message = "{validation.field.required}")
    @Column(name = "email_address", unique = true)
    private String emailAddress;
}
