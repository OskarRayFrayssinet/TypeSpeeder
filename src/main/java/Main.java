import se.ju23.typespeeder.GameChallenges;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/typespeeder";
    private static final String JDBC_USER = "NG";
    private static final String JDBC_PASS = "Husby164";

    private static String loggedInAnvandarnamn = null;
    private static GameChallenges gameChallenges = new GameChallenges(scanner);

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
            pstmt.setString(2, losenord);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    loggedInAnvandarnamn = anvandarnamn;
                    System.out.println("Du är nu inloggad.");
                    showGameMenu();
                } else {
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

        // Anslutning till databasen
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS)) {
            // Förbered SQL-frågan för att infoga en ny användare
            String query = "INSERT INTO anvandare (Anvandarnamn, Losenord, Spelnamn) VALUES (?, ?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, newUsername);
                stmt.setString(2, newPassword);
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
                    break;
                case 3:
                    showProfile();
                    break;
                case 4:
                    Settings(loggedInAnvandarnamn);
                    break;
                case 5:
                    showLatestUpdate();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Ogiltigt val, försök igen.");
            }
        }
    }

    private static void playGame() {
        do {
            System.out.println("Välkommen till spelmenyn!");
            System.out.println("Välj vilket spel du vill spela:");
            System.out.println("1. Räkna tecken");
            System.out.println("2. Hitta det markerade ordet");
            System.out.print("Ditt val: ");
            int gameChoice = scanner.nextInt();
            scanner.nextLine(); // Rensar bort newline-tecknet efter nummerinput

            switch (gameChoice) {
                case 1:
                    System.out.println("Välj svårighetsgrad:");
                    System.out.println("1. Lätt");
                    System.out.println("2. Medel");
                    System.out.println("3. Svårt");
                    System.out.print("Ditt val: ");
                    int difficulty = scanner.nextInt();
                    scanner.nextLine(); // Rensar bort newline-tecknet efter nummerinput
                    CharacterCountChallenge(difficulty); // Antag att denna metod hanterar spelet
                    break;
                case 2:
                    playFindMarkedWord(); // Antag att denna metod hanterar spelet
                    break;
                default:
                    System.out.println("Ogiltigt val, försök igen.");
            }

            // Här ska du lägga in koden för att fråga om användaren vill spela igen
            // Detta steg kommer direkt efter att ett spel har spelats
            if (!askToPlayAgain()) {
                break; // Bryt ut ur loopen om användaren väljer att inte spela igen
            }

        } while (true); // Loopen fortsätter om användaren väljer att spela igen
    }




    private static boolean CharacterCountChallenge(int difficulty) {
        int textLength = switch (difficulty) {
            case 1 -> 50;
            case 2 -> 100;
            case 3 -> 150;
            default -> 100;
        };

        char[] possibleChars = {'@', '#', '$', '%'};
        char targetChar = possibleChars[random.nextInt(possibleChars.length)];
        // Använder gameChallenges-instansen för att generera slumpmässig text
        String text = gameChallenges.generateRandomText(targetChar, textLength);

        System.out.println("Här är texten: " + text);
        System.out.println("Hur många gånger förekommer tecknet '" + targetChar + "' i texten ovan?");

        int userGuess = scanner.nextInt();
        long actualCount = text.chars().filter(ch -> ch == targetChar).count();

        if (userGuess == actualCount) {
            System.out.println("Rätt svar! Bra jobbat!");
        } else {
            System.out.println("Fel svar. Det rätta svaret är: " + actualCount);
        }

        return false;
    }

    private static boolean playFindMarkedWord() {
        System.out.println("Välj svårighetsgrad:");
        System.out.println("1. Lätt");
        System.out.println("2. Medel");
        System.out.println("3. Svårt");
        System.out.print("Ditt val: ");
        int difficultyChoice = scanner.nextInt();
        scanner.nextLine(); // Rensar bort newline-tecknet efter nummerinput

        int markedWordsCount = switch (difficultyChoice) {
            case 1 -> 2; // Lätt
            case 2 -> 4; // Medel
            case 3 -> 6; // Svårt
            default -> {
                System.out.println("Ogiltigt val, använder standardinställning 'Medel'.");
                yield 4; // Standardvärde för "Medel"
            }

        };

        List<String> words = new ArrayList<>(Arrays.asList("kaffe", "programmering", "musik", "bok", "dator", "penna", "glasögon", "telefon", "skrivbord", "stol"));
        Collections.shuffle(words);
        Set<String> markedWords = new HashSet<>(words.subList(0, markedWordsCount));

        System.out.println("\nSkriv in de markerade orden:");
        for (String word : words) {
            System.out.print(markedWords.contains(word) ? "[" + word + "] " : word + " ");
        }
        System.out.println("\nStartar tidtagning..");

        long startTime = System.currentTimeMillis();
        String input = scanner.nextLine().trim();
        long endTime = System.currentTimeMillis();

        Set<String> userInputWords = new HashSet<>(Arrays.asList(input.split("\\s+")));
        double secondsTaken = (endTime - startTime) / 1000.0;

        boolean isCorrect = userInputWords.equals(markedWords);

        if (isCorrect) {
            System.out.printf("Rätt! Det tog dig %.2f sekunder.\n", secondsTaken);
        } else {
            System.out.println("Fel eller ofullständigt svar.");
            System.out.println("De markerade orden var: " + markedWords);
        }

        // Returnera direkt utan att fråga om att spela igen
        return true; // Antag att spelet avslutades framgångsrikt
    }
    private static boolean askToPlayAgain() {
        System.out.println("Vill du spela igen eller gå tillbaka till huvudmenyn?");
        System.out.println("1. Ja, spela igen.");
        System.out.println("2. Nej, gå tillbaka till huvudmenyn.");
        System.out.print("Ditt val: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Hantera newline efter int-input
        return choice == 1;

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
}