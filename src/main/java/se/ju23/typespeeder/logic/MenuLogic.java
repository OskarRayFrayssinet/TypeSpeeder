package se.ju23.typespeeder.logic;

import org.springframework.beans.factory.annotation.Autowired;
import se.ju23.typespeeder.service.LoginService;

import java.util.Scanner;

public class MenuLogic {

    @Autowired
    private LoginService loginService;

    public void runApplication() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Typespeeder");

        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.println("Please enter you username");
            String username = scanner.nextLine();

            System.out.println("Please enter your password");
            String password = scanner.nextLine();

            loggedIn = loginService.login(username, password);

            if(loggedIn) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Login failed. Please try again.");
                /*Make sure MenuLogic is properly instantiated and called from your main application entry point. If you're using Spring Boot's dependency injection, ensure that MenuLogic and LoginService are correctly annotated (with @Service, @Component, etc.) and that Spring is managing their lifecycle.
                        Note

                Remember, if your application is not already set up to support dependency injection for classes like MenuLogic, you may need to adjust your approach to ensure that Spring's @Autowired components are correctly initialized. This might involve using Spring's application context to get beans or rethinking how your application's entry point is structured.*/
            }
        }
    }
    /*private final UserInterface userInterface;
    private final Scanner scanner;

    public MenuLogic(UserInterface userInterface) {
        this.userInterface = userInterface;
        this.scanner = new Scanner(System.in);

    }
    public void handleMenu() {
        boolean running = true;
        while(running) {
            userInterface.displayMenu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> loginUser();
                case 2 -> chooseLanguage();
                case 3 -> playGame();
                case 4 -> viewRanking();
                case 0 -> System.out.println("\n Avsluta programmet");
            }
        }
    }

    private void loginUser() {
        System.out.println("Ange användar-ID: ");
        String userID = scanner.nextLine();
        System.out.println("Ange lösenord: ");
        String password = scanner.nextLine();

        if(userCredentials.containsKey(userID) && userCredentials.get(userID).equals(password)) {
            System.out.println("Inloggning lyckades.");
        } else {
            System.out.println("Felaktigt användar-ID eller lösenord. Försök igen.");
        }
    }

    private void chooseLanguage() {
        System.out.println("""
                Välj språk:
                1. Engelska
                2. Svenska
                """);
        int languageChoice = scanner.nextInt();
        switch (languageChoice) {
            case 1 -> System.out.println("Språk valt: Engelska");
            case 2 -> System.out.println("Språk valt: Svenska");
            default -> System.out.println("Ogiltigt val, välj igen.");
        }
    }

    private void playGame() {
        System.out.println("Spela");
    }

    private void viewRanking() {
        System.out.println("Se ranking");
    }*/

}
