package se.ju23.typespeeder.logic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.IO.IO;
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

    private List<String> calculatedWords = new ArrayList<>();

    @Override
    public List<String> generateWordsEngEasyMode(int userSelectDifficulty) {
        if (userSelectDifficulty == 1) {
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
        }
        return calculatedWords;
    }

    public List<String> generateEngWordHardMode(int userSelectDifficulty) {
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

    public List<String> generateWordsSweEasyMode(int userSelectDifficulty) {
        if (userSelectDifficulty == 1) {
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
        }
        return calculatedWords;
    }

    public List<String> generateSweWordsHardMode(int userSelectDifficulty) {
        if (userSelectDifficulty == 2) {
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
        }
        return calculatedWords;
    }

    public List<String> generateEngToungeTwisters(int userSelectDifficulty) {
        if (userSelectDifficulty == 3) {
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
        }
        return calculatedWords;
    }

    public List<String> generateSweToungeTwisters(int userSelectDifficulty) {
        if (userSelectDifficulty == 3) {
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
        }
        return calculatedWords;
    }


    @Override
    public int generateGameDifficulty(Status status) {
        System.out.println();
        if (status == status.SVENSKA) {
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
                return userSelectDifficulty;
            }
            case 2 -> {
                return userSelectDifficulty;
            }
            case 3 -> {
                return userSelectDifficulty;
            }
        }
        return userSelectDifficulty;
    }

}

