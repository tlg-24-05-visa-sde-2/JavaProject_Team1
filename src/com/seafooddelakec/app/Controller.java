package com.seafooddelakec.app;

import com.seafooddelakec.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    private static final Table table = new Table();
    private final Host host = new Host();
    private final Server server = new Server();
    private final Scanner scanner = new Scanner(System.in);
    private final List<String> menuItems = new ArrayList<>();

    /* intro()
     * restaurant name displays,
     * customer meets host who prompt user to input their name,
     *
     * table()
     * Customer is seated and menu is read and displayed from a csv file
     * customer meets server
     *
     *
     * Menu items will include prices (potentially using if statements)
     *
     * user drink & combo selection prompt
     * user enters drink & combo codes for each selection,
     * Prompt1: DRINKS ex. [1]water [2]soda [3]tequila
     * Prompt2: COMBOS [1]shrimp,corn,potato [2]..
     *
     *
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
     * farewell()
     *
     */
    public void execute() {
        intro();
        table();
    }

    private void intro() {
        host.greeting();
        server.greeting();
    }

    private void table() {
        System.out.println();
        table.displayCombos();
        System.out.println();
        System.out.print("Which combo would you like? : ");
        int comboNumber = scanner.nextInt();
        String selectedCombo = table.getCombo(comboNumber);
        menuItems.add(selectedCombo);
        System.out.println();

        System.out.println();
        table.displayDrinks();
        System.out.println();
        System.out.print("What would you like to drink? : ");
        int drinkNumber = scanner.nextInt();
        String selectedDrink = table.getDrink(drinkNumber);
        menuItems.add(selectedDrink);
        table.displayOrder(menuItems);
    }

    private void reorder() {
    }

    private void bill() {
    }

    private void serverEmoji() {
    }

    private void farewell() {
    }
}