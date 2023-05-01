public class ClockDriver {
    public static void main(String[] args) {
        System.out.println(Clock.getTime());
        Clock.wait(5.00);
        Clock.skipTime(5);
        System.out.println(Clock.getTime());
        Clock.skipTime(3600);
        System.out.println(Clock.getTime());
        System.out.printf("Diff: %s\n", Clock.diffTime(null));
    }
}
