package se.ju23.typespeeder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepo extends JpaRepository<Player, Integer> {
    Optional<Player> findByUserName(String username);

    @Query(value = "SELECT * FROM TypeSpeeder.player " +
            "ORDER BY player.ranking DESC LIMIT 10", nativeQuery = true)
    List<Player> findTop10levelPlayer();
}
