package hu.unideb.inf;

import org.h2.tools.Server;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException {
        startDatabase();
        MainApp.main(args);
        System.exit(0);
    }
    private static void startDatabase() throws SQLException {
        new Server().runTool("-tcp", "-web", "-ifNotExists");
    }
}
