package se.ju23.typespeeder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class TypeSpeederApplication {

    public static void main(String[] args) {
        SpringApplication.run(TypeSpeederApplication.class, args);
        System.out.println(Arrays.toString(wordList()));
    }

    @Bean
    public static String[] wordList() {
        return new String[] {
            "hej",
            "på",
            "dig",
            "hur",
            "mår",
            "du",
            "jag",
            "är",
            "en",
            "robot",
            "som",
            "skriver",
            "snabbt",
            "och",
            "bra",
            "på",
            "att",
            "skriva",
            "ord",
            "och",
            "text",

        };
    }
}

