package se.ju23.typespeeder.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.IO.IO;
import se.ju23.typespeeder.enums.Role;
import se.ju23.typespeeder.model.Patch;
import se.ju23.typespeeder.model.Player;
import se.ju23.typespeeder.repository.PatchRepo;
import java.time.LocalDateTime;


@Service
public class PatchService {

    @Autowired
    PatchRepo patchRepo;
    public void createPatchNews(int status, Player activePlayer, IO io) {
        Patch patch = new Patch();
        if (activePlayer.getRole().equals(Role.admin)){
            if (status == 1){
                System.out.println(io.getInGameMessages().get(5));
            } else if (status == 2){
                System.out.println(io.getInGameMessages().get(6));
            }
            String getContent = io.inputUserString();
            patch.setPatchVersion(getContent);
            patch.setReleaseDateTime(LocalDateTime.now());
            patchRepo.save(patch);
        } else {
            if (status == 1){
                System.out.println(io.getInGameMessages().get(3));
            } else if (status == 2){
                System.out.println(io.getInGameMessages().get(4));
            }
        }
    }

    public void displayPatchNews(){
        System.out.println(patchRepo.findAll());
    }

}

