package tlog16java;

import java.time.LocalDate;
import java.util.ArrayList;

public class WorkDay {
        
//---- Variables ----
    
    private ArrayList<Task>tasks = new ArrayList<>();
    private long requiredMinPerDay=450;
    private LocalDate actualDay=LocalDate.now();
    private long sumPerDay;
        
//---- Constructors, Getters, Setters ----
    
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
        makeSumPerDay();
        return sumPerDay;
    }
    
        private void makeSumPerDay(){
            sumPerDay=0;
            for (int i=0; i<tasks.size(); i++){
                sumPerDay=sumPerDay+tasks.get(i).getMinPerTask();
            }
        }
    
    public void setRequiredMinPerDay(long requiredMinPerDay) {
        this.requiredMinPerDay = requiredMinPerDay;
    }
    
    public void setActualDay(int _year, int _month, int _day) {
        this.actualDay = LocalDate.of(_year, _month, _day);
    }
    
    public ArrayList<Task> getTasks() {
        return tasks;
    }
        
//---- User Methods ----       
    
    public long getExtraMinPerDay(){
        return sumPerDay-requiredMinPerDay;
    }
        
    public void addTask(Task t){
        getTasks().add(t);
    }
    
}
