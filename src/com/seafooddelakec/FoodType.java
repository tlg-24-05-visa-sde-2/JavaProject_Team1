package com.seafooddelakec;

enum FoodType {
    COMBO_1(16.99),
    COMBO_2(26.99),
    COMBO_3(64.99),
    COMBO_4(49.98),
    COMBO_5(48.49);

    private final double price;

    // Constructor
    FoodType(double price) {
        this.price = price;
    }

    // Getter for the price
    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%.2f", getClass().getSimpleName() + ": $" + price);       //.2f formats it to two decimal points
    }
}