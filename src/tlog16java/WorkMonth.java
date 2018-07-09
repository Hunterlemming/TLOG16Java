package tlog16java;

import java.util.List;
import java.time.YearMonth;
import java.util.ArrayList;


public class WorkMonth {
            
//---- Variables ----
    
    private ArrayList<WorkDay> days = new ArrayList<>();
    private YearMonth date;
    private long sumPerMonth;
    private long requiredMinPerMonth;
        
//---- Constructor, Getters, Setters ----
    
    public WorkMonth(int _year, int _month){
        date=YearMonth.of(_year,_month);
    }
    
    public ArrayList<WorkDay> getDays() {
        return days;
    }
    
    public YearMonth getDate() {
        return date;
    }
    
    public long getSumPerMonth() {
        return sumPerMonth;
    }
    
        private void setSumPerMonth(){
            for (int i=0; i<days.size(); i++){
                sumPerMonth=sumPerMonth+days.get(i).getSumPerDay();
            }
        }
    
    public long getRequiredMinPerMonth() {
        setRequiredMinPerMonth();
        return requiredMinPerMonth;
    }
    
        private void setRequiredMinPerMonth(){
            for(int i=0; i<days.size(); i++){
                requiredMinPerMonth=requiredMinPerMonth+days.get(i).getRequiredMinPerDay();
            }
        }
        
//---- User Methods ----       
    
    public long getExtraMinPerMonth(){
        return sumPerMonth-requiredMinPerMonth;
    }
    
    public boolean isNewDate(WorkDay wd){
        boolean isNew=true;
        for (int i=0; i<days.size(); i++){
            if (wd.equals(days.get(i))){
                isNew=false;
            }
        }
        return isNew;
    }
    
    public boolean isSameMonth(WorkDay wd){
        if(wd.getActualDay().getMonth()==date.getMonth()){
            return true;
        } else {
            return false;
        }
    }
    
    public void addWorkDay(WorkDay wd, boolean isWeekendEnabled){
        if ( isNewDate(wd)==true && isSameMonth(wd)==true ){
            if ( isWeekendEnabled==true || ( Util.isWeekDay(wd) && isWeekendEnabled==false ) ){
                days.add(wd);
            } else {
                if ( Util.isWeekDay(wd)==false && isWeekendEnabled==false ) System.out.println("The given workday is on a weekend!");
            }
        } else {
            if (isNewDate(wd)==false) System.out.println("The given workday already exists!");
            if (isSameMonth(wd)==false) System.out.println("The given workday does not belong to the given month!");
        }
    }
    
    public void addWorkDay(WorkDay wd){
        if ( isNewDate(wd)==true && isSameMonth(wd)==true && Util.isWeekDay(wd) ){
            days.add(wd);
        } else {
            if (Util.isWeekDay(wd)==false) System.out.println("The given workday is on a weekend!");
            if (isNewDate(wd)==false) System.out.println("The given workday already exists!");
            if (isSameMonth(wd)==false) System.out.println("The given workday does not belong to the given month!");
        }
    }
    
}