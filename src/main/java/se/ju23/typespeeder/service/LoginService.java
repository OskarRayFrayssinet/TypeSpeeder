package se.ju23.typespeeder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.entity.UserRepository;

@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;

    public boolean login(String username, String password) {
        return userRepository.existsByUsernameAndPassword(username, password);
    }
}
