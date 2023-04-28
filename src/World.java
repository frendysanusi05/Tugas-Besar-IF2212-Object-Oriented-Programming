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
                    cekPosisi[j][i] = true;
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
        for (int j = 63; j >= 0; j--) {
            for (int i = 0; i < 64; i++) {
                if (cekPosisi[j][i]) {
                    System.out.print("o");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }

    public Rumah getRumahSim(Sim sim) {
        for (Map.Entry<Rumah, Sim> entry : daftarRumah.entrySet()) {
            if (entry.getValue().getNama().equals(sim.getNama())) {
                return entry.getKey();
            }
        }
        return null;
    }

    public List<Point> checkAvailable(Rumah rumah) {
        List<Point> available = new ArrayList<Point>();
        //check the left
        for (Ruangan ruangan : rumah.getDaftarRuangan()) {
            int x1 = ruangan.getXRuangan();
            int x2 = ruangan.getXRuangan() - 6;
            int x3 = ruangan.getXRuangan() + 6;
            int y1 = ruangan.getYRuangan();
            int y2 = ruangan.getYRuangan() - 6;
            int y3 = ruangan.getYRuangan() + 6;

            // cek kiri ruangan
            // print nama ruangan
            System.out.println(ruangan.getNamaRuangan());
            if (x2 >= 0 && cekPetak(new Point(x2, y1))) {
                boolean found = false;
                for (Point point : available) {
                    if (point.getX() == x2 && point.getY() == y1) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    available.add(new Point(x2, y1));
                }
            }

            // cek kanan ruangan
            if (x3 < 64 && cekPetak(new Point(x3, y1))) {
                boolean found = false;
                for (Point point : available) {
                    if (point.getX() == x3 && point.getY() == y1) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    available.add(new Point(x3, y1));
                }
            }

            // cek atas ruangan
            if (y2 >= 0 && cekPetak(new Point(x1, y2))) {
                boolean found = false;
                for (Point point : available) {
                    if (point.getX() == x1 && point.getY() == y2) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    available.add(new Point(x1, y2));
                }
            }

            // cek bawah ruangan
            if (y3 < 64 && cekPetak(new Point(x1, y3))) {
                boolean found = false;
                for (Point point : available) {
                    if (point.getX() == x1 && point.getY() == y3) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    available.add(new Point(x1, y3));
                }
            }
        }
        return available;
    }

    public boolean cekPetak(Point posisi) {
        boolean isExpandable = true;
        int x = posisi.getX() - 3;
        int y = posisi.getY() - 3;
        while (isExpandable && y < posisi.getY() + 3) {
            if (cekPosisi[y][x]) {
                isExpandable = false;
            } else {
                x++;
                if (x == posisi.getX() + 3) {
                    x = posisi.getX() - 3;
                    y++;
                }
            }
            if (x >= 64 || y >= 64) {
                isExpandable = false;
            }
        }
        return isExpandable;
    }
}