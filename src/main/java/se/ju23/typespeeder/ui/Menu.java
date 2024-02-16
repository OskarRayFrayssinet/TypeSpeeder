package se.ju23.typespeeder.ui;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class Menu implements MenuService {

    @Override
    public void displayMenu() {
        for (String option : getMenuOptions()) {
            System.out.println(option);
        }
    }

    @Override
    public List<String> getMenuOptions() {
        return Arrays.asList(
                "1. Play",
                "2. Manage Account",
                "3. Language (Swe/Eng)",
                "4. Leaderboard",
                "5. Exit"
        );
    }

    public void StartMenu() {
        System.out.println("""
                
                1. Login.
                2. Create Account.
                0. Exit."""
        );
        System.out.print("\nYour choice: ");
    }


}
