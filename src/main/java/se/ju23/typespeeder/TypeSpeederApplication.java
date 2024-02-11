package se.ju23.typespeeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TypeSpeederApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TypeSpeederApplication.class, args);


    }
    @Override
    public void run(String... args) throws Exception {

        User.logIn();

        int menuChoice = 0;
        switch (menuChoice) {
            case 0 -> User.logOut();
            case 1 -> Menu.playGame();
            case 2 -> Menu.showRankingList();
            case 3 -> Menu.showNewsAndUpdates();
            case 4 -> Menu.changeLanguage();
            case 5 -> User.updateProfile();
            default -> System.out.println("Felaktig inmatning, försök igen.");
        }
    }




}