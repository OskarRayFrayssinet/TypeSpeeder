package se.ju23.typespeeder.logic;

import org.springframework.stereotype.Component;

@Component
public interface IGameLogic {

        // Hantera olika typer av utmaningar och svårighetsgrader
        void initializeGameLevel(int level);
        void setChallengeType(String challengeType);

        // Tidsmätning för hur lång tid en användare tar på sig för att svara
        void startTiming();
        void stopTiming();
        long getElapsedTime();

        // Hantering av poäng och levling
        void calculatePoints(boolean correctAnswer, long timeTaken);
        int getPoints();
        void updateLevel();
        int getLevel();

        // Ranking och bedömning
        void updateRankings();
        String getRankings();

        // Välja språk för spelet (kan också hanteras av ett UserInterface interface)
        void setLanguage(String language);

        void startGame();
}



