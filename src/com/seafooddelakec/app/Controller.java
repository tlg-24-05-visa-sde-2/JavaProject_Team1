package com.seafooddelakec.app;

import com.seafooddelakec.TipEnum;
import com.seafooddelakec.Animations;
import com.seafooddelakec.menu.*;

import java.util.List;
import java.util.Scanner;
import com.apps.util.Prompter;

import static com.apps.util.Console.blankLines;
import static com.apps.util.Console.pause;


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
        orderFood();
        bill();
        pay();
    }

    /*
     * THIS IS WHAT CORTNEY WILL TALK ABOUT
     */
    private void orderFood() {
        while (true) {
            menu.displayMenu();

            MenuItem selectedItem = null;
            while (selectedItem == null) {
                animations.server();
                pause(1000);
                System.out.println(" What would you like want to order? [1-9]: ");
                String input = prompter.prompt("> ");
                blankLines(2);

                for (int i = 0; i < input.length(); i++) {
                    System.out.print(input.charAt(i));
                    pause(75);
                }
                blankLines(1);
                pause(100);
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

            menu.addOrderedItem(selectedItem);

            String orderMore = prompter.prompt("Would you like to order more food? (y/n): ");
            if (orderMore.equalsIgnoreCase("n")) {
                System.out.println("Server: We will send your order to the kitchen and will be out shortly.");
                animations.cook();
                System.out.println("...");
                animations.food();
                System.out.println("Server: Here is your order, enjoy!");
                System.out.println("Server: Here is your bill.");
                break;
            } else if (!orderMore.equalsIgnoreCase("y")) {
                System.out.println("Invalid input! Assuming you want to order more. ");
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
    // TODO: move to Customer
    private void pay() {
        System.out.println("Server: To complete your payment, press Enter.");
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("y")) {
            System.out.println("Server: You paid successfully! Thank you!");

        } else {
            System.out.println("Server: Invalid input. You must press [Enter] key to proceed with payment.");
        }
    }

}