/*
 * Class: Newsletter
 * Description: A class that creates a newsletter.
 * Created by: Kerem Bjävenäs Tazedal
 * Email: kerem.tazedal@iths.se
 * Date: 2024-02-18
 */
package se.ju23.typespeeder.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "newsletter")
public class NewsLetter {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    private String content =  "Välkommen till vårt nyhetsbrev! Få de senaste nyheterna och exklusiva erbjudanden. Tack för att du är med oss!";

    @Column(name = "publishdate")
    public LocalDateTime publishDateTime = LocalDateTime.now();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public NewsLetter(String content, LocalDateTime publishDateTime) {
        this.content = content;
        this.publishDateTime = publishDateTime;
    }

    public NewsLetter() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public LocalDateTime getPublishDateTime() {
        return publishDateTime;
    }

    public void setPublishDateTime(LocalDateTime publishDateTime) {
        this.publishDateTime = publishDateTime;
    }

    public String getFormattedPublishDateTime() {
        return publishDateTime.format(formatter);
    }
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        System.out.println("");
        return "\nPubliceringsdatum: " + getFormattedPublishDateTime() + "\nSenaste nytt: " + "\n" + content;
    }
}
