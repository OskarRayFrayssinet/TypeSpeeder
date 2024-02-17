package se.ju23.typespeeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.User;
import se.ju23.typespeeder.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;
    // public UserService(UserRepository userRepository){


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User logIn(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

}
