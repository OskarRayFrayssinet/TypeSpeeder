package se.ju23.typespeeder.database;

import se.ju23.typespeeder.entity.Result;
import se.ju23.typespeeder.entity.User;


import java.util.List;

public interface IDb {

    // Användarhantering
    void addUser(String name);
    void updateUser(int userId, String newName);
    User getUser(int userId);
    List<User> getAllUsers();

    // Hantering av resultat
    void addResult(int userId, int result);
    List<Result> getResultsForUser(int userId);

    // Leaderboard och rankning
    List<Result> getTopResults(int limit); // Hämtar top-resultaten, möjligen med en gräns
    void updateLeaderboard(); // Kan användas om du väljer att ha en separat tabell för leaderboard
    //List<LeaderboardEntry> generateLeaderboard(); // Dynamiskt generera leaderboard via SQL-view eller query

}

