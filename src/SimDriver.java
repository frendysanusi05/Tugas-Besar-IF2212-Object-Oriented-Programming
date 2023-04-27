public class SimDriver {
    public static void main(String[] args) {
        Sim sim1 = new Sim("SIM1");

        System.out.printf("Nama: %s\n", sim1.getNama());
        System.out.printf("Pekerjaan: %s\n", sim1.getPekerjaan());
        System.out.printf("Uang: %d\n", sim1.getUang());
        System.out.printf("Inventory: %s\n", sim1.getInventory());
        System.out.printf("Kekenyangan: %d\n", sim1.getKekenyangan());
        System.out.printf("Mood: %d\n", sim1.getMood());
        System.out.printf("Kesehatan: %d\n", sim1.getKesehatan());
        System.out.printf("Status: %s\n", sim1.getStatus());
        System.out.println();

        // make sim1.kerja() work concurrently with sim1.getStatus(), for example, when the first thread does the 
        // sim1.kerja() then the 2nd thread will operate sim1.getStatus()
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                sim1.kerja();
            }
        });
        
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Still here");
                // do nothing while status is null
                while (sim1.getStatus() == null) {
                    try {
                        Thread.sleep(1);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                System.out.println("Now here");
                while (sim1.getStatus() != null) {
                    System.out.printf("Status: %s\n", sim1.getStatus());
                    try {
                        Thread.sleep(3000);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                System.out.println("Jumped");
            }
        });

        t1.start();
        t2.start();


        try {
            t1.join();
            t2.join();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        
    }
}
