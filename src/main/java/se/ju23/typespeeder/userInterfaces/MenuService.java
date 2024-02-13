package se.ju23.typespeeder.userInterfaces;

import java.util.List;

public interface MenuService {
    String printChangeLanguageText();

    String displayMenu();

    String printLoginText();

    String getUserSettingsMenu();

    String getUserNameChangeText();

    String getPasswordChangeText();

    String getEmailChangeText();

    List<String> getMenuOptions();

    int getNumberOfTries();

}
