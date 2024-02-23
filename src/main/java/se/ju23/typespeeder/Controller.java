package se.ju23.typespeeder;

import java.util.Optional;

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
        menu.refreshPlayerInfo();
        do {
            switch (menuChoice = menu.displayMenu()) {
                case "0" -> menu.setLoggedInPlayer(null);
                case "1" -> game();
                case "2" -> systemIO.addString(challenge.getTop10rankings());
                case "3" -> settings();
                case "4" -> patchNotesAndNews();
                case "5" -> systemIO.addString("\nSkriv svenska eller engelska för att välja språk.");

            }
        } while (!menuChoice.equals("0"));
    }
    public int chooseGameMode(){
        int menuChoice;
        do {
            menu.gameModeMenu();
            menuChoice = systemIO.readIntOnly();

        } while (menuChoice < 1 || menuChoice > 3);

        return menuChoice;
    }
    public void game(){
        int gameMode = chooseGameMode();
        challenge.setDifficulty(menu.chooseDifficulty());
        menu.challengeCountDown();
        challenge.runChallenge(gameMode);
        systemIO.addString(menu.postGameResults(challenge.ChallengeScoreCalc()));
        continueOrExit();
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
                case 5, 6 -> createAdminMessage(menuChoice);
            }
        } while (menuChoice != 0);
    }
    public void patchNotesAndNews() {
        int menuChoice;
        do {
            menu.patchNotesAndNewsMenu();
            switch (menuChoice = systemIO.readIntOnly()) {
                case 1 -> showNews();
                case 2 -> showPatchNotes();
            }
        } while (menuChoice != 0);
    }
    public void createAdminMessage(int newsOrPatch){
        if (!daoManager.getPlayer().isAdmin()){
            return;
        }
        String messageType = newsOrPatch == 5 ? "patch" : "news";

        String version = "N/A";

        if (newsOrPatch == 5){
            systemIO.addString("Ange version\n>");
            version = systemIO.getString();
        }

        systemIO.addString("Ange rubrik\n>");
        String headLine = systemIO.getString();

        systemIO.addString("Ange textstycke\n>");
        String content = systemIO.getString();

        daoManager.createPatchNoteOrNewsletter(content, headLine, messageType, version);
    }

    private void showPatchNotes() {
        Optional<AdminMessage> optionalAdminMessage = daoManager.fetchLatestPatchNotes();
        if (optionalAdminMessage.isPresent()){
            AdminMessage adminMessage = optionalAdminMessage.get();
            Patch patch = new Patch(adminMessage.getVersion(), adminMessage.getDateTime(),
                    adminMessage.getMessage(), adminMessage.getHeadLine());
            systemIO.addString(patch.toString() + "\n");
        }
    }

    private void showNews() {
        Optional<AdminMessage> optionalAdminMessage = daoManager.fetchLatestNews();
        if (optionalAdminMessage.isPresent()){
            AdminMessage adminMessage = optionalAdminMessage.get();
            NewsLetter news = new NewsLetter(adminMessage.getMessage(), adminMessage.getDateTime(),
                    adminMessage.getHeadLine());
            systemIO.addString(news.toString() + "\n");
        }
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
