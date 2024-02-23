package se.ju23.typespeeder.menu;

import se.ju23.typespeeder.challenge.Challenge;
import se.ju23.typespeeder.classer.PlayersService;
import se.ju23.typespeeder.database.Players;
import se.ju23.typespeeder.database.PlayersRepo;
import se.ju23.typespeeder.database.ResultatRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Menu implements MenuService {

    public Challenge challenge = new Challenge();
    private String language = "svenska";
    private Players currentPlayer;

    public PlayersService playersService = new PlayersService();

    @Override
    public List<String> getMenuOptions() {
        List<String> options = new ArrayList<>();

        options.add("1. Spela spel");
        options.add("2. Redigera spelare");
        options.add("3. Lägg till spelare");
        options.add("4. Få fram resultat");
        options.add("5. Radera spelare");
        options.add("6. Byte till engelska");
        return options;
    }

    public List<String> getMenuOptionsEnglish() {
        List<String> options = new ArrayList<>();

        options.add("1. Play game");
        options.add("2. Update player");
        options.add("3. Add player");
        options.add("4. Get your results");
        options.add("5. Delete player");
        options.add("6. Switch to svenska");
        return options;
    }

    @Override
    public void displayMenu() {
        languageChoosing();
        List<String> menuOptions;
        if (language.equalsIgnoreCase("svenska")) {
            menuOptions = getMenuOptions();
            System.out.println("Meny Alternativ - " + language + ": ");
        } else {
            menuOptions = getMenuOptionsEnglish();
            System.out.println("Menu Alternativ - " + language + ": ");
        }

        displayMenuOptions(menuOptions);

    }

    private void displayMenuOptions(List<String> menuOptions) {
        for (String option : menuOptions) {
            System.out.println(option);
        }


    }

    public void languageChoosing() {
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Välj språk (svenska/engelska):");
            String selectedLanguage = "svenska";

            while (selectedLanguage.isBlank()) {
                if (input.hasNextLine()) {
                    selectedLanguage = input.nextLine().toLowerCase();
                } else {
                    System.out.println("No input detected. Please enter the language choice.");
                }
            }

            if (selectedLanguage.equals("svenska") || selectedLanguage.equals("s")) {
                System.out.println("Svenska valt.");
                language = "svenska";
            } else if (selectedLanguage.equals("engelska") || selectedLanguage.equals("e")) {
                language = "engelska";
            } else {
                System.out.println("Invalid language selection. Default language set to English.");
                language = "English";
            }
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(e);
        }

    }

    public void handleMenuOption(PlayersRepo playersRepo, PlayersService playersService, ResultatRepo resultatRepo, Players players, Scanner scanner) {

        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option) {
            case 1:
                menuGame(playersRepo, scanner);
                break;
            case 2:
                playersService.updatePlayer(playersRepo, scanner);
                break;
            case 3:
                playersService.addNewPlayer(playersRepo, scanner);
                break;
            case 4:
                playersService.printYourStatistics(playersRepo, resultatRepo, currentPlayer, scanner);
                playersService.printOverallStatistics(playersRepo,resultatRepo,currentPlayer,scanner);
                break;
            case 5:
                playersService.deletePlayer(playersRepo, scanner);
                break;
            case 6:
                handleMenuOptionEnglish(playersRepo, playersService, resultatRepo, scanner);
                break;

            default:
                System.out.println("Invalid option.");
        }
    }

    public void handleMenuOptionEnglish(PlayersRepo playersRepo, PlayersService playersService, ResultatRepo resultatRepo, Scanner scanner) {
        System.out.println("Welcome to the english menu.");
        System.out.println("1. Play game");
        System.out.println("2. Update player");
        System.out.println("3. Add new player");
        System.out.println("4. Get your results");
        System.out.println("5. Delete player");
        System.out.println("6. Switch to Swedish");

        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option) {
            case 1:
                challenge.basicGame(currentPlayer);
                break;
            case 2:
                playersService.updatePlayer(playersRepo, scanner);
                break;
            case 3:
                playersService.addNewPlayer(playersRepo, scanner);
                break;
            case 4:
                System.out.println("CASE 4");
                break;
            case 5:
                playersService.deletePlayer(playersRepo, scanner);
                break;
            case 6:
                language = "svenska";
                displayMenu();
                handleMenuOption(playersRepo, playersService, resultatRepo, currentPlayer,scanner );
                break;

            default:
                System.out.println("Invalid option.");
        }
    }

    public void menuGame(PlayersRepo playersRepo, Scanner scanner) {

        int choice;

        do {
            System.out.println("You are entering game menu");
            System.out.println("""
                     Choose option below: 
                     1 - Play basic game
                     2 - Game with highlighted words
                    """);
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    challenge.basicGame(currentPlayer);
                    break;
                case 2:
                    challenge.colourGame(currentPlayer);
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (choice != 0);
    }

    public void login(PlayersRepo playersRepo, PlayersService playersService, ResultatRepo resultatRepo, Players players, Scanner input) {
        boolean runProgram = true;
        do {
            System.out.println("Enter your username: ");
            String answerUsername = input.nextLine();

            System.out.println("Enter corresponding password: ");
            String answerPassword = input.nextLine();

            Players foundPLayer = playersRepo.getPLayersByUsernameAndPassword(answerUsername, answerPassword);

            if (foundPLayer == null) {
                System.out.println("Player not found.");
                runProgram = false;

            } else {
                System.out.println("Welcome, " + foundPLayer.getNickname());
                System.out.println("Your current role is " + foundPLayer.getRole());
                currentPlayer = foundPLayer;
                displayMenu();
                handleMenuOption(playersRepo, playersService, resultatRepo,players, input);
                runProgram = false;
            }
        } while (runProgram);

    }


}
