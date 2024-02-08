package se.ju23.typespeeder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.logic.IGameLogic;

@Service
public class GameService {
    private final IGameLogic gameLogic;

    @Autowired
    public GameService(IGameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    public void startGame() {
        gameLogic.startGame();
        // Anropa andra metoder på gameLogic vid behov
    }
}
