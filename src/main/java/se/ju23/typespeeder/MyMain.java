package se.ju23.typespeeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyMain implements CommandLineRunner {
    private Menu menu;
    private SystemIO systemIO;
    private Challenge challenge;
    private DAOManager daoManager;
    @Autowired
    private PlayerRepo playerRepo;
    @Autowired
    private TypeGoalTextRepo typeGoalTextRepo;
    @Autowired
    public MyMain(PlayerRepo playerRepo, TypeGoalTextRepo typeGoalTextRepo) {
        this.playerRepo = playerRepo;
        this.typeGoalTextRepo = typeGoalTextRepo;
        systemIO = new SystemIO();
        challenge = new Challenge();
        daoManager = new DAOManager();
        menu = new Menu();
        menu.setSystemIO(systemIO);
        menu.setPlayerRepo(playerRepo);
        menu.setDaoManager(daoManager);
        daoManager.setPlayerRepo(playerRepo);
        daoManager.setTypeGoalTextRepo(typeGoalTextRepo);
        challenge.setDaoManager(daoManager);
    }

    @Override
    public void run(String... args) {
        Controller controller = new Controller(menu, systemIO, challenge);
        controller.run();
    }
}
