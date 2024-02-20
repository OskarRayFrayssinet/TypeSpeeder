package se.ju23.typespeeder;

import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;

@Component
public class Runner implements CommandLineRunner {
    private Menu menu;
    private SystemIO systemIO = new SystemIO();
    Challenge challenge = new Challenge();

    @Autowired
    private PlayerRepo playerRepo;
    @Autowired
    public Runner(PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
        menu = new Menu();
        menu.setSystemIO(systemIO);
        menu.setPlayerRepo(playerRepo);
    }

    @Override
    public void run(String... args) throws Exception {
        while(true) {
            menu.printTypeSpeederAnsi();
            menu.startMenu();

            if (menu.getLoggedInPlayer() == null) {
                return;
            }

            menu.displayMenu();

            switch (systemIO.getString()) {
                case "0" -> {
                    return;
                }
                case "1" -> {
                    menu.challengeCountDown();
                    challenge.startChallenge();
                }
            }
        }
    }
}
