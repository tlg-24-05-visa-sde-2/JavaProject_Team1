package com.seafooddelakec;

import java.util.List;

class Bill {

    public String calculateBill(List<FoodType> orderItems) {
        String bill = "";
        double total = 0;

        // Iterate through each item and construct the summary string
        for (FoodType food : orderItems) {
            bill += food.name() + " ($" + food.getPrice() + "), ";
            total += food.getPrice();
        }

        bill += " | Total: $" + String.format("%.2f", total);
        return bill;
    }
}