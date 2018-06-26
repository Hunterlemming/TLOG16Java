package tlog16java;

import java.util.List;
import java.time.LocalDate;

public class WorkDay {
    
    private List<Task>tasks;
    private long requiredMinPerDay=450;
    private LocalDate actualDay=LocalDate.now();
    private long sumPerDay;
    
}
