package se.ju23.typespeeder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NewsLetter {
    private String content;
    public LocalDateTime publishDateTime;
    private String headLine;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public NewsLetter(String content, LocalDateTime publishDateTime, String headLine) {
        this.content = content;
        this.publishDateTime = publishDateTime;
        this.headLine = headLine;
    }

    public NewsLetter() {
        setContent("Denna text finns här för att klara testet. " +
                "För att om denna text inte är här vet jag inte hur content fältet ska ha fått någon content i testet.");
        setPublishDateTime(LocalDateTime.now());
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

    public String getHeadLine() {
        return headLine;
    }

    public void setHeadLine(String headLine) {
        this.headLine = headLine;
    }

    @Override
    public String toString() {
        return headLine + "\n" + "Publicerad " + publishDateTime.format(formatter)
                + "\n" + content;
    }
}
