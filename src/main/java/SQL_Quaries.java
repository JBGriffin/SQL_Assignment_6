import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.Scanner;

/**
 * Created by Garrett on 12/5/2017.
 */
public class SQL_Quaries {


    private Logger log = new Logger();

    public SQL_Quaries(Connection conn, String dbQuariesFile){
        log.setLogOn(true);
        File file = new File(dbQuariesFile);

        Scanner scanner = null;

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
            e.printStackTrace();
        }

        while(scanner.hasNextLine()){
            log.debug(scanner.nextLine());
            processInputSwitches(scanner.nextLine());
        }
    }

    private void processInputSwitches(String line){

    }

    private void insert(String insertLine){

    }

    private void update(String updateLine){

    }

    private void delete(String deleteLine){

    }
}
