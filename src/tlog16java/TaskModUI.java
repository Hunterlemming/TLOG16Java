package tlog16java;

public class TaskModUI {
    
    private Task task;
    
    public void showMenu(Task _task){
        task=_task;
        System.out.println("-----------------------------------------------");
        System.out.println("Please choose an operation from the list below!");
        System.out.println("0. exit");
        System.out.println("1. new TaskID (" + task.getTaskId() + ")");
        System.out.println("2. new Comment (" + task.getComment() + ")");
        System.out.println("3. new StartTime (" + task.getStartTime().getHour() + ":" + task.getStartTime().getMinute() + ")");
        System.out.println("4. new EndTime (" + task.getEndTime().getHour() + ":" + task.getEndTime().getMinute() + ")");
        System.out.println("-----------------------------------------------");
    }
    
        private void subMenu(){
            System.out.println("----------------------------------");
            System.out.println("Please choose the input format!");
            System.out.println("1. String");
            System.out.println("2. Int, Int");
            System.out.println("----------------------------------");
        }
    
    public boolean execute(int procedure){
        int option;
        switch(procedure){
            case 0:
                return false;
            case 1:
                modTaskId();
                break;
            case 2:
                modComment();
                break;
            case 3:
                subMenu();
                option = Util.nextInt();;
                timeExecute(option, 3);
                break;
            case 4:
                subMenu();
                option = Util.nextInt();;
                timeExecute(option, 4);
                break;
            default:
                System.out.println("Number \"" + procedure + "\" does not refer to a procedure!");
                break;
        }
        return true;
    }
    
        private void timeExecute(int procedure, int endOrStart){
            switch(procedure){
                case 1:
                    modTimeString(endOrStart);
                    break;
                case 2:
                    modTimeInts(endOrStart);
                    break;
                default:
                    System.out.println("Number \"" + procedure + "\" does not refer to a procedure!");
                    break;
            }
        }
    
        private void modTaskId(){
            System.out.println("Please enter the new TaskID!");
            task.setTaskId(Util.userInput.nextLine());
        }
        
        private void modComment(){
            System.out.println("Please enter the new Comment!");
            task.setComment(Util.userInput.nextLine());
        }
        
        private void modTimeString(int endOrStart){
            String time;
            switch(endOrStart){
                case 3:
                    do{
                        System.out.println("Please enter the new StartTime!");
                        time=Util.userInput.nextLine();
                        if (!Util.validTaskTime(0, 0, time)) System.out.println("Invalid date!");
                    } while( !Util.validTaskTime(0, 0, time) );
                    task.setStartTime(time);
                    break;
                case 4:
                    do{
                        System.out.println("Please enter the new EndTime!");
                        time=Util.userInput.nextLine();
                        if (!Util.validTaskTime(task.getStartTime().getHour(), task.getStartTime().getMinute(), time )) System.out.println("Invalid date!");
                    } while ( !Util.validTaskTime(task.getStartTime().getHour(), task.getStartTime().getMinute(), time) );
                    task.setEndTime(time);
                    break;
            }
        }
        
        private void modTimeInts(int endOrStart){
            int h=0, m=0;
            switch(endOrStart){
                case 3:
                    do{
                        System.out.println("Please enter the new starting hour!");
                        h=Util.nextInt();
                        System.out.println("Please enter the new starting minute!");
                        m=Util.nextInt();
                        if (!Util.validTaskTime(0, 0, h + ":" + m)) System.out.println("Invalid date!");
                    } while( !Util.validTaskTime(0, 0, h + ":" + m) );
                    task.setStartTime(h, m);
                    break;
                case 4:
                    do{
                        System.out.println("Please enter the new ending hour!");
                        h=Util.nextInt();
                        System.out.println("Please enter the new ending minute!");
                        m=Util.nextInt();
                        if (!Util.validTaskTime(task.getStartTime().getHour(), task.getStartTime().getMinute(), h + ":" + m)) System.out.println("Invalid date!");
                    } while ( !Util.validTaskTime(task.getStartTime().getHour(), task.getStartTime().getMinute(), h + ":" + m) );
                    task.setEndTime(h, m);
                    break;
            }
        }
    
}
