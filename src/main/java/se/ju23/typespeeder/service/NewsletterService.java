/*
 * Class: NewsletterService
 * Description: A support class for the class Newsletter.
 * Author: Kerem Bjävenäs Tazedal
 * Email: kerem.tazedal@iths.se
 * Date: 2024-02-18
 */
package se.ju23.typespeeder.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.IO.IO;
import se.ju23.typespeeder.IO.Menu;
import se.ju23.typespeeder.enums.Role;
import se.ju23.typespeeder.enums.Status;
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
    @Autowired
    Menu menu;

    @Autowired
    StatusService statusService;

    public void createNewsLetter(Player activePlayer) {
        NewsLetter newsLetter = new NewsLetter();
        if (activePlayer.getRole().equals(Role.admin)){
            if (statusService.getStatus().equals(Status.SVENSKA)){
                System.out.println(io.getInGameMessages().get(1));
            } else if (statusService.getStatus().equals(Status.ENGLISH)){
                System.out.println(io.getInGameMessages().get(2));
            }
            String getContent = io.inputUserString();
            newsLetter.setContent(getContent);
            newsLetter.setPublishDateTime(LocalDateTime.now());
            newsLetterRepo.save(newsLetter);
        } else {
            if ((statusService.getStatus().equals(Status.SVENSKA))){
                System.out.println(io.getInGameMessages().get(3));
            } else if (statusService.getStatus().equals(Status.ENGLISH)){
                System.out.println(io.getInGameMessages().get(4));
            }
        }
    }

    public void displayNewsletters(){
        System.out.println(newsLetterRepo.findAll());
    }

}
