package com.seafooddelakec;

import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Table {
    // static
    private static final String COMBOS_FILE_PATH = "menu_data/combos.csv";
    private static final String DRINKS_FILE_PATH = "menu_data/drinks.csv";

    // properties
    private final Map<Integer, String> comboOptions = loadCombos();
    private final Map<Integer, String> drinkOptions = loadDrinks();

    // methods
    private Map<Integer, String> loadCombos() {
        Map<Integer, String> comboOptions = new HashMap<>();
        try {
            List<String> lines = Files.readAllLines(Path.of(COMBOS_FILE_PATH));
            for (String line : lines) {
                String[] split = line.split(",");
                Integer comboNumber = Integer.parseInt(split[0]);
                Double price = Double.parseDouble(split[1]);
                String comboName = split[2];
                String formattedCombo = String.format("[%d] $%.2f - %s", comboNumber, price, comboName);
                comboOptions.put(comboNumber, formattedCombo);
            }
        } catch (IOException e) {
            System.err.println("Error reading combos.csv file: " + e.getMessage());
        }
        return comboOptions;
    }

    private Map<Integer, String> loadDrinks() {
        Map<Integer, String> drinkOptions = new HashMap<>();
        try {
            List<String> lines = Files.readAllLines(Path.of(DRINKS_FILE_PATH));
            for (String line : lines) {
                String[] split = line.split(",", 3);
                Integer drinkNumber = Integer.parseInt(split[0]);
                Double price = Double.parseDouble(split[1]);
                String drinkName = split[2];
                String formattedDrink = String.format("[%d] $%.2f - %s", drinkNumber, price, drinkName);
                drinkOptions.put(drinkNumber, formattedDrink);
            }
        } catch (IOException e) {
            System.err.println("Error reading drinks.csv file: " + e.getMessage());
        }
        return drinkOptions;
    }

    public double extractPrice(String menuItem) {
        String[] parts = menuItem.split(" - ", 2);
        String priceString = parts[0].substring(parts[0].indexOf("$") + 1);
        return Double.parseDouble(priceString);
    }

    public String extractName(String menuItem) {
        String[] parts = menuItem.split(" - ", 2);
        return parts[1];
    }

    public void displayCombos() {
        System.out.println("-------SEAFOOD MENU-------");
        for (String combo : comboOptions.values()) {
            System.out.println(combo);
        }

    }

    public void displayDrinks() {
        System.out.println("-------DRINKS MENU-------");
        for (String drink : drinkOptions.values()) {
            System.out.println(drink);
        }
    }

    public void displayOrder(List<String> orderItems) {
        System.out.println("\n-------YOUR ORDER-------");
        double total = 0.0;
        for (String item : orderItems) {
            String name = extractName(item);
            double price = extractPrice(item);
            System.out.printf("%s - $%.2f%n", name, price);
            total += price;
        }
        System.out.printf("Total: $%.2f%n", total);
    }

    // accessor methods
    public String getCombo(int comboNumber) {
        return comboOptions.get(comboNumber);
    }

    public String getDrink(int drinkNumber) {
        return drinkOptions.get(drinkNumber);
    }
}