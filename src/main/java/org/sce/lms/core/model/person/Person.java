package org.sce.lms.core.model.person;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.sce.lms.core.model.ActivatableEntity;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Table(name = "lms_people")
@ToString
@AttributeOverride(name = "id", column = @Column(name = "person_id"))
public class Person extends ActivatableEntity {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_information_id", foreignKey = @ForeignKey(name="FK_PERSON_CONTACT_INFORMATION"))
    @Valid
    private ContactInformation contactInformation;

    @NotEmpty(message = "{validation.field.required}")
    @Column(name = "social_security_number")
    private String socialSecurityNumber;

    @Column(name = "date_of_birth")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Birth date should be less than current date!!")
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfBirth;

    @OneToOne
    @JoinColumn(name = "gender_id", foreignKey = @ForeignKey(name="FK_PERSON_GENDER"))
    @Valid
    private Gender gender;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", foreignKey = @ForeignKey(name = "FK_PERSON_ADDRESS"))
    @Valid
    private Address address;

    @NotEmpty(message = "{validation.field.required}")
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @NotEmpty(message = "{validation.field.required}")
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "description")
    private String description;

    @Column(name="alias")
    private String alias;

    @Transient
    private String fullName;

    public void setFullName(String fullName) {
        this.fullName.concat(" ").concat(this.lastName);
    }

}
