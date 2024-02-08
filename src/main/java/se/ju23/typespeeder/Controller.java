package se.ju23.typespeeder;


import java.sql.SQLException;

public class Controller implements Controllable {
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
            if (playable.currentId() == 0){
                io.addString(playable.printIntroOutroText());
                String input = io.getString();
                status = playable.playGame(input);
            } else {
                String input = io.getString();
                status = playable.playGame(input);
            }


            switch (status){
                case VERIFIED, PLAYING_GAME -> io.addString(playable.printIntroOutroText());
                case OK -> status = playable.playAgain(io.yesNo(playable.printIntroOutroText()));
                case NO_USER_FOUND -> {
                    io.addString(playable.noUserFoundText());
                    Thread.sleep(5000L);
                    io.exit();
                }
            }
            switch (status){
                case EXIT -> io.exit();
                case CONTINUANCE ->{
                    io.clear();
                    io.addString(playable.printIntroOutroText());
                }
            }
        }
    }
}