package se.ju23.typespeeder;

import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Scanner;
@Component
public class SystemIO {
    public void addString(String message) {
        System.out.print(message);
    }
    public String getString () {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public int readIntOnly() {
        while (true) {
            try {
                return Integer.parseInt(getString());
            } catch (InputMismatchException | NumberFormatException e) {
                addString(e + "\tVänligen skriv in ett heltal:\n>");
            }
        }
    }
    public void clear() {

    }
    public void exit() {

    }
}
