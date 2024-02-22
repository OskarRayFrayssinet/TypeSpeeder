package se.ju23.typespeeder.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.IO.IO;
import se.ju23.typespeeder.IO.Menu;
import se.ju23.typespeeder.enums.Role;
import se.ju23.typespeeder.enums.Status;
import se.ju23.typespeeder.model.Player;
import se.ju23.typespeeder.repository.PlayerRepo;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static se.ju23.typespeeder.enums.Role.user;

@Service
public class PlayerService {

    @Autowired
    PlayerRepo playerRepo;

    @Qualifier("gameIO")
    @Autowired
    IO io;

    @Autowired
    Menu menu;
    public static final String ANSI_DARK_GREY = "\u001B[90m";
    public static final String ANSI_RESET = "\u001B[0m";
    private Player activePlayer;
    public Status checkCredentials(PlayerRepo playerRepo, IO io) {
        System.out.print(ANSI_DARK_GREY + "Please enter a username -> " + ANSI_RESET);
        String username = io.getValidStringInput();
        System.out.print(ANSI_DARK_GREY + "Please enter a password -> " + ANSI_RESET);
        String password = io.getValidStringInput();

        boolean playerExists = playerRepo.existsPlayerByUsernameAndPassword(username, password);
        if (playerExists) {
            activePlayer = playerRepo.findPlayerByUsernameAndPassword(username, password);
            return Status.OK;
        } else {
            System.out.println("Wrong username or password.");
            return Status.ERROR;
        }
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public void calculatePlayerLevel(PlayerRepo playerRepo) {
        int totalPoints = activePlayer.getPoints();
        if (totalPoints > 10){
            int currentLevel = activePlayer.getLevel();
            currentLevel++;
            activePlayer.setLevel(currentLevel);
            playerRepo.save(activePlayer);
            int totalPointsReset =- activePlayer.getPoints();
            activePlayer.setPoints(totalPointsReset);
            playerRepo.save(activePlayer);
        }
    }

    public void addNewPlayer() {
        Player player = new Player();
        if (menu.getStatus().equals(Status.SVENSKA)){
            System.out.println(io.getInGameMessages().get(8));
        } else if (menu.getStatus().equals(Status.ENGLISH)){
            System.out.println(io.getInGameMessages().get(7));
        }
        String adminInputName = io.getString();
        player.setUsername(adminInputName);

        if (menu.getStatus().equals(Status.SVENSKA)){
            System.out.println(io.getInGameMessages().get(9));
        } else if (menu.getStatus().equals(Status.ENGLISH)){
            System.out.println(io.getInGameMessages().get(10));
        }
        String adminInputPassword = io.getString();
        player.setPassword(adminInputPassword);

        if (menu.getStatus().equals(Status.SVENSKA)){
            System.out.println(io.getInGameMessages().get(11));
        } else if (menu.getStatus().equals(Status.ENGLISH)){
            System.out.println(io.getInGameMessages().get(12));
        }
        String adminInputPlayerName = io.getString();
        player.setPlayerName(adminInputPlayerName);

        if (menu.getStatus().equals(Status.SVENSKA)){
            System.out.println(io.getInGameMessages().get(14));
        } else if (menu.getStatus().equals(Status.ENGLISH)) {
            System.out.println(io.getInGameMessages().get(13));
        }
        System.out.println(menu.getMenuOptions().get(15));
        int editRole = io.getValidIntegerInput(io.returnScanner(), 0, 2);
        switch (editRole) {
            case 1 -> {
                player.setRole(Role.admin);
                player.setLevel(0);
                player.setPoints(0);
                playerRepo.save(player);
                if (menu.getStatus().equals(Status.SVENSKA)){
                    System.out.println(io.getInGameMessages().get(16));
                    System.out.println(player);
                } else if (menu.getStatus().equals(Status.ENGLISH)) {
                    System.out.println(io.getInGameMessages().get(17));
                    System.out.println(player);
                }
            }
            case 2 -> {
                player.setRole(Role.user);
                player.setLevel(0);
                player.setPoints(0);
                playerRepo.save(player);
                if (menu.getStatus().equals(Status.SVENSKA)){
                    System.out.println(io.getInGameMessages().get(16));
                    System.out.println(player);
                } else if (menu.getStatus().equals(Status.ENGLISH)) {
                    System.out.println(io.getInGameMessages().get(17));
                    System.out.println(player);
                }
            }
        }
    }

    public void editUser() {
        boolean runEditMenu = true;
        do {
            System.out.println("Welcome to the menu for editing user, please select a user id from the list below to begin editing:");
            System.out.println(playerRepo.findAll());
            System.out.println("");
            System.out.print("Please enter the id of the specific user to begin editing\n" +
                    "Enter '0' to exit: ");
            int selectUserById = io.getValidIntegerInput(io.returnScanner(), 0, Integer.MAX_VALUE);
            if (selectUserById == 0) {
                return;
            } else {

                Optional<Player> playerOptional = playerRepo.findById(selectUserById);
                if (playerOptional.isPresent()) {
                    Player player = playerOptional.get();
                    System.out.println("Please choose from the following options to edit: ");
                    System.out.println("1. Edit name." + "\n2. Edit department" + "\n3. Edit email." + "\n4. Edit telephone number" +
                            "\n5. Edit username" + "\n6. Edit password." + "\n7. Edit role." + "\n8. Remove user." + "\n9. Quit menu.");
                    System.out.print("Please choose a option by entering the menu number: ");
                    int selectMenuOption = io.getValidIntegerInput(io.returnScanner(), 0, 9);
                    switch (selectMenuOption) {
                        case 1 -> {
                            System.out.println("Enter the new name: ");
                            String adminInputName = io.getString();
                            player.setUsername(adminInputName);
                            playerRepo.save(player);
                        }
                        case 2 -> {
                            System.out.println("Enter the new department: ");
                            String adminInputPassword = io.getString();
                            player.setPassword(adminInputPassword);
                            playerRepo.save(player);
                        }
                        case 3 -> {
                            System.out.println("Enter the new email: ");
                            String adminInputPlayerName = io.getString();
                            player.setPlayerName(adminInputPlayerName);
                        }
                        case 4 -> {
                            System.out.println("Enter the new telephone number: ");
                            String adminInputPlayerName = io.getString();
                            player.setPlayerName(adminInputPlayerName);
                        }
                        case 5 -> {
                            System.out.println("Enter the new username: ");
                            int adminInputPoints = io.getValidIntegerInput(io.returnScanner(), 0,  Integer.MAX_VALUE);
                            player.setPoints(adminInputPoints);
                        }
                        case 6 -> {
                            System.out.println("Enter the new password: ");
                            int adminInputLevel = io.getValidIntegerInput(io.returnScanner(), 0,  Integer.MAX_VALUE);
                            player.setLevel(adminInputLevel);
                        }
                        case 7 -> {
                            System.out.println("Please select the new user role from the following numerical options:" +
                                    "\n1. Admin" +
                                    "\n2. Superuser" +
                                    "\n3. User");
                            int editRole = io.getValidIntegerInput(io.returnScanner(), 0, 2);
                            switch (editRole) {
                                case 1 -> {
                                    player.setRole(Role.admin);
                                    playerRepo.save(player);
                                }
                                case 2 -> {
                                    player.setRole(Role.user);
                                    playerRepo.save(player);
                                }
                            }
                        }
                        case 8 -> {
                            System.out.println("Please enter the id of the user you want to remove: ");
                            int deleteUserById = io.getValidIntegerInput(io.returnScanner(), 0, Integer.MAX_VALUE);
                            if (playerRepo.existsUserById(deleteUserById)) {
                                    playerRepo.deleteById(deleteUserById);
                                    System.out.println("User has been successfully deleted");
                            } else {
                                System.out.println("No deletion occurred, user ID not found.");
                            }

                        }
                        case 9 -> runEditMenu = false;
                    }
                } else {
                    System.out.println("No user with the selected ID found.");
                    System.out.println("");
                }
            }
        } while (runEditMenu);
    }
}

