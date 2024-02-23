/*
 * Class: Menu
 * Description: A class that implements MenuService and handles the menu for the application.
 * Created by: Kerem Bjävenäs Tazedal
 * Email: kerem.tazedal@iths.se
 * Date: 2024-02-13
 */
package se.ju23.typespeeder.IO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.enums.Status;
import se.ju23.typespeeder.service.StatusService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@Service
public class Menu implements MenuService {
    private ArrayList<String> menuOptions;
    public static final String ANSI_DARK_BLUE = "\u001B[34m";
    public static final String ANSI_DARK_GREY = "\u001B[90m";
    public static final String ANSI_RESET = "\u001B[0m";

    @Qualifier("gameIO")
    @Autowired
    IO io;

    @Autowired
    StatusService statusService;


    public Menu() {
        this.menuOptions = new ArrayList<>();
        menuOptions.add(ANSI_DARK_GREY + "Play game" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "Display leaderboards" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "News from devs" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_BLUE + "Edit users" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "Logout" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "Quit game" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "Login" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "Spela spelet" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "Visa rankinglistan" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "Nyheter from utvecklarna" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_BLUE + "Redigera spelare" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "Logga ut" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "Avsluta spel" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "Visa nyhetsbrev" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_BLUE + "Redigera nyhetsbrev" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "Läs patchnyheter" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_BLUE + "Redigera patchnyheter" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "Avsluta" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "Display newsletter" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_BLUE + "Edit newsletter" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "Read patch news" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_BLUE + "Edit patch news" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "Exit" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "Lägg till spelare" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "Redigera spelare" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "Add player" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "Edit player" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "Logga in" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "\nVälj språk / Choose language" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "Skriv in ett för 1 för svenska, 2 för engelska." + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "Please type in 1 for swedish, 2 for english" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "Vänlingen välj ett av följande alternativ genom att ange nummer: "+ ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "Please select one of the following options by entering the number:  " + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "Vänligen välj från följande alternativ: " + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "1. Lätt TypeSpeeder" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "2. Svår TypeSpeeder" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "3. Tungvrickare" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "4. TypeSpeeder med färgutmaning" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "Please select from the following options: " + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "1. Easy TypeSpeeder" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "2. Hard TypeSpeeder" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "3. Tounge Twisters" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "4. TypeSpeeder with Color Challenge" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "Player Ranking --> Högsta level" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "Player Ranking --> Högsta poäng" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "Player Ranking --> Highest Level" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "Player Ranking --> Highest Points" + ANSI_RESET);

    }


    public List getMenuOptions() {
        return menuOptions;
    }


    public void displayLoginMenu() {
        if (statusService.getStatus().equals(Status.SVENSKA)){
            System.out.println(io.getInGameMessages().get(18));
            System.out.println("1. " + getMenuOptions().get(27));
            System.out.println("2. " + getMenuOptions().get(12));
            System.out.print(io.getInGameMessages().get(21));
        } else if (statusService.getStatus().equals(Status.ENGLISH)){
            System.out.println(io.getInGameMessages().get(19));
            System.out.println("1. " + getMenuOptions().get(6));
            System.out.println("2. " + getMenuOptions().get(5));
            System.out.print(io.getInGameMessages().get(20));
        }
        int userInput = io.getValidIntegerInput(io.returnScanner(), 1, 2);
        switch (userInput) {
            case 1 -> System.out.println();
            case 2 -> System.exit(0);
        }
    }

    @Override
    public void displayMenu() {
        System.out.print(ANSI_DARK_GREY + "\nVälj språk (svenska/engelska):" + ANSI_RESET);
        System.out.print(ANSI_DARK_GREY + "--> " + ANSI_RESET);
        System.out.print(ANSI_DARK_GREY + "Svenska valt." + ANSI_RESET);
        System.out.println(" ");
        int menuNumber = 1;
        LinkedList<String> tempmenuOptions = new LinkedList<>(getMenuOptions());
        for (int i = 0; i < tempmenuOptions.size(); i++) {
            if ((i >= 7) && (i <= 12)) {
                System.out.println(menuNumber + ". " + tempmenuOptions.get(i));
                menuNumber++;
            }
        }
    }

    public Status displayMenuEnglish() {
        System.out.print(io.getInGameMessages().get(32));
        System.out.print(io.getInGameMessages().get(35));
        System.out.print(io.getInGameMessages().get(34));
        System.out.println(" ");
        int menuNumber = 1;
        LinkedList<String> tempmenuOptions = new LinkedList<>(getMenuOptions());
        for (String options : tempmenuOptions) {
            System.out.println(menuNumber + ". " + options);
            menuNumber++;
            if (menuNumber == 7) {
                break;
            }
        }
        return statusService.setStatus(Status.ENGLISH);
    }


    public void setGameLanguage() {
        statusService.setStatus(Status.OK);
        System.out.println(getMenuOptions().get(28));
        System.out.println(getMenuOptions().get(29));
        System.out.println(getMenuOptions().get(30));
        System.out.print("-> ");
        int userInput = io.getValidIntegerInput(io.returnScanner(), 1, 2);
        switch (userInput) {
            case 1 -> statusService.setStatus(Status.SVENSKA);
            case 2 -> statusService.setStatus(Status.ENGLISH);
        }
    }

    public int selectMenuOptions() {
        if (statusService.getStatus().equals(Status.SVENSKA)) {
            System.out.println(getMenuOptions().get(31));
        } else if (statusService.getStatus().equals(Status.ENGLISH)) {
            System.out.println(getMenuOptions().get(32));
        }
        return io.getValidIntegerInput(io.returnScanner(), 1, 6);
    }

    public int selectNewsOptions() {
        if (statusService.getStatus().equals(Status.SVENSKA)) {
            System.out.println(getMenuOptions().get(31));
        } else if (statusService.getStatus().equals(Status.ENGLISH)) {
            System.out.println(getMenuOptions().get(32));
        }
        return io.getValidIntegerInput(io.returnScanner(), 1, 5);
    }

    public void displayNewsMenu() {
        int menuNumber = 1;
        LinkedList<String> tempmenuOptions = new LinkedList<>(getMenuOptions());
        if (statusService.getStatus().equals(Status.SVENSKA)) {
            for (int i = 0; i < tempmenuOptions.size(); i++) {
                if ((i >= 13) && (i <= 17)) {
                    System.out.println(menuNumber + ". " + tempmenuOptions.get(i));
                    menuNumber++;
                }
            }
        } else if (statusService.getStatus().equals(Status.ENGLISH)) {
            for (int i = 0; i < tempmenuOptions.size(); i++) {
                if ((i >= 18) && (i <= 22)) {
                    System.out.println(menuNumber + ". " + tempmenuOptions.get(i));
                    menuNumber++;
                }
            }

        }

    }

    public int selectEditPlayerMenuOptions() {
        if (statusService.getStatus().equals(Status.SVENSKA)) {
            System.out.println(getMenuOptions().get(31));
        } else if (statusService.getStatus().equals(Status.ENGLISH)) {
            System.out.println(getMenuOptions().get(32));
        }
        return io.getValidIntegerInput(io.returnScanner(), 1, 3);
    }

    public void displayEditPlayersMenu() {
        int menuNumber = 1;
        LinkedList<String> tempmenuOptions = new LinkedList<>(getMenuOptions());
        if (statusService.getStatus().equals(Status.SVENSKA)) {
            for (int i = 0; i < tempmenuOptions.size(); i++) {
                if ((i >= 23) && (i <= 24)) {
                    System.out.println(menuNumber + ". " + tempmenuOptions.get(i));

                    menuNumber++;
                }
            }
            System.out.println("3. " + getMenuOptions().get(17));
        } else if (statusService.getStatus().equals(Status.ENGLISH)) {
            for (int i = 0; i < tempmenuOptions.size(); i++) {
                if ((i >= 25) && (i <= 26)) {
                    System.out.println(menuNumber + ". " + tempmenuOptions.get(i));
                    menuNumber++;
                }
            }
            System.out.println("3. " + getMenuOptions().get(22));
        }

    }

    public int selectLeaderboardMenu() {
        if (statusService.getStatus().equals(Status.SVENSKA)) {
            System.out.println(getMenuOptions().get(31));
        } else if (statusService.getStatus().equals(Status.ENGLISH)) {
            System.out.println(getMenuOptions().get(32));
        }
        return io.getValidIntegerInput(io.returnScanner(), 0, 3);
    }

    public void displayLeaderboardMenu() {
        int menuNumber = 1;
        LinkedList<String> tempmenuOptions = new LinkedList<>(getMenuOptions());
        if (statusService.getStatus().equals(Status.SVENSKA)) {
            for (int i = 0; i < tempmenuOptions.size(); i++) {
                if ((i >= 43) && (i <= 44)) {
                    System.out.println(menuNumber + ". " + tempmenuOptions.get(i));

                    menuNumber++;
                }
            }
            System.out.println("3. " + getMenuOptions().get(17));
        } else if (statusService.getStatus().equals(Status.ENGLISH)) {
            for (int i = 0; i < tempmenuOptions.size(); i++) {
                if ((i >= 45) && (i <= 46)) {
                    System.out.println(menuNumber + ". " + tempmenuOptions.get(i));
                    menuNumber++;
                }
            }
            System.out.println("3. " + getMenuOptions().get(22));
        }

    }

}

