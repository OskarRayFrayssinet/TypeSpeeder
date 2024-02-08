package se.ju23.typespeeder.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ju23.typespeeder.data.model.Player;
import se.ju23.typespeeder.data.repo.PlayerRepo;
import se.ju23.typespeeder.data.service.PlayerService;


import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class Controller {
    Player player;

    //IO io;

    @Autowired
    PlayerRepo pRepo;

    @Autowired
    PlayerService pService;

    Scanner input = new Scanner(System.in);

    /*public Controller(IO io){
        this.io = io;
    }*/

    public void login(){
        System.out.println("Input logname to login: ");
        List<Player> playerList = pRepo.findAll();
        String inputName = input.nextLine();

        player = pService.getPlayerByLogName(inputName);
        //System.out.println(player);

        if (player != null){
            System.out.println("input password");
            String inputPassword = input.nextLine();
            if(player.getPassword().equals(inputPassword)){
                System.out.println("bingo");
            }else{
                System.out.println("wrong input");
                System.out.println("""
                        1. Try again
                        2. quit""");
            }
        }

    }

}
