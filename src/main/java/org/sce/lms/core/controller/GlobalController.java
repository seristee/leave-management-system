package org.sce.lms.core.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ModelAttribute;

@Configuration
@PropertySource(value = { "classpath:application.properties" })
public class GlobalController {
    protected Log logger = LogFactory.getLog(this.getClass());

    @Value("${build.version}")
    protected String buildVersion;

    @ModelAttribute("currrentUser")
    public String currentUser(){
        return getPrincipal();
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
        String[] arr = buildVersion.split("-");
        return arr[0];
    }
}
