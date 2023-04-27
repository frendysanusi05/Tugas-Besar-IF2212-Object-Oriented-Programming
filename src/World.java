import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class World {
    /* Atribut */
    private Map<Rumah, Sim> daftarRumah = new HashMap<Rumah, Sim>();
    private ArrayList<Sim> daftarSim = new ArrayList<Sim>();
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
        for (Map.Entry<Rumah, Sim> entry : daftarRumah.entrySet()) {
            System.out.println("IDRumah: " + entry.getKey().getIDRumah() + ", Pemilik: " + entry.getValue().getNama());
        }
    }

    public void addRumah(Rumah rumahSim, Sim sim) {
        daftarRumah.put(rumahSim, sim);
    }

    public void addCekPosisi(Rumah rumah) {
        for (Ruangan ruang : rumah.getDaftarRuangan()) {
            for (int i = ruang.getXRuangan() - 3; i < ruang.getXRuangan() + 3; i++) {
                for (int j = ruang.getYRuangan() - 3; j < ruang.getYRuangan() + 3; j++) {
                    cekPosisi[i][j] = true;
                }
            }
        }
    }

    public void addSim(Sim sim) {
        daftarSim.add(sim);
    }

    public Map<Rumah, Sim> getDaftarRumah() {
        return daftarRumah;
    }

    public List<Sim> getDaftarSim() {
        return daftarSim;
    }

    public void printWorld () {
        for (int j = 0; j < 64; j++) {
            for (int i = 0; i < 64; i++) {
                if (cekPosisi[i][j]) {
                    System.out.print("o");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }
}