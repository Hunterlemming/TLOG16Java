package tlog16java;
    
    import java.time.LocalTime;

public class Task {
    
//---- Variables ----
    
    private String taskId;
    private LocalTime startTime;
    private LocalTime endTime;
    private String comment;
    private long minPerTask;
    
//---- Constructors, Getters, Setters ----
    
    public Task(String _taskId, String _comment, int startHour, int startMin, int endHour, int endMin){
        setTaskId(_taskId);
        setStartTime(startHour,startMin);
        setEndTime(endHour, endMin);
        setComment(_comment);
    }
    
        private void makeMinPerTask(){
            long pastMinutes=endTime.getMinute()-startTime.getMinute();
            long pastHoursInMinutes=(endTime.getHour()-startTime.getHour())*60;
            minPerTask=pastMinutes+pastHoursInMinutes;
        }
    
    public Task(String _taskId, String _comment, String _startTime, String _endTime){
        setTaskId(_taskId);
        setStartTime(_startTime);
        setEndTime(_endTime);
        setComment(_comment);
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
        makeMinPerTask();
        return minPerTask;
    }
    
    public final void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    
    public final void setStartTime(int startHour, int startMin) {
        this.startTime = LocalTime.of(startHour, startMin);
    }
    
    public final void setStartTime(String _startTime){
        this.startTime=LocalTime.of( Integer.parseInt(_startTime.split(":")[0]) , Integer.parseInt(_startTime.split(":")[1]) );
    }
    
    public final void setEndTime(int endHour, int endMin) {
        this.endTime = LocalTime.of(endHour, endMin);
    }
    
    public final void setEndTime(String _endTime) {
        this.endTime = LocalTime.of( Integer.parseInt(_endTime.split(":")[0]) , Integer.parseInt(_endTime.split(":")[1]) );
    }
    
    public final void setComment(String comment) {
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
            return taskId.length()==7 && taskId.contains("-") && taskId.split("-")[0].equals("LT");
        }
        
        private boolean isValidRedmineTaskId(){
            return taskId.length() == 4;
        }
    
}
