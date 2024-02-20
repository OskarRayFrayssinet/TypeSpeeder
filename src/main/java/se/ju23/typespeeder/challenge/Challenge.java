package se.ju23.typespeeder.challenge;

import se.ju23.typespeeder.game.GameEnglish;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class Challenge implements iChallenge {

    public GameEnglish game = new GameEnglish();
    public Scanner scanner = new Scanner (System.in);
    private Instant startTime;
    private Instant endTime;

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

        double accuracy = calculateAccuracy(typedText.trim(), wordString.toString().trim());
        System.out.println("Accuracy: " + accuracy);
        endTime = Instant.now();
        if (accuracy == 100.0) {
            calculateTime();
        }
    }

    public void beginGame() {
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
        System.out.println("Enter your words: ");
        String inputLine = scanner.nextLine();
        String[] w = inputLine.split("\\s+");
        playersInputList.addAll(Arrays.asList(w));

        String typedText = String.join(" ", playersInputList);

        double accuracy = calculateAccuracy(typedText.trim(), wordString.toString().trim());
        System.out.println("Accuracy: " + accuracy);
        endTime = Instant.now();
        if (accuracy == 100.0) {
            calculateTime();
        }
    }

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
    public int calculateAccuracy(String typedText, String originalText) {

        int minLength = Math.min(typedText.length(), originalText.length());
        int maxLength = Math.max(typedText.length(), originalText.length());
        int errors = maxLength - minLength;
        char[] typedChars = typedText.toCharArray();
        char[] originalChars = originalText.toCharArray();



        for (int i = 0; i < minLength; i++ ) {
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

    public void calculateTime() {
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

}
