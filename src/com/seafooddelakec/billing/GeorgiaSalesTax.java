package com.seafooddelakec.billing;

public class GeorgiaSalesTax implements SalesTaxCalculator {
    // Define constants for tax-free amount and tax rate
    private static final double TAX_FREE_AMOUNT = 0.0; // Assuming there's no tax-free amount; adjust as needed
    private static final double TAX_RATE = 0.089; // 8.9% tax rate as a decimal

    @Override
    public double taxAmount(double taxable) {
        double result = 0.0;

        if (taxable > TAX_FREE_AMOUNT) {
            result = (taxable - TAX_FREE_AMOUNT) * TAX_RATE;
        }

        return result;
    }
}