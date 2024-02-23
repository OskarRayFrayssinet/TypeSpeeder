package se.ju23.typespeeder.gameLogic;

import java.sql.SQLException;

public interface Controllable {
    void run() throws SQLException, InterruptedException;
}
