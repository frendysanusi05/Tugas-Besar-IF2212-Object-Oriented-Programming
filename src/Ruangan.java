import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Ruangan extends Rumah{
    private String IDRuangan;
    private String namaRuangan;

    /**** titikRuangan bakal jadi titik terkiri bawah ****/
    private Point titikRuangan;
    /**** 
     * Contoh: kamar yang pertama kali digenerate setelah bikin rumah, titiknya jadi (0,0) 
     * titikRuangan buat nentuin ketika di-expand ke atas/bawah/kiri/kanan ada ruangan atau ngga 
    ****/
    
    /**** Atribut Tambahan ****/
    String IDRuanganAtas;
    String IDRuanganBawah;
    String IDRuanganKiri;
    String IDRuanganKanan;

    /**** 
     * Atribut ini dipake pas method pindahRuang
     * Jadi di currentRuangan nya sim, bakal dicek atas, bawah, kiri, kanannya
     ****/

    private List<Furniture> daftarFurniture = new ArrayList<Furniture>();
    private boolean[][] isAvailable = new boolean[6][6];
    //

    // Constructor
    public Ruangan(String namaRuangan, Rumah rumah /*Point titikRuangan*/){
        IDRuangan = rumah.getIDRumah() + "_" + rumah.getJumlahRuangan();
        this.namaRuangan = namaRuangan;
        //this.titikRuangan = titikRuangan;
        rumah.tambahRuangan(this);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                isAvailable[i][j] = true;
            }
        }
        /**** Ketika pertama kali di-construct, dibikin null dulu ****/
        IDRuanganAtas = null;
        IDRuanganBawah = null;
        IDRuanganKiri = null;
        IDRuanganKanan = null;
        /**** 
         * ruanganAtas/ruanganBawah/ruanganKiri/ruanganKanan punya nilai rumah ketika upgradeRumah 
         * (Prinsipnya bakal sama kaya linked list)
        ****/

    }

    //getter
    public String getIDRuangan(){
        return this.IDRuangan;
    }

    public Point getTitikRuangan(){
        return titikRuangan;
    }

    public int getXRuangan(){
        return titikRuangan.getX();
    }

    public int getYRuangan(){
        return titikRuangan.getY();
    }

    public String getNamaRuangan(){
        return this.namaRuangan;
    }

    public List<Furniture> getDaftarFurniture(){
        return daftarFurniture;
    }

    public boolean[][] getIsAvailable(){
        return isAvailable;
    }

    public Furniture getFurniture(String namaFurniture){
        for (Furniture furniture : daftarFurniture) {
            if (furniture.getNama().equals(namaFurniture)) {
                return furniture;
            }
        }
        return null;
    }

    public String getIDRuanganAtas() {
        return IDRuanganAtas;
    }

    public String getIDRuanganBawah() {
        return IDRuanganBawah;
    }

    public String getIDRuanganKiri() {
        return IDRuanganKiri;
    }

    public String getIDRuanganKanan() {
        return IDRuanganKanan;
    }

    // Setter
    public void setIDRuanganAtas(String IDRuanganAtas) {
        this.IDRuanganAtas = IDRuanganAtas;
    }

    public void setIDRuanganBawah(String IDRuanganBawah) {
        this.IDRuanganBawah = IDRuanganBawah;
    }

    public void setIDRuanganKiri(String IDRuanganKiri) {
        this.IDRuanganKiri = IDRuanganKiri;
    }

    public void setIDRuanganKanan(String IDRuanganKanan) {
        this.IDRuanganKanan = IDRuanganKanan;
    }

    public void setPosisiRuangan(Point newPos) {
        if (titikRuangan == null) {
            titikRuangan = new Point(0, 0);
        }
        Point copiedPoint = titikRuangan.clone();
        copiedPoint.setX(newPos.getX());
        copiedPoint.setY(newPos.getY());
        titikRuangan = copiedPoint;
    }

    // Method
    public void printRuangan(Sim sim, World world) {
        List<Sim> daftarSim = world.getDaftarSim();
        System.out.println();
        System.out.print("   +---+---+---+---+---+---+\n");
        for (int j = 5; j >= 0; j--) {
            System.out.print("   | ");
            for (int i = 0; i < 6; i++)  {
                //for (Sim sim: daftarSim) {
                if (i == sim.getXSim() && j == sim.getYSim()) {
                    System.out.print("웃| ");
                } else {
                    boolean filled = false;
                    for (Sim s: daftarSim) {
                        if (s.getXSim() == i && s.getYSim() == j && !s.getNama().equals(sim.getNama()) && s.getCurrentRuangan().equals(this)) {
                            System.out.print("유| ");
                            filled = true;
                            break;
                        }
                    }
                    if (isAvailable[j][i] && !filled) {
                        System.out.print("  | ");
                    } else if (!isAvailable[j][i] && !filled) {
                        System.out.print("X | ");
                    }
                }
                if (i == 5) {
                    System.out.print("\n   +---+---+---+---+---+---+");
                }
            }
            System.out.println();
        }
        System.out.print("     ");
        System.out.println();
    }

    public void printRuanganWithCoordinate(Sim sim) {
        int x = 0;
        int y = 5;
        System.out.println();
        System.out.print("   +---+---+---+---+---+---+\n");
        for (int j = 5; j >= 0; j--) {
            System.out.print(y + "  ");
            y--;
            System.out.print("| ");
            for (int i = 0; i < 6; i++)  {
                if (i == sim.getXSim() - titikRuangan.getX() + 3 && j == sim.getYSim() - titikRuangan.getY() + 3) {
                    System.out.print("웃| ");
                } else if (isAvailable[j][i]) {
                    System.out.print("  | ");
                } else {
                    System.out.print("X | ");
                }
                if (i == 5) {
                    System.out.print("\n   +---+---+---+---+---+---+");
                }
            }
            System.out.println();
        }
        System.out.print("     ");
        for (int i = 0; i < 6; i++) {
            System.out.print(x + "   ");
            x++;
        }
        System.out.println();
    }

    public void printRuanganNextTo() {
        int i = 1;
        if (IDRuanganAtas != null) {
            System.out.println(i + ". " + getRuanganBasedOnID(IDRuanganAtas).getNamaRuangan());
            i++;
        } 
        if (IDRuanganBawah != null) {
            System.out.println(i + ". " + getRuanganBasedOnID(IDRuanganBawah).getNamaRuangan());
            i++;
        }
        if (IDRuanganKiri != null) {
            System.out.println(i + ". " + getRuanganBasedOnID(IDRuanganKiri).getNamaRuangan());
            i++;
        }
        if (IDRuanganKanan != null) {
            System.out.println(i + ". " + getRuanganBasedOnID(IDRuanganKanan).getNamaRuangan());
            i++;
        }
    }

    public boolean isFurnitureInRuangan (String namaFurniture) {
        boolean found = false;
        for (Furniture furniture : daftarFurniture) {
            if (furniture.getNama().equals(namaFurniture)) {
                found = true;
                break;
            }
        }
        return found;
    }

    public boolean isSimInRuangan(Sim sim) {
        return sim.getCurrentRuangan().getNamaRuangan().equals(namaRuangan);
    }

    public void addDaftarFurniture (Furniture furniture) {
        daftarFurniture.add(furniture);
    }

    public void insertObjectToRuangan(String namaBarang, Point koordinat, AtomicBoolean isPlaced) throws Exception{
        // Furniture obj = new Furniture(namaBarang);
        
        // boolean opsi1 = (koordinat.getX() >= getXRuangan() - 3 && koordinat.getY() >= getYRuangan() - 3 && koordinat.getX() + panjang <= 3 + getXRuangan() && koordinat.getY() + lebar <= 3 + getYRuangan());
        // boolean opsi2 = (koordinat.getX() >= getXRuangan() - 3 && koordinat.getY() >= getYRuangan() - 3 && koordinat.getX() + lebar <= 3 + getXRuangan() && koordinat.getY() + panjang <= 3 + getYRuangan());
        
        // if(opsi1){
        //     int tempX = koordinat.getX() - getXRuangan() + 3;
        //     int tempY = koordinat.getY() - getYRuangan() + 3;

        //     for (int i = tempX; i < tempX + obj.getPanjang(); i++) {
        //         for (int j = tempY; j < tempY + obj.getLebar(); j++) {
        //             isAvailable[i][j] = false;
        //         }
        //     }
        //     obj.setXFurniture(koordinat.getX());
        //     obj.setYFurniture(koordinat.getY());
        //     daftarFurniture.add(obj);
        //     isPlaced.set(true);
        // } else if (opsi2){
        //     int tempX = koordinat.getX() - getXRuangan() + 3;
        //     int tempY = koordinat.getY() - getYRuangan() + 3;
        //     for (int i = tempX; i < tempX + obj.getLebar(); i++) {
        //         for (int j = tempY; j < tempY + obj.getPanjang(); j++) {
        //             isAvailable[i][j] = false;
        //         }
        //     }
        //     obj.setXFurniture(koordinat.getX());
        //     obj.setYFurniture(koordinat.getY());
        //     daftarFurniture.add(obj);
        //     isPlaced.set(true);
        // } else {

        if (isFurniturePlacable(namaBarang, koordinat)) {
            Furniture obj = new Furniture(namaBarang);
            int tempX = koordinat.getX();
            int tempY = koordinat.getY();
            for (int i = tempX; i < tempX + obj.getPanjang(); i++) {
                for (int j = tempY; j < tempY + obj.getLebar(); j++) {
                    isAvailable[j][i] = false;
                }
            }
            obj.setXFurniture(koordinat.getX());
            obj.setYFurniture(koordinat.getY());
            daftarFurniture.add(obj);
            isPlaced.set(true);
        } else {
            System.out.println("Furniture: " + namaBarang + " tidak dapat ditempatkan pada koordinat " + koordinat.getX() + ", " + koordinat.getY() + " pada Ruangan " + getNamaRuangan() + "!");
            System.out.println("Maaf, koordinat yang anda masukkan tidak memiliki cukup ruang pada Ruangan " + getNamaRuangan());
        }
        
    }

    public boolean isFurniturePlacable(String namaBarang, Point koordinat) throws Exception {
        Furniture obj = new Furniture(namaBarang);
        int panjang = obj.getPanjang();
        int lebar = obj.getLebar();
        boolean case1 = (koordinat.getX() >= 0 && koordinat.getY() >= 0 && koordinat.getX() + panjang <= 6 && koordinat.getY() + lebar <= 6);
        //boolean opsi2 = (koordinat.getX() >= 0 && koordinat.getY() >= 0 && koordinat.getX() + lebar <= 6 && koordinat.getY() + panjang <= 6);
        if (!case1) {
            if (koordinat.getX() < 0) {
                System.out.println("X kurang dari 0");
            } else if (koordinat.getY() < 0) {
                System.out.println("Y kurang dari 0");
            } else if (koordinat.getX() + panjang > 6) {
                System.out.println("X lebih dari 6");
            } else if (koordinat.getY() + lebar > 6) {
                System.out.println("Y lebih dari 6");
            }
        }
        boolean case2 = false;
        // for (int j = 0; j < 6; j++) {
        //     for (int i = 0; i < 6; i++) {
        //         if (!isAvailable[j][i]) {
        //             bool = true;
        //             break;
        //         }
        //     }
        // }
        if (isAvailable[koordinat.getY()][koordinat.getX()]) {
            case2 = true;
        }
        if (!case2) {
            System.out.println("Koordinat " + koordinat.getX() + ", " + koordinat.getY() + " sudah terisi");
        }

        return case1 && case2;
    }

    public void printDaftarFurniture() {
        // System.out.println("Daftar Furniture yang ada di Ruangan " + getNamaRuangan() + " :");

        // if there is two or more furniture with the same name, then only print one of them
        int i = 0;
        String[] daftarNamaFurniture = new String[daftarFurniture.size()];
        for (Furniture furniture : daftarFurniture) {
            boolean found = false;
            for (int j = 0; j < i; j++) {
                if (daftarNamaFurniture[j].equals(furniture.getNama())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                daftarNamaFurniture[i] = furniture.getNama();
                i++;
            }
        }
        int j = 1;
        for (String nama : daftarNamaFurniture) {
            if (nama != null) {
                System.out.println(j + ". " + nama);
                j++;
            }
        }

    }

    public void checkSurrounding(Rumah rumah) {
        for (Ruangan ruangan : rumah.getDaftarRuangan()) {
            if (ruangan.getXRuangan() == getXRuangan() && ruangan.getYRuangan() == titikRuangan.getY() + 6 && getIDRuanganAtas() == null) {
                System.out.println("Ruang " + ruangan.getNamaRuangan() + " berada di atas " + getNamaRuangan());
                setIDRuanganAtas(ruangan.getIDRuangan());
                ruangan.setIDRuanganBawah(IDRuangan);
            } 
            if (ruangan.getXRuangan() == getXRuangan() && ruangan.getYRuangan() == titikRuangan.getY() - 6 && getIDRuanganBawah() == null) {
                System.out.println("Ruang " + ruangan.getNamaRuangan() + " berada di bawah " + getNamaRuangan());
                setIDRuanganBawah(ruangan.getIDRuangan());
                ruangan.setIDRuanganAtas(IDRuangan);
            } 
            if (ruangan.getYRuangan() == getYRuangan() && ruangan.getXRuangan() == titikRuangan.getX() + 6 && getIDRuanganKanan() == null) {
                System.out.println("Ruang " + ruangan.getNamaRuangan() + " berada di kanan " + getNamaRuangan());
                setIDRuanganKanan(ruangan.getIDRuangan());
                ruangan.setIDRuanganKiri(IDRuangan);
            } 
            if (ruangan.getYRuangan() == getYRuangan() && ruangan.getXRuangan() == titikRuangan.getX() - 6 && getIDRuanganKiri() == null) {
                System.out.println("Ruang " + ruangan.getNamaRuangan() + " berada di kiri " + getNamaRuangan());
                setIDRuanganKiri(ruangan.getIDRuangan());
                ruangan.setIDRuanganKanan(IDRuangan);
            }
        }
    }
}
