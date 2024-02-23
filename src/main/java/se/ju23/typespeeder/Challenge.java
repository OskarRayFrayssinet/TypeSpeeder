package se.ju23.typespeeder;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class Challenge {
    private ArrayList<String> typeGoal;
    private String typeInput;
    private long startTimerInMillis;
    private long endTimerInMillis;
    private long timerInMillis;
    private final SystemIO systemIO = new SystemIO();
    private DAOManager daoManager;
    private static boolean isSwedish;
    private int difficulty;

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public static boolean isSwedish() {
        return isSwedish;
    }

    public static void setSwedish(boolean swedish) {
        isSwedish = swedish;
    }

    public void setDaoManager(DAOManager daoManager) {
        this.daoManager = daoManager;
    }

    public void generateTypingString() {
        typeGoal = new ArrayList<>();
        typeGoal = (ArrayList<String>) daoManager.fetchGoalText(isSwedish, difficulty);
    }
    private Double checkAccuracy(String input, String goal) {
        int length = Math.max(input.length(), goal.length());
        int matchingCount = 0;

        for (int i = 0; i < length; i++) {
            if (i > input.length()-1 || i > goal.length()-1) {
                break;
            } else if (input.charAt(i) == goal.charAt(i)) {
                matchingCount++;
            }
        }

        return (double) matchingCount / length * 100;
    }
    public double inputVsGoalCheck() {
        String typeGoalString = String.join(" ", typeGoal);

        double similarityPercentage = checkAccuracy(removeBlanks(typeInput),
                removeBlanks(typeGoalString));

        String[] typeInputStringArray = typeInputToStringArray();

        int singleWordChecks = 0;

        for (int i = 0; i < typeGoal.size(); i++) {
            if (typeInputStringArray.length == i){
                break;
            }
            similarityPercentage += checkAccuracy(typeInputStringArray[i], typeGoal.get(i));
            singleWordChecks++;
        }
        return similarityPercentage / (singleWordChecks + 1);
    }

    public String removeBlanks(String stringWithBlankSpaces) {
        return stringWithBlankSpaces.replaceAll("\\s", "");
    }

    public String[] typeInputToStringArray() {
        return typeInput.split(" ");
    }

    public int checkStreak() {
        String[] typeInputStringArray = typeInputToStringArray();

        ArrayList<Integer> streaks = new ArrayList<>();
        streaks.add(0);
        int streakIndex = 0;

        int maxIndex = Math.min(typeInputStringArray.length, typeGoal.size());

        for (int i = 0; i < maxIndex; i++) {
            if (typeInputStringArray[i].equals(typeGoal.get(i))) {
                streaks.set(streakIndex, streaks.get(streakIndex) + 1);
            } else {
                streakIndex++;
                streaks.add(0);
            }
        }
        return Collections.max(streaks);
    }

    public void postGamePlayerStatsUpdate(double[] results) {
        int playerLevel = daoManager.getPlayer().getLevel();
        int xpGainThreshHold = calcXpGainThreshHold();

        if (results[4] < xpGainThreshHold){
            results[4] = -(xpGainThreshHold - ((double) xpGainThreshHold / 5));
        }

        int playerXp = daoManager.getPlayer().getXp();
        int levelGainThreshHold = calcLevelGainThreshHold();

        int newTotalXpAmount = (int) (results[4] + playerXp);

        if (newTotalXpAmount >= levelGainThreshHold) {
            results[4] = newTotalXpAmount - levelGainThreshHold;
            daoManager.incrementPlayerLevel();
            systemIO.addString("Level up! Du är nu level: " + (playerLevel + 1) + "\n");
        }

        int rank = calcCurrentGameRanking(results[4]);

        daoManager.addGame(rank);

        daoManager.updatePlayerStats((int) results[4], rank);
    }
    public int calcXpGainThreshHold() {
        int playerLevel = daoManager.getPlayer().getLevel();
        return 70 + (playerLevel * 2);
    }
    public int calcLevelGainThreshHold() {
        return 1000 * (1 + (calcXpGainThreshHold() / 100));
    }
    public int calcCurrentGameRanking(double xpGain) {
        double[] thresholds = {135, 125, 110, 95, 85, 75, 65, 55};
        for (int i = 0; i < thresholds.length; i++) {
            if (xpGain >= thresholds[i]) {
                return thresholds.length - i + 1;
            }
        }
        return 0;
    }
    public double[] ChallengeScoreCalc() {
        double[] results = new double[5];
        double percentScore = results[0] =  inputVsGoalCheck();

        int streak = checkStreak();
        results[1] = streak;

        double time = results[2] = (double) timerInMillis / 1000;
        double timeScore = results[3] =(time * 2) > 20 ? 0 : 20 - (time * 2);
        double xpGain = results[4] = (percentScore/1.5) + (streak * 4) +
                timeScore + (difficulty * 2.5);

        postGamePlayerStatsUpdate(results);

        return results;
    }

    public void runChallenge() {
        startChallenge();
        typeInput = systemIO.getString();
        endTimer();
        calcTimerResult();
    }
    public String getTop10rankings(){
        List<Player> topTenPlayers = daoManager.top10LevelPlayers();
        StringBuilder topTen = new StringBuilder("""
                    --TOP PLAYERS--
                NAME~-~-RANKING~-~-LEVEL""");
        for (Player topTenPlayer : topTenPlayers) {
            int rank = daoManager.getAverageRankFromPlayer(topTenPlayer.getId());
            topTen.append(String.format("%n%-12s%-9s%-5s", topTenPlayer.getDisplayName(),
                            numberToRankConversion(rank), topTenPlayer.getLevel()));
        }
        topTen.append("\n");
        return topTen.toString();
    }
    public String numberToRankConversion(int rank) {
        String rankClass = "Unranked";
        switch (rank) {
            case 8 -> rankClass = "OP";
            case 7 -> rankClass = "S";
            case 6 -> rankClass = "A";
            case 5 -> rankClass = "B";
            case 4 -> rankClass = "C";
            case 3 -> rankClass = "D";
            case 2 -> rankClass = "E";
            case 1 -> rankClass = "F";
            case 0 -> rankClass = "Unranked";
        }
        return rankClass;
    }
    public void lettersToType() {
        generateTypingString();
        systemIO.addString(String.join(" ", typeGoal) + "\n>");
    }
    public void startChallenge() {
        lettersToType();
        startTimer();
    }

    public void startTimer() {
        startTimerInMillis = System.currentTimeMillis();
    }
    public void endTimer() {
        endTimerInMillis = System.currentTimeMillis();
    }
    public void calcTimerResult() {
        timerInMillis = endTimerInMillis - startTimerInMillis;
    }
}
