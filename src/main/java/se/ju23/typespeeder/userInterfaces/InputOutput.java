package se.ju23.typespeeder.userInterfaces;

import org.springframework.stereotype.Component;
import se.ju23.typespeeder.gameLogic.Playable;
import se.ju23.typespeeder.gameLogic.Translatable;

import java.util.Scanner;

@Component public class InputOutput implements IO {
    Playable playable;
    MenuService menuService;
    Translatable translatable;
    private Scanner scanner = new Scanner(System.in);
    public InputOutput(Playable playable, MenuService menuService, Translatable translatable){
        this.playable = playable;
        this.menuService = menuService;
        this.translatable = translatable;
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
    @Override
    public String getEnter() {
        while (true) {
            String inputString = scanner.nextLine().trim().toLowerCase();
            if (inputString.equalsIgnoreCase("")){
                return inputString;
            } else {
                System.out.println("Press ENTER to start game");
            }
        }
    }
    @Override
    public String getYesOrNo() {
        while (true) {
            String inputString = scanner.nextLine().trim().toLowerCase();
            if (inputString.equalsIgnoreCase("y") || inputString.equalsIgnoreCase("n")) {
                return inputString;
            } else {
                System.out.println("Please enter 'y' for YES or 'n' for NO!");
            }
        }
    }
    public int getInt(){
        while (true) {
            try {
                int intOnly = Integer.parseInt(getString());
                return intOnly;
            } catch (NumberFormatException e) {
                System.out.print("Please write an integer: ");
            }
        }
    }

    public void addString(String message){
        String a = message;
        if (menuService.getCurrentLanguage(0).equals("1")){
            try {
                a = translatable.translate(message,"sv");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            String translatedText = a.replaceAll("(\\d+)\\.\\s*", "\n$1. ");
            System.out.print(translatedText);
        } else {
            System.out.print(a);
        }

    }
    //Game Text Output
    public void addGameText(String message){
        System.out.print(message);
    }


    @Override
    public void clear() {

    }

    public void exit(){
        System.exit(0);
    }


}
