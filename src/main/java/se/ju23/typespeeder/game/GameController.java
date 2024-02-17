package se.ju23.typespeeder.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.database.PlayersRepo;
import se.ju23.typespeeder.database.ResultatRepo;
import se.ju23.typespeeder.menu.Menu;

@Service
public class GameController {
    @Autowired
    PlayersRepo playersRepo;
    @Autowired
    ResultatRepo resultatRepo;

    Menu menu = new Menu();

    public void startGame(){
        boolean foundUser = true;
        do {


        } while (foundUser);
    }

}
