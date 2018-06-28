package tlog16java;

public class Util {
    
    public static int roundToMultipleQuarterHour(int minPerTask){
        int remainingToQuarter = minPerTask%15;
        if (remainingToQuarter<=7) {
            minPerTask=minPerTask-(15-remainingToQuarter);
        } else {
            minPerTask=minPerTask+remainingToQuarter;
        }
        return minPerTask;
    }
    
}
