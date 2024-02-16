package se.ju23.typespeeder.gameLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.classesFromDB.Tasks;
import se.ju23.typespeeder.classesFromDB.TasksRepo;
import se.ju23.typespeeder.userInterfaces.MenuService;

import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

@Component public class Challenge implements IChallenge {
    public List<String> currentSolution = new ArrayList<>();
    public String currentLanguage = "2";
    public int currentGameTaskId = 0;
    public String currentGameText = "";
    public LocalTime startGame = LocalTime.now();
    public LocalTime endGame = LocalTime.now();
    Translatable translator;
    Playable playable;
    MenuService menuService;
    @Autowired
    TasksRepo tasksRepo;
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
        List<Tasks> tasksList = tasksRepo.findAll();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tasksList.size(); i++) {
            stringBuilder.append((i + 1)).append(". ").append(tasksList.get(i).getTaskName()).append("\n");
        }
        return "0. Go back\n" + stringBuilder.toString();
    }
    @Override
    public String chooseGame(int id){
        getAndSetCurrentLanguage();
        currentGameTaskId = id;
        return lettersToType();
    }
    @Override
    public String lettersToType() {
        String translatedText;
        StringBuilder stringBuilder = new StringBuilder();

        List<Tasks> tasksList = tasksRepo.findByTaskId(currentGameTaskId);
        for (Tasks tasks : tasksList) {
            currentGameText = tasks.getActualTask();
        }
        if (currentLanguage.equals("1")) {
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

        return stringBuilder.toString();
    }
    @Override
    public void calculateTimeToDouble(){
        Duration difference = Duration.between(startGame, endGame);
        long millisecondsDifference = difference.toMillis();
        playable.setTimeResult(millisecondsDifference / 1000.0);
    }
    @Override
    public List<String> addYellowHighlight(List<String> textInWords, List<String> randomWords) {
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
                if (yellowWords.size() == 7){
                    break;
                }
            }
        }
        currentSolution = yellowWords;
        setCurrentSolution();
        //TODO TA BORT DET HÄR SEN
        System.out.println("gula ord" + yellowWords);
        return textInWords;
    }
    @Override
    public List<String> generateRandomWords(String text) {
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
    public void setCurrentSolution(){
        playable.setCurrentSolution(currentSolution);
    }

    @Override
    public void startChallenge(){
        startGame = LocalTime.now();
    }
    @Override
    public void endChallenge(){
       endGame = LocalTime.now();
       calculateTimeToDouble();
    }

    public LocalTime getStartGame() {
        return startGame;
    }

    public LocalTime getEndGame() {
        return endGame;
    }

    public void setEndGame(LocalTime endGame) {
        this.endGame = endGame;
    }
@Override
    public void setStartGame(LocalTime startGame) {
        this.startGame = startGame;
    }
@Override
    public void getAndSetCurrentLanguage() {
    currentLanguage = menuService.getCurrentLanguage(0);

    }

}
