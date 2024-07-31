package com.seafooddelakec.menu;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Drink implements MenuItem {
    private static final Map<Integer, Drink> drinks = new HashMap<>();

    private final Integer id;
    private final Double price;
    private final String description;

    private Drink(Integer id, Double price, String description) {
        this.id = id;
        this.price = price;
        this.description = description;
    }

    public static Drink getDrink(int id) {
        return drinks.get(id);
    }

    public static void addDrink(Integer id, Double price, String description) {
        drinks.put(id, new Drink(id, price, description));
    }

    public static Collection<Drink> getAllDrinks() {
        return drinks.values();
    }

    @Override
    public Integer id() {
        return id;
    }

    @Override
    public Double price() {
        return price;
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("[%d] $%.2f - %s", id, price, description);
    }
}