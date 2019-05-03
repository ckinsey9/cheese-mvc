package org.launchcode.cheesemvc.controllers;


import org.launchcode.cheesemvc.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {


    @RequestMapping("add")
    public String add(Model model) {
        String verify_error = "";

        model.addAttribute("title", "Register User");
        model.addAttribute(new User());
        model.addAttribute("verify_error", verify_error);
        return "user/add";
    }
}
