interface Identitas {
    public void tampilkanNama();
    public void tampilkanUmur();
}

interface MakhlukHidup {
    public void makan();
    public void berjalan();
    public void bersuara();
}

class Hewan implements MakhlukHidup, Identitas {
    private String nama;
    private int umur;
    public Hewan(String nama, int umur){
        this.nama = nama;
        this.umur = umur;
    }
    @Override
    public void makan() {
        System.out.println("Makan pakai tangan dan mulut");
    }
    @Override
    public void berjalan() {
        System.out.println("Jalan pakai 4 kaki");
    }
    @Override
    public void bersuara() {
        System.out.println("Suaranya nggak jelas");
    }
    public void tampilkanNama (){}
    public void tampilkanUmur () {}
}

class Manusia implements MakhlukHidup, Identitas {
    private String nama;
    private int umur;

    public Manusia(String nama, int umur){
        this.nama = nama;
        this.umur = umur;
    }

    @Override
    public void makan() {
        System.out.println("Makan pakai sendok garpu");}
    @Override
    public void berjalan() {
        System.out.println("Jalan pakai dua kaki");}
    @Override
    public void bersuara() {
        System.out.println("Suaranya merdu");}
    @Override
    public void tampilkanNama() {
        System.out.println("Nama saya: " + this.nama);}
    @Override
    public void tampilkanUmur() {
        System.out.println("Umur saya: " + this.umur);}
}

public class interface2 {
    public static void main(String[] args) {
        Manusia manusia1 = new Manusia("Rayhan", 18);
        Hewan kucing1 = new Hewan("Belerang",2);
        manusia1.makan();
        kucing1.bersuara();
    }
}
