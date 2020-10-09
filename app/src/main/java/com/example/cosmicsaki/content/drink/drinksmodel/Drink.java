package com.example.cosmicsaki.content.drink.drinksmodel;

public class Drink {

    private String title, component, instructions;

    public Drink() {
    }

    public Drink(String title, String component, String instructions) {
        this.title = title;
        this.component = component;
        this.instructions = instructions;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
