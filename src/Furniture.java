public class Furniture extends ObjectType<Furniture> implements Purchasable{
    private String nama;
    private int panjang;
    private int lebar;
    private int harga;
    private String[] aksi = new String[2];
    private Point posisiFurniture = new Point();
    //atribut nya gw ubah jadi private yakk
    
    public Furniture(String nama) throws Exception {
        super("Furniture");
        switch(nama) {
            case "Kasur Single":
                this.nama = "Kasur Single";
                panjang = 4;
                this.lebar = 1;
                this.harga = 50;
                this.aksi[0] = "Tidur";
                break;
            case "Kasur Queen Size":
                this.nama = "Kasur Queen Size";
                this.panjang = 4;
                this.lebar = 2;
                this.harga = 100;
                this.aksi[0] = "Tidur";
                break;
            case "Kasur King Size":
                this.nama = "Kasur King Size";
                this.panjang = 5;
                this.lebar = 2;
                this.harga = 150;
                this.aksi[0] = "Tidur";
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
                this.aksi[1] = "Belajar Coding";
                break;
            case "Jam":
                this.nama = "Jam";
                panjang = 1;
                lebar = 1;
                harga = 10;
                aksi[0] = "Melihat Waktu";
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