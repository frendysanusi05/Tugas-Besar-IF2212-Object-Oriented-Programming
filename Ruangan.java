

public class Ruangan extends Rumah{
    /* Atribut */
    private String IDRuangan;
    private String namaRuangan;
    private Point titikRuangan = new Point();

    /* Konstruktor */
    public Ruangan(Point titikRuangan, String namaRuangan, String IDRumah) {
        this.namaRuangan = namaRuangan;
        IDRuangan = IDRumah + "_" + namaRuangan.replace(" ", "_");
        this.titikRuangan = titikRuangan;
    }

    /* Metode */
    public String getIDRuangan() {
        return IDRuangan;
    }

    public Point getTitikRuangan() {
        return titikRuangan;
    }

    public String getNamaRuangan() {
        return namaRuangan;
    }
    
}
