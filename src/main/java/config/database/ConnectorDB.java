package config.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectorDB {
    //todo ???
    public static Connection getConnection() throws SQLException {
        ResourceBundle resource = ResourceBundle.getBundle("store_app");
        String url = resource.getString("jdbc:postgresql://localhost:5432/store_app");
        String user = resource.getString("postgres");
        String pass = resource.getString("db.password");
        String dbName = resource.getString("db.name");

        return DriverManager.getConnection(url + dbName, user, pass);
    }

    public static void closeConnection() {
        //todo ???
        Connection connection = null;
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
