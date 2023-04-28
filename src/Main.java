import java.util.*;
public class Main {
    List<Sim> daftarSim = new ArrayList<Sim>();
    public static void mainMenu(String[] args) throws Exception {

        // World world = new World();
        // Save save = new Save();

        // Point titikRumah1 = new Point(3, 3);
        // Rumah rumah1 = new Rumah(titikRumah1);
        // Rumah rumah2 = new Rumah(new Point(30, 30));
        // world.addRumah(rumah1, "Ariq");
        // world.addRumah(rumah2, "Vina");
        // rumah1.addRuangan(new Ruangan("Kamar Mandi", rumah1, new Point (9, 3)));
        // rumah1.addRuangan(new Ruangan("Dapur", rumah1, new Point (15, 3)));
        // world.addCekPosisi(rumah1);
        // world.addCekPosisi(rumah2);
        // world.printWorld();
        // rumah1.printDaftarRuangan();
        // world.printDaftarRumah();
        // save.save(world);

        System.out.println("Welcome to Sim-Plicity!");
        System.out.println("1. New Game");
        System.out.println("2. Load Game");
        System.out.println("3. Help");
        System.out.println("4. Exit");
        System.out.print("Pilihan: ");

        Scanner input = new Scanner(System.in);
        int pilihan = input.nextInt();
        input.close();
        boolean exitGame = false;

        while (!exitGame) {
            switch (pilihan) {
                case 1:
                    newGameSim();
                    break;
                case 2:
                    System.out.println("Load Game");
                    break;
                case 3:
                    System.out.println("Help");
                    break;
                case 4:
                    System.out.println("Exit");
                    exitGame = true;
                    break;
                default:
                    while (pilihan < 1 || pilihan > 4) {
                        System.out.println("Pilihan tidak tersedia");
                        System.out.print("Pilihan: ");
                        pilihan = input.nextInt();
                    }
                    break;
            }
        }

        
        
    }

    public static void newGameSim () throws Exception {
        World world = new World();
        System.out.print("Masukkan nama pemain: ");
        Scanner input = new Scanner(System.in);

        String namaSim = input.nextLine();
        input.close();
        Sim sim = new Sim(namaSim);
        Random rand = new Random();
        int x = rand.nextInt(3, 60);
        int y = rand.nextInt(3, 60);
        Rumah rumah = new Rumah(new Point(x, y));
        world.addRumah(rumah, sim);
        world.addSim(sim);
        world.addCekPosisi(rumah);

        Thread.sleep(3000);
        System.out.println("Selamat datang, " + namaSim + "!");
        playSim(world);
    }

    public static void playSim(World world) {
        Sim currentSimPlayed;
        //if there's only 1 sim, then play that sim
        if (world.getDaftarSim().size() == 1) {
            currentSimPlayed = world.getDaftarSim().get(0);
        } else {
            //if there's more than 1 sim, then ask which sim to play
            System.out.println("Pilih sim yang ingin dimainkan: ");
            for (int i = 0; i < world.getDaftarSim().size(); i++) {
                System.out.println((i+1) + ". " + world.getDaftarSim().get(i).getNama());
            }
            System.out.print("Pilihan: ");
            Scanner input = new Scanner(System.in);
            int pilihan = input.nextInt();
            input.close();
            while (pilihan < 1 || pilihan > world.getDaftarSim().size()) {
                System.out.println("Pilihan tidak tersedia");
                System.out.print("Pilihan: ");
                pilihan = input.nextInt();
            }
            currentSimPlayed = world.getDaftarSim().get(pilihan-1);
        }

        boolean exitGame = false;
        
        
    }
}