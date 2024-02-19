package se.ju23.typespeeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.User;
import se.ju23.typespeeder.UserRepository;

import java.util.Scanner;

@Component
public class UserService implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
  //  private UserRepository userRepository;
    // public UserService(UserRepository userRepository){


   // Scanner input = new Scanner(System.in);

    //@Autowired
   // public UserService(UserRepository userRepository) {
      //  this.userRepository = userRepository;
  //  }


   // public User logIn(String username, String password) {
    //    return userRepository.findByUsernameAndPassword(username, password);
   // }

  //  public void saveUser(User user) {
 //   }

    @Override
    public void run(String... args) throws Exception {

        Menu.setUserService(this);
        User u = Menu.logIn();
        if (u !=null){
            Menu.displayMenu();
        }
        Menu.updateUserProfile(u);
    }
}
