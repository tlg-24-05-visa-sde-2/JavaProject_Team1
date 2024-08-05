package com.seafooddelakec.menu;

import java.util.ArrayList;
import java.util.List;

import static com.apps.util.Console.blankLines;
import static com.apps.util.Console.pause;

public class Menu {
    private final List<MenuItem> menuItems;
    private final List<MenuItem> orderedItems;

    public Menu(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
        this.orderedItems = new ArrayList<>();
    }

    public void displayMenu() {
        displayMenuSection("COMBOS  ", MenuItem.Type.COMBO);
        displayMenuSection("DRINKS  ", MenuItem.Type.DRINK);
    }

    private void displayMenuSection(String sectionTitle, MenuItem.Type type) {
        String border = "   +=====+==========+===============================================+";
        String titleFormat = "   | %-62s |";
        String itemFormat = "   | %-3d | $%-7.2f | %-45s |";
        String headerFormat = "   | %-3s | %-8s | %-45s |";

        blankLines(1);
        System.out.println(border);
        System.out.printf((titleFormat) + "%n", sectionTitle);
        System.out.println(border);
        System.out.printf((headerFormat) + "%n", "id", "price", "description");
        System.out.println(border);

        for (MenuItem item : menuItems) {
            if (item.type() == type) {
                String description = item.description();
                if (description.length() > 45) {
                    description = description.substring(0, 45) + "...";
                }
                String formattedItem = String.format(itemFormat,
                        item.id(), item.price(), description);
                System.out.println(formattedItem);
            }
        }
        System.out.println(border);
        blankLines(2);
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