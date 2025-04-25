import java.util.Scanner;

interface Payable {
    int getPayableAmount();
}

class Invoice implements Payable {
    private String productName;
    private int quantity;
    private int pricePerItem;

    public Invoice(String productName, int quantity, int pricePerItem) {
        this.productName = productName;
        this.quantity = quantity;
        this.pricePerItem = pricePerItem;
    }

    @Override
    public int getPayableAmount() {
        return quantity * pricePerItem;
    }

    public void printDetail() {
        System.out.println("- " + productName + " (" + quantity + " x " + pricePerItem + ") = " + getPayableAmount());
    }
}

class Employee implements Payable {
    private int registrationNumber;
    private String name;
    private int salaryPerMonth;
    private Invoice[] invoices;

    public Employee(int registrationNumber, String name, int salaryPerMonth, Invoice[] invoices) {
        this.registrationNumber = registrationNumber;
        this.name = name;
        this.salaryPerMonth = salaryPerMonth;
        this.invoices = invoices;
    }

    @Override
    public int getPayableAmount() {
        int totalBelanja = 0;
        for (Invoice invoice : invoices) {
            totalBelanja += invoice.getPayableAmount();
        }
        return salaryPerMonth - totalBelanja;
    }

    public void printEmployeeInfo() {
        System.out.println("\n=== Informasi Karyawan ===");
        System.out.println("Nama: " + name);
        System.out.println("No. Registrasi: " + registrationNumber);
        System.out.println("Gaji Bulanan: " + salaryPerMonth);
        System.out.println("Total Potongan Belanja: " + (salaryPerMonth - getPayableAmount()));
        System.out.println("Gaji Bersih: " + getPayableAmount());
        System.out.println("\n=== Detail Belanja ===");
        for (Invoice invoice : invoices) {
            invoice.printDetail();
        }
    }
}

public class NVMeneer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Input Data Karyawan ===");
        System.out.print("Nama: ");
        String name = scanner.nextLine();

        System.out.print("No. Registrasi: ");
        int regNo = Integer.parseInt(scanner.nextLine());

        System.out.print("Gaji per bulan: ");
        int salary = Integer.parseInt(scanner.nextLine());

        System.out.print("Jumlah jenis barang yang dibeli: ");
        int jumlahBarang = Integer.parseInt(scanner.nextLine());
        Invoice[] invoices = new Invoice[jumlahBarang];

        for (int i = 0; i < jumlahBarang; i++) {
            System.out.println("\nBarang ke-" + (i + 1));
            System.out.print("Nama produk: ");
            String productName = scanner.nextLine();
            System.out.print("Jumlah: ");
            int qty = Integer.parseInt(scanner.nextLine());
            System.out.print("Harga per item: ");
            int price = Integer.parseInt(scanner.nextLine());
            invoices[i] = new Invoice(productName, qty, price);
        }

        Employee emp = new Employee(regNo, name, salary, invoices);
        emp.printEmployeeInfo();
    }
}
