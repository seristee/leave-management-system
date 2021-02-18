package org.sce.lms.leave.controller;

import org.sce.lms.core.controller.GlobalController;
import org.sce.lms.core.dao.CoreDao;
import org.sce.lms.leave.model.Leave;
import org.sce.lms.leave.model.LeaveType;
import org.sce.lms.leave.repository.LeaveRepository;
import org.sce.lms.leave.repository.LeaveTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/leave")
public class LeaveController extends GlobalController {

    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private LeaveTypeRepository typeRepository;

    @Autowired
    private CoreDao coreDao;

    @GetMapping("/application/manage/get.do")
    private String index(Model model){

        return "screens/views/leave/manageleave";
    }

    @GetMapping("/application/user/{userid}/get.do")
    private String myApplications(Model model, @PathVariable long userid) {
        model.addAttribute("leave", new Leave());
        try {
            model.addAttribute("leaveTypeList", typeRepository.findAll());
        } catch (Exception ex){
            ex.printStackTrace();
        }

        return "screens/views/leave/applications";
    }
}