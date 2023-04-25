import java.util.HashMap;
import java.util.Map;

public class World {
    /* Atribut */
    private Map<String, String> daftarRumah = new HashMap<String, String>();
    private boolean[][] cekPosisi = new boolean[64][64];

    /* Konstruktor */
    public World() {
        /* Inisiasi nilai di dalam cekPosisi dengan false */
        for (int i = 0; i < 64; i++) {
            for (int j = 0; j < 64; j++) {
                cekPosisi[i][j] = false;
            }
        }
    }

    /* Metode */
    public void printDaftarRumah() {
        for (Map.Entry<String, String> entry : daftarRumah.entrySet()) {
            System.out.println("IDRumah: " + entry.getKey() + ", Pemilik: " + entry.getValue());
        }
    }

    public void addRumah(Rumah rumahSim, String namaSim) {
        daftarRumah.put(rumahSim.getIDRumah(), namaSim);
    }

    public void addCekPosisi(Rumah rumah) {
        for (Ruangan ruang : rumah.getDaftarRuangan()) {
            for (int i = ruang.getTitikRuangan().getX() - 3; i < ruang.getTitikRuangan().getX() + 3; i++) {
                for (int j = ruang.getTitikRuangan().getY() - 3; j < ruang.getTitikRuangan().getY() + 3; j++) {
                    cekPosisi[i][j] = true;
                }
            }
        }
    }

    public void printWorld () {
        for (int j = 0; j < 64; j++) {
            for (int i = 0; i < 64; i++) {
                if (cekPosisi[i][j]) {
                    System.out.print("X");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }
}