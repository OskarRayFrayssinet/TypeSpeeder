package se.ju23.typespeeder.logic;

import org.springframework.stereotype.Repository;

@Repository
public interface TypingGame {
    void generateWords();
    void generateGameDifficulty();
    void calculatePlayerResponse ();
    void calcuatePlayerXP();
    void generateLeaderboard();

}
