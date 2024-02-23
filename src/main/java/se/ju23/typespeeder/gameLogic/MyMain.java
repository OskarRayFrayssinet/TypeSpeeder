package se.ju23.typespeeder.gameLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.gameLogic.Controllable;
import se.ju23.typespeeder.gameLogic.Playable;
import se.ju23.typespeeder.userInterfaces.IO;

import java.sql.SQLException;

@Component public class MyMain implements CommandLineRunner {
    private final Playable tg;
    private final IO io;
    private final Controllable c;
    private final IChallenge challenge;


    @Autowired
    public MyMain(Playable tg, IO io, Controllable c,IChallenge challenge){
        this.tg = tg;
        this.io = io;
        this.c = c;
        this.challenge = challenge;
    }
    @Override
    public void run(String... args) throws SQLException, InterruptedException {
        c.run();

    }
}
