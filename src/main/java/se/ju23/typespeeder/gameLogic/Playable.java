package se.ju23.typespeeder.gameLogic;

public interface Playable {

    Status checkUser(String username, String password);

    Status standbyInMainMenu(int input);

    void setLanguage();

    Status standbyInSettingsMenu(int input);
    Status playAgain(boolean b);
    String getCurrentEmail(int place);
    void setCurrentEmail(String newCurrentEmail);
    String printGames();

    void setNewAlias(String input);


    void setNewEmail(String newEmail);

    boolean checkCurrentEmail(String input);

    boolean checkIfUserNameIsBusy(String input);

    void setNewPassword(String newPassword);

    boolean checkCurrentPassword(String input);

    String getCurrentLanguage(int place);

    Status playingGame(int input);
    String activeInGame(int id);
    int getCurrentId();
    String noUserFoundText();
    String getCurrentAlias(int place);

    String getPassword(int place);

    int getCurrentXp();
    int getCurrentLevel();
}
