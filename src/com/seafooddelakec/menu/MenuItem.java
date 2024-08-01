package com.seafooddelakec.menu;

import com.seafooddelakec.app.Server;

import java.util.Collection;

import java.util.Scanner;

import static com.apps.util.Console.*;


public interface MenuItem {
    Server server = new Server();
    Integer id();
    Double price();
    String description();

    static MenuItem selectItem(Scanner scanner, Collection<? extends MenuItem> items) {
        for (MenuItem item : items) {
            System.out.println(item);
        }
        blankLines(2);
        server.greeting();
        blankLines(1);
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
