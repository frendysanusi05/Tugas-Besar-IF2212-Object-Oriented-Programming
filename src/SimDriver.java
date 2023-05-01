public class SimDriver {
    public static void main(String[] args) throws Exception {
        World world = new World();
        Sim sim = new Sim("Sim");
        Point point = new Point(3, 3);
        Rumah rumah = new Rumah(point);
        world.addCekPosisi(rumah);
        world.addRumah(rumah, sim);
        world.printWorld();
        sim.setUang(1500);
        sim.upgradeRumah(world, rumah);
        rumah.printDaftarRuangan();
        world.printWorld();



    }
}
