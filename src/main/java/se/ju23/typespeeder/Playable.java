package se.ju23.typespeeder;

import java.sql.SQLException;

public interface Playable {
    Status checkUser(String input);
    String printIntroOutroText();
    Status playGame(String input) throws SQLException;
    Status playAgain(boolean b);
    int currentId();
    String noUserFoundText();
}
