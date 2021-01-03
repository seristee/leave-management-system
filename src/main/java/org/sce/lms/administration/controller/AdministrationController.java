package org.sce.lms.administration.controller;

import org.sce.lms.core.controller.GlobalController;
import org.sce.lms.core.dao.CoreDao;
import org.sce.lms.core.model.person.Gender;
import org.sce.lms.core.model.person.Municipality;
import org.sce.lms.core.model.user.model.Authority;
import org.sce.lms.core.model.user.model.User;
import org.sce.lms.core.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/admin")
@Controller
public class AdministrationController extends GlobalController {
    @Autowired
    private CoreDao coreDao;

    @Autowired
    private UserService userService;

    @GetMapping("/profile/user/{userid}/get.do")
    public String index(Model model, @PathVariable long userid){
        return "screens/views/administration/profile";
    }

    @GetMapping("/user/management/get.do")
    public String userManagement(Model model){
        model.addAttribute("user", new User());
        return setAdminModels(model);
    }

    @PostMapping("/user/management/save.do")
    public String saveUser(HttpServletRequest request,@ModelAttribute @Valid User user, BindingResult result,Model model){
        if(result.hasErrors()){
//            return "screens/views/administration/usermanagement";
            return setAdminModels(model);
        } else {
            User dbUser = (User) coreDao.getObjectByCriteria(User.class, "username", user.getUsername());
            user.getPerson().setDateOfBirth(convertToLocalDate(request.getParameter("person.dateOfBirth")));
            List<Authority> authorityList = new ArrayList<Authority>();
            String[] roles = request.getParameterValues("userRoles");
            for (int i = 0; i < roles.length; i++) {
                Authority authority = (Authority) coreDao.getObjectById(Authority.class, Long.parseLong(roles[i]));
                authorityList.add(authority);
            }

            user.setUserRoles(authorityList);
            if (user.getPassword().equals(request.getParameter("confirmPassword"))) {
                if (!user.equals(dbUser)) {
                    userService.save(user);
                }
            }
        }
//        return "redirect:/admin/user/management/get.do";
        return setAdminModels(model);
    }

    @GetMapping("/user/{userid}/management/edit.do")
    public String getUser(@PathVariable long userid, Model model) {
        model.addAttribute("user", coreDao.getObjectById(User.class, userid));
        return setAdminModels(model);
    }

    @RequestMapping("/user/{userid}/management/delete.do")
    public String deleteUser(@PathVariable long userid, Model model, @ModelAttribute User user) {
        model.addAttribute("user", user);
        coreDao.deleteObject(User.class, userid);
        return "redirect:/admin/user/management/get.do";
    }

    public String setAdminModels(Model model) {
        model.addAttribute("userList", coreDao.getAllObjects(User.class));
        model.addAttribute("municipalityList", coreDao.getAllObjects(Municipality.class));
        model.addAttribute("genderList", coreDao.getAllObjects(Gender.class));
        model.addAttribute("rolesList", coreDao.getAllObjects(Authority.class));
        return "screens/views/administration/usermanagement";
    }
}
