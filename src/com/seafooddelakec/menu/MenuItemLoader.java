package com.seafooddelakec.menu;

import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class MenuItemLoader {

    private static final String MENU_FILE_PATH = "menu_data/menu.csv";

    public List<MenuItem> loadMenu() {
        List<MenuItem> menuItems = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Path.of(MENU_FILE_PATH));
            for (String line : lines) {
                String[] split = line.split(",");
                Integer id = Integer.parseInt(split[0].trim());
                Double price = Double.parseDouble(split[1].trim());
                String description = split[2].trim();
                MenuItem.Type type = MenuItem.Type.valueOf(split[3].trim());
                menuItems.add(new MenuItem(id, price, description, type));
            }
        } catch (IOException e) {
            System.err.println("Error reading menu.csv file: " + e.getMessage());
        }
        return menuItems;
    }
}