package tlog16java;

import java.time.LocalTime;

public class TimeLogAdder {
    
//---- Variables ----
    
    private final TimeLogger workLog;
    private final TimeLogLister lister;
    
//---- Constructor ----
  
    public TimeLogAdder(TimeLogger workLog){
        this.workLog=workLog;
        this.lister=new TimeLogLister(workLog);
    }
    
//---- User Methods ----
    
    public void addMonth(){
        int year=0, month=0;
            
        boolean valid=false;
        while(valid==false){
            System.out.println("Please specify the year!");
            year=Util.nextInt();
            if (1900<=year) valid=true;
        }
            
        valid=false;
        while(valid==false){
            System.out.println("Please specify the month!");
            month=Util.nextInt();
            if ( 1<=month && month<=12 ) valid=true;
        }
            
        WorkMonth newMonth = new WorkMonth(year, month);
        workLog.addMonth(newMonth);
    }
    
    public void addDayToMonth(){
        if (lister.listMonths()==true) {
            System.out.println("Choose the month you wish to add a workday to!");
            int destinationMonthID = Util.getListElement(workLog.getMonths());
            
            int destinationDay;
            int destinationMonth = workLog.getMonths().get(destinationMonthID).getDate().getMonth().getValue();
            int destinationYear = workLog.getMonths().get(destinationMonthID).getDate().getYear();

            do{
                System.out.println("Which day of the month should the workday be on?");
                destinationDay=Util.nextInt();
            }while(Util.validDate(destinationYear, destinationMonth, destinationDay)==false);
            
            System.out.println("What is the required worktime for the day (minutes) ? (write \"d\" for default (7.5 hours)");
            String workTime = Util.userInput.nextLine();
            WorkDay newDay;
            if (workTime.equals("d")) {
                newDay = new WorkDay(destinationYear,destinationMonth,destinationDay);
            } else {
                long workTimeInMinutes=Long.parseLong(workTime);
                if (workTimeInMinutes <= 1440) {
                    newDay = new WorkDay(workTimeInMinutes,destinationYear,destinationMonth,destinationDay);
                } else {
                    while (workTimeInMinutes > 1440){
                        System.out.println("We are sorry to inform you, but you cannot work more than 24 hours a day.\nPlease enter another worktime!");
                        workTimeInMinutes=Long.parseLong(Util.userInput.nextLine());
                    }
                    newDay = new WorkDay(workTimeInMinutes,destinationYear,destinationMonth,destinationDay);
                }
            }
            workLog.getMonths().get(destinationMonthID).addWorkDay(newDay);
        }
        
    }
    
    public void addTaskToDay(){
        if (lister.listDaysforMonth()) {
            WorkMonth destinationMonth=lister.getChosenMonth();
            System.out.println("Choose the day you wish to add the task to!");
            int destinationDayID = Util.getListElement(destinationMonth.getDays());
            WorkDay destinationDay = destinationMonth.getDays().get(destinationDayID);
            
            Task newTask;
            System.out.println("Please enter the taskId!");
            String newTaskId=Util.userInput.nextLine();
            System.out.println("Please enter your comment to the task!");
            String newComment=Util.userInput.nextLine();
                    
            String defaultEndTime=lastEndTimeOfTheDay(destinationDay);
            System.out.println("Please enter the start of the task (hh:mm)! [" + defaultEndTime + "]");
            String newStartTime=Util.userInput.nextLine();
                    
            if (newStartTime.equals("")){
                newTask = new Task(newTaskId, newComment, defaultEndTime, defaultEndTime);
            } else {
                if (Util.validTaskTime(0, 0, newStartTime)){
                    newTask = new Task(newTaskId, newComment, newStartTime, newStartTime);
                } else {
                    while (!Util.validTaskTime(0, 0, newStartTime)){
                        System.out.println("Please enter a valid time! (hh:mm)");
                        newStartTime=Util.userInput.nextLine();
                    }
                    newTask = new Task(newTaskId, newComment, newStartTime, newStartTime);
                }
            }
            
            destinationDay.addTask(newTask);
        }
        
    }
    
        private String lastEndTimeOfTheDay(WorkDay wd){
            String lastTime="00:00";
                
            if (!wd.getTasks().isEmpty()) {
                int lastId=0;
                LocalTime lastEndTime=wd.getTasks().get(0).getEndTime();
                    
                for (int i = 0; i < wd.getTasks().size(); i++) {
                    if (wd.getTasks().get(i).getEndTime().compareTo(lastEndTime)>0){
                        lastEndTime=wd.getTasks().get(i).getEndTime();
                        lastId=i;
                    }
                }
                    
                lastTime = wd.getTasks().get(lastId).getEndTime().getHour() + ":" + wd.getTasks().get(lastId).getEndTime().getMinute();
            }
                
            return  lastTime;
        }
    
    public void finishTask(){
            
        if(lister.listTasksForDay(false)){
            System.out.println("\nEnter the taskId of the task you wish to end!");
            String targetId = Util.userInput.nextLine();
            
            for(int i=0; i<lister.getChosenDay().getTasks().size(); i++){
                if (targetId.equals(lister.getChosenDay().getTasks().get(i).getTaskId())){
                    System.out.println("Please enter the end of the task (hh:mm)!");
                    String endTime = Util.userInput.nextLine();
                    int startTimeH = lister.getChosenDay().getTasks().get(i).getStartTime().getHour();
                    int startTimeM = lister.getChosenDay().getTasks().get(i).getStartTime().getMinute();
                    while(!Util.validTaskTime(startTimeH, startTimeM, endTime)){
                        System.out.println("Please enter a valid time field (hh:mm)!");
                        endTime = Util.userInput.nextLine();
                    }
                    lister.getChosenDay().getTasks().get(i).setEndTime(endTime);
                }
            }
        }
    }
        
    public void deleteTask(){
        if(lister.listTasksForDay(true)){
            System.out.println("\nEnter the taskId of the task you wish to delete!");
            String targetId = Util.userInput.nextLine();
            
            for(int i=0; i<lister.getChosenDay().getTasks().size(); i++){
                if (targetId.equals(lister.getChosenDay().getTasks().get(i).getTaskId())){
                    System.out.println("Are you sure about deleting this record? (y/n)");
                    if(Util.userInput.nextLine().equals("y")){
                        lister.getChosenDay().getTasks().remove(i);
                        System.out.println("The item has been removed.");
                    } else {
                        System.out.println("The item still exists.");
                    }
                    
                }
                
            }
            
        }
    }
    
    public void modifyTask(){
        if(lister.listTasksForDay(true)){
            System.out.println("\nEnter the taskId of the task you wish to modify!");
            String targetId = Util.userInput.nextLine();
            
            for(int i=0; i<lister.getChosenDay().getTasks().size(); i++){
                if (targetId.equals(lister.getChosenDay().getTasks().get(i).getTaskId())){
                    Task targetTask = lister.getChosenDay().getTasks().get(i);
                    TaskModUI localUI = new TaskModUI();
                    boolean next=true;
                    while(next==true){
                        localUI.showMenu(targetTask);
                        next=localUI.execute(Util.nextInt());
                    }
                }
                
            }
        }
    }
    
    
}
