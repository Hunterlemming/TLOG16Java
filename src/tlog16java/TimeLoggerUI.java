package tlog16java;

public class TimeLoggerUI {
    
    public void showMenu(){
        System.out.println("-----------------------------------------------");
        System.out.println("Please choose an operation from the list below!");
        System.out.println("0. Exit");
        System.out.println("1. List months");
        System.out.println("2. List days");
        System.out.println("-----------------------------------------------");
    }
    
    public boolean execute(int procedure){
        switch (procedure){
            case 0:
                System.out.println("Thank you for using the system!");
                return false;
            case 1:
                listMonths();
                break;
            case 2:
                
                break;
            default:
                System.out.println("Number \"" + procedure + "\" does not refer to a procedure!");
                break;
        }
        return true;
    }
    
        private void listMonths(){
            TimeLogger timelog = new TimeLogger();
            for (int i=0; i<timelog.getMonths().size(); i++){
                System.out.println( (i+1) + ". " + timelog.getMonths().get(i).getDate().getYear() + "-" + timelog.getMonths().get(i).getDate().getMonth() );
            }
        }
    
}
