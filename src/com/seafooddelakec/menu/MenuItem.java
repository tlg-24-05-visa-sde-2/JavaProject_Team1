package com.seafooddelakec.menu;

import com.apps.util.Prompter;

import java.util.Collection;

import java.util.Scanner;


public interface MenuItem {
    Integer id();
    Double price();
    String description();

    static MenuItem selectItem(Scanner scanner, Collection<? extends MenuItem> items) {
        for (MenuItem item : items) {
            System.out.println(item);
        }
        System.out.print("Enter the number of your selection: ");
        int selection = Integer.parseInt(scanner.nextLine().trim());
        for (MenuItem item : items) {
            if (item.id().equals(selection)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Invalid selection");
    }
}
