package se.ju23.typespeeder.ui;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class Menu implements MenuService {

    @Override
    public void loginUser(String username, String password) {

    }

    @Override
    public void updateUsername(String oldUsername, String newUsername) {

    }

    @Override
    public void updatePassword(String username, String newPassword) {

    }

    @Override
    public void updateDisplayName(String username, String newDisplayName) {

    }

    @Override
    public void displayMenu() {
        System.out.println("Huvudmeny:");
        for (String option : getMenuOptions()) {
            System.out.println(option);
        }
    }

    @Override
    public List<String> getMenuOptions() {
        return Arrays.asList(
                "1. Logga in",
                "2. Konto Hantering",
                "3. Språkval (Svenska/Engelska)",
                "4. Spela",
                "5. Nyheter och Patch-information"
        );
    }

    @Override
    public void displaySettingsMenu() {

    }

    @Override
    public void displayLanguageSelectionMenu() {

    }

    @Override
    public void displayRankings() {

    }

    @Override
    public void displayPlayerStats(String username) {

    }

    @Override
    public void displayNewsUpdates() {

    }

    @Override
    public void displayPatchNotes() {

    }
}
