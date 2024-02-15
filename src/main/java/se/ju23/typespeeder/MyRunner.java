package se.ju23.typespeeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.database.PlayersRepo;
import se.ju23.typespeeder.database.ResultatRepo;
import se.ju23.typespeeder.service.PlayersService;
import se.ju23.typespeeder.utils.InputOutput;

import java.util.Scanner;

@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    PlayersRepo playersRepo;
    @Autowired
    ResultatRepo resultatRepo;
    Scanner input = new Scanner(System.in);
    @Override
    public void run(String... args) throws Exception {
        int userChoice = 0;
        do {
            System.out.println("""
            Please choose option below:
            1 - Login
            3 - Create
            2 - Exit Program""");
            userChoice = input.nextInt();
            input.nextLine();

            switch (userChoice) {
                case 1:
                    InputOutput.login(playersRepo);
                    break;
                case 2:
                    System.out.println("Thank you, goodbye");
                    break;
                case 3:
                    PlayersService playersService = new PlayersService();
                    playersService.addNewPlayer(playersRepo,input);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1 or 2.");
                    break;
            }
        } while (userChoice!=2);
        input.close();


    }
}
