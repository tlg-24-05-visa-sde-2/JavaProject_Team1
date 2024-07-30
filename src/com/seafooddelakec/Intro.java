package com.seafooddelakec;

public class Intro {
    public static void main(String[] args) {
        // Host greets customer and captures the name
        Host host = new Host();
        host.greeting();

        Server server = new Server();
        server.greeting();
    }

}