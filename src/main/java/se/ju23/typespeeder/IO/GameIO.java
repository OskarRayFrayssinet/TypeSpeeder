/*
 * Class: GameIO
 * Description: A class that implements IO interface, contructs messages in the game and more.
 * Created by: Kerem Bjävenäs Tazedal
 * Email: kerem.tazedal@iths.se
 * Date: 2024-02-13
 */
package se.ju23.typespeeder.IO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.enums.Status;
import se.ju23.typespeeder.service.StatusService;

import java.util.ArrayList;
import java.util.Scanner;

@Service
public class GameIO implements IO {
    Scanner input = new Scanner(System.in);
    ArrayList<String> inGameMessages;
    public static final String ANSI_DARK_BLUE = "\u001B[34m";
    public static final String ANSI_DARK_GREY = "\u001B[90m";
    public static final String ANSI_RESET = "\u001B[0m";

    @Autowired
    StatusService statusService;

    public GameIO() {
        inGameMessages = new ArrayList<>();
        inGameMessages.add(ANSI_DARK_GREY + "Välkomna till spelet." + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Skriv in nyhetsbrevets innehåll -> " + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Please enter the content of the newsletter -> " + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Åtkomst nekad, endast admin har åtkomst." + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Access denied, only admin has access." + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Skriv in patchversion -> " + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Please enter the patch version -> " + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Please enter the username -> " + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Ange spelarens användarnamn -> " + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Ange spelarens lösenord -> " + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Please enter the users password-> " + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Ange spelarens gamingnamn -> " + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Please enter the users gaming name -> " + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Please select the user role from the following numerical options: " + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Välj användarroll från följande numrerade alternativ: " + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "\n1. Admin" + "\n2. User" + "\n -->" + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Du lade till: " + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "You added: " + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "\nVälkomna till TypeSpeeder!" + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "\nWelcome to the TypeSpeeder!" + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Please select one of the following options by entering the number -> " + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Välj en av följande alternativ genom att skriva in numret -> " + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Invalid input. Please try again" + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Felaktig inmatning, var vänlingen och försök igen" + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Felaktig inmatning, var vänligen och ange ett nummer mellan " + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + " och " + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Invalid entry, please enter a number between " + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + " and " + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Felaktig inmatning, du får inte använda specialtecken" + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Incorrect format, you cannot use special characters" + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Inmatning får inte vara tom" + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "\nVälj språk (svenska/engelska):" + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "\nChoose Language (swedish/english):" + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Svenska valt." + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "English chosen" + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + " --> " + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Skriv in användarnamn -> " + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Skriv in lösenord -> " + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Please enter a username -> " + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Please enter a password -> " + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Felaktigt användarnamn eller lösenord" + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Wrong username or password" + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "\nKör Kör Kör -> Skriv!" + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "\nGo Go Go -> Type!" + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Antalet korrekta svar: " + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Antalet korrekta svar i följd: " + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Det tog " + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + " sekunder" + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Total poäng: " + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Correct answers: " + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Correct answers in a row: " + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Time elapsed: " + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + " seconds" + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Time elapsed: " + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Välkomna till menyn för att redigera spelare, ange id för att välja spelare att redigera: " + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Welcome to the menu for editing user, please select a user id from the list below to begin editing: " + "Ange 0 för att avsluta: " + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Please enter the id of the specific user to begin editing\n" + "Enter '0' to exit: " + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Please choose from the following options to edit: " + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY +
                "\n1. Edit username" +
                "\n2. Edit password" +
                "\n3. Edit Playername" +
                "\n4. Edit player points" +
                "\n5. Edit player level" +
                "\n6. Edit player role" +
                "\n7. Remove player" +
                "\n8. Quit menu" + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Please choose a option by entering the menu number: " + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Var vänligen välj följande alternativ för att börja redigera: " + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY +
                "\n1.  Redigera användarnamn" +
                "\n2.  Redigera lösenord" +
                "\n3.  Redigera spelarnamn" +
                "\n4.  Redigera spelarpoäng" +
                "\n5.  Redigera spelarlevel" +
                "\n6.  Redigera spelarroll" +
                "\n7.  Ta bort spelare" +
                "\n8.  Avsluta menyn" + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Välj följande alternativ genom att ange menunummer: " + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Ange nytt användarnamn: " + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Enter the new username: " + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Ange poäng -> " + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Enter points ->" + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Ange level ->" + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Enter level ->" + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Ange id för spelaren du vill radera ->" + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Enter id of the player you want to remove ->" + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Användaren har raderats" + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "User has been removed" + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "Användaren hittades inte, ingen radering ägde rum" + ANSI_RESET);
        inGameMessages.add(ANSI_DARK_GREY + "User not found, no deletion took place" + ANSI_RESET);
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
                if (statusService.getStatus().equals(Status.SVENSKA)) {
                    System.out.println(getInGameMessages().get(23));
                } else if (statusService.getStatus().equals(Status.ENGLISH)) {
                    System.out.println(getInGameMessages().get(22));
                }
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
                    if (statusService.getStatus().equals(Status.SVENSKA)) {
                        System.out.println(getInGameMessages().get(24) + minValue + getInGameMessages().get(25) + maxValue);
                    } else if (statusService.getStatus().equals(Status.ENGLISH)) {
                        System.out.println(getInGameMessages().get(26) + minValue + getInGameMessages().get(27) + maxValue);
                    } else
                        System.out.println(getInGameMessages().get(24) + minValue + getInGameMessages().get(25) + maxValue);
                    System.out.println(getInGameMessages().get(26) + minValue + getInGameMessages().get(27) + maxValue);

                    isUserInputInvalid = true;
                }
            } catch (Exception e) {
                if (statusService.getStatus().equals(Status.SVENSKA)) {
                    System.out.println(getInGameMessages().get(24) + minValue + getInGameMessages().get(25) + maxValue);
                } else if (statusService.getStatus().equals(Status.ENGLISH)) {
                    System.out.println(getInGameMessages().get(26) + minValue + getInGameMessages().get(27) + maxValue);
                } else
                    System.out.println(getInGameMessages().get(24) + minValue + getInGameMessages().get(25) + maxValue);
                System.out.println(getInGameMessages().get(26) + minValue + getInGameMessages().get(27) + maxValue);

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
                if (statusService.getStatus().equals(Status.SVENSKA)) {
                    System.out.println(getInGameMessages().get(29));
                } else if (statusService.getStatus().equals(Status.ENGLISH)) {
                    System.out.println(getInGameMessages().get(30));
                }
                isUserInputInvalid = true;
            } else if (userInput.isEmpty()) {
                if (statusService.getStatus().equals(Status.SVENSKA)) {
                    System.out.println(getInGameMessages().get(30));
                } else if (statusService.getStatus().equals(Status.ENGLISH)) {
                    System.out.println(getInGameMessages().get(31));
                }
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
