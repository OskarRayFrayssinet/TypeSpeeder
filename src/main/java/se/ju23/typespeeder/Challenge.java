package se.ju23.typespeeder;

import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import static se.ju23.typespeeder.TypeSpeederApplication.userService;

public class Challenge {
    private static ResourceBundle messages = ResourceBundle.getBundle("Messages");
    public static Scanner input = new Scanner(System.in);
    public static boolean stopTimer = false;
    public static long startTime;
    public static String game;
    public static String colorWords;
    public static int countWords;
    public static int countOrder;
    public static long timeSeconds;
    public static List<String> redWords = new ArrayList<>();
    public static final String WHITE = "\u001B[37m";
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static ArrayList<PlayerRanking>rankingList = new ArrayList<>();

    public static void startChallenge() throws IOException {
        boolean continueGame = true;
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
            timer();
            System.out.println();
            System.out.print(messages.getString("write.here"));
            game = input.nextLine();
            System.out.println(game);
            stopTimer = true;
            checkSpelling();
            checkOrder();
            System.out.println(messages.getString("return.menu"));
            String goBack = input.nextLine().toLowerCase();
            if ("ja".equalsIgnoreCase(goBack) || "yes".equalsIgnoreCase(goBack)) {
                Menu.displayMenu();
                continueGame = false;
            }

        }
    }

    public void lettersToType() {
    }
    public static void openTextFile() throws FileNotFoundException {
        StringBuilder colorWordsBuilder = new StringBuilder();
        //File file = new File("C:\\GitHub Repositories\\TypeSpeeder\\src\\main\\resources\\TextFile");
        File file = new File("./src/main/resources/TextFile");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String textFile = scanner.nextLine();
            String[] words = textFile.split(" ");
            for (String word : words) {
                String color = Math.random() < 0.5 ? RED : GREEN;
                colorWordsBuilder.append(color).append(word).append(" ");
                System.out.print(color + word + " " + RESET);
            }
            //System.out.print(RESET);
        }
        colorWords = colorWordsBuilder.toString();
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
            timeSeconds = (System.currentTimeMillis() - startTime) / 1000;

           // System.out.println(messages.getString("your.time" + timeSeconds + "seconds"));
            System.out.println("Din tid blev: " + timeSeconds + " sekunder");
        });
        timer.start();
    }
    public static void checkSpelling(){
        String [] words = colorWords.split("\\s+");
        for (String word : words) {
            if (word.startsWith(RED)) {
                redWords.add(word.substring(RED.length()));
            }
        }
        String [] gameList = game.split("\\s+");
        //int countWords = 0;
        for (String list : gameList){
            for(String red : redWords){
                if (red.equals(list)){
                    countWords++;
                }
            }
        }
        //System.out.println(messages.getString("correct.words" + countWords));
        System.out.println("Antal rättstavade ord: " + countWords);
    }
    public static void checkOrder(){
        String[] gameList = game.split("\\s+");
        int minWord = Math.min(redWords.size(), gameList.length);
        for(int i = 0; i < minWord; i++){
            if(gameList[i].equals(redWords.get(i))){
                countOrder++;
            //} else {
                //break;
            }
        }
       System.out.println("Antal ord i korrekt ordning: " + countOrder);
        //System.out.println(messages.getString("right.order" + countOrder));
    }

    public static void printRankingList(ArrayList<PlayerRanking> topList) {
        System.out.println("Ranking List:\nPosition   Player     Score\n");
        int position = 1;
        topList.sort((p1, p2) -> Double.compare(p1.result, p2.result));

        for(PlayerRanking player : topList){
            System.out.printf(String.format("%-9d%-10s%7.2f%n", position++, player.name, player.result));
        }
    }
    public static void showRankingList() throws IOException {
        ArrayList<PlayerRanking> topList = rankingList();
        printRankingList(topList);
        System.out.println(messages.getString("return.menu"));
        String goBack = input.nextLine().toLowerCase();
        if ("ja".equalsIgnoreCase(goBack) || "yes".equalsIgnoreCase(goBack)) {
            Menu.displayMenu();
        }
    }
    public static class PlayerRanking{
        String name;
        double result;

        public PlayerRanking(String name, double result) {
            this.name = name;
            this.result = result;
        }

        public String getName() {
            return name;
        }

        public double getResult() {
            return result;
        }

        public void setResult(double result) {
            this.result = result;
        }
    }

    public static ArrayList<PlayerRanking> rankingList(){
        int wordsValue = 1;
        int orderValue = 2;
        int wordPoints = countWords * wordsValue;
        int orderPoints = countOrder * orderValue;
        int points = wordPoints + orderPoints;
        float score = (float) points /timeSeconds;
        String username = Menu.loggedInUsername;
        boolean playerExist = false;
        for (PlayerRanking player : rankingList) {
            if (player.getName().equals(username)) {
                double result = player.getResult();
                player.setResult(result + score);
                playerExist = true;
                break;
            }
        }
        if (!playerExist){
            rankingList.add(new PlayerRanking(username, score));
        }
        return rankingList;
    }
}
