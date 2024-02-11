package se.ju23.typespeeder;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component public class Controller implements Controllable {
    Playable playable;
    MenuService menuService;
    IO io;


    public Controller(Playable playable, IO io,MenuService m) {
        this.playable = playable;
        this.io = io;
        this.menuService = m;
    }

    @Override
    public void run() throws SQLException, InterruptedException {


        while (true) {

            Status status;
            if (playable.getCurrentAlias().isEmpty()){
                if (menuService.getNumberOfTries() == 0){
                io.exit();
                }
                io.addString(menuService.printLoginText());
                String email = io.getString();
                io.addString(menuService.printLoginText());
                String password = io.getString();

                status = playable.checkUser(email, password);
            } else {
                io.addString(menuService.displayMenu(menuService.getMenuOptions()));
                int input = io.getInt();
                status = playable.standbyInMainMenu(input);
            }


            switch (status){
                case IN_GAME_SETTINGS -> {
                    io.addString(menuService.displayMenu(menuService.getUserSettingsMenu()));
                    int input = io.getInt();
                    if(input == 0){
                        status = Status.VERIFIED;
                    } else {
                        status = playable.standbyInSettingsMenu(input);
                    }
                    switch (status){
                        case CHANGING_ALIAS -> {
                            io.addString(menuService.displayMenu(menuService.getUserNameChangeText()));
                            String newAlias = io.getString();
                            playable.setNewAlias(newAlias);
                            io.addString(menuService.displayMenu(menuService.getUserNameChangeText()));
                        }
                    }

                }

                case ACTIVE_IN_GAME -> {
                    io.addString(playable.printGames());
                    int input = io.getInt();
                    if (input == 0){
                        status = Status.VERIFIED;
                    } else {
                        io.addString(playable.activeInGame(input));
                    }
                    //status = playable.playingGame(input);
                }
               // case OK -> status = playable.playAgain(io.yesNo(playable.printMenu()));
                case NO_USER_FOUND -> {
                    io.addString(menuService.printLoginText());
                }
            }
            switch (status){
                case EXIT -> io.exit();
                case CONTINUANCE ->{
                    io.clear();
                    io.addString(menuService.displayMenu(menuService.getMenuOptions()));
                }
                //case PLAYING_GAME ->
            }
        }
    }
}