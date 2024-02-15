package se.ju23.typespeeder.classesFromDB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.ju23.typespeeder.classesFromDB.Attempt;

@Repository
public interface AttemptRepo extends JpaRepository<Attempt, Integer> {

}
