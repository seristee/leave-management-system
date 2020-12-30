package org.sce.lms.core.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Configuration
@PropertySource(value = { "classpath:application.properties" })
public class GlobalController {
    protected Log logger = LogFactory.getLog(this.getClass());

//    @Value("${build.version}")
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

//    @InitBinder("dateBinder")
//    public void dateBinder(WebDataBinder binder) {
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
//    }
}
