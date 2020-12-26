package org.sce.lms.core.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sce.lms.core.model.user.model.Authority;
import org.sce.lms.core.model.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    protected Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private UserService userService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username not found");
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                !user.isDisabled(), !user.isAccountExpired(), !user.isCredentialsExpired(), !user.isAccountLocked(),
                getGrantedAuthorities(user));
    }

    private List<GrantedAuthority> getGrantedAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Authority userProfile : user.getUserRoles()) {
            authorities.add(new SimpleGrantedAuthority(userProfile.getConstant()));
        }
        return authorities;
    }
}
