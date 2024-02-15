package se.ju23.typespeeder.utils;

import se.ju23.typespeeder.classer.Players;
import se.ju23.typespeeder.database.PlayersRepo;
import se.ju23.typespeeder.enums.Language;
import se.ju23.typespeeder.menu.Menu;
import se.ju23.typespeeder.service.PlayersService;

import java.util.Scanner;

public class InputOutput {
    public static Scanner input = new Scanner(System.in);
    public static Menu menu = new Menu();
    public static PlayersService playersService = new PlayersService();

    public static void login (PlayersRepo playersRepo) {

            System.out.println("Enter your username: ");
            String answerUsername = input.nextLine();
            System.out.println("Enter corresponding password: ");
            String answerPassword = input.nextLine();

            Players foundPLayer = playersRepo.getPLayersByUsernameAndPassword(answerUsername, answerPassword);

            if (foundPLayer == null) {
                System.out.println("Player not found.");
                System.out.println("Do you want to create a new user, yes/no?");
                String answer = input.nextLine();
                if (!answer.equalsIgnoreCase("yes")) {
                    playersService.addNewPlayer(playersRepo, input);
                }
            } else {
                System.out.println("Welcome, " + foundPLayer.getNickname());
                System.out.println("Your current role is " + foundPLayer.getRole());
                menu.displayMenu();
            }
    }

}

