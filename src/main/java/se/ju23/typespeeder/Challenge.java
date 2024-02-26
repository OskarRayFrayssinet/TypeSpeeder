package se.ju23.typespeeder;

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
    public static String username = Menu.loggedInUsername;
    public static float score;
    public static int levelNumber;
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
        boolean continueGame = true;
        while(continueGame){
            stopTimer = false;
            game = "";
            redWords = new ArrayList<>();
            countWords = 0;
            countOrder = 0;
            String language = input.nextLine();

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
            System.out.println(game);
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
        //int countWords = 0;
        for (String list : gameList){
            for(String red : redWords){
                if (red.equals(list)){
                    countWords++;
                }
            }
        }
       System.out.println(messages.getString("correct.words") + countWords);

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

        System.out.println(messages.getString("right.order") + countOrder);
    }

    public static void printRankingList(ArrayList<PlayerRanking> topList) {
        System.out.println("Ranking List:\nPlace    Player      Score       Level\n");
        int position = 1;
        topList.sort((p1, p2) -> Double.compare(p1.result, p2.result));

        for(PlayerRanking player : topList){
            System.out.printf(String.format("%-9d%-10s%7.2f%9d%n", position++, player.name, player.result, player.level));
        }
    }
    public static void showRankingList() throws IOException {
        ArrayList<PlayerRanking> topList = rankingList();
        printRankingList(topList);
        returnToMenu();
    }
    public static class PlayerRanking{
        String name;
        double result;
        int level;

        public PlayerRanking(String name, double result) {
            this.name = name;
            this.result = result;
        }
        public PlayerRanking(String name, double result, int level) {
            this.name = name;
            this.result = result;
            this.level = level;
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

        public void setLevel(int level) {
            this.level = level;
        }
    }

    public static ArrayList<PlayerRanking> rankingList(){
        int wordsValue = 1;
        int orderValue = 2;
        int wordPoints = countWords * wordsValue;
        int orderPoints = countOrder * orderValue;
        int points = wordPoints + orderPoints;
        score = 0.0f;
        if(timeSeconds!=0) {
            score = (float) points / timeSeconds;
        }
        boolean playerExist = false;
        for (PlayerRanking player : rankingList) {
            if (player.getName().equals(username)) {
                double result = player.getResult();
                player.setResult(result + score);
                playerExist = true;
                break;
            }
        }
        level();
        if (!playerExist){
            rankingList.add(new PlayerRanking(username, score, levelNumber));
        }
        return rankingList;
    }

    public static void level(){
        levelNumber = 1;
        for (PlayerRanking player : rankingList) {
            if (player.getName().equals(username)) {
                double result = player.getResult();
                if (result>=5){
                    levelNumber = (int) (result / 5 + 1);
                } else {
                    levelNumber = 1;
                }
                player.setLevel(levelNumber);
            }
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
