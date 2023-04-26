import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;

public class Clock {
    private static int skip = 0; /* in seconds */

    public static void wait(int duration) {
        LocalTime localTime = getTime();
        int seconds = localTime.toSecondOfDay();
        
        while (convertToSeconds(getTime()) - seconds < duration) {
            /*** for debugging ***/
            // System.out.println(getTime().toSecondOfDay() - seconds);
            // try {
            //     Thread.sleep(1000);
            // } catch (InterruptedException e) {
            //     e.printStackTrace();
            // }
        }
        System.out.println();
        System.out.println("Finished");
    }

    public static boolean isEqualDuration(int timeInSeconds, int duration) {
        return getTime().toSecondOfDay() - timeInSeconds == duration;
    }

    private static int convertToSeconds(LocalTime time) {
        return time.toSecondOfDay();
    }

    public static LocalTime getTime() {
        LocalTime time = LocalTime.now();
        Duration addSkip = Duration.ofSeconds(skip);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        time = LocalTime.parse(time.format(formatter));
        return time.plus(addSkip);
    }

    public static LocalTime diffTime(LocalTime start) {
        /* return end - start */
        /* example output: 00:01:18 */
        LocalTime end = getTime(); /* current local time */
        Duration duration = Duration.between(start, end);
        return LocalTime.ofSecondOfDay(duration.getSeconds());
    }

    public static void skipTime(int seconds) {
        skip += seconds;
    }
}
