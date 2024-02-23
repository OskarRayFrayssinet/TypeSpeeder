package se.ju23.typespeeder.classesFromDB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.ju23.typespeeder.classesFromDB.Users;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepo extends JpaRepository<Users, Integer> {
    Optional<Users> findByEmailAndPassword(String email, String password);
    Optional<Users> findByUserId(int id);
    List<Users> findByAlias(String alias);
    Optional<Users> findByEmail (String email);
}
