package se.ju23.typespeeder;

import java.util.Random;
import java.util.Scanner;

public class GameChallenges {
    private Scanner scanner;
    private Random random = new Random();

    public GameChallenges(Scanner scanner) {
        this.scanner = scanner;
    }

    // Metod för att starta spelet, tar emot val av språk och svårighetsgrad
    public void startGame(int languageChoice, int difficulty) {
        System.out.println("Spelet startar...");

        switch (languageChoice) {
            case 1:
                System.out.println("Spelet är på Svenska.");
                break;
            case 2:
                System.out.println("The game is in English.");
                break;
            default:
                System.out.println("Invalid language choice. Defaulting to English.");
                break;
        }

        CharacterCountChallenge(difficulty); // Korrekt anrop med svårighetsgrad som argument
    }

    // Metod för karaktärsräkningsutmaningen, anpassas efter svårighetsgrad
    public void CharacterCountChallenge(int difficulty) {
        // Exempel på hur du kan anpassa utmaningen baserat på svårighetsgrad
        int textLength = switch (difficulty) {
            case 1 -> 50;  // Lätt
            case 2 -> 100; // Medel
            case 3 -> 150; // Svårt
            default -> 100; // Standardlängd om något går fel
        };

        char[] possibleChars = {'@', '#', '$', '%'};
        char targetChar = possibleChars[random.nextInt(possibleChars.length)];
        String text = generateRandomText(targetChar, textLength);

        System.out.println("Här är texten: " + text);
        System.out.println("Hur många gånger förekommer tecknet '" + targetChar + "' i texten ovan?");

        int userGuess = scanner.nextInt();
        long actualCount = text.chars().filter(ch -> ch == targetChar).count();

        if (userGuess == actualCount) {
            System.out.println("Rätt svar! Bra jobbat!");
        } else {
            System.out.println("Fel svar. Det rätta svaret är: " + actualCount);
        }
    }

    // Hjälpmetod för att generera slumpmässig text
    public String generateRandomText(char includeChar, int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (random.nextBoolean() && builder.length() < length - 1) {
                builder.append(includeChar);
            } else {
                builder.append((char) ('a' + random.nextInt(26)));
            }
        }
        return builder.toString();
    }
}
