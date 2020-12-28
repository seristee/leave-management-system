package org.sce.lms.leave.controller;

import org.sce.lms.core.controller.GlobalController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/leave")
public class LeaveController extends GlobalController {

    @GetMapping("/application/manage/get.do")
    private String index(Model model){

        return "screens/views/leave/manageleave";
    }

    @GetMapping("/application/user/{userid}/get.do")
    private String myApplications(Model model, @PathVariable long userid){

        return "screens/views/leave/applications";
    }
}
