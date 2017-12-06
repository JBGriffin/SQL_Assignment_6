import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class Driver {
    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_RESOURCE_FILE = "src//main//resources//db.properties";
    private static final String TABLE_CREATION_FILE = "src//main//resources//tableCreation.txt";
    private static final String DB_QUARIES_FILE = "src//main//resources//databaseQuaries.txt";

    private static Logger log = new Logger();

    public static void main(String[] args) {
        log.setLogOn(true);
        Connection conn = null;
        Statement stmt = null;
        TableCreation tableCreation;
        SQL_Quaries quaries;

        try{
            conn = connectToDatabase();
            tableCreation = new TableCreation(conn, TABLE_CREATION_FILE);
            quaries = new SQL_Quaries(conn, DB_QUARIES_FILE);

        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }
    }//end main

    /**
     * Creates a connection to the database
     * @return Active connection to the database
     * @throws ClassNotFoundException If driver can not be created
     * @throws SQLException Malformed Database connection
     */
    private static Connection connectToDatabase() throws ClassNotFoundException, SQLException {
        //  Database credentials
        String databaseURL;
        String username;
        String password;
        InputStream input;
        Properties prop = new Properties();
        try {
            input = new FileInputStream(DB_RESOURCE_FILE);
            prop.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        databaseURL = prop.getProperty("url");
        username = prop.getProperty("username");
        password = prop.getProperty("password");

        // Register JDBC driver
        Class.forName(JDBC_DRIVER);

        // Open a connection
        log.info("Connecting to database...");
        return DriverManager.getConnection(databaseURL, username, password);
    }


}


