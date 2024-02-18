package se.ju23.typespeeder.logic;
import org.springframework.stereotype.Repository;
import se.ju23.typespeeder.enums.Status;
import java.util.List;

@Repository
public interface TypingGame {
    List<String> generateWordsEngEasyMode(int userSelectDifficulty);
    List<String> generateEngWordHardMode(int userSelectDifficulty);
    List<String> generateWordsSweEasyMode(int userSelectDifficulty);
    List<String> generateSweWordsHardMode(int userSelectDifficulty);
    int generateGameDifficulty(Status status);
    List<String> generateEngToungeTwisters(int userSelectDifficulty);
    List<String> generateSweToungeTwisters(int userSelectDifficulty);

}
