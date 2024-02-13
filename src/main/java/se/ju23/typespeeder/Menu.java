package se.ju23.typespeeder;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileInputStream;

public class Menu implements MenuService {
    List<String>MenuOptions;
    public static Window window;
    public static final String YELLOW = "\u001B[33m";

    public void displayMenu() {

        System.out.println("Välkommen till TypeSpeeder!");

    }

    public static void playGame() throws IOException {
        openTextFile();
    }
    public static void openTextFile() throws FileNotFoundException {
        String path = "C:\\Users\\janss\\IdeaProjects\\Text.txt";
        //InputStream inputStream = new FileInputStream(path);
        Scanner scanner = new Scanner(new File(path));
        while(scanner.hasNextLine()){
            window.addString(YELLOW + scanner.nextLine());
        }
    }

    public static void showRankingList(){

    }

    public static void showNewsAndUpdates(){

    }

    public static void changeLanguage(){

    }

    public List<String> getMenuOptions() {
        return MenuOptions;
    }
}
