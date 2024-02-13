package se.ju23.typespeeder.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.ju23.typespeeder.enums.Role;
import se.ju23.typespeeder.model.Player;

@Repository
public interface PlayerRepo extends JpaRepository<Player, Integer> {
    boolean existsPlayerByUsernameAndPassword(String username, String password);
}
