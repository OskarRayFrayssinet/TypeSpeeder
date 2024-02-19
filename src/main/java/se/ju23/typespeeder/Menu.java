package se.ju23.typespeeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileInputStream;

//import static se.ju23.typespeeder.User.logIn;
@SpringBootApplication
@Component
public class Menu implements MenuService {
    private static UserService userService;
    private static User loggedInUser;
    private static Object LoggedInUser;
    private static ResourceBundle messages;

    @Autowired
    static void setUserService(UserService userService){
        Menu.userService = userService;
    }

    static List<String> MenuOptions = new ArrayList<>();
    public static final String WHITE = "\u001B[37m";
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static Scanner input = new Scanner(System.in);
    public static boolean stopTimer = false;
    public static long startTime;
    public static String game;
    public static String colorWords;
    public static List<String> redWords = new ArrayList<>();


    public static void displayMenu() throws IOException {
        UserService userService = TypeSpeederApplication.userService;

       // System.out.println(messages.getString("welcome.message.sv"));
        //System.out.println(messages.getString("welcome.message"));
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
        LoggedInUser = null;
        System.out.println("Du har loggats ut.");
    }


    public static void playGame() throws IOException {

       // System.out.println(messages.getString("game.instructions.sv"));
       // System.out.println(messages.getString("game.instructions"));

        System.out.println("Skriv de röda orden korrekt med samma ordning som de står och tryck enter när du är klar.");
        System.out.println("Tiden börjar när texten skrivs ut");
        System.out.print("Klicka Enter för att starta spelet");
        input.nextLine();
        openTextFile();
        timer();
        System.out.println();
        System.out.print("SKRIV HÄR --> ");
        game = input.nextLine();
        stopTimer = true;
        checkSpelling();
        checkOrder();

    }

    public static void openTextFile() throws FileNotFoundException {
        StringBuilder colorWordsBuilder = new StringBuilder();
        File file = new File("C:\\Users\\janss\\IdeaProjects\\Text2.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String textFile = scanner.nextLine();
            String[] words = textFile.split(" ");
            for (String word : words) {
                String color = Math.random() < 0.5 ? RED : GREEN;
                colorWordsBuilder.append(color).append(word).append(" ");
                System.out.print(color + word + " " + RESET);
            }
            //System.out.print(RESET);
        }
        colorWords = colorWordsBuilder.toString();
    }
    public static void timer(){
    Thread timer = new Thread(()->{
            startTime = System.currentTimeMillis();
            while (!stopTimer) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            long timeSeconds = (System.currentTimeMillis() - startTime) / 1000;
            System.out.println(RESET + "Din tid blev: " + timeSeconds + " sekunder");
        });
        timer.start();
    }
    public static void checkSpelling(){
        String [] words = colorWords.split("\\s+");
        for (String word : words) {
            if (word.startsWith(RED)) {
                redWords.add(word.substring(RED.length()));
            }
        }
        String [] gameList = game.split("\\s+");
        int countWords = 0;
        for (String list : gameList){
            for(String red : redWords){
                if (red.equals(list)){
                    countWords++;
                }
            }
        }
        System.out.println(RESET + "Antal rättstavade ord = " + countWords);
    }
    public static void checkOrder(){
        int countOrder = 0;
        String[] gameList = game.split("\\s+");
        int minWord = Math.min(redWords.size(), gameList.length);
        for(int i = 0; i < minWord; i++){
            if(gameList[i].equals(redWords.get(i))){
                countOrder++;
            } else {
                break;
            }
        }
        System.out.println("Antal ord i korrekt ordnind: " + countOrder);
    }

    public static void showRankingList() {

    }

    public static void showNewsAndUpdates() {

    }

    public static void changeLanguage() throws IOException {
        System.out.print("Välj språk (sv/en): ");
        String language = input.nextLine().toLowerCase();

        // Uppdatera ResourceBundle för det valda språket
        System.out.println("Valt språk: " + language);
        if ("en".equalsIgnoreCase(language)) {
            messages = ResourceBundle.getBundle("messages", new Locale(language, "US"));
            System.out.println(messages.getString("language.changed"));
        } else if ("sv".equalsIgnoreCase(language)) {
            messages = ResourceBundle.getBundle("messages", new Locale(language, "SE"));
            System.out.println(messages.getString("language.changed"));
        } else {
            System.out.println("Ogiltigt språkval. Använder systemets standardspråk.");
            messages = ResourceBundle.getBundle("messages", Locale.getDefault());
        }

        System.out.println(messages.getString("return.menu"));
        String goBack = input.nextLine().toLowerCase();
        if ("ja".equalsIgnoreCase(goBack) || "yes".equalsIgnoreCase(goBack)) {
            displayMenu();
        }

    }






    public static void loadResources() {
        // Ladda resursbundeln här med standardlokalen
        messages = ResourceBundle.getBundle("messages", Locale.getDefault());
    }


    public List<String> getMenuOptions() {
        return MenuOptions;
    }
}