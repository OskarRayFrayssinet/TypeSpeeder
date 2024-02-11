package se.ju23.typespeeder.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.ju23.typespeeder.Players;

@Repository
public interface PlayersRepo extends JpaRepository<Players, Integer> {
}
