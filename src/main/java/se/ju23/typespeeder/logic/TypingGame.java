package se.ju23.typespeeder.logic;

import org.springframework.stereotype.Repository;

@Repository
public interface TypingGame {
    void generateWords(int userinput);
    int generateGameDifficulty();
    void inputFromPlayerInGame();
    void calcuatePlayerXP();
    void generateLeaderboard();

}
