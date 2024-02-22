package se.ju23.typespeeder.gameLogic;

import java.time.LocalTime;
import java.util.List;

public interface IChallenge {
    String printListOfGames();

    String printListOfEasyGames();

    String chooseGame(int id);

    void calculateTimeToDouble();

    int getRandomWordsAccordingToLevel();

    void startChallenge();

    void endChallenge();
    void setCurrentGameTaskId();

    void getAndSetCurrentLanguage();

    int getGameListSize();
}
