package se.ju23.typespeeder.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.entity.ResultRepository;
import se.ju23.typespeeder.entity.UserRepository;
import se.ju23.typespeeder.util.UserInput;

import java.util.Scanner;
@Component
public class UserInterface {
    private final Scanner input = new Scanner(System.in);

    @Autowired
    public UserService userService(UserRepository userRepository, ResultRepository resultRepository) {
        return new UserService(userRepository, resultRepository);
    }

    private static void menu(){

        int menuChoice;
        do{
            System.out.println("""
                
                Vänligen välj en siffra!
                
                Huvudmeny
                ----------------------------
                 1. Logga in
                 2. Skapa konto
                 3. Spela
                 0. Avsluta
                ----------------------------""");

            menuChoice = UserInput.readInt(0, 3);

            switch(menuChoice){
                //case 1 -> loginUser();
                //case 2 -> ();
                //case 3 -> ();
                case 0 -> System.out.println("\nExiting program...");
            }


        } while(menuChoice != 0);
    }
}
