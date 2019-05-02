package org.launchcode.cheesemvc.models;

import java.util.ArrayList;

public class CheeseData {
    static ArrayList<Cheese> cheeses = new ArrayList<>();

    //getAll
    public static ArrayList<Cheese> getAll() {
        return cheeses;
    }


    public static void add(Cheese newCheese) {
        cheeses.add(newCheese);
    }

    //remove
    public static void remove(int id) {
        Cheese cheeseToRemove = getById(id);
        cheeses.remove(cheeseToRemove);
    }

    //getbyId
    public static Cheese getById(int id) {
        Cheese theCheese = null;

        for (Cheese candCheese : cheeses) {
            if (candCheese.getCheeseId() == id) {
                theCheese = candCheese;
            }
        }
        return theCheese;
    }


    //edit the cheese in question
    public static void edit(int id, String name, String description, CheeseType type,
            int rating) {
        Cheese cheeseToEdit = getById(id);
        cheeseToEdit.setName(name);
        cheeseToEdit.setDescription(description);
        cheeseToEdit.setType(type);
        cheeseToEdit.setRating(rating);
    }

}
