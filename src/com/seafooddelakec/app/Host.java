package com.seafooddelakec.app;

import com.seafooddelakec.RestaurantEmployee;
import com.apps.util.Console;
import com.apps.util.Prompter;

import java.util.Scanner;

import static com.apps.util.Console.*;

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

        String serverGreeting = getClass().getSimpleName() + ": Hi! Welcome to Seafood Delake-C & Co!\n";

        blankLines(2);

        for (int i = 0; i < serverGreeting.length(); i++) {
            System.out.print(serverGreeting.charAt(i));
            pause(75);
        }
        blankLines(1);
        pause(100);

        while (true) {
            String hostGreeting = "Host: What name is your reservation under?\n";

            for (int i = 0; i < hostGreeting.length(); i++) {
                System.out.print(hostGreeting.charAt(i));
                pause(75);
            }

            String nameChoice = prompter.prompt("> ",
                    "^[A-Za-z]+(?: [A-Za-z]+)*$",
                    "Invalid input: Please enter a valid name " +
                            "(only letters and names with space between allowed).\n");
            blankLines(1);

            if (!nameChoice.trim().isEmpty()) {
                customerName = nameChoice.trim();
                System.out.println(getClass().getSimpleName() + ": Your table is ready, " + customerName +
                        ". Right this way, I will bring you to your table. Take a moment to review the menu.\n");
                clear();
                break;
            } else {
                System.out.println(getClass().getSimpleName() +
                        ": Please enter a valid name to proceed with the reservation.");
            }
        }

    }
}
