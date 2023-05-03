// import java.util.Random;
// import java.util.Map;
// import java.util.HashMap;
// import java.util.ArrayList;
// import java.util.Scanner;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Sim {
    private String nama;
    private String pekerjaan;
    private int uang;
    private Inventory inventory;
    private int kekenyangan;
    private int mood;
    private int kesehatan;
    private String status;
    private Point posisiSim;
    private boolean isOutside;

    /*** Atribut Baru****/
    private Ruangan currentRuangan;
    /**** 
     * Setiap kali pindahRuang, currentRuangan-nya diupdate
    ****/

    private List<String> daftarAksi = new ArrayList<String>();

    private volatile boolean isThreadFinished = false;
    private volatile Double durasi;

    private static final String[] daftarPekerjaan = {"Badut Sulap", "Koki", "Polisi", "Programmer", "Dokter"};

    private boolean isSudahMakan = false;

    int timeTidur = Clock.convertToSeconds(Clock.getTime());
    boolean isSudahTidur = false;
    int jumlahTidakTidur = 0;

    int timeBuangAir = Clock.convertToSeconds(Clock.getTime());
    boolean isSudahBuangAir = false;
    int jumlahTidakBuangAir = 0;

    volatile boolean isBeliBarang = false;
    String statusBeliBarang;
    int timeBeliBarang = 0;
    int lamaBeliBarang = 0;

    volatile boolean isUpgradeRumah = false;
    String statusUpgradeRumah;
    int timeUpgradeRumah = 0;
    int lamaUpgradeRumah = 0;

    private volatile boolean isAksiAktif = false;
    volatile boolean stopTimeUpgradeRumah = false;
    volatile boolean stopTimeBeliBarang = false;

    public Sim(String nama) {
        this.nama = nama;
        this.uang = 10000;
        this.kekenyangan = 80;
        this.mood = 80;
        this.kesehatan = 80;
        this.inventory = new Inventory();
        this.pekerjaan = daftarPekerjaan[new Random().nextInt(daftarPekerjaan.length)];
        this.status = null; 
        daftarAksi.add("Kerja");
        daftarAksi.add("Olahraga");
        daftarAksi.add("Tidur");
        daftarAksi.add("Makan");
        daftarAksi.add("Memasak");
        daftarAksi.add("Berkunjung");
        daftarAksi.add("Buang Air");
        daftarAksi.add("Upgrade Rumah");
        daftarAksi.add("Beli Barang");
        daftarAksi.add("Pindah Ruang");
        daftarAksi.add("Lihat Inventory");
        daftarAksi.add("Lihat Waktu");
        daftarAksi.add("Pasang Barang");
        daftarAksi.add("Bergerak ke Objek");
        daftarAksi.add("Ganti Sim");
    }

    // Getter
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

    public int getXSim() {
        return posisiSim.getX();
    }

    public int getYSim() {
        return posisiSim.getY();
    }

    public List<String> getDaftarAksi() {
        return daftarAksi;
    }

    public boolean getIsAksiAktif() {
        return isAksiAktif;
    }

    public boolean getIsOutside() {
        return isOutside;
    }

    public Ruangan getCurrentRuangan() {
        return currentRuangan;
    }

    public boolean getIsSudahMakan() {
        return isSudahMakan;
    }


    // Setter
    public void setIsSudahMakan(boolean isSudahMakan) {
        this.isSudahMakan = isSudahMakan;
    }

    public void addUang(int uangTambahan) {
        uang += uangTambahan;
        if (uang > 100) uang = 100;
    }

    public void minUang(int uangTambahan) {
        uang -= uangTambahan;
        if (uang < 0) uang = 0;
    }

    public void addKekenyangan(int kekenyanganTambahan) {
        kekenyangan += kekenyanganTambahan;
        if (kekenyangan < 0) kekenyangan = 0;
        else if (kekenyangan > 100) kekenyangan = 100;
    }

    public void addMood(int moodTambahan) {
        mood += moodTambahan;
        if (mood < 0) mood = 0;
        else if (mood > 100) mood = 100;
    }

    public void addKesehatan(int kesehatanTambahan) {
        kesehatan += kesehatanTambahan;
        if (kesehatan < 0) kesehatan = 0;
        else if (kesehatan > 100) kesehatan = 100;
    } 

    public void setStatus(String activity) {
        status = activity;
    }

    public void setKekenyangan(int kekenyangan) {
        this.kekenyangan = kekenyangan;
    }
    
    public void setMood(int mood) {
        this.mood = mood;
    }

    public void setKesehatan(int kesehatan) {
        this.kesehatan = kesehatan;
    }

    public void setUang(int uang) {
        this.uang = uang;
    }
    
    public void setPosisiSim(Point newPos) {
        if (posisiSim == null) {
            posisiSim = new Point(0, 0);
        }
        Point copiedPoint = posisiSim.clone();
        copiedPoint.setX(newPos.getX());
        copiedPoint.setY(newPos.getY());
        posisiSim = copiedPoint;
    }

    public void setCurrentRuangan(Ruangan ruangan) {
        currentRuangan = ruangan;
    }
    
    public void addDaftarAksi(String aksi) {
        daftarAksi.add(aksi);
    }

    public void removeElmtDaftarAksi(int index) {
        daftarAksi.remove(index);
    }

    public boolean isAlive() {
        return matiDepresi() && matiKelaparan() && matiSakit();
    }

    public boolean matiDepresi() {
        return mood > 0 && mood <= 100;
    }

    public boolean matiKelaparan() {
        return kekenyangan > 0 && kekenyangan <= 100;
    }

    public boolean matiSakit() {
        return kesehatan > 0 && kesehatan <= 100;
    }

    // Methods (Aksi)
    public void makan() {
        if (inventory.isItemTypeInInventory("Bahan Makanan") || inventory.isItemTypeInInventory("Masakan")) {
            System.out.println("Berikut ini adalah makanan yang ada di inventory: ");

            System.out.println("Bahan Makanan : ");
            inventory.printSpecificItem("Bahan Makanan");

            System.out.println("Masakan : ");
            inventory.printSpecificItem("Masakan");
        } else {
            System.out.println("Inventory kosong\nKamu bisa membeli makanan di Indimaret");
            return;
        }
    
        System.out.print("Masukkan makanan yang ingin dimakan: ");
        
        Scanner input = new Scanner(System.in);
        String namaMakanan = input.nextLine();

        while (!inventory.containsItem(namaMakanan)) {
            System.out.println("\nMakanan tidak ada di inventory\n");
            System.out.print("Masukkan makanan yang ingin dimakan: ");
            namaMakanan = input.nextLine();
        }

        durasi = (double) 30;
        System.out.println();
        
        setStatus("Sedang Makan");
        System.out.printf("Sedang makan %s...\n", namaMakanan);

        isAksiAktif = true;
        Clock.wait(durasi);
        isAksiAktif = false;
        inventory.decreaseItem(namaMakanan, 1);
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
        System.out.println();
        System.out.println("Selesai Makan");
        isSudahMakan = true;
    }
    
    public void kerja() {
        Scanner scan = new Scanner(System.in);
        
        boolean isInputDouble = false;
        while (!isInputDouble) {
            try {
                System.out.print("Masukkan durasi kerja: ");
                durasi = scan.nextDouble();
                while (durasi % 120 != 0) {
                    System.out.println("Durasi kerja harus kelipatan 120");
                    System.out.print("Masukkan kembali durasi kerja: ");
                    durasi = scan.nextDouble();
                }
                isInputDouble = true;
            }
            catch (Exception e) {
                System.out.println("Masukan harus double");
                scan.next();
            }
        }

        System.out.println();
        setStatus("Sedang Bekerja");
        System.out.println("Sedang bekerja...");
        
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
                isAksiAktif = true;
                while (!isThreadFinished) {
                    /*
                    -10 kekenyangan / 30 detik
                    -10 mood / 30 detik
                     */

                    if (Clock.isEqualDuration(timeInSeconds, duration)) {
                        addKekenyangan(-10);
                        addMood(-10);

                        timeInSeconds = LocalTime.now().toSecondOfDay();
                        try {
                            Thread.sleep(1000);
                        }
                        catch (InterruptedException e) {

                        }
                    }
                }
                isAksiAktif = false;
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
        System.out.println("\nSelesai bekerja");
        isThreadFinished = false;
        setStatus(null);
    }

    public void olahraga() {
        Scanner scan = new Scanner(System.in);
        boolean isInputDouble = false;
        while (!isInputDouble) {
            try {
                System.out.print("Masukkan durasi olahraga: ");
                durasi = scan.nextDouble();
                while (durasi % 2 != 0) {
                // while (durasi % 20 != 0) {
                    System.out.println("Durasi olahraga harus merupakan kelipatan 20");
                    System.out.print("Masukkan kembali durasi olahraga: ");
                    durasi = scan.nextDouble();
                }
                isInputDouble = true;
            }
            catch (Exception e) {
                System.out.println("Masukan harus double");
                scan.next();
            }
        }

        System.out.println();
        setStatus("Sedang Berolahraga");
        System.out.println("Sedang berolahraga...");
        
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
                
                isAksiAktif = true;
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
                isAksiAktif = false;
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
        System.out.println("\nSelesai berolahraga");
        isThreadFinished = false;
        setStatus(null);
    }

    public void tidur() {
        Scanner scan = new Scanner(System.in);
        boolean isInputDouble = false;
        while (!isInputDouble) {
            try {
                System.out.print("Masukkan durasi tidur: ");
                durasi = scan.nextDouble();
                while (durasi < 3*60) {
                    System.out.println("Durasi tidur harus lebih dari sama dengan 3 menit");
                    System.out.print("Masukkan durasi tidur: ");
                    durasi = scan.nextDouble();
                }
                isInputDouble = true;
            }
            catch (Exception e) {
                System.out.println("Masukan harus double");
                scan.next();
            }
        }
        System.out.println();
        System.out.println("\nSedang tidur...");
        
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
                int duration = 3*60;
                isAksiAktif = true;
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
                isAksiAktif = false;
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
        isSudahTidur = true;
    }

    public void memasak() throws Exception {
        if (!inventory.isItemTypeInInventory("Bahan Makanan")) {
            System.out.println("Kamu belum memiliki bahan makanan\nKamu bisa membeli makanan di Indimaret");
            return;
        }
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

        System.out.println("+-------------------------+----------------------------------+");
        System.out.println("| Masakan                 | Bahan Makanan                    |");
        System.out.println("+-------------------------+----------------------------------+");
        for (Masakan masakan : listMasakan) {
            System.out.print("| ");
            System.out.printf("%-24s", masakan.getName());
            System.out.print("| ");
            String daftar = "";
            int i = 0;
            for (String bahanMakanan : masakan.getDaftarBahanMakanan()) {
                if (i == masakan.getDaftarBahanMakanan().length-1) {
                    daftar += bahanMakanan;
                } else {
                    daftar += bahanMakanan + ", ";
                }   
            }
            System.out.printf("%-33s", daftar);
            System.out.println("|");
            System.out.println("+-------------------------+----------------------------------+");
        }


        System.out.print("Masukkan nama masakan yang ingin dimasak: ");
        Scanner input = new Scanner(System.in);
        String namaMasakan = input.nextLine(); 

        boolean found = false;
        for (Masakan masakan : listMasakan) {
            if (masakan.getName().equals(namaMasakan)) {
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Masakan tidak ditemukan");
            return;
        }
       
        Masakan masakanBaru = new Masakan(namaMasakan);
        boolean isAllIngredientsAvailable = true;
        for (String bahan : masakanBaru.getDaftarBahanMakanan()) {
            if (!inventory.containsItem(bahan)) {
                isAllIngredientsAvailable = false;
                break;
            }
        }

        if (isAllIngredientsAvailable) {
            for (String bahan : masakanBaru.getDaftarBahanMakanan()) {
                inventory.decreaseItem(bahan, 1);
            }

            durasi = 1.5 * masakanBaru.getKekenyangan();

            System.out.println();
            System.out.println("Sedang memasak...");
            
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    isAksiAktif = true;
                    Clock.wait(durasi);
                    isAksiAktif = false;
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
            System.out.println("\nSelesai memasak");
            addMood(10);

            inventory.addItem(masakanBaru.getName());
        } else {
            System.out.println("Bahan makanan tidak cukup!");
        }
    }   

    public void berkunjung(World world){
        //semangat deh ngitung waktunya makai koordinat rumah
        // rumusnya sqrt(((x2-x1)^2) + ((y2-y1)^2))
        // x2 dan y2 adalah koordinat rumah yang akan dikunjungi
        // x1 dan y1 adalah koordinat rumah Sim

        if (world.getDaftarSim().size() == 1) {
            System.out.println("Tidak ada teman yang bisa dikunjungi");
            return;
        }
        System.out.println("Berikut ini adalah daftar teman yang bisa kamu kunjungi:");
        int i = 1;
        for (Sim sim : world.getDaftarSim()) {
            if (!sim.getNama().equals(this.getNama())) {
                System.out.println(i + ". " + sim.getNama());
                i++;
            }
        }
        System.out.print("Masukkan nama teman yang ingin dikunjungi: ");
        Scanner input = new Scanner(System.in);
        String namaTeman = input.nextLine();
        if (nama.equals(namaTeman)) {
            System.out.println("Kamu tidak bisa mengunjungi dirimu sendiri");
            return;
        }
        if (!world.isSimInWorld(namaTeman)) {
            System.out.println("Teman tidak ditemukan");
            return;
        }
        Sim temanSim = world.getSim(namaTeman);
        Rumah rumahTemanSim = world.getRumahSim(temanSim);
        Rumah rumahSim = world.getRumahSim(this);

        double x1 = rumahSim.getXRumah();
        double y1 = rumahSim.getYRumah();
        double x2 = rumahTemanSim.getXRumah();
        double y2 = rumahTemanSim.getYRumah();
        double waktuBerkunjung = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
        System.out.println("Waktu berkunjung: " + waktuBerkunjung + " detik");
        System.out.println("Apakah kamu yakin akan berkunjung ke rumah " + temanSim.getNama() + "? (y/n)");
        String jawaban = input.nextLine();
        while (!jawaban.equals("y") && !jawaban.equals("n")) {
            System.out.println("Masukan invalid");
            System.out.println("Apakah kamu yakin akan berkunjung ke rumah " + temanSim.getNama() + "? (y/n)");
            jawaban = input.nextLine();
        }
        if (jawaban.equals("n")) {
            return;
        }
        /**** Buat Testing ****/
        // double waktuBerkunjung = 0;

        System.out.println("Mengunjungi rumah " + temanSim.getNama() + "...");
        setStatus("Berkunjung");

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                isAksiAktif = true;
                Clock.wait(waktuBerkunjung);
                isAksiAktif = false;
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int timeInSeconds = LocalTime.now().toSecondOfDay();
                int duration = 30;
                
                    if (Clock.isEqualDuration(timeInSeconds, duration)) {
                        addMood(10);
                        addKekenyangan(-10);
                        timeInSeconds = LocalTime.now().toSecondOfDay();
                        try {
                            Thread.sleep(1000);
                        }
                        catch (InterruptedException e) {
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
        isOutside = true;
        currentRuangan = rumahTemanSim.getRuangan("Kamar");
        setPosisiSim(new Point(3,3));
        setStatus(null);
    }

    public void pulang(World world) {
        Rumah rumahTemanSim = world.getCurrentRumah(this);
        Rumah rumahSim = world.getRumahSim(this);
        double x1 = rumahSim.getXRumah();
        double y1 = rumahSim.getYRumah();
        double x2 = rumahTemanSim.getXRumah();
        double y2 = rumahTemanSim.getYRumah();
        double durasi = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
        System.out.println("Waktu pulang: " + durasi + " detik");
        System.out.println("Apakah kamu yakin akan pulang? (y/n)");
        Scanner input = new Scanner(System.in);
        String jawaban = input.nextLine();
        while (!jawaban.equals("y") && !jawaban.equals("n")) {
            System.out.println("Masukan invalid");
            System.out.println("Apakah kamu yakin akan pulang? (y/n)");
            jawaban = input.nextLine();
        }
        if (jawaban.equals("n")) {
            return;
        }
        System.out.println("Pulang ke rumah...");
        isAksiAktif = true;
        Clock.wait(durasi);
        isAksiAktif = false;
        isOutside = false;
    }

    public void buangAir(){
        durasi = (double)10;
        System.out.println("\nSedang buang air...");
        isAksiAktif = true;
        Clock.wait(durasi);
        isAksiAktif = false;
        System.out.println("\nSelesai buang air");
        addKekenyangan(-20);
        addMood(10);
        isSudahBuangAir = true;
    }

    public void upgradeRumah(Rumah rumah, Ruangan ruangan) throws Exception{
        //menambah ruangan
        //membutuhkan waktu sejumlah 18 menit
        if (isOutside) {
            System.out.println("Kamu tidak bisa upgrade rumah di rumah orang lain");
            return;
        }

        if (statusUpgradeRumah == "Sedang Upgrade Rumah") {
            System.out.println("Rumah sedang di-upgrade, belum bisa menggunakan aksi ini.");
        } else {
            lamaUpgradeRumah = 18*60;
            int hargaUpgradeRumah = 1500;
            //List<Point> available = world.checkAvailable(rumah);

            //cek space di worldnya masih cukup apa ngga sama ngecek saldo cukup apa ngga
            ArrayList<String> expandable = new ArrayList<String>();
            if (ruangan.getRuanganAtas() == null) {
                expandable.add("Atas");
            } 
            if (ruangan.getRuanganBawah() == null) {
                expandable.add("Bawah");
            } 
            if (ruangan.getRuanganKiri() == null) {
                expandable.add("Kiri");
            } 
            if (ruangan.getRuanganKanan() == null) {
                expandable.add("Kanan");
            }

            if (getUang() >= hargaUpgradeRumah && expandable.size() > 0) {
                for (int i = 0; i < expandable.size(); i++) {
                    System.out.println((i + 1) + ". " + expandable.get(i));
                }
                Scanner input = new Scanner(System.in);
                String pilihan = "";
                boolean isInputValid = false;
                while (!isInputValid) {
                    try {
                        System.out.println("Ke mana kamu ingin menambah ruangan?");
                        pilihan = input.nextLine();
                        while (!expandable.contains(pilihan)) {
                            System.out.println("Pilihan tidak valid");
                            System.out.println("Ke mana kamu ingin menambah ruangan?");
                            input = new Scanner(System.in);
                            pilihan = input.nextLine();
                        }
                        isInputValid = true;
                    }
                    catch (Exception e) {
                        System.out.println("Masukan harus String");
                        input.next();
                    }
                }

                System.out.print("Apa nama ruangan baru itu? ");
                input = new Scanner(System.in);
                String namaRuangan = input.nextLine();

                // Point point = available.get(pilihan - 1);

                System.out.println("Mengupgrade rumah...");
                //ubah status
                timeUpgradeRumah = Clock.convertToSeconds(Clock.getTime());
                statusUpgradeRumah = "Sedang Upgrade Rumah";
                Thread t1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        isUpgradeRumah = true;
                        if (!stopTimeBeliBarang) stopTimeUpgradeRumah = true;
                        durasi = (double) lamaUpgradeRumah;
                        Clock.wait(durasi);
                        isUpgradeRumah = false;
                        stopTimeUpgradeRumah = false;
                    }
                });

                Thread t2 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            if (!isAksiAktif && !stopTimeBeliBarang) {
                                Clock.stopTime(1);
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            else Clock.setStopTime(0);
                        }
                    }
                });

                t2.start();
                t1.start();
                //kurangin uangnya
                minUang(hargaUpgradeRumah);
                Ruangan newRoom = new Ruangan(namaRuangan, rumah);
                if (pilihan.equals("Atas")) {
                    ruangan.setRuanganAtas(newRoom);
                    newRoom.setRuanganBawah(ruangan);
                    newRoom.setPosisiRuangan(new Point(ruangan.getXRuangan(), ruangan.getYRuangan() + 6));
                } else if (pilihan.equals("Bawah")) {
                    ruangan.setRuanganBawah(newRoom);
                    newRoom.setRuanganAtas(ruangan);
                    newRoom.setPosisiRuangan(new Point(ruangan.getXRuangan(), ruangan.getYRuangan() - 6));
                } else if (pilihan.equals("Kiri")) {
                    ruangan.setRuanganKiri(newRoom);
                    newRoom.setRuanganKanan(ruangan);
                    newRoom.setPosisiRuangan(new Point(ruangan.getXRuangan() - 6, ruangan.getYRuangan()));
                } else if (pilihan.equals("Kanan")) {
                    ruangan.setRuanganKanan(newRoom);
                    newRoom.setRuanganKiri(ruangan);
                    newRoom.setPosisiRuangan(new Point(ruangan.getXRuangan() + 6, ruangan.getYRuangan()));
                }

                newRoom.checkSurrounding(rumah);
            } else {
                if (expandable.size() == 0) {
                    System.out.println("Space di world tidak cukup");
                } else {
                    System.out.println("Saldo tidak cukup");
                }
            }
        }
    }

    public void beliBarang(){
        //mengisi rumahnya dengan barang-barang.
        //waktu kedatangan barang tidak dapat dipastikan.
        //durasi pengiriman barang akan selalu acak tetapi tetap dalam range waktu X menit.
        if (statusBeliBarang == "Sedang Beli Barang") {
            System.out.println("Barang lain masih dalam proses pengantaran, belum bisa menggunakan aksi ini.");
        }
        else {
            ArrayList<String> furniture = new ArrayList<String>() {{
                add("Kasur Single");
                add("Kasur Queen Size");
                add("Kasur King Size");
                add("Toilet");
                add("Kompor Gas");
                add("Kompor Listrik");
                add("Meja dan Kursi");
                add("Jam");
                add("Komputer");
                add("TV");
                add("Shower");
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
            Selamat Datang di Indimaret Online!\nBerikut ini adalah barang yang bisa dibeli:\n
                +-----+-------------------------------------------------------+
                |     |                        Kategori                       |
                | No. +----------------------------+--------------------------+
                |     |      Furniture   (Harga)   |   Bahan Makanan (Harga)  |
                +-----+----------------------------+--------------------------+
                |  1. | Kasur Single     ( 50  )   | Nasi    ( 5  )           |
                |  2. | Kasur Queen Size ( 100 )   | Kentang ( 3  )           |
                |  3. | Kasur King Size  ( 150 )   | Ayam    ( 10 )           |
                |  4. | Toilet           ( 50  )   | Sapi    ( 12 )           |
                |  5. | Kompor Gas       ( 100 )   | Wortel  ( 3  )           |
                |  6. | Kompor Listrik   ( 200 )   | Bayam   ( 3  )           |
                |  7. | Meja dan Kursi   ( 50  )   | Kacang  ( 2  )           |
                |  8. | Jam              ( 10  )   | Susu    ( 2  )           |
                |  9. | Komputer         ( 250 )   |                          |
                | 10. | TV               ( 100 )   |                          |
                | 11. | Shower           ( 150 )   |                          |   
                +-----+----------------------------+--------------------------+
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

            if (barang instanceof Furniture) {
                System.out.println("Harga " + item + ": " + ((Furniture) barang).getHarga());
            }
            else if (barang instanceof BahanMakanan) {
                System.out.println("Harga : " + item + ": " + ((BahanMakanan) barang).getHarga());
            }

            System.out.println("Uang Anda saat ini : " + uang);
            if (barang instanceof Furniture) {
                if (uang < ((Furniture) barang).getHarga()) {
                    System.out.println("Uang tidak cukup!");
                    return;
                }
            }
            if (barang instanceof BahanMakanan) {
                if (uang < ((BahanMakanan) barang).getHarga()) {
                    System.out.println("Uang tidak cukup!");
                    return;
                }
            }
            System.out.print("\nKonfirmasi Pembelian (y/n): ");
            String konfirmasi = scan.nextLine();
            System.out.println();
            while (!konfirmasi.equals("y") && !konfirmasi.equals("n")) {
                System.out.println("Masukan invalid!");
                System.out.print("Konfirmasi Pembelian (y/n): ");
                konfirmasi = scan.nextLine();
                System.out.println();
            }
            
            if (konfirmasi.equals("y")) {
                lamaBeliBarang = (new Random().nextInt(4) + 1) /* between (1,5) */ * 30;
                System.out.printf("%s akan segera dikirim dalam waktu %d detik\n", item, lamaBeliBarang);
                timeBeliBarang = Clock.convertToSeconds(Clock.getTime());
                statusBeliBarang = "Sedang Beli Barang";
                Thread t1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        isBeliBarang = true;
                        if (!stopTimeUpgradeRumah) stopTimeBeliBarang = true;
                        Clock.wait((double)lamaBeliBarang);
                        isBeliBarang = false;
                        stopTimeBeliBarang = false;
                    }
                });

                Thread t2 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            if (!isAksiAktif && !stopTimeUpgradeRumah) {
                                Clock.stopTime(1);
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            else Clock.setStopTime(0);
                        }
                    }
                });

                t2.start();
                t1.start();
                // System.out.printf("%s telah diterima\n", item);

                if (barang instanceof Furniture) uang -= ((Furniture) barang).getHarga();
                else if (barang instanceof BahanMakanan) uang -= ((BahanMakanan) barang).getHarga();

                inventory.addItem(item);
            } else {
                System.out.println("Pembelian dibatalkan");
            }
        }
    }

    public void pindahRuang(Rumah rumah){
        Scanner scan = new Scanner(System.in);
        Ruangan ruangan = currentRuangan;
        ruangan.printRuanganNextTo();
        ArrayList<String> nextTo = new ArrayList<String>();
        if (ruangan.getRuanganAtas() != null) {
            nextTo.add(ruangan.getRuanganAtas().getNamaRuangan());
        } 
        if (ruangan.getRuanganBawah() != null) {
            nextTo.add(ruangan.getRuanganBawah().getNamaRuangan());
        } 
        if (ruangan.getRuanganKiri() != null) {
            nextTo.add(ruangan.getRuanganKiri().getNamaRuangan());
        } 
        if (ruangan.getRuanganKanan() != null) {
            nextTo.add(ruangan.getRuanganKanan().getNamaRuangan());
        }

        if (nextTo.size() == 0) {
            System.out.println("Rumah ini tidak memiliki ruangan lain!");
            return;
        }
        
        String input = "";
        boolean isInputValid = false;
        while (!isInputValid) {
            try {
                System.out.print("Kamu mau berpindah kemana? ");
                input = scan.nextLine();
                isInputValid = true;
            } catch (Exception e) {
                System.out.println("Masukan harus String");
                scan.next();
            }
        }

        if (nextTo.contains(input)) {
            System.out.println("Berpindah ke ruang " + input + "...");
            this.currentRuangan = rumah.getRuangan(input);
            posisiSim = new Point(3, 3);
        } else {
            System.out.println("Maaf, sepertinya kamu salah memasukkan tujuan untuk berpindah");
        }
    }

    public void lihatInventory(){
        //berisi dengan makanan, barang-barang yang sedang tidak terpasang pada ruangan, dan objek-objek lainnya.
        inventory.printItem();
    }

    public void pasangBarang(Ruangan ruangan) throws Exception {
        if (!inventory.isItemTypeInInventory("Furniture")) {
            System.out.println("Kamu belum memiliki furniture di inventory");
            return;
        }
        inventory.printSpecificItem("Furniture");
        System.out.println("Masukkan nama furniture yang ingin dipasang (contoh: Meja Makan) ");
        Scanner input = new Scanner(System.in);
        String namaFurniture = input.nextLine();

        if (!inventory.containsItem(namaFurniture)) {
            System.out.println("Tidak ada furniture dengan nama tersebut di inventory!");
            return;
        }
        

        Furniture barang = new Furniture(namaFurniture);

        ArrayList<Sim> daftarSim = new ArrayList<Sim>();
        daftarSim.add(this);
        ruangan.printRuanganWithCoordinate(this);
        System.out.println("(Posisi yang dimasukkan akan menjadi titik terkiri-bawah dari "+ namaFurniture + ") ");
    
        int x = -1;
        int y = -1;
        boolean isInputInt = false;
        while (!isInputInt) {
            try {
                System.out.print("Masukkan posisi x furniture: ");
                x = input.nextInt();
                System.out.println();
                System.out.print("Masukkan posisi y furniture: ");
                y = input.nextInt();
                System.out.println();

                while (x > 5 && y > 5 && x < 0 && y < 0) {
                    System.out.println("Posisi tidak valid!");
                    System.out.print("Masukkan posisi x furniture: ");
                    x = input.nextInt();
                    System.out.println();
                    System.out.print("Masukkan posisi y furniture: ");
                    y = input.nextInt();
                    System.out.println();
                }
                isInputInt = true;
            }
            catch (Exception e) {
                System.out.println("Masukan harus integer");
                input.next();
            }
        }

        
        System.out.print("Apakah ingin diputar? (y/n) ");
        String jawaban = input.next();

        while (!jawaban.equals("y") && !jawaban.equals("n")) {
            System.out.println("Masukan invalid!");
            System.out.print("Apakah ingin diputar? (y/n) ");
            jawaban = input.next();
        }

        if (jawaban.equals("y")) {
            barang.rotateFurniture();
        } 
        AtomicBoolean isPlaced = new AtomicBoolean(false);
        ruangan.insertObjectToRuangan(namaFurniture, new Point(x, y), isPlaced);
        if (isPlaced.get()) {
            System.out.println("Furniture berhasil dipasang!");
            barang.setXFurniture(x);
            barang.setYFurniture(y);
            inventory.decreaseItem(namaFurniture, 1);
        } else {
            System.out.println("Furniture gagal dipasang!");
        }
       
    }

    public void lihatWaktu() {
        Clock.getTime();
        System.out.println();
        System.out.println(" WAKTU SIMPLICITY");
        System.out.println("------------------");
        System.out.println("Hari ke-" + Clock.getDay() + "\n");
        System.out.print("Sisa waktu hari ini: ");
        Clock.printTime(Clock.dayRemaining());
        System.out.println("\n");
        if (isBeliBarang) {
            System.out.print("Sisa waktu beli barang: ");
            System.out.println();
            Clock.printTime(Clock.minusTime(Clock.diffTime(Clock.convertToLocalTime(timeBeliBarang)), Clock.convertToLocalTime(lamaBeliBarang)));
            System.out.println("\n");
        }
        if (isUpgradeRumah) {
            System.out.print("Sisa waktu upgrade rumah: ");
            System.out.println();
            Clock.printTime(Clock.minusTime(Clock.diffTime(Clock.convertToLocalTime(timeUpgradeRumah)), Clock.convertToLocalTime(lamaUpgradeRumah)));
            System.out.println("\n");
        }
        if (!isBeliBarang && !isUpgradeRumah) System.out.println("Tidak sedang melakukan upgrade rumah maupun beli barang\n");
    }

    public void moveTo(Point point) {
        Point copiedPoint = posisiSim.clone();
        copiedPoint.setX(point.getX());
        copiedPoint.setY(point.getY());
        posisiSim = copiedPoint;  
    }

    public void moveToFurniture(Ruangan ruangan, World world) throws Exception {
        ruangan.printRuangan(this, world);
        System.out.println("Berikut ini adalah daftar furniture yang ada di ruangan: ");
        ruangan.printDaftarFurniture();
        System.out.print("Masukkan nama furniture yang ingin dituju: ");
        Scanner input = new Scanner(System.in);
        String namaFurniture = input.nextLine();

        if (!ruangan.isFurnitureInRuangan(namaFurniture)) {
            System.out.println("Furniture tidak ada di ruangan!");
        } else {
            int count = 0;
            for (Furniture furniture : ruangan.getDaftarFurniture()) {
                if (furniture.getNama().equals(namaFurniture)) {
                    count++;
                }
            }
            if (count > 1) {
                System.out.println("Terdapat lebih dari satu " + namaFurniture + " di dalam " + ruangan.getNamaRuangan());
                int i = 1;
                for (Furniture furniture : ruangan.getDaftarFurniture()) {
                    if (furniture.getNama().equals(namaFurniture)) {
                        System.out.println(i + ". (" + furniture.getXFurniture() + ", " + furniture.getYFurniture() + ")");
                        i++;
                    }
                }
                int pilihan = -1;
                boolean isInputInt = false;
                while (!isInputInt) {
                    try {
                        pilihan = input.nextInt();
                        while (pilihan < 1 || pilihan > count) {
                            System.out.println("Pilihan tidak valid!");
                            System.out.print("Pilih salah satu " + namaFurniture + " yang ingin dituju (Masukkan angka): ");
                            pilihan = input.nextInt();
                        }
                        isInputInt = true;
                    }
                    catch (Exception e) {
                        System.out.println("Masukan harus integer");
                        input.next();
                    }
                }
                int j = 1;
                for (Furniture furniture : ruangan.getDaftarFurniture()) {
                    if (furniture.getNama().equals(namaFurniture)) {
                        if (j == pilihan) {
                            System.out.println("Bergerak menuju " + namaFurniture);
                            int x = furniture.getXFurniture();
                            int y = furniture.getYFurniture();
                            posisiSim.setX(x);
                            posisiSim.setY(y);
                        }
                        j++;
                    }
                }
            } else {
                Furniture furniture = ruangan.getFurniture(namaFurniture);
                System.out.println("Bergerak menuju " + namaFurniture);
                int a = furniture.getXFurniture();
                int b = furniture.getYFurniture();
                Point copiedPoint = posisiSim.clone();
                copiedPoint.setX(a);
                copiedPoint.setY(b);
                posisiSim = copiedPoint;       
            }
        }
    }

    public void checkFurniture(Ruangan ruangan) {
        List<Furniture> daftarFurniture = ruangan.getDaftarFurniture();
        if (daftarAksi.size() > 10) {
            int size = daftarAksi.size();
            for (int i = 10; i < size; i++) {
                daftarAksi.remove(10);
            }
        }

        for (Furniture furniture : daftarFurniture) {
            if (furniture.isNearSim(posisiSim)) {
                for (String aksi : furniture.getAksi()) {
                    if (aksi != null) {
                        daftarAksi.add(aksi);
                    }
                }
            }
        }

        if (isOutside) {
            daftarAksi.add("Pulang");
        }
    }

    public void printCurrentSimRoom(World world) {
        getCurrentRuangan().printRuangan(this, world);
        Map <Rumah, Sim> daftarRumah = world.getDaftarRumah();
        for (Map.Entry<Rumah, Sim> entry : daftarRumah.entrySet()) {
            Rumah rumah = entry.getKey();
            ArrayList<Ruangan> daftarRuangan = rumah.getDaftarRuangan();
            for (Ruangan ruangan : daftarRuangan) {
                //ruangan.printRuangan(world.getDaftarSim());
                //ruangan.printRuangan(this);
                if (getCurrentRuangan().getIDRuangan().equals(ruangan.getIDRuangan())) {
                    System.out.println("\nRuang " + ruangan.getNamaRuangan() + " (Rumah " + entry.getValue().getNama() + ")");
                    break;
                }
            }
        }
    }

    public void printSimAttribute() {
        System.out.println();
        System.out.println("+------------------+----------+----------+----------------+----------------+");
        System.out.println("| Pekerjaan        | Uang     | Mood     | Kesehatan      | Kekenyangan    |");
        System.out.println("+------------------+----------+----------+----------------+----------------+");
        System.out.printf("| %-16s | %-8d | %-8d | %-14d | %-14d |\n", pekerjaan, uang, mood, kesehatan, kekenyangan);
        System.out.println("+------------------+----------+----------+----------------+----------------+");
        System.out.println();
    }

    public void printDaftarAksi() {

        System.out.println();
        System.out.println("+------------------------+");
        for (String aksi : daftarAksi) {
            System.out.printf("| %-22s |\n", aksi);
            System.out.println("+------------------------+");
        }
        System.out.println();
    }

    //harus buat 7 aksi lain yang dapat berhubungan dengan objek sesuai dengan kreasi masing-masing.
    // pake objek komputer (3x3, harga 250)
    public void mainGame() {
        // mood bertambah 15 dan kesehatan berkurang 5 setiap 30 detik
        System.out.println("Daftar rekomendasi game:");
        System.out.println("1. Taken 7 "); // 30 detik
        System.out.println("2. Faloran "); // 60 detik
        System.out.println("3. EmEl "); // 90 detik
        System.out.print("Pilih game yang ingin dimainkan (Masukkan angka): ");
        Scanner input = new Scanner(System.in);
        int pilihan = input.nextInt();
        
        if (pilihan < 1 || pilihan > 3) {
            System.out.println("Pilihan tidak valid");
            return;
        }

        System.out.print("Bermain game");
        if (pilihan == 1) {
            System.out.println(" 'Taken 7'... ");
            durasi = 30.00;
        } else if (pilihan == 2) {
            System.out.println(" 'Faloran'... ");
            durasi = 60.00;
        } else if (pilihan == 3) {
            System.out.println(" 'EmEl'... ");
            durasi = 90.00;
        }

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

        if (pilihan == 1) {
            addMood(15);
            addKesehatan(-5);
        } else if (pilihan == 2) {
            addMood(30);
            addKekenyangan(-10);
        } else if (pilihan == 3) {
            addMood(45);
            addKesehatan(-15);
        }

        isThreadFinished = false;
    }

    public void mandi() {
        // kesehatan bertambah 10 dan mood bertambah 10 setiap 1 siklus (20 detik)

        durasi = 20.00;
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

        addKesehatan(10);
        addMood(10);

        isThreadFinished = false;
    }

    public void rebahan() {
        // mood bertambah 5 dan kesehatan bertambah 5 setiap 10 detik
        System.out.println("Durasi rebahan harus kelipatan 10");
        System.out.print("Masukkan durasi belajar coding: ");
        Scanner sc = new Scanner(System.in);
        int lamaRebahan = sc.nextInt();

        if (lamaRebahan % 10 != 0) {
            System.out.println("Durasi rebahan harus kelipatan 10!");
            return;
        }

        durasi = (double) lamaRebahan;
   
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

        addMood((lamaRebahan / 10) * 5);
        addKesehatan((lamaRebahan / 10) * 5);

        isThreadFinished = false;
    }

    // pake objek komputer
    public void belajarCoding() {
        // mood bertambah 10 setiap 30 detik
        // kesehatan berkurang 10 setiap 30 detik

        System.out.println("Durasi belajar coding harus kelipatan 30");
        System.out.print("Masukkan durasi belajar coding: ");
        Scanner sc = new Scanner(System.in);
        int lamaBelajarCoding = sc.nextInt();

        if (lamaBelajarCoding % 30 != 0) {
            System.out.println("Durasi belajar coding harus kelipatan 30!");
            return;
        }

        durasi = (double) lamaBelajarCoding;

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

        addKesehatan((lamaBelajarCoding / 30) * 10);
        addMood((lamaBelajarCoding / 30) * 10);

        isThreadFinished = false;
    } 

    // pake objek komputer
    public void bukaSosmed() {
        // mood bertambah 20 setiap 30 detik
        // kesehatan berkurang 5 setiap 30 detik

        System.out.println("Durasi buka sosmed harus kelipatan 20");
        System.out.print("Masukkan durasi buka sosmed: ");
        Scanner sc = new Scanner(System.in);
        int lamaBukaSosmed = sc.nextInt();

        if (lamaBukaSosmed % 20 != 0) {
            System.out.println("Durasi buka sosmed harus kelipatan 20!");
            return;
        }

        durasi = (double) lamaBukaSosmed;
   
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

        addKesehatan((lamaBukaSosmed / 30) * 5);
        addMood((lamaBukaSosmed / 30) * 20);

        isThreadFinished = false;
    }

    // pake objek komputer atau TV (3x2, harga 200)
    public void nontonNetflix() {
        // mood bertambah 15 setiap 30 detik
        // kasih daftar rekomendasi film, durasinya 30/60/90 detik ajaa
        // (asumsi nonton di HP jadi ga perlu objek TV)

        // if (lamaNonton % 15 != 0) {
        //     System.out.println("Durasi nonton Netflix harus kelipatan 15");
        //     return;
        // }

        System.out.println("Daftar rekomendasi film:");
        System.out.println("1. Ada Apa Dengan Tubes? "); // 30 detik
        System.out.println("2. Cek Kelompok Sebelah "); // 60 detik
        System.out.println("3. Pengabdi Tubes 3 "); // 90 detik
        System.out.print("Pilih film yang ingin ditonton (Masukkan angka): ");
        Scanner input = new Scanner(System.in);
        int pilihan = input.nextInt();
        
        if (pilihan < 1 || pilihan > 3) {
            System.out.println("Pilihan tidak valid");
            return;
        }

        System.out.print("Menonton film");
        if (pilihan == 1) {
            System.out.println(" 'Ada Apa Dengan Tubes?'... ");
            durasi = 30.00;
        } else if (pilihan == 2) {
            System.out.println(" 'Cek Kelompok Sebelah'... ");
            durasi = 60.00;
        } else if (pilihan == 3) {
            System.out.println(" 'Pengabdi Tubes 3'... ");
            durasi = 90.00;
        }

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

        if (pilihan == 1) {
            addMood(15);
        } else if (pilihan == 2) {
            addMood(30);
        } else if (pilihan == 3) {
            addMood(45);
        }

        isThreadFinished = false;
    }

    public void ikutUndianBerhadiah() {
        durasi = 3.00;
        Random rand = new Random();

        System.out.println("Selamat Datang di Undian Berhadiah!");
        System.out.println("Tekan tombol apapun untuk memulai undian!");
        Scanner input = new Scanner(System.in);
        input.nextLine();
        System.out.println("Menguji peruntungan Anda...");

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Clock.wait(durasi);
                isThreadFinished = true;
            }
        });

        t1.start();

        int random = rand.nextInt(4);
        try {
            t1.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (random == 0) {
            System.out.println("Selamat! Anda mendapatkan 5 rupiah!");
            uang += 5;
            setUang(uang);
        } else if (random == 1) {
            System.out.println("Selamat! Anda mendapatkan 10 rupiah!");
            uang += 10;
            setUang(uang);
        } else if (random == 2) {
            System.out.println("Selamat! Anda mendapatkan 15 rupiah1");
            uang += 15;
            setUang(uang);
        } else {
            System.out.println("ZONK!!! Nice Try:(");
            setUang(uang);
        }

        isThreadFinished = false;
    }

    public void efekTidakTidur() {
        addKesehatan(-5);
        addMood(-5);
    }

    public void efekTidakBuangAir() {
        addKesehatan(-5);
        addMood(-5);
    }

    public void checkSudahTidur() {
        if (isSudahTidur) {
            timeTidur = Clock.convertToSeconds(Clock.getTime());
            isSudahTidur = false;
            jumlahTidakTidur = 0;
        }
        else {
            if (Clock.getDiffTimeInSeconds(timeTidur) >= (jumlahTidakTidur+1)*10*60) {
                efekTidakTidur();
                jumlahTidakTidur++;
            }
        }
    }

    public void checkSudahBuangAir() {
        if (isSudahBuangAir) {
            timeTidur = Clock.convertToSeconds(Clock.getTime());
            isSudahBuangAir = false;
            jumlahTidakBuangAir = 0;
        }
        else {
            if (!isSudahMakan) {
                if (Clock.getDiffTimeInSeconds(timeBuangAir) >= (jumlahTidakBuangAir+1)*4*60) efekTidakBuangAir();
            }
        }
    }
}