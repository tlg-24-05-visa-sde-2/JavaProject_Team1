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
        String serverIntro = " Server: Hi, my name is Beyonce and I will be your server today! \n ";
        for (int i = 0; i < serverIntro.length(); i++) {
            System.out.print(serverIntro.charAt(i));
            pause(1);
        }
        pause(3000);
        clear();

        menu.displayMenu();
        blankLines(2);

        String orderMsg = " Type a number [1-9] to make a selection: ";

        while (true) {
            MenuItem selectedItem = null;
            while (selectedItem == null) {
                System.out.println(" What would you like to order? " + orderMsg);
                String input = prompter.prompt(" > ");
                blankLines(1);
                try {
                    int id = Integer.parseInt(input);
                    selectedItem = menu.getItemById(id);
                    if (selectedItem == null) {
                        clear();
                        menu.displayMenu();
                        blankLines(1);
                        System.out.println(orderMsg);

                    }
                } catch (NumberFormatException e) {
                    clear();
                    menu.displayMenu();
                    blankLines(1);
                    System.out.println(orderMsg);
                }
            }

            menu.addOrderedItem(selectedItem);

            boolean validInput = false;
            while (!validInput) {
                System.out.println(" Would you like to order something else? [y/n]: ");
                String orderMore = prompter.prompt(" > ", "^[yYnN]$",
                        " Invalid input: Please enter 'y' for yes or 'n' for no.\n");

                if (orderMore.equalsIgnoreCase("n")) {
                    clear();
                    System.out.println(" Server: We will send your order to the kitchen and will be out shortly.");
                    pause(2000);
                    clear();

                    animations.cook();
                    pause(1000);
                    clear();

                    animations.food();
                    blankLines(1);
                    System.out.println(" Server: Here is your order, enjoy!");
                    pause(2000);
                    clear();

                    animations.finalMeal();
                    pause(3000);
                    clear();

                    System.out.println(" Server: Here is your bill.");
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
        double TAX_RATE = 0.089;
        TipEnum tipEnum;

        String border = "\t   +=====+==========+====================================+";
        String titleFormat = "\t   | %-51s |";
        String itemFormat = "\t   | %-3d | $%-7.2f | %-34s |";
        String headerFormat = "\t   | %-3s | %-8s | %-34s |";
        String totalFormat = "\t   | %-3s | $%-7.2f | %-34s |";

        displayAnimatedString(border);
        displayAnimatedString(String.format(titleFormat, "BILL"));
        displayAnimatedString(border);
        displayAnimatedString(String.format(headerFormat, "id", "price", "description"));
        displayAnimatedString(border);

        double total = 0.0;
        for (MenuItem item : menu.getOrderedItems()) {
            String description = item.description();
            if (description.length() > 34) {
                description = description.substring(0, 31) + "...";
            }
            displayAnimatedString(String.format(itemFormat, item.id(), item.price(), description));
            total += item.price();
        }

        displayAnimatedString(border);
        displayAnimatedString(String.format(totalFormat, "", total, "Subtotal"));

        double salesTax = total * TAX_RATE;
        displayAnimatedString(String.format(totalFormat, "", salesTax, "Sales Tax"));

        double result = total + salesTax;
        displayAnimatedString(String.format(totalFormat, "", result, "Total"));
        displayAnimatedString(border);

        blankLines(2);
        System.out.println(" Server: Would you like to leave a tip? [y/n]");
        String input = prompter.prompt(" > ", "^[yYnN]$",
                " Invalid input: Please enter 'y' for yes or 'n' for no.\n");
        double tipAmount = 0;
        if (input.equalsIgnoreCase("y")) {
            blankLines(1);
            System.out.printf(" Tip Percentages: [Type 1, 2, or 3]\n\n [1]%s\n [2]%s\n [3]%s\n\n",
                    TipEnum.OKAY, TipEnum.GREAT, TipEnum.EXCELLENT);
            String input2 = prompter.prompt(" > ", "^[123]$",
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

            displayAnimatedString(border);
            displayAnimatedString(String.format(totalFormat, "", tipAmount, "Tip Amount"));
            double finalTotal = result + tipAmount;
            displayAnimatedString(String.format(totalFormat, "", finalTotal, "Final Total"));
            displayAnimatedString(border);
        }
    }

    private void displayAnimatedString(String str) {
        blankLines(1);
        for (int i = 0; i < str.length(); i++) {
            System.out.print(str.charAt(i));
            pause(2);
        }
        System.out.println();
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
            System.out.println(" Server: You paid successfully! Thank you!");
            System.out.println(" Server: Come back again!");
        }
    }

}