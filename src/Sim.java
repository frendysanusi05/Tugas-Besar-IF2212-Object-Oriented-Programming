import java.util.Random;
import java.util.Scanner;
import java.time.LocalTime;

//mmaaf bagian perkerjaan sama kerja gue bingung

public class Sim {
    private String nama;
    private String pekerjaan;
    private int uang;
    private Inventory inventory; //MASIH BINGUNG GIMANA INVENTORY
    private int kekenyangan;
    private int mood;
    private int kesehatan;
    private String status;

    private volatile int durasi;

    private static final String[] listPekerjaan = {"Badut Sulap", "Koki", "Polisi", "Programmer", "Dokter"};
    
    /* Scanner */
    Scanner scan = new Scanner(System.in);

    public Sim(String nama) {
        this.nama = nama;
        this.uang = 100;
        this.kekenyangan = 80;
        this.mood = 80;
        this.kesehatan = 80;
        this.inventory = new Inventory();
        this.pekerjaan = listPekerjaan[new Random().nextInt(listPekerjaan.length)];
        this.status = null;
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

    public void setStatus(String aksi) {
        status = aksi;
    }

    //public void setPekerjaan(String pekerjaan) { this.pekerjaan = pekerjaan;//belum mikirin kalau dia masukkin belumsesuai yang ada di list}

    public void addUang(int uangTambahan) {
        uang += uangTambahan;
        if (uang > 100){
            uang = 100;
        }
    }

    public void addKekenyangan(int kekenyanganTambahan) {
        kekenyangan += kekenyanganTambahan;
        if (kekenyangan > 100){
            kekenyangan = 100;
        }
    }

    public void addMood(int moodTambahan) {
        mood += moodTambahan;
        if (mood > 100){
            mood = 100;
        }
    }

    public void addKesehatan(int kesehatanTambahan) {
        this.kesehatan += kesehatanTambahan;
    }

    public void kerja() {
        System.out.print("Masukkan durasi kerja: ");
        durasi = scan.nextInt();
        while (durasi % 120 != 0) {
            System.out.println("Durasi kerja harus merupakan kelipatan 120");
            durasi = scan.nextInt();
        }
        System.out.println();

        
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Clock.wait(durasi);
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int timeInSeconds = LocalTime.now().toSecondOfDay();
                int duration = 30;
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
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                int timeInSeconds = LocalTime.now().toSecondOfDay();
                int duration = 4 * 60;


                while (!isThreadFinished) {
                
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
            
        });

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t2.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void olahraga() {
        System.out.print("Masukkan durasi olahraga: ");
        durasi = scan.nextInt();
        while (durasi % 20 != 0) {
            System.out.println("Durasi olahraga harus merupakan kelipatan 20");
            durasi = scan.nextInt();
        }
        System.out.println();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Clock.wait(durasi);
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int timeInSeconds = LocalTime.now().toSecondOfDay();
                int duration = 20;
                
                    if (Clock.isEqualDuration(timeInSeconds, duration)) {
                        kesehatan += 5;
                        kekenyangan -= 5;
                        mood += 10;

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
    }

    public void tidur() {
    // bingung cek haru tidur 3 menit dalam 24 jam
    //gatau ini bingung banget

        boolean sudahTidur = false;

        if(sudahTidur){
            addKesehatan(20);
            addMood(30);
        }else{
            kesehatan -= 5;
            mood -= 5;
        }
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
            durasi = 30;
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

            try {
                t1.join();
                t2.join();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
                setStatus(null);
                setStatus(null);
                addMood(10);

                // Add the masakan to the inventory
                inventory.addItem(masakanBaru.getName());
            setStatus(null);
                addMood(10);

                // Add the masakan to the inventory
                inventory.addItem(masakanBaru.getName());
        } else {
            System.out.println("Makanan tidak ditemukan");
        }
    }    

    public void memasak() {
        //bingung pakai banget
    }   

    public void berkunjung(){
        //semangat deh ngitung waktunya makai koordinat rumah
        // rumusnya sqrt(((x2-x1)^2) + ((y2-y1)^2))
        addMood(10);
        kekenyangan -= 10;
    }   

    public void buangAir(){
    //kayak tidur bingungnya
    //buang air setidaknya 1 kali setiap habis makan. Apabila tidak dilakukan, maka mood dan kesehatan Sim akan berkurang.

    boolean sudahBA = false;

        if(sudahBA){
            kekenyangan -= 20;
            addMood(10);
        }else{
            kesehatan -= 5;
            mood -= 5;
        }
    }

    public void upgradeRumah(){
        //menambah ruangan
        //membutuhkan waktu sejumlah 18 menit
        int waktuUpgradeRumah = 18;
        int harga = 1500;
        int saldo = getUang();

        if (saldo >= harga) {
            saldo -= harga;
            getJumlahRuangan()++;
            System.out.println("Rumah berhasil di-upgrade! Jumlah ruangan sekarang: " + jumlahRuangan);
            try {
                Thread.sleep(waktuUpgradeRumah * 60 * 1000); // konversi waktu dari menit ke milidetik
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Uang tidak cukup untuk upgrade rumah!");
        }
    }




    public void beliBarang(){
        //mengisi rumahnya dengan barang-barang.
        //waktu kedatangan barang tidak dapat dipastikan.
        //durasi pengiriman barang akan selalu acak tetapi tetap dalam range waktu X menit.

    }

    public void pindahRuang(){
        
    }

    public void lihatInventory(){
        //berisi dengan makanan, barang-barang yang sedang tidak terpasang pada ruangan, dan objek-objek lainnya.
    }

    public void addInventory(){
        //gatau ada di sheet spek tapi gaada di doc spesifikasi
    }

    public void pasangBarang(){
        //Barang yang akan dipasang harus muat dalam ruanga
    }

    public void lihatWaktu(){
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
