package tlog16java;

    import java.util.Scanner;

public class TLOG16Java {

    public static void main(String[] args) {
        TLOG16Java application = new TLOG16Java();
        application.start();
    }
    
    private Scanner userInput = new Scanner(System.in);
    private TimeLogger workLog = new TimeLogger();
    
    public void start() {
        System.out.println("Precognox TimeLogger Project");
        TimeLoggerUI UI = new TimeLoggerUI();
        
        boolean next = true;
        while (next==true){
            UI.showMenu(workLog);
            int procedure = userInput.nextInt();
            next = UI.execute(procedure);
        }
    }
    
}
