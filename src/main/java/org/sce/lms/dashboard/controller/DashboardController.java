package org.sce.lms.dashboard.controller;

import org.sce.lms.core.controller.GlobalController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController extends GlobalController {

    @GetMapping("/get")
    public String index() {
        return "screens/views/dashboard/dashboard";
    }
}
