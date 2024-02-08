package se.ju23.typespeeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.data.model.Player;
import se.ju23.typespeeder.data.repo.PlayerRepo;
import se.ju23.typespeeder.data.service.PlayerService;
import se.ju23.typespeeder.logic.Controller;

import java.util.List;
import java.util.Optional;

@Component
public class MyMain implements CommandLineRunner {
    @Autowired
    private PlayerRepo pRepo;
    @Autowired
    Controller controller;

    public static void main(String[] args){
        SpringApplication.run(TypeSpeederApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        controller.login();
    }
}
