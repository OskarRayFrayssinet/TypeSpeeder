package se.ju23.typespeeder.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultatRepo extends JpaRepository<Resultat, Integer> {
    Resultat findById(int id);
    boolean existsById(int id);


}
