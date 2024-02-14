package se.ju23.typespeeder;
import org.springframework.stereotype.Service;

@Service
public interface MenuService{
    void displayMenu();
    void displayLoginMenu();
    int selectMenuOptions();
}
