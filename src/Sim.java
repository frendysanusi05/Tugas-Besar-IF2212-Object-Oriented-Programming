import java.util.Random;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.time.LocalTime;

//mmaaf bagian perkerjaan sama kerja gue bingung

public class Sim {
    private String nama;
    private String pekerjaan;
    private int uang;
    private Map<String, Integer> inventory; //MASIH BINGUNG GIMANA INVENTORY
    private int kekenyangan;
    private int mood;
    private int kesehatan;
    private String status;

    private volatile int durasi;

    private static final String[] PEKERJAAN = {"Badut Sulap", "Koki", "Polisi", "Programmer", "Dokter"};
    
    /* Scanner */
    Scanner scan = new Scanner(System.in);

    public Sim(String nama) {
        this.nama = nama;
        this.uang = 100;
        this.kekenyangan = 80;
        this.mood = 80;
        this.kesehatan = 80;
        this.inventory = new HashMap<String, Integer>();
        this.pekerjaan = PEKERJAAN[new Random().nextInt(PEKERJAAN.length)]; // bingung cara biar random, ini cara chatgpt
        this.status = "Tidak sedang melakukan apa-apa"; //gimana kosonginnya?
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

    public Map<String, Integer>  getInventory() {
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

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;//belum mikirin kalau dia masukkin belumsesuai yang ada di list
    }

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

    public void makan() {
        if (kekenyangan < 100) {
            kekenyangan += 10;
            if (kekenyangan > 100) {
                kekenyangan = 100;
            }
            mood += 5;
            if (mood > 100) {
                mood = 100;
            }
            uang -= 5;
            status = "Sedang makan";
        } else {
            status = "Sudah kenyang";
        }
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
            kesehatan += 20;
            mood += 30;
        }else{
            kesehatan -= 5;
            mood -= 5;
        }
    }

    public void makan(Edible makanan) {
        //cara ambil makanan di Inventory gimana?
        //ngurangin makanan di inventory 
        addKekenyangan(makanan.getKekenyangan());   
    }    

    public void memasak() {
        //bingung pakai banget
    }   

    public void berkunjung(){
        //semangat deh ngitung waktunya makai koordinat rumah
        // rumusnya sqrt(((x2-x1)^2) + ((y2-y1)^2))
        mood += 10;
        kekenyangan -= 10;
    }   

    public void buangAir(){
    //kayak tidur bingungnya
    //buang air setidaknya 1 kali setiap habis makan. Apabila tidak dilakukan, maka mood dan kesehatan Sim akan berkurang.

    boolean sudahBA = false;

        if(sudahBA){
            kekenyangan -= 20;
            mood += 10;
        }else{
            kesehatan -= 5;
            mood -= 5;
        }
    }

    public void upgradeRumah(){
        //menambah ruangan
        //membutuhkan waktu sejumlah 18 menit
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

    //harus buat 7 aksi lain yang dapat berhubungan dengan objek sesuai dengan kreasi masing-masing.
}
