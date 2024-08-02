package com.seafooddelakec.app;

import com.seafooddelakec.TipEnum;
import com.seafooddelakec.Animations;
import com.seafooddelakec.menu.*;

import java.util.List;
import java.util.Scanner;
import com.apps.util.Prompter;

import static com.apps.util.Console.*;


public class Controller {
    private final Animations animations = new Animations();
    private final MenuItemLoader menuItemLoader = new MenuItemLoader();
    private final Host host = new Host();
    private final Scanner scanner = new Scanner(System.in);
    private final Prompter prompter = new Prompter(new Scanner(System.in));
    private final List<MenuItem> menuItems = menuItemLoader.loadMenu();
    private Menu menu;


    public void execute() {
        menu = new Menu(menuItems);
        animations.intro();
        host.greeting();
        clear();
        orderFood();
        bill();
        pay();
    }

    /*
     * THIS IS WHAT CORTNEY WILL TALK ABOUT
     */
    private void orderFood() {
        animations.server();

        while (true) {
            menu.displayMenu();
            blankLines(1);

            MenuItem selectedItem = null;
            while (selectedItem == null) {
                pause(1000);
                System.out.println(" What would you like to order? Type a number [1-9] to make a section: ");
                String input = prompter.prompt("> ");
                clear();

                try {
                    int id = Integer.parseInt(input);
                    selectedItem = menu.getItemById(id);
                    if (selectedItem == null) {
                        System.out.println("Invalid ID. Please try again.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                }
            }
            clear();

            menu.addOrderedItem(selectedItem);
            System.out.println("Would you like to order something else? [y/n]: ");
            String orderMore = prompter.prompt("> ");

            if (orderMore.equalsIgnoreCase("n")) {
                clear();
                System.out.println("Server: We will send your order to the kitchen and will be out shortly.");
                pause(1000);
                clear();

                animations.cook();
                pause(1000);
                clear();

                animations.food();
                System.out.println("Server: Here is your order, enjoy!");
                pause(1000);
                clear();


                System.out.println("Server: Here is your bill.");
                break;
                // TODO : ERROR HANDLING
            } else if (!orderMore.equalsIgnoreCase("y") && !orderMore.equalsIgnoreCase("n")) {
                System.out.println("Invalid input! Assuming you want to order more. Please type 'y/n' to make a section.");
            }
        }
    }


    /*
     * THIS IS WHAT KEA WILL TALK ABOUT
     */
    private void bill() {
        double TAX_RATE = 0.089;
        TipEnum tipEnum;

        System.out.println("\n-------YOUR ORDER-------");
        blankLines(1);
        // TODO:
        double total = 0.0;
        for (MenuItem item : menu.getOrderedItems()) {
            System.out.printf("%s - $%.2f%n", item.description(), item.price());
            total += item.price();
        }
        System.out.printf("Total (before tax): $%.2f%n", total);
        double salesTax = total * TAX_RATE;
        double result = salesTax + total;

        System.out.printf("Sales Tax: $%.2f%n", salesTax);

        System.out.printf("Total (after): $%.2f%n", result);

        System.out.println("Server: Would you like to leave a tip?");
        String input = scanner.nextLine();

        double newTotal = 0;
        if (input.equalsIgnoreCase("y")) {
            System.out.println("Tip Percentages: " + TipEnum.OKAY + TipEnum.GREAT + TipEnum.EXCELLENT);

            String input2 = scanner.nextLine();

            switch (input2) {
                case "12.00" -> {
                    tipEnum = TipEnum.OKAY;
                    double okay = result * tipEnum.getRate();
                    newTotal += okay;
                }
                case "18.00" -> {
                    tipEnum = TipEnum.GREAT;
                    double great = result * tipEnum.getRate();
                    newTotal += great;
                }
                case "20.00" -> {
                    tipEnum = TipEnum.EXCELLENT;
                    double excellent = result * tipEnum.getRate();
                    newTotal += excellent;
                }
            }

        }

        System.out.printf("Tip Amount: $%.2f%n\n", newTotal);
        // the newTotal is holding the tip decision, result is the previous total
        // finalTotal is the previous total + tip decision
        double finalTotal = newTotal + result;
        System.out.printf("Final total: $%.2f%n \n", finalTotal);
    }

    private void pay() {
        System.out.println("Server: To complete your payment, type 'p'.");
        String input = scanner.nextLine();

        if (!input.equalsIgnoreCase("p")) {
            System.out.println("Server: Invalid input. You must type 'p' to proceed with payment.");
        } else {
            System.out.println("Server: You paid successfully! Thank you!");
        }
    }

}