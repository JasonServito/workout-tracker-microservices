package Persistence;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConfig {
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    static {
        Properties props = new Properties();
        try (InputStream input = DBConfig.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("config.properties not found in classpath"); 
            }
            props.load(input);

            URL = System.getenv().getOrDefault("DB_URL", props.getProperty("db.url"));
            USERNAME = System.getenv().getOrDefault("DB_USER", props.getProperty("db.username"));
            PASSWORD = System.getenv().getOrDefault("DB_PASSWORD", props.getProperty("db.password"));

        } catch (Exception e) {
            throw new RuntimeException("Could not load database configuration", e);
        }
    }

    public static Connection getCon() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            throw new RuntimeException("Database Connection Error", e); 
        }
    }
}
