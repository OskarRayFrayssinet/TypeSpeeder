package se.ju23.typespeeder;
import org.springframework.stereotype.Service;
import java.util.Scanner;

@Service
public interface MenuService{
    void displayMenu();
    void displayLoginMenu();
    void selectMenuOptions();
}
