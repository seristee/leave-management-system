package org.sce.lms.administration.controller;

import org.sce.lms.core.controller.GlobalController;
import org.sce.lms.core.dao.CoreDao;
import org.sce.lms.core.model.person.Gender;
import org.sce.lms.core.model.person.Municipality;
import org.sce.lms.core.model.user.model.User;
import org.sce.lms.core.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequestMapping("/admin")
@Controller
public class AdministrationController extends GlobalController {
    @Autowired
    private CoreDao coreDao;

    @Autowired
    private UserService userService;

    @GetMapping("/profile/user/{userid}/get.do")
    private String index(Model model, @PathVariable long userid){
        return "screens/views/administration/profile";
    }

    @GetMapping("/user/management/get.do")
    private String userManagement(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("userList", coreDao.getAllObjects(User.class));
        model.addAttribute("municipalityList", coreDao.getAllObjects(Municipality.class));
        model.addAttribute("genderList", coreDao.getAllObjects(Gender.class));
        return "screens/views/administration/usermanagement";
    }

    @PostMapping("/user/management/save.do")
    private String saveUser(@Valid @ModelAttribute("user") User user, Model model, HttpServletRequest request, BindingResult result){
        System.out.println(result.hasErrors());
        User dbUser = (User) coreDao.getObjectByCriteria(User.class, "username", user.getUsername());
        if(user.getPassword().equals(request.getParameter("confirmPassword"))){
            if(!user.equals(dbUser)){
                userService.save(user);
            }
        }
        return "redirect:/admin/user/management/get.do";
    }
}
