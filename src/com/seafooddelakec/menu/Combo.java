package com.seafooddelakec.menu;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Combo implements MenuItem {
    private static final Map<Integer, Combo> combos = new HashMap<>();

    private final Integer id;
    private final Double price;
    private final String description;

    private Combo(Integer id, Double price, String description) {
        this.id = id;
        this.price = price;
        this.description = description;
    }

    public static Combo getCombo(int id) {
        return combos.get(id);
    }

    public static void addCombo(Integer id, Double price, String description) {
        combos.put(id, new Combo(id, price, description));
    }

    public static Collection<Combo> getAllCombos() {
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

    @Override
    public String toString() {
        return String.format("[%d] $%.2f - %s", id, price, description);
    }
}