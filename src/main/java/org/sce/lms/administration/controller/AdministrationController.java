package org.sce.lms.administration.controller;

import org.sce.lms.core.controller.GlobalController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
@Controller
public class AdministrationController extends GlobalController {

    @GetMapping("/profile/user/{userid}/get.do")
    private String index(Model model, @PathVariable long userid){
        return "screens/views/administration/profile";
    }

    @GetMapping("/user/management/get.do")
    private String userManagement(Model model){
        return "screens/views/administration/usermanagement";
    }
}
