import java.util.*;

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

        // daftarRuangan.add(R1);

        // addRuangan(R1);
        // inisiasi furniture apa saja yg perlu ada di dalam Rumah (Ruangan 1)
        // Random rand = new Random();
        // List<String> givenList = new ArrayList<String>();
        // givenList.add("Kasur Single");
        // givenList.add("Kasur Queen Size");
        // givenList.add("Kasur King Size");
        // String randomElmt = givenList.get(rand.nextInt(givenList.size()));
        // R1.addObject(randomElmt);
        // R1.addObject("Toilet");
        
        // List<String> givenList2 = new ArrayList<String>();
        // givenList2.add("Kompor Gas");
        // givenList2.add("Kompor Listrik");
        // randomElmt = givenList2.get(rand.nextInt(givenList2.size()));
        // R1.addObject(randomElmt);
        // R1.addObject("Meja dan Kursi");
        // R1.addObject("Jam");

        String[] furnitureStarter = {"Kasur Single", "Toilet", "Kompor Gas", "Meja dan Kursi", "Jam"};
        for(String furniture : furnitureStarter) {
            kamar.insertObjectRandomly(furniture);
        }
    }

    public Rumah() {}

    //methods
    public void printDaftarRuangan(){
        for (Ruangan ruangan : daftarRuangan){
            //ini sbenernya gua masi kurang ngerti, konstruktor ruangan mau dibentuk kek gmn wkwk
            System.out.println("IDRuangan: " + ruangan.getIDRuangan() + ", Nama Ruangan: " + ruangan.getNamaRuangan());
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
            if (ruangan.getXRuangan() == sim.getXSim() && ruangan.getYRuangan() == sim.getYSim()) {
                return ruangan;
            }
        }
        return null;
    }

    public int getJumlahRuangan(){
        return jumlahRuangan;
    }

    public int getJumlahRumah(){
        return jumlahRumah;
    }
}
