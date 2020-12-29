package org.sce.lms.core.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String redirectUrl = null;
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROLE_USER") || grantedAuthority.getAuthority().equals("USER")) {
                redirectUrl = "/dashboard/get.do";
                break;
            } else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN") || grantedAuthority.getAuthority().equals("ADMIN")) {
                redirectUrl = "/dashboard/get.do";
                break;
            } else {
                redirectUrl = "/access/denied.do";
            }
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        logger.info("User " + authentication.getName() + " has Logged in successfully at " + dtf.format(LocalDateTime.now()));
        if (redirectUrl == null) {
            throw new IllegalStateException();
        } else {

            redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, redirectUrl);
        }
    }
}

