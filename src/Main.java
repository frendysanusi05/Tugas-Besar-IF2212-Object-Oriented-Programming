import java.util.*;

import saveload.Load;
import saveload.Save;
import sim.Sim;
import utilz.Clock;
import utilz.Keyboard;
import utilz.Point;
import world.Ruangan;
import world.Rumah;
import world.World;

public class Main {
    List<Sim> daftarSim = new ArrayList<Sim>();
    static boolean exitGame;
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

        Keyboard input = Keyboard.getInstance();
        boolean exitMainMenu = false;

        while (!exitMainMenu) {
            int pilihan = -999;
            boolean isPilihanValid = false;

            while (!isPilihanValid) {
                System.out.print("\u001B[103m");
                System.out.print("Pilihan: ");
                pilihan = input.getIntKeyboard();
                System.out.print("\u001B[0m");
                if ((pilihan < 1 || pilihan > 4) && (pilihan != -999)) {
                    System.out.println("\nPilihan tidak tersedia\n");
                }
                else isPilihanValid = true;
            }
            switch (pilihan) {
                case 1:
                    newGameSim();
                    exitMainMenu = true;
                    break;
                case 2:
                    World world = load();
                    playSim(world);
                    exitMainMenu = true;
                    break;
                case 3:
                    System.out.println("Help\n");
                    help();
                    break;
                case 4:
                    exitMainMenu = true;
                    break;
            }
        }
        clearConsole();
        System.out.println("\033[1;33m");
        System.out.println("""
            ▄███▄──────────▓───────▓──────────▄███▄
            █████████───────▓─────▓───────█████████
            ▄████▒▒████──────▓───▓──────████▒▒████▄
            ▄███▒▒▒▒▒▒███────▓───▓────███▒▒▒▒▒▒███▄
            ███▒▒▒▓▓▓▒▒▒▒███──███──███▒▒▒▒▓▓▓▒▒▒███
            ███▒▒▒▒▓▓▓▓▒▒▒▒▒█▄▄█▄▄█▒▒▒▒▒▓▓▓▓▒▒▒▒███
            ─███▒▒▒▒▒▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒▒▒▒███
            ─████▒▒▒▒▒▒▓▓▓▓▒▒▓▓▓▓▓▒▒▓▓▓▓▒▒▒▒▒█████
            ──███▒▒▒▒▒▓▓▓▓▒▒▒▒▒▒▒▒▒▒▓▓▓▓▒▒▒▒▒████
            ─▄███▒▒▒▒▒▒▓▓▓▓▒▒▒▒▒▒▒▒▓▓▓▓▒▒▒▒▒▒████▄
            ── ▄██░ ▀█▀░█░█░█▀█░█▄░█░█░█░█▀▀░░███▄
            ── ███░ ░█░░█▀█░█▀█░█░██░█▀▄░▀▀█░░████
            ── ███░ ░▀░░▀░▀░▀░▀░▀░░▀░▀░▀░▀▀▀░░████
            ───██████▒▒▒▒▓▓▓▓▒███▒▓▓▓▓▒▒▒▒██████
            ────█████████▓▒▒▒▒███▒▒▒▒▓█████████
            ────█────▄█▒▒▓▒▒▒█████▒▒▒▓▒▒█▄────█
            ─────█──▄█▒▒▓▓▒▒█─███─█▒▒▓▓▒▒█▄──█
            ───────███▒▒▓▒▒▒█─███─█▒▒▒▓▒▒███
            ──────█████▒▒▒▒██─███─██▒▒▒▒█████
            ─────███████████───█───███████████
            ──────█─███████─────────███████─█
            ─────────██───█─────────█───██
            ─────────█───────────────────█
                """);
        System.out.println("\033[0m");
        System.out.println("\nTerima kasih telah bermain!");
        System.exit(0);
    }

    public static void generateSim (World world) throws Exception {
        // World world = new World();
        System.out.print("\nMasukkan nama pemain: ");
        Keyboard input = Keyboard.getInstance();
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
        sim.setPosisiSim(new Point(3, 3));
        sim.setCurrentRuangan(rumah.getRuangan("Kamar"));
        world.addRumah(rumah, sim);
        world.addSim(sim);
        world.placeRumah(rumah);

        // Animasi, biarin dulu aja
        System.out.print("Generating Sim");
        
        Thread.sleep(1000);
        System.out.print(".");
        Thread.sleep(1000);
        System.out.print(".");
        Thread.sleep(1000);
        System.out.println(".");
        Thread.sleep(1000);

        System.out.println("\n\nWelcome to the game, " + namaSim + "!\n");
        Thread.sleep(1000);
        playSim(world);
    }

    public static void newGameSim() throws Exception {
        World world = World.getInstance();
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
        return Load.load("data/data.json", "data/clock.json");
    }

    public static void cheat(Sim sim) {
        System.out.println("Kriptografi itu mudah, tahu gak tentang ROT13?\nPurngGhorfBBC");
        Keyboard scan = Keyboard.getInstance();
        System.out.println("\nMasukkan password: ");
        String passwd = scan.nextLine();
        if (passwd.equals("CheatTubesOOP")) {
            sim.addKekenyangan(100);
            sim.addKesehatan(100);
            sim.addMood(100);
            sim.addUang(999999);

            sim.setMood(0);

            System.out.println("Calon hacker...");
        }
        else System.out.println("\nPassword salah. NT NT :D\n");
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
            Keyboard input = Keyboard.getInstance();
            int pilihan = input.getIntKeyboard();
            while (pilihan < 1 || pilihan > world.getDaftarSim().size()) {
                System.out.println("Pilihan tidak tersedia");
                System.out.print("Pilihan: ");
                pilihan = input.getIntKeyboard();
            }
            sim = world.getDaftarSim().get(pilihan-1);
        }

        // Set boolean ke false supaya ga exit dari game
        exitGame = false;

        
        System.out.println("\nGenerating World");
        Thread.sleep(1000);
        System.out.print("[                ]");
        Thread.sleep(1000);
        for (int i = 0; i < 18; i++) {
            System.out.print("\b");
        }
        System.out.print("[===             ]");
        Thread.sleep(1000);
        for (int i = 0; i < 18; i++) {
            System.out.print("\b");
        }
        System.out.print("[======          ]");
        Thread.sleep(1000);
        for (int i = 0; i < 18; i++) {
            System.out.print("\b");
        }
        System.out.print("[==========      ]");
        Thread.sleep(1000);
        for (int i = 0; i < 18; i++) {
            System.out.print("\b");
        }
        System.out.print("[================]");
        Thread.sleep(1000);
        for (int i = 0; i < 18; i++) {
            System.out.print("\b");
        }
        Thread.sleep(1000);
        System.out.println();

        while (!exitGame) {
            Keyboard input = Keyboard.getInstance();
            if (!sim.isAlive()) {
                System.out.print("\u001B[41m");
                if (sim.matiDepresi()) System.out.printf("%s mati karena depresi.", sim.getNama());
                else if (sim.matiKelaparan()) System.out.printf("%s mati karena kelaparan.", sim.getNama());
                else if (sim.matiSakit()) System.out.printf("%s mati karena sakit.", sim.getNama());
                System.out.print("\u001B[0m\n\n");
                world.removeSim(sim);

                System.out.println("Ganti ke sim lain...");
                if (world.getDaftarSim().size() == 0) {
                    System.out.println("\033[1;31m");
                    System.out.println("""
┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼
███▀▀▀██┼███▀▀▀███┼███▀█▄█▀███┼██▀▀▀
██┼┼┼┼██┼██┼┼┼┼┼██┼██┼┼┼█┼┼┼██┼██┼┼┼
██┼┼┼▄▄▄┼██▄▄▄▄▄██┼██┼┼┼▀┼┼┼██┼██▀▀▀
██┼┼┼┼██┼██┼┼┼┼┼██┼██┼┼┼┼┼┼┼██┼██┼┼┼
███▄▄▄██┼██┼┼┼┼┼██┼██┼┼┼┼┼┼┼██┼██▄▄▄
┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼
███▀▀▀███┼▀███┼┼██▀┼██▀▀▀┼██▀▀▀▀██▄┼
██┼┼┼┼┼██┼┼┼██┼┼██┼┼██┼┼┼┼██┼┼┼┼┼██┼
██┼┼┼┼┼██┼┼┼██┼┼██┼┼██▀▀▀┼██▄▄▄▄▄▀▀┼
██┼┼┼┼┼██┼┼┼██┼┼█▀┼┼██┼┼┼┼██┼┼┼┼┼██┼
███▄▄▄███┼┼┼─▀█▀┼┼─┼██▄▄▄┼██┼┼┼┼┼██▄
┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼
┼┼┼┼┼┼┼┼██┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼██┼┼┼┼┼┼┼┼┼
┼┼┼┼┼┼████▄┼┼┼▄▄▄▄▄▄▄┼┼┼▄████┼┼┼┼┼┼┼
┼┼┼┼┼┼┼┼┼▀▀█▄█████████▄█▀▀┼┼┼┼┼┼┼┼┼┼
┼┼┼┼┼┼┼┼┼┼┼█████████████┼┼┼┼┼┼┼┼┼┼┼┼
┼┼┼┼┼┼┼┼┼┼┼██▀▀▀███▀▀▀██┼┼┼┼┼┼┼┼┼┼┼┼
┼┼┼┼┼┼┼┼┼┼┼██┼┼┼███┼┼┼██┼┼┼┼┼┼┼┼┼┼┼┼
┼┼┼┼┼┼┼┼┼┼┼█████▀▄▀█████┼┼┼┼┼┼┼┼┼┼┼┼
┼┼┼┼┼┼┼┼┼┼┼┼███████████┼┼┼┼┼┼┼┼┼┼┼┼┼
┼┼┼┼┼┼┼┼▄▄▄██┼┼█▀█▀█┼┼██▄▄▄┼┼┼┼┼┼┼┼┼
┼┼┼┼┼┼┼┼▀▀██┼┼┼┼┼┼┼┼┼┼┼██▀▀┼┼┼┼┼┼┼┼┼
┼┼┼┼┼┼┼┼┼┼▀▀┼┼┼┼┼┼┼┼┼┼┼▀▀┼┼┼┼┼┼┼┼┼┼┼
┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼
                    """);
                    System.out.println("\033[0m");
                    exitGame = true;
                }
                else sim = world.getDaftarSim().get(world.getDaftarSim().size()-1);
            }
            else {
                /* Cek apakah dalam sehari sim sudah makan */
                if (Clock.checkChangeDay()) sim.setIsSudahMakan(false);
                /* Cek apakah sudah 10 menit tanpa tidur */
                sim.checkSudahTidur();

                /* Cek apakah sudah 4 menit setelah makan tanpa buang air */
                sim.checkSudahBuangAir();
                
                // Get current ruangan dan rumah dari sim
                Rumah rumah = world.getCurrentRumah(sim);
                Ruangan ruangan = sim.getCurrentRuangan();

                // Informasi sim dan lokasinya
                sim.printCurrentSimRoom(world);
                Thread.sleep(1000);
                if (world.getDaftarSim().size() > 1) {
                    for (Sim s : world.getDaftarSim()) {
                        if (s.getCurrentRuangan().getIDRuangan().equals(ruangan.getIDRuangan()) && !s.getNama().equals(sim.getNama())) {
                            System.out.println("웃 : Anda");
                            System.out.println("유 : Teman Anda");
                            break;
                        }
                    }
                } 

                System.out.println("\n\nBermain Sebagai " + sim.getNama());

                sim.printStatus();

                // Mengecek furniture di sekitarnya yang bisa diinteract
                sim.checkFurniture(ruangan);

                /* Add change pekerjaan */
                sim.addDaftarAksi("Ganti Pekerjaan");

                /* Add cheat */
                sim.addDaftarAksi("Cheat");

                // Tambah opsi save dan exit supaya bisa keluar sama save game
                sim.addDaftarAksi("Save");
                sim.addDaftarAksi("Exit");

                // Print current value dari atribut milik sim
                sim.printSimAttribute();

                // Daftar aksi yang bisa dilakukan Sim (termasuk save dan exit)
                sim.printDaftarAksi();

                // Minta masukan user buat aksi yang mau dilakukan
                System.out.print("\nApa yang ingin kamu lakukan? ");
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
                    case "pulang":
                        sim.pulang(world);
                        break;
                    case "upgrade rumah":
                        sim.upgradeRumah(rumah, ruangan);
                        break;
                    case "beli barang" :
                        sim.beliBarang();
                        break;
                    case "pindah ruang" :
                        sim.pindahRuang(rumah);
                        break;
                    case "lihat inventory" :
                        sim.lihatInventory();
                        break;
                    case "melihat waktu":
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
                            int pilihan = input.getIntKeyboard();
                            while (pilihan < 0 || pilihan > world.getDaftarSim().size()) {
                                System.out.println("Pilihan tidak tersedia");
                                System.out.print("Pilihan: ");
                                pilihan = input.getIntKeyboard();
                            }
                            if (pilihan == 0) {
                                generateSim(world);
                            } else {
                                sim = world.getDaftarSim().get(pilihan-1);
                            }
                        }
                        break;
                    case "ganti pekerjaan":
                        sim.changePekerjaan();
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
                    case "rebahan":
                        sim.rebahan();
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
                    case "cheat":
                        cheat(sim);
                        break;
                    
                    //masih ada beberapa aksi yang belum, nanti ditambahin lagi
                }
            }
            System.out.println("\nTekan enter untuk melanjutkan");
            input.nextLine();
            clearConsole();
        }
        
        
    }
}