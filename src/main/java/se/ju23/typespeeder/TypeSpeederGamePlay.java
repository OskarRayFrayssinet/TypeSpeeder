package se.ju23.typespeeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TypeSpeederGamePlay implements Playable{

    public int currentId = 0;

    public int currentGameTaskId = 0;
    public String currentGameText = "";
    public String currentEmail = "";
    public String currentAlias = "";
    public int currentXp = 0;
    public int currentLevel = 0;
    MenuService menuService;


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
        } else {
            setCurrentEmail("1");
            return Status.NO_USER_FOUND;
        }
    }


    public String printGames(){
        List<Tasks> tasksList = tRepo.findAll();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tasksList.size(); i++) {
            stringBuilder.append((i + 1)).append(". ").append(tasksList.get(i).getTaskName()).append("\n");
        }
        return "0. Go back\n" + stringBuilder.toString();
    }
    public String activeInGame(int id){
        StringBuilder stringBuilder = new StringBuilder();
        currentGameTaskId = id;
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
    public Status standbyInMainMenu(int input) {
        Status status = null;
        switch (input){
            case 0 -> status = Status.EXIT;
            //case 1 -> status = setLanguage();
            case 2 -> status = Status.ACTIVE_IN_GAME;
            case 4 -> status = Status.IN_GAME_SETTINGS;
        }
        return status;
    }
    @Override
    public Status standbyInSettingsMenu(int input){
        Status status = null;
        switch (input){
            case 1 -> status = Status.CHANGING_ALIAS;
        }
        return status;
    }
    @Override
    public void setNewAlias(String input){
        List<Users> aliasFromDB = uRepo.findByAlias(getCurrentAlias());
        for (Users u : aliasFromDB){
            u.setAlias(input);
            uRepo.save(u);
        }
    }
    public Status playingGame(int input){
        return null;
    }

    @Override
    public Status playAgain(boolean b) {
        return null;
    }

    @Override
    public String getCurrentEmail() {
        return currentEmail;
    }

    @Override
    public void setCurrentEmail(String newCurrentEmail) {
        currentEmail = newCurrentEmail;
    }


    @Override
    public int currentId() {
        return 0;
    }

    @Override
    public String noUserFoundText() {
        return null;
    }

    @Override
    public String getCurrentAlias() {
        return currentAlias;
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
