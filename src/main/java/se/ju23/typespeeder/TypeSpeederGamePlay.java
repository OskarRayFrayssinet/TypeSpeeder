package se.ju23.typespeeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class TypeSpeederGamePlay implements Playable{

    public int currentId = 0;
    public String currentEmail = "";

    public TypeSpeederGamePlay() {
    }
    @Autowired
    UsersRepo uRepo;
    public Status checkUser(String email, String password) {


        currentEmail = email;
        Optional<Users> users = uRepo.findByEmailAndPassword(email,password);
        if (users.isPresent()){
            Users found = users.get();
            System.out.println(found);
            return Status.VERIFIED;
        } else {
            currentEmail = "err=nofound";
            return Status.NO_USER_FOUND;
        }
    }

    @Override
    public String printIntroOutroText() {
        return null;
    }


    @Override
    public String printLoginText() {
        if (currentEmail.isEmpty()){
            return "Skriv användarnamn";
        } else if (currentEmail.equals("err=nofound")){
            return "User Not found";
        } else {
            return "Skriv Lösenord: ";
        }
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
    public String currentEmail() {
        return currentEmail;
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
