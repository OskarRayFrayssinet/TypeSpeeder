package se.ju23.typespeeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    PlayersRepo playersRepo;
    @Autowired
    ResultatRepo resultatRepo;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("HALLO");
    }
}
