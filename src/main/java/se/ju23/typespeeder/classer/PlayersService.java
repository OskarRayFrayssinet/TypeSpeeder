package se.ju23.typespeeder.classer;

import org.springframework.stereotype.Service;
import se.ju23.typespeeder.database.Players;
import se.ju23.typespeeder.database.PlayersRepo;
import se.ju23.typespeeder.database.Resultat;
import se.ju23.typespeeder.database.ResultatRepo;

import java.sql.SQLOutput;
import java.util.Comparator;
import java.util.List;
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

        do {
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

    public void deletePlayer(PlayersRepo playersRepo, Scanner scanner) {
        boolean carryOn = true;

        do {
            System.out.println("Här kan du radera en spelare");
            System.out.println("Nedan ser du en lista med nuvarande spelare");
            List<Players> playersList = playersRepo.findAll();
            for (Players players : playersList) {
                System.out.println(players.getNickname() + " " + players.getId());
            }
            System.out.println("Ange spelar-id eller nickname som ska tas bort");
            String choice = scanner.nextLine();

            if (choice.equals("0")) {
                carryOn = false;
                continue;
            }
            try {
                int playerId = Integer.parseInt(choice);
                playersRepo.deleteById(playerId);
                System.out.println("Spelare med id " + playerId + "tagits bort");
            } catch (NumberFormatException e) {
                Players playerToDelete = playersRepo.findByNickname(choice);
                if (playerToDelete != null) {
                    playersRepo.delete(playerToDelete);
                    System.out.println("Spelare med nickname " + choice + "tagits bort");
                } else {
                    System.out.println("Inte hittats");
                }
            }

        } while (carryOn);

    }

    public void printYourStatistics(PlayersRepo playersRepo, ResultatRepo resultatRepo, Players players, Scanner scanner) {
        if (players != null) {
            System.out.println("Here are the results for " + players.getNickname());
            Players foundPlayer = playersRepo.findByNickname(players.getNickname());
            if (foundPlayer != null) {
                List<Resultat> resultatList = foundPlayer.getResultat();
                if (resultatList != null && !resultatList.isEmpty()) {
                    int totalMistakes = 0;
                    long totalTime = 0;
                    int totalResult = 0;

                    for (Resultat resultat : resultatList) {
                        totalMistakes += resultat.getMistakes();
                        totalTime += resultat.getTime();
                        totalResult += resultat.getResultat();
                    }

                    double averageResult = totalResult / (double) resultatList.size();
                    double averageTime = totalTime / (double) resultatList.size();

                    System.out.println("Aggregate Results for " + players.getNickname() + ":");
                    System.out.println("Total Mistakes: " + totalMistakes);
                    System.out.println("Average Time: " + averageTime);
                    System.out.println("Average Result: " + averageResult);

                } else {
                    System.out.println("No results found");
                }
            } else {
                System.out.println("Player not found");
            }
        } else {
            System.out.println("No player object provided");
        }
    }

    public void printOverallStatistics(PlayersRepo playersRepo, ResultatRepo resultatRepo, Players players, Scanner scanner) {
        List<Players> allPlayers = playersRepo.findAll();


        if (!allPlayers.isEmpty()) {
            allPlayers.sort(Comparator.comparingDouble(this::calculateAverageResult).reversed());

            System.out.println("==========================================");
            System.out.println("Below is the current leaderboard according to average result:");

            for (Players player : allPlayers) {
                System.out.println("=============================================");
                System.out.println("Player: " + player.getNickname());

                List<Resultat> resultatList = player.getResultat();
                if (resultatList != null && !resultatList.isEmpty()) {
                    int totalMistakes = 0;
                    long totalTime = 0;
                    int totalResult = 0;
                    int level = 0;

                    for (Resultat resultat : resultatList) {
                        totalMistakes += resultat.getMistakes();
                        totalTime += resultat.getTime();
                        totalResult += resultat.getResultat();
                        level = players.getLevel();
                    }
                    double averageResult = totalResult / (double) resultatList.size();
                    double averageTime = totalTime / (double) resultatList.size();

                    System.out.println("Total Mistakes: " + totalMistakes);
                    System.out.println("Average Time: " + averageTime);
                    System.out.println("Average Result: " + averageResult);
                    System.out.println("Current level: " + level);
                    System.out.println("Level up possible when reaching 10 points");
                } else {
                    System.out.println("No results found");
                }
            }
        } else {
            System.out.println("No players found");
        }
    }

    private double calculateAverageResult(Players player) {
        List<Resultat> resultatList = player.getResultat();
        if (resultatList != null && !resultatList.isEmpty()) {
            int totalResult = 0;
            for (Resultat resultat : resultatList) {
                totalResult += resultat.getResultat();
            }
            return totalResult / (double) resultatList.size();
        }
        return 0.0;
    }

    private void levelUp(PlayersRepo playersRepo, ResultatRepo resultatRepo, Scanner scanner) {
        List<Players> allPLayers = playersRepo.findAll();

        for (Players player : allPLayers) {
            List<Resultat> resultatList = player.getResultat();
            if(resultatList != null && !resultatList.isEmpty()) {
                int totalResultat = 0;
                for (Resultat resultat : resultatList) {
                    totalResultat += resultat.getResultat();
                    double averageResultat = totalResultat / (double) resultatList.size();

                    if(averageResultat > 10) {
                        int newLevel = player.getLevel() + 1;
                        player.setLevel(newLevel);
                        playersRepo.save(player);
                        System.out.println("Player " + player.getNickname() + " leveled up to " + newLevel);
                    }
                }
            }
        }
    }
}


