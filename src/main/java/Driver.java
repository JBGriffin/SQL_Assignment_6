import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Driver {
    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/";

    //  Database credentials
    private static final String USER = "root";
    private static final String PASS = "admin";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        TableCreation tableCreation;
        try{
            conn = connectToDatabase();

            tableCreation = new TableCreation(conn);

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
        // Register JDBC driver
        Class.forName(JDBC_DRIVER);

        // Open a connection
        System.out.println("Connecting to database...");
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }


}


