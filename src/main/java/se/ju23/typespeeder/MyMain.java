package se.ju23.typespeeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component public class MyMain implements CommandLineRunner {
    private final Playable tg;
    private final IO io;
    private final Controllable c;

    @Autowired
    public MyMain(Playable tg, IO io, Controllable c){
        this.tg = tg;
        this.io = io;
        this.c = c;
    }
    @Override
    public void run(String... args) throws SQLException, InterruptedException {
        c.run();

    }
}
