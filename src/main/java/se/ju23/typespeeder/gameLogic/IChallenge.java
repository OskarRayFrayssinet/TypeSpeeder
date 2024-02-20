package se.ju23.typespeeder.gameLogic;

import java.time.LocalTime;
import java.util.List;

public interface IChallenge {
    String printListOfGames();

    String chooseGame(int id);





    void calculateTimeToDouble();


    //TODO OPTIMERA HÄR ENKELT
    int getRandomWordsAccordingToLevel();

    void startChallenge();

    void endChallenge();


    int getCurrentGameTaskId();

    void setCurrentGameTaskId();

    void getAndSetCurrentLanguage();

}
