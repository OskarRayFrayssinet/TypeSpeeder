package se.ju23.typespeeder.service;

import org.springframework.stereotype.Service;
import se.ju23.typespeeder.classer.Dictionary;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class DictionaryService {

    public List<String> generateRandomEnglishWords(int numberOfWords) {
        List<String>words = new ArrayList<>();
        Random random = new Random();

        List<String> englishWords = Arrays.asList("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten");

        for (int i = 0; i < numberOfWords; i++) {
            String randomWord = englishWords.get(random.nextInt(englishWords.size()));
            words.add(randomWord);
        }
        return words;
    }


    public List<String> generateRandomSwedishWords(int numberOfWords) {
        List<String>words = new ArrayList<>();
        Random random = new Random();

        List<String> swedishWords = Arrays.asList("ett", "två", "tre", "fyra", "fem", "sex", "sju", "atta", "nio", "tio");

        for (int i = 0; i < numberOfWords; i++) {
            String randomWord = swedishWords.get(random.nextInt(swedishWords.size()));
            words.add(randomWord);
        }
        return words;
    }
}