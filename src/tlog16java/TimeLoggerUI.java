package tlog16java;

public class TimeLoggerUI {
    
    public void showMenu(){
        System.out.println("Please choose an operation from the list below!");
    }
    
    public boolean execute(int procedure){
        switch (procedure){
            case 0:
                System.out.println("Thank you for using the system!");
                return false;
            default:
                System.out.println("Number \"" + procedure + "\" does not refer to a procedure!");
                break;
        }
        return true;
    }
    
}
