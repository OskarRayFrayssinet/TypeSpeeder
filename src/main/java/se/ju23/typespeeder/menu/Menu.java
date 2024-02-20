package se.ju23.typespeeder.menu;

import se.ju23.typespeeder.challenge.Challenge;
import se.ju23.typespeeder.game.GameEnglish;
import se.ju23.typespeeder.classer.DictionaryService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Menu implements MenuService {

    public Challenge challenge = new Challenge();
    private String language = "svenska";

    public DictionaryService dictionaryService = new DictionaryService();

    @Override
    public List<String> getMenuOptions() {
        List<String> options = new ArrayList<>();

        options.add("1. Spela spel");
        options.add("2. Avsluta spel");
        options.add("3. Lägg till spelare");
        options.add("4. Få fram dina resultat");
        options.add("5. Få fram andras resultat");
        options.add("6. Byte till engelska");



        return options;
    }

    public List<String> getMenuOptionsEnglish() {
        List<String> options = new ArrayList<>();

        options.add("1. Play game");
        options.add("2. Finish game");
        options.add("3. Add player");
        options.add("4. Get your results");
        options.add("5. Get others results");
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

    public void handleMenuOption() {
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option) {
            case 1:
                System.out.println("Spela spel");

                challenge.beginGame();
           //     challenge.playersInput();
                break;
            case 2:
                System.out.println("CASE 2");
                break;
            case 3:
                System.out.println("CASE 3");
                break;
            case 4:
                System.out.println("CASE 4");
                break;
            case 5:
                System.out.println("CASE 5");
                break;
            case 6:
                System.out.println("CASE 6");
                break;
            default:
                System.out.println("Invalid option.");
        }
    }
}
