package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectorBBDD {
    private static final String URL = "jdbc:mysql://localhost:3306/Pixel?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWD = "root";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWD);
        } catch (SQLException e) {
            System.out.println("Trouble connecting to the database: " + e.getMessage());
            return null;
    }

    }
}
