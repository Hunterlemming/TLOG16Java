package tlog16java;

import java.util.ArrayList;

public class TimeLogger {
            
//---- Variables ----
    
    private ArrayList<WorkMonth> months = new ArrayList<>();
        
//---- Getter ----
    
    public ArrayList<WorkMonth> getMonths() {
        return months;
    }
        
//---- User Methods ----       
    
    public boolean isNewMonth(WorkMonth wm){
        boolean isNew=true;
        for (int i=0; i<months.size(); i++){
            if (wm.getDate().compareTo(months.get(i).getDate()) == 0){
                System.out.println("This month already exists!");
                isNew=false;
            }
        }
        return isNew;
    }
    
    public void addMonth(WorkMonth wm){
        if (isNewMonth(wm)){
            months.add(wm);
        }
    }
}
