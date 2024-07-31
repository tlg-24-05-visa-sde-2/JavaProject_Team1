package com.seafooddelakec.menu;

import com.apps.util.Prompter;

import java.util.*;

public class Drink implements MenuItem {
    private static final Map<Integer, Drink> drinks = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);
    private final Integer id;
    private final Double price;
    private final String description;

    public Drink(Integer id, Double price, String description) {
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

    public Collection<Drink> getAllDrinks() {
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

    public static Drink selectDrink(Scanner scanner) {
        System.out.println("\n--- Drink Menu ---");
        return (Drink) MenuItem.selectItem(scanner, drinks.values());
    }

    @Override
    public String toString() {
        return String.format("[%d] $%.2f - %s", id, price, description);
    }
}