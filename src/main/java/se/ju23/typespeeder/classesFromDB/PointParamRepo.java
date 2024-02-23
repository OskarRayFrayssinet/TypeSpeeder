package se.ju23.typespeeder.classesFromDB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.ju23.typespeeder.classesFromDB.PointParam;

import java.util.List;

@Repository
public interface PointParamRepo extends JpaRepository<PointParam, Integer> {
    List<PointParam> findPointParamByAttemptId(int id);
    List<PointParam> findPointParamByAttemptByAttemptId(Attempt attemptByAttemptId);
}
