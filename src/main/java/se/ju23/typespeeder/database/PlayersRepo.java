package se.ju23.typespeeder.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.ju23.typespeeder.classer.Players;

@Repository
public interface PlayersRepo extends JpaRepository<Players, Integer> {
    Players getPLayersByUsernameAndPassword(String username, String password);
    Players findByNickname (String nickname);
    Players findByUsername (String username);
    boolean existsById(int id);
}
