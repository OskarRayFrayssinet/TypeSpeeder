package se.ju23.typespeeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileInputStream;

//import static se.ju23.typespeeder.User.logIn;
@SpringBootApplication
@Component
public class Menu implements MenuService {
    private static UserService userService;

    @Autowired
    static void setUserService(UserService userService){
        Menu.userService = userService;
    }

    static List<String> MenuOptions = new ArrayList<>();
    public static final String YELLOW = "\u001B[33m";
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static Scanner input = new Scanner(System.in);
    private static User loggedInUser;



    public static void displayMenu() throws IOException {
        UserService userService = TypeSpeederApplication.userService;

        System.out.println("Välkommen till TypeSpeeder!");

        if (loggedInUser == null) {
            System.out.println("0. Logga in");
        } else {
            System.out.println("0. Logga ut");
            System.out.println("Inloggad som: " + loggedInUser.getUsername());
        }

        MenuOptions.add("1. Starta spelet");
        MenuOptions.add("2. Rankningslista");
        MenuOptions.add("3. Nyheter och updateringar");
        MenuOptions.add("4. Ändra språk");
        MenuOptions.add("5. Updatera profil");
        System.out.println(MenuOptions);
        System.out.print("Ange siffran för ditt val: ");

        int menuChoice = input.nextInt();
        input.nextLine();

        if (loggedInUser == null && menuChoice == 0) {
            loggedInUser = logIn();
        } else {
            switch (menuChoice) {
                case 0 -> logOut();
                case 1 -> playGame();
                case 2 -> showRankingList();
                case 3 -> showNewsAndUpdates();
                case 4 -> changeLanguage();
                case 5 -> User.updateProfile();
                default -> System.out.println("Felaktig inmatning, försök igen.");
            }
        }
    }

    public static User logIn() {
        System.out.print("Ange användarnamn: ");
        String username = input.nextLine();
        System.out.print("Ange lösenord: ");
        String password = input.nextLine();



        User user = userService.logIn(username, password);

        if (user != null) {
            System.out.println("Inloggning lyckades!");
            return user;
        } else {
            System.out.println("Felaktiga inloggningsuppgifter. Försök igen.");
            return null;
        }
    }

    public static void logOut() {
        loggedInUser = null;
        System.out.println("Du har loggats ut.");
    }


    private static void playGame() throws IOException {
        openTextFile();
    }

    public static void openTextFile() throws FileNotFoundException {

        File file = new File("C:\\Users\\janss\\IdeaProjects\\Text.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String textFile = scanner.nextLine();
            String[] words = textFile.split(" ");
            for (String word : words) {
                String color = Math.random() < 0.5 ? RED : GREEN;
                System.out.print(color + word + " ");
            }
            System.out.print(RESET);
        }
    }

    public static void showRankingList() {

    }

    public static void showNewsAndUpdates() {

    }

    public static void changeLanguage() {

    }

    public List<String> getMenuOptions() {
        return MenuOptions;
    }
}