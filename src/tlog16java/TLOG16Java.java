package tlog16java;

public class TLOG16Java {

    public static void main(String[] args) {
        TLOG16Java application = new TLOG16Java();
        application.start();
    }
    
    private TimeLogger workLog = new TimeLogger();
    
    public void start() {
        System.out.println("Precognox TimeLogger Project");
        TimeLoggerUIv2 UI = new TimeLoggerUIv2();
        
        boolean next = true;
        while (next==true){
            UI.showMenu(workLog);
            int procedure = Util.nextInt();
            next = UI.execute(procedure);
        }
    }
    
}