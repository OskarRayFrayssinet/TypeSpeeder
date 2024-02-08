package se.ju23.typespeeder;

import java.sql.SQLException;

public interface Controllable {
    void run() throws SQLException, InterruptedException;
}
