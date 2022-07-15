package config.database;

import org.postgresql.ds.PGConnectionPoolDataSource;
import org.postgresql.ds.common.BaseDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class ConnectorDB {
    public static BaseDataSource getPGDataSource() throws IOException {
        Properties props = new Properties();

        FileInputStream fis = new FileInputStream("src/res/database.properties");
        props.load(fis);

        BaseDataSource ds = new PGConnectionPoolDataSource();
        ds.setURL(props.getProperty("postgresql.url"));
        ds.setUser(props.getProperty("postgresql.user"));
        ds.setPassword(props.getProperty("postgresql.password"));

        return ds;
    }
}
