package org.sce.lms.core.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomErrorController extends GlobalController implements ErrorController {
    @GetMapping("/error")
    public String handleError(){
        return "screens/views/error/404";
    }

    @GetMapping("/access/denied")
    public String accessDenied() {
        return "screens/views/error/403";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
