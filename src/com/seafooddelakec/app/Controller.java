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
    private final Prompter prompter = new Prompter(new Scanner(System.in));
    private final List<MenuItem> menuItems = menuItemLoader.loadMenu();
    private Menu menu;


    public void execute() {
        clear();
        menu = new Menu(menuItems);
        animations.intro();
        host.greeting();
        clear();
        orderFood();
        bill();
        pay();
    }


    private void orderFood() {
        animations.server();
        String serverIntro = " Server: Hi, my name is Beyonce and I will be your server today!";
        for (int i = 0; i < serverIntro.length(); i++) {
            System.out.print(serverIntro.charAt(i));
            pause(20);
        }
        pause(2000);
        clear();

        menu.displayMenu();

        while (true) {
            MenuItem selectedItem = null;
            while (selectedItem == null) {
                String orderMsg =  " What would you like to order? Type a number [1-9] to make a selection: \n ";
                for (int i = 0; i < orderMsg.length(); i++) {
                    System.out.print(orderMsg.charAt(i));
                    pause(20);
                }
                String input = prompter.prompt(" > ");
                blankLines(1);
                try {
                    int id = Integer.parseInt(input);
                    selectedItem = menu.getItemById(id);
                    if (selectedItem == null) {
                        clear();
                        menu.displayMenu();
                        System.out.println(orderMsg);

                    }
                } catch (NumberFormatException e) {
                    clear();
                    menu.displayMenu();
                    System.out.println(orderMsg);
                }
            }

            menu.addOrderedItem(selectedItem);

            boolean validInput = false;
            while (!validInput) {
                System.out.println(" Would you like to order something else? [y/n]: ");
                String orderMore = prompter.prompt("  > ", "^[yYnN]$",
                        " Invalid input: Please enter 'y' for yes or 'n' for no.");
                blankLines(1);

                if (orderMore.equalsIgnoreCase("n")) {
                    clear();
                    blankLines(2);
                    String sendToKitchen = " Server: We will send your order to the kitchen and will be out shortly.";
                    for (int i = 0; i <  sendToKitchen.length(); i++) {
                        System.out.print(sendToKitchen.charAt(i));
                        pause(20);
                    }
                    pause(2000);
                    clear();

                    animations.cook();
                    pause(2000);
                    clear();

                    animations.food();
                    blankLines(1);
                    String orderReady = " Server: Here is your order, enjoy!";
                    for (int i = 0; i <  orderReady.length(); i++) {
                        System.out.print(orderReady.charAt(i));
                        pause(20);
                    }
                    pause(3000);
                    clear();

                    animations.finalMeal();
                    pause(3000);
                    clear();

                    blankLines(1);
                    String billReady = " Server: Here is your bill.";
                    for (int i = 0; i <  billReady.length(); i++) {
                        System.out.print(billReady.charAt(i));
                        pause(20);
                    }
                    pause(2000);
                    clear();
                    return;
                } else if (orderMore.equalsIgnoreCase("y")) {
                    clear();
                    menu.displayMenu();
                    validInput = true;
                }
            }
        }
    }


    private void bill() {
        TipEnum tipEnum;

        String border = " +=====+==========+===============================================+";
        String titleFormat = " | %-62s |";
        String itemFormat = " | %-3d | $%-7.2f | %-45s |";
        String headerFormat = " | %-3s | %-8s | %-45s |";
        String totalFormat = " | %-3s | $%-7.2f | %-45s |";

        blankLines(2);
        System.out.println(border);
        System.out.printf((titleFormat) + "%n", "BILL");
        System.out.println(border);
        System.out.printf((headerFormat) + "%n", "id", "price", "description");
        System.out.println(border);

        double total = 0.0;
        for (MenuItem item : menu.getOrderedItems()) {
            String description = item.description();
            if (description.length() > 45) {
                description = description.substring(0, 45) + "...";
            }
            System.out.printf((itemFormat) + "%n", item.id(), item.price(), description);
            total += item.price();
        }

        System.out.println(border);
        System.out.printf((totalFormat) + "%n", "", total, "Subtotal");

        double TAX_RATE = 0.089;
        double salesTax = total * TAX_RATE;
        System.out.printf((totalFormat)+ "%n", "", salesTax, "Sales Tax");

        double result = total + salesTax;
        System.out.printf((totalFormat)+ "%n", "", result, "Total");
        System.out.println(border);

        blankLines(2);
        System.out.println(" Server: Would you like to leave a tip? [y/n]: ");
        String input = prompter.prompt("  > ", "^[yYnN]$",
                " Invalid input: Please enter 'y' for yes or 'n' for no.\n");
        double tipAmount = 0;
        if (input.equalsIgnoreCase("y")) {
            blankLines(1);
            System.out.printf(" Tip Percentages: [Type 1, 2, or 3]\n\n [1]%s\n [2]%s\n [3]%s\n\n",
                    TipEnum.OKAY, TipEnum.GREAT, TipEnum.EXCELLENT);
            String input2 = prompter.prompt("  > ", "^[123]$",
                    "Invalid input: Please enter '1', '2', or '3'.");

            switch (input2) {
                case "1" -> {
                    tipEnum = TipEnum.OKAY;
                    tipAmount = result * tipEnum.getRate();
                }
                case "2" -> {
                    tipEnum = TipEnum.GREAT;
                    tipAmount = result * tipEnum.getRate();
                }
                case "3" -> {
                    tipEnum = TipEnum.EXCELLENT;
                    tipAmount = result * tipEnum.getRate();
                }
            }

            blankLines(1);

            System.out.println(border);
            System.out.printf((titleFormat) + "%n", "BILL");
            System.out.println(border);
            System.out.printf((totalFormat)+ "%n", "", tipAmount, "Tip Amount");
            double finalTotal = result + tipAmount;
            System.out.printf((totalFormat)+ "%n", "", finalTotal, "Final Total");
            System.out.println(border);
        }
    }

    private void pay() {
        blankLines(2);
        System.out.println(" Server: To complete your payment, type 'p'.");
        String input = prompter.prompt(" > ", "^[pP]$",
                " Server: Invalid input. You must type 'p' to proceed with payment.");
        clear();
        if (input.equalsIgnoreCase("p")) {
            animations.payment();
            clear();
            animations.server();
            blankLines(1);

            String successfulPayment = " Server: You paid successfully! Thank you! \n";
            for (int i = 0; i <  successfulPayment.length(); i++) {
                System.out.print(successfulPayment.charAt(i));
                pause(20);
            }

            String comeBack = " Server: Come back again!";
            for (int i = 0; i <  comeBack.length(); i++) {
                System.out.print(comeBack.charAt(i));
                pause(20);
            }
            blankLines(2);
        }
    }

}