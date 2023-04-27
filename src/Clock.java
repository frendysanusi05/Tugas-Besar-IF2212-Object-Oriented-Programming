import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;

public class Clock {
    private static int skip = 0; /* in seconds */
    private static int stop = 0; /* in seconds */
    final static LocalTime startTime = LocalTime.now();

    /* Menjalankan durasi waktu */
    /* Jika ingin menjalankan 2 menit, maka gunakan Clock.wait(2*60) */
    public static void wait(Double duration) {
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
        // System.out.println();
        // System.out.println("Finished");
    }

    /* Mengecek durasi waktu == duration */
    public static boolean isEqualDuration(int timeInSeconds, int duration) {
        return getTime().toSecondOfDay() - timeInSeconds == duration;
    }

    /* Konversi LocalTime ke seconds*/
    private static int convertToSeconds(LocalTime time) {
        return time.toSecondOfDay();
    }

    /* Mengambil waktu sekarang di dunia Sim */
    public static LocalTime getTime() {
        LocalTime time = LocalTime.now();
        Duration addSkipAndStop = Duration.ofSeconds(skip-stop);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        time = LocalTime.parse(time.format(formatter));
        return time.plus(addSkipAndStop);
    }

    /* return end - start */
    /* example output: 00:01:18 */
    public static LocalTime diffTime() {
        LocalTime endTime = getTime(); /* current local time */
        Duration duration = Duration.between(startTime, endTime);
        return LocalTime.ofSecondOfDay(duration.getSeconds());
    }

    /* dipakai jika ingin melakukan timeskip sebesar seconds detik */
    public static void skipTime(int seconds) {
        skip += seconds;
    }

    /* Menghitung lama waktu idle */
    /* dipakai ketika waktu tidak berjalan (Sim sedang tidak melakukan aksi) */
    public static void stopTime(int seconds) {
        stop -= seconds;
    }
}
