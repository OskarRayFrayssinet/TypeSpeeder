package se.ju23.typespeeder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeGoalTextRepo extends JpaRepository<TypeGoalText, Integer> {
    @Query(value = "SELECT word_or_char FROM TypeSpeeder.string_source where is_swedish is true order by RAND() LIMIT ?", nativeQuery = true)
    List<String> getSwedishWords(int limit);
    @Query(value = "SELECT word_or_char FROM TypeSpeeder.string_source where is_swedish is false order by RAND() LIMIT ?", nativeQuery = true)
    List<String> getEnglishWords(int limit);
}
