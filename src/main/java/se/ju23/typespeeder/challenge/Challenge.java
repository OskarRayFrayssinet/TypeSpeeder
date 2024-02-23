package se.ju23.typespeeder.challenge;

import se.ju23.typespeeder.MyRunner;
import se.ju23.typespeeder.classer.TerminalColors;
import se.ju23.typespeeder.database.Players;
import se.ju23.typespeeder.database.Resultat;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class Challenge implements iChallenge {

    public Scanner scanner = new Scanner(System.in);
    private Instant startTime;
    private Instant endTime;

    @Override
    public List<Character> lettersToType() {
        List<Character> letters = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            char randomChar = (char) (random.nextInt(26) + 'a');
            letters.add(randomChar);
        }
        return letters;
    }

    @Override
    public void startChallenge() {
        startTime = Instant.now();
        System.out.println("Type the following characters: ");
        List<String> words = generateRandomEnglishWords(2);
        StringBuilder wordString = new StringBuilder();
        for (String word : words) {
            wordString.append(word).append(" ");
        }
        System.out.println(wordString.toString().trim());
        System.out.println("\nBegin typing now!");
        List<String> playersInputList = new ArrayList<>();

        String typedText = String.join(" ", playersInputList);

        double accuracy = calculateAccuracyBasicGame(typedText.trim(), wordString.toString().trim());
        System.out.println("Accuracy: " + accuracy);
        endTime = Instant.now();
        if (accuracy == 100.0) {
            calculateTime2();
        }
    }

    public void calculateTime2() {
        if (startTime != null && endTime != null) {
            Duration duration = Duration.between(startTime, endTime);
            long seconds = duration.getSeconds();
            long minutes = seconds / 60;
            seconds %= 60;
            System.out.println("Time taken: " + minutes + " minutes " + seconds + " seconds");

        } else {
            System.out.println("Time calculation failed. Start and end times are not properly set.");

        }
    }

    public void basicGame(Players players) {
        startTime = Instant.now();
        System.out.println("Type the following characters: ");
        List<String> words = generateRandomEnglishWords(10);
        StringBuilder wordString = new StringBuilder();
        for (String word : words) {
            wordString.append(word).append(" ");
        }
        System.out.println(wordString.toString().trim());
        System.out.println("\nBegin typing now!");
        List<String> playersInputList = new ArrayList<>();
        System.out.println("Enter your words: ");
        String inputLine = scanner.nextLine();
        String[] w = inputLine.split("\\s+");
        playersInputList.addAll(Arrays.asList(w));

        String typedText = String.join(" ", playersInputList);

        double accuracy = calculateAccuracyBasicGame(typedText.trim(), wordString.toString().trim());
        System.out.println("Accuracy: " + accuracy);
        endTime = Instant.now();
        if (accuracy == 100.0) {
            calculateTime(players);
        }
    }

    public List<String> generateRandomEnglishWords(int numberOfWords) {
        List<String> words = new ArrayList<>();
        Random random = new Random();

        List<String> englishWords = Arrays.asList("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten");

        for (int i = 0; i < numberOfWords; i++) {
            String randomWord = englishWords.get(random.nextInt(englishWords.size()));
            words.add(randomWord);
        }
        return words;
    }

    public void colourGame(Players players) {
        startTime = Instant.now();
        System.out.println("Type the following characters: ");
        System.out.println("If the first word starts with [33m, consider it as highlighted");
        List<String> originalWords = generateRandomEnglishWords(40);
        List<String> highlightedWords = generateRandomEnglishWordsWithColour(4);

        StringBuilder wordString = new StringBuilder();
        for (String word : highlightedWords) {
            wordString.append(word).append(" ");
        }
        System.out.println(wordString.toString().trim());
        System.out.println("\nBegin typing now!");

        System.out.println("Enter your words: ");
        String inputLine = scanner.nextLine();
        String[] typedWord = inputLine.split("\\s+");

        double accuracy = calculateAccuracyColouredGame(typedWord, highlightedWords);
        endTime = Instant.now();
        if (accuracy == 100.0) {
            calculateTime(players);
        }
    }

    public List<String> generateRandomEnglishWordsWithColour(int numberOfWords) {
        List<String> englishWords = Arrays.asList("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten");
        return highlightEnglishWordsInYellow(englishWords, numberOfWords);
    }

    private List<String> highlightEnglishWordsInYellow(List<String> words, int numberOfWords) {
        List<String> highlightedWords = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < numberOfWords; i++) {
            String word = words.get(random.nextInt(words.size()));
            if (random.nextBoolean()) {
                word = TerminalColors.YELLOW + word + TerminalColors.RESET;
            }
            highlightedWords.add(word);
        }
        return highlightedWords;
    }

    public int calculateAccuracyBasicGame(String typedText, String originalText) {

        int minLength = Math.min(typedText.length(), originalText.length());
        int maxLength = Math.max(typedText.length(), originalText.length());
        int errors = maxLength - minLength;
        char[] typedChars = typedText.toCharArray();
        char[] originalChars = originalText.toCharArray();

        for (int i = 0; i < minLength; i++) {
            if (typedChars[i] != originalChars[i]) {
                errors++;
            }
        }

        int accuracy = 100 - (errors * 100) / maxLength;

        if (errors == 0) {
            System.out.println("You typed perfectly!");
        } else {
            System.out.println("You made " + errors + " mistake(s).");
        }
        return accuracy;
    }

    public double calculateAccuracyColouredGame(String[] typedWords, List<String> originalWords) {
        List<String> yellowHighlightedWords = new ArrayList<>();
        int correctHighlightedWords = 0;


        for (String originalWord : originalWords) {

            String cleanOriginalWord = originalWord.replaceAll("\u001B\\[[;\\d]*m", "");
            if (originalWord.startsWith(TerminalColors.YELLOW)) {
                yellowHighlightedWords.add(cleanOriginalWord);
            }
        }

        for (String typedWord : typedWords) {

            for (String yellowWord : yellowHighlightedWords) {
                if (typedWord.equalsIgnoreCase(yellowWord)) {
                    correctHighlightedWords++;
                    break;
                }
            }
        }

        int totalHighlightedWords = yellowHighlightedWords.size();
        double accuracy = totalHighlightedWords == 0 ? 0.0 :
                (double) correctHighlightedWords / totalHighlightedWords * 100.0;

        accuracy = Math.round(accuracy * 100.0) / 100.0;
        System.out.println("Accuracy for highlighted words: " + accuracy + "%");

        return accuracy;
    }

    public void calculateTime(Players players) {
        if (startTime != null && endTime != null) {
            Duration duration = Duration.between(startTime, endTime);
            long seconds = duration.getSeconds();
            long minutes = seconds / 60;
            seconds %= 60;
            System.out.println("Time taken: " + minutes + " minutes " + seconds + " seconds");

            Resultat resultat = new Resultat(0, seconds, 4, players);
            MyRunner.resultatRepo.save(resultat);

        } else {
            System.out.println("Time calculation failed. Start and end times are not properly set.");

        }
    }
    public double savedCountWodsMethod(String[] typedWords, List<String> originalWords){
        int totalHighlightedWords = 0;
        int correctHighlightedWords = 0;

        for (int i = 0; i < typedWords.length && i < originalWords.size(); i++) {
            String typedWord = typedWords[i];
            String originalWord = originalWords.get(i);

            String cleanedOriginalWord = originalWord.replaceAll("\u001B\\[[;\\d]*m", "");

            if (originalWord.contains(TerminalColors.YELLOW)) {
                totalHighlightedWords++;

                // Compare typed word with cleaned original word (without ANSI escape sequences)
                if (typedWord.equalsIgnoreCase(cleanedOriginalWord)) {
                    correctHighlightedWords++;
                }
            }
        }

        double accuracy = totalHighlightedWords == 0 ? 0.0 :
                (double) correctHighlightedWords / totalHighlightedWords * 100.0;

        accuracy = Math.round(accuracy * 100.0) / 100.0;
        System.out.println("Accuracy: " + accuracy + "%");

        return (int) accuracy;
    }

    public double savedCountWordsMethod2(String[] typedWords, List<String> originalWords) {
        int totalHighlightedWords = 0;
        int correctHighlightedWords = 0;

        for (int i = 0; i < typedWords.length && i < originalWords.size(); i++) {
            String typedWord = typedWords[i];
            String originalWord = originalWords.get(i);

            // Remove ANSI escape sequences from original word
            originalWord = originalWord.replaceAll("\u001B\\[[;\\d]*m", "");

            // Check if the original word starts with YELLOW
            if (originalWord.startsWith(TerminalColors.YELLOW)) {
                totalHighlightedWords++;

                if (typedWord.equalsIgnoreCase(originalWord)) {
                    correctHighlightedWords++;
                }
            }
        }

        double accuracy = totalHighlightedWords == 0 ? 0.0 :
                (double) correctHighlightedWords / totalHighlightedWords * 100.0;

        accuracy = Math.round(accuracy * 100.0) / 100.0;
        System.out.println("Accuracy for highlighted words: " + accuracy + "%");

        return accuracy;
    }
}
