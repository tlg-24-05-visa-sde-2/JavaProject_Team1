package com.seafooddelakec;

class Server implements RestaurantEmployee {

    @Override
    public void greeting() {
        String serverName = "Beyonce";

        if (Host.getCustomerName() != null && !Host.getCustomerName().isEmpty()) {
            System.out.println("Server: Hi " + Host.getCustomerName() +
                    ", my name is " + serverName + " and I will be your server today.");
        } else {
            System.out.println("Server: Hi, my name is " + serverName + " and I will be your server today.");
        }
    }

}