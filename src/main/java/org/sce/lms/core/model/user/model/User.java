package org.sce.lms.core.model.user.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.sce.lms.core.model.ModifiableEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Table(name = "lms_users")
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
public class User extends ModifiableEntity {
    @Column(name="username", unique = true, nullable = false)
    private String username;
    @Column(name="password", nullable = false)
    private String password;
    @Column(name = "disabled")
    private boolean disabled;
    @Column(name = "account_expired")
    private boolean accountExpired;
    @Column(name = "credentials_expired")
    private boolean credentialsExpired;
    @Column(name = "account_locked")
    private boolean accountLocked;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(name = "acsis_user_authorities",joinColumns = {
            @JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "authority_id")}, foreignKey = @ForeignKey(name="FK_USER_USER_AUTHORITY"))
    private List<Authority> userRoles= new ArrayList<>();
    @OneToOne
    @JoinColumn(name="account_type_id", foreignKey=@ForeignKey(name="FK_ACCOUNT_INFO_ACCOUNT_TYPE"))
    private AccountType accountType;
}
