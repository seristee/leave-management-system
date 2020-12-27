package org.sce.lms.login.controller;

import org.sce.lms.core.controller.GlobalController;
import org.sce.lms.core.model.user.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController extends GlobalController {

    @GetMapping("/")
    private String index(){
        logger.info("redirected to login");
        return "redirect:/login.do";
    }

    @GetMapping("/login.do")
    private String home(){
        logger.info("home called");
        return "screens/views/login/index";
    }

    @GetMapping("/registration/get.do")
    private String registration(Model model) {
        model.addAttribute("user", new User());
        return "screens/views/login/registration";
    }

    @GetMapping("/forgot/password/get.do")
    public String forgotPassword() {

        return "screens/view/login/forgot-password";
    }

    @GetMapping("/logout.do")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
//        SecurityContextHolder.clearContext();
//        if (request.getSession(false) != null) {
//            request.getSession().invalidate();
//        }
//        for (Cookie cookie : request.getCookies()) {
//            cookie.setMaxAge(0);
//        }
        return "redirect:/login";
    }
}
