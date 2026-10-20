package com.example;

public class App {
    public String getGreeting() {
        return "Hello, CI/CD Pipeline!";
    }

    public static void main(String[] args) {
        App app = new App();
        System.out.println(app.getGreeting());
    }
}