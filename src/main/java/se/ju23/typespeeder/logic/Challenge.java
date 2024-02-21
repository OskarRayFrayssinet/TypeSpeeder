package se.ju23.typespeeder.logic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.IO.IO;
import se.ju23.typespeeder.IO.Menu;
import se.ju23.typespeeder.enums.Status;
import se.ju23.typespeeder.repository.ResultRepo;
import se.ju23.typespeeder.service.DictionaryService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class Challenge implements TypingGame {

    @Autowired
    DictionaryService dictionaryService;

    @Qualifier("gameIO")
    @Autowired
    IO io;

    @Autowired
    ResultRepo resultRepo;

    @Autowired
    Menu menu;

    private List<String> calculatedWords = new ArrayList<>();

    private int userSelectDifficulty;

    public List<String> getCalculatedWords() {
        return calculatedWords;
    }

    public List<String> setCalculatedWords(List<String> calculatedWords) {
        this.calculatedWords = calculatedWords;
        return calculatedWords;
    }

    public int getUserSelectDifficulty() {
        return userSelectDifficulty;
    }

    public int setUserSelectDifficulty(int userSelectDifficulty) {
        this.userSelectDifficulty = userSelectDifficulty;
        return userSelectDifficulty;
    }

    public void lettersToType(){
        if (getUserSelectDifficulty() == 1 && (menu.getStatus().equals(Status.ENGLISH))) {
            generateWordsEngEasyMode();
        } else if (getUserSelectDifficulty() == 1 && (menu.getStatus().equals(Status.SVENSKA))) {
            generateWordsSweEasyMode();
        } else if (getUserSelectDifficulty() == 2 && (menu.getStatus().equals(Status.ENGLISH))) {
            generateEngWordHardMode();
        } else if (getUserSelectDifficulty() == 2 && (menu.getStatus().equals(Status.SVENSKA))) {
            generateSweWordsHardMode();
        } else if (getUserSelectDifficulty() == 3 && (menu.getStatus().equals(Status.ENGLISH))) {
            generateEngToungeTwisters();
        } else if ((getUserSelectDifficulty() == 3 && (menu.getStatus().equals(Status.SVENSKA)))) {
            generateSweToungeTwisters();
        }

    }

    public void startChallenge(){
        lettersToType();
    }

    @Override
    public List<String> generateWordsEngEasyMode() {
            int generateQuantWords = 0;
            List<String> words = new ArrayList<>(dictionaryService.EnglishDictionary().getWords());
            Collections.shuffle(words);
            do {
                for (int i = 0; i < words.size(); i++) {
                    System.out.print(words.get(i) + " ");
                    calculatedWords.add(words.get(i));
                    generateQuantWords++;
                    if (generateQuantWords == 10) {
                        break;
                    }
                }
            } while (generateQuantWords < 10);
        return setCalculatedWords(calculatedWords);
    }

    public List<String> generateEngWordHardMode() {
        if (userSelectDifficulty == 2) {
            int generateQuantWords = 0;
            List<String> words = new ArrayList<>(dictionaryService.EnglishDictionary().getWords());
            Collections.shuffle(words);
            do {
                for (int i = 0; i < words.size(); i++) {
                    System.out.print(words.get(i) + " ");
                    calculatedWords.add(words.get(i));
                    generateQuantWords++;
                    if (generateQuantWords == 20) {
                        break;
                    }
                }
            } while (generateQuantWords < 20);
        }
        return calculatedWords;
    }

    public List<String> generateWordsSweEasyMode() {
            int generateQuantWords = 0;
            List<String> words = new ArrayList<>(dictionaryService.SwedishDictionary().getWords());
            Collections.shuffle(words);
            do {
                for (int i = 0; i < words.size(); i++) {
                    System.out.print(words.get(i) + " ");
                    calculatedWords.add(words.get(i));
                    generateQuantWords++;
                    if (generateQuantWords == 10) {
                        break;
                    }
                }
            } while (generateQuantWords < 10);
        return setCalculatedWords(calculatedWords);
    }

    public List<String> generateSweWordsHardMode() {
            int generateQuantWords = 0;
            List<String> words = new ArrayList<>(dictionaryService.SwedishDictionary().getWords());
            Collections.shuffle(words);
            do {
                for (int i = 0; i < words.size(); i++) {
                    System.out.print(words.get(i) + " ");
                    calculatedWords.add(words.get(i));
                    generateQuantWords++;
                    if (generateQuantWords == 20) {
                        break;
                    }
                }
            } while (generateQuantWords < 20);
        return setCalculatedWords(calculatedWords);
    }

    public List<String> generateEngToungeTwisters() {
            List<String> words = new ArrayList<>(dictionaryService.toungeTwistersEng().getWords());
            Collections.shuffle(words);
            int displayWords = 0;
            for (int i = 0; i < words.size(); i++) {
                System.out.print(words.get(i));
                calculatedWords.add(words.get(i));
                displayWords++;
                if (displayWords == 1) {
                    break;
                }
            }
        return setCalculatedWords(calculatedWords);
    }

    public List<String> generateSweToungeTwisters() {
            List<String> words = new ArrayList<>(dictionaryService.toungeTwistersSwe().getWords());
            Collections.shuffle(words);
            int displayWords = 0;
            for (int i = 0; i < words.size(); i++) {
                System.out.print(words.get(i));
                calculatedWords.add(words.get(i));
                displayWords++;
                if (displayWords == 1) {
                    break;
                }
            }
        return setCalculatedWords(calculatedWords);
    }


    @Override
    public int generateGameDifficulty() {
        System.out.println();
        if (menu.getStatus().equals(Status.SVENSKA)) {
            System.out.println("Vängligen välj från följande alternativ: ");
            System.out.println("1. Lätt TypeSpeeder");
            System.out.println("2. Svår TypeSpeeder");
            System.out.println("3. Tungvrickare");
        } else {
            System.out.println("Please select from the following options");
            System.out.println("1. Easy TypeSpeeder");
            System.out.println("2. Hard TypeSpeeder");
            System.out.println("3. Tounge Twisters");
        }

        int userSelectDifficulty = io.getValidIntegerInput(io.returnScanner(), 1, 3);
        switch (userSelectDifficulty) {
            case 1 -> {
                return setUserSelectDifficulty(userSelectDifficulty);
            }
            case 2 -> {
                return setUserSelectDifficulty(userSelectDifficulty);
            }
            case 3 -> {
                return setUserSelectDifficulty(userSelectDifficulty);
            }
        }
        return setUserSelectDifficulty(userSelectDifficulty);
    }

}

