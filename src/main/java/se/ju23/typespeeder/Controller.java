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

            startMenu();
        }
    }
    public void startMenu(){
        int menuChoice;
        do {
            menu.displayMenu();
            switch (menuChoice = systemIO.readIntOnly()) {
                case 0 -> menu.setLoggedInPlayer(null);
                case 1 -> {
                    challenge.setDifficulty(menu.chooseDifficulty());
                    menu.challengeCountDown();
                    challenge.runChallenge();
                    systemIO.addString(menu.postGameResults(challenge.postChallengeSummary()));
                }
                case 2 -> systemIO.addString(challenge.getTop10rankings());
            }
        } while (menuChoice != 0);
    }
}
