package se.ju23.typespeeder.gameLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.classesFromDB.Tasks;
import se.ju23.typespeeder.classesFromDB.TasksRepo;
import se.ju23.typespeeder.colors.ConsoleColor;
import se.ju23.typespeeder.userInterfaces.MenuService;

import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

@Component
public class Challenge implements IChallenge {
    public String stringToPrint = "";
    public List<String> currentSolution = new ArrayList<>();
    public String currentLanguage = "2";
    public int currentGameTaskId = 0;
    public String currentGameText = "";
    public LocalTime startGame = LocalTime.now();
    public LocalTime endGame = LocalTime.now();
    public int gameDifficulty = 0;
    public int gameListSize = 0;
    public int chosenGame = 0;
    Translatable translator;
    Playable playable;
    MenuService menuService;
    @Autowired
    TasksRepo tasksRepo;
    public List<Tasks> listOfCurrentGames = new ArrayList<>();

    @Autowired
    public void setPlayable(Playable playable) {
        this.playable = playable;
    }

    @Autowired
    public void setTranslatable(Translatable translator) {
        this.translator = translator;
    }

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    public Challenge() {

    }
    @Override
    public String printListOfGames() {
        gameDifficulty = 2;
        setGameDifficulty();
        List<Tasks> tasksList = tasksRepo.findByDifficulty("HARD");
        return getGameList(tasksList);
    }
    @Override
    public String printListOfEasyGames(){
        gameDifficulty = 1;
        setGameDifficulty();
        List<Tasks> tasksList = tasksRepo.findByDifficulty("EASY");
        return getGameList(tasksList);
    }

    private String getGameList(List<Tasks> tasksList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tasksList.size(); i++) {
            stringBuilder.append((i + 1)).append(". ").append(tasksList.get(i).getTaskName()).append("\n");
        }
        listOfCurrentGames = tasksList;
        gameListSize = tasksList.size();
        if (gameDifficulty == 1){
            return ConsoleColor.GREEN + "EASY GAMES" + ConsoleColor.RESET + ConsoleColor.CYAN + ConsoleColor.BOLD +  "\n0. Go back\n" + ConsoleColor.RESET + ConsoleColor.CYAN + stringBuilder + ConsoleColor.RESET ;
        } else {
            return ConsoleColor.RED + "HARD GAMES" + ConsoleColor.RESET + ConsoleColor.CYAN + ConsoleColor.BOLD + "\n0. Go back\n" + ConsoleColor.RESET + ConsoleColor.CYAN + stringBuilder + ConsoleColor.RESET ;
        }

    }


    @Override
    public String chooseGame(int input) {

        getAndSetCurrentLanguage();
        chosenGame = input;
        findTaskByName();
        generateAndMarkWords();

        return lettersToType();

    }


    private void findTaskByName() {
        for (int i = 0; i < listOfCurrentGames.size(); i++) {
           currentGameText = listOfCurrentGames.get(chosenGame - 1).getActualTask();
           currentGameTaskId = listOfCurrentGames.get(chosenGame - 1).getTaskId();

        }
        setCurrentGameTaskId();
    }

    private void generateAndMarkWords() {
        StringBuilder stringBuilder = new StringBuilder();

        if (!containsOnlySingleLetters(currentGameText)) {
            if (currentLanguage.equals("1")) {
                try {
                    currentGameText = translator.translate(currentGameText, "sv");
                } catch (Exception e) {
                    throw new RuntimeException();
                }
            }
        }
        List<String> textInWords = Arrays.asList(currentGameText.split("\\s+"));
        List<String> textWithYellowWords = addYellowHighlight(textInWords, generateRandomWords(currentGameText));

        for (String t : textWithYellowWords) {
            stringBuilder.append(t).append(" ");
        }
        stringToPrint = stringBuilder.toString();
    }
    private boolean containsOnlySingleLetters(String text) {
        String[] words = text.split("\\s+");
        for (String word : words) {
            if (word.length() != 1) {
                return false;
            }
        }
        return true;
    }

    public String lettersToType() {
        return stringToPrint;
    }

    @Override
    public void calculateTimeToDouble() {
        Duration difference = Duration.between(startGame, endGame);
        long millisecondsDifference = difference.toMillis();
        playable.setTimeResult(millisecondsDifference / 1000.0);
    }

    private List<String> addYellowHighlight(List<String> textInWords, List<String> randomWords) {
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

            if (word.startsWith("\u001B[33m") && word.endsWith("\u001B[0m")) {

                yellowWords.add(word.substring(5, word.length() - 4));
                if (yellowWords.size() == getRandomWordsAccordingToLevel()){
                    break;
                }
            }
        }
        currentSolution = yellowWords;
        setCurrentSolution();
        return textInWords;
    }
    private List<String> generateRandomWords(String text) {
        int wordsIndicies;
        String[] words = text.split("\\s+");
        Random random = new Random();
        List<String> randomWordsList = new ArrayList<>();
        if (gameDifficulty == 2){
            wordsIndicies = getRandomWordsAccordingToLevel();
        } else {
            wordsIndicies = 3;
        }

        int[] indices = new int[wordsIndicies];
        Set<Integer> chosenIndices = new HashSet<>();
        for (int i = 0; i < wordsIndicies; i++) {
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
        return randomWordsList;
    }

    @Override
    public int getRandomWordsAccordingToLevel(){
        int numberOfWords = 3;
        int level = getCurrentLevel();
        if (level > 0){
            numberOfWords += (level * 2);
        }
        playable.setNumOfWords(numberOfWords);

        return numberOfWords;
    }

    private void setCurrentSolution() {
        playable.setCurrentSolution(currentSolution);
    }


    @Override
    public void startChallenge() {
        startGame = LocalTime.now();
    }

    public int getCurrentLevel() {
        return playable.getCurrentLevel();
    }

    @Override
    public void endChallenge() {
        endGame = LocalTime.now();
        calculateTimeToDouble();
    }
    @Override
    public void setCurrentGameTaskId() {
        playable.setCurrentTaskId(currentGameTaskId);
    }

    @Override
    public void getAndSetCurrentLanguage() {
        currentLanguage = menuService.getCurrentLanguage(0);

    }

    public void setGameDifficulty() {
        playable.setGameDifficulty(gameDifficulty);
    }
    @Override
    public int getGameListSize() {
        return gameListSize;
    }
}
