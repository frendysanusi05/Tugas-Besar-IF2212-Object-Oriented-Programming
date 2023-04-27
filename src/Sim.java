import java.util.Random;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.time.LocalTime;
import java.util.*;

//mmaaf bagian perkerjaan sama kerja gue bingung

public class Sim {
    private String nama;
    private String pekerjaan;
    private int uang;
    public Inventory inventory;
    private int kekenyangan;
    private int mood;
    private int kesehatan;
    private String status;
    private Point posisiSim;
    private List<String> daftarAksi = new ArrayList<String>();

    private volatile boolean isThreadFinished = false;
    private volatile Double durasi;

    private static final String[] daftarPekerjaan = {"Badut Sulap", "Koki", "Polisi", "Programmer", "Dokter"};
    
    /* Scanner */
    Scanner scan = new Scanner(System.in);

    public Sim(String nama) {
        this.nama = nama;
        this.uang = 100;
        this.kekenyangan = 80;
        this.mood = 80;
        this.kesehatan = 80;
        this.inventory = new Inventory();
        this.pekerjaan = daftarPekerjaan[new Random().nextInt(daftarPekerjaan.length)];
        this.status = null; 
        daftarAksi.add("Kerja");
        daftarAksi.add("Olahraga");
        daftarAksi.add("Berkunjung");
        daftarAksi.add("Upgrade Rumah");
        daftarAksi.add("Beli Barang");
        daftarAksi.add("Pindah Ruang");
        daftarAksi.add("Lihat Inventory");
        daftarAksi.add("Pasang Barang");
    }

    public String getNama() {
        return nama;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public int getUang() {
        return uang;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getKekenyangan() {
        return kekenyangan;
    }

    public int getMood() {
        return mood;
    }

    public int getKesehatan() {
        return kesehatan;
    }

    public String getStatus() {
        return status;
    }

    public Point getPosisiSim() {
        return posisiSim;
    }

    public List<String> getDaftarAksi() {
        return daftarAksi;
    }


    // Setter
    public void addUang(int uangTambahan) {
        this.uang = uang + uangTambahan;
    }

    public void addKekenyangan(int kekenyanganTambahan) {
        this.kekenyangan = kekenyangan + kekenyanganTambahan;
    }

    public void addMood(int moodTambahan) {
        this.mood = mood + moodTambahan;
    }

    public void addKesehatan(int kesehatanTambahan) {
        this.kesehatan = kesehatan + kesehatanTambahan;
    }

    public void setStatus(String activity) {
        status = activity;
    }

    public void setPosisiSim(Point posisiSim) {
        this.posisiSim = posisiSim;
    }
    
    public void addDaftarAksi(String aksi) {
        daftarAksi.add(aksi);
    }

    public void removeElmtDaftarAksi(int index) {
        daftarAksi.remove(index);
    }

    // Methods (Aksi)
    public void printDaftarAksi() {
        System.out.println("Daftar Aksi: ");
        for (int i = 0; i < daftarAksi.size(); i++) {
            System.out.println(i+1 + ". " + daftarAksi.get(i));
        }
    }

    public void makan() {
        // Gw coba ganti biar ngikutin speknya yaa - Ariq
        System.out.println("Berikut ini adalah makanan yang ada di inventory: ");

        System.out.println("Bahan Makanan: ");
        inventory.printSpecificItem("Bahan Makanan");

        System.out.println("Masakan: ");
        inventory.printSpecificItem("Masakan");

        // User Input
        System.out.print("Masukkan nama makanan yang ingin dimakan: ");
        
        Scanner input = new Scanner(System.in);
        String namaMakanan = input.nextLine();
        input.close();

        // Input Processing
        if (inventory.containsItem(namaMakanan)) {

            inventory.decreaseItem(namaMakanan, 1);
            durasi = (double) 30;
            System.out.println();
            
            setStatus("Sedang Makan");

            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    Clock.wait(durasi);
                    isThreadFinished = true;
                }
            });

            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    /*
                    +X kekenyangan / 1 siklus makan (30 detik), 
                    X bergantung pada jenis makanan.
                     */

                    int timeInSeconds = LocalTime.now().toSecondOfDay();
                    int duration = 30;
                    if (Clock.isEqualDuration(timeInSeconds, duration)) {
                        switch(namaMakanan) {
                            case "Nasi Ayam":
                                addKekenyangan(16);
                                break;
                            case "Nasi Kari":
                                addKekenyangan(30);
                                break;
                            case "Susu Kacang":
                                addKekenyangan(5);
                                break;
                            case "Tumis Sayur":
                                addKekenyangan(5);
                                break;
                            case "Bistik":
                                addKekenyangan(22);
                                break;
                            case "Nasi":
                                addKekenyangan(5);
                                break;
                            case "Kentang":
                                addKekenyangan(4);
                                break;
                            case "Ayam":
                                addKekenyangan(8);
                                break;
                            case "Sapi":
                                addKekenyangan(15);
                                break;
                            case "Wortel":
                                addKekenyangan(2);
                                break;
                            case "Bayam":
                                addKekenyangan(2);
                                break;
                            case "Kacang":
                                addKekenyangan(2);
                                break;
                            case "Susu":
                                addKekenyangan(1);
                                break;
                        }
                    }
                    
                }
            });

            t1.start();
            t2.start();

            try {
                t1.join();
                t2.join();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            isThreadFinished = false;
            setStatus(null);
            
        } else {
            System.out.println("Makanan tidak ditemukan");
        }

        
    }
    
    public void kerja() {
        System.out.print("Masukkan durasi kerja: ");
        durasi = scan.nextDouble();
        while (durasi % 120 != 0) {
            System.out.println("Durasi kerja harus merupakan kelipatan 120");
            durasi = scan.nextDouble();
        }
        System.out.println();

        setStatus("Sedang Bekerja");
        
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Clock.wait(durasi);
                isThreadFinished = true;
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int timeInSeconds = LocalTime.now().toSecondOfDay();
                int duration = 30;
                
                while (!isThreadFinished) {
                    /*
                    -10 kekenyangan / 30 detik
                    -10 mood / 30 detik
                     */

                    if (Clock.isEqualDuration(timeInSeconds, duration)) {
                        kekenyangan -= 10;
                        mood -= 10;

                        timeInSeconds = LocalTime.now().toSecondOfDay();
                        try {
                            Thread.sleep(1000);
                        }
                        catch (InterruptedException e) {

                        }
                    }
                }
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                int timeInSeconds = LocalTime.now().toSecondOfDay();
                int duration = 4 * 60;

                while (!isThreadFinished) {
                    if (Clock.isEqualDuration(timeInSeconds, duration)) {
                        if (pekerjaan.equals("Badut Sulap")) 
                        {
                            addUang(15);
                        }
                        else if(pekerjaan.equals("Koki"))
                        {
                            addUang(30);
                        }
                        else if(pekerjaan.equals("Polisi"))
                        {
                            addUang(35);
                        }
                        else if(pekerjaan.equals("Programmer"))
                        {
                            addUang(45);
                        }
                        else if(pekerjaan.equals("Dokter"))
                        {
                            addUang(50);
                        }

                        timeInSeconds = LocalTime.now().toSecondOfDay();
                        try {
                            Thread.sleep(1000);
                        }
                        catch (InterruptedException e) {

                        }
                    }
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        isThreadFinished = false;
        setStatus(null);
    }

    public void olahraga() {
        // Gw sesuaian sama spek yaa - Ariq
        System.out.print("Masukkan durasi olahraga: ");
        durasi = scan.nextDouble();
        while (durasi % 20 != 0) {
            System.out.println("Durasi olahraga harus merupakan kelipatan 20");
            durasi = scan.nextDouble();
        }
        System.out.println();

        setStatus("Sedang Berolahraga");
        
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Clock.wait(durasi);
                isThreadFinished = true;
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int timeInSeconds = LocalTime.now().toSecondOfDay();
                int duration = 240;
                
                while (!isThreadFinished) {
                    /*
                    +5 kesehatan/20 detik
                    -5 kekenyangan/20 detik
                    +10 mood/20 detik
                    */

                    if (Clock.isEqualDuration(timeInSeconds, duration)) {
                        addKesehatan(5);
                        addKekenyangan(-5);
                        addMood(10);

                        timeInSeconds = LocalTime.now().toSecondOfDay();
                        try {
                            Thread.sleep(1000);
                        }
                        catch (InterruptedException e) {
                        }
                    }
                }
            }
        });


        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        isThreadFinished = false;
        setStatus(null);
    }

    public void tidur() {
        System.out.print("Masukkan durasi tidur (detik): ");
        durasi = scan.nextDouble();
        while (durasi < 180) {
            System.out.println("Durasi tidur harus lebih dari sama dengan 3 menit");
            System.out.print("Masukkan durasi tidur: ");
            durasi = scan.nextDouble();
        }
        System.out.println();

        setStatus("Sedang Tidur");
        
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Clock.wait(durasi);
                isThreadFinished = true;
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int timeInSeconds = LocalTime.now().toSecondOfDay();
                int duration = 20;
                
                while (!isThreadFinished) {
                    /*
                    +30 mood / 4 menit
                    +20 kesehatan / 4 menit
                    */

                    if (Clock.isEqualDuration(timeInSeconds, duration)) {
                        addKesehatan(20);
                        addMood(30);

                        timeInSeconds = LocalTime.now().toSecondOfDay();
                        try {
                            Thread.sleep(1000);
                        }
                        catch (InterruptedException e) {
                        }
                    }
                }
            }
        });


        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        isThreadFinished = false;
        setStatus(null);
    }

    public void makan(Edible makanan) {
    //mengambil makanan di Inventory
        System.out.println("Berikut ini adalah makanan yang ada di inventory: ");

        System.out.println("Bahan Makanan : ");
        inventory.printSpecificItem("Bahan Makanan");

        System.out.println("Masakan : ");
        inventory.printSpecificItem("Masakan");
    
    //memilih makanan 
        System.out.print("Masukkan makanan yang ingin dimakan: ");
        
        Scanner input = new Scanner(System.in);
        String namaMakanan = input.nextLine();
        input.close();
    
        //memastikan makanan ada di Inventor
        if (inventory.containsItem(namaMakanan)) {
            inventory.decreaseItem(namaMakanan, 1);
            durasi = (double) 30;
            System.out.println();
            
            setStatus("Sedang Makan");

            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    Clock.wait(durasi);
                }
            });

            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    /*
                    +X kekenyangan / 1 siklus makan (30 detik), 
                    X bergantung pada jenis makanan.
                     */

                    int timeInSeconds = LocalTime.now().toSecondOfDay();
                    int duration = 30;
                    if (Clock.isEqualDuration(timeInSeconds, duration)) {
                        switch(namaMakanan){
                            case "Nasi" :
                                addKekenyangan(5);
                                break;
                            case "Kentang" :
                                addKekenyangan(4);
                                break;
                            case "Ayam" :
                                addKekenyangan(8);
                                break;
                            case "Sapi" :
                                addKekenyangan(15);
                                break;
                            case "Wortel" :
                                addKekenyangan(2);
                                break;
                            case "Bayam" :
                                addKekenyangan(2);
                                break;
                            case "Kacang" :
                                addKekenyangan(2);
                                break;
                            case "Susu" :
                                addKekenyangan(1);
                                break;
                            case "Nasi Ayam" :
                                addKekenyangan(16);
                                break;
                            case "Nasi Kari" :
                                addKekenyangan(30);
                                break;
                            case "Susu Kacang" :
                                addKekenyangan(5);
                                break;
                            case "Tumis Sayur" :
                                addKekenyangan(5);
                                break;
                            case "Bistik" :
                                addKekenyangan(22);
                                break;
                        }
                    }
                }
            });

            t1.start();
            t2.start();
        }
    }

    public void memasak() throws Exception {
        // print "Berikut ini adalah bahan makanan yang kamu miliki: " then shows the list of all items in the inventory
        // execute 
        System.out.println("Berikut ini adalah bahan makanan yang kamu miliki:");
        inventory.printSpecificItem("Bahan Makanan");
        List<Masakan> listMasakan = new ArrayList<Masakan>();
        Masakan nasiAyam = new Masakan("Nasi Ayam");
        Masakan nasiKari = new Masakan("Nasi Kari");
        Masakan susuKacang = new Masakan("Susu Kacang");
        Masakan tumisSayur = new Masakan("Tumis Sayur");
        Masakan bistik = new Masakan("Bistik");
        listMasakan.add(nasiAyam);
        listMasakan.add(nasiKari);
        listMasakan.add(susuKacang);
        listMasakan.add(tumisSayur);
        listMasakan.add(bistik);

        System.out.println("Berikut ini adalah daftar makanan yang dapat kamu masak:");
        for (Masakan masakan : listMasakan) {
            System.out.println(masakan.getName());
            masakan.printDaftarBahanMakanan();
        }

        System.out.print("Masukkan nama masakan yang ingin dimasak: ");
        Scanner input = new Scanner(System.in);
        String namaMasakan = input.nextLine();
        input.close();

        // Make the first character of every word in the namaMasakan is upper case, and the rest is lower case
        namaMasakan = namaMasakan.toLowerCase();
        namaMasakan = namaMasakan.substring(0, 1).toUpperCase() + namaMasakan.substring(1);

        Masakan masakanBaru = new Masakan(namaMasakan);
        if (masakanBaru.getName() != null) {
            // Check if the ingredients of masakanBaru are in the inventory
            boolean isAllIngredientsAvailable = true;
            for (String bahan : masakanBaru.getDaftarBahanMakanan()) {
                if (!inventory.containsItem(bahan)) {
                    isAllIngredientsAvailable = false;
                    break;
                }
            }

            if (isAllIngredientsAvailable) {
                // Remove the ingredients from the inventory
                for (String bahan : masakanBaru.getDaftarBahanMakanan()) {
                    inventory.decreaseItem(bahan, 1);
                }

                durasi = 1.5 * masakanBaru.getKekenyangan();
                System.out.println();

                setStatus("Sedang Memasak");
                
                Thread t1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Clock.wait(durasi);
                        isThreadFinished = true;
                    }
                });

                t1.start();

                try {
                    t1.join();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }

                isThreadFinished = false;
                setStatus(null);
                addMood(10);

                // Add the masakan to the inventory
                inventory.addItem(masakanBaru.getName());
            } else {
                System.out.println("Bahan makanan tidak cukup");
            }
        }
    }   

    public void berkunjung(Rumah rumahSim, Rumah rumahTemanSim){
        //semangat deh ngitung waktunya makai koordinat rumah
        // rumusnya sqrt(((x2-x1)^2) + ((y2-y1)^2))
        // x2 dan y2 adalah koordinat rumah yang akan dikunjungi
        // x1 dan y1 adalah koordinat rumah Sim
        mood += 10;
        kekenyangan -= 10;
    }   

    public void buangAir(){
    //kayak tidur bingungnya
    //buang air setidaknya 1 kali setiap habis makan. Apabila tidak dilakukan, maka mood dan kesehatan Sim akan berkurang.

            kekenyangan -= 20;
            addMood(10);
        
    }

    public void upgradeRumah(){
        //menambah ruangan
        //membutuhkan waktu sejumlah 18 menit
    }

    public void beliBarang(){
        //mengisi rumahnya dengan barang-barang.
        //waktu kedatangan barang tidak dapat dipastikan.
        //durasi pengiriman barang akan selalu acak tetapi tetap dalam range waktu X menit.
        ArrayList<String> furniture = new ArrayList<String>() {{
            add("Kasur Single");
            add("Kasur Queen Size");
            add("Kasur King Size");
            add("Toilet");
            add("Kompor Gas");
            add("Kompor Listrik");
            add("Meja dan Kursi");
            add("Jam");
        }};

        ArrayList<String> bahanMakanan = new ArrayList<String>() {{
            add("Nasi");
            add("Kentang");
            add("Ayam");
            add("Sapi");
            add("Wortel");
            add("Bayam");
            add("Kacang");
            add("Susu");
        }};

        System.out.println("""
        Berikut ini adalah barang yang bisa dibeli:\n
            +-----------------------------------------------+
            |     |                 Kategori                |
            | No. |-----------------------------------------+
            |     |      Furniture      |   Bahan Makanan   |
            +-----+-----------------------------------------+
            |  1. | Kasur Single        | Nasi              |
            |  2. | Kasur Queen Size    | Kentang           |
            |  3. | Kasur King Size     | Ayam              |
            |  4. | Toilet              | Sapi              |
            |  5. | Kompor Gas          | Wortel            |
            |  6. | Kompor Listrik      | Bayam             |
            |  7. | Meja dan Kursi      | Kacang            |
            |  8. | Jam                 | Susu              |
            +-----+-----------------------------------------+
        """);

        Scanner scan = new Scanner(System.in);
        System.out.print("Masukkan nama barang yang ingin dibeli: ");
        String item = scan.nextLine();
        System.out.println();
        
        while (!furniture.contains(item) && !bahanMakanan.contains(item)) {
            System.out.println("Masukan invalid! Masukan harus ada di tabel.");
            System.out.print("Masukkan nama barang yang ingin dibeli: ");
            item = scan.nextLine();
            System.out.println();
        }
        
        int durasi = (new Random().nextInt(4) + 1) /* between (1,5) */ * 30;
        System.out.printf("%s akan segera dikirim dalam waktu %d detik\n", item, durasi);
        Clock.wait((double)durasi);
        System.out.printf("%s telah diterima\n", item);

        Object barang = new Object();

        if (furniture.contains(item)) {
            try {
                barang = new Furniture(item);
            }
            catch (Exception e) {

            }
        }
        else if (bahanMakanan.contains(item)) {
            try {
                barang = new BahanMakanan(item);
            }
            catch (Exception e) {
                
            }
        }

        if (barang instanceof Furniture) uang -= ((Furniture) barang).getHarga();
        else if (barang instanceof BahanMakanan) uang -= ((BahanMakanan) barang).getHarga();

        inventory.addItem(item);
    }

    public void pindahRuang(){
        
    }

    public void lihatInventory(){
        //berisi dengan makanan, barang-barang yang sedang tidak terpasang pada ruangan, dan objek-objek lainnya.
    }

    public void addInventory() {
        //gatau ada di sheet spek tapi gaada di doc spesifikasi
    }

    public void pasangBarang(Ruangan ruangan) throws Exception {

        AtomicBoolean isFinished = new AtomicBoolean(false);
        inventory.printSpecificItem("Furniture");
        System.out.println("Masukkan nama furniture yang ingin dipasang (contoh: Meja Makan) ");
        Scanner input = new Scanner(System.in);
        String namaFurniture = input.nextLine();

        while (!isFinished.get()) {
            try {
                if (!inventory.containsItem(namaFurniture)) {
                    throw new Exception();
                }
                isFinished.set(true);
            } catch (Exception e) {
                System.out.println("Tidak ada furniture dengan nama tersebut di inventory");
                System.out.println("Masukkan nama furniture yang ingin dipasang (contoh: Meja Makan) ");
                namaFurniture = input.nextLine();
                continue;
            }
        }
        isFinished.set(false);

        while (!isFinished.get()) {
            //boolean isRotated = false;
            Furniture barang = new Furniture(namaFurniture);
            int x, y;
            ruangan.printRuangan(this);
            System.out.println("(Posisi yang dimasukkan akan menjadi titik terkiri-atas dari "+ namaFurniture + ") ");
            try {
                System.out.print("Masukkan posisi x furniture: ");
                x = input.nextInt();
                System.out.println();
                System.out.print("Masukkan posisi y furniture: ");
                y = input.nextInt();
                System.out.println();

                if (x < 0 || y < 0 || x > 5 || y > 5) {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Input tidak valid!");
                input.nextLine();
                continue;
            }

            try {
                System.out.print("Apakah ingin diputar? (y/n) ");
                String jawaban = input.next();
                if (jawaban.equals("y")) {
                    barang.rotateFurniture();
                } else if (!jawaban.equals("n")) {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Input tidak valid!");
                input.nextLine();
                continue;
            }
            barang.setXFurniture(x + ruangan.getXRuangan() - 3);
            barang.setYFurniture(y + ruangan.getYRuangan() - 3);
            // try {
            //     if (!ruangan.isFurniturePlacable(barang, isRotated)) {
            //         throw new Exception();
            //     }
            // } catch (Exception e) {
            //     System.out.println("Furniture tidak dapat ditempatkan di posisi tersebut!");
            //     input.nextLine();
            //     continue;
            // }
            ruangan.insertObjectToRuangan(namaFurniture, new Point(barang.getXFurniture(), barang.getYFurniture()), isFinished);
            
            // isInputValid = true;
        }
        input.close();
        inventory.decreaseItem(namaFurniture, 1);
    }

    public void lihatWaktu() {
        //membutuhkan objek Jam
        //menunjukkan sisa waktu pada hari tersebut beserta sisa waktu yang masih ada untuk seluruh tindakan yang bisa ditinggal
    }

    public void moveTo(Point point) {
        posisiSim.setX(point.getX());
        posisiSim.setY(point.getY());
    }

    public void moveToFurniture(Ruangan ruangan) throws Exception {
        System.out.print("Masukkan nama furniture yang ingin dituju: ");
        Scanner input = new Scanner(System.in);
        String namaFurniture = input.nextLine();
        Furniture furniture = new Furniture(namaFurniture);

        if (!ruangan.isFurnitureInRuangan(furniture)) {
            System.out.println("Furniture tidak ada di ruangan!");
        } else {
            System.out.println("Bergerak menuju" + namaFurniture);
            moveTo(new Point(furniture.getXFurniture(), furniture.getYFurniture()));
        }
    }

    // public void checkFurniture(Ruangan ruangan) {
    //     // remove element of daftaraksi from 8th index (daftarAksi index starts from 0), and then
    //     // check the furniture near sim and get the aksi that can be done, put it inside daftarAksi
    //     // if there is no furniture near sim, there will be no addition to daftarAksi
        
    //     if (daftarAksi.size() > 8) {
    //         for (int i = 8; i < daftarAksi.size(); i++) {
    //             daftarAksi.remove(i);
    //         }
    //     }

        



    // }
    //harus buat 7 aksi lain yang dapat berhubungan dengan objek sesuai dengan kreasi masing-masing.
}