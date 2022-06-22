package config.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnUtils {

    public static Connection getPostgresConnection() throws SQLException, ClassNotFoundException {

        String hostName = "localhost";

        String dbName = "store_app";
        String userName = "postgres";
        String password = "root";

        return getPostgresConnection(hostName, dbName, userName, password);
    }

    public static Connection getPostgresConnection(String hostName, String dbName,
                                                   String userName, String password)
            throws SQLException, ClassNotFoundException{
        Class.forName("org.postgresql.Driver");

        String connectionURL="jdbc:postgresql://"+ hostName+":5432/"+dbName;

        Connection conn= DriverManager.getConnection(connectionURL,userName,password);
        return conn;
    }
}
