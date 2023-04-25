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

        for(int i=titikRuangan.getY() - 3; i< titikRuangan.getY() + 3; i++){
            for(int j=titikRuangan.getX() - 3; j<titikRuangan.getX() + 3; j++){
                isAvailable[j][i] = true;
            }
        }
    }

    //getter
    public String getIDRuangan(){
        return this.IDRuangan;
    }

    public Point getPosisi(){
        return titikRuangan;
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
    public void addObject(Furniture obj){
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

    //dipake buat ngecek penempatan barang pada koordinat yg diinput.

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
            while(!found && tempY<tempY+lebar){
                while(!found && tempX<tempX+panjang){
                    if(isAvailable[tempX][tempY]){
                        tempX++;
                        tempY++;
                    } else{
                        found = true;
                    }
                    
                }
            }

            if(!found){
                //update Map Furniture pada Ruangan. Merubah bagian yang telah terisi objek menjadi false.
                tempX = koordinat.getX();
                tempY = koordinat.getY();
                while(tempY<tempY+lebar){
                    while(tempX<tempX+panjang){
                        isAvailable[tempX][tempY] = false;
                    }
                }
                this.addObject(obj);
            }

        } else if (opsi2){
            int tempX = koordinat.getX();
            int tempY = koordinat.getY();
            boolean found = false;
            while(!found && tempY<tempY+panjang){
                while(!found && tempX<tempX+lebar){
                    if(isAvailable[tempX][tempY]){
                        tempX++;
                        tempY++;
                    } else{
                        found = true;
                    }
                    
                }
            }

            if(!found){
                //update Map Furniture pada Ruangan. Merubah bagian yang telah terisi objek menjadi false.
                tempX = koordinat.getX();
                tempY = koordinat.getY();
                while(tempY<tempY+panjang){
                    while(tempX<tempX+lebar){
                        isAvailable[tempX][tempY] = false;
                    }
                }
                this.addObject(obj);
            }


        } else {
            System.out.println("Maaf, koordinat yang anda masukkan tidak memiliki cukup ruang pada Ruangan " + getIDRuangan());
        }
    }
}
