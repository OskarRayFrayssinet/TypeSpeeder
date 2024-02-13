package se.ju23.typespeeder;

public class TypeSpeederGame {
    private String typeGoal;
    private String typeInput;
    private long startTimerInMillis;
    private long endTimerInMillis;
    private long timerInMillis;
    public void generateTypingString() {

    }
    public void checkTypingAccuracy() {

    }
    public void generateRatingScore() {

    }
    public void logIn() {

        verifyUsername();
        verifyPassword();
    }
    public void verifyUsername() {

    }
    public void verifyPassword() {

    }
    public void addPlayerAccount() {

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
