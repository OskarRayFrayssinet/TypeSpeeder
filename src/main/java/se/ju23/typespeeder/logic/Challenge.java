/*
 * Class: Challenge
 * Description: A class that implements TypingGame and handles the logic of the game.
 * Created by: Kerem Bjävenäs Tazedal
 * Email: kerem.tazedal@iths.se
 * Date: 2024-02-14
 */
package se.ju23.typespeeder.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.IO.IO;
import se.ju23.typespeeder.IO.Menu;
import se.ju23.typespeeder.enums.Status;
import se.ju23.typespeeder.repository.ResultRepo;
import se.ju23.typespeeder.service.DictionaryService;
import se.ju23.typespeeder.service.StatusService;

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

    @Autowired
    StatusService statusService;

    private List<String> calculatedWords = new ArrayList<>();

    private List<String> redWords = new ArrayList<>();

    private int userSelectDifficulty;

    public List<String> getCalculatedWords() {
        return calculatedWords;
    }

    public List<String> setCalculatedWords(List<String> calculatedWords) {
        this.calculatedWords = calculatedWords;
        return calculatedWords;
    }

    public List<String> getRedWords() {
        return redWords;
    }

    public List<String> setRedWords(List<String> redWords) {
        this.redWords = redWords;
        return redWords;
    }

    public int getUserSelectDifficulty() {
        return userSelectDifficulty;
    }

    public int setUserSelectDifficulty(int userSelectDifficulty) {
        this.userSelectDifficulty = userSelectDifficulty;
        return userSelectDifficulty;
    }

    public void lettersToType() {
        if (getUserSelectDifficulty() == 1 && (statusService.getStatus().equals(Status.ENGLISH))) {
            generateWordsEngEasyMode();
        } else if (getUserSelectDifficulty() == 1 && (statusService.getStatus().equals(Status.SVENSKA))) {
            generateWordsSweEasyMode();
        } else if (getUserSelectDifficulty() == 2 && (statusService.getStatus().equals(Status.ENGLISH))) {
            generateEngWordHardMode();
        } else if (getUserSelectDifficulty() == 2 && (statusService.getStatus().equals(Status.SVENSKA))) {
            generateSweWordsHardMode();
        } else if (getUserSelectDifficulty() == 3 && (statusService.getStatus().equals(Status.ENGLISH))) {
            generateEngToungeTwisters();
        } else if ((getUserSelectDifficulty() == 3 && (statusService.getStatus().equals(Status.SVENSKA)))) {
            generateSweToungeTwisters();
        } else if ((getUserSelectDifficulty() == 4 && (statusService.getStatus().equals(Status.SVENSKA)))) {
            generateWordsSweColor();
        } else if ((getUserSelectDifficulty() == 4 && (statusService.getStatus().equals(Status.ENGLISH)))) {
            generateWordsEngColor();
        }
    }

    public void startChallenge() {
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

    public List<String> generateWordsSweColor() {
        int generateQuantWords = 0;
        List<String> words = new ArrayList<>(dictionaryService.SwedishDictionary().getWords());
        Collections.shuffle(words);
        boolean redColor = false;

        List<String> redWords = new ArrayList<>();

        do {
            for (int i = 0; i < words.size(); i++) {
                if (redColor) {
                    String redWord = "\u001B[31m" + words.get(i) + "\u001B[0m";
                    redWords.add(redWord);
                    System.out.print(redWord + " ");
                } else {
                    System.out.print(words.get(i) + " ");
                }

                generateQuantWords++;

                if (generateQuantWords == 10) {
                    break;
                }

                redColor = !redColor;
            }
        } while (generateQuantWords < 10);

        return setRedWords(redWords);
    }


    public List<String> generateWordsEngColor() {
        int generateQuantWords = 0;
        List<String> words = new ArrayList<>(dictionaryService.EnglishDictionary().getWords());
        Collections.shuffle(words);
        boolean redColor = false;

        List<String> redWords = new ArrayList<>();

        do {
            for (int i = 0; i < words.size(); i++) {
                if (redColor) {
                    String redWord = "\u001B[31m" + words.get(i) + "\u001B[0m";
                    redWords.add(redWord);
                    System.out.print(redWord + " ");
                } else {
                    System.out.print(words.get(i) + " ");
                }

                generateQuantWords++;

                if (generateQuantWords == 10) {
                    break;
                }

                redColor = !redColor;
            }
        } while (generateQuantWords < 10);

        return setRedWords(redWords);
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
        if (statusService.getStatus().equals(Status.SVENSKA)) {
            System.out.println(menu.getMenuOptions().get(33));
            System.out.println(menu.getMenuOptions().get(34));
            System.out.println(menu.getMenuOptions().get(35));
            System.out.println(menu.getMenuOptions().get(36));
            System.out.println(menu.getMenuOptions().get(37));
        } else {
            System.out.println(menu.getMenuOptions().get(38));
            System.out.println(menu.getMenuOptions().get(39));
            System.out.println(menu.getMenuOptions().get(40));
            System.out.println(menu.getMenuOptions().get(41));
            System.out.println(menu.getMenuOptions().get(42));
        }

        int userSelectDifficulty = io.getValidIntegerInput(io.returnScanner(), 1, 4);
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
            case 4 -> {
                return setUserSelectDifficulty(userSelectDifficulty);
            }
        }
        return setUserSelectDifficulty(userSelectDifficulty);
    }

}

