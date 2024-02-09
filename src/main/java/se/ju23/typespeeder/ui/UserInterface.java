package se.ju23.typespeeder.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.entity.ResultRepository;
import se.ju23.typespeeder.entity.UserRepository;
import se.ju23.typespeeder.util.UserInput;

import java.util.Scanner;
@Component
public class UserInterface {
    private final Menu menu;

    public UserInterface(Menu menu) {
        this.menu = menu;
    }
    public void start() {
        boolean running = true;
        while (running) {
            menu.displayMenu();
            System.out.println("Välj ett alternativ (eller ange 0 för att avsluta):");
            int choice = UserInput.readInt(0, 5);

            switch (choice) {
                case 1 -> {
                    // Logga in
                    System.out.println("Logga in...");
                }
                case 2 -> {
                    // Konto Hantering
                    System.out.println("Konto Hantering...");
                }
                case 3 -> {
                    // Språkval
                    System.out.println("Väljer språk...");
                }
                case 4 -> {
                    // Spela
                    System.out.println("Startar spel...");
                }
                case 5 -> {
                    // Nyheter och Patch-information
                    System.out.println("Visar nyheter och patch-information...");
                }
                case 0 -> running = false;
                default -> System.out.println("Ogiltigt val, försök igen.");
            }
        }
    }
}
