package org.sce.lms.administration.controller;

import org.sce.lms.core.controller.GlobalController;
import org.sce.lms.core.dao.CoreDao;
import org.sce.lms.core.model.person.Municipality;
import org.sce.lms.core.model.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin")
@Controller
public class AdministrationController extends GlobalController {
    @Autowired
    private CoreDao coreDao;

    @GetMapping("/profile/user/{userid}/get.do")
    private String index(Model model, @PathVariable long userid){
        return "screens/views/administration/profile";
    }

    @GetMapping("/user/management/get.do")
    private String userManagement(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("userList", coreDao.getAllObjects(User.class));
        model.addAttribute("municipalityList", coreDao.getAllObjects(Municipality.class));
        return "screens/views/administration/usermanagement";
    }

    @PostMapping("/user/management/save.do")
    private String saveUser(@ModelAttribute User user, Model model){
        System.out.println(user.toString());
        return "redirect:/admin/user/management/get.do";
    }
}
