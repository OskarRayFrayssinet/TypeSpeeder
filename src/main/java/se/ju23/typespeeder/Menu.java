package se.ju23.typespeeder;

import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileInputStream;

public class Menu implements MenuService {
    static List<String> MenuOptions = new ArrayList<>();
    public static final String YELLOW = "\u001B[33m";
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static Scanner input = new Scanner(System.in);
    public static boolean stopTimer = false;
    public static long startTime;
    public static String game;
    public static String colorWords;


    public static void displayMenu() throws IOException {

        System.out.println("Välkommen till TypeSpeeder!");
        MenuOptions.add("1. Starta spelet");
        MenuOptions.add("2. Rankningslista");
        MenuOptions.add("3. Nyheter och updateringar");
        MenuOptions.add("4. Ändra språk");
        MenuOptions.add("5. Updatera profil");
        System.out.println(MenuOptions);
        System.out.print("Ange siffran för ditt val: ");

        int menuChoice = input.nextInt();
        input.nextLine();
        switch (menuChoice) {
            case 0 -> User.logOut();
            case 1 -> playGame();
            case 2 -> showRankingList();
            case 3 -> showNewsAndUpdates();
            case 4 -> changeLanguage();
            case 5 -> User.updateProfile();
            default -> System.out.println("Felaktig inmatning, försök igen.");
        }

    }

    public static void playGame() throws IOException {
        System.out.println("Skriv de röda orden korrekt med samma ordning som de står och tryck enter när du är klar.");
        System.out.println("Tiden börjar när texten skrivs ut");
        System.out.print("Klicka Enter för att starta spelet");
        input.nextLine();
        openTextFile();
        timer();
        System.out.println();
        System.out.print("SKRIV HÄR --> ");
        game = input.nextLine();
        stopTimer = true;
        correctSpell();

    }

    public static void openTextFile() throws FileNotFoundException {

        File file = new File("C:\\Users\\janss\\IdeaProjects\\Text2.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String textFile = scanner.nextLine();
            String[] words = textFile.split(" ");
            for (String word : words) {
                String color = Math.random() < 0.5 ? RED : GREEN;
                colorWords = color + word + " ";
                System.out.print(colorWords + RESET);
            }
            //System.out.print(RESET);
        }
    }
    public static void timer(){
    Thread timer = new Thread(()->{
            startTime = System.currentTimeMillis();
            while (!stopTimer) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            long timeSeconds = (System.currentTimeMillis() - startTime) / 1000;
            System.out.println("Din tid blev: " + timeSeconds + " sekunder");
        });
        timer.start();
    }
    public static void correctSpell(){
        List<String> redWords = new ArrayList<>();
        String[] words = colorWords.split("\\s+");
        for (String word : words) {
            if (word.contains(RED)) {
                String[] red = word.split(RED);
                redWords.add(red[1]);
            }
        }
        int countWords = 0;
        for(String red : redWords){
            if (game.equals(red)){
            countWords++;

            }
        }
        System.out.println("Antal rättstavade ord = " + countWords);
    }
    public static void rightOrder(){

    }

    public static void showRankingList() {

    }

    public static void showNewsAndUpdates() {

    }

    public static void changeLanguage() {

    }

    public List<String> getMenuOptions() {
        return MenuOptions;
    }
}
