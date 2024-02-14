package se.ju23.typespeeder.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.IO.IO;
import se.ju23.typespeeder.service.DictionaryService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TypeSpeederGameLogic implements TypingGame {

    @Autowired
    DictionaryService dictionaryService;

    @Qualifier("gameIO")
    @Autowired
    IO io;

    @Override
    public void generateWords(int userSelectDifficulty) {
        if (userSelectDifficulty == 1) {
            int generateQuantWords = 0;
            List<String> words = new ArrayList<>(dictionaryService.EnglishDictionary().getWords());
            Collections.shuffle(words);
            do {
                for (int i = 0; i < words.size(); i++) {
                    System.out.print(words.get(i) + " ");
                    List<String> calculatedWords = new ArrayList<>();
                    calculatedWords.add(words.get(i));
                    generateQuantWords++;
                    if (generateQuantWords == 10) {
                        break;
                    }
                }
            } while (generateQuantWords < 10);
        } else if (userSelectDifficulty == 2) {
            int generateQuantWords = 0;
            List<String> words = new ArrayList<>(dictionaryService.EnglishDictionary().getWords());
            Collections.shuffle(words);
            do {
                for (int i = 0; i < words.size(); i++) {
                    System.out.print(words.get(i) + " ");
                    generateQuantWords++;
                    if (generateQuantWords == 20) {
                        break;
                    }
                }
            } while (generateQuantWords < 20);
        }
    }

    @Override
    public int generateGameDifficulty() {
        System.out.println();
        System.out.println("Please select from the following options");
        System.out.println("1. Easy");
        System.out.println("2. Hard");
        int userSelectDifficulty = io.getValidIntegerInput(io.returnScanner(), 1, 2);
        switch (userSelectDifficulty) {
            case 1 -> {
                return userSelectDifficulty;
            }
            case 2 -> {
                return userSelectDifficulty;
            }
        }
        return userSelectDifficulty;
    }

    @Override
    public void inputFromPlayerInGame() {
        io.getValidStringInput(io.returnScanner());
        System.out.println("Please type all the words as fast you );



    }

    @Override
    public void calcuatePlayerXP() {

    }

    @Override
    public void generateLeaderboard() {

    }
}
