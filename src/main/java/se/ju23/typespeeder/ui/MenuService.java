package se.ju23.typespeeder.ui;

import org.springframework.stereotype.Service;

import java.util.List;
public interface MenuService {
    // Inloggning
    void loginUser(String username, String password);

    // Uppdatera användaruppgifter
    void updateUsername(String oldUsername, String newUsername);
    void updatePassword(String username, String newPassword);
    void updateDisplayName(String username, String newDisplayName); // Det namn som syns för andra spelare

    // Menyhantering
    void displayMenu();
    List<String> getMenuOptions();
    void displaySettingsMenu();
    void displayLanguageSelectionMenu();

    // Visa rankings och spelarinformation
    void displayRankings();
    void displayPlayerStats(String username);

    // Visa nyhetsinformation och patch-uppdateringar
    void displayNewsUpdates();
    void displayPatchNotes();

}




