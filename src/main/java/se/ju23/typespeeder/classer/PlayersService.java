package se.ju23.typespeeder.classer;

import org.springframework.stereotype.Service;
import se.ju23.typespeeder.database.Players;
import se.ju23.typespeeder.database.PlayersRepo;

import java.util.Scanner;

@Service
public class PlayersService {

       public void addNewPlayer(PlayersRepo playersRepo, Scanner input) {

        Players players = new Players();

        System.out.println("Enter the nickname");
        String inputNickname = input.nextLine();
        players.setNickname(inputNickname);

        System.out.println("Enter username");
        String inputUsername = input.nextLine();
        players.setUsername(inputUsername);

        System.out.println("Enter password");
        String inputPassword = input.nextLine();
        players.setPassword(inputPassword);

        System.out.println("You initial level will be set to 1 and the role as user");
        players.setLevel(1);
        players.setRole("user");
        playersRepo.save(players);

    }
    public void updatePlayer(PlayersRepo playersRepo, Scanner input) {
        boolean carryOn = true;

        do{
            System.out.println("Här kan du uppdatera dina upggifter");
            System.out.println("Ange den info som du vill uppdatera");
            System.out.println("1. Ändra nickname." + "\n2. Ändra username" + "\n3. Lämna menu.");
            System.out.print("Please choose a option by entering the menu number: ");

            int option = input.nextInt();
            input.nextLine();
            switch (option) {
                case 1 -> {

                    System.out.println("What nickname do you want to change");
                    String oldNickname = input.nextLine();
                    Players players = playersRepo.findByNickname(oldNickname);
                    System.out.println("Player " + oldNickname + " har hittats");

                    System.out.println("Ange nytt nickname: ");
                    String newNickname = input.nextLine();

                    players.setNickname(newNickname);
                    playersRepo.save(players);
                }
                case 2 -> {

                    System.out.println("What username do you want to change");
                    String oldUsername = input.nextLine();
                    Players players = playersRepo.findByUsername(oldUsername);
                    System.out.println("Player " + oldUsername + " har hittats");

                    System.out.println("Ange nytt username: ");
                    String newUsername = input.nextLine();

                    players.setUsername(newUsername);
                    playersRepo.save(players);
                }
                case 3 -> carryOn = false;

            }

        } while (carryOn);


    }

}
