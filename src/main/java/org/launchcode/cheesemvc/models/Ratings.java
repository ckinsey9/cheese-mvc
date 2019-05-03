package org.launchcode.cheesemvc.models;

public enum Ratings {

    ONE ("1"),
    TWO ("2"),
    THREE ("3"),
    FOUR ("4"),
    FIVE ("5");

    private final String rating;

    Ratings(String rating) {
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }

}
