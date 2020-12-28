package org.sce.lms.api.controller;

import org.sce.lms.core.controller.GlobalController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ApiController extends GlobalController {

    @GetMapping("/test")
    private @ResponseBody String index(){
        logger.info("you just joined the dark side");
        return "Api works correctly";
    }
}
