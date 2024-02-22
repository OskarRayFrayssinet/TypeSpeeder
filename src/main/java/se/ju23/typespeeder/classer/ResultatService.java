package se.ju23.typespeeder.classer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.database.PlayersRepo;
import se.ju23.typespeeder.database.Resultat;
import se.ju23.typespeeder.database.ResultatRepo;

import java.util.Scanner;

@Service
public class ResultatService {

    @Autowired
    private ResultatRepo resultatRepo;

    public void saveResultat(Resultat resultat) {
        resultatRepo.save(resultat);
    }
}
