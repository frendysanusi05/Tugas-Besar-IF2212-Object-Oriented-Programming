import java.util.*;

public class Ruangan extends Rumah{
    private String IDRuangan;
    private String namaRuangan;
    private Point titikRuangan;
    private ArrayList<Furniture> daftarObjek;
    private int capacity;
    private boolean[][] isAvailable = new boolean[6][6];

    //constructor
    public Ruangan(String namaRuangan, Rumah rumah, Point titikRuangan){
        super();
        IDRuangan = rumah.getIDRumah() + "_" + rumah.getJumlahRuangan();
        this.namaRuangan = namaRuangan;
        this.titikRuangan = titikRuangan;
        this.capacity = 36;

        for(int i=0; i<6; i++){
            for(int j=0; j<6; j++){
                isAvailable[j][i] = true;
            }
        }
    }

    //getter
    public String getIDRuangan(){
        return this.IDRuangan;
    }

    public int getXRuangan(){
        return titikRuangan.getX();
    }

    public int getYRuangan(){
        return titikRuangan.getY();
    }

    public ArrayList<Furniture> getDaftarObjek(){
        return this.daftarObjek;
    }

    public int getRemainingCapacity(){
        return this.capacity;
    }

    public String getNamaRuangan(){
        return this.namaRuangan;
    }

    //method
    public void addObjectToList(Furniture obj){
        daftarObjek.add(obj);
    }

    //dipake buat mempermudah user untuk memilih daerah yg mau diisi (?)
    //user bakalan lbh tergambarkan kondisi buat mau nambahin atau engga furniture di ruangan itu kalo dikasi gambaran kek gini gasi (?)
    public void printRuangan(){
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                if(isAvailable[j][i]){
                    System.out.print("O");
                }else{
                    System.out.print("X");
                }
            }
            System.out.println();
        }
        System.out.println("Bagian bertanda 'X' adalah area yang terisi oleh furniture, adapun 'O' digunakan untuk menyatakan area yang masih kosong");
    }

    public void insertObjectToRuangan(String namaBarang, Point koordinat /*koordinat barang yg mau disimpen*/, Ruangan ruangan) throws Exception{
        Furniture obj = new Furniture(namaBarang);
        int panjang = obj.getPanjang();
        int lebar = obj.getLebar();
        boolean opsi1 = (koordinat.getX() >= ruangan.getXRuangan() - 3 && koordinat.getY() >= ruangan.getYRuangan() - 3 && koordinat.getX() + panjang <= 3 + ruangan.getXRuangan() && koordinat.getY() + lebar <= 3 + ruangan.getYRuangan());
        boolean opsi2 = (koordinat.getX() >= ruangan.getXRuangan() - 3 && koordinat.getY() >= ruangan.getYRuangan() - 3 && koordinat.getX() + lebar <= 3 + ruangan.getXRuangan() && koordinat.getY() + panjang <= 3 + ruangan.getYRuangan());
        
        if(opsi1){
            int tempX = koordinat.getX();
            int tempY = koordinat.getY();
            boolean found = false;
            while(!found && tempX<tempX+panjang){
                while(!found && tempY<tempY+lebar){
                    if(isAvailable[tempY][tempX]){
                        tempX++;
                        tempY++;
                    } else{
                        found = true;
                    }
                    
                }
            }

            if(!found){
                //update Map Furniture pada Ruangan
                tempX = koordinat.getX();
                tempY = koordinat.getY();
                while(tempX<tempX+panjang){
                    while(tempY<tempY+lebar){
                        isAvailable[tempY][tempX] = false;
                    }
                }
                this.addObjectToList(obj);
            }

        } else if (opsi2){
            int tempX = koordinat.getX();
            int tempY = koordinat.getY();
            boolean found = false;
            while(!found && tempX<tempX+lebar){
                while(!found && tempY<tempY+panjang){
                    if(isAvailable[tempY][tempX]){
                        tempX++;
                        tempY++;
                    } else{
                        found = true;
                    }
                    
                }
            }

            if(!found){
                //update Map Furniture pada Ruangan
                tempX = koordinat.getX();
                tempY = koordinat.getY();
                while(tempX<tempX+lebar){
                    while(tempY<tempY+panjang){
                        isAvailable[tempY][tempX] = false;
                    }
                }
                this.addObjectToList(obj);
            }


        } else {
            System.out.println("Maaf, koordinat yang anda masukkan tidak memiliki cukup ruang pada Ruangan " + getIDRuangan());
        }
    }
}
