package se.ju23.typespeeder.userInterfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.colors.ConsoleColor;
import se.ju23.typespeeder.gameLogic.Playable;
import se.ju23.typespeeder.gameLogic.Translatable;

import java.util.ArrayList;
import java.util.List;

@Component public class Menu implements MenuService {
    Playable playable;
    Translatable translatable;
    public String[] currentLanguage = {"2", "", ""};
    private String language = "";
    private int tries = 3;
    @Autowired
    public void setPlayable(Playable playable) {
        this.playable = playable;
    }
    @Autowired
    public void setTranslatable(Translatable translatable) {
        this.translatable = translatable;
    }


    public Menu() {
    }
    @Override
    //Login menu first to be shown
    public String printLoginText() {
        String toReturn = null;

        if (playable.getCurrentUsername(0).equals("2")){
            toReturn = "Password: ";
        } else if (playable.getCurrentUsername(0).equals("1")){
            tries--;
            playable.setCurrentUsername("");
            toReturn = "\u001B[1mCheck your details or contact admin if troubled" +
                    "\nTries left before shutdown: " + tries + "\n\u001B[0m";
        } else {
            playable.setCurrentUsername("2");
            toReturn = "--Login menu--\nUsername: ";
        }

        return toReturn;
    }


    @Override
    public String printChangeLanguageText(){
        if (getCurrentLanguage(2).equals("3")){
            return "Changed\n";
        } else if (getCurrentLanguage(0).equals("1")) {
            return "Byta spelspråk till Engelska? y/n: ";
        } else {
            return "Change game language to Swedish? y/n: ";
        }
    }
    @Override
    //Main menu
    //Only in English
    public String displayMenu() {
        String textToReturn = null;
        String translatedText;
        StringBuilder stringBuilder = new StringBuilder();
        for (String option : getMenuOptions()){
            stringBuilder.append(option);
        }
        return stringBuilder.toString();
    }
    @Override
    public String getUserSettingsMenu(){

        List<String> menuOptions = new ArrayList<>();

        menuOptions.add("\n0. Go back\n");
        menuOptions.add("1. Change Alias\n");
        menuOptions.add("2. Change Password\n");
        menuOptions.add("3. Change Username\n");
        menuOptions.add("Your choice: ");
        StringBuilder stringBuilder = new StringBuilder();
        for (String option : menuOptions){
            stringBuilder.append(option);
        }
        return stringBuilder.toString();
    }
    @Override
    public String getUserNameChangeText(){
        List<String> menuOptions = new ArrayList<>();

        if (playable.getCurrentAlias(1).equals("1")){
            menuOptions.add("\u001B[1mAlias changed to: " + playable.getCurrentAlias(0) + "\nYOU HAVE TO SIGN OUT BEFORE CHANGING AGAIN\u001B[0m\n");
        } else {
            menuOptions.add("New Alias(Go back 'b'): ");

        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String option : menuOptions){
            stringBuilder.append(option);
        }
        return stringBuilder.toString();

    }
    @Override
    public String getPasswordChangeText(){
        List<String> menuOptions = new ArrayList<>();
        if (playable.getPassword(1).equals("1")){
            menuOptions.add(ConsoleColor.BG_BRIGHT_CYAN + "\u001B[1mPASSWORD CHANGED\n" +
                    "YOU HAVE TO SIGN OUT BEFORE CHANGING AGAIN\u001B[0m\n" + ConsoleColor.RESET);
        } else {
            if (playable.getPassword(1).equals(playable.getPassword(0))){
                menuOptions.add("New password (Go back 'b'): ");
            } else if (playable.getPassword(2).equals("2")){
                menuOptions.add("Wrong password");
            } else {
                menuOptions.add("Current password (Go back 'b'): ");
            }

        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String option : menuOptions){
            stringBuilder.append(option);
        }
        return stringBuilder.toString();
    }
    @Override
    public String getUsernameChangeText(){
        List<String> menuOptions = new ArrayList<>();
        if (playable.getCurrentUsername(1).equals("1")){
            menuOptions.add(ConsoleColor.CYAN + "\u001B[1mUSERNAME CHANGED\n" +
                    "YOU HAVE TO SIGN OUT BEFORE CHANGING AGAIN\u001B[0m\n" + ConsoleColor.RESET);
        } else {
            if (playable.getCurrentUsername(1).equals(playable.getCurrentUsername(0))/* && playable.getCurrentEmail(2).isEmpty()*/){
                menuOptions.add("New Username (Go back 'b'): ");
            } else if (playable.getCurrentUsername(2).equals("2")){
                menuOptions.add("\u001B[1mWrong Username Or already taken\u001B[0m\n");
            } else {
                menuOptions.add("Type your current Username (Go back 'b'): ");
            }

        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String option : menuOptions){
            stringBuilder.append(option);
        }
        return stringBuilder.toString();
    }


    @Override
    public List<String> getMenuOptions() {
        currentLanguage[2] = "";
        List<String> menuOptions = new ArrayList<>();
        if (currentLanguage[0].equals("1")){
            menuOptions.add("Svenska valt");
        } else {
            menuOptions.add("English chosen");
        }
        menuOptions.add("\n0.Sign out and exit\n");
        menuOptions.add("1. Choose language (Swedish/English)\n");
        menuOptions.add("2. Select game\n");
        menuOptions.add("3. Show ranking list\n");
        menuOptions.add("4. Change user info\n");
        menuOptions.add("5. Show newsletter\n");
        menuOptions.add("Your choice: ");

        return menuOptions;
    }
    @Override
    public void setLanguage() {
        if (currentLanguage[0] == "1") {
            currentLanguage[0] = "2";
            currentLanguage[2] = "3";
        } else {
            currentLanguage[0] = "1";
            currentLanguage[2] = "3";
        }
    }
    @Override
    public String getCurrentLanguage(int place) {
        return currentLanguage[place];
    }
    public int getNumberOfTries(){
        return tries;
    }
}
