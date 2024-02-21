package se.ju23.typespeeder;

public interface MenuService {
    void displayMenu();
    void logInMenu();
    Player verifyPlayer();
    boolean verifyPassword();
    void startMenu();
    void challengeCountDown();
    Player getLoggedInPlayer();
    void setLoggedInPlayer(Player player);
    void setSystemIO(SystemIO systemIO);
    void setPlayerRepo(PlayerRepo playerRepo);
    void changeLanguage();
    void printTypeSpeederAnsi();
    int chooseDifficulty();
    String postGameResults(double[] results);
    void setDaoManager(DAOManager daoManager);
}
