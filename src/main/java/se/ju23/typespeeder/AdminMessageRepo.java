package se.ju23.typespeeder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminMessageRepo extends JpaRepository<AdminMessage, Integer> {
    @Query(value = "SELECT * FROM TypeSpeeder.adminmessages WHERE" +
            " adminmessages.patch_or_news = 'patch' ORDER BY date_time desc limit 1", nativeQuery = true)
    Optional<AdminMessage> findLatestPatchNotes();
    @Query(value = "SELECT * FROM TypeSpeeder.adminmessages WHERE" +
            " adminmessages.patch_or_news = 'news' ORDER BY date_time desc limit 1", nativeQuery = true)
    Optional<AdminMessage> findLatestNews();
}
