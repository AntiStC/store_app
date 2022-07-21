package config.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectorDB {
    public static Connection getConnection(){
        ResourceBundle resource = ResourceBundle.getBundle("database");
        try {
            Class.forName("db.driver");

            String url = resource.getString("db.url");
            String user = resource.getString("db.user");
            String pass = resource.getString("db.password");
            String dbName = resource.getString("db.name");

        return DriverManager.getConnection(url + dbName, user, pass);
    }catch (SQLException | ClassNotFoundException e) {
        }
        return null;
    }
}
