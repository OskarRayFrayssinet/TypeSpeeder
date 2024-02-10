package se.ju23.typespeeder;


import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component public class Controller implements Controllable {
    Playable playable;
    MenuService menuService = new Menu();
    IO io;

    public Controller(Playable playable, IO io) {
        this.playable = playable;
        this.io = io;
    }

    @Override
    public void run() throws SQLException, InterruptedException {


        while (true) {

            Status status;
            if (playable.currentEmail().isEmpty()){
                io.addString(playable.printLoginText());
                String email = io.getString();
                io.addString(playable.printLoginText());
                String password = io.getString();
                status = playable.checkUser(email, password);
            } else {
                int input = io.getInt();
                status = playable.standbyInGame(input);
            }


            switch (status){
                case VERIFIED -> io.addString(menuService.displayMenu());
                case ACTIVE_IN_GAME -> {
                    io.addString(playable.printGames());
                    int input = io.getInt();
                    io.addString(playable.activeInGame(input));
                    //status = playable.playingGame(input);
                }
                case OK -> status = playable.playAgain(io.yesNo(playable.printMenu()));
                case NO_USER_FOUND -> {
                    io.addString(playable.printLoginText());
                }
            }
            switch (status){
                case EXIT -> io.exit();
                case CONTINUANCE ->{
                    io.clear();
                    io.addString(playable.printMenu());
                }
                //case PLAYING_GAME ->
            }
        }
    }
}