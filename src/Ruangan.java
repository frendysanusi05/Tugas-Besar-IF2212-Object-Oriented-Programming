
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Ruangan extends Rumah{
    private String IDRuangan;
    private String namaRuangan;
    private Point titikRuangan;
    private List<Furniture> daftarFurniture = new ArrayList<Furniture>();
    private boolean[][] isAvailable = new boolean[6][6];
    //

    //constructor
    public Ruangan(String namaRuangan, Rumah rumah, Point titikRuangan){
        IDRuangan = rumah.getIDRumah() + "_" + rumah.getJumlahRuangan();
        this.namaRuangan = namaRuangan;
        this.titikRuangan = titikRuangan;
        rumah.tambahRuangan(this);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                isAvailable[i][j] = true;
            }
        }
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

    //method
    public void printRuangan(Sim sim) {
        int x = getXRuangan() - 3;
        int y = getYRuangan() + 2;
        System.out.println();
        System.out.print("   +---+---+---+---+---+---+\n");
        for (int j = 5; j >= 0; j--) {
            if (y < 10) {
                System.out.print(y + "  ");
            } else {
                System.out.print(y + " ");
            }
            y--;
            System.out.print("| ");
            for (int i = 0; i < 6; i++)  {
                //for (Sim sim: daftarSim) {
                if (i == sim.getXSim() - titikRuangan.getX() + 3 && j == sim.getYSim() - titikRuangan.getY() + 3) {
                    System.out.print("웃| ");
                } else if (isAvailable[i][j]) {
                    System.out.print("  | ");
                } else {
                    System.out.print("X | ");
                }
                if (i == 5) {
                    System.out.print("\n   +---+---+---+---+---+---+");
                }
                //}
            }
            System.out.println();
        }
        System.out.print("     ");
        for (int i = 0; i < 6; i++) {
            if (x > 9) {
                System.out.print(x + "  ");
            } else {
                System.out.print(x + "   ");
            }
            x++;
        }
        System.out.println();
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
        boolean found = false;
        if (sim.getXSim() >= getXRuangan() - 3 && sim.getXSim() <= getXRuangan() + 2 && sim.getYSim() >= getYRuangan() - 3 && sim.getYSim() <= getYRuangan() + 2) {
            found = true;
        }
        return found;
    }

    public void addDaftarFurniture (Furniture furniture) {
        daftarFurniture.add(furniture);
    }

    public void insertObjectToRuangan(String namaBarang, Point koordinat /*koordinat barang yg mau disimpen*/, AtomicBoolean isPlaced) throws Exception{
        Furniture obj = new Furniture(namaBarang);
        int panjang = obj.getPanjang();
        int lebar = obj.getLebar();
        boolean opsi1 = (koordinat.getX() >= getXRuangan() - 3 && koordinat.getY() >= getYRuangan() - 3 && koordinat.getX() + panjang <= 3 + getXRuangan() && koordinat.getY() + lebar <= 3 + getYRuangan());
        boolean opsi2 = (koordinat.getX() >= getXRuangan() - 3 && koordinat.getY() >= getYRuangan() - 3 && koordinat.getX() + lebar <= 3 + getXRuangan() && koordinat.getY() + panjang <= 3 + getYRuangan());
        
        if(opsi1){
            int tempX = koordinat.getX() - getXRuangan() + 3;
            int tempY = koordinat.getY() - getYRuangan() + 3;

            for (int i = tempX; i < tempX + obj.getPanjang(); i++) {
                for (int j = tempY; j < tempY + obj.getLebar(); j++) {
                    isAvailable[i][j] = false;
                }
            }
            obj.setXFurniture(koordinat.getX());
            obj.setYFurniture(koordinat.getY());
            daftarFurniture.add(obj);
            isPlaced.set(true);
        } else if (opsi2){
            int tempX = koordinat.getX() - getXRuangan() + 3;
            int tempY = koordinat.getY() - getYRuangan() + 3;
            for (int i = tempX; i < tempX + obj.getLebar(); i++) {
                for (int j = tempY; j < tempY + obj.getPanjang(); j++) {
                    isAvailable[i][j] = false;
                }
            }
            obj.setXFurniture(koordinat.getX());
            obj.setYFurniture(koordinat.getY());
            daftarFurniture.add(obj);
            isPlaced.set(true);
        } else {
            System.out.println("Maaf, koordinat yang anda masukkan tidak memiliki cukup ruang pada Ruangan " + getNamaRuangan());
        }
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
}
