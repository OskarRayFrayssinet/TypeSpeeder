package se.ju23.typespeeder.userInterfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.gameLogic.Playable;
import se.ju23.typespeeder.gameLogic.Translatable;
import se.ju23.typespeeder.gameLogic.Translator;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component public class Menu implements MenuService {
    Playable playable;
    Translatable translatable;

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

        if (playable.getCurrentEmail(0).equals("2")){
            toReturn = "Password: ";
        } else if (playable.getCurrentEmail(0).equals("1")){
            tries--;
            playable.setCurrentEmail("");
            toReturn = "\u001B[1mCheck your details or contact admin if troubled" +
                    "\nTries left before shutdown: " + tries + "\n\u001B[0m";
        } else {
            playable.setCurrentEmail("2");
            toReturn = "--Login menu--\nUsername: ";
        }

        return toReturn;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String printChangeLanguageText(){
        if (playable.getCurrentLanguage(2).equals("3")){
            return "Changed";
        } else if (playable.getCurrentLanguage(0).equals("1")) {
            return "Byta spelspråk till Engelska? y/n: ";
        } else {
            return "Change game language to Swedish? y/n: ";
        }
    }
    @Override
    //Main menu
    //Only in English
    public void displayMenu() {
        String textToReturn = null;
        String translatedText;
        StringBuilder stringBuilder = new StringBuilder();
        for (String option : getMenuOptions()){
            stringBuilder.append(option);
        }

        System.out.println(stringBuilder);

/*
        if (language.equals("1")){
            try {
                translatedText = translatable.translate(stringBuilder.toString(), "sv");
                textToReturn = translatedText.replaceAll("(\\d+)\\.\\s*", "\n$1. ");

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            textToReturn = stringBuilder.toString();
        }
       */




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
            menuOptions.add("New Alias: ");

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
            menuOptions.add("\u001B[1mPASSWORD CHANGED\n" +
                    "YOU HAVE TO SIGN OUT BEFORE CHANGING AGAIN\u001B[0m\n");
        } else {
            if (playable.getPassword(1).equals(playable.getPassword(0))){
                menuOptions.add("New password: ");
            } else if (playable.getPassword(2).equals("2")){
                menuOptions.add("Wrong password");
            } else {
                menuOptions.add("Current password: ");
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
        if (playable.getCurrentEmail(1).equals("1")){
            menuOptions.add("\u001B[1mUSERNAME CHANGED\n" +
                    "YOU HAVE TO SIGN OUT BEFORE CHANGING AGAIN\u001B[0m\n");
        } else {
            if (playable.getCurrentEmail(1).equals(playable.getCurrentEmail(0))/* && playable.getCurrentEmail(2).isEmpty()*/){
                menuOptions.add("New Username: ");
            } else if (playable.getCurrentEmail(2).equals("2")){
                menuOptions.add("\u001B[1mWrong Username Or already taken\u001B[0m\n");
            } else {
                menuOptions.add("Type your current Username: ");
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
        List<String> menuOptions = new ArrayList<>();
        menuOptions.add("\n0.Sign out and exit\n");
        menuOptions.add("1. Game Languange\n");
        menuOptions.add("2. Select game\n");
        menuOptions.add("3. Show your stats\n");
        menuOptions.add("4. Change user info\n");
        menuOptions.add("Your choice: ");

        return menuOptions;
    }
    public int getNumberOfTries(){
        return tries;
    }
}
