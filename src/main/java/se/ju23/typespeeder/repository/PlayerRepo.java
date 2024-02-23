/*
 * Class: PlayerRepo
 * Description: Manages players with functionalities for storage and retrieval.
 * Author: Kerem Bjävenäs Tazedal
 * Email: kerem.tazedal@iths.se
 * Date: 2024-02-18
 */
package se.ju23.typespeeder.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.ju23.typespeeder.enums.Role;
import se.ju23.typespeeder.model.Player;

@Repository
public interface PlayerRepo extends JpaRepository<Player, Integer> {
    boolean existsPlayerByUsernameAndPassword(String username, String password);
    Player findPlayerByUsernameAndPassword(String username, String password);
    boolean existsUserById(int id);
}
