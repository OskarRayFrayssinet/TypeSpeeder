package se.ju23.typespeeder.service;

import org.springframework.stereotype.Service;
import se.ju23.typespeeder.classer.Dictionary;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class DictionaryService {

    public Dictionary EnglishText() {
        Dictionary englishText = new Dictionary ("English");
        englishText.addWord("one");
        englishText.addWord("two");
        englishText.addWord("three");
        englishText.addWord("four");
        englishText.addWord("five");
        englishText.addWord("six");
        englishText.addWord("seven");
        englishText.addWord("eight");
        englishText.addWord("nine");
        englishText.addWord("ten");
        englishText.addWord("one1");
        englishText.addWord("two2");
        englishText.addWord("three3");
        englishText.addWord("four4");
        englishText.addWord("five5");
        englishText.addWord("six6");
        englishText.addWord("seven7");
        englishText.addWord("eight8");
        englishText.addWord("nine9");
        englishText.addWord("ten10");
        englishText.setWords(generateRandomWords(30));
        return englishText;
    }

    public Dictionary SwedishText() {
        Dictionary swedishText = new Dictionary ("Swedish");
        swedishText.addWord("");
        swedishText.addWord("");
        swedishText.addWord("");
        swedishText.addWord("");
        swedishText.addWord("");
        swedishText.addWord("");
        swedishText.addWord("");
        swedishText.addWord("");
        swedishText.addWord("");
        swedishText.addWord("");
        swedishText.addWord("");
        swedishText.addWord("");
        swedishText.addWord("");
        swedishText.addWord("");
        swedishText.addWord("");
        swedishText.addWord("");
        swedishText.addWord("");
        swedishText.addWord("");
        swedishText.addWord("");
        swedishText.addWord("");
        swedishText.addWord("");
        swedishText.addWord("");
        swedishText.addWord("");
        swedishText.addWord("");
        swedishText.addWord("");
        swedishText.addWord("");
        swedishText.addWord("");
        swedishText.addWord("");
        swedishText.addWord("");
        swedishText.addWord("");
        swedishText.addWord("");
        swedishText.addWord("");
        swedishText.addWord("");
        swedishText.addWord("");
        swedishText.addWord("");
        swedishText.addWord("");
        swedishText.addWord("");
        swedishText.addWord("");
        swedishText.addWord("");
        swedishText.setWords(generateRandomWords(100));
        return swedishText;
    }

    private List<String> generateRandomWords(int numberOfWords) {
        List<String>words = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < numberOfWords; i++) {
            StringBuilder word = new StringBuilder();
            int wordLength = random.nextInt(10) + 3;

            for (int j = 0; i < wordLength; j++) {
                char randomChar = (char)('a' + random.nextInt(26));
                word.append(randomChar);
            }
            words.add(word.toString());
        }
        return words;
    }
}