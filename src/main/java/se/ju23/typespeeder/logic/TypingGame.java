package se.ju23.typespeeder.logic;
import org.springframework.stereotype.Repository;
import se.ju23.typespeeder.enums.Status;
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

}
