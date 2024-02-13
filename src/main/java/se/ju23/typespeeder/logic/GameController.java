package se.ju23.typespeeder.logic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.IO.IO;
import se.ju23.typespeeder.MenuService;
import se.ju23.typespeeder.enums.Status;
import se.ju23.typespeeder.repository.PlayerRepo;
import se.ju23.typespeeder.service.PlayerService;
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
    PlayerService playerService;

    public void startGame() {
        boolean foundUser = false;
        do {
            menuService.displayLoginMenu();
            if (playerService.checkCredentials(playerRepo, io).equals(Status.OK)) {
                menuService.displayMenu();
                menuService.selectMenuOptions();
            }

        } while (!foundUser) ;
    }
}

