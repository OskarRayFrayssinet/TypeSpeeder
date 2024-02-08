package se.ju23.typespeeder.constants;

public final class SqlConstants {
    public static final String URL = "jdbc:mysql://localhost/TypeSpeeder";
    public static final String USER = "root";
    public static final String PASSWORD = "root123!";
    public static final String insertSql = "INSERT INTO results (playerid, result) VALUES (?, ?)";
    public static final String checkSql1 = "SELECT * FROM players WHERE name = ?";
    public static final String checkSQL2 = "SELECT * FROM players";
    public static final String checkSql3 = "select * from results where playerid = ?";
}
