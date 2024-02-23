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
    private String exitMessage;
    private String logOutMenuChoice;
    private Player loggedInPlayer;
    private SystemIO systemIO;
    private PlayerRepo playerRepo;
    private DAOManager daoManager;

    public Menu() {
        this.systemIO = new SystemIO();
        setLanguageToSwedish();
    }

    public ArrayList<String> getMenuOptions() {
        return menuOptions;
    }
    public String displayMenu() {
        refreshPlayerInfo();
        for (String menuOption : menuOptions) {
            systemIO.addString("\n" + menuOption);
        }
        systemIO.addString("\n5. Välj språk (svenska/engelska):");
        systemIO.addString("\n" + logOutMenuChoice);
        systemIO.addString("\n>");
        String choice = systemIO.getString();

        if (choice.equals("svenska")) {
            choice = "Svenska valt.";
            systemIO.addString(choice);
            setLanguageToSwedish();
        } else if (choice.equals("engelska")) {
            choice = "English selected.";
            systemIO.addString(choice);
            setLanguageToEnglish();
        }
        return choice;
    }
    public void refreshPlayerInfo() {
        loggedInPlayer = daoManager.getPlayer();
    }
    public int chooseDifficulty() {
        int difficulty;
        do {
            systemIO.addString("Välj svårighetsgrad (1-10)\n>");
            difficulty = systemIO.readIntOnly();
        } while (difficulty > 11 && difficulty < 0);
        return difficulty;
    }
    public String postGameResults(double[] results) {
        return String.format("%s %.2f %s\n", "Accuracy:", results[0], "%.") +
                String.format("%s %.0f %s", "Longest streak:", results[1], "words.\n") +
                String.format("%s %.2f %s", "time:", results[2], " seconds.\n") +
                String.format("%s %.0f%n", "XP gain:", results[4]);
    }
    public void logInMenu() {
        systemIO.addString(logInMenuOptions.get(0));
        setLoggedInPlayer(verifyPlayer());

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
    public void settingsMenu() {
        systemIO.addString("""
                
                1. Ändra användarnamn
                2. Ändra visningsnamn
                3. Ändra lösenord
                4. Se din statistik
                """);
        if (loggedInPlayer.isAdmin()){
            systemIO.addString("""
                    5. Skapa ny patchnote
                    6. Skapa nytt nyhetsbrev
                    """);
        }
        systemIO.addString("0. Backa\n>");
    }
    public void patchNotesAndNewsMenu() {
        systemIO.addString("""
                1. Visa nyhetsbrev
                2. Visa Patch notes
                0. Backa
                >""");
    }
    public void continueOrExit() {
        systemIO.addString("""
                1. Fortsätt
                0. Spara och avsluta
                >""");
    }

    public Player getLoggedInPlayer() {
        return loggedInPlayer;
    }

    public void setLoggedInPlayer(Player loggedInPlayer) {
        this.loggedInPlayer = loggedInPlayer;
        daoManager.setPlayer(loggedInPlayer);
    }
    public void setSystemIO(SystemIO systemIO) {
        this.systemIO = systemIO;
    }
    public void setPlayerRepo(PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
    }

    public void setDaoManager(DAOManager daoManager) {
        this.daoManager = daoManager;
    }

    public String getExitMessage() {
        return exitMessage;
    }

    public void setExitMessage(String exitMessage) {
        this.exitMessage = exitMessage;
    }

    public void setLanguageToSwedish(){
        Challenge.setSwedish(true);
        menuOptions = new ArrayList<>();
        menuOptions.add("1. Spela");
        menuOptions.add("2. Visa rankningslista");
        menuOptions.add("3. Inställningar");
        menuOptions.add("4. Patch notes och nyheter");
        logOutMenuChoice = "0. Logga ut";

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

        exitMessage = "\nTack för att du spelade TypeSpeeder! Programmet avslutas...";
    }
    public void setLanguageToEnglish(){
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
    }
}
