package tlog16java;
    
    import java.time.LocalTime;

public class Task {
    
//---- Variables ----
    
    private String taskId;
    private LocalTime startTime;
    private LocalTime endTime;
    private String comment;
    private long minPerTask=0;
    
//---- Constructors, Getters, Setters ----
    
    public Task(String _taskId, String _comment, int startHour, int startMin, int endHour, int endMin){
        taskId=_taskId;
        startTime=LocalTime.of(startHour, startMin);
        endTime=LocalTime.of(endHour, endMin);
        comment=_comment;
        makeMinPerTask();
    }
    
        private void makeMinPerTask(){
            long pastMinutes=endTime.getMinute()-startTime.getMinute();
            long pastHoursInMinutes=(endTime.getHour()-startTime.getHour())*60;
            minPerTask=pastMinutes+pastHoursInMinutes;
        }
    
    public Task(String _taskId, String _comment, String _startTime, String _endTime){
        taskId=_taskId;
        startTime=LocalTime.of( Integer.parseInt(_startTime.split(":")[0]) , Integer.parseInt(_startTime.split(":")[1]) );
        endTime=LocalTime.of( Integer.parseInt(_endTime.split(":")[0]) , Integer.parseInt(_endTime.split(":")[1]) );
        comment=_comment;
        makeMinPerTask();
    }
    
    public Task(String _taskId){
        taskId=_taskId;
    }
    
    public String getTaskId() {
        return taskId;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getComment() {
        return comment;
    }
    
    public long getMinPerTask() {
        return minPerTask;
    }
    
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    
    public void setStartTime(int startHour, int startMin) {
        this.startTime = LocalTime.of(startHour, startMin);
        makeMinPerTask();
    }
    
    public void setStartTime(String _startTime){
        this.startTime=LocalTime.of( Integer.parseInt(_startTime.split(":")[0]) , Integer.parseInt(_startTime.split(":")[1]) );
        makeMinPerTask();
    }
    
    public void setEndTime(int endHour, int endMin) {
        this.endTime = LocalTime.of(endHour, endMin);
        makeMinPerTask();
    }
    
    public void setEndTime(String _endTime) {
        this.endTime = LocalTime.of( Integer.parseInt(_endTime.split(":")[0]) , Integer.parseInt(_endTime.split(":")[1]) );
        makeMinPerTask();
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
    
//---- User Methods ----       
    
    public boolean isValidTaskId(){
        boolean theIdIsValid=false;
        if ( isValidRedmineTaskId() || isValidLTTaskId()==true ){
            theIdIsValid=true;
        }
        return theIdIsValid;
    }
    
        private boolean isValidLTTaskId(){
            return taskId.length()==7 && taskId.split("-")[0].equals("LT");
        }
        
        private boolean isValidRedmineTaskId(){
            return taskId.length() == 4;
        }
    
}
