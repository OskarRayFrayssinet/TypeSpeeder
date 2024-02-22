package se.ju23.typespeeder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class AdminMessage {
    @Id
    private int id;
    private String message;
    @Column(name = "headline")
    private String headLine;
    @Column(name = "patch_or_news")
    private String patchOrNews;
    @Column(name = "date_time")
    private LocalDateTime dateTime;

    public AdminMessage(String message, String headLine, String patchOrNews) {
        this.message = message;
        this.headLine = headLine;
        this.patchOrNews = patchOrNews;
        this.dateTime = LocalDateTime.now();
    }

    public AdminMessage() {

    }

    public static void createPatchNoteOrNewsletter(String message, String headLine, String patchOrNews, DAOManager daoManager) {
        AdminMessage adminMessage = new AdminMessage(message, headLine, patchOrNews);
        daoManager.createAdminMessage(adminMessage);
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
