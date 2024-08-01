package com.seafooddelakec.menu;

import com.apps.util.Prompter;

import java.util.*;

public class Combo implements MenuItem {
    private static final Map<Integer, Combo> combos = new HashMap<>();
    private final Scanner scanner = new Scanner(System.in);
    private final Prompter prompter = new Prompter(new Scanner(System.in));

    private final Integer id;
    private final Double price;
    private final String description;


    public Combo(Integer id, Double price, String description) {
        this.id = id;
        this.price = price;
        this.description = description;
    }


    public Combo getCombo(int id) {
        return combos.get(id);
    }

    public static void addCombo(Integer id, Double price, String description) {
        combos.put(id, new Combo(id, price, description));
    }

    public Collection<Combo> getAllCombos() {
        return combos.values();
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

    public static Combo selectCombo(Scanner scanner) {
        System.out.println("\n--- Combo Menu ---");
        return (Combo) MenuItem.selectItem(scanner, combos.values());
    }

    @Override
    public String toString() {
        return String.format("[%d] $%.2f - %s", id, price, description);
    }
}