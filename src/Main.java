import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
public class Main {
    List<Sim> daftarSim = new ArrayList<Sim>();
    public static void main(String[] args) throws Exception {

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
        World world = new World();
        System.out.println("Welcome to Sim-Plicity!");
        System.out.println("1. New Game");
        System.out.println("2. Load Game");
        System.out.println("3. Help");
        System.out.println("4. Exit");
        System.out.print("Pilihan: ");

        Scanner input = new Scanner(System.in);
        int pilihan = input.nextInt();
        boolean exitMainMenu = false;

        while (!exitMainMenu) {
            switch (pilihan) {
                case 1:
                    newGameSim();
                    exitMainMenu = true;
                    break;
                case 2:
                    System.out.println("Load Game");
                    break;
                case 3:
                    System.out.println("Help");
                    break;
                case 4:
                    System.out.println("Exit");
                    exitMainMenu = true;
                    break;
                default:
                    System.out.println("Pilihan tidak tersedia");
                    System.out.print("Pilihan: ");
                    pilihan = input.nextInt();  
            }
        }
        System.out.println("Terima kasih telah bermain!");
    }

    public static void generateSim (World world) throws Exception {
        // World world = new World();
        System.out.print("Masukkan nama pemain: ");
        Scanner input = new Scanner(System.in);
        String namaSim = input.nextLine();
        Sim sim = new Sim(namaSim);
        Random rand = new Random();
        int x = rand.nextInt(3, 60);
        int y = rand.nextInt(3, 60);
        Rumah rumah = new Rumah(new Point(x, y));
        sim.setPosisiSim(new Point(x, y));
        world.addRumah(rumah, sim);
        world.addSim(sim);
        world.addCekPosisi(rumah);

        System.out.print("Generating Sim");
        Thread.sleep(1000);
        System.out.print(".");
        Thread.sleep(1000);
        System.out.print(".");
        Thread.sleep(1000);
        System.out.println(".");
        Thread.sleep(1000);
        System.out.println("Halo, " + namaSim + "!");
        Thread.sleep(3000);
        playSim(world);
    }

    public static void newGameSim() throws Exception {
        World world = new World();
        generateSim(world);
    }

    public static void playSim(World world) {
        Sim currentSim;
        //if there's only 1 sim, then play that sim
        if (world.getDaftarSim().size() == 1) {
            currentSim = world.getDaftarSim().get(0);
        } else {
            //if there's more than 1 sim, then ask which sim to play
            System.out.println("Pilih sim yang ingin dimainkan (masukkan angka) ");
            for (int i = 0; i < world.getDaftarSim().size(); i++) {
                System.out.println((i+1) + ". " + world.getDaftarSim().get(i).getNama());
            }
            System.out.print("=> ");
            Scanner input = new Scanner(System.in);
            int pilihan = input.nextInt();
            while (pilihan < 1 || pilihan > world.getDaftarSim().size()) {
                System.out.println("Pilihan tidak tersedia");
                System.out.print("Pilihan: ");
                pilihan = input.nextInt();
            }
            currentSim = world.getDaftarSim().get(pilihan-1);
        }

        boolean exitGame = false;
        
        while (!exitGame) {
            currentSim.printCurrentSimRoom(world);
            System.out.println("\nKamu Bermain Sebagai " + currentSim.getNama());
            //print daftar aksi
            for (int i = 0; i < currentSim.getDaftarAksi().size(); i++) {
                System.out.println("-> " + currentSim.getDaftarAksi().get(i));
            }
            System.out.print("Apa yang ingin kamu lakukan? ");
            Scanner input = new Scanner(System.in);
            String aksi = input.nextLine().toLowerCase();
            //turn the first letter to upper case
            aksi = aksi.substring(0, 1).toUpperCase() + aksi.substring(1);
            while (!currentSim.getDaftarAksi().contains(aksi)) {
                System.out.println("Aksi tidak tersedia!");
                System.out.print("Apa yang ingin kamu lakukan? ");
                aksi = input.nextLine().toLowerCase();
                aksi = aksi.substring(0, 1).toUpperCase() + aksi.substring(1);
            }
            
            switch (aksi) {
                case "Kerja" :
                    currentSim.kerja();
                    break;
                case "Olahraga" :
                    currentSim.olahraga();
                    break;
                case "Berkunjung" :
                
                    // currentSim.berkunjung(w);
                    break;
            }
            break;
        }
        
    }
}