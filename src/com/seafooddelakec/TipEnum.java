package com.seafooddelakec;

// TODO: Mostly like remove the tip enum
public enum TipEnum {
    NONE(0.0),
    OKAY(0.12),
    GREAT(0.18),
    EXCELLENT(0.20);

    private final double rate;

    // Constructor
    TipEnum(double rate) {
        this.rate = rate;
    }

    // Getter for the rate
    public double getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return String.format(" tip: %.2f%%", getRate() * 100);
    }
}