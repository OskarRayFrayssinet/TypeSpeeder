package se.ju23.typespeeder.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.model.Dictionary;
import se.ju23.typespeeder.service.DictionaryService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TypeSpeederGameLogic implements TypingGame{

    @Autowired
    DictionaryService dictionaryService;
    @Override
    public void generateWords() {
        List<String> words = new ArrayList<>(dictionaryService.EnglishDictionary().getWords());
        Collections.shuffle(words);
        for (String word : words) {
            System.out.print(word + " ");
        }
        System.out.println("");

    }

    @Override
    public void generateGameDifficulty() {
        System.out.println();
        System.out.println("Please select from the following options");

    }

    @Override
    public void calculatePlayerResponse() {

    }

    @Override
    public void calcuatePlayerXP() {

    }

    @Override
    public void generateLeaderboard() {

    }
}
