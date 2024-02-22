package se.ju23.typespeeder.logic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.IO.IO;
import se.ju23.typespeeder.IO.MenuService;
import se.ju23.typespeeder.enums.Status;
import se.ju23.typespeeder.model.Player;
import se.ju23.typespeeder.repository.PlayerRepo;
import se.ju23.typespeeder.repository.ResultRepo;
import se.ju23.typespeeder.service.NewsletterService;
import se.ju23.typespeeder.service.PatchService;
import se.ju23.typespeeder.service.PlayerService;
import se.ju23.typespeeder.service.ResultService;

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

    @Autowired
    NewsletterService newsletterService;

    @Autowired
    PatchService patchService;


    public void startGame() {
        boolean continueGame = true;
        do {
            menuService.displayLoginMenu();
            Status credentials = playerService.checkCredentials(playerRepo, io);
            int userChoiceLanguage = menuService.setMenuInput();
            do {
                if (credentials.equals(Status.OK)) {
                    Player activePlayer = playerService.getActivePlayer();
                    do {
                        switch (userChoiceLanguage) {
                            case 1 -> menuService.displayMenu();
                            case 2 -> menuService.displayMenuEnglish();
                        }
                        int userInput = menuService.selectMenuOptions();
                        switch (userInput) {
                            case 1 -> {
                                List<String> calculatedWords;
                                typingGame.generateGameDifficulty();
                                if (menuService.getStatus().equals(Status.SVENSKA)) {
                                    typingGame.lettersToType();
                                } else {
                                    typingGame.lettersToType();
                                }
                                resultService.inputFromPlayerInGame(typingGame.getCalculatedWords(), io, resultRepo, activePlayer, playerRepo);
                                playerService.calculatePlayerLevel(playerRepo);
                                typingGame.getCalculatedWords().removeAll(typingGame.getCalculatedWords());
                            }
                            case 2 -> {
                                System.out.println("Leaderboards");
                            }
                            case 3 -> {
                                boolean runNewsMenu = true;
                                do {
                                    menuService.displayNewsMenu();
                                    int userInputNews = menuService.selectNewsOptions();
                                    switch (userInputNews) {
                                        case 1 -> newsletterService.displayNewsletters();
                                        case 2 -> newsletterService.createNewsLetter(userChoiceLanguage, activePlayer);
                                        case 3 -> patchService.displayPatchNews();
                                        case 4 -> patchService.createPatchNews(userChoiceLanguage, activePlayer, io);
                                        case 5 -> runNewsMenu = false;
                                    }
                                } while (runNewsMenu);

                            }
                            case 4 -> {
                                boolean runEditPlayerMenu = true;
                                do {
                                    menuService.displayEditPlayersMenu();
                                    int userInputNews = menuService.selectEditPlayerMenuOptions();
                                    switch (userInputNews) {
                                        case 1 -> playerService.addNewPlayer();
                                        case 2 -> playerService.editUser();
                                        case 3 -> runEditPlayerMenu = false;
                                    }
                                } while (runEditPlayerMenu);

                            }
                            case 5 -> {
                                continueGame = false;
                            }
                            case 6 -> System.exit(0);
                        }
                    } while (continueGame);
                }
            } while (continueGame);

        } while (true);
    }
}


