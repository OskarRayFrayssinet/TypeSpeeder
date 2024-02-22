package se.ju23.typespeeder;

public class Controller {
    MenuService menu;
    SystemIO systemIO;
    Challenge challenge;
    public Controller(Menu menu, SystemIO systemIO, Challenge challenge) {
        this.menu = menu;
        this.systemIO = systemIO;
        this.challenge = challenge;
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
                    systemIO.addString(menu.postGameResults(challenge.postChallengeSummary()));
                    continueOrExit();
                }
                case "2" -> systemIO.addString(challenge.getTop10rankings());
                case "3" -> menu.settingsMenu();
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
}
