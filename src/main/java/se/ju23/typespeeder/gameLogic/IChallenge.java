package se.ju23.typespeeder.gameLogic;

import java.time.LocalTime;
import java.util.List;

public interface IChallenge {
    String printListOfGames();

    String chooseGame(int id);





    void calculateTimeToDouble();







    void startChallenge();

    void endChallenge();


    void getAndSetCurrentLanguage();

}
