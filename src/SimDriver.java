public class SimDriver {
    public static void main(String[] args) throws Exception {
        World world = World.getInstance();
        Sim sim = new Sim("Sim");
        Point point = new Point(0, 0);
        sim.setPosisiSim(point);
        Rumah rumah = new Rumah(point);
        sim.setCurrentRuangan(rumah.getRuangan("Kamar"));
        Ruangan ruangan = sim.getCurrentRuangan();
        world.placeRumah(rumah);
        world.addRumah(rumah, sim);
        //world.printWorld();
        sim.printCurrentSimRoom(world);
        //sim.setUang(1500);
        // Sim sim2 = new Sim("Sim2");
        // sim2.setPosisiSim(new Point(3, 3));
        // Rumah rumah2 = new Rumah(new Point(30,0));
        // world.placeRumah(rumah2);
        // world.addRumah(rumah2, sim2);
        // world.addSim(sim);
        // world.addSim(sim2);
        // sim.printSimAttribute();
        // sim.berkunjung(world);
        // sim.kerja();
        // //sim.kerja();
        // sim.printSimAttribute();
        //sim.upgradeRumah(rumah, ruangan);
        // sim.beliBarang();
        // Save.save(world);
        sim.getInventory().addItem("Shower");
        Furniture shower = new Furniture("Shower");
        sim.pasangBarang(ruangan);
        // Ruangan kamarMandi = new Ruangan("Kamar Mandi", rumah);
        // kamarMandi.setPosisiRuangan(new Point(6, 0));
        // Ruangan dapur = new Ruangan("Dapur", rumah);
        // dapur.setPosisiRuangan(new Point(6, 6));
        // Ruangan ruangTamu = new Ruangan("Ruang Tamu", rumah);
        // ruangTamu.setPosisiRuangan(new Point(0, 6));
        // kamarMandi.checkSurrounding(rumah);
        // dapur.checkSurrounding(rumah);
        // ruangTamu.checkSurrounding(rumah);
        // ruangan.checkSurrounding(rumah);
        // kamarMandi.insertObjectToRuangan("Shower", new Point(0,0), new AtomicBoolean(false));

        // System.out.println("Kanan: " + ruangan.getRuanganKanan().getNamaRuangan());
        // System.out.println("Atas: " + ruangan.getRuanganAtas().getNamaRuangan());
        // // Ruang Tamu ---- Dapur
        // ///  |              |
        // // Kamar ---- Kamar Mandi

        // rumah.printDaftarRuangan();
        // System.out.println();
        // sim.pindahRuang(rumah);
        // sim.moveToFurniture(kamarMandi, world);
        // sim.printCurrentSimRoom(world);



    }
}
