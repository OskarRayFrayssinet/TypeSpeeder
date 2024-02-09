package se.ju23.typespeeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Optional;

@Component
public class TypeSpeederGamePlay implements Playable{

    public int currentId = 0;
    public int tries = 3;
    public String currentEmail = "";
    public String currentAlias = "";
    public int currentXp = 0;
    public int currentLevel = 0;

    public TypeSpeederGamePlay() {
    }
    @Autowired
    UsersRepo uRepo;
    public Status checkUser(String email, String password) {



        Optional<Users> users = uRepo.findByEmailAndPassword(email,password);
        if (users.isPresent()){
            Users found = users.get();
            currentEmail = found.getEmail();
            currentAlias = found.getAlias();
            currentXp = found.getXp();
            currentLevel = found.getLevel();
            return Status.VERIFIED;
        } else if (tries == 1){
            return Status.EXIT;
        }else {
            currentEmail = "1";
            return Status.NO_USER_FOUND;
        }
    }

    @Override
    public String printMenu() {
        return "Alias " + currentAlias +
                " --" +
                " XP " + currentXp +
                "\n1. Game Languange\n" +
                "2. Select game\n" +
                "3. Show your stats" +
                "Your choice: ";
    }


    @Override
    public String printLoginText() {

        if (currentEmail.equals("2")){
            return "Skriv Lösenord";
        } else if (currentEmail.equals("1")){
            tries--;
            currentEmail = "";
            return "User Not found, Tries left: " + tries;
        } else {
            currentEmail = "2";
            return "Skriv användarnamn: ";
        }
    }

    @Override
    public Status playGame(int input) {
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
