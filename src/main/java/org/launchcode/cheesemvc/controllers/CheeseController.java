package org.launchcode.cheesemvc.controllers;


import org.launchcode.cheesemvc.models.Cheese;
import org.launchcode.cheesemvc.models.CheeseData;
import org.launchcode.cheesemvc.models.CheeseType;
import org.launchcode.cheesemvc.models.Ratings;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;


@Controller
@RequestMapping("cheese")
public class  CheeseController {


    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("cheeses", CheeseData.getAll());
        model.addAttribute("title", "My Cheeses");
        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {

        model.addAttribute("title", "Add Cheese");
        model.addAttribute(new Cheese());
        model.addAttribute("cheeseTypes", CheeseType.values());
        model.addAttribute("ratings", Ratings.values());

        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@ModelAttribute @Valid Cheese newCheese,
                                       Errors errors, Model model) {

        if (errors.hasErrors()) {

            model.addAttribute("title", "Add Cheese");
            model.addAttribute("cheeseTypes", CheeseType.values());
            model.addAttribute("ratings", Ratings.values());

            return "cheese/add";
        }

        CheeseData.add(newCheese);

        //no need to add anything below, leave empty to go to /cheese
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("cheeses", CheeseData.getAll());
        model.addAttribute("title", "Remove Cheese");
        return "cheese/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam(value="cheeseIds",
            required = false, defaultValue = "") int[] cheeseIds) {

        for (int cheeseId : cheeseIds) {
            CheeseData.remove(cheeseId);
        }
        return "redirect:";
    }

    @RequestMapping(value= "edit/{cheeseId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int cheeseId) {
        Cheese originalCheese = CheeseData.getById(cheeseId);
        model.addAttribute(originalCheese);
        model.addAttribute("originalCheese", originalCheese);
        model.addAttribute("cheeseTypes", CheeseType.values());
        model.addAttribute("ratings", Ratings.values());



        return "cheese/edit";
    }


    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.POST)
    public String processEditForm(@PathVariable int cheeseId,
            @ModelAttribute @Valid Cheese editCheese,
                                  Errors errors, Model model) {

        if (errors.hasErrors()) {
            Cheese originalCheese = CheeseData.getById(cheeseId);
            model.addAttribute(editCheese);
            model.addAttribute("originalCheese", originalCheese);
            model.addAttribute("cheeseTypes", CheeseType.values());
            model.addAttribute("ratings", Ratings.values());

            return "cheese/edit";
        }


        CheeseData.edit(cheeseId,
                editCheese.getName(), editCheese.getDescription(),
                editCheese.getType(), editCheese.getRating());

        return "redirect:/cheese"; //must specify /cheese, otherwise getting error

    }
    //TODO fix Id numbering
}
