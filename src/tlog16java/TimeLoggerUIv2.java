package tlog16java;

public class TimeLoggerUIv2 {
    
    private TimeLogLister lister;
    private TimeLogAdder adder;
    
    public void showMenu(TimeLogger _workLog){
        lister=new TimeLogLister(_workLog);
        adder=new TimeLogAdder(_workLog);
        
        System.out.println("-----------------------------------------------");
        System.out.println("Please choose an operation from the list below!");
        System.out.println("0. Exit");
        System.out.println("1. List months");
        System.out.println("2. List days for a specific month");
        System.out.println("3. List tasks for a specific day");
        System.out.println("4. Add a new workmonth");
        System.out.println("5. Add a workday to a specific month");
        System.out.println("6. Add a task to a specific day");
        System.out.println("7. Finish a specific task");
        System.out.println("8. Delete a specific task");
        System.out.println("9. Modify a specific task");
        System.out.println("-----------------------------------------------");
    }
    
    public boolean execute(int procedure){
        switch (procedure){
            case 0:
                System.out.println("Thank you for using the system!");
                return false;
            case 1:
                lister.listMonths();
                break;
            case 2:
                lister.listDaysforMonth();
                break;
            case 3:
                lister.listTasksForDay(true);
                break;
            case 4:
                adder.addMonth();
                break;
            case 5:
                adder.addDayToMonth();
                break;
            case 6:
                adder.addTaskToDay();
                break;
            case 7:
                adder.finishTask();
                break;
            case 8:
                adder.deleteTask();
                break;
            case 9:
                adder.modifyTask();
                break;
            default:
                System.out.println("Number \"" + procedure + "\" does not refer to a procedure!");
                break;
        }
        return true;
    }
    
}
