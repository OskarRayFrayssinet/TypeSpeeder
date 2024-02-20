package se.ju23.typespeeder.game;

public interface iGame {
    void generateWords();
    void generateChallenge();
    double calculateAccuracy(String typedText, String originalText);
    void calculateTime();
    void calculatePoints();

}
