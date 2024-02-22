package se.ju23.typespeeder.IO;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Scanner;

@Service
public class GameIO implements IO {
    Scanner input = new Scanner(System.in);
    ArrayList<String> inGameMessages;

    public GameIO() {
        inGameMessages = new ArrayList<>();
        inGameMessages.add("Välkomna till spelet.");
        inGameMessages.add("Skriv in nyhetsbrevets innehåll -> ");
        inGameMessages.add("Please enter the content of the newsletter -> ");
        inGameMessages.add("Åtkomst nekad, endast admin har åtkomst.");
        inGameMessages.add("Access denied, only admin has access.");
        inGameMessages.add("Skriv in patchversion -> ");
        inGameMessages.add("Please enter the patch version -> ");
        inGameMessages.add("Please enter the users full name -> ");
        inGameMessages.add("Ange spelarens användarnamn -> ");
        inGameMessages.add("Ange spelarens lösenord -> ");
        inGameMessages.add("Please enter the users password-> ");
        inGameMessages.add("Ange spelarens gamingnamn -> ");
        inGameMessages.add("Please enter the users gaming name -> ");
        inGameMessages.add("Please select the user role from the following numerical options: ");
        inGameMessages.add("Välj användarroll från följande numrerade alternativ: ");
        inGameMessages.add("1. Admin" + "\n2. User");
        inGameMessages.add("Du lade till: ");
        inGameMessages.add("You added: ");
    }

    public ArrayList<String> getInGameMessages() {
        return inGameMessages;
    }

    public void setInGameMessages(ArrayList<String> inGameMessages) {
        this.inGameMessages = inGameMessages;
    }

    @Override
    public String getValidStringInput() {
        String userInput = null;
        boolean isValidInput = false;

        while (!isValidInput) {
            try {
                userInput = input.nextLine();
                isValidInput = true;
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                input.nextLine();
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
            userInput = input.nextLine();
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

    public String inputUserString() {
        return input.nextLine();
    }


}
