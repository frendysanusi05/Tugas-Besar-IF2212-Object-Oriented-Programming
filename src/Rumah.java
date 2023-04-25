import java.util.*;

public class Rumah {
    private String IDRumah;
    private Point posisi;
    private ArrayList<Ruangan> daftarRuangan = new ArrayList<Ruangan>();
    //private Ruangan R1;
    private int jumlahRuangan;
    private static int jumlahRumah = 0;


    //constructor
    public Rumah(Point posisi){
        jumlahRumah++;
        IDRumah = "R" + jumlahRumah;
        //this.IDRumah = IDRumah;
        this.posisi = posisi;

        jumlahRuangan++;
        Ruangan R1 = new Ruangan("Kamar", this, posisi); 

        //inisiasi Ruangan pada Rumah
        // daftarRuangan.add(R1);

        addRuangan(R1);
        //inisiasi furniture apa saja yg perlu ada di dalam Rumah (Ruangan 1)
        Random rand = new Random();
        List<String> givenList = new ArrayList<String>();
        givenList.add("Kasur Single");
        givenList.add("Kasur Queen Size");
        givenList.add("Kasur King Size");
        String randomElmt = givenList.get(rand.nextInt(givenList.size()));
        R1.addObject(randomElmt);
        R1.addObject("Toilet");
        
        List<String> givenList2 = new ArrayList<String>();
        givenList2.add("Kompor Gas");
        givenList2.add("Kompor Listrik");
        randomElmt = givenList2.get(rand.nextInt(givenList2.size()));
        R1.addObject(randomElmt);
        R1.addObject("Meja dan Kursi");
        R1.addObject("Jam");
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
    public void addRuangan(Ruangan newRoom){
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

    public int getJumlahRuangan(){
        return jumlahRuangan;
    }

    public int getJumlahRumah(){
        return jumlahRumah;
    }

    // public Ruangan getFirstRuangan(){
    //     return R1;
    // }

}
