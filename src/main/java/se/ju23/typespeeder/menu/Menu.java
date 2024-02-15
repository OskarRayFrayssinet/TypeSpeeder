package se.ju23.typespeeder.menu;

import se.ju23.typespeeder.enums.Language;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu implements MenuService {

    Scanner scanner = new Scanner(System.in);

    @Override
    public List<String> getMenuOptions() {
        return getMenuOptions(Language.ENGLISH);
    }

    public List<String> getMenuOptions(Language language) {
        List<String> options = new ArrayList<>();
        if (language == Language.ENGLISH) {
            options.add("1. Play game");
            options.add("2. Quit game");
            options.add("3. Add player");
            options.add("4. Get your results");
            options.add("5. Get others results");
        } else if (language == Language.SWEDISH) {
            options.add("1. Spela spelet");
            options.add("2. Avsluta spelet");
            options.add("3. Lägg till spelare");
            options.add("4. Visa dina resultat");
            options.add("5. Visa de andras resultat");
        }
        return options;
    }

    @Override
    public void displayMenu() {
        int menuOption = 1;
        ArrayList<String> options = new ArrayList<>(getMenuOptions());

        System.out.println("Menu options:");
        for (String o : options) {
            System.out.println(o);
        }
    }


}
