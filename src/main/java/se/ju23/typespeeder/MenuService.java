package se.ju23.typespeeder;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.enums.Status;

@Service
public interface MenuService{
    Status displayMenu();
    void displayLoginMenu();
    int selectMenuOptions(Status status);
}
