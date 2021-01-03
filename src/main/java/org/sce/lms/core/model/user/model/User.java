package org.sce.lms.core.model.user.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.SQLDelete;
import org.sce.lms.core.model.ModifiableEntity;
import org.sce.lms.core.model.person.Person;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Table(name = "lms_users")
@ToString
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
@SQLDelete(sql = "UPDATE lms_users SET active=false WHERE user_id=?")
public class User extends ModifiableEntity {
    @Column(name="username", unique = true, nullable = false)
    @NotEmpty(message = "{validation.username.required}")
    private String username;

    @Column(name="password", nullable = false)
    @NotEmpty(message = "Password is required")
    private String password;

    @Column(name = "disabled")
    private boolean disabled;

    @Column(name = "account_expired")
    private boolean accountExpired;

    @Column(name = "credentials_expired")
    private boolean credentialsExpired;

    @Column(name = "account_locked")
    private boolean accountLocked;
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "acsis_user_authorities",joinColumns = {
            @JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "authority_id")}, foreignKey = @ForeignKey(name="FK_USER_USER_AUTHORITY"))
    private List<Authority> userRoles= new ArrayList<>();
    @OneToOne
    @JoinColumn(name="account_type_id", foreignKey=@ForeignKey(name="FK_ACCOUNT_INFO_ACCOUNT_TYPE"))
    private AccountType accountType;
    @OneToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="person_id", foreignKey=@ForeignKey(name="FK_USER_PERSON"))
    private Person person;
}
