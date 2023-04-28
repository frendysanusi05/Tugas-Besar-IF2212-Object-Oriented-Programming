import java.util.*;

public class SimRuanganDriver {
    public static void main(String[] args) throws Exception {
        World world = new World();
        Sim sim = new Sim("Ariq");
        sim.setUang(100000);
        Random rand = new Random();
        int x = 57;
        int y = 3;
        System.out.println("x Rumah = "+ x + " y Rumah = "+ y);
        Rumah rumah = new Rumah(new Point(x, y));
        world.addRumah(rumah, sim);
        world.addCekPosisi(rumah);
        sim.setPosisiSim(new Point(x, y));
        rumah.printDaftarRuangan();
        Ruangan kamar = rumah.getRuangan("Kamar");
        Ruangan kamarMandi = new Ruangan("Kamar Mandi", rumah, new Point(57, 9));
        world.addCekPosisi(rumah);
        while(true) {
            world.printWorld();
            rumah.printDaftarRuangan();
            System.out.println("Koordinat Rumah: " + rumah.getXRumah() + ", " + rumah.getYRumah());
            sim.upgradeRumah(world, rumah);
            // world.addCekPosisi(rumah);
        }
        
        // Furniture item = new Furniture("Kasur Single");
        // sim.inventory.addItem(item.getNama());
        // sim.inventory.printSpecificItem("Furniture");
        //sim.pasangBarang(kamar);
        // kamar.printRuangan(sim);

        // sim.moveToFurniture(kamar);
        // kamar.printRuangan(sim);
        // sim.checkFurniture(kamar);

        // List<String> daftarAksi = sim.getDaftarAksi();

        // while(true) {
        //     System.out.println("\nDaftar Aksi:");
        //     for (int i = 0; i < daftarAksi.size(); i++) {
        //         System.out.println(i+1 + ". " + daftarAksi.get(i));
        //     }
        //     sim.moveToFurniture(kamar);
        //     kamar.printRuangan(sim);
        //     sim.checkFurniture(kamar);
        // }

    }
}