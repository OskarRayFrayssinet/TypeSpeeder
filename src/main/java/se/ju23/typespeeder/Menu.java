package se.ju23.typespeeder;

import java.util.ArrayList;
import java.util.List;

public class Menu implements MenuService{
    @Override
    public String displayMenu() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String option : getMenuOptions()){
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
        menuOptions.add("4. another choice\n");
        menuOptions.add("Your choice: ");

        return menuOptions;
    }
}
