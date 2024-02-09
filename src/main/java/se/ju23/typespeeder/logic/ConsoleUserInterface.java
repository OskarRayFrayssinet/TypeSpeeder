package se.ju23.typespeeder.logic;

import java.util.Scanner;

public class ConsoleUserInterface implements UserInterface{
    private Scanner scanner;

    public ConsoleUserInterface() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void displayText(String text) {
        System.out.println(text);
    }

    public String getUserInput() {
        return scanner.nextLine();
    }
}
