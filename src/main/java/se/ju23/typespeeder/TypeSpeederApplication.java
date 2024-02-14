package se.ju23.typespeeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

@SpringBootApplication
public class TypeSpeederApplication implements CommandLineRunner {
    public static Scanner input = new Scanner(System.in);
    //public static Window window;

    public static void main(String[] args) {
        SpringApplication.run(TypeSpeederApplication.class, args);



    }
    @Override
    public void run(String... args) throws Exception {

        System.setProperty("java.awt.headless", "false");

        Window window = new Window("TypeSpeeder");

        //Menu.displayMenu();
        //User.logIn();

        window.addString("1. Play the Game\n");
        window.addString("Ange siffran för ditt val: ");


        int menuChoice = input.nextInt();
        input.nextLine();
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