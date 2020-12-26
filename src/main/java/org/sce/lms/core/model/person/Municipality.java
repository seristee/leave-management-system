package org.sce.lms.core.model.person;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.sce.lms.core.model.ActivatableNamedConstantEntity;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Table(name = "lms_municipality")
@AttributeOverride(name = "id", column = @Column(name = "municipality_id"))
public class Municipality extends ActivatableNamedConstantEntity {
    @OneToOne
    @JoinColumn(name = "district_id", foreignKey = @ForeignKey(name = "FK_MUNICIPALITY_DISTRICT"))
    private District district;
}
