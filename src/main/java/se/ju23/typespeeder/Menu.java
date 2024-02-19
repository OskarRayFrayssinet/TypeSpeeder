package se.ju23.typespeeder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.IO.IO;
import se.ju23.typespeeder.enums.Status;
import se.ju23.typespeeder.logic.TypingGame;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;


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
        menuOptions.add(ANSI_DARK_GREY + "Spela spelet" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "Visa rankinglistan" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "Nyheter from utvecklarna" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "Redigera spelare" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "Logga ut" + ANSI_RESET);
        menuOptions.add(ANSI_DARK_GREY + "Avsluta spel" + ANSI_RESET);
    }

    public ArrayList<String> getMenuOptions() {
        return menuOptions;
    }

    public void setMenuOptions(ArrayList<String> menuOptions) {
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
    public Status displayMenu() {
        int menuNumber = 1;
        LinkedList<String> tempmenuOptions = new LinkedList<>(getMenuOptions());
        System.out.println(" ");
        System.out.println("Välj språk (svenska/engelska):");
        System.out.println(ANSI_DARK_GREY + "Choose language (english/swedish);" + ANSI_RESET);
        System.out.println("Please type in svenska/swedish to select it, or any key to continue in english."+ ANSI_RESET);
        System.out.println(ANSI_DARK_GREY + "Vänligen skriv in svenska/swedish för byta språk, eller valfri tangenttryck för att fortsätta på engelska. ");
        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();
        if (userInput.equalsIgnoreCase("svenska") || (userInput.equalsIgnoreCase("swedish"))) {
            System.out.println("Svenska valt.");
            System.out.println(" ");
            for (int i = 0; i < tempmenuOptions.size(); i++) {
                tempmenuOptions.set(i, tempmenuOptions.get(i).replace("TypeSpeeder", "TypSpeeder").replace("News from devs", "Nyheter from utvecklarna"));
                tempmenuOptions.set(i, tempmenuOptions.get(i).replace("Edit users", "Redigera spelare").replace("Logout", "Logga ut").replace("Quit game", "Avsluta spel"));
                tempmenuOptions.set(i, tempmenuOptions.get(i).replace("Play game", "Spela spelet").replace("Display leaderboards", "Visa rankinglistan"));
                tempmenuOptions.set(i, tempmenuOptions.get(i).replace("Login", "Logga in").replace("News from devs", "Nyheter from utvecklarna"));
            }
            for (String options : tempmenuOptions) {
                System.out.println(menuNumber + ". " + options);
                menuNumber++;
                if (menuNumber == 7){
                    break;
                }
            }
            return Status.SVENSKA;
        } else {
            for (String options : tempmenuOptions) {
                System.out.println(menuNumber + ". " + options);
                menuNumber++;
                if (menuNumber == 7){
                    break;
                }
            }
        }
        return Status.ENGLISH;
    }

    public int selectMenuOptions(Status status) {
        if (status.equals(status.SVENSKA)) {
            System.out.println("Vänlingen välj ett av följande alternativ genom att ange nummer: ");
            return io.getValidIntegerInput(io.returnScanner(), 1, 6);
        } else {
            System.out.println(ANSI_DARK_GREY + "Please select one of the following options by entering the number:  " + ANSI_RESET);
            return io.getValidIntegerInput(io.returnScanner(), 1, 6);
        }
    }

}

