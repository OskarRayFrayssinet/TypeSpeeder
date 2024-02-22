package se.ju23.typespeeder;

public interface MenuService {
    String displayMenu();
    void logInMenu();
    Player verifyPlayer();
    boolean verifyPassword();
    void startMenu();
    void challengeCountDown();
    Player getLoggedInPlayer();
    void setLoggedInPlayer(Player player);
    void setSystemIO(SystemIO systemIO);
    void setPlayerRepo(PlayerRepo playerRepo);
    void printTypeSpeederAnsi();
    int chooseDifficulty();
    String postGameResults(double[] results);
    void setDaoManager(DAOManager daoManager);
    void settingsMenu();
    void patchNotesAndNewsMenu();
    void continueOrExit();
    String getExitMessage();
}
