package se.ju23.typespeeder;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import se.ju23.typespeeder.service.GameService;

import java.util.Arrays;

@SpringBootApplication
public class TypeSpeederApplication {

    public static void main(String[] args) {
        SpringApplication.run(TypeSpeederApplication.class, args);

    }

    @Bean
    public CommandLineRunner runGame(GameService gameService) {
        return args -> gameService.startGame();
    }
}


