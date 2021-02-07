package org.sce.lms.administration.cruds;

import org.sce.lms.core.repositories.RoleRepository;
import org.sce.lms.core.repositories.UserRepository;
import org.sce.lms.core.controller.GlobalController;
import org.sce.lms.core.dao.CoreDao;
import org.sce.lms.core.model.user.model.Authority;
import org.sce.lms.leave.model.LeaveType;
import org.sce.lms.leave.repository.LeaveTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/crud")
public class CrudController extends GlobalController {

    @Autowired
    private CoreDao coreDao;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private LeaveTypeRepository leaveTypeRepository;

    @GetMapping("/roles/get.do")
    public String getRoles(Model model){
        model.addAttribute("roles", new Authority());
        model.addAttribute("rolesList", coreDao.getAllObjects(Authority.class));
        return "screens/views/admin/cruds/roles";
    }

    @GetMapping("/roles/{roleid}/edit.do")
    public String editRoles(@PathVariable long roleid, Model model, @ModelAttribute Authority authority){
        model.addAttribute("roles", coreDao.getObjectById(Authority.class, roleid));
        model.addAttribute("rolesList", coreDao.getAllObjects(Authority.class));
        return "screens/views/admin/cruds/roles";
    }

    @PostMapping("/roles/save.do")
    public String saveRoles(@ModelAttribute("roles") @Valid Authority authority, BindingResult result, Model model){
        authority.setConstant(authority.getName().toUpperCase());
        roleRepository.save(authority);
        return "redirect:/admin/crud/roles/get.do";
    }

    @RequestMapping("/roles/{roleid}/delete.do")
    public String deleteRole(@PathVariable Long roleid){
        roleRepository.deleteById(roleid);
        return "redirect:/admin/crud/roles/get.do";
    }

    @GetMapping("/leave/type/get.do")
    public String getLeaveType(Model model){
        model.addAttribute("leaveType", new LeaveType());
        model.addAttribute("leaveTypeList", leaveTypeRepository.findAll());
        return "screens/views/admin/cruds/leavetype";
    }

    @PostMapping("/leave/type/save.do")
    public String saveLeaveType(@ModelAttribute("roles") @Valid LeaveType leaveType, BindingResult result, Model model){
        leaveType.setConstant(leaveType.getName().toUpperCase());
        leaveTypeRepository.save(leaveType);
        return "redirect:/admin/crud/leave/type/get.do";
    }

    @GetMapping("/leave/type/{leavetypeid}/edit.do")
    public String editLeaveType(@PathVariable long leavetypeid, Model model, @ModelAttribute LeaveType leaveType){
        model.addAttribute("leaveType", leaveTypeRepository.findById(leavetypeid));
        model.addAttribute("leaveTypeList", leaveTypeRepository.findAll());
        return "screens/views/admin/cruds/leavetype";
    }

    @RequestMapping("/leave/type/{leavetypeid}/delete.do")
    public String deleteLeaveType(@PathVariable Long leavetypeid){
        leaveTypeRepository.deleteById(leavetypeid);
        return "redirect:/admin/crud/leave/type/get.do";
    }
}
