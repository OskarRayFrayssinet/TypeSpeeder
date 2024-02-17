package se.ju23.typespeeder.menu;

import se.ju23.typespeeder.enums.Language;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Menu implements MenuService {

    Scanner scanner = new Scanner(System.in);
    private String language = "svenska";

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
        options.add("6. Switch to swedish");
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

}
