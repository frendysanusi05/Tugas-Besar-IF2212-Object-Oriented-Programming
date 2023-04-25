import java.util.*;

public class Rumah {
    private String IDRumah;
    private Point posisi;
    private ArrayList<Ruangan> daftarRuangan = new ArrayList<Ruangan>();
    private Ruangan R1;
    private static int jumlahRumah = 0; 
    private int jumlahRuangan;


    //constructor
    public Rumah(Point posisi){
        this.posisi = posisi;
        jumlahRumah ++;
        jumlahRuangan = 1;
        IDRumah = "R" + jumlahRumah;
        Ruangan R1 = new Ruangan("Kamar", this, posisi);

        //Ruangan IDRumah = new Ruangan("IniPerluDiGenerateDlu" /*Perlu digenerate dlu gasi?*/, "Awal", IDRumah, posisi); //ini IDRuangannya mau digimanain? buat yg bagian awal. 

        //inisiasi Ruangan pada Rumah
        tambahRuangan(R1);

        //tanya ke asisten, bisa dirandom atau dah pasti punya tipe kasur dll yg tetap?
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

    //bikin the second option untuk konstruktor, biar di ruangan ga perlu nginput super berisi atribut.
    public Rumah(){}

    //methods
    public void printDaftarRuangan(){
        for (Ruangan ruangan : daftarRuangan){
            //ini sbenernya gua masi kurang ngerti, konstruktor ruangan mau dibentuk kek gmn wkwk
            System.out.println("IDRuangan: " + ruangan.getIDRuangan() + ", Nama Ruangan: " + ruangan.getNamaRuangan());
        }
    }

    public void tambahRuangan(Ruangan newRoom){
        daftarRuangan.add(newRoom);
        jumlahRuangan ++;
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

    public Ruangan getFirstRuangan(){
        return R1;
    }

}
