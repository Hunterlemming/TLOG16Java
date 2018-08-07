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
}
