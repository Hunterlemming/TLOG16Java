package tlog16java;

import java.util.Scanner;
import java.time.LocalTime;
import java.util.List;

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
        System.out.println("4. Add a new workmonth");
        System.out.println("5. Add a workday to a specific month");
        System.out.println("6. Add a task to a specific day");
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
            case 4:
                addMonth();
                break;
            case 5:
                addDayToMonth();
                break;
            case 6:
                addTaskToDay();
                break;
            case 99:
                test();
                break;
            default:
                System.out.println("Number \"" + procedure + "\" does not refer to a procedure!");
                break;
        }
        return true;
    }
    
        private void test(){
            System.out.println(workLog);
            System.out.println(workLog.getMonths().size());
        }
    
        private void listMonths(){
            if (workLog.getMonths().isEmpty()) {
                System.out.println("No months added yet.");
            } else {
                for (int i=0; i<workLog.getMonths().size(); i++){
                    System.out.println( (i+1) + ". " + workLog.getMonths().get(i).getDate().getYear() + "-" + workLog.getMonths().get(i).getDate().getMonth() );
                }
            }
        }
        
        private WorkMonth listDaysForMonth(){
            listMonths();
            WorkMonth invalidMonth = new WorkMonth(0,1);
            if (!workLog.getMonths().isEmpty()) {
                System.out.println("Choose a month to list workdays from!");
                WorkMonth chosenMonth = workLog.getMonths().get(getListElement(workLog.getMonths().size()));
                
                if (chosenMonth.getDays().isEmpty()){
                    System.out.println("No days added yet.");
                    return invalidMonth;
                } else {
                    for(int i=0; i<chosenMonth.getDays().size(); i++){
                        System.out.println((i+1) + " " + chosenMonth.getDays().get(i).getActualDay());
                    }
                    return chosenMonth;
                }
            } else {
                return invalidMonth; 
            }
        }
        
            private boolean listDaysForMonth(int MonthID){
                boolean notEmpty=true;
                WorkMonth chosenMonth = workLog.getMonths().get(MonthID);
                if (chosenMonth.getDays().isEmpty()){
                    notEmpty=false;
                    System.out.println("No days added yet.");
                } else {
                    for(int i=0; i<chosenMonth.getDays().size(); i++){
                        System.out.println((i+1) + " " + chosenMonth.getDays().get(i).getActualDay());
                    }
                }
                return notEmpty;
            }
            
            private int getListElement(int listsize){
                int option=Integer.parseInt(userInput.nextLine())-1;
                
                while ( option>=listsize || option<0 ){
                    System.out.println("Please enter a valid number!");
                    option=Integer.parseInt(userInput.nextLine())-1;
                }
                
                return option;
            }
        
        private void listTasksForDay(){
            WorkMonth chosenMonth = listDaysForMonth();
            if( chosenMonth.getDate().getYear()!=0 && chosenMonth.getDate().getMonth().getValue()!=1 ){
                System.out.println("Choose a day to list tasks from!");
                WorkDay chosenDay = chosenMonth.getDays().get(getListElement(chosenMonth.getDays().size()));
                
                if( chosenDay.getTasks().isEmpty()){
                    System.out.println("No tasks added yet.");
                } else {
                    for (int i=0; i<chosenDay.getTasks().size(); i++){
                        System.out.println("TaskId: " + chosenDay.getTasks().get(i).getTaskId() + ", Comment: " + chosenDay.getTasks().get(i).getComment());
                        System.out.println("StartTime: " + chosenDay.getTasks().get(i).getStartTime() + ", EndTime: " + chosenDay.getTasks().get(i).getEndTime() +"\n");
                    }
                }
            }
        }
        
        private void addMonth(){
            int year=0, month=0;
            
            boolean valid=false;
            while(valid==false){
                System.out.println("Please specify the year!");
                year=Integer.parseInt(userInput.nextLine());
                if (1900<=year) valid=true;
            }
            
            valid=false;
            while(valid==false){
                System.out.println("Please specify the month!");
                month=Integer.parseInt(userInput.nextLine());
                if ( 1<=month && month<=12 ) valid=true;
            }
            
            WorkMonth newMonth = new WorkMonth(year, month);
            workLog.addMonth(newMonth);
        }
        
        private void addDayToMonth(){
            listMonths();
            
            if (!workLog.getMonths().isEmpty()){
                System.out.println("Choose the month you wish to add a workday to!");
                int destinationMonthID=getListElement(workLog.getMonths().size());

                int destinationDay;
                int destinationMonth = workLog.getMonths().get(destinationMonthID).getDate().getMonth().getValue();
                int destinationYear = workLog.getMonths().get(destinationMonthID).getDate().getYear();

                do{
                    System.out.println("Which day of the month should the workday be on?");
                    destinationDay=Integer.parseInt(userInput.nextLine());
                }while(Util.validDate(destinationYear, destinationMonth, destinationDay)==false);

                System.out.println("What is the required worktime for the day (minutes) ? (write \"d\" for default (7.5 hours)");
                String workTime = userInput.nextLine();
                WorkDay newDay;
                if (workTime.equals("d")) {
                    newDay = new WorkDay(destinationYear,destinationMonth,destinationDay);
                } else {
                    long workTimeInMinutes=Long.parseLong(workTime);
                    newDay = new WorkDay(workTimeInMinutes,destinationYear,destinationMonth,destinationDay);
                }
                workLog.getMonths().get(destinationMonthID).addWorkDay(newDay);
            }
        }
        
        private void addTaskToDay(){
            listMonths();
            if (!workLog.getMonths().isEmpty()){
                System.out.println("Choose the month you wish to add the task to!");
                int destinationMonthID=getListElement(workLog.getMonths().size());
                
                WorkMonth destinationMonth = workLog.getMonths().get(destinationMonthID);
                if(listDaysForMonth(destinationMonthID)){
                    System.out.println("Choose the day you wish to add the task to!");
                    int destinationDayID=getListElement(destinationMonth.getDays().size());
                    WorkDay destinationDay = destinationMonth.getDays().get(destinationDayID);
                
                    Task newTask;
                    System.out.println("Please enter the taskID!");
                    String newTaskId=userInput.nextLine();
                    System.out.println("Please enter your comment to the task!");
                    String newComment=userInput.nextLine();
                    
                    String defaultEndTime=lastEndTimeOfTheDay(destinationDay);
                    System.out.println("Please enter the start of the task (hh:mm)! [" + defaultEndTime + "]");
                    String newStartTime=userInput.nextLine();
                    
                    if (newStartTime.equals("")){
                        newTask = new Task(newTaskId, newComment, defaultEndTime);
                    } else {
                        newTask = new Task(newTaskId, newComment, newStartTime);
                    }
                    
                    workLog.getMonths().get(destinationMonthID).getDays().get(destinationDayID).addTask(newTask);
                }
                
            }
        }
        
            private String lastEndTimeOfTheDay(WorkDay wd){
                String lastTime="00:00";
                
                if (!wd.getTasks().isEmpty()) {
                    int lastId=0;
                    LocalTime lastEndTime=wd.getTasks().get(0).getEndTime();
                    
                    for (int i = 0; i < wd.getTasks().size(); i++) {
                        if (wd.getTasks().get(i).getEndTime().compareTo(lastEndTime)>0){
                            lastEndTime=wd.getTasks().get(i).getEndTime();
                            lastId=i;
                        }
                    }
                    
                    lastTime = wd.getTasks().get(lastId).getEndTime().getHour() + ":" + wd.getTasks().get(lastId).getEndTime().getMinute();
                }
                
                return  lastTime;
            }
    
}