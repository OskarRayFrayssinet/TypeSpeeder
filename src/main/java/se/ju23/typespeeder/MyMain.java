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
    private RankingsRepo rankingsRepo;
    @Autowired
    private AdminMessageRepo adminMessageRepo;
    @Autowired
    public MyMain(PlayerRepo playerRepo, TypeGoalTextRepo typeGoalTextRepo,
                  RankingsRepo rankingsRepo, AdminMessageRepo adminMessageRepo) {
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
        daoManager.setRankingsRepo(rankingsRepo);
        daoManager.setAdminMessageRepo(adminMessageRepo);
        challenge.setDaoManager(daoManager);
    }

    @Override
    public void run(String... args) {
        Controller controller = new Controller(menu, systemIO, challenge, daoManager);
        controller.run();
    }
}
