package se.ju23.typespeeder.InfoForUsers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NewsLetter {
    public String content = "När kvällen närmar sig, målar solens sista strålar himlen i en mångfärgad palett av rosa och orange, och stjärnorna tänds på himlen ";


    public LocalDateTime publishDateTime;

    public NewsLetter(String content, LocalDateTime publishDateTime) {
        this.content = content;
        this.publishDateTime = publishDateTime;
    }

    public NewsLetter() {
        this.content = getContent();
        this.publishDateTime = LocalDateTime.now();
    }

    public String getContent() {
        return content;
    }
    public String getPublishDateTime() {
        return getFormattedPublishDateTime();
    }
    public String getFormattedPublishDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return publishDateTime.format(formatter);
    }

    @Override
    public String toString() {
        return "NewsLetter{" +
                "content='" + content + '\'' +
                ", publishDateTime=" + getFormattedPublishDateTime() +
                '}';
    }
}
