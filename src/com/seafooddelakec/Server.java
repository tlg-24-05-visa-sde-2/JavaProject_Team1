package com.seafooddelakec;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Server class functions to greet and capture customer's order.
 * once customer enters a valid menu selection, server prompts user to either:
 * select another menu item or complete order
 * If user chooses to add another menu item, same server prompt as before (add or complete order)
 * If user completes order, server submits order to "kitchen"
 * Chat screen should clear
 */
public class Server implements RestaurantEmployee {
    // private fields to access:
    // FoodType Enum (menu item + price)
    // user inputted customer name
    private List<FoodType> orderItems;
    private String customerName;

    // constructors for encapsulation
    public Server() {
        this.orderItems = new ArrayList<>();
        this.customerName = Host.getCustomerName();
    }

    public List<FoodType> getOrderItems() {
        return orderItems;
    }

    public String getCustomerName() {
        return customerName;
    }


    @Override
    public void greeting() {
        String serverName = "Beyonce";
        // show menu board (from stream file)

        if (getCustomerName() != null && !getCustomerName().isEmpty()) {
            System.out.println("Server: Hi " + getCustomerName() +
                    ", my name is " + serverName + " and I will be your server today.");
        } else {
            System.out.println("Server: Hi, my name is " + serverName + " and I will be your server today.");
        }
    }

    public void takeOrder() {
        Scanner scanner = new Scanner(System.in);
        String foodInputMsg = " [ex. COMBO_1, COMBO_2..]";
        String instructions = "Type 'y' to add another item or 'n' to complete your order: ";

        while (true) {
            System.out.print("Please enter the item you would like to order" + foodInputMsg + ": \n");
            String orderInput = scanner.nextLine().trim().toUpperCase();

                FoodType selectedFood = FoodType.valueOf(orderInput);
                getOrderItems().add(selectedFood);

                if (!orderInput.isEmpty()) {
                    System.out.println("\n" + getClass().getSimpleName() +
                            ": Would you like to add another item or will this complete your order?\n");
                    System.out.println("Current order: " + getOrderItems() + "\n");
                    System.out.print(instructions + "\n");
                }

                String decision = scanner.nextLine().trim().toLowerCase();

                if (decision.equals("n")) {
                    break;
                } else if (!decision.equals("y")) {
                    System.out.println(getClass().getSimpleName() +
                            ": Invalid input. " + instructions);
                }

        }
        System.out.println();
    }

    public void submitOrder() {
        System.out.println(getCustomerName() + "'s order: " + this.getOrderItems());
        System.out.println("Your order has been placed. We should have that out for you shortly!");
    }


}


