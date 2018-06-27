package tlog16java;

import java.util.List;
import java.time.LocalDate;

public class WorkDay {
        
//---- Variables ----
    
    private List<Task>tasks;
    private long requiredMinPerDay=450;
    private LocalDate actualDay=LocalDate.now();
    private long sumPerDay;
        
//---- Constructors, Getters ----
    
    public WorkDay(long _requiredMinPerDay, int _actualYear, int _actualMonth, int _actualDay){
        requiredMinPerDay=_requiredMinPerDay;
        actualDay=LocalDate.of(_actualYear, _actualMonth, _actualDay);
    }
    
    public WorkDay(long _requiredMinPerDay){
        requiredMinPerDay=_requiredMinPerDay;
    }
    
    public WorkDay(int _actualYear, int _actualMonth, int _actualDay){
        actualDay=LocalDate.of(_actualYear, _actualMonth, _actualDay);
    }
    
    public WorkDay(){
    }
    
    public long getRequiredMinPerDay() {
        return requiredMinPerDay;
    }
    
    public LocalDate getActualDay() {
        return actualDay;
    }
    
    public long getSumPerDay() {
        return sumPerDay;
    }
        
//---- User Methods ----       
    
    public long getExtraMinPerDay(){
        return sumPerDay-requiredMinPerDay;
    }
    
    public boolean isSeparatedTime(Task t){
        boolean isSeparated=true;
        for (int i=0; i<tasks.size(); i++){
            if ( isInTheIntervall(t,tasks.get(i))==true ){
                isSeparated=false;
            }
        }
        return isSeparated;
    }
    
        private boolean isInTheIntervall(Task newTask, Task oldTask){
            return ( ( newTask.getStartTime().compareTo(oldTask.getStartTime())<=0 && oldTask.getEndTime().compareTo(newTask.getEndTime())<=0 ) ||
                     ( oldTask.getStartTime().compareTo(newTask.getEndTime())<0 && newTask.getEndTime().compareTo(oldTask.getEndTime())<=0 ) ||
                     ( oldTask.getStartTime().compareTo(newTask.getStartTime())<=0 && newTask.getStartTime().compareTo(oldTask.getEndTime())<0 ) );
        }
        
    public void addTask(Task t){
        if( t.isMultipleQuarterHour() && isSeparatedTime(t) ){
            tasks.add(t);
        }
    }
    
    public boolean isWeekDay(){
        return actualDay.getDayOfWeek().getValue()<=5;
    }
    
}
