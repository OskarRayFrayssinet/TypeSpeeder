package se.ju23.typespeeder;


import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component public class Controller implements Controllable {
    Playable playable;
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
                io.addString(playable.printMenu());
                String input = io.getString();
                status = playable.playGame(input);
            }


            switch (status){
                case VERIFIED, PLAYING_GAME -> io.addString(playable.printMenu());
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
            }
        }
    }
}