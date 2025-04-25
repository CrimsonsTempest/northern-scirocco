import java.util.Scanner;

interface Colorable {
    public void howToColor();
}
interface Comparable {
    public void compareTo(Object obj);
}
class Rectangle implements Colorable, Comparable{ // lass rectanggle
    private String warna;
    private int kategori;
    public Rectangle() {
    }
    public Rectangle(String warna) {
        this.warna = warna;
    }
    public void howToColor() {
        if(this.warna == null){
            System.out.println("tidak ada warna, warna bangun kotak masih polos");
        } else {
            System.out.println("bangun kotak sudah diwarnai dengan warna "+this.warna);
        }
    }
    public void compareTo(Object obj) {
        this.kategori = (int) obj;
        if(this.kategori == 0) {
            System.out.println("ukuran cat yang cocok untuk bangun kotak dengan ukuran kategori " +this.kategori+" yaitu 2.5L" );
        } else {
            System.out.println("ukuran cat yang cocok untuk bangun kotak dengan ukuran kategori " +this.kategori+" yaitu 6.5L" );
        }
    }
}

public class interface1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Masukkan warna kotak (atau kosong jika tidak ingin memberi warna): ");
        String inputWarna = in.nextLine().trim();

        Rectangle kotak;
        if (inputWarna.isEmpty()) {
            kotak = new Rectangle();
        } else {
            kotak = new Rectangle(inputWarna);
        }

        String command = "";

        while (true) {
            System.out.println("\n===== Rectangle Menu =====");
            System.out.println("1 - Tampilkan warna kotak");
            System.out.println("2 - Tentukan kategori ukuran (0 untuk kecil, selain itu besar)");
            System.out.println("exit - Keluar dari program");
            System.out.print("Masukkan perintah: ");
            command = in.nextLine().trim();

            if (command.equalsIgnoreCase("exit")) {
                break;
            }

            if (command.equals("1")) {
                kotak.howToColor();
            } else if (command.equals("2")) {
                System.out.print("Masukkan nilai kategori (0 atau lainnya): ");
                String kategoriStr = in.nextLine().trim();
                boolean validNumber = true;
                for (int i = 0; i < kategoriStr.length(); i++) {
                    if (!Character.isDigit(kategoriStr.charAt(i))) {
                        validNumber = false;
                        break;
                    }
                }

                if (validNumber && !kategoriStr.isEmpty()) {
                    int kategori = Integer.parseInt(kategoriStr);
                    kotak.compareTo(kategori);
                } else {
                    System.out.println("Kategori harus berupa angka bulat positif.");
                }
            } else {
                System.out.println("Perintah tidak dikenali. Silakan coba lagi.");
            }
        }

        in.close();
        System.out.println("Program selesai.");
    }
}
