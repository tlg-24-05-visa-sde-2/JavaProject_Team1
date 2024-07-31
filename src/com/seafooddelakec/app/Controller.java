package com.seafooddelakec.app;

import com.seafooddelakec.Title;
import com.seafooddelakec.menu.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    private static final Title title = new Title();
    private static final MenuItemLoader menuItemLoader = new MenuItemLoader();
    private static final MenuDisplay menuDisplay = new MenuDisplay();
    private static final Host host = new Host();
    private static final Server server = new Server();
    private static final Scanner scanner = new Scanner(System.in);
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
        selectCombo();
        selectDrink();
    }

    private void selectCombo() {
        System.out.println();
        menuDisplay.displayCombos();
        System.out.println();
        System.out.print("Which combo would you like? : ");
        String input = scanner.nextLine().trim();
        int comboNumber = Integer.parseInt(input);
        Combo selectedCombo = Combo.getCombo(comboNumber);
        menuItems.add(selectedCombo);
        System.out.println("You selected: " + selectedCombo);
    }

    private void selectDrink() {
        System.out.println();
        menuDisplay.displayDrinks();
        System.out.println();
        System.out.print("Which drink would you like? : ");
        String input = scanner.nextLine().trim();
        int drinkNumber = Integer.parseInt(input);
        Drink selectedDrink = Drink.getDrink(drinkNumber);
        menuItems.add(selectedDrink);
        System.out.println("You selected: " + selectedDrink);
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