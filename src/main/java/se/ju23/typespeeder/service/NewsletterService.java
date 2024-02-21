package se.ju23.typespeeder.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.IO.IO;
import se.ju23.typespeeder.enums.Role;
import se.ju23.typespeeder.model.NewsLetter;
import se.ju23.typespeeder.model.Player;
import se.ju23.typespeeder.repository.NewsLetterRepo;
import java.time.LocalDateTime;

@Service
public class NewsletterService {


    @Qualifier("gameIO")
    @Autowired
    IO io;

    @Autowired
    NewsLetterRepo newsLetterRepo;

    public void createNewsLetter(int status, Player activePlayer) {
        NewsLetter newsLetter = new NewsLetter();
        if (activePlayer.getRole().equals(Role.admin)){
            if (status == 1){
                System.out.println(io.getInGameMessages().get(1));
            } else if (status == 2){
                System.out.println(io.getInGameMessages().get(2));
            }
            String getContent = io.inputUserString();
            newsLetter.setContent(getContent);
            newsLetter.setPublishDateTime(LocalDateTime.now());
            newsLetterRepo.save(newsLetter);
        } else {
            if (status == 1){
                System.out.println(io.getInGameMessages().get(3));
            } else if (status == 2){
                System.out.println(io.getInGameMessages().get(4));
            }
        }
    }

    public void displayNewsletters(){
        System.out.println(newsLetterRepo.findAll());
    }

}
