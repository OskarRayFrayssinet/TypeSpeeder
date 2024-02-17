package se.ju23.typespeeder.utils;

import se.ju23.typespeeder.classer.Players;
import se.ju23.typespeeder.database.PlayersRepo;
import se.ju23.typespeeder.menu.Menu;
import se.ju23.typespeeder.service.PlayersService;

import java.util.Scanner;

public class InputOutput {
    public static Scanner input = new Scanner(System.in);
    public static Menu menu = new Menu();
    public static PlayersService playersService = new PlayersService();

    public static void login(PlayersRepo playersRepo, PlayersService playersService, Scanner input) {
        boolean runProgram = true;
        do {
            System.out.println("Enter your username: ");
            String answerUsername = input.nextLine();

            System.out.println("Enter corresponding password: ");
            String answerPassword = input.nextLine();

            Players foundPLayer = playersRepo.getPLayersByUsernameAndPassword(answerUsername, answerPassword);

            if (foundPLayer == null) {
                System.out.println("Player not found.");
                runProgram = false;

            } else {
                System.out.println("Welcome, " + foundPLayer.getNickname());
                System.out.println("Your current role is " + foundPLayer.getRole());
                menu.displayMenu();
                menu.handleMenuOption();
                runProgram = false;
            }
        } while (runProgram);

    }

}

