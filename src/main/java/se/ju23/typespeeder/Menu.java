package se.ju23.typespeeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class Menu {
    private ArrayList<String> menuOptions;
    private Player loggedInPlayer;
    private SystemIO systemIO;
    private PlayerRepo playerRepo;

    public Menu(SystemIO systemIO, PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
        this.systemIO = systemIO;
        menuOptions = new ArrayList<>();
        menuOptions.add("1. Spela");
        menuOptions.add("2. Visa rankningslista");
        menuOptions.add("3. Inställningar");
        menuOptions.add("4. Patch notes och nyheter");
        menuOptions.add("5. Logga ut");
    }

    public ArrayList<String> getMenuOptions() {
        return menuOptions;
    }
    public void displayMenu() {
        for (String menuOption : menuOptions) {
            systemIO.addString("\n" + menuOption);
        }
    }
    public void logInMenu() {
        systemIO.addString("Ange ditt användarnamn\n>");
        loggedInPlayer = verifyPlayer();

        systemIO.addString("Ange ditt lösenord\n>");
        for (int i = 2; i != -1; i--) {
            if(verifyPassword()){
                systemIO.addString("Inloggad som användare: " + loggedInPlayer.getUserName());
                String welcomeBackText = loggedInPlayer.getGamesPlayed() > 0 ? " tillbaka" : "";
                systemIO.addString("\nVälkommen " + welcomeBackText + loggedInPlayer.getDisplayName() + "!");

                break;
            }
            System.out.println("Felaktigt lösenord. Du har " + i + " försök kvar.");
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
                systemIO.addString("Felaktigt användarid, försök igen. \nAnge användarid:\n>");
            }
        } while (player.isEmpty());
        return player.get();
    }

    public boolean verifyPassword() {
        String password = systemIO.getString();
        return loggedInPlayer.getPassword().equals(password);
    }

    public void startMenu() {
        systemIO.addString("""
                Ange siffra för motsvarande menyval
                1. Logga in
                0. Avsluta programmet
                >""");
        int menuChoice;
        do{
            switch (menuChoice = systemIO.readIntOnly()){
                case 0 -> systemIO.addString("Tack för att du spelade TypeSpeeder! Programmet avslutas...");
                case 1 -> logInMenu();
                default -> systemIO.addString("Felaktig inmatning, försök igen.\n>");
            }
        }while (menuChoice != 0 && menuChoice != 1);
    }

    public Player getLoggedInPlayer() {
        return loggedInPlayer;
    }

    public void changeLanguage() {
        if (menuOptions.get(0).equals("1. Spela")) {
            menuOptions = new ArrayList<>();
            menuOptions.add("1. Play");
            menuOptions.add("2. Show Scoreboard");
            menuOptions.add("3. Settings");
            menuOptions.add("4. Patch notes and news");
            menuOptions.add("5. log out");
        } else {
            menuOptions = new ArrayList<>();
            menuOptions.add("1. Spela");
            menuOptions.add("2. Visa rankningslista");
            menuOptions.add("3. Inställningar");
            menuOptions.add("4. Patch notes och nyheter");
            menuOptions.add("5. Logga ut");
        }
    }
}
