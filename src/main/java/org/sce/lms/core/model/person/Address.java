package org.sce.lms.core.model.person;


import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.sce.lms.core.model.ActivatableEntity;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Table(name = "lms_address")
@AttributeOverride(name = "id", column = @Column(name = "address_id"))
public class Address extends ActivatableEntity {
    @Column(name = "address_line_1")
    private String addressLine1;

    @Column(name = "address_line_2")
    private String addressLine2;

    @OneToOne
    @JoinColumn(name = "municipality_id")
    private Municipality municipality;

    @Column(name="zip_code")
    private String zipCode;
}
