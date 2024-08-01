package com.seafooddelakec.menu;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private final List<MenuItem> menuItems;
    private final List<MenuItem> orderedItems;

    public Menu(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
        this.orderedItems = new ArrayList<>();
    }

    public void displayMenu() {
        displayMenuSection("COMBOS", MenuItem.Type.COMBO);
        System.out.println();
        displayMenuSection("DRINKS", MenuItem.Type.DRINK);
    }

    private void displayMenuSection(String sectionTitle, MenuItem.Type type) {
        System.out.println("-----" + sectionTitle + "-----");
        for (MenuItem item : menuItems) {
            if (item.type() == type) {
                System.out.println(item);
            }
        }
    }

    public MenuItem getItemById(int id) {
        for (MenuItem item : menuItems) {
            if (item.id() == id) {
                return item;
            }
        }
        return null;
    }

    public void addOrderedItem(MenuItem item) {
        orderedItems.add(item);
    }

    public List<MenuItem> getOrderedItems() {
        return new ArrayList<>(orderedItems);
    }
}