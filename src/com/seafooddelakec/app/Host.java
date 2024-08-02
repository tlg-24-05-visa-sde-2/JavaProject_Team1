package com.seafooddelakec.app;

import com.seafooddelakec.RestaurantEmployee;
import com.apps.util.Prompter;
import com.seafooddelakec.Animations;

import java.util.Scanner;

import static com.apps.util.Console.*;

/**
 * Host class functions to greet and capture customer's name.
 * Once a name is provided, the host leads the customer to a table and provides
 * the customer with a menu. The chat screen is cleared after a valid name is entered.
 */

// TODO: MOST LIKELY MOVE TO CONTROLLER
public class Host implements RestaurantEmployee {
    private final static Animations ANIMATIONS = new Animations();
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

        ANIMATIONS.host();
        blankLines(1);

        String hostGreeting1 = " Host: Hi! Welcome to Seafood Delake-C & Co!\n";

        for (int i = 0; i < hostGreeting1.length(); i++) {
            System.out.print(hostGreeting1.charAt(i));
            pause(25);
        }
        blankLines(1);
        pause(100);

        String hostGreeting2 = " Host: What name is your reservation under?\n";

        for (int i = 0; i < hostGreeting2.length(); i++) {
            System.out.print(hostGreeting2.charAt(i));
            pause(25);
        }

        blankLines(1);

        while (true) {

            String nameChoice = prompter.prompt(" > ",
                    "^[A-Za-z]+(?: [A-Za-z]+)*$",
                    " Invalid input: Please enter a valid name " +
                            "(only letters and names with space between allowed).\n");
            blankLines(1);

            if (!nameChoice.trim().isEmpty()) {

                customerName = nameChoice.trim();

                System.out.print(" Searching");
                for (int i = 0; i < 7; i++) {
                    System.out.print(".");
                    pause(500);
                }
                clear();
                String response = " Host: Your table is ready, " + customerName +
                        ". Right this way, I will bring you to your table.";
                for (int i = 0; i < response .length(); i++) {
                    System.out.print(response .charAt(i));
                    pause(75);
                }
                pause(2000);
                clear();

                ANIMATIONS.table();
                blankLines(1);

                String review = " Host: Please take a moment to review the menu. The server will be right with you.";
                for (int i = 0; i < review.length(); i++) {
                    System.out.print(review.charAt(i));
                    pause(75);
                }
                pause(1000);
                clear();
                break;
            } else {
                System.out.println(" Host: Please enter a valid name to proceed with the reservation.");
            }
        }

    }
}
