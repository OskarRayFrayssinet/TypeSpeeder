package se.ju23.typespeeder;

public class Controller {
    MenuService menu;
    SystemIO systemIO;
    Challenge challenge;
    DAOManager daoManager;
    public Controller(Menu menu, SystemIO systemIO, Challenge challenge, DAOManager daoManager) {
        this.menu = menu;
        this.systemIO = systemIO;
        this.challenge = challenge;
        this.daoManager = daoManager;
    }

    public void run(){
        while(true) {
            menu.printTypeSpeederAnsi();
            menu.startMenu();

            if (menu.getLoggedInPlayer() == null) {
                return;
            }

            mainMenu();
        }
    }
    public void mainMenu(){
        String menuChoice;
        do {
            switch (menuChoice = menu.displayMenu()) {
                case "5" -> menu.setLoggedInPlayer(null);
                case "1" -> {
                    challenge.setDifficulty(menu.chooseDifficulty());
                    menu.challengeCountDown();
                    challenge.runChallenge();
                    systemIO.addString(menu.postGameResults(challenge.ChallengeScoreCalc()));
                    continueOrExit();
                }
                case "2" -> systemIO.addString(challenge.getTop10rankings());
                case "3" -> settings();
                case "4" -> menu.patchNotesAndNewsMenu();
                case "6" -> systemIO.addString("\nSkriv svenska eller engelska för att välja språk.");

            }
        } while (!menuChoice.equals("5"));
    }
    public void continueOrExit(){
        int choice;
        do {
            menu.continueOrExit();
            choice = systemIO.readIntOnly();
            if (choice == 0) {
                systemIO.addString(menu.getExitMessage());
                System.exit(0);
            }
        } while (choice != 1);
    }
    public void settings() {
        int menuChoice;
        do {
            menu.settingsMenu();
            switch (menuChoice = systemIO.readIntOnly()) {
                case 1 -> changeUserName();
                case 2 -> changeDisplayName();
                case 3 -> changePassword();
                case 4 -> showPlayerStatistics();
            }
        } while (menuChoice != 0);
    }
    public void changePassword() {
        systemIO.addString("Ange ditt nya lösenord\n>");
        String newPassword = systemIO.getString();
        daoManager.changePassword(newPassword);
        systemIO.addString("Ditt lösenord har ändrats.");
    }
    public void changeUserName() {
        systemIO.addString("Ange ditt nya användarnamn\n>");
        String newUserName = systemIO.getString();
        daoManager.changeUsername(newUserName);
        systemIO.addString("Ditt användarnamn har ändrats till " + newUserName + ".");
    }
    public void changeDisplayName() {
        systemIO.addString("Ange ditt nya visningsnamn\n>");
        String newDisplayName = systemIO.getString();
        daoManager.changeDisplayName(newDisplayName);
        systemIO.addString("Ditt visningsnamn har ändrats till " + newDisplayName + ".");
    }
    public void showPlayerStatistics() {
        int levelGainThreshHold = challenge.calcLevelGainThreshHold();

        String rank = challenge.numberToRankConversion(daoManager.getPlayer().getRanking());

        systemIO.addString(daoManager.getPlayer().getDisplayName() + "\n" +
                "Ranking: " + rank + "\tLevel: " +
                daoManager.getPlayer().getLevel() + "\tXP: " + daoManager.getPlayer().getXp() +
                "/" + levelGainThreshHold + "\tGames played: " + daoManager.getPlayer().getGamesPlayed());
    }
}
