package se.ju23.typespeeder.gameLogic;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.classesFromDB.*;
import se.ju23.typespeeder.userInterfaces.MenuService;

import java.time.LocalTime;
import java.util.*;

@Component
public class TypeSpeederGamePlay implements Playable {
    MenuService menuService;

    public static LocalTime startGame = LocalTime.now();
    public static LocalTime endGame = LocalTime.now();




    public int currentId = 0;

    double timeResult = 0;
    public String[] currentEmail = {"", "", ""};
    public String[] currentAlias = {"", ""};
    public int currentXp = 0;
    public int currentLevel = 0;
    public String[] currentPassword = {"", "", ""};
    public static List<String> currentSolution = new ArrayList<>();
    IChallenge challenge;




    public TypeSpeederGamePlay() {

    }

    @Autowired
    UsersRepo usersRepo;
    @Autowired
    AttemptRepo attemptRepo;
    @Autowired
    PointParamRepo pointParamRepo;

    public Status checkUser(String email, String password) {


        Optional<Users> users = usersRepo.findByEmailAndPassword(email, password);
        if (users.isPresent()) {
            Users found = users.get();
            currentId = found.getUserId();
            currentEmail[0] = found.getEmail();
            currentAlias[0] = found.getAlias();
            currentXp = found.getXp();
            currentLevel = found.getLevel();
            currentPassword[0] = found.getPassword();
            return Status.VERIFIED;
        } else {
            currentEmail[0] = "1";

            return Status.NO_USER_FOUND;
        }
    }

    @Override
    public void calculateTotalPointsForGame(String userAnswer){
        int correctAnswers = 0;
        int correctAnswersInRow = 0;
        List<String> userList = new ArrayList<>(Arrays.asList(userAnswer.split("\\s+")));
        for (int i = 0; i < userList.size(); i++) {
            for (int j = 0; j < currentSolution.size(); j++) {
                if (userList.get(i).equals(currentSolution.get(j))){
                    correctAnswers++;
                }
            }
        }
        int find = 0;
        for (int i = 0; i < userList.size(); i++) {

            if (userList.get(i).equals(currentSolution.get(find))){
                correctAnswersInRow++;
            }
            find++;
        }
        System.out.println(currentSolution);
        System.out.println(userList);
        System.out.println("Antal rätt: " + correctAnswers + " rätt i rad:" + correctAnswersInRow + " Din tid: " + getTimeResult());
        /*
        if (temp.equals(currentSolution)) {
            System.out.println("User's answer matches the current solution." + " Time in seconds: " + timeResult);

        } else {
            System.out.println("User's answer does not match the current solution."+ " Time in seconds: " + timeResult);

        }

         */
    }

    public static List<String> getCurrentSolution() {
        return currentSolution;
    }
    @Override
    public void setCurrentSolution(List<String> currentSolution1) {
        currentSolution = currentSolution1;
    }

    @Override
    public String beforeGameStartsText(){
        return "Time starts when you press ENTER, READY?";
    }


    @Override
    public Status standbyInMainMenu(int input) {
        currentPassword[2] = "";
        currentEmail[2] = "";

        Status status = null;
        switch (input) {
            case 0 -> status = Status.EXIT;
            case 1 -> status = Status.SETTING_LANGUAGE;
            case 2 -> status = Status.ACTIVE_IN_GAME;
            //case 3
            case 4 -> status = Status.IN_GAME_SETTINGS;
        }
        return status;
    }


    @Override
    public Status standbyInSettingsMenu(int input) {
        Status status = null;
        switch (input) {
            case 1 -> status = Status.CHANGING_ALIAS;
            case 2 -> status = Status.CHANGING_PASSWORD;
            case 3 -> status = Status.CHANGING_USERNAME;
        }
        return status;
    }
    @Override
    public void setNewAlias(String input) {

        List<Users> aliasFromDB = usersRepo.findByAlias(getCurrentAlias(0));
        for (Users u : aliasFromDB) {
            u.setAlias(input);
            usersRepo.save(u);
            currentAlias[0] = input;
            currentAlias[1] = "1";
        }
    }
    @Override
    public void setNewUsername(String newUsername) {
        Optional<Users> emailFromDB = usersRepo.findByUserId(getCurrentId());
        if (emailFromDB.isPresent()) {
            Users found = emailFromDB.get();
            found.setEmail(newUsername);
            usersRepo.save(found);
            currentEmail[1] = "1";
        }
    }
    @Override
    public boolean checkCurrentEmail(String input) {
        if (Objects.equals(input, getCurrentEmail(0))) {
            currentEmail[1] = input;
            return true;
        } else {
            currentEmail[2] = "2";
            return false;
        }

    }
    @Override
    public boolean checkIfUserNameIsBusy(String input) {
        Optional<Users> checkIfBusy = usersRepo.findByEmail(input);
        if (checkIfBusy.isEmpty()) {
            return true;
        } else {
            currentEmail[1] = "";
            currentEmail[2] = "2";
            return false;
        }
    }
    @Override
    public void setNewPassword(String newPassword) {
        Optional<Users> passwordFromDB = usersRepo.findById(getCurrentId());
        if (passwordFromDB.isPresent()) {
            Users found = passwordFromDB.get();
            found.setPassword(newPassword);
            usersRepo.save(found);
            currentPassword[1] = "1";
        }
    }
    @Override
    public boolean checkCurrentPassword(String input) {
        if (Objects.equals(input, getPassword(0))) {
            currentPassword[1] = input;
            return true;
        } else {
            currentPassword[2] = "2";
            return false;
        }
    }

    public double getTimeResult() {
        return timeResult;
    }
    @Override
    public void setTimeResult(double timeResult) {
        this.timeResult = timeResult;
    }

    public static LocalTime getStartGame() {
        return startGame;
    }
    @Override
    public void setStartGame(LocalTime startGame1) {
       startGame = startGame1;
    }

    public static LocalTime getEndGame() {
        return endGame;
    }
    @Override
    public void setEndGame(LocalTime endGame1) {
        endGame = endGame1;
    }


    @Override
    public String getCurrentEmail(int place) {
        return currentEmail[place];
    }
    @Override
    public void setCurrentEmail(String input) {
        currentEmail[0] = input;
    }
    @Override
    public int getCurrentId() {
        return currentId;
    }
    @Override
    public String noUserFoundText() {
        return null;
    }
    @Override
    public String getCurrentAlias(int place) {
        return currentAlias[place];
    }
    @Override
    public String getPassword(int place) {
        return currentPassword[place];
    }
    @Override
    public int getCurrentXp() {
        return currentXp;
    }
    @Override
    public int getCurrentLevel() {
        return currentLevel;
    }
}
