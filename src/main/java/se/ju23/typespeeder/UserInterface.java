package se.ju23.typespeeder;

import java.util.Scanner;

public class UserInterface {
    private Scanner input = new Scanner(System.in);

    private void startMenu() {

        System.out.println("""
                Välkommen till TypeSpeeder!
                Ange siffra för motsvarande menyval
                1. Logga in
                2. Nånting
                3. Nånting annat
                4. Avsluta spelet
                >""");

        int menuChoice;
        do {

            switch (menuChoice = input.nextInt()) {
                case 1 -> loginUser();
                case 2 -> ();
                case 3 -> ();
                case 0 -> System.out.println("\n Avsluta programmet");
            }

        } while(menuChoice != 0);
    }
}
