package tlog16java;

import java.util.List;
import java.util.ArrayList;

public class TimeLogger {
            
//---- Variables ----
    
    private List<WorkMonth> months = new ArrayList<>();
        
//---- Getter ----
    
    public List<WorkMonth> getMonths() {
        return months;
    }
        
//---- User Methods ----       
    
    public boolean isNewMonth(WorkMonth wm){
        boolean isNew=true;
        for (int i=0; i<months.size(); i++){
            if (wm.equals(months.get(i))){
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
