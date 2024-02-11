package se.ju23.typespeeder;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component public class Menu implements MenuService{
    Playable playable;
    private int tries = 3;
    private String oldAlias = "";


    public Menu(Playable playable) {
        this.playable = playable;
    }
    @Override
    //Login menu first to be shown
    public String printLoginText() {

        if (playable.getCurrentEmail().equals("2")){
            return "Password: ";
        } else if (playable.getCurrentEmail().equals("1")){
            tries--;
            playable.setCurrentEmail("");
            return "Check your details, Tries left: " + tries + "\n";
        } else {
            playable.setCurrentEmail("2");
            return "--Login menu--\nUsername: ";
        }
    }
    @Override
    //Main menu
    public String displayMenu(List<String> menuToDisplay) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String option : menuToDisplay){
            stringBuilder.append(option);
        }
        return stringBuilder.toString();
    }
    @Override
    public List<String> getUserSettingsMenu(){
        List<String> menuOptions = new ArrayList<>();
        menuOptions.add("\n0. Go back\n");
        menuOptions.add("1. Change Alias\n");
        menuOptions.add("2. Change Password\n");
        menuOptions.add("Your choice: ");
        return menuOptions;
    }
    @Override
    public List<String> getUserNameChangeText(){
        List<String> menuOptions = new ArrayList<>();

        String oldAlias = playable.getCurrentAlias();
        if (!playable.getCurrentAlias().equals(oldAlias)){
            menuOptions.add("Alias is now changed to: " + playable.getCurrentAlias());
        } else {
            menuOptions.add("New Alias: ");
        }
        return menuOptions;

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
