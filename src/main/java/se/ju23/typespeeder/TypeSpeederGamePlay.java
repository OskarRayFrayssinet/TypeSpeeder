package se.ju23.typespeeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component public class TypeSpeederGamePlay implements Playable{
    public int currentId = 0;

    @Autowired UsersRepo uRepo;

    public TypeSpeederGamePlay() {
    }

    @Override
    public Status checkUser(String input) {
        return null;
    }

    @Override
    public String printIntroOutroText() {
        return "helloooo";
    }

    @Override
    public Status playGame(String input) throws SQLException {
        return null;
    }

    @Override
    public Status playAgain(boolean b) {
        return null;
    }

    @Override
    public int currentId() {
        return 0;
    }

    @Override
    public String noUserFoundText() {
        return null;
    }
}
