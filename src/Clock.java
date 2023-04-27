import java.time.LocalTime;

public class Clock {
    public static void wait(double duration) {
        LocalTime localTime = LocalTime.now();
        int seconds = localTime.toSecondOfDay();
        
        while (LocalTime.now().toSecondOfDay() - seconds != duration) {
            //System.out.println(LocalTime.now().toSecondOfDay() - seconds);
        }
        System.out.println();
        System.out.println("Finished");
    }

    public static boolean isEqualDuration(int timeInSeconds, int duration) {
        return LocalTime.now().toSecondOfDay() - timeInSeconds == duration;
    }
}