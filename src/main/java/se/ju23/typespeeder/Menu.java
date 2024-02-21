package se.ju23.typespeeder;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class Menu implements MenuService {
    private ArrayList<String> menuOptions;
    private ArrayList<String> startMenuOptions;
    private ArrayList<String> logInMenuOptions;
    private String wrongUsernameMessage;
    private Player loggedInPlayer;
    private SystemIO systemIO;
    private PlayerRepo playerRepo;

    public Menu() {
        this.systemIO = new SystemIO();
        Challenge.setSwedish(true);
        menuOptions = new ArrayList<>();
        menuOptions.add("1. Spela");
        menuOptions.add("2. Visa rankningslista");
        menuOptions.add("3. Inställningar");
        menuOptions.add("4. Patch notes och nyheter");
        menuOptions.add("0. Logga ut\n>");

        startMenuOptions = new ArrayList<>();
        startMenuOptions.add("""
                Ange siffra för motsvarande menyval
                1. Logga in
                0. Avsluta programmet
                >""");
        startMenuOptions.add("Tack för att du spelade TypeSpeeder! Programmet avslutas...");
        startMenuOptions.add("Felaktig inmatning, försök igen.\n>");

        logInMenuOptions = new ArrayList<>();
        logInMenuOptions.add("Ange ditt användarnamn\n>");
        logInMenuOptions.add("Ange ditt lösenord\n>");
        logInMenuOptions.add("Inloggad som användare: ");
        logInMenuOptions.add(" tillbaka");
        logInMenuOptions.add("Välkommen ");
        logInMenuOptions.add("Felaktigt lösenord. Du har ");
        logInMenuOptions.add(" försök kvar.");

        wrongUsernameMessage = "Felaktigt användarid, försök igen. \nAnge användarid:\n>";


    }

    public ArrayList<String> getMenuOptions() {
        return menuOptions;
    }
    public void displayMenu() {
        for (String menuOption : menuOptions) {
            systemIO.addString("\n" + menuOption);
        }
    }
    public int chooseDifficulty() {
        int difficulty;
        do {
            systemIO.addString("Välj svårighetsgrad (1-10)\n>");
            difficulty = systemIO.readIntOnly();
        } while (difficulty > 11 && difficulty < 0);
        return difficulty;
    }
    public void logInMenu() {
        systemIO.addString(logInMenuOptions.get(0));
        loggedInPlayer = verifyPlayer();

        systemIO.addString(logInMenuOptions.get(1));
        for (int i = 2; i != -1; i--) {
            if(verifyPassword()){
                systemIO.addString(logInMenuOptions.get(2) + loggedInPlayer.getUserName());
                String welcomeBackText = loggedInPlayer.getGamesPlayed() > 0 ? logInMenuOptions.get(3) : "";
                systemIO.addString("\n" + logInMenuOptions.get(4) + welcomeBackText + loggedInPlayer.getDisplayName() + "!");

                break;
            }
            System.out.println(logInMenuOptions.get(5) + i + logInMenuOptions.get(6));
            if (i == 0){
                System.exit(0);
            }
        }
    }
    public Player verifyPlayer(){
        Optional<Player> player;
        do {
            String username = systemIO.getString();
            player = playerRepo.findByUserName(username);
            if(player.isEmpty()){
                systemIO.addString(wrongUsernameMessage);
            }
        } while (player.isEmpty());
        return player.get();
    }

    public boolean verifyPassword() {
        String password = systemIO.getString();
        return loggedInPlayer.getPassword().equals(password);
    }

    public void startMenu() {

        systemIO.addString(startMenuOptions.get(0));
        int menuChoice;
        do{
            switch (menuChoice = systemIO.readIntOnly()){
                case 0 -> {
                    systemIO.addString(startMenuOptions.get(1));
                    setLoggedInPlayer(null);
                }
                case 1 -> logInMenu();
                default -> systemIO.addString(startMenuOptions.get(2));
            }
        }while (menuChoice != 0 && menuChoice != 1);
    }
    public void printTypeSpeederAnsi() {
        systemIO.addString("""
                  ____                     ___                       _
                |_   _| _    _  __   ___ /  __/  __   ___    ___    | |   ___   ___
                  | |  \\ \\  / | . \\/ __ \\\\ \\__ | . \\/ __ \\ / __ \\   | | / __ \\|  . \\\s
                  | |   \\ \\/ /|  _/ /__\\| \\__ \\|  _/ /__\\// /__\\/ __| |/ /__\\||    /\s
                  | |    \\  / | | \\ \\__   __/ /| | \\ \\___ \\ \\___ / .  |\\ \\___ | |\\ \\
                  |_|    / /  |_|  \\__ / \\__ / |_|  \\___ / \\___ /\\___ | \\___ /|_| \\_\\
                        /_/
                """);
    }
    public void challengeCountDown() {
        systemIO.addString("Timern börjar när texten visas.\n");
        for (int i = 3; i > 0; i--) {
            systemIO.addString(i + "\n");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Player getLoggedInPlayer() {
        return loggedInPlayer;
    }

    public void setLoggedInPlayer(Player loggedInPlayer) {
        this.loggedInPlayer = loggedInPlayer;
    }
    public void setSystemIO(SystemIO systemIO) {
        this.systemIO = systemIO;
    }
    public void setPlayerRepo(PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
    }

    public void changeLanguage() {
        if (menuOptions.get(0).equals("1. Spela")) {
            Challenge.setSwedish(false);
            menuOptions = new ArrayList<>();
            menuOptions.add("1. Play");
            menuOptions.add("2. Show Scoreboard");
            menuOptions.add("3. Settings");
            menuOptions.add("4. Patch notes and news");
            menuOptions.add("5. log out");

            startMenuOptions = new ArrayList<>();
            startMenuOptions.add("""
                Enter number for the corresponding menu choice:
                1. Log in
                0. Exit
                >""");
            startMenuOptions.add("Thank you for playing TypeSpeeder! Exiting program...");
            startMenuOptions.add("Incorrect entry, try again.\n>");

            logInMenuOptions = new ArrayList<>();
            logInMenuOptions.add("Enter username\n>");
            logInMenuOptions.add("Enter password\n>");
            logInMenuOptions.add("Logged in as user: ");
            logInMenuOptions.add(" back");
            logInMenuOptions.add("Welcome ");
            logInMenuOptions.add("Incorrect password. You have ");
            logInMenuOptions.add(" attempts remaining.");

            wrongUsernameMessage = "Incorrect username, try again. \nEnter username:\n>";
        } else {
            Challenge.setSwedish(true);
            menuOptions = new ArrayList<>();
            menuOptions.add("1. Spela");
            menuOptions.add("2. Visa rankningslista");
            menuOptions.add("3. Inställningar");
            menuOptions.add("4. Patch notes och nyheter");
            menuOptions.add("5. Logga ut");

            startMenuOptions = new ArrayList<>();
            startMenuOptions.add("""
                Ange siffra för motsvarande menyval:
                1. Logga in
                0. Avsluta programmet
                >""");
            startMenuOptions.add("Tack för att du spelade TypeSpeeder! Programmet avslutas...");
            startMenuOptions.add("Felaktig inmatning, försök igen.\n>");

            logInMenuOptions = new ArrayList<>();
            logInMenuOptions.add("Ange ditt användarnamn\n>");
            logInMenuOptions.add("Ange ditt lösenord\n>");
            logInMenuOptions.add("Inloggad som användare: ");
            logInMenuOptions.add(" tillbaka");
            logInMenuOptions.add("Välkommen ");
            logInMenuOptions.add("Felaktigt lösenord. Du har ");
            logInMenuOptions.add(" försök kvar.");

            wrongUsernameMessage = "Felaktigt användarid, försök igen. \nAnge användarid:\n>";
        }
    }
}
