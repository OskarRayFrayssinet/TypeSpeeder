package se.ju23.typespeeder.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayersRepo extends JpaRepository<Players, Integer> {
    Players getPLayersByUsernameAndPassword(String username, String password);
    Players findByNickname (String nickname);
    Players findByUsername (String username);
    Players findById (int id);
    boolean existsById(int id);
}
