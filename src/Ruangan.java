import java.util.*;

public class Ruangan extends Rumah{
    private String IDRuangan;
    private String namaRuangan;
    private Point titikRuangan;
    //private int capacity;
    private Map<String, Point> daftarFurniture = new HashMap<String, Point>();
    private boolean[][] isAvailable = new boolean[6][6];
    //

    //constructor
    public Ruangan(String namaRuangan, Rumah rumah, Point titikRuangan){
        IDRuangan = rumah.getIDRumah() + "_" + rumah.getJumlahRuangan();
        this.namaRuangan = namaRuangan;
        this.titikRuangan = titikRuangan;
        //this.capacity = 36;
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

    public int getXRuangan(){
        return titikRuangan.getX();
    }

    public int getYRuangan(){
        return titikRuangan.getY();
    }

    // public int getCapacity(){
    //     return this.capacity;
    // }

    public String getNamaRuangan(){
        return this.namaRuangan;
    }

    //method
    public void printRuangan(Sim sim) {
        for (int j = 0; j < 6; j++) {
            for (int i = 0; i < 6; i++) {
                if (i == sim.getPosisiSim().getX() - titikRuangan.getX() + 3 && j == sim.getPosisiSim().getY() - titikRuangan.getY() + 3) {
                    System.out.print("𖨆");
                } else if (isAvailable[i][j]) {
                    System.out.print("o");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
        System.out.println("X adalah area yang terisi oleh furniture, adapun 'O' digunakan untuk menyatakan area yang masih kosong");
    }
    
    // public void addFurniture(Furniture barang, Sim sim, boolean isRotated) {
    //     int x = barang.getPosisiFurniture().getX();
    //     int y = barang.getPosisiFurniture().getY();
    //     if (isRotated) {
    //         for (int i = x; i < x + barang.getLebar(); i++) {
    //             for (int j = y; j < y + barang.getPanjang(); j++) {
    //                 isAvailable[i][j] = true;
    //             }
    //         }
    //     } else {
    //         for (int i = x; i < x + barang.getPanjang(); i++) {
    //             for (int j = y; j < y + barang.getLebar(); j++) {
    //                 isAvailable[i][j] = true;
    //             }
    //         }
    //     }
    // }

    // public boolean isFurniturePlacable(Furniture barang, boolean isRotated) {
    //     // lets say a furniture with 2x3 size is placed at (x,y), c
    //     // then the furniture will occupy (x,y), (x+1,y), (x,y+1), (x+1,y+1), (x,y+2), (x+1,y+2)
    //     // so we need to check if the position is available
    //     int x = barang.getPosisiFurniture().getX(); 
    //     int y = barang.getPosisiFurniture().getY();
    //     if (isRotated) {
    //         for (int i = x; i < x + barang.getLebar(); i++) {
    //             for (int j = y; j < y + barang.getPanjang(); j++) {
    //                 if (isAvailable[i][j]) {
    //                     return false;
    //                 }
    //             }
    //         }
    //     } else {
    //         for (int i = x; i < x + barang.getPanjang(); i++) {
    //             for (int j = y; j < y + barang.getLebar(); j++) {
    //                 if (isAvailable[i][j]) {
    //                     return false;
    //                 }
    //             }
    //         }
    //     }
    //     return true;
    // }

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
                //this.addObjectToList(obj);
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
                //this.addObjectToList(obj);
            }


        } else {
            System.out.println("Maaf, koordinat yang anda masukkan tidak memiliki cukup ruang pada Ruangan " + getIDRuangan());
        }
    }
}

    // public void addObject(String namaBarang){
    //     try {
    //         Furniture barang = new Furniture(namaBarang);
    //         daftarObjek[daftarObjek.length] = barang;
    //         capacity -= barang.getPanjang() * barang.getLebar();
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

