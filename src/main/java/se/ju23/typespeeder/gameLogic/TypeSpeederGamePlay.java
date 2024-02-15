package se.ju23.typespeeder.gameLogic;


import org.apache.commons.lang3.time.StopWatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.classesFromDB.Tasks;
import se.ju23.typespeeder.classesFromDB.TasksRepo;
import se.ju23.typespeeder.classesFromDB.Users;
import se.ju23.typespeeder.classesFromDB.UsersRepo;
import se.ju23.typespeeder.userInterfaces.MenuService;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Component
public class TypeSpeederGamePlay implements Playable {
    MenuService menuService;
    Translatable translator;
    public static LocalTime startGame = LocalTime.now();
    public static LocalTime endGame = LocalTime.now();




    public int currentId = 0;
    public String[] currentLanguage = {"2", "", ""};

    public int currentGameTaskId = 0;
    public String currentGameText = "";
    public String[] currentEmail = {"", "", ""};
    public String[] currentAlias = {"", ""};
    public int currentXp = 0;
    public int currentLevel = 0;
    public String[] currentPassword = {"", "", ""};
    public static List<String> currentSolution = new ArrayList<>();


    public TypeSpeederGamePlay(Translatable translator) {
        this.translator = translator;

    }

    @Autowired
    UsersRepo uRepo;
    @Autowired
    TasksRepo tRepo;

    public Status checkUser(String email, String password) {


        Optional<Users> users = uRepo.findByEmailAndPassword(email, password);
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


    public String printListOfGames() {
        List<Tasks> tasksList = tRepo.findAll();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tasksList.size(); i++) {
            stringBuilder.append((i + 1)).append(". ").append(tasksList.get(i).getTaskName()).append("\n");
        }
        return "0. Go back\n" + stringBuilder.toString();
    }

    public String activeInGame(int id) {
        String translatedText;
        StringBuilder stringBuilder = new StringBuilder();
        currentGameTaskId = id;
        List<Tasks> tasksList = tRepo.findByTaskId(id);
        for (Tasks tasks : tasksList) {
            currentGameText = tasks.getActualTask();
        }
        if (getCurrentLanguage(0).equals("1")) {
            try {
                translatedText = translator.translate(currentGameText, "sv");
                currentGameText = translatedText;
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }

        List<String> textInWords = Arrays.asList(currentGameText.split("\\s+"));
        List<String> textWithYellowWords = addYellowHighlight(textInWords, generateRandomWords(currentGameText));

        for (String t : textWithYellowWords) {
            stringBuilder.append(t).append(" ");
        }

        //watch.start();
        startGame = LocalTime.now();
        return stringBuilder.toString();
    }


    @Override
    public void calculatePoints(String userAnswer){
        endGame = LocalTime.now();
        double timeResult = calculateTimeToDouble();
        List<String> temp = new ArrayList<>(Arrays.asList(userAnswer.split("\\s+")));
        if (temp.equals(currentSolution)) {
            System.out.println("User's answer matches the current solution." + " Time in seconds: " + timeResult);

        } else {
            System.out.println("User's answer does not match the current solution."+ " Time in seconds: " + timeResult);

        }
    }
    public double calculateTimeToDouble(){
        Duration difference = Duration.between(startGame, endGame);
        long millisecondsDifference = difference.toMillis();
        return millisecondsDifference / 1000.0;
    }

    private static List<String> addYellowHighlight(List<String> textInWords, List<String> randomWords) {
        List<String> temp = new ArrayList<>();
        for (String a : randomWords) {
            boolean markedWords = false;

            for (int j = 0; j < textInWords.size(); j++) {
                if (textInWords.get(j).equals(a) && !temp.contains(a)) {
                    String highlightedWord = "\u001B[33m" + a + "\u001B[0m";
                    textInWords.set(j, highlightedWord);
                    temp.add(a);
                    markedWords = true;
                    break;
                }
            }
            if (!markedWords) {
                temp.add(a);
            }
        }
        List<String> yellowWords = new ArrayList<>();
        for (String word : textInWords) {
            // Kolla om ordet har markerats gult
            if (word.startsWith("\u001B[33m") && word.endsWith("\u001B[0m")) {
                // Ta bort färgkoderna och lägg till det i listan av gula ord
                yellowWords.add(word.substring(5, word.length() - 4));
                if (yellowWords.size() == 7){
                    break;
                }
            }
        }
        currentSolution = yellowWords;
        //TODO TA BORT DET HÄR SEN
        System.out.println("gula ord" + yellowWords);
        return textInWords;
    }
    @Override
    public String beforeGameStartsText(){
        return "Time starts when you press ENTER, READY?";
    }
    private static List<String> generateRandomWords(String text) {
        String[] words = text.split("\\s+");
        Random random = new Random();
        List<String> randomWordsList = new ArrayList<>();

        int[] indices = new int[7];
        Set<Integer> chosenIndices = new HashSet<>();
        for (int i = 0; i < 7; i++) {
            int index = random.nextInt(words.length);
            while (chosenIndices.contains(index)) {
                index = random.nextInt(words.length);
            }
            chosenIndices.add(index);
            indices[i] = index;
        }
        for (int i = 0; i < indices.length - 1; i++) {
            for (int j = 0; j < indices.length - i - 1; j++) {
                if (indices[j] > indices[j + 1]) {
                    int temp = indices[j];
                    indices[j] = indices[j + 1];
                    indices[j + 1] = temp;
                }
            }
        }
        for (int index : indices) {
            randomWordsList.add(words[index]);
        }


       /* String[] words = text.split("\\s+");
        Random random = new Random();
        List<String> randomWordsList = new ArrayList<>();


        for (int i = 0; i < 7; i++) {
            int randomIndex = random.nextInt(words.length);
            randomWordsList.add(words[randomIndex]);
            currentSolution.add(words[randomIndex]);

        }
        //Collections.sort(randomWordsList);


*/

        return randomWordsList;
    }

    @Override
    public Status standbyInMainMenu(int input) {
        currentPassword[2] = "";
        currentEmail[2] = "";
        currentLanguage[2] = "";
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
    public void setLanguage() {
        if (currentLanguage[0] == "1") {
            currentLanguage[0] = "2";
            currentLanguage[2] = "3";
        } else {
            currentLanguage[0] = "1";
            currentLanguage[2] = "3";
        }
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
        List<Users> aliasFromDB = uRepo.findByAlias(getCurrentAlias(0));
        for (Users u : aliasFromDB) {
            u.setAlias(input);
            uRepo.save(u);
            currentAlias[0] = input;
            currentAlias[1] = "1";
        }
    }
    @Override
    public void setNewUsername(String newUsername) {
        Optional<Users> emailFromDB = uRepo.findByUserId(getCurrentId());
        if (emailFromDB.isPresent()) {
            Users found = emailFromDB.get();
            found.setEmail(newUsername);
            uRepo.save(found);
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
        Optional<Users> checkIfBusy = uRepo.findByEmail(input);
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
        Optional<Users> passwordFromDB = uRepo.findById(getCurrentId());
        if (passwordFromDB.isPresent()) {
            Users found = passwordFromDB.get();
            found.setPassword(newPassword);
            uRepo.save(found);
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
    @Override
    public String getCurrentLanguage(int place) {
        return currentLanguage[place];
    }
    public Status playingGame(int input) {
        return null;
    }
    @Override
    public Status playAgain(boolean b) {
        return null;
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
