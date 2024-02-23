/*
 * Class: TypingGame
 * Description: A class that acts as the controller for the application.
 * Created by: Kerem Bjävenäs Tazedal
 * Email: kerem.tazedal@iths.se
 * Date: 2024-02-14
 */
package se.ju23.typespeeder.logic;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TypingGame {
    List<String> generateWordsEngEasyMode();
    List<String> generateEngWordHardMode();
    List<String> generateWordsSweEasyMode();
    List<String> generateSweWordsHardMode();
    int generateGameDifficulty();
    List<String> generateEngToungeTwisters();
    List<String> generateSweToungeTwisters();
    void lettersToType();
    List<String> getCalculatedWords();
    void startChallenge();
    List<String> generateWordsSweColor();
    List<String> getRedWords();

}
