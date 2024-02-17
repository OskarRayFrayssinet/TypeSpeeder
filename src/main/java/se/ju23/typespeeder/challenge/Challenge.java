package se.ju23.typespeeder.challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Challenge implements iChallenge {
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
        List<Character> letters = lettersToType();
        System.out.println("Type the following characters: ");
        for (char letter : letters) {
            System.out.println(letter + " ");
        }
        System.out.println("\nBegin typing now!");
    }
}
