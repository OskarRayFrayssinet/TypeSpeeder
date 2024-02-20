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

import java.util.List;
import java.util.Scanner;

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
                    int userChoiceLanguage = menuService.setMenuInput();
                    switch (userChoiceLanguage) {
                        case 1 -> menuService.displayMenu();
                        case 2 -> menuService.displayMenuEnglish();
                    }
                    int userInput = menuService.selectMenuOptions();
                    switch (userInput) {
                        case 1 -> {
                            List<String> calculatedWords;
                            typingGame.generateGameDifficulty();
                            if (menuService.getStatus().equals(Status.SVENSKA)){
                                typingGame.lettersToType();
                            } else {
                                typingGame.lettersToType();
                            }
                            resultService.inputFromPlayerInGame(typingGame.getCalculatedWords(), io, resultRepo, activePlayer, playerRepo);
                            playerService.calculatePlayerLevel(playerRepo);
                            typingGame.getCalculatedWords().removeAll(typingGame.getCalculatedWords());

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

