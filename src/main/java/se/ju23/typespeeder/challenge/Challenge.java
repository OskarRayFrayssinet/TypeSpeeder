package se.ju23.typespeeder.challenge;

import se.ju23.typespeeder.game.GameEnglish;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Challenge implements iChallenge {

    public GameEnglish game = new GameEnglish();
    @Override
    public List<Character> lettersToType() {
        List<Character> letters = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            char randomChar = (char)(random.nextInt(26) + 'a');
            letters.add(randomChar);
        }
        return letters;
    }

    @Override
    public void startChallenge() {
        System.out.println("Type the following characters: ");
        game.generateWords();
        System.out.println("\nBegin typing now!");
    }
}
