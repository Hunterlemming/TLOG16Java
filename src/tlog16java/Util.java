package tlog16java;

import java.util.Scanner;
import java.util.ArrayList;

public class Util {
    
    public static Scanner userInput = new Scanner(System.in);
    
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
        return (minPerTask % 15) == 0;
    }
    
    public static boolean isSeparatedTime(WorkDay wd, Task t){
        boolean isSeparated=true;
        for (int i=0; i<wd.getTasks().size(); i++){
            if ( isInTheIntervall(t,wd.getTasks().get(i))==true ){
                isSeparated=false;
            }
        }
        return isSeparated;
    }
    
        private static boolean isInTheIntervall(Task newTask, Task oldTask){
            return ( newTask.getStartTime().compareTo(oldTask.getEndTime())<=0 && newTask.getEndTime().compareTo(oldTask.getStartTime())>=0 );
        }
        
    public static int getListElement(ArrayList imputList){
        int option=Integer.parseInt(userInput.nextLine())-1;

        while ( option>=imputList.size() || option<0 ){
            System.out.println("Please enter a valid number!");
            option=Integer.parseInt(userInput.nextLine())-1;
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
    
}
