public class Main {
    public static void main(String[] args) {
        World world = new World();
        Point titikRumah1 = new Point(3, 3);
        Rumah rumah1 = new Rumah(titikRumah1);
        Rumah rumah2 = new Rumah(new Point(30, 30));
        world.addRumah(rumah1, "Sim");
        world.addRumah(rumah2, "Sim");
        rumah1.addDaftarRuangan(new Ruangan(new Point(9, 3), "Kamar Mandi", rumah1.getIDRumah()));
        world.addCekPosisi(rumah1);
        world.addCekPosisi(rumah2);
        world.printWorld();
        rumah1.printDaftarRuangan();
        rumah2.printDaftarRuangan();
        world.printDaftarRumah();
    }
}