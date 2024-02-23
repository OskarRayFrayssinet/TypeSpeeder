/*
 * Class: PatchService
 * Description: A support class for the class Patch.
 * Author: Kerem Bjävenäs Tazedal
 * Email: kerem.tazedal@iths.se
 * Date: 2024-02-18
 */
package se.ju23.typespeeder.service;
import org.hibernate.cfg.StatisticsSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.IO.IO;
import se.ju23.typespeeder.IO.Menu;
import se.ju23.typespeeder.enums.Role;
import se.ju23.typespeeder.enums.Status;
import se.ju23.typespeeder.model.Patch;
import se.ju23.typespeeder.model.Player;
import se.ju23.typespeeder.repository.PatchRepo;
import java.time.LocalDateTime;


@Service
public class PatchService {

    @Autowired
    PatchRepo patchRepo;

    @Autowired
    Menu menu;

    @Autowired
    StatusService statusService;
    public void createPatchNews(Player activePlayer, IO io) {
        Patch patch = new Patch();
        if (activePlayer.getRole().equals(Role.admin)){
            if (statusService.getStatus().equals(Status.SVENSKA)){
                System.out.println(io.getInGameMessages().get(5));
            } else if (statusService.getStatus().equals(Status.ENGLISH)){
                System.out.println(io.getInGameMessages().get(6));
            }
            String getContent = io.inputUserString();
            patch.setPatchVersion(getContent);
            patch.setReleaseDateTime(LocalDateTime.now());
            patchRepo.save(patch);
        } else {
            if (statusService.getStatus().equals(Status.SVENSKA)){
                System.out.println(io.getInGameMessages().get(3));
            } else if (statusService.getStatus().equals(Status.ENGLISH)){
                System.out.println(io.getInGameMessages().get(4));
            }
        }
    }

    public void displayPatchNews(){
        System.out.println(patchRepo.findAll());
    }

}

