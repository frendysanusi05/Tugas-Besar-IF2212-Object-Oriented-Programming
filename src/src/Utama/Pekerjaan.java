package src.Utama;

public class Pekerjaan {
    private String nama;
    private int gaji = 0;

    /* Constructor */
    public Pekerjaan(String nama) {
        this.nama = nama;
        switch(nama) {
            case "Badut Sulap":
                this.gaji = 15;
                break;
            case "Koki":
                this.gaji = 30;
                break;
            case "Polisi":
                this.gaji = 35;
                break;
            case "Programmer":
                this.gaji = 45;
                break;
            case "Dokter":
                this.gaji = 50;
                break;
        }
    }

    /* Getter */
    public String getNama() {
        return nama;
    }

    public int getGaji() {
        return gaji;
    }
}
