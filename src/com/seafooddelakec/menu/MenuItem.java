package com.seafooddelakec.menu;

public record MenuItem(Integer id, Double price, String description, Type type) {

    @Override
    public String toString() {
        return String.format("[%d] $%.2f - %s", id, price, description);
    }

    public enum Type {
        COMBO, DRINK
    }
}
