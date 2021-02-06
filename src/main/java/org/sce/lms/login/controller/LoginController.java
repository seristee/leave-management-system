package org.sce.lms.login.controller;

import org.sce.lms.core.controller.GlobalController;
import org.sce.lms.core.dao.CoreDao;
import org.sce.lms.core.model.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController extends GlobalController {
    @Autowired
    private CoreDao coreDao;


//    @GetMapping("/")
//    private String index(){
//        return "redirect:/login.do";
//    }

    @GetMapping("/login.do")
    private String home(){
        return "screens/views/login/index";
    }

    @GetMapping("/registration/get.do")
    private String registration(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("userList", coreDao.getAllObjects(User.class));
        return "screens/views/login/registration";
    }

    @GetMapping("/forgot/password/get.do")
    public String forgotPassword() {
        return "screens/views/login/forgotpassword";
    }

    @PostMapping("/forgot/password/reset.do")
    public @ResponseBody boolean resetPassword(@RequestParam(name = "email", required = true) String emailAddress){
        return true;
    }

    @GetMapping("/logout.do")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextHolder.clearContext();
        if (request.getSession(false) != null) {
            request.getSession().invalidate();
        }
        for (Cookie cookie : request.getCookies()) {
            cookie.setMaxAge(0);
        }
        return "redirect:/login.do";
    }
}
