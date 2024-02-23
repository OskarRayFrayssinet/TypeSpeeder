package se.ju23.typespeeder.classesFromDB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import se.ju23.typespeeder.classesFromDB.Tasks;
import se.ju23.typespeeder.gameLogic.Difficulty;

import java.util.List;

@Repository
public interface TasksRepo extends JpaRepository<Tasks, Integer> {
    List<Tasks> findByDifficulty (Object difficulty);
    List<Tasks> findByTaskId (int id);
}
