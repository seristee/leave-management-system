package org.sce.lms.core.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sce.lms.core.dao.CoreDao;
import org.sce.lms.core.model.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
@PropertySource(value = { "classpath:application.properties" })
@Transactional
@SessionAttributes("currentUser")
public class GlobalController {
    protected Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private CoreDao coreDao;

//    @Value("${build.version}")
    protected String buildVersion;

    @ModelAttribute("currentUser")
    public User currentUser(){
        User user = (User) coreDao.getObjectByCriteria(User.class, "username", getPrincipal());
        return user;
    }

    protected String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    @ModelAttribute("appVersion")
    public String getBuildVersion() {
//        String[] arr = buildVersion.split("-");
//        return arr[0];
        return "0.0.1";
    }

    /**
     * Convert string to LocalDate with formatting
     */
    public LocalDate convertToLocalDate(String reqDate) {
        return LocalDate.parse(reqDate, DateTimeFormatter.ofPattern("yyyy-MM-d"));
    }
}
