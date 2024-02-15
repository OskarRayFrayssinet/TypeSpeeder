package se.ju23.typespeeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

@SpringBootApplication
public class TypeSpeederApplication implements CommandLineRunner {


    @Autowired
    public static UserService userService;



    public static void main(String[] args) {
        SpringApplication.run(TypeSpeederApplication.class, args);

    }
    @Override
    public void run(String... args) throws Exception {
        Menu.setUserService(userService);
        Menu.displayMenu();
    }

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserService(userRepository);
    }


    //System.setProperty("java.awt.headless", "false");

    // Menu.displayMenu();
    // Menu.logIn();
    //Menu.displayMenu();
    //Menu.openTextFile();


}


