package org.launchcode.cheesemvc.models;

import java.util.ArrayList;

public class UserData {
    static ArrayList<User> users = new ArrayList<>();

    //getAll
    public static ArrayList<User> getAll() {
        return users;
    }


    public static void add(User newUser) {
        users.add(newUser);
    }

    //remove
    public static void remove(int id) {
        User userToRemove = getById(id);
        users.remove(userToRemove);
    }

    //getbyId
    public static User getById(int id) {
        User theUser = null;

        for (User user : users) {
            if (user.getUserId() == id) {
                theUser = user;
            }
        }
        return theUser;
    }

    //check if username is taken
    public static Boolean checkUsername(String pickedUsername) {
        for (User user : users) {
            if (user.getUsername().equals(pickedUsername)) {
                return false;
            }

        }
        return true;
    }
}
