/*
 * Class: PlayerService
 * Description: A support class for the class Player.
 * Author: Kerem Bjävenäs Tazedal
 * Email: kerem.tazedal@iths.se
 * Date: 2024-02-18
 */
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

    @Autowired
    StatusService statusService;

    public static final String ANSI_DARK_GREY = "\u001B[90m";
    public static final String ANSI_RESET = "\u001B[0m";
    private Player activePlayer;
    public Status checkCredentials(PlayerRepo playerRepo, IO io) {
        System.out.print(io.getInGameMessages().get(36) + " / " +  io.getInGameMessages().get(38));
        String username = io.getValidStringInput();
        System.out.print(io.getInGameMessages().get(37) + " / " +  io.getInGameMessages().get(39));
        String password = io.getValidStringInput();

        boolean playerExists = playerRepo.existsPlayerByUsernameAndPassword(username, password);
        if (playerExists) {
            activePlayer = playerRepo.findPlayerByUsernameAndPassword(username, password);
            return Status.OK;
        } else {
            System.out.println(io.getInGameMessages().get(40) + " / " +  io.getInGameMessages().get(41));
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
        if (statusService.getStatus().equals(Status.SVENSKA)){
            System.out.print(io.getInGameMessages().get(8));
        } else if (statusService.getStatus().equals(Status.ENGLISH)){
            System.out.print(io.getInGameMessages().get(7));
        }
        String adminInputName = io.getString();
        player.setUsername(adminInputName);

        if (statusService.getStatus().equals(Status.SVENSKA)){
            System.out.print(io.getInGameMessages().get(9));
        } else if (statusService.getStatus().equals(Status.ENGLISH)){
            System.out.print(io.getInGameMessages().get(10));
        }
        String adminInputPassword = io.getString();
        player.setPassword(adminInputPassword);

        if (statusService.getStatus().equals(Status.SVENSKA)){
            System.out.print(io.getInGameMessages().get(11));
        } else if (statusService.getStatus().equals(Status.ENGLISH)){
            System.out.print(io.getInGameMessages().get(12));
        }
        String adminInputPlayerName = io.getString();
        player.setPlayerName(adminInputPlayerName);

        if (statusService.getStatus().equals(Status.SVENSKA)){
            System.out.print(io.getInGameMessages().get(14));
        } else if (statusService.getStatus().equals(Status.ENGLISH)) {
            System.out.print(io.getInGameMessages().get(13));
        }
        System.out.println(io.getInGameMessages().get(15));
        int editRole = io.getValidIntegerInput(io.returnScanner(), 0, 2);
        switch (editRole) {
            case 1 -> {
                player.setRole(Role.admin);
                player.setLevel(0);
                player.setPoints(0);
                playerRepo.save(player);
                if (statusService.getStatus().equals(Status.SVENSKA)){
                    System.out.print(io.getInGameMessages().get(16));
                    System.out.println(player);
                    System.out.println(" ");
                } else if (statusService.getStatus().equals(Status.ENGLISH)) {
                    System.out.print(io.getInGameMessages().get(17));
                    System.out.println(player);
                    System.out.println(" ");
                }
            }
            case 2 -> {
                player.setRole(Role.user);
                player.setLevel(0);
                player.setPoints(0);
                playerRepo.save(player);
                if (statusService.getStatus().equals(Status.SVENSKA)){
                    System.out.print(io.getInGameMessages().get(16));
                    System.out.println(player);
                    System.out.println(" ");
                } else if (statusService.getStatus().equals(Status.ENGLISH)) {
                    System.out.print(io.getInGameMessages().get(17));
                    System.out.println(player);
                    System.out.println(" ");
                }
            }
        }
    }

    public void editUser() {
        boolean runEditMenu = true;
        do {
            if (statusService.getStatus().equals(Status.SVENSKA))
                System.out.println(io.getInGameMessages().get(54));
            else if (statusService.getStatus().equals(Status.ENGLISH)){
                System.out.println(io.getInGameMessages().get(55));
            }
            System.out.println(playerRepo.findAll());
            System.out.println("");
            if (statusService.getStatus().equals(Status.SVENSKA)){
                System.out.println(io.getInGameMessages().get(60));
            } else if (statusService.getStatus().equals(Status.ENGLISH)){
                System.out.println(io.getInGameMessages().get(57));
            }
            int selectUserById = io.getValidIntegerInput(io.returnScanner(), 0, Integer.MAX_VALUE);
            if (selectUserById == 0) {
                return;
            } else {
                Optional<Player> playerOptional = playerRepo.findById(selectUserById);
                if (playerOptional.isPresent()) {
                    Player player = playerOptional.get();
                    if (statusService.getStatus().equals(Status.SVENSKA)) {
                        System.out.println(io.getInGameMessages().get(61));
                        System.out.println(io.getInGameMessages().get(62));
                    } else if (statusService.getStatus().equals(Status.ENGLISH)) {
                        System.out.println(io.getInGameMessages().get(57));
                        System.out.println(io.getInGameMessages().get(58));
                        System.out.println(io.getInGameMessages().get(59));
                    }
                    int selectMenuOption = io.getValidIntegerInput(io.returnScanner(), 0, 8);
                    switch (selectMenuOption) {
                        case 1 -> {
                            if (statusService.getStatus().equals(Status.SVENSKA)) {
                                System.out.print(io.getInGameMessages().get(36));
                            } else if (statusService.getStatus().equals(Status.ENGLISH)) {
                                System.out.print(io.getInGameMessages().get(38));
                            }
                            String adminInputName = io.getString();
                            player.setUsername(adminInputName);
                            playerRepo.save(player);
                        }
                        case 2 -> {
                            if (statusService.getStatus().equals(Status.SVENSKA)) {
                                System.out.println(io.getInGameMessages().get(37));
                            } else if (statusService.getStatus().equals(Status.ENGLISH)) {
                                System.out.println(io.getInGameMessages().get(39));
                            }
                            String adminInputPassword = io.getString();
                            player.setPassword(adminInputPassword);
                            playerRepo.save(player);
                        }
                        case 3 -> {
                            if (statusService.getStatus().equals(Status.SVENSKA)) {
                                System.out.println(io.getInGameMessages().get(11));
                            } else if (statusService.getStatus().equals(Status.ENGLISH)) {
                                System.out.println(io.getInGameMessages().get(12));
                            }
                            String adminInputPlayerName = io.getString();
                            player.setPlayerName(adminInputPlayerName);
                            playerRepo.save(player);
                        }

                        case 4 -> {
                            if (statusService.getStatus().equals(Status.SVENSKA)) {
                                System.out.println(io.getInGameMessages().get(65));
                            } else if (statusService.getStatus().equals(Status.ENGLISH)) {
                                System.out.println(io.getInGameMessages().get(66));
                            }
                            int adminInputPoints = io.getValidIntegerInput(io.returnScanner(), 0, Integer.MAX_VALUE);
                            player.setPoints(adminInputPoints);
                            playerRepo.save(player);
                        }
                        case 5 -> {
                            if (statusService.getStatus().equals(Status.SVENSKA)) {
                                System.out.println(io.getInGameMessages().get(67));
                            } else if (statusService.getStatus().equals(Status.ENGLISH)) {
                                System.out.println(io.getInGameMessages().get(68));
                            }
                            int adminInputLevel = io.getValidIntegerInput(io.returnScanner(), 0, Integer.MAX_VALUE);
                            player.setLevel(adminInputLevel);
                            playerRepo.save(player);
                        }
                        case 6 -> {
                            if (statusService.getStatus().equals(Status.SVENSKA)) {
                                System.out.println(io.getInGameMessages().get(14));
                            } else if (statusService.getStatus().equals(Status.ENGLISH)) {
                                System.out.println(io.getInGameMessages().get(13));
                            }
                            System.out.println(io.getInGameMessages().get(15));
                            int editRole = io.getValidIntegerInput(io.returnScanner(), 0, 2);
                            switch (editRole) {
                                case 1 -> {
                                    player.setRole(Role.admin);
                                    playerRepo.save(player);
                                    if (statusService.getStatus().equals(Status.SVENSKA)) {
                                        System.out.print(io.getInGameMessages().get(16));
                                    } else if (statusService.getStatus().equals(Status.ENGLISH)) {
                                        System.out.print(io.getInGameMessages().get(17));
                                    }
                                }
                                case 2 -> {
                                    player.setRole(Role.user);
                                    playerRepo.save(player);
                                    if (statusService.getStatus().equals(Status.SVENSKA)) {
                                        System.out.print(io.getInGameMessages().get(16));
                                    } else if (statusService.getStatus().equals(Status.ENGLISH)) {
                                        System.out.print(io.getInGameMessages().get(17));
                                    }
                                }
                            }
                        }
                        case 7 -> {
                            if (statusService.getStatus().equals(Status.SVENSKA)) {
                                System.out.println(io.getInGameMessages().get(69));
                            } else if (statusService.getStatus().equals(Status.ENGLISH)) {
                                System.out.println(io.getInGameMessages().get(70));
                            }
                            int deleteUserById = io.getValidIntegerInput(io.returnScanner(), 0, Integer.MAX_VALUE);
                            if (playerRepo.existsUserById(deleteUserById)) {
                                playerRepo.deleteById(deleteUserById);
                                if (statusService.getStatus().equals(Status.SVENSKA)) {
                                    System.out.println(io.getInGameMessages().get(71));
                                } else if (statusService.getStatus().equals(Status.ENGLISH)) {
                                    System.out.println(io.getInGameMessages().get(72));
                                }
                            } else {
                                if (statusService.getStatus().equals(Status.SVENSKA)) {
                                    System.out.println(io.getInGameMessages().get(73));
                                } else if (statusService.getStatus().equals(Status.ENGLISH)) {
                                    System.out.println(io.getInGameMessages().get(74));
                                }
                            }

                        }
                        case 8 -> runEditMenu = false;
                    }
                } else {
                    if (statusService.getStatus().equals(Status.SVENSKA)) {
                        System.out.println(io.getInGameMessages().get(73));
                    } else if (statusService.getStatus().equals(Status.ENGLISH)) {
                        System.out.println(io.getInGameMessages().get(74));
                    }
                    System.out.println("");
                }
            }
        } while (runEditMenu);
    }
}

