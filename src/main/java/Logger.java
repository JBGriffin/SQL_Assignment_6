/**
 * Created by Garrett on 12/5/2017.
 */
public class Logger {

    private boolean logOn;

    public Logger(){
        logOn = false;
    }

    public void debug(String msg){
        if(logOn){
            System.out.println("-== DEBUG: " + msg);
        }
    }

    public void info(String msg){
        if(logOn){
            System.out.println("-== INFO: " + msg);
        }
    }

    public void setLogOn(boolean flag){
        this.logOn = flag;
    }
}
