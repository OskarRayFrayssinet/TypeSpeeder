package se.ju23.typespeeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TypeSpeederGamePlay implements Playable{

    public int currentId = 0;
    public int tries = 3;
    public int currentGameId = 0;
    public String currentGameText = "";
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
    public String activeInGame(int id){
        StringBuilder stringBuilder = new StringBuilder();
        currentGameId = id;
        List<Tasks> tasksList = tRepo.findByTaskId(id);
        for (Tasks tasks : tasksList){
            currentGameText = tasks.getActualTask();
        }
        List<String> textInWords = Arrays.asList(currentGameText.split("\\s+"));
        List<String> textWithYellowWords = addYellowHighlight(textInWords, generateRandomWords(currentGameText, 7));
        for (String t : textWithYellowWords){
            stringBuilder.append(t).append(" ");
        }
        return stringBuilder.toString();
    }
    private static List<String> addYellowHighlight(List<String> textInWords, List<String> randomWords) {
        for (int i = 0; i < randomWords.size(); i++) {
            String a = randomWords.get(i);
            for (int j = 0; j < textInWords.size(); j++) {
                if (textInWords.get(j).equals(a)){
                    String highlightedWord = "\u001B[33m" + a + "\u001B[0m";
                    textInWords.set(j, highlightedWord);
                }
            }
        }
        return textInWords;
    }
    private static List<String> generateRandomWords(String text, int numberOfWords) {
        String[] words = text.split("\\s+");
        Random random = new Random();
        List<String> randomWordsList = new ArrayList<>();

        for (int i = 0; i < numberOfWords; i++) {
            int randomIndex = random.nextInt(words.length);
            randomWordsList.add(words[randomIndex]);
        }

        return randomWordsList;
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
