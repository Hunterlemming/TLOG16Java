    package tlog16java;

    public class TaskModUI {

        private Task task;

        //--- Menu ---

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

        public boolean execute(int procedure){
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
                    modTimeString(3);
                    break;
                case 4:
                    modTimeString(4);
                    break;
                default:
                    System.out.println("Number \"" + procedure + "\" does not refer to a procedure!");
                    break;
            }
            return true;
        }

        //--- Methods ---

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
    
    }