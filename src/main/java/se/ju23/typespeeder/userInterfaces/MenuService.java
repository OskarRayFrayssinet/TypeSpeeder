package se.ju23.typespeeder.userInterfaces;

import java.util.List;

public interface MenuService {
    String printChangeLanguageText();

    void displayMenu();

    String printLoginText();

    String getUserSettingsMenu();

    String getUserNameChangeText();

    String getPasswordChangeText();

    String getUsernameChangeText();

    List<String> getMenuOptions();

    void setLanguage();

    String getCurrentLanguage(int place);

    int getNumberOfTries();

}
