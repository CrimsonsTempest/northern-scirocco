import java.util.*;
import java.lang.*;

class Tiket {
    private String id;
    private String kodeKursi;
    private String tanggal;
    private String type;
    private double price;

    public Tiket(String id, String kodeKursi, String tanggal) {
        this.id = id;
        this.kodeKursi = kodeKursi;
        this.tanggal = tanggal;
        this.type = "UNKNOWN";
        this.price = 0;

        this.setType();
    }

    private void setType() {
        if (!isValidKodeKursi(this.kodeKursi)) {
            this.type = "INVALID SEAT";
            return;
        }

        char baris = Character.toUpperCase(kodeKursi.charAt(0));
        int nomor = Integer.parseInt(kodeKursi.substring(1));

        if (baris == 'A' || baris == 'B') {
            this.type = "VIP";
            this.price = 1500000;
        } else if (baris >= 'C' && baris <= 'G') {
            this.type = "REG";
            this.price = 750000;
        }

        if (isEarlyBird(this.tanggal)) {
            this.price *= 0.76;
        }
    }

    private boolean isValidKodeKursi(String kode) {
        if (kode.length() < 2) return false;

        char baris = Character.toUpperCase(kode.charAt(0));
        String nomorStr = kode.substring(1);

        try {
            int nomor = Integer.parseInt(nomorStr);
            if (baris == 'A' || baris == 'B') {
                return nomor >= 1 && nomor <= 10;
            } else if (baris >= 'C' && baris <= 'G') {
                return nomor >= 1 && nomor <= 20;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isEarlyBird(String tanggal) {
        String[] parts = tanggal.split("-");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);

        if (month < 6) return true;
        if (month == 6 && day <= 19) return true;
        return false;
    }

    public String getType() {
        return this.type;
    }

    public String getId() {
        return this.id;
    }

    public String getKodeKursi() {
        return this.kodeKursi;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void printTicket() {
        if (type.equals("VIP") || type.equals("REG")) {
            System.out.printf("%s -> %s (%s) -> Rp%.2f\n", id, kodeKursi, type, price);
        } else {
            System.out.printf("%s -> %s -> %s\n", id, kodeKursi, type);
        }
    }
}

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        ArrayList<Tiket> ticketList = new ArrayList<>();

        System.out.println("-------- PRINT TRANSAKSI --------");

        for (int i = 0; i < n; i++) {
            String[] data = in.nextLine().split(" ");
            String id = data[0];
            String kodeKursi = data[1];
            String tanggal = data[2];

            Tiket tiketBaru = new Tiket(id, kodeKursi, tanggal);

            boolean pembeliSudahBeli = false;
            boolean kursiSudahTerjual = false;

            for (Tiket t : ticketList) {
                if (t.getId().equals(id)) {
                    pembeliSudahBeli = true;
                    break;
                }
                if (t.getKodeKursi().equals(kodeKursi)) {
                    kursiSudahTerjual = true;
                }
            }

            if (pembeliSudahBeli) {
                tiketBaru.setType("ALREADY PURCHASED");
                tiketBaru.printTicket();
                continue;
            }

            if (tiketBaru.getType().equals("INVALID SEAT")) {
                tiketBaru.printTicket();
                continue;
            }

            if (kursiSudahTerjual) {
                tiketBaru.setType("ALREADY SOLD");
                tiketBaru.printTicket();
                continue;
            }

            tiketBaru.printTicket();
            ticketList.add(tiketBaru);
        }
    }
}
