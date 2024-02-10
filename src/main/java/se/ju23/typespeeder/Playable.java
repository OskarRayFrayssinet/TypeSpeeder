package se.ju23.typespeeder;

public interface Playable {

    Status checkUser(String username, String password);
    String printMenu();
    String printLoginText();
    Status standbyInGame(int input);
    Status playAgain(boolean b);
    String currentEmail();
    String printGames();
    Status playingGame(int input);
    String getGameById(int id);
    int currentId();
    String noUserFoundText();
}
