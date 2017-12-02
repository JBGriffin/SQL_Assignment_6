import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by garre on 11/30/2017.
 */
public class DatabaseConnector {

    Properties prop = new Properties();

    // Mysql DataSource
    private MysqlDataSource dataSource = null;

    // Datasource connection
    private Connection dsConn = null;

    public DatabaseConnector() throws SQLException, FileNotFoundException {
        
        InputStream input = null;
        input = new FileInputStream("src//main//resources//db.properties");
        try {
            prop.load(input);
        } catch (IOException e) {
            System.err.println("Unable to load properties file");
            e.printStackTrace();
        }

        String sqlURL = prop.getProperty("url");
        String userName = prop.getProperty("username");
        String password = prop.getProperty("password");

        dsConn = DriverManager.getConnection(sqlURL, userName, password);
        if(dsConn != null){
            System.out.println("Connected to database!");
        }
    }

    public void closeConnection(){
        try {
            dsConn.close();
        } catch (SQLException e) {
            System.err.println("Could not close connection to data base");
            e.printStackTrace();
        }
    }
}
