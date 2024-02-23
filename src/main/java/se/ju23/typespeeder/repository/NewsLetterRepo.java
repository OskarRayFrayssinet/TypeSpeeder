/*
 * Class: NewsletterRepo
 * Description: Manages newsletters with functionalities for storage and retrieval.
 * Author: Kerem Bjävenäs Tazedal
 * Email: kerem.tazedal@iths.se
 * Date: 2024-02-18
 */
package se.ju23.typespeeder.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.ju23.typespeeder.model.NewsLetter;

@Repository
public interface NewsLetterRepo extends JpaRepository <NewsLetter, Integer> {
}
