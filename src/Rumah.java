
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Rumah {
    private String IDRumah;
    private Point posisi;
    private ArrayList<Ruangan> daftarRuangan = new ArrayList<Ruangan>();
    //private Ruangan R1;
    private int jumlahRuangan;
    private static int jumlahRumah = 0;


    //constructor
    public Rumah(Point posisi) throws Exception {
        jumlahRumah++;
        IDRumah = "R" + jumlahRumah;
        this.posisi = posisi;

        //inisiasi Ruangan pada Rumah
        jumlahRuangan++;
        Ruangan kamar = new Ruangan("Kamar", this, posisi); 

        Furniture kasurSingle = new Furniture("Kasur Single");
        Furniture toilet = new Furniture("Toilet");
        Furniture komporGas = new Furniture("Kompor Gas");
        Furniture mejaDanKursi = new Furniture("Meja dan Kursi");
        Furniture jam = new Furniture("Jam");

        // kamar.addDaftarFurniture(kasurSingle);
        // kamar.addDaftarFurniture(toilet);
        // kamar.addDaftarFurniture(komporGas);
        // kamar.addDaftarFurniture(mejaDanKursi);
        // kamar.addDaftarFurniture(jam);

        int xKamar = kamar.getXRuangan();
        int yKamar = kamar.getYRuangan();
        kamar.insertObjectToRuangan("Kasur Single", new Point (xKamar - 3, yKamar + 1), new AtomicBoolean(false));
        kasurSingle.setXFurniture(xKamar - 3);
        kasurSingle.setYFurniture(yKamar + 1);

        kamar.insertObjectToRuangan("Toilet", new Point (xKamar + 2, yKamar + 2), new AtomicBoolean(false));
        toilet.setXFurniture(xKamar + 2);
        toilet.setYFurniture(yKamar + 2);

        kamar.insertObjectToRuangan("Kompor Gas", new Point (xKamar + 1, yKamar - 3), new AtomicBoolean(false));
        komporGas.setXFurniture(xKamar - 3);
        komporGas.setYFurniture(yKamar + 2);

        kamar.insertObjectToRuangan("Meja dan Kursi", new Point (xKamar - 3, yKamar - 3), new AtomicBoolean(false));
        mejaDanKursi.setXFurniture(xKamar - 3);
        mejaDanKursi.setYFurniture(yKamar - 3);

        kamar.insertObjectToRuangan("Jam", new Point (xKamar + 2, yKamar), new AtomicBoolean(false));
        jam.setXFurniture(xKamar + 2);
        jam.setYFurniture(yKamar);
    }

    public Rumah() {}

    //methods
    public void printDaftarRuangan(){
        int i = 1;
        for (Ruangan ruangan : daftarRuangan){
            //ini sbenernya gua masi kurang ngerti, konstruktor ruangan mau dibentuk kek gmn wkwk
            System.out.println(i + ") " + ruangan.getNamaRuangan());
            i++;
        }
    }

    //ini blom dikonekin sama #cek ruang kosong yg ada di class ruangan dan #cekposisi yg ada di world
    public void tambahRuangan(Ruangan newRoom){
        jumlahRuangan++;
        daftarRuangan.add(newRoom);
    } 

    //getter
    public String getIDRumah(){
        return this.IDRumah;
    }

    public int getXRumah(){
        return posisi.getX();
    }

    public int getYRumah(){
        return posisi.getY();
    }

    public ArrayList<Ruangan> getDaftarRuangan(){
        return daftarRuangan;
    }

    public Ruangan getRuangan(String namaRuangan){
        for (Ruangan ruangan : daftarRuangan) {
            if (ruangan.getNamaRuangan().equals(namaRuangan)){
                return ruangan;
            }
        }
        return null;
    }

    public Ruangan getCurrentRuanganSim(Sim sim) {
        for (Ruangan ruangan : daftarRuangan) {
            if (sim.getXSim() >= ruangan.getXRuangan() - 3 && sim.getXSim() <= ruangan.getXRuangan() + 2 && sim.getYSim() >= ruangan.getYRuangan() - 3 && sim.getYSim() <= ruangan.getYRuangan() + 2) {
                // print nama ruangan dan point nya
                return ruangan;
            }
        }
        System.out.println("Sim tidak sedang di dalam rumah");
        return null;
    }

    public int getJumlahRuangan(){
        return jumlahRuangan;
    }

    public int getJumlahRumah(){
        return jumlahRumah;
    }

    
}