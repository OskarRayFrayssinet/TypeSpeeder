package se.ju23.typespeeder;

import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.Scanner;

@Component public class InputOutput implements IO{
    private Scanner scanner = new Scanner(System.in);
    public InputOutput(){
    }

    @Override
    public boolean yesNo(String prompt) {
        return false;
    }

    @Override
    public String getString() {
        String inputString = "";
        while (true) {
            inputString = scanner.nextLine().trim();
            if (inputString.isEmpty()) {
                System.out.println("Du måste ange något!");
            } else {
                return inputString;
            }
        }

    }
    public int getInt(){
        while (true) {
            try {
                int intOnly = Integer.parseInt(getString());
                return intOnly;
            } catch (NumberFormatException e) {
                System.out.print(" Vänligen skriv in ett heltal:");
            }
        }
    }

    public void addString(String message){
        System.out.print(message);
    }

    @Override
    public void clear() {

    }

    public void exit(){
        System.exit(0);
    }


}
