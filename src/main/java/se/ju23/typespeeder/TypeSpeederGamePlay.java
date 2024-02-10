package se.ju23.typespeeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
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
    @Autowired
    TasksRepo tRepo;
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
                "\n0.Sign out and exit" +
                "1. Game Languange\n" +
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
            return "User Not found, Tries left: " + tries + "\n";
        } else {
            currentEmail = "2";
            return "Skriv användarnamn: ";
        }
    }
    public String printGames(){
        List<Tasks> tasksList = tRepo.findAll();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tasksList.size(); i++) {
            stringBuilder.append((i + 1)).append(". ").append(tasksList.get(i).getTaskName()).append("\n");
        }
        return stringBuilder.toString();
    }
    public String getGameById(int id){
        List<Tasks> tasksList = tRepo.findByTaskId(id);
        StringBuilder stringBuilder = new StringBuilder();
        for (Tasks tasks : tasksList){
            stringBuilder.append(tasks.getActualTask());
        }
        return stringBuilder.toString();
    }

    @Override
    public Status standbyInGame(int input) {
        Status status = null;
        switch (input){
            case 0 -> status = Status.EXIT;
            //case 1 -> status = setLanguage();
            case 2 -> status = Status.ACTIVE_IN_GAME;
        }
        return status;
    }
    public Status playingGame(int input){
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
