package se.ju23.typespeeder.classesFromDB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.ju23.typespeeder.classesFromDB.Attempt;

import java.util.List;

@Repository
public interface AttemptRepo extends JpaRepository<Attempt, Integer> {
    List<Attempt> findByUserId(int id);
}
