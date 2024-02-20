package se.ju23.typespeeder.service;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.IO.IO;
import se.ju23.typespeeder.enums.Status;
import se.ju23.typespeeder.model.Player;
import se.ju23.typespeeder.repository.PlayerRepo;
import java.util.Scanner;

@Service
public class PlayerService {

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
}

