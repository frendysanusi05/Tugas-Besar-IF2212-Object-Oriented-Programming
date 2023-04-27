import java.util.*;

public class SimRuanganDriver {
    public static void main(String[] args) throws Exception {
        World world = new World();
        Sim sim = new Sim("Ariq");
        Random rand = new Random();
        int x = rand.nextInt(3, 60);
        int y = rand.nextInt(3, 60);
        System.out.println("x Rumah = "+ x + " y Rumah = "+ y);
        Rumah rumah = new Rumah(new Point(x, y));
        world.addRumah(rumah, sim);
        world.addCekPosisi(rumah);
        sim.setPosisiSim(new Point(x, y));
        rumah.printDaftarRuangan();
        Ruangan kamar = rumah.getRuangan("Kamar");
        // Furniture item = new Furniture("Kasur Single");
        // sim.inventory.addItem(item.getNama());
        // sim.inventory.printSpecificItem("Furniture");
        //sim.pasangBarang(kamar);
        kamar.printRuangan(sim);
    }
}
