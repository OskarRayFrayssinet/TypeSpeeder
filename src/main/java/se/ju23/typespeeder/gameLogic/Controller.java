package se.ju23.typespeeder.gameLogic;


import org.springframework.stereotype.Component;
import se.ju23.typespeeder.userInterfaces.IO;
import se.ju23.typespeeder.userInterfaces.MenuService;

import java.sql.SQLException;

@Component public class Controller implements Controllable {
    Playable playable;
    MenuService menuService;
    IO io;


    public Controller(Playable playable, IO io,MenuService m) {
        this.playable = playable;
        this.io = io;
        this.menuService = m;
    }

    @Override
    public void run() throws SQLException, InterruptedException {


        while (true) {

            Status status;
            if (playable.getCurrentAlias(0).isEmpty()){
                if (menuService.getNumberOfTries() == 0){
                io.exit();
                }
                io.addString(menuService.printLoginText());
                String email = io.getString();
                io.addString(menuService.printLoginText());
                String password = io.getString();

                status = playable.checkUser(email, password);
            } else {
                menuService.displayMenu();

                //io.addString(menuService.displayMenu());
                int input = io.getInt();
                status = playable.standbyInMainMenu(input);
            }


            switch (status){
                case IN_GAME_SETTINGS -> {
                    io.addString(menuService.getUserSettingsMenu());
                    int input = io.getInt();
                    if(input == 0){
                        status = Status.VERIFIED;
                    } else {
                        status = playable.standbyInSettingsMenu(input);
                    }
                }
                case ACTIVE_IN_GAME -> {
                    io.addString(playable.printGames());
                    int input = io.getInt();
                    if (input == 0){
                        status = Status.VERIFIED;
                    } else {
                        io.addStringWithoutTranslation(playable.activeInGame(input));
                    }
                }
                case NO_USER_FOUND -> io.addString(menuService.printLoginText());
            }
            switch (status){
                case EXIT -> io.exit();
                case CONTINUANCE ->{
                    io.clear();
                    menuService.displayMenu();
                    //io.addString(menuService.displayMenu());
                }
                case CHANGING_ALIAS -> {

                    if (playable.getCurrentAlias(1).equals("1")){
                        io.addString(menuService.getUserNameChangeText());
                    } else {
                        io.addString(menuService.getUserNameChangeText());
                        String newAlias = io.getString();
                        playable.setNewAlias(newAlias);
                        io.addString(menuService.getUserNameChangeText());
                    }
                }
                case CHANGING_PASSWORD -> {
                    if (playable.getPassword(1).equals("1")){
                        io.addString(menuService.getPasswordChangeText());
                    } else {
                        io.addString(menuService.getPasswordChangeText());
                        String checkCurrentPassword = io.getString();
                        boolean checked = playable.checkCurrentPassword(checkCurrentPassword);
                        if (checked) {
                            io.addString(menuService.getPasswordChangeText());
                            String newPassword = io.getString();
                            playable.setNewPassword(newPassword);
                            io.addString(menuService.getPasswordChangeText());
                        } else {
                            io.addString(menuService.getPasswordChangeText());
                        }
                    }
                }
                case CHANGING_USERNAME -> {
                    if (playable.getCurrentEmail(1).equals("1")){
                        io.addString(menuService.getUsernameChangeText());
                    } else {
                        io.addString(menuService.getUsernameChangeText());
                        String checkCurrentEmail = io.getString();
                        boolean checked = playable.checkCurrentEmail(checkCurrentEmail);
                        if (checked) {
                            io.addString(menuService.getUsernameChangeText());
                            String newEmail = io.getString();
                            boolean checkIfBusy = playable.checkIfUserNameIsBusy(newEmail);
                            if (checkIfBusy){
                                playable.setNewUsername(newEmail);
                                io.addString(menuService.getUsernameChangeText());
                            } else {
                                io.addString(menuService.getUsernameChangeText());
                            }
                        } else {
                            io.addString(menuService.getUsernameChangeText());
                        }
                    }
                }
                case SETTING_LANGUAGE -> {
                    io.addString(menuService.printChangeLanguageText());
                    String answer = io.getYesOrNo();
                    if (answer.equals("y")){
                        playable.setLanguage();
                        io.addString(menuService.printChangeLanguageText());
                    }


                }
            }
        }
    }
}