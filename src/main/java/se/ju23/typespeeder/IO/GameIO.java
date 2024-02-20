package se.ju23.typespeeder.IO;
import org.springframework.stereotype.Service;
import java.util.Scanner;

@Service
public class GameIO implements IO {
    Scanner scanner = new Scanner(System.in);
    @Override
    public String getValidStringInput() {
        String userInput = null;
        boolean isValidInput = false;

        while (!isValidInput) {
            try {
                userInput = scanner.nextLine();
                isValidInput = true;
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                scanner.nextLine();
            }
        }

        return userInput;
    }


    @Override
    public int getValidIntegerInput(Scanner input, int minValue, int maxValue) {
        int userInput = 0;
        boolean isUserInputInvalid;

        do {
            isUserInputInvalid = false;
            try {
                userInput = input.nextInt();
                if (userInput < minValue || userInput > maxValue) {
                    System.out.println("Invalid entry, please enter a number between " + minValue + " and " + maxValue + "...");
                    isUserInputInvalid = true;
                }
            } catch (Exception e) {
                System.out.println("Invalid entry, please enter a number between " + minValue + " and " + maxValue + "...");
                isUserInputInvalid = true;
            }
            input.nextLine();
        } while (isUserInputInvalid);

        return userInput;
    }

    @Override
    public String getString() {
        String userInput;
        boolean isUserInputInvalid;

        do {
            userInput = scanner.nextLine();
            if (!userInput.matches("[-a-zA-ZåäöÅÄÖ0-9@._ ]+")) {
                System.out.println("Incorrect format, you cannot use special characters!");
                isUserInputInvalid = true;
            } else if (userInput.isEmpty()) {
                System.out.println("entry cannot be blank..");
                isUserInputInvalid = true;
            } else {
                isUserInputInvalid = false;
            }

        } while (isUserInputInvalid);

        return userInput;
    }

    @Override
    public Scanner returnScanner() {
        return new Scanner(System.in);
    }

}
