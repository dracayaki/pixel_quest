package model.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLOutput;

public class connectorBBDD {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/?user=root";
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
