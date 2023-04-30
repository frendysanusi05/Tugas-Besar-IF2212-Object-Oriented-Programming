import java.util.*;

public class Main {
    List<Sim> daftarSim = new ArrayList<Sim>();
    public static void main(String[] args) throws Exception {
        clearConsole();
        System.out.println("     :::       ::: :::::::::: :::        ::::::::   ::::::::    :::   :::   ::::::::::      ::::::::::: ::::::::"); 
        System.out.println("    :+:       :+: :+:        :+:       :+:    :+: :+:    :+:  :+:+: :+:+:  :+:                 :+:    :+:    :+::"); 
        System.out.println("   +:+       +:+ +:+        +:+       +:+        +:+    +:+ +:+ +:+:+ +:+ +:+                 +:+    +:+    +:+");  
        System.out.println("  +#+  +:+  +#+ +#++:++#   +#+       +#+        +#+    +:+ +#+  +:+  +#+ +#++:++#            +#+    +#+    +:+");   
        System.out.println(" +#+ +#+#+ +#+ +#+        +#+       +#+        +#+    +#+ +#+       +#+ +#+                 +#+    +#+    +#+");    
        System.out.println("#+#+# #+#+#  #+#        #+#       #+#    #+# #+#    #+# #+#       #+# #+#                 #+#    #+#    #+#");     
        System.out.println("###   ###   ########## ########## ########   ########  ###       ### ##########          ###     ########\n");
        System.out.println(" ________  ___  _____ ______                  ________  ___       ___  ________  ___  _________    ___    ___ ___       ");
        System.out.println("|\\   ____\\|\\  \\|\\   _ \\  _   \\               |\\   __  \\|\\  \\     |\\  \\|\\   ____\\|\\  \\|\\___   ___\\ |\\  \\  /  /|\\  \\      ");
        System.out.println("\\ \\  \\___|\\ \\  \\ \\  \\\\\\__\\ \\  \\  ____________\\ \\  \\|\\  \\ \\  \\    \\ \\  \\ \\  \\___|\\ \\  \\|___ \\  \\_| \\ \\  \\/  / | \\  \\     ");
        System.out.println(" \\ \\_____  \\ \\  \\ \\  \\\\|__| \\  \\|\\____________\\ \\   ____\\ \\  \\    \\ \\  \\ \\  \\    \\ \\  \\   \\ \\  \\   \\ \\    / / \\ \\  \\    ");
        System.out.println("  \\|____|\\  \\ \\  \\ \\  \\    \\ \\  \\|____________|\\ \\  \\___|\\ \\  \\____\\ \\  \\ \\  \\____\\ \\  \\   \\ \\  \\   \\/  /  /   \\ \\_\\   ");
        System.out.println("    ____\\_\\  \\ \\__\\\\__\\    \\ \\__\\              \\ \\__\\    \\ \\_______\\ \\__\\ \\_______\\ \\__\\   \\ \\__\\__/  / /      \\|__|   ");
        System.out.println("   |\\_________\\|__|\\|__|     \\|__|               \\|__|     \\|_______|\\|__|\\|_______|\\|__|    \\|__|\\___/ /           ___ ");
        System.out.println("   \\|_________|                                                                                  \\|___|/           |\\__\\");
        System.out.println("                                                                                                                   \\|__|");
                                                                                                                                       
        
        System.out.println("1. New Game");
        System.out.println("2. Load Game");
        System.out.println("3. Help");
        System.out.println("4. Exit");

        Scanner input = new Scanner(System.in);
        int pilihan = 0;
        boolean isPilihanValid = false;
        while (!isPilihanValid) {
            System.out.print("Pilihan: ");
            try {
                pilihan = input.nextInt();
                isPilihanValid = true;
            }
            catch (InputMismatchException e) {
                System.out.println("\nMasukan harus bernilai integer\n");
                input.nextLine();
            }
        }
        boolean exitMainMenu = false;

        while (!exitMainMenu) {
            switch (pilihan) {
                case 1:
                    newGameSim();
                    exitMainMenu = true;
                    break;
                case 2:
                    load();
                    exitMainMenu = true;
                    break;
                case 3:
                    System.out.println("Help");
                    help();
                    break;
                case 4:
                    System.out.println("Exit");
                    exitMainMenu = true;
                    break;
                default:
                    System.out.println("Pilihan tidak tersedia\n");
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
    public static void help(){
        System.out.println("Berikut adalah panduan untuk bermain Sim-Plicity: ");
        System.out.println("1. Objective Game ini untuk menjaga kesejahteraan SIM agar tidak depresi dan mati.");
        System.out.println("2. Pemain dapat melanjutkan SIM yang telah dimainkan atau membuat SIM baru.");
        System.out.println("3. Pastikan untuk save SIM kalian sebelum meninggalkan permainan.");
        System.out.println("4. Pemain menunggu lama-nya aktivitas sesuai dengan waktu yang telah ditentukan.");
        System.out.println("5. Pemain dapat memilih untuk memainkan salah satu sim yang sudah ada atau membuat sim baru");
        System.out.println("SEMOGA MEMBANTU!");
    }
    public static void save(World world) {
        Save.save(world);
    }
    public static void load() {
        Load.load("data/data.json");
    }

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            
        }
    }

    public static void playSim(World world) throws Exception {
        Sim sim;
        //if there\s only 1 sim, then play that sim
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
        boolean isSudahTidur = false;
        while (!exitGame && sim.isAlive()) {
            // int day = Clock.getDay();
            
            if (Clock.getDiffTimeInSeconds() == 10*60) {
                if (isSudahTidur) isSudahTidur = false;
                else {
                    sim.efekTidakTidur();
                }
            }
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
                System.out.println("Aksi tidak tersedia\n");
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
                    sim.pindahRuang(world, rumah);
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
                    isSudahTidur = true;
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
                    save(world);
                    break;
                case "exit":
                    System.out.print("Apakah kamu ingin melakukan save? (y/n) => ");
                    String pilihan = input.nextLine();
                    while (!pilihan.equals("y") && !pilihan.equals("n")) {
                        System.out.println("Pilihan tidak tersedia");
                        System.out.print("Apakah kamu ingin menyimpan game? (y/n) => ");
                        pilihan = input.nextLine();
                    }
                    if (pilihan.equals("y")) {
                        save(world);
                    }
                    exitGame = true;
                    break;
                
                //masih ada beberapa aksi yang belum, nanti ditambahin lagi
            }
            System.out.println("\nTekan enter untuk melanjutkan");
            input.nextLine();
            clearConsole();
        }
        
    }
}