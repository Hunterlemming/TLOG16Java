package tlog16java;

import java.util.List;
import java.time.YearMonth;

public class WorkMonth {
            
//---- Variables ----
    
    private List<WorkDay> days;
    private YearMonth date;
    private long sumPerMonth;
    private long requiredMinPerMonth;
        
//---- Constructors, Getters ----
    
    public WorkMonth(int _year, int _month){
        date=YearMonth.of(_year,_month);
    }
    
    public List<WorkDay> getDays() {
        return days;
    }
    
    public YearMonth getDate() {
        return date;
    }
    
    public long getSumPerMonth() {
        return sumPerMonth;
    }
    
    public long getRequiredMinPerMonth() {
        return requiredMinPerMonth;
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
        if ( isNewDate(wd)==true && isSameMonth(wd)==true )
            if ( isWeekendEnabled==true || ( wd.isWeekDay() && isWeekendEnabled==false ) ){
                days.add(wd);
            }
    }
    
}
