import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/typespeeder";
    private static final String JDBC_USER = "NG";
    private static final String JDBC_PASS = "Husby164";

    private static String loggedInAnvandarnamn = null;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nHuvudmeny");
            System.out.println("1. Logga in");
            System.out.println("2. Registrera nytt konto");
            System.out.println("3. Avsluta");

            System.out.print("Välj ett alternativ: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    registerAccount();
                    // Här bör du inte kalla på showGameMenu eller något som leder tillbaka till menyn.
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Ogiltigt val, försök igen.");
            }
        }
    }

    private static void login() {
        System.out.print("Ange användarnamn: ");
        String anvandarnamn = scanner.nextLine().trim();
        System.out.print("Ange lösenord: ");
        String losenord = scanner.nextLine().trim();

        String query = "SELECT Anvandarnamn FROM anvandare WHERE Anvandarnamn = ? AND Losenord = ?";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, anvandarnamn);
            pstmt.setString(2, losenord); // Antag att lösenordet inte är hashat för demonstrationens skull

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Användarnamn och lösenord matchar en användare i databasen
                    loggedInAnvandarnamn = anvandarnamn;
                    System.out.println("Du är nu inloggad.");
                    showGameMenu(); // Visa spelets meny efter framgångsrik inloggning
                } else {
                    // Inga poster matchade användarnamnet och lösenordet
                    System.out.println("Inloggningen misslyckades. Fel användarnamn eller lösenord.");
                }
            }
        } catch (Exception e) {
            System.out.println("Ett fel inträffade: " + e.getMessage());
        }
    }

    private static void registerAccount() {
        System.out.println("Registrera nytt konto:");
        System.out.print("Ange önskat användarnamn: ");
        String newUsername = scanner.nextLine();
        System.out.print("Ange önskat lösenord: ");
        String newPassword = scanner.nextLine();
        System.out.print("Ange önskat spelnamn: ");
        String newGameName = scanner.nextLine();

        // Anslutning till data3basen
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS)) {
            // Förbered SQL-frågan för att infoga en ny användare
            String query = "INSERT INTO anvandare (Anvandarnamn, Losenord, Spelnamn) VALUES (?, ?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, newUsername);
                stmt.setString(2, newPassword); // I en riktig applikation bör du hash:a lösenordet här
                stmt.setString(3, newGameName);

                // Exekvera SQL-frågan
                int affectedRows = stmt.executeUpdate();
                if (affectedRows > 0) {
                    System.out.println("Kontot har skapats.");
                } else {
                    System.out.println("Kunde inte skapa kontot.");
                }
            }
        } catch (Exception e) {
            System.out.println("Ett fel inträffade vid registrering: " + e.getMessage());
        }
    }




    private static void showGameMenu() {
        while (true) {
            System.out.println("\nVälkommen till spelet!");
            System.out.println("1. Spela");
            System.out.println("2. Rankinglista");
            System.out.println("3. Din Profil");
            System.out.println("4. Inställningar");
            System.out.println("5. Senaste uppdateringar");
            System.out.println("6. Logga ut");

            System.out.print("Välj ett alternativ: ");
            int gameChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (gameChoice) {
                case 1:
                    playGame();
                    break;
                case 2:
                    showRanking();
                    // Visa rankinglista
                    break;
                case 3:
                    showProfile();
                    // Visa användarens profil
                    break;
                case 4:
                    Settings(loggedInAnvandarnamn);
                    // Visa inställningar
                    break;
                case 5:
                    showLatestUpdate();
                    break;
                    case 6:
                        return; // Gå tillbaka till huvudmenyn
                default:
                    System.out.println("Ogiltigt val, försök igen.");
            }
        }
    }

    private static void playGame() {
        System.out.println("Välj språk för spelet:");
        System.out.println("1. Svenska");
        System.out.println("2. Engelska");
        int languageChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Här skulle logik för att starta spelet baserat på valt språk implementeras.
    }
    private static void showRanking() {
        System.out.println("\nRankinglista:");
        // Uppdaterad SQL-fråga för att hämta Spelnamn och Poäng
        String query = "SELECT Spelnamn, Poang FROM anvandare ORDER BY Poang DESC";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String spelnamn = rs.getString("Spelnamn");
                int poang = rs.getInt("Poang");
                System.out.println(spelnamn + " - Poäng: " + poang);
            }
        } catch (Exception e) {
            System.out.println("Ett fel inträffade vid hämtning av rankinglistan: " + e.getMessage());
        }
    }

    private static void showProfile() {
        if (loggedInAnvandarnamn == null) {
            System.out.println("Ingen användare är inloggad.");
            return;
        }

        String query = "SELECT Anvandarnamn, Spelnamn, Poang, Level FROM anvandare WHERE Anvandarnamn = ?";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, loggedInAnvandarnamn); // Sätter användarnamnet i frågan
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String anvandarnamn = rs.getString("Anvandarnamn");
                    String spelnamn = rs.getString("Spelnamn");
                    int poang = rs.getInt("Poang");
                    int level = rs.getInt("Level");
                    System.out.println("Användarnamn: " + anvandarnamn);
                    System.out.println("Spelnamn: " + spelnamn);
                    System.out.println("Poäng: " + poang);
                    System.out.println("Level: " + level);
                } else {
                    System.out.println("Användaren hittades inte.");
                }
            }
        } catch (Exception e) {
            System.out.println("Ett fel inträffade vid hämtning av användarprofil: " + e.getMessage());
        }
    }

    private static void Settings(String anvandarnamn) {
        if (anvandarnamn == null) {
            System.out.println("Ingen användare är inloggad.");
            return;
        }
        System.out.println("\nInställningar för " + anvandarnamn);
        System.out.println("1. Ändra spelnamn");
        System.out.println("2. Ändra lösenord");
        System.out.print("Välj ett alternativ: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                changeGameName(anvandarnamn);
                break;
            case 2:
                changePassword(anvandarnamn);
                break;
            default:
                System.out.println("Ogiltigt val, försök igen.");
                break;
        }
    }


    private static void changeGameName(String anvandarnamn) {
        System.out.print("Ange ditt nya spelnamn: ");
        String newGameName = scanner.nextLine();

        // SQL-fråga för att uppdatera spelnamnet
        String query = "UPDATE anvandare SET Spelnamn = ? WHERE Anvandarnamn = ?";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, newGameName);
            pstmt.setString(2, anvandarnamn);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Ditt spelnamn har uppdaterats.");
            } else {
                System.out.println("Uppdateringen misslyckades. Kontrollera att användarnamnet är korrekt.");
            }
        } catch (Exception e) {
            System.out.println("Ett fel inträffade: " + e.getMessage());
        }
    }

    private static void changePassword(String anvandarnamn) {
        System.out.print("Ange nytt lösenord: ");
        String newPassword = scanner.nextLine();

        // OBS: I en riktig applikation bör du hash:a och salta lösenordet innan du sparar det i databasen.
        String query = "UPDATE anvandare SET Losenord = ? WHERE Anvandarnamn = ?";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, newPassword); // Här bör du hash:a lösenordet
            stmt.setString(2, anvandarnamn);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Lösenordet har uppdaterats.");
            } else {
                System.out.println("Lösenordet kunde inte uppdateras.");
            }
        } catch (Exception e) {
            System.out.println("Ett fel inträffade vid uppdatering av lösenordet: " + e.getMessage());
        }
    }
    private static void showLatestUpdate() {
        System.out.println("Senaste uppdateringen:");
        String query = "SELECT titel, beskrivning, publiceringsdatum FROM uppdateringar ORDER BY publiceringsdatum DESC LIMIT 1";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                String titel = rs.getString("titel");
                String beskrivning = rs.getString("beskrivning");
                String publiceringsdatum = rs.getString("publiceringsdatum");
                System.out.println("Titel: " + titel);
                System.out.println("Publiceringsdatum: " + publiceringsdatum);
                System.out.println("Beskrivning: " + beskrivning);
            } else {
                System.out.println("Ingen uppdateringsinformation tillgänglig.");
            }
        } catch (Exception e) {
            System.out.println("Ett fel inträffade vid hämtning av uppdateringsinformation: " + e.getMessage());
        }
    }



    // Metoder för att hantera registrering, inloggning, och andra funktioner behöver implementeras här.
}
