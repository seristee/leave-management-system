package org.sce.lms.administration.controller;

import org.sce.lms.core.controller.GlobalController;
import org.sce.lms.core.dao.CoreDao;
import org.sce.lms.core.model.user.model.Authority;
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
        coreDao.saveObject(authority);
        return "redirect:/admin/crud/roles/get.do";
    }
}
