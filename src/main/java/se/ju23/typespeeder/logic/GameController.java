/*
 * Class: TypingGame.
 * Description:  A interface for TypingGame.
 * Created by: Kerem Bjävenäs Tazedal
 * Email: kerem.tazedal@iths.se
 * Date: 2024-02-14
 */
package se.ju23.typespeeder.logic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.IO.IO;
import se.ju23.typespeeder.IO.Menu;
import se.ju23.typespeeder.IO.MenuService;
import se.ju23.typespeeder.enums.Status;
import se.ju23.typespeeder.model.Player;
import se.ju23.typespeeder.model.RankingTableByTotalPoints;
import se.ju23.typespeeder.repository.PlayerRepo;
import se.ju23.typespeeder.repository.RankingTableByLevelRepo;
import se.ju23.typespeeder.repository.RankingTableByTotalPointsRepo;
import se.ju23.typespeeder.repository.ResultRepo;
import se.ju23.typespeeder.service.*;

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

    @Autowired
    RankingTableByLevelRepo rankingTableByLevelRepo;

    @Autowired
    Menu menu;

    @Autowired
    StatusService statusService;

    @Autowired
    RankingTableByTotalPointsRepo rankingTableByTotalPointsRepo;



    public void startGame() {
        boolean continueGame;
        do {
            continueGame = true;
            menuService.setGameLanguage();
            menuService.displayLoginMenu();
            Status credentials = playerService.checkCredentials(playerRepo, io);
            if (credentials.equals(Status.OK)) {
                do {
                    Player activePlayer = playerService.getActivePlayer();
                    do {
                        if (statusService.getStatus().equals(Status.SVENSKA)){
                            menuService.displayMenu();
                        } else if (statusService.getStatus().equals(Status.ENGLISH)) {
                            menuService.displayMenuEnglish();
                        }
                        int userInput = menuService.selectMenuOptions();
                        switch (userInput) {
                            case 1 -> {
                                List<String> calculatedWords;
                                typingGame.generateGameDifficulty();
                                if (statusService.getStatus().equals(Status.SVENSKA)) {
                                    typingGame.lettersToType();
                                } else if (statusService.getStatus().equals(Status.ENGLISH)){
                                    typingGame.lettersToType();
                                }
                                resultService.inputFromPlayerInGame(typingGame.getCalculatedWords(), io, resultRepo, activePlayer, playerRepo);
                                playerService.calculatePlayerLevel(playerRepo);
                                typingGame.getCalculatedWords().removeAll(typingGame.getCalculatedWords());
                                typingGame.getRedWords().removeAll(typingGame.getRedWords());
                            }
                            case 2 -> {
                                boolean runLeaderBoardMenu = true;
                                do {
                                    menu.displayLeaderboardMenu();
                                    int userInputChoice = menu.selectLeaderboardMenu();
                                    switch (userInputChoice) {
                                        case 1 -> System.out.println(rankingTableByLevelRepo.findAll());
                                        case 2 -> System.out.println(rankingTableByTotalPointsRepo.findAll());
                                        case 3 -> runLeaderBoardMenu = false;
                                    }
                                } while (runLeaderBoardMenu);
                            }
                            case 3 -> {
                                boolean runNewsMenu = true;
                                do {
                                    menuService.displayNewsMenu();
                                    int userInputNews = menuService.selectNewsOptions();
                                    switch (userInputNews) {
                                        case 1 -> newsletterService.displayNewsletters();
                                        case 2 -> newsletterService.createNewsLetter(activePlayer);
                                        case 3 -> patchService.displayPatchNews();
                                        case 4 -> patchService.createPatchNews(activePlayer, io);
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
                } while (continueGame);
            }
        } while (true);
    }
}


