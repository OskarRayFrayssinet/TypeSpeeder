package se.ju23.typespeeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TypeSpeederApplication implements CommandLineRunner {


    @Autowired
    public static UserService userService;



    public static void main(String[] args) {

        SpringApplication.run(TypeSpeederApplication.class, args);

    }
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hej och välkommen till TypeSpeeder!");
        System.out.println("Börja med att ange dina inloggningsuppgifter nedan.");
        Menu.setUserService(userService);
        Menu.displayMenu();
    }



  //  @Bean
  //  public UserService userService(UserRepository userRepository) {
       // return new UserService(userRepository);
  //  }


    //System.setProperty("java.awt.headless", "false");

    // Menu.displayMenu();
    // Menu.logIn();
    //Menu.displayMenu();
    //Menu.openTextFile();


}


