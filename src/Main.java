public class Main {
    public static void main(String[] args) {
        World world = new World();
        Save save = new Save();

        Point titikRumah1 = new Point(3, 3);
        Rumah rumah1 = new Rumah(titikRumah1);
        Rumah rumah2 = new Rumah(new Point(30, 30));
        world.addRumah(rumah1, "Ariq");
        world.addRumah(rumah2, "Vina");
        rumah1.addRuangan(new Ruangan("Kamar Mandi", rumah1, new Point (9, 3)));
        rumah1.addRuangan(new Ruangan("Dapur", rumah1, new Point (15, 3)));
        world.addCekPosisi(rumah1);
        world.addCekPosisi(rumah2);
        world.printWorld();
        rumah1.printDaftarRuangan();
        world.printDaftarRumah();
        save.save(world);
    }
}