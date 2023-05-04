import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;

public class Clock {
    private static int day = 0;
    private static int stop = 0; /* in seconds */
    // final static LocalTime firstTime = LocalTime.now();
    static LocalTime startTime = convertToLocalTime(0);
    private static LocalTime currTime = startTime;
    static boolean firstTimeClock = true;

    /* Menjalankan durasi waktu */
    /* Jika ingin menjalankan 2 menit, maka gunakan Clock.wait(2*60) */
    public static void wait(Double duration) {
        LocalTime localTime = getRealTime();
        int seconds = localTime.toSecondOfDay();
        if (firstTimeClock) {
            startTime = Clock.getTime();
            currTime = startTime;
            firstTimeClock = false;
        }
        while ((convertToSeconds(getRealTime()) - stop) - seconds + 1 < duration) {
            /*** for debugging ***/
            // try {
            //     Thread.sleep(1000);
            // } catch (InterruptedException e) {
            //     e.printStackTrace();
            // }
        }
        Duration durationInDuration = Duration.ofSeconds(duration.intValue());
        currTime = currTime.plus(durationInDuration);
    }

    /* Mengecek durasi waktu == duration */
    public static boolean isEqualDuration(int timeInSeconds, int duration) {
        return getTime().toSecondOfDay() - timeInSeconds == duration;
    }

    /* Konversi LocalTime ke seconds*/
    public static int convertToSeconds(LocalTime time) {
        return time.toSecondOfDay();
    }

    /* Mengambil waktu sekarang di dunia Sim */
    public static LocalTime getTime() {
        return currTime;
    }

    /* Get current Local Time */
    public static LocalTime getRealTime() {
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        time = LocalTime.parse(time.format(formatter));
        return time;
    }

    public static void printTime(LocalTime time) {
        int timeInSeconds = convertToSeconds(time);
        if (timeInSeconds % 60 == 0) System.out.print(time + ":" + timeInSeconds % 60);
        else System.out.print(time);
    }

    /* return end - start */
    /* example output: 00:01:18 */
    public static LocalTime diffTime(LocalTime currTime) {
        if (currTime == null) {
            if (firstTimeClock) {
                return LocalTime.ofSecondOfDay(0);
            }
            else {
                currTime = startTime;
            }
        }
        LocalTime endTime = getTime();
        Duration duration = Duration.between(currTime, endTime);
        return LocalTime.ofSecondOfDay(duration.getSeconds());
    }

    public static int getDiffTimeInSeconds(int currTime) {
        return convertToSeconds(diffTime(LocalTime.ofSecondOfDay(currTime)));
    }

    public static LocalTime convertToLocalTime(int seconds) {
        return LocalTime.ofSecondOfDay(seconds);
    }

    public static LocalTime minusTime(LocalTime currTime, LocalTime endTime) {
        Duration duration = Duration.between(currTime, endTime);
        return LocalTime.ofSecondOfDay(duration.getSeconds());
    }

    /* Menghitung lama waktu idle */
    /* dipakai ketika waktu tidak berjalan (Sim sedang tidak melakukan aksi) */
    public static void stopTime(int seconds) {
        stop += seconds;
    }

    public static void setStopTime(int seconds) {
        stop = seconds;
    }

    public static int getDay() {
        countDay(); /* Meng-update day */
        return day;
    }

    public static void countDay() {
        if (checkChangeDay()) day++;
    }

    public static boolean checkChangeDay() {
        LocalTime currTime = Clock.getTime();
        return (getDiffTimeInSeconds(convertToSeconds(currTime)) - day*12*60 == 0);
    }

    public static LocalTime dayRemaining() {
        return (minusTime(diffTime(null), convertToLocalTime(12*60)));
    }
}