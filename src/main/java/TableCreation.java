import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Table creation class creates a table off provided tableCreation.txt.
 * Created by Garrett on 12/5/2017.
 */
public class TableCreation {

    private static String TABLE_CREATION_FILE = "src//main//resources//tableCreation.txt";

    private String inputStatement = null;
    Statement statement = null;

    public TableCreation(Connection connection) throws SQLException {

        //STEP 4: Execute a query
        System.out.println("Creating database...");
        statement = connection.createStatement();
        File file = new File(TABLE_CREATION_FILE);
        Scanner scanner = null;

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
            e.printStackTrace();
        }

        while(scanner.hasNextLine()) {
            processInputFile(scanner.nextLine());
        }

        System.out.println("Database created successfully...");
    }

    /**
     * Reads input from the table creation file, executing the given SQL statements
     * @param line Next line in the input file
     * @throws SQLException Malformed SQL statement throws error
     */
    private void processInputFile(String line) throws SQLException {
        statement.executeUpdate(line);
    }
}
