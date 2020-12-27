package org.sce.lms.core.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
    protected Log logger = LogFactory.getLog(this.getClass());

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (authentication != null && authentication.getDetails() != null) {
            try {
                request.getSession().invalidate();
                logger.info(authentication.getName() + " has logged out successfully at " + LocalDateTime.now());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        logger.info(authentication.getName() + " has logged out successfully at " + LocalDateTime.now());
        response.setStatus(HttpServletResponse.SC_OK);
        response.sendRedirect(request.getHeader("/logout/get.do"));
    }
}
