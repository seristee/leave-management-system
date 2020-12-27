package org.sce.lms.login.controller;

import org.sce.lms.core.controller.GlobalController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController extends GlobalController {

    @GetMapping("/")
    private String index(){
        logger.info("redirected to login");
        return "redirect:/login";
    }

    @GetMapping("/login")
    private String home(){
        logger.info("home called");
        return "screens/views/login/index";
    }

}