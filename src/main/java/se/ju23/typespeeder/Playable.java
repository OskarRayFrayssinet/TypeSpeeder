package se.ju23.typespeeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

public interface Playable {

    Status checkUser(String username, String password);
    String printIntroOutroText();
    String printLoginText();
    Status playGame(String input) throws SQLException;
    Status playAgain(boolean b);
    String currentEmail();
    int currentId();
    String noUserFoundText();
}
