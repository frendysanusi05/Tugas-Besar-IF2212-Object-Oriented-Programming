import java.util.concurrent.atomic.AtomicBoolean;

public class SimDriver {
    public static void main(String[] args) throws Exception {
        World world = new World();
        Sim sim = new Sim("Sim");
        Point point = new Point(3, 3);
        sim.setPosisiSim(point);
        Rumah rumah = new Rumah(point);
        sim.setCurrentRuangan(rumah.getRuangan("Kamar"));
        Ruangan ruangan = sim.getCurrentRuangan();
        world.placeRumah(rumah);
        world.addRumah(rumah, sim);
        //world.printWorld();
        sim.printCurrentSimRoom(world);
        sim.setUang(1500);
        // sim.upgradeRumah(rumah, ruangan);
        Ruangan kamarMandi = new Ruangan("Kamar Mandi", rumah);
        kamarMandi.setPosisiRuangan(new Point(6, 0));
        Ruangan dapur = new Ruangan("Dapur", rumah);
        dapur.setPosisiRuangan(new Point(6, 6));
        Ruangan ruangTamu = new Ruangan("Ruang Tamu", rumah);
        ruangTamu.setPosisiRuangan(new Point(0, 6));
        kamarMandi.checkSurrounding(rumah);
        dapur.checkSurrounding(rumah);
        ruangTamu.checkSurrounding(rumah);
        ruangan.checkSurrounding(rumah);
        kamarMandi.insertObjectToRuangan("Shower", new Point(0,0), new AtomicBoolean(false));

        System.out.println("Kanan: " + ruangan.getRuanganKanan().getNamaRuangan());
        System.out.println("Atas: " + ruangan.getRuanganAtas().getNamaRuangan());
        // Ruang Tamu ---- Dapur
        ///  |              |
        // Kamar ---- Kamar Mandi

        rumah.printDaftarRuangan();
        System.out.println();
        sim.pindahRuang(rumah);
        sim.moveToFurniture(kamarMandi, world);
        sim.printCurrentSimRoom(world);



    }
}
