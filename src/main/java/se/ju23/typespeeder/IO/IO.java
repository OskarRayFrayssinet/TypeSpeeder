package se.ju23.typespeeder.IO;
import org.springframework.stereotype.Repository;
import java.util.Scanner;

@Repository
public interface IO {
    String getValidStringInput(Scanner input);
    int getValidIntegerInput(Scanner input, int minValue, int maxValue);

    Scanner returnScanner();
}
