package se.ju23.typespeeder.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.entity.ResultRepository;
import se.ju23.typespeeder.entity.UserRepository;

@Service
public class UserService implements IO {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ResultRepository resultRepository;


    @Override
    public void loginUser(String username, String password) {}

    @Override
    public void updateUsername(String oldUsername, String newUsername) {

    }

    @Override
    public void updatePassword(String username, String newPassword) {

    }

    @Override
    public void updateDisplayName(String username, String newDisplayName) {

    }

    @Override
    public void displayMainMenu() {

    }

    @Override
    public void displaySettingsMenu() {

    }

    @Override
    public void displayLanguageSelectionMenu() {

    }

    @Override
    public void displayRankings() {

    }

    @Override
    public void displayPlayerStats(String username) {

    }

    @Override
    public void displayNewsUpdates() {

    }

    @Override
    public void displayPatchNotes() {

    }
}
