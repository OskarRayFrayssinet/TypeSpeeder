package se.ju23.typespeeder.logic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.IO.IO;
import se.ju23.typespeeder.MenuService;
import se.ju23.typespeeder.enums.Status;
import se.ju23.typespeeder.model.Player;
import se.ju23.typespeeder.repository.PlayerRepo;
import se.ju23.typespeeder.repository.ResultRepo;
import se.ju23.typespeeder.service.PlayerService;
import se.ju23.typespeeder.service.ResultService;
import java.util.ArrayList;
import java.util.List;

@Service
public class GameController {

    @Autowired
    PlayerRepo playerRepo;

    @Qualifier("gameIO")
    @Autowired
    IO io;

    @Autowired
    MenuService menuService;

    @Autowired
    ResultService resultService;

    @Autowired
    PlayerService playerService;

    @Autowired
    TypingGame typingGame;

    @Autowired
    ResultRepo resultRepo;

    public void startGame() {
        boolean foundUser = false;
        boolean continueGame = true;
        do {
            menuService.displayLoginMenu();
            if (playerService.checkCredentials(playerRepo, io).equals(Status.OK)) {
                Player activePlayer = playerService.getActivePlayer();
                do {
                    Status selectedLanguage = menuService.displayMenu();
                    int userInput = menuService.selectMenuOptions(selectedLanguage);
                    switch (userInput) {
                        case 1 -> {
                            List<String> calculatedWords = new ArrayList<>();
                            int userSelectDifficulty = typingGame.generateGameDifficulty(selectedLanguage);

                            if (selectedLanguage == Status.SVENSKA){
                                calculatedWords = typingGame.generateWordsSweEasyMode(userSelectDifficulty);
                                calculatedWords = typingGame.generateSweWordsHardMode(userSelectDifficulty);
                                calculatedWords = typingGame.generateSweToungeTwisters(userSelectDifficulty);
                            } else {
                                calculatedWords = typingGame.generateWordsEngEasyMode(userSelectDifficulty);
                                calculatedWords = typingGame.generateEngWordHardMode(userSelectDifficulty);
                                calculatedWords = typingGame.generateEngToungeTwisters(userSelectDifficulty);

                            }
                            resultService.inputFromPlayerInGame(calculatedWords, io, resultRepo, activePlayer, playerRepo);
                            playerService.calculatePlayerLevel(playerRepo);
                            calculatedWords.removeAll(calculatedWords);

                        }
                        case 2 -> System.out.println("Leaderboards");
                        case 3 -> System.out.println("News");
                        case 4 -> System.out.println("Edit users");
                        case 5 -> continueGame = false;
                        case 6 -> System.exit(0);
                    }
                } while (continueGame);
            }

        } while (!foundUser);
    }
}

