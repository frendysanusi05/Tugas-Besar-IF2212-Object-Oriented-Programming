import java.util.*;

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

        // Animasi, hiraukan
        // Thread.sleep(1000);
        // System.out.print(".");
        // Thread.sleep(1000);
        // System.out.print(".");
        // Thread.sleep(1000);
        // System.out.println(".");
        // Thread.sleep(1000);

        System.out.println("\nWelcome to the game, " + namaSim + "!\n");
        //Thread.sleep(1000);
        playSim(world);
    }

    public static void newGameSim() throws Exception {
        World world = new World();
        generateSim(world);
    }

    public static void save() {
        // proses save
    }
    public static void load() {
        // proses load
    }



    public static void playSim(World world) throws Exception {
        Sim sim;
        //if there's only 1 sim, then play that sim
        if (world.getDaftarSim().size() == 1) {
            sim = world.getDaftarSim().get(0);
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
            sim = world.getDaftarSim().get(pilihan-1);
        }

        // Set boolean ke false supaya ga exit dari game
        boolean exitGame = false;

        // Ini cuma animasi loading

        // System.out.println("Generating World");
        // Thread.sleep(1000);
        // System.out.print("[                ]");
        // Thread.sleep(1000);
        // for (int i = 0; i < 18; i++) {
        //     System.out.print("\b");
        // }
        // Thread.sleep(1000);
        // System.out.print("[===             ]");
        // Thread.sleep(1000);
        // for (int i = 0; i < 18; i++) {
        //     System.out.print("\b");
        // }
        // Thread.sleep(1000);
        // System.out.print("[======          ]");
        // Thread.sleep(1000);
        // for (int i = 0; i < 18; i++) {
        //     System.out.print("\b");
        // }
        // Thread.sleep(1000);
        // System.out.print("[==========      ]");
        // Thread.sleep(1000);
        // for (int i = 0; i < 18; i++) {
        //     System.out.print("\b");
        // }
        // Thread.sleep(1000);
        // System.out.print("[================]");
        // Thread.sleep(1000);
        // for (int i = 0; i < 18; i++) {
        //     System.out.print("\b");
        // }
        // Thread.sleep(1000);
        // System.out.println();

        // End of animasi loading (Gausah dihirauikan)

        // Keluar dari loop sampe user milih exit
        while (!exitGame) {
            // Get current ruangan dan rumah dari sim
            Rumah rumah = world.getRumahSim(sim);
            Ruangan ruangan = rumah.getCurrentRuanganSim(sim);

            // Informasi sim dan lokasinya
            sim.printCurrentSimRoom(world);
            Thread.sleep(1000);
            System.out.println("\nBermain Sebagai " + sim.getNama());

            // Mengecek furniture di sekitarnya yang bisa diinteract
            sim.checkFurniture(ruangan);

            // Tambah opsi save dan exit supaya bisa keluar sama save game
            sim.addDaftarAksi("Save");
            sim.addDaftarAksi("Exit");

            // Print current value dari atribut milik sim
            sim.printSimAttribute();

            // Daftar aksi yang bisa dilakukan Sim (termasuk save dan exit)
            sim.printDaftarAksi();

            // Minta masukan user buat aksi yang mau dilakukan
            System.out.print("\nApa yang ingin kamu lakukan? ");
            Scanner input = new Scanner(System.in);
            String aksi = input.nextLine();

            while (!sim.getDaftarAksi().contains(aksi)) {
                System.out.println("Aksi tidak tersedia");
                System.out.print("Apa yang ingin kamu lakukan? ");
                aksi = input.nextLine();
            }

            aksi = aksi.toLowerCase();

            switch (aksi) {
                case "kerja" :
                    sim.kerja();
                    break;
                case "olahraga" :
                    sim.olahraga();
                    break;
                case "berkunjung" :

                    // currentSim.berkunjung(w);
                case "upgrade rumah":
                    sim.upgradeRumah(world, rumah);
                    break;
                case "beli barang" :
                    sim.beliBarang();
                    break;
                case "pindah ruang" :
                    sim.pindahRuang();
                    break;
                case "lihat inventory" :
                    sim.lihatInventory();
                    break;
                case "pasang barang" :
                    sim.pasangBarang(ruangan);
                    break;
                case "bergerak ke objek" :
                    sim.moveToFurniture(ruangan);
                    break;
                case "ganti sim":
                    if (world.getDaftarSim().size() == 1) {
                        System.out.print("Tidak ada sim lain\nApakah kamu ingin membuat Sim baru? (y/n)");
                        String pilihan = input.nextLine();
                        while (!pilihan.equals("y") && !pilihan.equals("n")) {
                            System.out.println("Pilihan tidak tersedia");
                            System.out.print("Apakah kamu ingin membuat Sim baru? (y/n)");
                            pilihan = input.nextLine();
                        }
                        if (pilihan.equals("y")) {
                            generateSim(world);;
                        }

                    } else {
                        System.out.println("Pilih sim yang ingin dimainkan (masukkan angka)\n(Masukkan angka 0 jika ingin menambahkan Sim)");
                        for (int i = 0; i < world.getDaftarSim().size(); i++) {
                            System.out.println((i+1) + ". " + world.getDaftarSim().get(i).getNama());
                        }
                        System.out.print("=> ");
                        int pilihan = input.nextInt();
                        while (pilihan < 0 || pilihan > world.getDaftarSim().size()) {
                            System.out.println("Pilihan tidak tersedia");
                            System.out.print("Pilihan: ");
                            pilihan = input.nextInt();
                        }
                        if (pilihan == 0) {
                            generateSim(world);
                        } else {
                            sim = world.getDaftarSim().get(pilihan-1);
                        }
                    }
                    break;
                case "makan":
                    sim.makan();
                    break;
                case "tidur":
                    sim.tidur();
                    break;
                case "memasak":
                    sim.memasak();
                    break;
                case "buang air":
                    sim.buangAir();
                    break;
                case "main game":
                    sim.mainGame();
                    break;
                case "mandi":
                    sim.mandi();
                    break;
                case "belajar coding":
                    sim.belajarCoding();
                    break;
                case "buka sosmed":
                    sim.bukaSosmed();
                    break;
                case "nonton netflix":
                    sim.nontonNetflix();
                    break;
                case "ikut undian berhadiah":
                    sim.ikutUndianBerhadiah();
                    break;
                case "save":
                    //proses save
                    break;
                case "exit":
                    System.out.println("Apakah kamu ingin melakukan save? (y/n)");
                    String pilihan = input.nextLine();
                    while (!pilihan.equals("y") && !pilihan.equals("n")) {
                        System.out.println("Pilihan tidak tersedia");
                        System.out.print("Apakah kamu ingin menyimpan game? (y/n)");
                        pilihan = input.nextLine();
                    }
                    if (pilihan.equals("y")) {
                        //proses save
                    }
                    exitGame = true;
                    break;
                
                //masih ada beberapa aksi yang belum, nanti ditambahin lagi
            }
            System.out.println("Tekan enter untuk melanjutkan");
            input.nextLine();
        }
        
    }
}