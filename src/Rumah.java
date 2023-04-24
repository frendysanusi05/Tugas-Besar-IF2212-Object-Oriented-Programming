import java.util.*;

public class Rumah {
    private String IDRumah;
    private Point posisi;
    private Ruangan[] daftarRuangan;
    private Ruangan R1;
    public static int jumlahRuangan;


    //constructor
    public Rumah(String IDRumah, Point posisi){
        this.IDRumah = IDRumah;
        this.posisi = posisi;

        Ruangan R1 = new Ruangan("IniPerluDiGenerateDlu" /*Perlu digenerate dlu gasi?*/, "Awal", IDRumah, posisi); //ini IDRuangannya mau digimanain? buat yg bagian awal. 

        //inisiasi Ruangan pada Rumah
        tambahRuangan(R1);
        jumlahRuangan = 1;

        //inisiasi furniture apa saja yg perlu ada di dalam Rumah (Ruangan 1)
        Random rand = new Random();
        List<String> givenList = new ArrayList<String>();
        givenList.add("Kasur Single");
        givenList.add("Kasur Queen Size");
        givenList.add("Kasur King Size");
        String randomElmt = givenList.get(rand.nextInt(givenList.size()));
        R1.addObject(randomElmt);
        R1.addObject("Toilet");
        
        List<String> givenList2 = new ArrayList<String>();
        givenList2.add("Kompor Gas");
        givenList2.add("Kompor Listrik");
        randomElmt = givenList2.get(rand.nextInt(givenList2.size()));
        R1.addObject(randomElmt);
        R1.addObject("Meja dan Kursi");
        R1.addObject("Jam");
    }

    //methods
    public void printDaftarRuangan(){
        for (Ruangan ruangan : daftarRuangan){
            //ini sbenernya gua masi kurang ngerti, konstruktor ruangan mau dibentuk kek gmn wkwk
            System.out.println("IDRuangan: " + ruangan.getIDRuangan() + ", Nama Ruangan: " + ruangan.getNamaRuangan());
        }
    }

    //ini blom dikonekin sama #cek ruang kosong yg ada di class ruangan dan #cekposisi yg ada di world
    public void tambahRuangan(Ruangan newRoom){
        this.daftarRuangan[daftarRuangan.length] = newRoom;
        jumlahRuangan ++;
    }

    //getter
    public String getIDRumah(){
        return this.IDRumah;
    }

    public int getXRumah(){
        return posisi.getX();
    }

    public int getYRumah(){
        return posisi.getY();
    }

    public Ruangan[] getDaftarRuangan(){
        return daftarRuangan;
    }

    public int getJumlahRuangan(){
        return jumlahRuangan;
    }

    public Ruangan getFirstRuangan(){
        return R1;
    }

}
