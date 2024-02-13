package se.ju23.typespeeder;

import java.util.ArrayList;

public class Menu {
    private ArrayList<String> menuOptions;

    public Menu() {
        menuOptions = new ArrayList<>();
        menuOptions.add("1. Spela");
        menuOptions.add("2. Visa rankningslista");
        menuOptions.add("3. Inställningar");
        menuOptions.add("4. Patch notes och nyheter");
        menuOptions.add("5. Logga ut");
    }

    public ArrayList<String> getMenuOptions() {
        return menuOptions;
    }
    public void displayMenu() {
        for (String menuOption : menuOptions) {
            System.out.println(menuOption);
        }
    }
    public void changeLanguage() {
        if (menuOptions.get(0).equals("1. Spela")) {
            menuOptions = new ArrayList<>();
            menuOptions.add("1. Play");
            menuOptions.add("2. Show Scoreboard");
            menuOptions.add("3. Settings");
            menuOptions.add("4. Patch notes and news");
            menuOptions.add("5. log out");
        } else {
            menuOptions = new ArrayList<>();
            menuOptions.add("1. Spela");
            menuOptions.add("2. Visa rankningslista");
            menuOptions.add("3. Inställningar");
            menuOptions.add("4. Patch notes och nyheter");
            menuOptions.add("5. Logga ut");
        }
    }
}
