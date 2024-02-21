package se.ju23.typespeeder;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

@SpringBootApplication
@Component
public class Menu implements MenuService {
    private static UserService userService;
    public static User loggedInUser;
    private static Object LoggedInUser;
    private static ResourceBundle messages = ResourceBundle.getBundle("Messages");
    static List<String> MenuOptions = new ArrayList<>();
    public static Scanner input = new Scanner(System.in);

    public static String userName;
    public static String passWord;

    public static void displayMenu() throws IOException {
        UserService userService = TypeSpeederApplication.userService;

       // System.out.println(messages.getString("welcome.message.sv"));
        //System.out.println(messages.getString("welcome.message"));


     //   if (loggedInUser == null) {
         //   System.out.println("0. Logga in");
       // } else {
         //   System.out.println("0. Logga ut");
          //  System.out.println("Inloggad som: " + loggedInUser.getUsername());
      //  }



        System.out.println("Ange siffran för ditt val: ");
        MenuOptions.add("1. Starta spelet");
        MenuOptions.add("2. Rankningslista");
        MenuOptions.add("3. Nyheter och updateringar");
        MenuOptions.add("4. Ändra språk");
        MenuOptions.add("5. Updatera profil");
        MenuOptions.add("6. Logga ut");
        for (String list: MenuOptions){
            System.out.println(list);
        }



        int menuChoice = input.nextInt();
        input.nextLine();

        if (loggedInUser == null && menuChoice == 0) {
            loggedInUser = logIn();
        } else {
            switch (menuChoice) {
                case 6 -> logOut();
                case 1 -> Challenge.startChallenge();
                case 2 -> Challenge.showRankingList();
                case 3 -> showNewsAndUpdates();
                case 4 -> changeLanguage();
              //  case 5 -> updateUser();

                default -> System.out.println("Felaktig inmatning, försök igen.");
            }
            }
        }



    public static User logIn() {
        User user = null;
        boolean loggedIn = false;

        while (!loggedIn) {
            System.out.print("Ange användarnamn: ");
            userName = input.nextLine();
            System.out.print("Ange lösenord: ");
            passWord = input.nextLine();

            user = userService.userRepository.findByUsernameAndPassword(userName, passWord);

            if (user != null) {
                System.out.println("Inloggning lyckades!");
                loggedIn = true;
            } else {
                System.out.println("Felaktiga inloggningsuppgifter. Försök igen.");
            }
        }

        return user;
    }


   /* public static void updateUser() {
        System.out.print("Ange nytt användarnamn (lämna tomt om ingen ändring): ");
        String newUsername = input.nextLine();
        user.setUsername(newUsername);

        System.out.print("Ange nytt lösenord (lämna tomt om ingen ändring): ");
        String newPassword = input.nextLine();
        user.setPassword(newPassword);



        }*/




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
        messages = ResourceBundle.getBundle("messages", Locale.getDefault());
    }


    public List<String> getMenuOptions() {
        return MenuOptions;
    }
}
