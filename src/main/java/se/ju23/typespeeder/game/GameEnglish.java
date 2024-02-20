package se.ju23.typespeeder.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.challenge.Challenge;
import se.ju23.typespeeder.classer.DictionaryService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
public class GameEnglish implements iGame {

    @Autowired
    DictionaryService dictionaryService;
    private Challenge challenge;
    private long startTime;
    private long endTime;

    @Override
    public void generateWords() {
        if (dictionaryService != null) {
            List<String> words = new ArrayList<>(challenge.generateRandomEnglishWords(5));
            Collections.shuffle(words);
            for (String word : words) {
                System.out.println(word + " ");
            }
            System.out.println("");
        } else {
            System.out.println("Dictionary service is not available.");
        }
    }

    @Override
    public void generateChallenge() {
        if (dictionaryService != null) {
            challenge = new Challenge();
            startTime = System.currentTimeMillis();
            challenge.beginGame();
            endTime = System.currentTimeMillis();
        } else {
            System.out.println("Dictionary service is not available.");
        }
    }

    @Override
    public double calculateAccuracy(String typedText, String originalText) {
        return 0;
    }

    @Override
    public void calculateTime() {

    }

    @Override
    public void calculatePoints() {

    }

}
