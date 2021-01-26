package org.sce.lms.administration.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/admin")
@Controller
public class AdministrationController extends GlobalController {
    @Autowired
    private CoreDao coreDao;

    @Autowired
    private UserService userService;

    @GetMapping("/profile/user/{userid}/get.do")
    public String index(Model model, @PathVariable long userid){
        return "screens/views/administration/userprofile";
    }

    @GetMapping("/user/management/get.do")
    public String userManagement(Model model){
        model.addAttribute("user", new User());
        return setAdminModels(model);
    }

    @PostMapping("/user/management/save.do")
    public String saveUser(HttpServletRequest request,@ModelAttribute("user") User user, BindingResult result,Model model){
        List<Authority> authorityList = new ArrayList<Authority>();
        try {
            String[] roles = request.getParameterValues("userRoles");
            if(roles.length > 0){
                for (int i = 0; i < roles.length; i++) {
                    Authority authority = (Authority) coreDao.getObjectById(Authority.class, Long.parseLong(roles[i]));
                    authorityList.add(authority);
                }
                user.setUserRoles(authorityList);
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }

        System.out.println(authorityList.size() + " => authorityList.size()");

//        if(result.hasErrors() && authorityList.isEmpty()){
//            return setAdminModels(model);
//        } else {
            User dbUser = (User) coreDao.getObjectByCriteria(User.class, "username", user.getUsername());
            user.getPerson().setDateOfBirth(convertToLocalDate(request.getParameter("person.dateOfBirth")));
            if (user.getPassword().equals(user.getConfirmPassword())) {
                if (!user.equals(dbUser)) {
//                    userService.save(user);
                    user.toString();
                }
            }
            else {
                System.out.println("passwords do not match");
            }
//        }
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
