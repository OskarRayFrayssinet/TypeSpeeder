package se.ju23.typespeeder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static se.ju23.typespeeder.TypeSpeederApplication.userService;

public class Challenge {
    public static ResourceBundle messages = ResourceBundle.getBundle("Messages");
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    public static Scanner input = new Scanner(System.in);
    public static Scanner scanner = new Scanner(System.in);

    public static boolean stopTimer = false;
    public static long startTime;
    public static String game;
    public static String game2;
    public static String colorWords;
    public static String colorLetters;
    public static int countWords;
    public static int countLetters;
    public static int countOrder;
    public static long timeSeconds;
    public static int gameChoice;
    public static int difficulty;
    public static List<String> redWords = new ArrayList<>();
    public static List<String> redLetters = new ArrayList<>();
    public static final String WHITE = "\u001B[37m";
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static ArrayList<PlayerRanking>rankingList = new ArrayList<>();
    public static Thread timer;



    public static void changeLanguage() throws IOException {
        long startTime = System.nanoTime();

        System.out.print("Välj språk (sv/en):");
        String language = input.nextLine().toLowerCase();

        System.out.println("Valt språk: " + language);
        if ("en".equalsIgnoreCase(language)) {
            messages = ResourceBundle.getBundle("messages", new Locale(language, "US"));
            System.out.println(messages.getString("language.changed"));
        } else if ("sv".equalsIgnoreCase(language)) {
            messages = ResourceBundle.getBundle("messages", new Locale(language, "SE"));
            System.out.println(messages.getString("language.changed"));
        } else {
            System.out.println("Ogiltigt språkval. Använder systemets standardspråk.");
            messages = ResourceBundle.getBundle("messages", Locale.getDefault());
        }

        startGameAfterLanguageSelection();
    }

    public static void startGameAfterLanguageSelection() throws IOException {
        System.out.print(messages.getString("you.want.play"));
        String playGame = input.nextLine().toLowerCase();
        if ("ja".equalsIgnoreCase(playGame) || "yes".equalsIgnoreCase(playGame)) {
            startChallenge();
        } else {
            returnToMenu();
        }
    }

    public static void startChallenge() throws IOException {
        System.out.println(messages.getString("game.type"));
        System.out.println(messages.getString("word"));
        System.out.println(messages.getString("characters"));
        System.out.print(messages.getString("choose.number"));
        gameChoice = input.nextInt();
        input.nextLine();
        switch (gameChoice){
            case 1 -> typeWords();
            case 2 -> lettersToType();
            default -> System.out.println("Felaktig inmatning, försök igen.");
        }

    }
    public static void typeWords() throws IOException {
        boolean continueGame = true;
        System.out.println(messages.getString("choose.level"));
        System.out.println(messages.getString("easy"));
        System.out.println(messages.getString("hard"));
        System.out.print(messages.getString("choose.number"));
        difficulty = input.nextInt();
        input.nextLine();
        while(continueGame){
            stopTimer = false;
            game = "";
            redWords = new ArrayList<>();
            countWords = 0;
            countOrder = 0;
            System.out.println(messages.getString("game.instructions"));
            System.out.println(messages.getString("time.starts"));
            System.out.print(messages.getString("press.enter.to.play"));
            input.nextLine();
            openTextFile();


            Thread timerThread = new Thread(() -> {
                startTime = System.currentTimeMillis();
                while (!stopTimer) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                timeSeconds = (System.currentTimeMillis() - startTime) / 1000;

                System.out.print(messages.getString("your.time") + timeSeconds);
                System.out.println(messages.getString("seconds"));
            });

            timerThread.start();

            System.out.println();
            System.out.print(messages.getString("write.here"));
            game = input.nextLine();
            stopTimer = true;
            try {
                timerThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            checkSpelling();
            checkOrder();
            returnToMenu();
        }
    }

    public static void lettersToType() throws IOException {
        boolean continueGame2 = true;
        while(continueGame2){
            stopTimer = false;
            game2 = "";
            redLetters = new ArrayList<>();
            countLetters = 0;
            countOrder = 0;
            System.out.println(messages.getString("game2.instructions"));
            System.out.println(messages.getString("time.starts"));
            System.out.print(messages.getString("press.enter.to.play"));
            input.nextLine();
            openTextFile();
            timer();
            System.out.println();
            System.out.print(messages.getString("write.here"));
            game2 = input.nextLine();
            stopTimer = true;
            checkLetters();
            checkOrder();
            returnToMenu();
        }

    }
    public static void openTextFile() throws IOException {
        String textFile = "";
        StringBuilder colorWordsBuilder = new StringBuilder();
        if (gameChoice == 1) {
            File file;
            if(difficulty==1) {
                file = new File("./src/main/resources/TextFile");
                scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    textFile += scanner.nextLine();
                }
            } else {
                BufferedReader reader;
                file = new File("./src/main/resources/TextFile2");
                reader = new BufferedReader(new FileReader(file));
                String lines = reader.readLine();

                while (lines != null) {
                    textFile += lines + "\n";
                    lines = reader.readLine();
                }
            }
            String[] words = textFile.split(" ");
            for (String word : words) {
                String color = Math.random() < 0.5 ? RED : GREEN;
                colorWordsBuilder.append(color).append(word).append(" ");
                System.out.print(color + word + " " + RESET);
            }

        } else {
            File file2 = new File("./src/main/resources/TextFile");
            scanner = new Scanner(file2);
            while (scanner.hasNextLine()) {
                textFile = scanner.nextLine();
                char[] letters = textFile.toCharArray();
                for (char letter : letters) {
                    String color = Math.random() < 0.5 ? RED : GREEN;
                    colorWordsBuilder.append(color).append(letter).append(" ");
                    System.out.print(color + letter + " " + RESET);
                }
            }
        }
        colorWords = colorWordsBuilder.toString();
    }
       /*  public static void timer(){
            Thread timer = new Thread(()->{
            startTime = System.currentTimeMillis();
            while (!stopTimer) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            timeSeconds = (System.currentTimeMillis() - startTime) / 1000;

                System.out.print(messages.getString("your.time") + timeSeconds);
                System.out.println(messages.getString("seconds"));
        });

        timer.start();

    }*/
    public static void checkSpelling(){
        String [] words = colorWords.split("\\s+");
        for (String word : words) {
            if (word.startsWith(RED)) {
                redWords.add(word.substring(RED.length()));
            }
        }
        String [] gameList = game.split("\\s+");
        for (String list : gameList){
            for(String red : redWords){
                if (red.equals(list)){
                    countWords++;
                }
            }
        }
       System.out.println(messages.getString("correct.words") + countWords);

    }
    public static void checkLetters(){
        String[] letters = colorWords.split("\\s+");
        for(String letter : letters){
            if (letter.startsWith(RED)) {
                redLetters.add(letter.substring(RED.length()));
            }
        }
        System.out.println(redLetters);
        String [] gameLetters = game2.split("\\s+");
        System.out.println(game2);
        for (String list : gameLetters){
            for(String red : redLetters){
                if (red.equals(list.trim())){
                    countLetters++;
                }
            }
        }
        System.out.println(messages.getString("correct.characters") + countLetters);
    }
    public static void checkOrder(){
        if(gameChoice == 1) {
            String[] gameList = game.split("\\s+");
            int minWord = Math.min(redWords.size(), gameList.length);
            for (int i = 0; i < minWord; i++) {
                if (gameList[i].equals(redWords.get(i))) {
                    countOrder++;
                }
            }
            System.out.println(messages.getString("right.order") + countOrder);
        } else {
            String [] gameList2 = game2.split("\\s+");
            int minWord = Math.min(redLetters.size(), gameList2.length);
            for (int i = 0; i < minWord; i++) {
                if (gameList2[i].equals(redLetters.get(i))) {
                    countOrder++;
                }
            }
            System.out.println(messages.getString("right.char.order") + countOrder);
        }
    }
         public static void returnToMenu() throws IOException {
        System.out.print(messages.getString("return.menu"));
        String goBack = input.nextLine().toLowerCase();
        if ("ja".equalsIgnoreCase(goBack) || "yes".equalsIgnoreCase(goBack)) {
            Menu.displayMenu();
        }
    }
}
