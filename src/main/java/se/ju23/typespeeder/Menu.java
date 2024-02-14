package se.ju23.typespeeder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.IO.IO;
import se.ju23.typespeeder.logic.GameController;
import se.ju23.typespeeder.logic.TypingGame;

import java.util.ArrayList;


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
    TypingGame typingGame;

    public Menu() {
        this.menuOptions = new ArrayList<>();
        menuOptions.add(ANSI_DARK_GREY + "Play game" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "Display leaderboards" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "News from devs" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_BLUE + "Edit users" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "Logout" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "Quit game" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "Login" + ANSI_RESET);
    }

    public ArrayList<String> getMenuOptions() {
        return menuOptions;
    }

    public void setMenuOptions(ArrayList<String> menuOptions) {
    }

    public void displayLoginMenu() {
        System.out.println(ANSI_DARK_GREY + "Welcome to the Type Speeder Game!  " + ANSI_RESET);
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
    public void displayMenu() {
        int menuNumber = 1;
        ArrayList<String> tempmenuOptions = new ArrayList<>(getMenuOptions());
        tempmenuOptions.remove(6);
        for (String options : tempmenuOptions) {
            System.out.println(menuNumber + ". " + options);
            menuNumber++;
        }
    }

    public int selectMenuOptions() {
        System.out.println(ANSI_DARK_GREY + "Please select one of the following options by entering the number:  " + ANSI_RESET);
        int userInput = io.getValidIntegerInput(io.returnScanner(), 1, 6);
        return userInput;
        }
    }

