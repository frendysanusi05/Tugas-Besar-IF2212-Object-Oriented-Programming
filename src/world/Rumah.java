package world;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import obj.Inventory;
import utilz.Point;

public class Rumah {
    private String IDRumah;
    private Point posisi;
    private List<Ruangan> daftarRuangan = new ArrayList<Ruangan>();
    //private Ruangan R1;
    private int jumlahRuangan;
    private static int jumlahRumah = 0;


    //constructor
    public Rumah(Point posisi) throws Exception {
        jumlahRumah++;
        IDRumah = "R" + jumlahRumah;
        this.posisi = posisi;

        //inisiasi Ruangan pada Rumah
        jumlahRuangan++;
        Ruangan kamar = new Ruangan("Kamar", this); 
        kamar.setPosisiRuangan(new Point(0, 0));

        // Diset (0,4)
        kamar.insertObjectToRuangan("Kasur Single", new Point (0,4), new Inventory());

        // Diset (5,5)
        kamar.insertObjectToRuangan("Toilet", new Point (5, 5), new Inventory());

        // Diset (4,0)
        kamar.insertObjectToRuangan("Kompor Gas", new Point (4, 0), new Inventory());

        // Diset (0,0)
        kamar.insertObjectToRuangan("Meja dan Kursi", new Point (0, 0), new Inventory());

        // Diset (5,3)
        kamar.insertObjectToRuangan("Jam", new Point (5, 3), new Inventory());
    }

    public Rumah() {}

    //methods
    public void printDaftarRuangan(){
        int i = 1;
        for (Ruangan ruangan : daftarRuangan){
            //ini sbenernya gua masi kurang ngerti, konstruktor ruangan mau dibentuk kek gmn wkwk
            System.out.println(i + ") " + ruangan.getNamaRuangan());
            i++;
        }
    }

    //ini blom dikonekin sama #cek ruang kosong yg ada di class ruangan dan #cekposisi yg ada di world
    public void tambahRuangan(Ruangan newRoom){
        jumlahRuangan++;
        daftarRuangan.add(newRoom);
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

    public List<Ruangan> getDaftarRuangan(){
        return daftarRuangan;
    }

    public Ruangan getRuangan(String namaRuangan){
        for (Ruangan ruangan : daftarRuangan) {
            if (ruangan.getNamaRuangan().equals(namaRuangan)){
                return ruangan;
            }
        }
        return null;
    }

    public Ruangan getRuanganBasedOnID (String IDRuangan) {
        for (Ruangan ruangan : daftarRuangan) {
            if (ruangan.getIDRuangan().equals(IDRuangan)) {
                return ruangan;
            }
        }
        return null;
    }
    public int getJumlahRuangan(){
        return jumlahRuangan;
    }

    public int getJumlahRumah(){
        return jumlahRumah;
    }

    
}