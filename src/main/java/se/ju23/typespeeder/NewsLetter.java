package se.ju23.typespeeder;

import java.io.IOException;
import  java.time.LocalDateTime;

import static se.ju23.typespeeder.Menu.displayMenu;
import static se.ju23.typespeeder.Menu.input;

public class NewsLetter {
    private String content;
    private LocalDateTime publishDateTime;



    public NewsLetter(String content, LocalDateTime publishDateTime)throws IOException{
        this.content=content;
        this.publishDateTime=publishDateTime;
    }


    public static void showNewsAndUpdates() throws IOException {
        System.out.println("Nyheter och uppdateringar:");
        System.out.println("Nu finns möjlighet att spela spelet på engelska!");

        System.out.println("Vill du återgå till huvudmenyn? (ja/nej): ");
        String goBack = input.nextLine().toLowerCase();

        if ("ja".equals(goBack)) {
            displayMenu();
        } else {
            System.out.println("Programmet avslutas.");
        }
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
