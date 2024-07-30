package com.seafooddelakec;

import java.util.Scanner;
/*
 * Host class functions to greet and capture customer's name.
 * One name is taken, Host leads customer to table and provides
 * customer a menu.
 * Chat screen should clear
 */
class Host implements RestaurantEmployee {

    private static String customerName;

    public static String getCustomerName() {
        return customerName;
    }

    // host greets customer and captures name through scanner implementation
    // while the name variable is null, customer is prompted to enter a name until valid
    // if the name is valid, customer name is captured and receives a menu
    // clear screen
    @Override
    public void greeting() {
        Scanner scanner = new Scanner(System.in);
        customerName = "";

        System.out.println(getClass().getSimpleName() + ": Hi! Welcome to Seafood Delake-C & Co! " +
                "What name is your reservation under?");

        while (true) {
            System.out.print("You: ");
            customerName = scanner.nextLine().trim();

            if (customerName.isEmpty()) {
                System.out.println(getClass().getSimpleName() +
                        ": Please enter a valid name to proceed with the reservation.");
            } else {
                System.out.println(getClass().getSimpleName() +
                        ": Your table is ready, " + customerName +
                        ". Right this way, I will bring you to your table. Take a moment to review the menu.");
                break;
            }
        }
        System.out.println();

        // clears the screen and closes scanner
        System.out.print("\033[H\033[2J");
        System.out.flush();
        scanner.close();
    }
}