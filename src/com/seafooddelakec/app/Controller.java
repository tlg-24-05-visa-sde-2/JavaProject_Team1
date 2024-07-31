package com.seafooddelakec.app;

import com.seafooddelakec.TipEnum;
import com.seafooddelakec.Title;
import com.seafooddelakec.menu.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    private final Title title = new Title();
    private final MenuItemLoader menuItemLoader = new MenuItemLoader();
    private final Host host = new Host();
    private final Server server = new Server();
    private final Scanner scanner = new Scanner(System.in);
    private final List<MenuItem> menuItems = new ArrayList<>();
    private TipEnum tipEnum = TipEnum.NONE;


    public void execute() {
        title.display();
        menuItemLoader.loadMenuItems();
        intro();
        orderFood();
        bill();
        pay();
//        serverExpression();
    }

    private void intro() {
        host.greeting();
        server.greeting();
    }

    private void orderFood() {
        Combo selectedCombo = Combo.selectCombo(scanner);
        Drink selectedDrink = Drink.selectDrink(scanner);

        menuItems.add(selectedCombo);
        menuItems.add(selectedDrink);

        while (true) {
            System.out.println("Would you like to order food? (y/n)");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("n")) {
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
        System.out.println("You selected: " + selectedCombo);
        System.out.println("You selected: " + selectedDrink);
    }



    public void bill() {
        double TAX_RATE = 0.089;

        System.out.println("\n-------YOUR ORDER-------");
        double total = 0.0;
        for (MenuItem item : menuItems) {
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
                    TipEnum tipEnum = TipEnum.OKAY;
                    double okay = result * tipEnum.getRate();
                    newTotal += okay;
                }
                case "18.00" -> {
                    TipEnum tipEnum = TipEnum.GREAT;
                    double great = result * tipEnum.getRate();
                    newTotal += great;
                }
                case "20.00" -> {
                    TipEnum tipEnum = TipEnum.EXCELLENT;
                    double excellent = result * tipEnum.getRate();
                    newTotal += excellent;
                }
            }

        } else if (input.equalsIgnoreCase("n")) {
            tipEnum = TipEnum.NONE;
        }

        System.out.printf("Tip Amount: $%.2f%n\n", newTotal);
        // the newTotal is holding the tip decision, result is the previous total
        // finalTotal is the previous total + tip decision
        double finalTotal = newTotal + result;
        System.out.printf("Final total: $%.2f%n \n", finalTotal);
    }

    public void pay() {
        System.out.println("Server: Are you ready to pay? [y/n]");
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("y")) {
            System.out.println("Server: You paid successfully!");
        } else if (input.equalsIgnoreCase("n")) {
            System.out.println("Server: Would you like to order more? [y/n]");
            String input2 = scanner.nextLine();

            if (input2.equalsIgnoreCase("y")) {
                orderFood();
                bill();
            } else if (input2.equalsIgnoreCase("n")) {
                System.out.println("Server: Let me know when you're ready.");
            } else {
                System.out.println("Server: Invalid input. Please enter 'y' or 'n'.");
            }
        } else {
            System.out.println("Server: Invalid input. Please enter 'y' or 'n'.");
        }
    }

    private void serverExpression() {
        System.out.println("Server: ");
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("y")) {
            switch (tipEnum) {
                case OKAY -> System.out.println(":|");
                case GREAT -> System.out.println(":)");
                case EXCELLENT -> System.out.println(":D");
            }
        } else if (input.equalsIgnoreCase("n")) {
            System.out.println(">:(");
        }
        // prompt customer if they want to tip
        // if Y, then:
        // display tip percentage options
        // user can only select from the stated available options
        // once user selects, the tip will be applied to the total
        // customer then officially pays for the bill

        // if customer tips:
        //                  OKAY: responds :|
        //                  GREAT: responds :)
        //                  EXCELLENT: responds :D

        // if N, then:
        //                  NONE: responds >:(
    }

}