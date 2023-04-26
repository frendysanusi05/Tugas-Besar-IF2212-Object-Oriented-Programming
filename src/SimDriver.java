public class SimDriver {
    public static void main(String[] args) {
        Sim sim1 = new Sim("SIM1");

        System.out.printf("Nama: %s\n", sim1.getNama());
        System.out.printf("Pekerjaan: %s\n", sim1.getPekerjaan());
        System.out.printf("Uang: %d\n", sim1.getUang());
        System.out.printf("Inventory: %s\n", sim1.getInventory());
<<<<<<< Updated upstream
        System.out.printf("Kekenyangan: %d\n", sim1.getKekenyanganSIM());
=======
        System.out.printf("Kekenyangan: %d\n", sim1.getKekenyanganSIM());
>>>>>>> Stashed changes
        System.out.printf("Mood: %d\n", sim1.getMood());
        System.out.printf("Kesehatan: %d\n", sim1.getKesehatan());
        System.out.printf("Status: %s\n", sim1.getStatus());
        System.out.println();

        sim1.kerja();
        System.out.printf("Nama: %s\n", sim1.getNama());
        System.out.printf("Pekerjaan: %s\n", sim1.getPekerjaan());
        System.out.printf("Uang: %d\n", sim1.getUang());
        System.out.printf("Inventory: %s\n", sim1.getInventory());
<<<<<<< Updated upstream
        System.out.printf("Kekenyangan: %d\n", sim1.getKekenyangan());
=======
        System.out.printf("Kekenyangan: %d\n", sim1.getKekenyanganSIM());
>>>>>>> Stashed changes
        System.out.printf("Mood: %d\n", sim1.getMood());
        System.out.printf("Kesehatan: %d\n", sim1.getKesehatan());
        System.out.printf("Status: %s\n", sim1.getStatus());
    }
}
