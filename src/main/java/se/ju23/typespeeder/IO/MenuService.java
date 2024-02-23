/*
 * Class: MenuService
 * Description: A interface for MenuService.
 * Created by: Kerem Bjävenäs Tazedal
 * Email: kerem.tazedal@iths.se
 * Date: 2024-02-13
 */
package se.ju23.typespeeder.IO;

import org.springframework.stereotype.Service;
import se.ju23.typespeeder.enums.Status;

@Service
public interface MenuService {
    void displayLoginMenu();
    void displayMenu();
    int selectMenuOptions();
    Status displayMenuEnglish();
    void setGameLanguage();
    public int selectNewsOptions();
    void displayNewsMenu();
    int selectEditPlayerMenuOptions();
    void displayEditPlayersMenu();

}
