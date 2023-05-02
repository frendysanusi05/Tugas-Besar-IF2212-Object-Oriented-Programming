import java.util.*;

public class Main {
    List<Sim> daftarSim = new ArrayList<Sim>();
    public static void main(String[] args) throws Exception {
        clearConsole();
        System.out.println("\033[1;32m");
        System.out.println("     :::       ::: :::::::::: :::        ::::::::   ::::::::    :::   :::   ::::::::::      ::::::::::: ::::::::"); 
        System.out.println("    :+:       :+: :+:        :+:       :+:    :+: :+:    :+:  :+:+: :+:+:  :+:                 :+:    :+:    :+::"); 
        System.out.println("   +:+       +:+ +:+        +:+       +:+        +:+    +:+ +:+ +:+:+ +:+ +:+                 +:+    +:+    +:+");  
        System.out.println("  +#+  +:+  +#+ +#++:++#   +#+       +#+        +#+    +:+ +#+  +:+  +#+ +#++:++#            +#+    +#+    +:+");   
        System.out.println(" +#+ +#+#+ +#+ +#+        +#+       +#+        +#+    +#+ +#+       +#+ +#+                 +#+    +#+    +#+");    
        System.out.println("#+#+# #+#+#  #+#        #+#       #+#    #+# #+#    #+# #+#       #+# #+#                 #+#    #+#    #+#");     
        System.out.println("###   ###   ########## ########## ########   ########  ###       ### ##########          ###     ########\n");
        System.out.println("\033[1;36m");
        System.out.println(" ________  ___  _____ ______                  ________  ___       ___  ________  ___  _________    ___    ___ ___       ");
        System.out.println("|\\   ____\\|\\  \\|\\   _ \\  _   \\               |\\   __  \\|\\  \\     |\\  \\|\\   ____\\|\\  \\|\\___   ___\\ |\\  \\  /  /|\\  \\      ");
        System.out.println("\\ \\  \\___|\\ \\  \\ \\  \\\\\\__\\ \\  \\  ____________\\ \\  \\|\\  \\ \\  \\    \\ \\  \\ \\  \\___|\\ \\  \\|___ \\  \\_| \\ \\  \\/  / | \\  \\     ");
        System.out.println(" \\ \\_____  \\ \\  \\ \\  \\\\|__| \\  \\|\\____________\\ \\   ____\\ \\  \\    \\ \\  \\ \\  \\    \\ \\  \\   \\ \\  \\   \\ \\    / / \\ \\  \\    ");
        System.out.println("  \\|____|\\  \\ \\  \\ \\  \\    \\ \\  \\|____________|\\ \\  \\___|\\ \\  \\____\\ \\  \\ \\  \\____\\ \\  \\   \\ \\  \\   \\/  /  /   \\ \\_\\   ");
        System.out.println("    ____\\_\\  \\ \\__\\\\__\\    \\ \\__\\              \\ \\__\\    \\ \\_______\\ \\__\\ \\_______\\ \\__\\   \\ \\__\\__/  / /      \\|__|   ");
        System.out.println("   |\\_________\\|__|\\|__|     \\|__|               \\|__|     \\|_______|\\|__|\\|_______|\\|__|    \\|__|\\___/ /           ___ ");
        System.out.println("   \\|_________|                                                                                  \\|___|/           |\\__\\");
        System.out.println("                                                                                                                   \\|__|\n");
        System.out.println("\033[0m");
        System.out.println("Silahkan pilih menu dibawah ini:");                                                                                                                                        
        System.out.println("1. New Game");
        System.out.println("2. Load Game");
        System.out.println("3. Help");
        System.out.println("4. Exit\n");

        Scanner input = new Scanner(System.in);
        boolean exitMainMenu = false;

        while (!exitMainMenu) {
            int pilihan = 0;
            boolean isPilihanValid = false;

            while (!isPilihanValid) {
                System.out.print("\u001B[103m");
                System.out.print("Pilihan: ");
                try {
                    pilihan = input.nextInt();
                    System.out.print("\u001B[0m");
                    if (pilihan < 0 || pilihan > 4) {
                        System.out.println("Pilihan tidak tersedia\n");
                    }
                    else isPilihanValid = true;

                    switch (pilihan) {
                        case 1:
                            newGameSim();
                            exitMainMenu = true;
                            break;
                        case 2:
                            World world = load();
                            playSim(world);
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
                    }
                }
                catch (InputMismatchException e) {
                    System.out.print("\u001B[0m");
                    System.out.println("\nMasukan harus bernilai integer\n");
                    input.nextLine();
                }
            }
        }
        System.out.println("Terima kasih telah bermain!");
    }

    public static void generateSim (World world) throws Exception {
        // World world = new World();
        System.out.print("\nMasukkan nama pemain: ");
        Scanner input = new Scanner(System.in);
        String namaSim = input.nextLine();
        while (world.isSimInWorld(namaSim)) {
            System.out.println("Nama sudah digunakan, silahkan masukkan nama lain");
            System.out.print("Masukkan nama pemain: ");
            namaSim = input.nextLine();
        }
        Sim sim = new Sim(namaSim);
        Random rand = new Random();
        int x = rand.nextInt(58) + 3;
        int y = rand.nextInt(58) + 3;
        Rumah rumah = new Rumah(new Point(x, y));
        sim.setPosisiSim(new Point(x, y));
        world.addRumah(rumah, sim);
        world.addSim(sim);
        world.addCekPosisi(rumah);

        // Animasi, biarin dulu aja
        System.out.println(" Generating Sim...\n");

        System.out.println("\n\nWelcome to the game, " + namaSim + "!\n");
        //Thread.sleep(1000);
        playSim(world);
    }

    public static void newGameSim() throws Exception {
        World world = new World();
        generateSim(world);
    }
    public static void help(){
        System.out.println("Berikut adalah panduan untuk bermain Sim-Plicity:\n");
        System.out.println("1. Objective Game ini untuk menjaga kesejahteraan SIM agar tidak depresi dan mati.");
        System.out.println("2. Pemain dapat melanjutkan SIM yang telah dimainkan atau membuat SIM baru.");
        System.out.println("3. Pastikan untuk save SIM kalian sebelum meninggalkan permainan.");
        System.out.println("4. Pemain menunggu lama-nya aktivitas sesuai dengan waktu yang telah ditentukan.");
        System.out.println("5. Pemain dapat berimajinasi sesuai dengan keinginannya di Game ini.");
        System.out.println("\nSEMOGA MEMBANTU!\n");
    }
    public static void save(World world) {
        Save.save(world);
    }
    public static World load() {
        return Load.load("data/data.json");
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
        System.out.println("\u001B[0m");
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
        System.out.println(" Generating World...\n");

        while (!exitGame) {
            if (!sim.isAlive()) {
                System.out.print("\u001B[41m");
                if (sim.matiDepresi()) System.out.printf("%s mati karena depresi.", sim.getNama());
                else if (sim.matiKelaparan()) System.out.printf("%s mati karena kelaparan.", sim.getNama());
                else if (sim.matiSakit()) System.out.printf("%s mati karena sakit.", sim.getNama());
                System.out.print("\u001B[0m\n\n");
                world.removeSim(sim);

                System.out.println("Ganti ke sim lain...");
                if (world.getDaftarSim().size() == 0) {
                    System.out.println("GAME OVER!!!");
                    exitGame = true;
                }
                else sim = world.getDaftarSim().get(world.getDaftarSim().size()-1);
            }

            /* Cek apakah sudah 10 menit tanpa tidur */
            sim.checkSudahTidur();

            /* Cek apakah sudah 4 menit setelah makan tanpa buang air */
            sim.checkSudahBuangAir();
            
            // Get current ruangan dan rumah dari sim
            Rumah rumah = world.getCurrentRumah(sim);
            Ruangan ruangan = rumah.getCurrentRuanganSim(sim);

            // Informasi sim dan lokasinya
            sim.printCurrentSimRoom(world);
            Thread.sleep(1000);
            System.out.println("\nBermain Sebagai " + sim.getNama());
            System.out.println("Posisi: " + sim.getPosisiSim().getX() + ", " + sim.getPosisiSim().getY());
            // System.out.println("Kamar Mandi: " + kamarMandi.getXRuangan() + ", " + kamarMandi.getYRuangan());
            //rumah.printDaftarRuangan();

            // Mengecek furniture di sekitarnya yang bisa diinteract
            sim.checkFurniture(ruangan);

            /********************* testing only *******************/
            sim.addDaftarAksi("Lihat Inventory");
            sim.addDaftarAksi("Lihat Waktu");
            sim.addDaftarAksi("Pasang Barang");
            sim.addDaftarAksi("Bergerak ke Objek");
            sim.addDaftarAksi("Ganti Sim");
            /************************ end testing *******************/

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
                    sim.berkunjung(world);
                    break;
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
                case "lihat waktu":
                    sim.lihatWaktu();
                    break;
                case "pasang barang" :
                    sim.pasangBarang(ruangan);
                    break;
                case "bergerak ke objek" :
                    sim.moveToFurniture(ruangan, world);
                    break;
                case "ganti sim":
                    if (world.getDaftarSim().size() == 1) {
                        System.out.print("\nTidak ada sim lain\nApakah kamu ingin membuat Sim baru? (y/n) ");
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