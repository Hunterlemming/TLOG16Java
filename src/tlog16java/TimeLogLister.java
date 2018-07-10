package tlog16java;

public class TimeLogLister {
    
//---- Variables ----
    
    private final TimeLogger workLog;
    private WorkMonth chosenMonth;
    private WorkDay chosenDay;
    
//---- Constructor, Getters ----
  
    public TimeLogLister(TimeLogger workLog){
        this.workLog=workLog;
    }
    
    public WorkMonth getChosenMonth(){
        return this.chosenMonth;
    }
    
    public WorkDay getChosenDay(){
        return this.chosenDay;
    }
    
//---- User Methods ----
    
    public boolean listMonths () {
        if (workLog.getMonths().isEmpty()) {
            System.out.println("No months added yet.");
            return false;
        } else {
            for (int i=0; i<workLog.getMonths().size(); i++){
                System.out.println( (i+1) + ". " + workLog.getMonths().get(i).getDate().getYear() + "-" + workLog.getMonths().get(i).getDate().getMonth() );
            }
            return true;
        }
    }
    
    public boolean listDaysforMonth () {
        if (listMonths()==true){
            System.out.println("Choose a month to list workdays from!");
            int monthID = Util.getListElement(workLog.getMonths());

            chosenMonth = workLog.getMonths().get(monthID);
            if (!chosenMonth.getDays().isEmpty()){
                for(int i=0; i<chosenMonth.getDays().size(); i++){
                    System.out.println((i+1) + " " + chosenMonth.getDays().get(i).getActualDay());
                }
                return true;
            } else {
                System.out.println("No workdays added yet.");
            } 
            
        }
        return false;
    }
    
    public boolean listTasksForDay (boolean printall) {
        if (listDaysforMonth()==true){
            System.out.println("Choose a workday to list tasks from!");
            int dayID = Util.getListElement(chosenMonth.getDays());
            chosenDay = chosenMonth.getDays().get(dayID);
                if(!chosenDay.getTasks().isEmpty()){
                    if (printall==true){
                        for(int i=0; i<chosenDay.getTasks().size(); i++){
                            System.out.println("\nTaskId: " + chosenDay.getTasks().get(i).getTaskId() + ", Comment: " + chosenDay.getTasks().get(i).getComment());
                            System.out.println("StartTime: " + chosenDay.getTasks().get(i).getStartTime() + ", EndTime: " + chosenDay.getTasks().get(i).getEndTime());
                        }
                    } else {
                        for (int i=0; i<chosenDay.getTasks().size(); i++){
                            if (chosenDay.getTasks().get(i).getStartTime().compareTo(chosenDay.getTasks().get(i).getEndTime())==0){
                                System.out.println("\nTaskId: " + chosenDay.getTasks().get(i).getTaskId() + ", Comment: " + chosenDay.getTasks().get(i).getComment());
                                System.out.println("StartTime: " + chosenDay.getTasks().get(i).getStartTime() + ", EndTime: " + chosenDay.getTasks().get(i).getEndTime());
                            }
                        }
                    }
                    return true;
                } else {
                    System.out.println("No tasks added yet.");
                }
                
        }
        return false;
    }
    
}
