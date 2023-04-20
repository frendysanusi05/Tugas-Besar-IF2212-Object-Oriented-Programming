import java.util.*;

public class Ruangan extends Rumah{
    private String IDRuangan;
    private String namaRuangan;
    private Point titikRuangan;
    private Furniture[] daftarObjek;
    private int capacity;
    private Map<String, Point> sebelah = new HashMap<String, Point>();

    //constructor
    public Ruangan(String IDRuangan, String namaRuangan, String IDRumah, Point Posisi){
        super(IDRumah, Posisi);
        this.IDRuangan = IDRuangan;
        this.namaRuangan = namaRuangan;
        this.capacity = 36;
        jumlahRuangan ++;


        sebelah.put("Depan", new Point(super.getXRumah() + 6, super.getYRumah()));
        sebelah.put("Belakang", new Point(super.getXRumah() - 6, super.getYRumah()));
        sebelah.put("Kanan", new Point(super.getXRumah(), super.getYRumah() + 6));
        sebelah.put("Kiri", new Point(super.getXRumah(), super.getYRumah() - 6));
    }

    //getter
    public String getIDRuangan(){
        return this.IDRuangan;
    }

    public Point getTitikRuangan(){
        return titikRuangan;
    }

    public Furniture[] getDaftarObjek(){
        return this.daftarObjek;
    }

    public int getCapacity(){
        return this.capacity;
    }

    public String getNamaRuangan(){
        return this.namaRuangan;
    }

    public Point getDepan(){
        return sebelah.get("Depan");
    }

    public Point getBelakang(){
        return sebelah.get("Belakang");
    }

    public Point getKanan(){
        return sebelah.get("Kanan");
    }

    public Point getKiri(){
        return sebelah.get("Kiri");
    }

    //method
    public boolean isAvailable(Furniture barang){
        return (barang.getLebar() * barang.getPanjang() <= capacity);
    }

    public void addObject(String namaBarang){
        try {
            Furniture barang = new Furniture(namaBarang);
            daftarObjek[daftarObjek.length] = barang;
            capacity -= barang.getPanjang() * barang.getLebar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
