package se.ju23.typespeeder.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GameLogic {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    public void startGame() {
        System.out.println("Welcome to Color Typing Game!");

        while (true) {
            String text = generateText(50, 5);
            System.out.println("Type the following words:");

            System.out.println(text);

            // Starta tidtagning
            long startTime = System.currentTimeMillis();

            // Läs in användarens inmatning
            String userInput = scanner.nextLine();

            // Stoppa tidtagning
            long endTime = System.currentTimeMillis();

            // Räkna antalet rätt ord
            int score = countCorrectWords(userInput, text);

            // Räkna antalet ord i rätt ordning
            int orderScore = countOrderScore(userInput, text);

            // Beräkna den totala poängen
            int totalScore = score + orderScore;

            // Beräkna den totala tiden det tog för spelaren att svara
            double elapsedTimeInSeconds = (endTime - startTime) / 1000.0;

            System.out.println("Your score: " + totalScore);
            System.out.printf("Time taken: %.2f seconds%n", elapsedTimeInSeconds);

            System.out.println("Do you want to play again? (yes/no)");
            String playAgain = scanner.nextLine().toLowerCase();
            if (!playAgain.equals("yes")) {
                System.out.println("Thanks for playing! Goodbye.");
                break;
            }
        }

        scanner.close();
    }

    private static String generateText(int length, int wordFrequency) {
        StringBuilder text = new StringBuilder();

        for (int i = 0; i < length; i++) {
            if (i > 0 && i % wordFrequency == 0) {
                // Generera ett slumpmässigt ord
                int wordLength = (int) (Math.random() * 6) + 5; // Ord längd mellan 5 och 10 bokstäver
                StringBuilder word = new StringBuilder();
                for (int j = 0; j < wordLength; j++) {
                    char randomChar = (char) (Math.random() * 26 + 'a');
                    word.append(randomChar);
                }
                text.append(word).append(" ");
            } else {
                // Generera en slumpmässig bokstav
                char randomChar = (char) (Math.random() * 26 + 'a');
                text.append(randomChar);
            }
        }

        return text.toString();
    }

    private static int countCorrectWords(String userInput, String text) {
        // Jämför användarens inmatning med de genererade orden
        String[] generatedWords = text.split("\\s+");
        String[] inputWords = userInput.split("\\s+");

        List<String> correctWordsList = new ArrayList<>();

        for (String inputWord : inputWords) {
            for (String generatedWord : generatedWords) {
                if (inputWord.equals(generatedWord) && !correctWordsList.contains(inputWord)) {
                    correctWordsList.add(inputWord); // Lägg till korrekta ord i en lista
                }
            }
        }

        return correctWordsList.size(); // Returnera antalet korrekta ord
    }

    private static int countOrderScore(String userInput, String text) {
        // Räkna antalet ord som är i rätt ordning
        String[] generatedWords = text.split("\\s+");
        String[] inputWords = userInput.split("\\s+");

        int orderScore = 0;
        int minLen = Math.min(generatedWords.length, inputWords.length);

        for (int i = 0; i < minLen; i++) {
            if (generatedWords[i].equals(inputWords[i])) {
                orderScore++; // Öka poängen om ordet är i rätt ordning
            }
        }

        return orderScore;
    }
}
