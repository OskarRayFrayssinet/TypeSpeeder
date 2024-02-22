package se.ju23.typespeeder;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

import static se.ju23.typespeeder.Challenge.startChallenge;

@SpringBootApplication
@Component
public class Menu implements MenuService {
    private static UserService userService;
    public static User loggedInUser;
    private static Object LoggedInUser;
    public static String loggedInUsername;
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    public static ResourceBundle messages = ResourceBundle.getBundle("Messages");
    static List<String> MenuOptions = new ArrayList<>();
    public static Scanner input = new Scanner(System.in);

    public static String userName;
    public static String passWord;
    public static boolean loggedIn=false;

    public static void displayMenu() throws IOException {
        UserService userService = TypeSpeederApplication.userService;

        MenuOptions.clear();
        MenuOptions.add("1. Starta spelet");
        MenuOptions.add("2. Rankningslista");
        MenuOptions.add("3. Nyheter och updateringar");
        MenuOptions.add("4. Ändra språk");
        MenuOptions.add("5. Updatera profil");
        MenuOptions.add("6. Logga ut");
        for (String list: MenuOptions){
            System.out.println(list);
        }
        System.out.print("Ange siffran för ditt val: ");




        int menuChoice = input.nextInt();
        input.nextLine();

        if (loggedInUser == null && menuChoice == 0) {
             logIn();
        } else {
            switch (menuChoice) {
                case 6 -> logOut();
                case 1 -> startChallenge();
                case 2 -> Challenge.showRankingList();
                case 3 -> showNewsAndUpdates();
                case 4 -> changeLanguage();
                case 5 -> updateUser();

                default -> System.out.println("Felaktig inmatning, försök igen.");
            }
        }
    }



    public static void logIn() {


        while (!loggedIn) {
            System.out.print("Ange användarnamn: ");
            userName = input.nextLine();
            System.out.print("Ange lösenord: ");
            passWord = input.nextLine();

            loggedInUser = userService.userRepository.findByUsernameAndPassword(userName, passWord);

            if (loggedInUser != null) {
                System.out.println("Inloggning lyckades!");
                loggedInUsername = loggedInUser.getUsername();
                loggedIn = true;
            } else {
                System.out.println("Felaktiga inloggningsuppgifter. Försök igen.");
            }
        }

    }



    public static void updateUser() throws IOException {
        while (true) {
            if (loggedInUser != null) {
                System.out.print("Ange nytt användarnamn (tryck enter om du inte vill ändra): ");
                String newUsername = input.nextLine();

                System.out.print("Ange nytt lösenord (tryck enter om du inte vill ändra): ");
                String newPassword = input.nextLine();

                if (!newUsername.isEmpty() || !newPassword.isEmpty()) {

                    loggedInUser.updateCredentials(newUsername, newPassword);
                    userService.userRepository.save(loggedInUser);
                    System.out.println("Användarnamn och/eller lösenord är uppdaterade!");
                    System.out.println("Vill du återgå till huvudmenyn? (ja/nej): ");
                    String goBack = input.nextLine().toLowerCase();

                    if ("ja".equals(goBack)) {
                        displayMenu();
                    } else {
                        System.out.println("Programmet avslutas.");

                    }
                }

            }


        }
    }


    public static void logOut() {
        LoggedInUser = null;
        System.out.println("Du har loggats ut.");
    }


    static void setUserService(UserService userService) {
       Menu.userService = userService;
    }

 //   @Autowired
   // private static UserService userService;


    public static void showNewsAndUpdates() throws IOException {
        System.out.println("Nyheter och uppdateringar:");
        System.out.println("Nu finns möjlighet att spela spelet på engelska!");

        System.out.println("Vill du återgå till huvudmenyn? (ja/nej): ");
        String goBack = input.nextLine().toLowerCase();

        if ("ja".equals(goBack)) {
            displayMenu();
        } else {
            System.out.println("Programmet avslutas.");
        }
    }


    public static void changeLanguage() throws IOException {
        System.out.print("Välj språk (sv/en): ");
        String language = input.nextLine().toLowerCase();

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

        startGameAfterLanguageSelection();
       /* System.out.println(messages.getString("return.menu"));
        String goBack = input.nextLine().toLowerCase();
        if ("ja".equalsIgnoreCase(goBack) || "yes".equalsIgnoreCase(goBack)) {
            displayMenu();
        }*/

    }

    public static void startGameAfterLanguageSelection() throws IOException {
        System.out.print(messages.getString("you.want.play"));
        String playGame = input.nextLine().toLowerCase();
        if ("ja".equalsIgnoreCase(playGame) || "yes".equalsIgnoreCase(playGame)) {
            startChallenge();
        } else {
            displayMenu();
        }
    }




    public static void loadResources() {
        messages = ResourceBundle.getBundle("messages", Locale.getDefault());
    }


    public List<String> getMenuOptions() {
        return MenuOptions;
    }
}
