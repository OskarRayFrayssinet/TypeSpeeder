package se.ju23.typespeeder.ui;

import org.springframework.stereotype.Service;

import java.util.List;
public interface MenuService {
    void displayMenu();
    List<String> getMenuOptions();
}




