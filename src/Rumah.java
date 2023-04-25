import java.util.*;

public class Rumah {
    private String IDRumah;
    private Point posisi;
    private ArrayList<Ruangan> daftarRuangan = new ArrayList<Ruangan>();
    private Ruangan R1;
    private static int jumlahRumah = 0; 
    private int jumlahRuangan;


    //constructor==
    public Rumah(Point posisi) throws Exception{
        this.posisi = posisi;
        jumlahRumah ++;
        jumlahRuangan = 1;
        IDRumah = "R" + jumlahRumah; //biar IDRuangan selalu punya nama yg berbeda
        Ruangan R1 = new Ruangan("Kamar", this, posisi);
        tambahRuangan(R1); //inisiasi Ruangan pada Rumah

        //tanya ke asisten, bisa dirandom atau dah pasti punya tipe kasur,dll (furniture) yg tetap tiap kali ngebuat rumah?
        
        //inisiasi masing2 furniture yg bakalan ada tiap kali ngekonstruksiin rumah
        konstruksiR1(R1);
    }

    //bikin the second options untuk konstruktor, biar di ruangan ga perlu nginput super berisi atribut.
    public Rumah(){}

    //methods==
    public void printDaftarRuangan(){
        for (Ruangan ruangan : daftarRuangan){
            System.out.println("IDRuangan: " + ruangan.getIDRuangan() + ", Nama Ruangan: " + ruangan.getNamaRuangan());
        }
    }

    public void tambahRuangan(Ruangan newRoom){
        daftarRuangan.add(newRoom);
        jumlahRuangan ++;
    }

    //untuk mengotomasi pemasukan furniture ke Ruangan 1
    public void konstruksiR1(Ruangan R1) throws Exception{
        Random rand = new Random();

        List<String> temp = new ArrayList<String>();
        temp.add("Kasur Single");
        temp.add("Kasur Queen Size");
        temp.add("Kasur King Size");
        String randomElmt = temp.get(rand.nextInt(temp.size()));
        R1.insertObjectToRuangan(randomElmt, R1.getPosisi(), R1);

        List<String> temp2 = new ArrayList<String>();
        temp2.add("Kompor Gas");
        temp2.add("Kompor Listrik");
        randomElmt = temp2.get(rand.nextInt(temp2.size()));
        R1.insertObjectToRuangan(randomElmt, R1.getPosisi(), R1);

        R1.insertObjectToRuangan("Toilet", R1.getPosisi(), R1);
        R1.insertObjectToRuangan("Meja dan Kursi", R1.getPosisi(), R1);
        R1.insertObjectToRuangan("Jam", R1.getPosisi(), R1);
    }

    //getter==
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
