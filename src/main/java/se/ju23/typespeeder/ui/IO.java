package se.ju23.typespeeder.ui;

public interface IO {

    // Inloggning
    void loginUser(String username, String password);

    // Uppdatera användaruppgifter
    void updateUsername(String oldUsername, String newUsername);
    void updatePassword(String username, String newPassword);
    void updateDisplayName(String username, String newDisplayName); // Det namn som syns för andra spelare

    // Menyhantering
    void displayMainMenu();
    void displaySettingsMenu();
    void displayLanguageSelectionMenu();

    // Visa rankings och spelarinformation
    void displayRankings();
    void displayPlayerStats(String username);

    // Visa nyhetsinformation och patch-uppdateringar
    void displayNewsUpdates();
    void displayPatchNotes();

}


