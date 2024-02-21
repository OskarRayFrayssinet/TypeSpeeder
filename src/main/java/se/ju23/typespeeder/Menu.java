package se.ju23.typespeeder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.IO.IO;
import se.ju23.typespeeder.enums.Status;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@Service
public class Menu implements MenuService {
    private ArrayList<String> menuOptions;
    public static final String ANSI_DARK_BLUE = "\u001B[34m";
    public static final String ANSI_DARK_GREY = "\u001B[90m";
    public static final String ANSI_RESET = "\u001B[0m";
    private Status status;

    @Qualifier("gameIO")
    @Autowired
    IO io;


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
        menuOptions.add(ANSI_DARK_GREY + "Redigera spelare" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "Logga ut" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "Avsluta spel" + ANSI_RESET);
    }


    public List getMenuOptions() {
        return menuOptions;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public Status setStatus(Status status) {
        this.status = status;
        return status;
    }

    public void displayLoginMenu() {
        System.out.println(ANSI_DARK_GREY + "\nWelcome to the Type Speeder Game!  " + ANSI_RESET);
        System.out.println("1. " + getMenuOptions().get(6));
        System.out.println("2. " + getMenuOptions().get(5));
        System.out.print(ANSI_DARK_GREY + "Please select one of the following options by entering the number -> " + ANSI_RESET);
        int userInput = io.getValidIntegerInput(io.returnScanner(), 1, 2);
        switch (userInput) {
            case 1 -> System.out.println();
            case 2 -> System.exit(0);
        }
    }

    @Override
    public Status displayMenu(){
        System.out.println("Välj språk (svenska/engelska):");
        System.out.println("Svenska valt.");
        int menuNumber = 1;
        LinkedList<String> tempmenuOptions = new LinkedList<>(getMenuOptions());
        for (int i = 0; i < tempmenuOptions.size(); i++) {
            if ((i >= 7) && (i <= 12)) {
                System.out.println(menuNumber + ". " + tempmenuOptions.get(i));
                menuNumber++;
            }
        }
        return setStatus(Status.SVENSKA);
    }

    public Status displayMenuEnglish() {
        System.out.println("English chosen");
        int menuNumber = 1;
        LinkedList<String> tempmenuOptions = new LinkedList<>(getMenuOptions());
        for (String options : tempmenuOptions) {
                System.out.println(menuNumber + ". " + options);
                menuNumber++;
                if (menuNumber == 7) {
                    break;
                }
            }
        return setStatus(Status.ENGLISH);
    }


    public int setMenuInput() {
        System.out.println(ANSI_DARK_GREY + "\nVälj språk / Choose language" + ANSI_RESET);
        System.out.println(ANSI_DARK_GREY + "Skriv in ett för 1 för svenska, 2 för engelska." + ANSI_RESET);
        System.out.println(ANSI_DARK_GREY + "Please type in 1 for swedish, 2 for english" + ANSI_RESET);
        System.out.print("-> ");
        int userInput = io.getValidIntegerInput(io.returnScanner(), 1, 2);
        return userInput;
    }

    public int selectMenuOptions() {
        if (getStatus().equals(Status.SVENSKA)) {
            System.out.println("Vänlingen välj ett av följande alternativ genom att ange nummer: ");
        } else if (getStatus().equals(Status.ENGLISH)) {
            System.out.println(ANSI_DARK_GREY + "Please select one of the following options by entering the number:  " + ANSI_RESET);
        }
        return io.getValidIntegerInput(io.returnScanner(), 1, 6);
    }

    public int selectNewsOptions() {
        if (getStatus().equals(Status.SVENSKA)) {
            System.out.println("Vänlingen välj ett av följande alternativ genom att ange nummer: ");
        } else if (getStatus().equals(Status.ENGLISH)) {
            System.out.println(ANSI_DARK_GREY + "Please select one of the following options by entering the number:  " + ANSI_RESET);
        }
        return io.getValidIntegerInput(io.returnScanner(), 1, 2);
    }

}

