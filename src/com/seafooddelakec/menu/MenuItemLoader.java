package com.seafooddelakec.menu;

import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class MenuItemLoader {
    private static final String COMBOS_FILE_PATH = "menu_data/combos.csv";
    private static final String DRINKS_FILE_PATH = "menu_data/drinks.csv";

    public void loadMenuItems() {
        loadCombos();
        loadDrinks();
    }

    private void loadCombos() {
        try {
            List<String> lines = Files.readAllLines(Path.of(COMBOS_FILE_PATH));
            for (String line : lines) {
                String[] split = line.split(",");
                Integer id = Integer.parseInt(split[0]);
                Double price = Double.parseDouble(split[1]);
                String description = split[2];
                Combo.addCombo(id, price, description);
            }
        } catch (IOException e) {
            System.err.println("Error reading combos.csv file: " + e.getMessage());
        }
    }

    private void loadDrinks() {
        try {
            List<String> lines = Files.readAllLines(Path.of(DRINKS_FILE_PATH));
            for (String line : lines) {
                String[] split = line.split(",", 3);
                Integer id = Integer.parseInt(split[0]);
                Double price = Double.parseDouble(split[1]);
                String description = split[2];
                Drink.addDrink(id, price, description);
            }
        } catch (IOException e) {
            System.err.println("Error reading drinks.csv file: " + e.getMessage());
        }
    }
}