package org.sce.lms.core.model.user.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name="lms_account_type")
@AttributeOverride(name="id", column=@Column(name="account_type_id"))
public class AccountType extends ActivatableNamedConstantEntity {
}
