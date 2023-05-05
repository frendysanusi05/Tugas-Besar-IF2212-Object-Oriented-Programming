package obj;

import utilz.ObjectType;
import utilz.Point;
import interfaces.Purchasable;

public class Furniture extends ObjectType<Furniture> implements Purchasable{
    private String nama;
    private int panjang;
    private int lebar;
    private int harga;
    private String[] aksi = new String[4];
    private Point posisiFurniture = new Point();
    
    public Furniture(String nama) throws Exception {
        super("Furniture");
        switch(nama) {
            case "Kasur Single":
                this.nama = "Kasur Single";
                panjang = 4;
                this.lebar = 1;
                this.harga = 50;
                this.aksi[0] = "Tidur";
                this.aksi[1] = "Rebahan";
                break;
            case "Kasur Queen Size":
                this.nama = "Kasur Queen Size";
                this.panjang = 4;
                this.lebar = 2;
                this.harga = 100;
                this.aksi[0] = "Tidur";
                this.aksi[1] = "Rebahan";
                break;
            case "Kasur King Size":
                this.nama = "Kasur King Size";
                this.panjang = 5;
                this.lebar = 2;
                this.harga = 150;
                this.aksi[0] = "Tidur";
                this.aksi[1] = "Rebahan";
                break;
            case "Toilet":
                this.nama = "Toilet";
                this.panjang = 1;
                this.lebar = 1;
                this.harga = 50;
                this.aksi[0] = "Buang air";
                break;
            case "Kompor Gas":
                this.nama = "Kompor Gas";
                this.panjang = 2;
                this.lebar = 1;
                this.harga = 100;
                this.aksi[0] = "Memasak";
                break;
            case "Kompor Listrik":
                this.nama = "Kompor Listrik";
                this.panjang = 1;
                this.lebar = 1;
                this.harga = 200;
                this.aksi[0] = "Memasak";
                break;
            case "Meja dan Kursi":
                this.nama = "Meja dan Kursi";
                this.panjang = 3;
                this.lebar = 3;
                this.harga = 50;
                this.aksi[0] = "Makan";
                break;
            case "Jam":
                this.nama = "Jam";
                this.panjang = 1;
                this.lebar = 1;
                this.harga = 10;
                this.aksi[0] = "Melihat Waktu";
                break;
            case "Komputer":
                this.nama = "Komputer";
                this.panjang = 3;
                this.lebar = 3;
                this.harga = 250;
                this.aksi[0] = "Bermain Game";
                this.aksi[1] = "Belajar Coding";
                this.aksi[2] = "Buka Sosmed";
                this.aksi[3] = "Ikut Undian Berhadiah";
                break;
            case "TV":
                this.nama = "TV";
                this.panjang = 3;
                this.lebar = 2;
                this.harga = 100;
                this.aksi[0] = "Nonton Netflix";
                break;
            case "Shower":
                this.nama = "Shower";
                this.panjang = 2;
                this.lebar = 2;
                this.harga = 150;
                this.aksi[0] = "Mandi";
                break;
            default:
                throw new Exception("Furniture tidak tersedia!");
        }
    }
    public String getNama() {
        return nama;
    }

    public int getPanjang() {
        return panjang;
    }
    
    public int getLebar() {
        return lebar;
    }
    
    public int getHarga() {
        return harga;
    }
    
    public String[] getAksi() {
        return aksi;
    }

    public int getXFurniture() {
        return posisiFurniture.getX();
    }

    public int getYFurniture() {
        return posisiFurniture.getY();
    }

    public void setXFurniture(int x) {
        posisiFurniture.setX(x);
    }

    public void setYFurniture(int y) {
        posisiFurniture.setY(y);
    }

    public void rotateFurniture() {
        int temp = panjang;
        panjang = lebar;
        lebar = temp;
    }

    public boolean isNearSim(Point posisiSim) {
        return (Math.abs(posisiSim.getX() - posisiFurniture.getX()) <= 1 && Math.abs(posisiSim.getY() - posisiFurniture.getY()) <= 1);
    }
}