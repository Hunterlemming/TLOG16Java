package tlog16java;

    import java.util.Scanner;

public class TLOG16Java {

    public static void main(String[] args) {
        TLOG16Java application = new TLOG16Java();
        application.start();
    }
    
    public void start() {
        System.out.println("Precognox TimeLogger Project");
        TimeLoggerUI UI = new TimeLoggerUI();
        Scanner userInput = new Scanner(System.in);
        
        boolean next = true;
        while (next==true){
            UI.showMenu();
            int procedure = userInput.nextInt();
            next = UI.execute(procedure);
        }
    }
    
}
