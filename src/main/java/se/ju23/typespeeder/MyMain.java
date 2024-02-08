package se.ju23.typespeeder;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Scanner;

@Component public class MyMain implements CommandLineRunner {
    @Override
    public void run(String... args) throws SQLException, InterruptedException {
        Scanner input = new Scanner(System.in);
        Playable tg = new TypeSpeederGamePlay();
        IO io = new InputOutput(input);
        Controllable c = new Controller(tg,io);
        c.run();

    }
}
