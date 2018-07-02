package tlog16java;

import java.util.Scanner;

public class TimeLoggerUI {
    
    private Scanner userInput = new Scanner(System.in);
    private TimeLogger workLog;
    
    public void showMenu(TimeLogger _workLog){
        workLog=_workLog;
        System.out.println("-----------------------------------------------");
        System.out.println("Please choose an operation from the list below!");
        System.out.println("0. Exit");
        System.out.println("1. List months");
        System.out.println("2. List days for a specific month");
        System.out.println("3. List tasks for a specific day");
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
                listDaysForMonth();
                break;
            case 3:
                listTasksForDay();
                break;
            default:
                System.out.println("Number \"" + procedure + "\" does not refer to a procedure!");
                break;
        }
        return true;
    }
    
        private void listMonths(){
            for (int i=0; i<workLog.getMonths().size(); i++){
                System.out.println( (i+1) + ". " + workLog.getMonths().get(i).getDate().getYear() + "-" + workLog.getMonths().get(i).getDate().getMonth() );
            }
        }
        
        private WorkMonth listDaysForMonth(){
            listMonths();
            System.out.println("Choose a month to list workdays from!");
            int option=userInput.nextInt()-1;
            
            WorkMonth chosenMonth = workLog.getMonths().get(option);
            for(int i=0; i<chosenMonth.getDays().size(); i++){
                System.out.println((i+1) + " " + chosenMonth.getDays().get(i).getActualDay());
            }
            
            return chosenMonth;
        }
        
        private void listTasksForDay(){
            WorkMonth chosenMonth = listDaysForMonth();
            System.out.println("Choose a day to list tasks from!");
            int option=userInput.nextInt()-1;
            
            WorkDay chosenDay = chosenMonth.getDays().get(option);
            for (int i=0; i<chosenDay.getTasks().size(); i++){
                System.out.println("TaskId: " + chosenDay.getTasks().get(i).getTaskId() + ", Comment: " + chosenDay.getTasks().get(i).getComment());
                System.out.println("StartTime: " + chosenDay.getTasks().get(i).getStartTime() + ", EndTime: " + chosenDay.getTasks().get(i).getEndTime() +"\n");
            }
        }
    
}
