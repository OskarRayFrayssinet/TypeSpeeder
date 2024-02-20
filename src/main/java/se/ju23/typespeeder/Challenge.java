package se.ju23.typespeeder;

import org.springframework.stereotype.Component;

@Component
public class Challenge {
    private String typeGoal;
    private String typeInput;
    private long startTimerInMillis;
    private long endTimerInMillis;
    private long timerInMillis;
    private final SystemIO systemIO = new SystemIO();
    public String generateTypingString() {
        typeGoal = "hej på dig";
        return typeGoal;
    }
    public void checkTypingAccuracy() {
        int maxLength = Math.max(typeInput.length(), typeGoal.length());
        int matchingCount = 0;

        for (int i = 0; i < maxLength; i++) {
            if (i < typeInput.length() && i < typeGoal.length() && typeInput.charAt(i) == typeGoal.charAt(i)) {
                matchingCount++;
            }
        }

        double similarityPercentage = (double) matchingCount / maxLength * 100;
    }
    public void generateRatingScore() {

    }

    public void addPlayerAccount() {

    }
    public void lettersToType() {
        systemIO.addString(generateTypingString() + "\n>");
    }
    public void startChallenge() {
        lettersToType();
        startTimer();
        typeInput = systemIO.getString();
        endTimer();
        calcTimerResult();
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
