package org.sce.lms.core.model.user.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    final static List roles = new ArrayList();
    final static User user = new User();
    final static Authority authority = new Authority();
    @BeforeAll
    static void beforeAll() {

        user.setUsername("Mary");
        user.setPassword("testing");

        Authority authority1 = new Authority();
        Authority authority2 = new Authority();
        authority.setName("Admin");
        authority1.setName("User");
        authority2.setName("Guest");

        roles.add(authority);
        roles.add(authority1);
        roles.add(authority2);
        user.setUserRoles(roles);
        user.setAccountExpired(false);
        user.setAccountLocked(false);
        user.setCredentialsExpired(false);
        user.setDisabled(false);
    }

    @Test
    void testUser() {
        assertEquals(user.getPassword(), "testing");
        assertEquals(user.getUsername(), "Mary");
        assertEquals(user.isAccountExpired(), false);

    }
}