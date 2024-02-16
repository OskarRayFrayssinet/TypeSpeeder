package se.ju23.typespeeder.logic;

import org.springframework.beans.factory.annotation.Autowired;
import se.ju23.typespeeder.service.LoginService;

import java.util.Scanner;

public class MenuLogic {

    private final Scanner scanner = new Scanner(System.in);
    private final GameLogic gameLogic = new GameLogic();

    @Autowired
    private LoginService loginService;
    private boolean isEnglish = true; // Default language setting

    public void runApplication() {
        System.out.println("Welcome to TypeSpeeder");
        mainMenu();
    }
    public void displayMainMenu() {
        boolean running = true;
        while (running) {
            System.out.println("Welcome to the TypeSpeeder Application");
            System.out.println("1. Login");
            System.out.println("2. Start Game");
            System.out.println("3. Choose Language");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    login();//i went solo and it worked, the fuck..
                    // Implement your login logic here
                    System.out.println("Login functionality not implemented yet.");
                    break;
                case "2":
                    gameLogic.startGame();
                    break;
                case "3":
                    chooseLanguage();
                    break;
                case "4":
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    private void mainMenu() {
        boolean running = true;
        while (running) {
            System.out.println(getMessage("Select an option: \n1. Login\n2. Go to Game Menu\n3. Choose Language\n0. Exit"));
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    goToGameMenu();
                    break;
                case 3:
                    chooseLanguage();
                    break;
                case 0:
                    running = false;
                    System.out.println(getMessage("Exiting program."));
                    break;
                default:
                    System.out.println(getMessage("Invalid option. Please try again."));
            }
        }
    }

    private void login() {
        System.out.println(getMessage("Please enter your username:"));
        String username = scanner.nextLine();
        System.out.println(getMessage("Please enter your password:"));
        String password = scanner.nextLine();
        if (loginService.login(username, password)) {
            System.out.println(getMessage("Login successful!"));
            // You can proceed to game menu or another part of your application here
        } else {
            System.out.println(getMessage("Login failed. Please try again."));
        }
    }

    private void goToGameMenu() {
        // Placeholder for game menu logic
        System.out.println(getMessage("Welcome to the game menu!"));
    }

    private void chooseLanguage() {
        System.out.println("1. English\n2. Svenska");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over
        if (choice == 1) {
            isEnglish = true;
            System.out.println("Language set to English.");
        } else if (choice == 2) {
            isEnglish = false;
            System.out.println("Språk inställt på svenska.");
        } else {
            System.out.println(getMessage("Invalid option. Please try again."));
        }
    }

    // Helper method to get messages in the selected language
    private String getMessage(String englishMessage) {
        // Placeholder for actual translation logic
        if (isEnglish) {
            return englishMessage;
        } else {
            // Here you would return the Swedish translation
            // This is a simplified example, you would have a better translation mechanism
            switch (englishMessage) {
                case "Select an option: \n1. Login\n2. Go to Game Menu\n3. Choose Language\n0. Exit":
                    return "Välj ett alternativ: \n1. Logga in\n2. Gå till spelmenyn\n3. Välj språk\n0. Avsluta";
                case "Exiting program.":
                    return "Avslutar programmet.";
                case "Invalid option. Please try again.":
                    return "Ogiltigt alternativ. Försök igen.";
                case "Please enter your username:":
                    return "Vänligen ange ditt användarnamn:";
                case "Please enter your password:":
                    return "Vänligen ange ditt lösenord:";
                case "Login successful!":
                    return "Inloggning lyckades!";
                case "Login failed. Please try again.":
                    return "Inloggning misslyckades. Försök igen.";
                case "Welcome to the game menu!":
                    return "Välkommen till spelmenyn!";
                default:
                    return "Oversatt meddelande saknas";
            }
        }
    }

}