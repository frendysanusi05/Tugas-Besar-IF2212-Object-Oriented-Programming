import java.util.Scanner;

public class TryThread {
    public static void main(String[] args) {
        boolean exit = false;
        while(!exit) {
            Thread t0 = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Thread 0");
                    for (int i = 0; i < 10; i++) {
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    System.out.println("Done");
                }
            });
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Menu");
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    if (!t0.isAlive()) {
                        System.out.println("Mulai thread 0?");
                        Scanner sc = new Scanner(System.in);
                        String input = sc.nextLine();
                        if (input.equals("y")) {
                            t0.start();
                        }
                    } 
                }
            });
            t1.start();

            try {
                t0.join();
                t1.join();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("press 1 to exit");
            Scanner sc = new Scanner(System.in);
            int input = sc.nextInt();
            if (input == 1) {
                if (t0.isAlive()) {
                    System.out.println("t0 is alive");
                } else {
                    exit = true;
                }
            }
        }
       
    }
}
