package se.ju23.typespeeder;

import java.util.ArrayList;

public class Menu {
    private ArrayList<String> menuOptions;

    public ArrayList<String> getMenuOptions(){
        menuOptions = new ArrayList<>();
        menuOptions.add("1. Spela");
        menuOptions.add("2. Visa rankningslista");
        menuOptions.add("3. Inställningar");
        menuOptions.add("4. Patch notes och nyheter");
        menuOptions.add("5. Logga ut");
        return menuOptions;
    }
    public void displayMenu(){
        for (String menuOption : menuOptions) {
            System.out.println(menuOption);
        }
    }
    public void setLanguage(){

    }
}
