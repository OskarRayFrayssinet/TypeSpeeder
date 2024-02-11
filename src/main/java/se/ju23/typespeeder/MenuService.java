package se.ju23.typespeeder;

import java.util.List;

public interface MenuService {
    String displayMenu(List<String> menuToDisplay);

    String printLoginText();

    List<String> getUserSettingsMenu();

    List<String> getUserNameChangeText();

    List<String> getMenuOptions();

    int getNumberOfTries();

}
