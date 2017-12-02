import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;

public class Driver {

    private static DatabaseConnector databaseConnector;



    public static void main(String[] args){
        File file = new File("src//main//resources//tableCreation.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
            e.printStackTrace();
        }

        try {
            databaseConnector = new DatabaseConnector();
        } catch (SQLException e) {
            System.err.println("Could not connect to data base.");
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.err.println("Unable to read from properties file");
            e.printStackTrace();
        }

        while(scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }
        databaseConnector.closeConnection();
    }
}
