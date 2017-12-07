import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Created by Garrett on 12/5/2017.
 */
public class SQL_Queries {
    // SQL Query calls
    private static final String DELETE_STMT = "DELETE";
    private static final String UPDATE_STMT = "UPDATE";
    private static final String INSERT_STMT = "INSERT";

    // Constants
    private static final int KEY_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    private Logger log = new Logger();
    private Statement statement = null;


    public SQL_Queries(Connection conn, String dbQuariesFile) throws SQLException {
        log.setLogOn(true);
        statement = conn.createStatement();
        File file = new File(dbQuariesFile);
        String readLine;
        Scanner scanner = null;

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
            e.printStackTrace();
        }


        while(scanner.hasNextLine()){
            readLine = scanner.nextLine();
            processInputSwitches(readLine);
        }
    }

    private void processInputSwitches(String line){
        String [] stmtLine = line.split(",");
        String query = stmtLine[0];
        String table = stmtLine[1];

        switch(query.toUpperCase()){
            case DELETE_STMT:

                break;
            case INSERT_STMT:
                executeInsert(stmtLine);
                break;
            case UPDATE_STMT:

                break;
            default:

        }
    }

    /**
     * Grabs the table from the first index of the array, and sets the key and values
     * from the rest of the elements of the array. Executes the SQL statement after values
     * have been set
     * @param insertValues Assumes the first index is the query type, second is the table, and
     *                     the rest of the values to be key/value pairs separated by '=' signs
     */
    private void executeInsert(String [] insertValues){
        String table = insertValues[1];
        String [] keyValueArr;
        StringBuilder values = new StringBuilder();
        StringBuilder keys = new StringBuilder();
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO `" + table.trim() + "` (");

        values.append("VALUES (");

        for(int i = 2; i < insertValues.length; i++){
            keyValueArr = insertValues[i].split("=");

            if(insertValues.length - 1 == i){
                keys.append(keyValueArr[KEY_INDEX].trim() + ") ");
                values.append("'" + keyValueArr[VALUE_INDEX] + "');");
            } else {
                keys.append(keyValueArr[KEY_INDEX].trim() + ", ");
                values.append("'" + keyValueArr[VALUE_INDEX] + "', ");
            }
        }

        query.append(keys.append(values));
        try {
            statement.execute(query.toString());
        } catch (SQLException e) {
            log.debug("Statement executed: " + query.toString());
            /**
             * TODO: Error Trap
             */
            e.printStackTrace();
        }
        log.debug(query.toString());
    }

    private void update(String updateLine){

    }

    private void delete(String deleteLine){

    }


}
