package utilz;

public class ClockSerializer {
    private int day;
    private int stop;
    private int startTime;
    private int currTime;
    private boolean firstTimeClock;

    public ClockSerializer(int day, int stop, int startTime, int currTime, boolean firstTimeClock) {
        this.day = day;
        this.stop = stop;
        this.startTime = startTime;
        this.currTime = currTime;
        this.firstTimeClock = firstTimeClock;
    }

    public int getDay() {
        return day;
    }

    public int getStop() {
        return stop;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getCurrTime() {
        return currTime;
    }

    public boolean isFirstTimeClock() {
        return firstTimeClock;
    }
}
