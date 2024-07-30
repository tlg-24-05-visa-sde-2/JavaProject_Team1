package com.seafooddelakec;

public class Intro {
    public static void main(String[] args) {
        // Host greets customer and captures the name
        Host host = new Host();
        host.greeting();

        // insert menu board

        // Server greets customer and prompts user to order
        Server server = new Server();
        server.greeting();
        server.takeOrder();
        server.submitOrder();
    }

}