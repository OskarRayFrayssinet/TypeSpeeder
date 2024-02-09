package se.ju23.typespeeder;

import java.sql.SQLException;

public interface Playable {

    Status checkUser(String username, String password);
    String printMenu();
    String printLoginText();
    Status playGame(int input);
    Status playAgain(boolean b);
    String currentEmail();
    int currentId();
    String noUserFoundText();
}
