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
