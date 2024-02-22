package se.ju23.typespeeder.classer;
import java.time.LocalDateTime;

public class NewsLetter {
    public String content;
    public LocalDateTime publishDateTime;

    public NewsLetter() {

        this.content = "NEWS ABOUT NEW CONTENT, consectetur adipiscing elit. \" +\n" +
                "                       \"Nulla eget arcu nec turpis eleifend dictum.\";";
        this.publishDateTime = LocalDateTime.of(2024, 02, 18, 13, 34, 25);
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
}
