package se.ju23.typespeeder;

import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;

@Component
public class Runner implements CommandLineRunner {
    private Menu menu;

    @Autowired
    private PlayerRepo playerRepo;
    @Autowired
    public Runner(PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
        menu = new Menu(new SystemIO(), playerRepo);
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
        }
    }
}
