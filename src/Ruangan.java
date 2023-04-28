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

    //method
    public void printRuangan(Sim sim) {
        int x = getXRuangan() - 3;
        int y = getYRuangan() - 3;
        System.out.print("   ");
        for (int i = 0; i < 6; i++) {
            if (x > 9) {
                System.out.print(x + " ");
            } else {
                System.out.print(x + "  ");
            }
            x++;
        }
        System.out.println();
        for (int j = 0; j < 6; j++) {
            System.out.print(y + " ");
            y++;
            for (int i = 0; i < 6; i++) {
                if (i == sim.getPosisiSim().getX() - titikRuangan.getX() + 3 && j == sim.getPosisiSim().getY() - titikRuangan.getY() + 3) {
                    System.out.print("𖨆  ");
                } else if (isAvailable[i][j]) {
                    System.out.print("O  ");
                } else {
                    System.out.print("X  ");
                }
            }
            System.out.println();
        }
        System.out.println("X adalah area yang terisi oleh furniture, adapun 'O' digunakan untuk menyatakan area yang masih kosong");
    }

    public boolean isFurnitureInRuangan (Furniture furniture) {
        return daftarFurniture.contains(furniture);
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
            // int tempX = koordinat.getX();
            // int tempY = koordinat.getY();
            int tempX = koordinat.getX() - getXRuangan() + 3;
            int tempY = koordinat.getY() - getYRuangan() + 3;
            // boolean found = false;
            // while(!found && tempX<tempX+panjang){
            //     while(!found && tempY<tempY+lebar){
            //         if(isAvailable[tempY][tempX]){
            //             tempX++;
            //             tempY++;
            //         } else{
            //             found = true;
            //         }
                    
            //     }
            // }

            // if(!found){
            //     //update Map Furniture pada Ruangan
            //     // tempX = koordinat.getX();
            //     // tempY = koordinat.getY();
            //     tempX = koordinat.getX() - getXRuangan() + 3;
            //     tempY = koordinat.getY() - getYRuangan() + 3;
            //     while(tempX<tempX+panjang){
            //         while(tempY<tempY+lebar){
            //             isAvailable[tempY][tempX] = false;
            //         }
            //     }
            //     addDaftarFurniture(obj);
            //     isPlaced = true;
            // }

            for (int i = tempX; i < tempX + obj.getPanjang(); i++) {
                for (int j = tempY; j < tempY + obj.getLebar(); j++) {
                    isAvailable[i][j] = false;
                }
            }
            isPlaced.set(true);
            System.out.println("Berhasil menaruh barang");
            
        } else if (opsi2){
            // int tempX = koordinat.getX();
            // int tempY = koordinat.getY();
            int tempX = koordinat.getX() - getXRuangan() + 3;
            int tempY = koordinat.getY() - getYRuangan() + 3;
            // boolean found = false;
            // while(!found && tempX<tempX+lebar){
            //     while(!found && tempY<tempY+panjang){
            //         if(isAvailable[tempY][tempX]){
            //             tempX++;
            //             tempY++;
            //         } else{
            //             found = true;
            //         }
            //     }
            // }

            // if(!found){
            //     //update Map Furniture pada Ruangan
            //     // tempX = koordinat.getX();
            //     // tempY = koordinat.getY();
            //     tempX = koordinat.getX() - getXRuangan() + 3;
            //     tempY = koordinat.getY() - getYRuangan() + 3;
            //     while(tempX<tempX+lebar){
            //         while(tempY<tempY+panjang){
            //             isAvailable[tempY][tempX] = false;
            //         }
            //     }
            //     addDaftarFurniture(obj); 
            //     isPlaced = true;
            // }

            for (int i = tempX; i < tempX + obj.getLebar(); i++) {
                for (int j = tempY; j < tempY + obj.getPanjang(); j++) {
                    isAvailable[i][j] = false;
                }
            }
            isPlaced.set(true);
            System.out.println("Berhasil menaruh barang");


        } else {
            System.out.println("Maaf, koordinat yang anda masukkan tidak memiliki cukup ruang pada Ruangan " + getNamaRuangan());
        }
    }

    public boolean isFurniturePlacable(Furniture barang, boolean isRotated) {
        // int x = barang.getXFurniture() - getXRuangan() + 3; 
        // int y = barang.getYFurniture() - getYRuangan() + 3;
        int panjang = barang.getPanjang();
        int lebar = barang.getLebar();
        if (isRotated) {
            // if (x + barang.getLebar() > 6 || y + barang.getPanjang() > 6) {
            //     return false;
            // } else {
            //     for (int i = x; i < x + barang.getLebar(); i++) {
            //         for (int j = y; j < y + barang.getPanjang(); j++) {
            //             if (!isAvailable[i][j]) {
            //                 return false;
            //             }
            //         }
            //     }
            // }
            return (barang.getXFurniture() >= getXRuangan() - 3 && barang.getYFurniture() >= getYRuangan() - 3 && barang.getXFurniture() + lebar <= 3 + getXRuangan() && barang.getYFurniture() + panjang <= 3 + getYRuangan());
        } else {
            // if (x + barang.getPanjang() > 6 || y + barang.getLebar() > 6) {
            //     return false;
            // } else {
            //     for (int i = x; i < x + barang.getPanjang(); i++) {
            //         for (int j = y; j < y + barang.getLebar(); j++) {
            //             if (!isAvailable[i][j]) {
            //                 return false;
            //             }
            //         }
            //     }
            // }
            return (barang.getXFurniture() >= getXRuangan() - 3 && barang.getYFurniture() >= getYRuangan() - 3 && barang.getXFurniture() + panjang <= 3 + getXRuangan() && barang.getYFurniture() + lebar <= 3 + getYRuangan());
        }
        // return true;
    }

    public void insertObjectRandomly(String namaBarang) throws Exception {
        System.out.println("Nama Barang: " + namaBarang);
        Furniture barang = new Furniture(namaBarang);
        System.out.println("Panjang = " + barang.getPanjang() + ", Lebar = " + barang.getLebar());
        Random rand = new Random();
        int x = rand.nextInt(6) + getXRuangan() - 3;
        int y = rand.nextInt(6) + getYRuangan() - 3;
        barang.setXFurniture(x);
        barang.setYFurniture(y);
        while(!isFurniturePlacable(barang, false)) {
            // barang.rotateFurniture();
            // if (!isFurniturePlacable(barang, true)) {
            //     barang.rotateFurniture();
            //     x++;
            //     if (x > getXRuangan()) {
            //         x = 0;
            //         y++;
            //     }
            //     barang.setXFurniture(x);
            //     barang.setYFurniture(y);
            // }
            System.out.println("x = " + x + " y = " + y);
            barang.rotateFurniture();
            if (!isFurniturePlacable(barang, true)) {
                System.out.println("x = " + x + " y = " + y);
                barang.rotateFurniture();
                x = rand.nextInt(6) + getXRuangan() - 3;
                y = rand.nextInt(6) + getYRuangan() - 3;
                barang.setXFurniture(x);
                barang.setYFurniture(y);
            }
        }
        System.out.println("x = " + x + " y = " + y);

        //ini jaga2 kalo dia udah ditaro di semua sudut tapi gabisa
        if (x >= getXRuangan() +  3 && y >= getYRuangan() + 3) {
            System.out.println("Tidak bisa ditaruh di manapun!");
        } else {
            insertObjectToRuangan(namaBarang, new Point(x, y), new AtomicBoolean(false));
        }     
    }
}

