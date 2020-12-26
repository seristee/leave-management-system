package org.sce.lms.core.model.person;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Transient;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.sce.lms.core.model.ActivatableEntity;

@MappedSuperclass
@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Person extends ActivatableEntity {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_information_id", foreignKey = @ForeignKey(name="FK_PERSON_CONTACT_INFORMATION"))
    private ContactInformation contactInformation;

    @Column(name = "social_security_number")
    private String socialSecurityNumber;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @OneToOne
    @JoinColumn(name = "gender_id", foreignKey = @ForeignKey(name="FK_PERSON_GENDER"))
    private Gender gender;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", foreignKey = @ForeignKey(name = "FK_PERSON_ADDRESS"))
    private Address address;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "description")
    private String description;

    @Transient
    private String fullName;

    public void setFullName(String fullName) {
        this.fullName.concat(" ").concat(this.lastName);
    }
}
