package se.ju23.typespeeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.database.PlayersRepo;
import se.ju23.typespeeder.database.ResultatRepo;
import se.ju23.typespeeder.menu.Menu;
import se.ju23.typespeeder.classer.PlayersService;


import java.util.Scanner;

@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    public static PlayersRepo playersRepo;
    @Autowired
    public static ResultatRepo resultatRepo;

    @Autowired
    public MyRunner(PlayersRepo playersRepo, ResultatRepo resultatRepo) {
        this.playersRepo = playersRepo;
        this.resultatRepo = resultatRepo;
    }

    Scanner input = new Scanner(System.in);
    PlayersService playersService = new PlayersService();
    Menu menu = new Menu();

    @Override
    public void run(String... args) throws Exception {
        boolean exitProgram = false;

        do {
            System.out.println("""
                    Please choose option below:
                    1 - Login
                    2 - Exit Program""");
            int userChoice = input.nextInt();
            input.nextLine();

            switch (userChoice) {
                case 1:
                    menu.login(playersRepo, playersService, input);
                    break;
                case 2:
                    System.out.println("HEJ DÅ");
                    exitProgram = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, or 2.");
                    break;
            }
        } while (exitProgram);
    }
}
