package org.launchcode.cheesemvc.controllers;


import org.launchcode.cheesemvc.models.Cheese;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping("cheese")
public class CheeseController {


        static ArrayList<Object> cheeses = new ArrayList<>();

        // Request path: /cheese
        @RequestMapping(value = "")
        public String index(Model model) {

            model.addAttribute("cheeses", cheeses);
            model.addAttribute("title", "My Cheeses");
            return "cheese/index";
    }

        @RequestMapping(value= "add", method = RequestMethod.GET)
        public String displayAddCheeseForm(Model model) {

            model.addAttribute("title", "Add Cheese");

            return "cheese/add";
        }

        @RequestMapping(value= "add", method = RequestMethod.POST)
        public String processAddCheeseForm(@RequestParam String cheeseName, @RequestParam
                                           String cheeseNotes) {

            cheeses.add(new Cheese(cheeseName, cheeseNotes));

            //no need to add anything below, leave empty to go to /cheese
            return "redirect:";
        }
}
