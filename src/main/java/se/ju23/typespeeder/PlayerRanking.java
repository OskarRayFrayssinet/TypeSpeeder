package se.ju23.typespeeder;

import java.io.IOException;
import java.util.ArrayList;

import static se.ju23.typespeeder.Challenge.timeSeconds;

public class PlayerRanking {
    String name;
    double result;
    int level;
    public static float score;
    public static int levelNumber;
    public static String username = Menu.loggedInUsername;
    public static ArrayList<PlayerRanking>rankingList = new ArrayList<>();

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


    public static ArrayList<PlayerRanking> rankingList(){
        int wordsValue = 1;
        int orderValue = 2;
        int wordPoints = Challenge.countWords * wordsValue;
        int orderPoints = Challenge.countOrder * orderValue;
        int points = wordPoints + orderPoints;
        score = 0.0f;
        if(timeSeconds!=0) {
            score = (float) points / timeSeconds;
        }
        boolean playerExist = false;
        for (PlayerRanking player : rankingList) {
            if (player.getName().equals(username)) {
                double result = player.getResult();
                if(score>1){
                    player.setResult(result + score);
                    playerExist = true;
                    break;
                } else {
                    player.setResult(result - score);
                    playerExist = true;
                    break;
                }

            }
        }
        level();
        if (!playerExist){
            rankingList.add(new PlayerRanking(username, score, levelNumber));
        }
        return rankingList;
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
        Challenge.returnToMenu();
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
}
