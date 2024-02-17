package se.ju23.typespeeder.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.challenge.Challenge;
import se.ju23.typespeeder.service.DictionaryService;

import java.util.ArrayList;
import java.util.Collection;
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
        List<String> words = new ArrayList<>(dictionaryService.EnglishText().getWords());
        Collections.shuffle(words);
        for (String word : words) {
            System.out.println(word + " ");
        }
        System.out.println("");
    }

    @Override
    public void generateChallenge() {
        if (dictionaryService != null) {
            challenge = new Challenge();
            startTime = System.currentTimeMillis();
            challenge.startChallenge();
            endTime = System.currentTimeMillis();
        } else {
            System.out.println("Dictionary service is not available.");
        }
    }

    @Override
    public double calculateAccuracy(String typedText, String originalText) {


        int minLen = Math.min(typedText.length(), originalText.length());
        int correctChars = 0;

        for (int i = 0; i < minLen; i++) {
            if (typedText.charAt(i) == originalText.charAt(i)) {
                correctChars++;
            }
        }
        double accuracy = (double) correctChars / originalText.length() * 100.0;
        return accuracy;
    }

    @Override
    public void calculateTime() {
        long elapsedTime = endTime - startTime;
        System.out.println("Time taken: " + elapsedTime + " milliseconds");
    }

    @Override
    public void calculatePoints() {

    }
}
