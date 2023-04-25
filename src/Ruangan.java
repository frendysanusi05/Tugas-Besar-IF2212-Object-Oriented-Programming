import java.util.*;

public class Ruangan extends Rumah{
    private String IDRuangan;
    private String namaRuangan;
    private Point titikRuangan;
    private ArrayList<Furniture> daftarObjek;
    private int capacity;

    //constructor
    public Ruangan(String namaRuangan, Rumah rumah, Point titikRuangan){
        super();
        IDRuangan = rumah.getIDRumah() + "_" + rumah.getJumlahRuangan();
        this.namaRuangan = namaRuangan;
        this.titikRuangan = titikRuangan;
        this.capacity = 36;
    }

    //getter
    public String getIDRuangan(){
        return this.IDRuangan;
    }

    public int getXRuangan(){
        return titikRuangan.getX();
    }

    public int getYRuangan(){
        return titikRuangan.getY();
    }

    public ArrayList<Furniture> getDaftarObjek(){
        return this.daftarObjek;
    }

    public int getRemainingCapacity(){
        return this.capacity;
    }

    public String getNamaRuangan(){
        return this.namaRuangan;
    }

    //method
    public boolean isAvailable(Furniture barang){
        return (barang.getLebar() * barang.getPanjang() <= capacity);
    }

    //pake arraylist (dalemnya furniture) -> identifikasinya di ruangan
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
