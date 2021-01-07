package org.sce.lms.core.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {
    protected Log logger = LogFactory.getLog(this.getClass());

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setPath(request.getContextPath());
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        if(request.getParameter("expired") != null)
        {
            response.sendRedirect(request.getContextPath()+"/login.do?expired=true");
        }
        else
        {
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
            response.sendRedirect(request.getContextPath() + "/login.do?logout=true");
        }
    }
}
