package se.ju23.typespeeder.service;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.IO.IO;
import se.ju23.typespeeder.enums.Status;
import se.ju23.typespeeder.repository.PlayerRepo;
import java.util.Scanner;

import static se.ju23.typespeeder.enums.Status.ERROR;
import static se.ju23.typespeeder.enums.Status.OK;

@Service
public class PlayerService {
    public Status checkCredentials(PlayerRepo playerRepo, IO io) {
        System.out.print("Please enter a username -> ");
        String username = io.getValidStringInput(new Scanner(System.in));
        System.out.print("Please enter a password -> ");
        String password = io.getValidStringInput(new Scanner(System.in));

        boolean playerExists = playerRepo.existsPlayerByUsernameAndPassword(username, password);

        if (playerExists) {
            return Status.OK;
        } else {
            System.out.println("Wrong username or password.");
            return Status.ERROR;
        }
    }
}

