package se.ju23.typespeeder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.classer.Players;
import se.ju23.typespeeder.database.PlayersRepo;
import se.ju23.typespeeder.database.ResultatRepo;

import java.util.Scanner;

@Service
public class PlayersService {

    public static Scanner input = new Scanner(System.in);

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
}
