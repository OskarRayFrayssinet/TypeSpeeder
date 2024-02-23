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
        int xpGainThreshHold = (70 + (playerLevel * 2));

        if (results[4] < xpGainThreshHold){
            results[4] = -(xpGainThreshHold - ((double) xpGainThreshHold / 5));
        }

        int playerXp = daoManager.getPlayer().getXp();
        int levelGainThreshHold = (1000 * (1 + (xpGainThreshHold / 100)));

        int newTotalXpAmount = (int) (results[4] + playerXp);

        if (newTotalXpAmount >= levelGainThreshHold) {
            results[4] = newTotalXpAmount - levelGainThreshHold;
            daoManager.incrementPlayerLevel();
            systemIO.addString("Level up! Du är nu level: " + (playerLevel + 1) + "\n");
        }

        daoManager.updatePlayerStats((int) results[4], calcNewRanking());
    }
    public int calcNewRanking() {
        int ranking = 1;
        daoManager.getPlayer().setRanking(ranking);
        return ranking;
    }
    public double[] postChallengeSummary() {
        double[] results = new double[5];
        double percentScore = results[0] =  inputVsGoalCheck();

        int streak = checkStreak();
        results[1] = streak;

        double time = results[2] = (double) timerInMillis / 1000;
        double timeScore = results[3] =(time * 2) > 20 ? 0 : 20 - (time * 2);
        double xpGain = results[4] = (percentScore/1.5) + (streak * 4) +
                timeScore + difficulty * 2;

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
            topTen.append(String.format("%n%-12s%-9s%-5s", topTenPlayer.getDisplayName(),
                            topTenPlayer.getRanking(), topTenPlayer.getLevel()));
        }
        topTen.append("\n");
        return topTen.toString();
    }
    public void lettersToType() {
        generateTypingString();
        systemIO.addString(String.join(" ", typeGoal) + "\n>");
    }
    public void startChallenge() {
        lettersToType();
        startTimer();
    }
    public void settings(){

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
