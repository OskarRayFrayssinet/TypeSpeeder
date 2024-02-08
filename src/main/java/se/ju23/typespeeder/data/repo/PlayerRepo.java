package se.ju23.typespeeder.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.ju23.typespeeder.data.model.Player;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepo extends JpaRepository<Player, Integer> {
    Optional<Player> findByLogName(String logName);
    Optional<Player> deleteByLogName(String logName);
    List<Player> findAllBy();
}
