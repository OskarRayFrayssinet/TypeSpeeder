package se.ju23.typespeeder;

public interface Playable {

    Status checkUser(String username, String password);

    Status standbyInMainMenu(int input);
    Status standbyInSettingsMenu(int input);
    Status playAgain(boolean b);
    String getCurrentEmail();
    void setCurrentEmail(String newCurrentEmail);
    String printGames();

    void setNewAlias(String input);

    Status playingGame(int input);
    String activeInGame(int id);
    int currentId();
    String noUserFoundText();
    String getCurrentAlias();
    int getCurrentXp();
    int getCurrentLevel();
}
