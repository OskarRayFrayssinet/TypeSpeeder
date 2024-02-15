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



    public static void main(String[] args) {
        SpringApplication.run(TypeSpeederApplication.class, args);



    }
    @Override
    public void run(String... args) throws Exception {

        //System.setProperty("java.awt.headless", "false");

        Menu.displayMenu();
        //User.logIn();
        //Menu.openTextFile();





    }




}