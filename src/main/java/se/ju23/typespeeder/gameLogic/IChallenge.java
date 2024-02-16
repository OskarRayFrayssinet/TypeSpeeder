package se.ju23.typespeeder.gameLogic;

import java.time.LocalTime;
import java.util.List;

public interface IChallenge {
    String printListOfGames();

    String chooseGame(int id);





    void calculateTimeToDouble();

    List<String> addYellowHighlight(List<String> textInWords, List<String> randomWords);

    List<String> generateRandomWords(String text);

    String lettersToType();

    void startChallenge();

    void endChallenge();

    void setStartGame(LocalTime startGame);

    void getAndSetCurrentLanguage();

}
