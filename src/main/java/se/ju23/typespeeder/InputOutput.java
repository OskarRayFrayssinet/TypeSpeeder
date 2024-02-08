package se.ju23.typespeeder;

import javax.swing.*;
import java.util.Scanner;

public class InputOutput implements IO{
    private Scanner scanner;
    public InputOutput(Scanner scanner){
        this.scanner = scanner;
    }

    @Override
    public boolean yesNo(String prompt) {
        return false;
    }

    @Override
    public String getString() {
        return scanner.nextLine();
    }

    public void addString(String message){
        System.out.println(message);
    }

    @Override
    public void clear() {

    }

    public void exit(){
        System.exit(0);
    }


}
