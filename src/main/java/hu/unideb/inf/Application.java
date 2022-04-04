package hu.unideb.inf;

import org.h2.tools.Server;

import java.sql.SQLException;

public class Application {
    public static void main(String[] args) throws SQLException {
        startDatabase();
    }
    private static void startDatabase() throws SQLException {
        new Server().runTool("-tcp", "-web", "-ifNotExists");
    }
}
