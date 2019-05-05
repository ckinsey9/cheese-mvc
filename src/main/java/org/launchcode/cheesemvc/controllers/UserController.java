package org.launchcode.cheesemvc.controllers;


import org.launchcode.cheesemvc.models.User;
import org.launchcode.cheesemvc.models.UserData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("user")
public class UserController {


    @RequestMapping(value="add")
    public String displayUserRegister(Model model) {
        String verify_error = "";

        model.addAttribute("title", "Register User");
        model.addAttribute(new User());
        model.addAttribute("verify_error", verify_error);
        return "user/add";
    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public String processUserRegister(Model model, @ModelAttribute @Valid User user,
                      Errors errors, @RequestParam(value="verify") String verify){
        if (errors.hasErrors()) {
            model.addAttribute("title", "Register User");
            model.addAttribute(user);
            return "user/add";
        }

        if (!UserData.checkUsername(user.getUsername())) {
            String username_error = "That username is already taken.";

            model.addAttribute("title", "Register User");
            model.addAttribute(user);
            model.addAttribute("username_error", username_error);
            return "user/add";
        }

        if (!user.getPassword().equals(verify)) {


            String verify_error = "The passwords do not match.";

            model.addAttribute("title", "Register User");
            model.addAttribute(user);
            model.addAttribute("verify_error", verify_error);
            return "user/add";
        }
        UserData.add(user);
        model.addAttribute("users", UserData.getAll());
        model.addAttribute("title", "Welcome, " + user.getUsername()+"!");
        return "user/index";

    }

    @RequestMapping(value="single_user{userId}", method= RequestMethod.GET)
    public String displaySingleUser(Model model, @PathVariable int userId){
        User single = UserData.getById(userId);
        model.addAttribute("title", "User Information: ");
        model.addAttribute("user", single);
        model.addAttribute("timeJoined", single.getDate().toString());

        //TODO FINISH THE DATE FIELD / VIEW
        return "user/single_user";
    }
}
