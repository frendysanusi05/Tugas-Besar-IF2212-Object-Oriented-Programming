package world;
import java.util.*;

import sim.Sim;

public class World {
    /*** Atribut ***/
    private Map<Rumah, Sim> daftarRumah = new HashMap<Rumah, Sim>();
    private List<Sim> daftarSim = new ArrayList<Sim>();
    private boolean[][] isAvailable = new boolean[64][64];
    private static World instance = new World();

    /*** Constructor ***/
    private World() {
        /* Inisiasi nilai di dalam cekPosisi dengan false */
        for (int i = 0; i < 64; i++) {
            for (int j = 0; j < 64; j++) {
                isAvailable[i][j] = true;
            }
        }
    }

    public static World getInstance() {
        return instance;
    }

    public Sim getSim(String namaSim) {
        for (Sim sim : daftarSim) {
            if (sim.getNama().equals(namaSim)) {
                return sim;
            }
        }
        return null;
    }

    public Map<Rumah, Sim> getDaftarRumah() {
        return daftarRumah;
    }

    public List<Sim> getDaftarSim() {
        return daftarSim;
    }

    public Rumah getRumahSim(Sim sim) {
        for (Map.Entry<Rumah, Sim> entry : daftarRumah.entrySet()) {
            if (entry.getValue().getNama().equals(sim.getNama())) {
                return entry.getKey();
            }
        }
        return null;
    }

    public Rumah getCurrentRumah(Sim sim) {
        // Locate the house based on the location of the sim
        for (Rumah rumah: daftarRumah.keySet()) {
            for (Ruangan ruangan: rumah.getDaftarRuangan()) {
                if (sim.getCurrentRuangan().getIDRuangan().equals(ruangan.getIDRuangan())) {
                    return rumah;
                }
            }
        }
        return null;
    }

    public String getPemilikRumah(Rumah rumah) {
        for (Map.Entry<Rumah, Sim> entry : daftarRumah.entrySet()) {
            if (entry.getKey().getIDRumah() == rumah.getIDRumah()) {
                return entry.getValue().getNama();
            }
        }
        return null;
    }

    /*** Methods ***/
    public void printDaftarRumah() {
        for (Map.Entry<Rumah, Sim> entry : daftarRumah.entrySet()) {
            System.out.println("IDRumah: " + entry.getKey().getIDRumah() + ", Pemilik: " + entry.getValue().getNama());
        }
    }

    public void addRumah(Rumah rumahSim, Sim sim) {
        daftarRumah.put(rumahSim, sim);
    }

    public void placeRumah(Rumah rumah) {
        isAvailable[rumah.getYRumah()][rumah.getXRumah()] = false;
    }

    public void addSim(Sim sim) {
        daftarSim.add(sim);
    }

    public void removeSim(Sim sim) {
        daftarSim.remove(sim);
    }

    public boolean isSimInWorld(String namaSim) {
        for (Sim s : daftarSim) {
            if (s.getNama().equals(namaSim)) {
                return true;
            }
        }
        return false;
    }
}