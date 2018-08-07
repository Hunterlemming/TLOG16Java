package tlog16java;

import java.util.Scanner;
import java.util.ArrayList;

public class Util {
    
    public static Scanner userInput = new Scanner(System.in);
    
    public static boolean isInt(String text){
        return text.matches("[0-9]+");
    }
    
    public static int nextInt(){
        String text = userInput.nextLine();
        while ( !text.matches("[0-9]+") ) {
                System.out.println("Please enter a valid value!");
                text = userInput.nextLine();
            }
        return Integer.parseInt(text);
    }
    
    public static long roundToMultipleQuarterHour(long minPerTask){
        long remainingToQuarter = minPerTask%15;
        if (remainingToQuarter<=7) {
            minPerTask=minPerTask-(15-remainingToQuarter);
        } else {
            minPerTask=minPerTask+remainingToQuarter;
        }
        return minPerTask;
    }
    
    public static boolean isMultipleQuarterHour(long minPerTask){
        return ((minPerTask % 15) == 0);
    }
    
    public static boolean isSeparatedTime(WorkDay wd, Task t){
        boolean isSeparated=true;
        for (int i=0; i<wd.getTasks().size(); i++){
            if (isInTheIntervall(t,wd.getTasks().get(i))==true){
                if (t.getTaskId().equals(wd.getTasks().get(i).getTaskId())){
                    if (intervallMerger(wd.getTasks().get(i), t)) wd.getTasks().remove(i);
                } else {
                    isSeparated=false;
                }
            }
        }
        return isSeparated;
    }
    
        private static boolean isInTheIntervall(Task newTask, Task oldTask){
            return ( newTask.getStartTime().compareTo(oldTask.getEndTime())<=0 && newTask.getEndTime().compareTo(oldTask.getStartTime())>=0 );
        }
        
        private static boolean intervallMerger(Task oldt, Task newt){
            long elapsedTimeInMinutes;
            if (oldt.getStartTime().compareTo(newt.getStartTime())<=0 && oldt.getEndTime().compareTo(newt.getEndTime())<=0) {
                elapsedTimeInMinutes = (newt.getEndTime().getHour()-oldt.getStartTime().getHour())*60 + newt.getEndTime().getMinute()-oldt.getStartTime().getMinute();
                if (isMultipleQuarterHour(elapsedTimeInMinutes)){
                    newt.setStartTime(oldt.getStartTime().getHour(), oldt.getStartTime().getMinute());
                    return true;
                }
            }
            if (newt.getStartTime().compareTo(oldt.getStartTime())<=0 && newt.getEndTime().compareTo(oldt.getEndTime())<=0) {
                elapsedTimeInMinutes = (oldt.getEndTime().getHour()-newt.getStartTime().getHour())*60 + oldt.getEndTime().getMinute()-newt.getStartTime().getMinute();
                if (isMultipleQuarterHour(elapsedTimeInMinutes)){
                    newt.setEndTime(oldt.getEndTime().getHour(), oldt.getEndTime().getMinute());
                    return true;
                }
            }
            return false;
        }
        
    public static int getListElement(ArrayList imputList){
        int option=Util.nextInt()-1;

        while ( option>=imputList.size() || option<0 ){
            System.out.println("Please enter a valid number!");
            option=Util.nextInt()-1;
        }
            
        return option;
    }
        
    public static boolean isWeekDay(WorkDay wd){
        return wd.getActualDay().getDayOfWeek().getValue()<=5;
    }
    
    public static boolean validDate(int year, int month, int day){
        boolean valid=false;
        if ( 1<=day && day<32 ) {
            if (month<8){
                if ( month%2==0 && day<=30 ) {
                    if (month==2){
                        if ( year%4==0 && day<=29 ) valid=true;
                        if ( year%4!=0 && day<=28 ) valid=true;
                    } else {
                        valid=true;
                    }
                }
                if ( month%2==1 && day<=31) {
                    valid=true;
                }
            } else {
                if ( month%2!=0 && day<=30 ) {
                    valid=true;
                }
                if ( month%2==0 && day<=31 ) {
                    valid=true;
                }
            }
        }
        return valid;
    }
    
    public static boolean validTaskTime( int startH, int startM, String time ){
        if (!time.contains(":"))  return false;
        if ( !isInt(time.split(":")[0]) || !isInt(time.split(":")[1]) ) return false; 
        int hour = Integer.parseInt(time.split(":")[0]), 
            min = Integer.parseInt(time.split(":")[1]);
        return ( (( 0<=hour && hour<=23 ) && ( 0<=min && min<=59 )) && 
               ( (hour>startH) || ( hour==startH && min > startM ) ) );
    }
    
    public static boolean validTask(WorkDay targetDay, Task newTask){
        if( isMultipleQuarterHour(newTask.getMinPerTask()) && newTask.isValidTaskId() && isSeparatedTime(targetDay, newTask) ){
            return true;
        } else {
            if (!isMultipleQuarterHour(newTask.getMinPerTask())) System.out.println("The tasktime cannot be divided into quarter hours.");
            if (!isSeparatedTime(targetDay, newTask)) System.out.println("Two tasktimes collide.");
            if (!newTask.isValidTaskId()) System.out.println("Invalid ID.");
            return false;
        }
    }
    
}
