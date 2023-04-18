
import java.util.List;
import java.util.ArrayList;

public class Rumah extends World {
    private String IDRumah;
    private Point titikRumah = new Point();
    private List<Ruangan> daftarRuangan = new ArrayList<Ruangan>();
    private static int jumlahRumah = 0;

    /* Konstruktor */
    public Rumah(Point titikRumah) {
        jumlahRumah++;
        IDRumah = "R" + jumlahRumah;
        this.titikRumah = titikRumah; 
        addDaftarRuangan(new Ruangan(titikRumah, "Kamar", IDRumah));
    }

    public Rumah() {
    }

    /* Metode */
    public String getIDRumah() {
        return IDRumah;
    }

    public Point getTitikRumah() {
        return titikRumah;
    }

    public List<Ruangan> getDaftarRuangan() {
        return daftarRuangan;
    }

    public void printDaftarRuangan() {
        for (Ruangan ruang : daftarRuangan) {
            System.out.println("IDRuangan: " + ruang.getIDRuangan() + ", Nama Ruangan: " + ruang.getNamaRuangan());
        }
    }

    public void addDaftarRuangan(Ruangan ruang) {
        daftarRuangan.add(ruang);
    }
}
