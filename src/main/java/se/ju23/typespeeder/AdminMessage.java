package se.ju23.typespeeder;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Table(name = "adminmessages")
@Entity
public class AdminMessage {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private String message;
    @Column(name = "headline")
    private String headLine;
    @Column(name = "patch_or_news")
    private String patchOrNews;
    @Column(name = "date_time")
    private LocalDateTime dateTime;
    private String version;

    public AdminMessage(String message, String headLine, String patchOrNews, String version) {
        this.message = message;
        this.headLine = headLine;
        this.patchOrNews = patchOrNews;
        this.version = version;
        this.dateTime = LocalDateTime.now();
    }

    public AdminMessage() {

    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getHeadLine() {
        return headLine;
    }

    public void setHeadLine(String headLine) {
        this.headLine = headLine;
    }

    public String getPatchOrNews() {
        return patchOrNews;
    }

    public void setPatchOrNews(String patchOrNews) {
        this.patchOrNews = patchOrNews;
    }

    public int getId() {
        return id;
    }
}
