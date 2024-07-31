package com.seafooddelakec.app;

import com.seafooddelakec.RestaurantEmployee;
import com.apps.util.Console;
import com.apps.util.Prompter;

import java.util.Scanner;

/**
 * Host class functions to greet and capture customer's name.
 * Once a name is provided, the host leads the customer to a table and provides
 * the customer with a menu. The chat screen is cleared after a valid name is entered.
 */
public class Host implements RestaurantEmployee {

    private static String customerName;

    public static String getCustomerName() {
        return customerName;
    }

    /**
     * Greets the customer and captures their name using Prompter.
     * Continues to prompt for a valid name until received.
     * Once a valid name is entered, the screen is cleared and the customer is informed.
     */
    @Override
    public void greeting() {
        Prompter prompter = new Prompter(new Scanner(System.in));

        System.out.println(getClass().getSimpleName() + ": Hi! Welcome to Seafood Delake-C & Co!\n");

        while (true) {
            String name = prompter.prompt(
                    getClass().getSimpleName() + ": What name is your reservation under?\n",
                    "^[A-Za-z]+(?: [A-Za-z]+)*$",
                    "Invalid input: Please enter a valid name " +
                            "(only letters and names with space between allowed).\n");

            if (!name.trim().isEmpty()) {
                customerName = name.trim();
                System.out.println(getClass().getSimpleName() + ": Your table is ready, " + customerName +
                        ". Right this way, I will bring you to your table. Take a moment to review the menu.\n");
                Console.clear();
                break;
            } else {
                System.out.println(getClass().getSimpleName() +
                        ": Please enter a valid name to proceed with the reservation.");
            }
        }
    }
}
