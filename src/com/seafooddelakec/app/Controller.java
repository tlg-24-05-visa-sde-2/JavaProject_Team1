package com.seafooddelakec.app;

import com.seafooddelakec.Title;
import com.seafooddelakec.menu.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.apps.util.Prompter;
import java.util.Scanner;

public class Controller {
    private final Title title = new Title();
    private final MenuItemLoader menuItemLoader = new MenuItemLoader();
    private final Host host = new Host();
    private final Server server = new Server();
    private final Scanner scanner = new Scanner(System.in);
    private final List<MenuItem> menuItems = new ArrayList<>();


    /* TODO:
     * after first selection is made, customer has option to reorder another selection
     * (repeat previous prompts)
     *
     * or end order selection (menu will leave screen)
     * server sends order to the kitchen
     *
     * customer waits for food (i.e demo loading screen)
     * customer eats food [ -- keep eating, press E] *** if we have time to implement
     *
     * server delivers customer their bill
     * (behind the scenes): each menu item has prices, the bill is calculated by adding its total items
     * user is shown total
     * and then being prompted to tip after
     * the tip amounts: NONE, OKAY, GREAT, EXCELLENT
     * the new total is displayed to customer
     *
     * server responds based on the tip amount selected
     *
     * exit()
     *
     */
    public void execute() {
        title.display();
        menuItemLoader.loadMenuItems();
        intro();
        orderFood();
        bill();
    }

    private void intro() {
        host.greeting();
        server.greeting();
    }

    private void orderFood() {
        Combo selectedCombo = Combo.selectCombo(scanner);
        Drink selectedDrink = Drink.selectDrink(scanner);

        while (true) {
            System.out.println("Would you like to order food? (y/n)");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("n")) {
                System.out.println("You selected: " + selectedCombo);
                System.out.println("You selected: " + selectedDrink);
                menuItems.add(selectedCombo);
                menuItems.add(selectedDrink);

                System.out.println("Server: We will send your order to the kitchen and will be out shortly.");
                System.out.println("...");
                System.out.println("Server: Here is your order, enjoy!");
                System.out.println("Server: Here is your bill.");
                break;
            } else if (input.equalsIgnoreCase("y")) {
                selectedCombo = Combo.selectCombo(scanner);
                selectedDrink = Drink.selectDrink(scanner);
                menuItems.add(selectedCombo);
                menuItems.add(selectedDrink);
            } else {
                System.out.println("Invalid input!");
            }
        }
    }


    public void bill() {
        System.out.println("\n-------YOUR ORDER-------");
        double total = 0.0;
        for (MenuItem item : menuItems) {
            System.out.printf("%s - $%.2f%n", item.description(), item.price());
            total += item.price();
        }
        System.out.printf("Total: $%.2f%n", total);
    }

    private void reorder() {
    }

    private void serverEmoji() {
    }

    private void exit() {
    }
}