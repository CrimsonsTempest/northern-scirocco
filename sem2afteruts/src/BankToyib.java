import java.io.*;
import java.util.*;

class InvalidFormatException extends Exception {
    public InvalidFormatException(String message) {
        super("ERROR: " + message);
    }
}

class BankFullException extends Exception {
    public BankFullException(String message) {
        super("ERROR: " + message);
    }
}

class DuplicateAccountException extends Exception {
    public DuplicateAccountException(String message) {
        super("ERROR: " + message);
    }
}

class InvalidAmountException extends Exception {
    public InvalidAmountException(String message) {
        super("ERROR: " + message);
    }
}

class DailyLimitExceededException extends Exception {
    public DailyLimitExceededException(String message) {
        super("ERROR: " + message);
    }
}

class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super("ERROR: " + message);
    }
}

class Account {
    private String number;
    private String name;
    private double balance;
    private int dailyTransactions;
    private final int MAX_DAILY_TRANSACTIONS = 3;
    private final double MAX_WITHDRAW = 5000000.0;

    public Account(String number, String name, double balance) throws InvalidAmountException {
        if (balance < 0) {
            throw new InvalidAmountException("InvalidAmountException - Saldo awal tidak boleh negatif");
        }
        this.number = number;
        this.name = name;
        this.balance = balance;
        this.dailyTransactions = 0;
    }

    public String getNumber() {
        return number;
    }

    public void deposit(double amount) throws InvalidAmountException, DailyLimitExceededException {
        if (dailyTransactions >= MAX_DAILY_TRANSACTIONS) {
            throw new DailyLimitExceededException("DailyLimitExceededException - Akun " + number + " telah mencapai batas 3 transaksi harian");
        }
        if (amount <= 0) {
            throw new InvalidAmountException("InvalidAmountException - Jumlah deposit harus lebih dari 0");
        }
        balance += amount;
        dailyTransactions++;
        System.out.printf("Deposit ke %s berhasil. Saldo sekarang: %.1f%n", number, balance);
    }

    public void withdraw(double amount) throws InvalidAmountException, DailyLimitExceededException, InsufficientFundsException {
        if (dailyTransactions >= MAX_DAILY_TRANSACTIONS) {
            throw new DailyLimitExceededException("DailyLimitExceededException - Akun " + number + " telah mencapai batas 3 transaksi harian");
        }
        if (amount <= 0) {
            throw new InvalidAmountException("InvalidAmountException - Jumlah penarikan harus lebih dari 0");
        }
        if (amount > MAX_WITHDRAW) {
            throw new InvalidAmountException("InvalidAmountException - Jumlah penarikan maksimal 5000000.0");
        }
        if (balance < amount) {
            throw new InsufficientFundsException("InsufficientFundsException - Saldo tidak mencukupi untuk penarikan sebesar " + amount);
        }
        balance -= amount;
        dailyTransactions++;
        System.out.printf("Penarikan dari %s berhasil. Saldo sekarang: %.1f%n", number, balance);
    }

    public void checkBalance() {
        System.out.printf("Saldo %s: %.1f%n", number, balance);
    }
}

public class BankToyib {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Account[] accounts = new Account[5];
        int accountCount = 0;

        while (true) {
            String[] input = in.nextLine().split(" ");

            try {
                int kodeOperasi = Integer.parseInt(input[0]);

                switch (kodeOperasi) {
                    case 1 -> {
                        if (input.length != 4) throw new InvalidFormatException("Format tidak valid untuk membuat akun");
                        String nomor = input[1];
                        String nama = input[2];
                        double saldoAwal;
                        try {
                            saldoAwal = Double.parseDouble(input[3]);
                        } catch (NumberFormatException e) {
                            throw new InvalidFormatException("Saldo awal harus berupa angka");
                        }
                        boolean duplicate = false;
                        for (int i = 0; i < accountCount; i++) {
                            if (accounts[i] != null && accounts[i].getNumber().equals(nomor)) {
                                duplicate = true;
                                break;
                            }
                        }
                        if (duplicate) throw new DuplicateAccountException("Nomor akun " + nomor + " sudah digunakan");
                        if (accountCount >= 5) throw new BankFullException("BankFullException - Bank telah mencapai batas maksimal 5 akun");

                        accounts[accountCount++] = new Account(nomor, nama, saldoAwal);
                        System.out.printf("Akun %s berhasil dibuat untuk %s dengan saldo %.1f%n", nomor, nama, saldoAwal);
                    }
                    case 2 -> {
                        if (input.length != 3) throw new InvalidFormatException("Format tidak valid untuk deposit");
                        String nomor = input[1];
                        double jumlah;

                        try {
                            jumlah = Double.parseDouble(input[2]);
                        } catch (NumberFormatException e) {
                            throw new InvalidFormatException("Jumlah deposit harus berupa angka");
                        }
                        boolean found = false;
                        for (int i = 0; i < accountCount; i++) {
                            if (accounts[i] != null && accounts[i].getNumber().equals(nomor)) {
                                found = true;
                                accounts[i].deposit(jumlah);
                                break;
                            }
                        }
                        if (!found) throw new InvalidFormatException("AccountNotFoundException - Akun " + nomor + " tidak ditemukan");
                    }
                    case 3 -> {
                        if (input.length != 3) throw new InvalidFormatException("Format tidak valid untuk penarikan");
                        String nomor = input[1];
                        double jumlah;

                        try {
                            jumlah = Double.parseDouble(input[2]);
                        } catch (NumberFormatException e) {
                            throw new InvalidFormatException("Jumlah penarikan harus berupa angka");
                        }
                        boolean found = false;
                        for (int i = 0; i < accountCount; i++) {
                            if (accounts[i] != null && accounts[i].getNumber().equals(nomor)) {
                                found = true;
                                accounts[i].withdraw(jumlah);
                                break;
                            }
                        }
                        if (!found) throw new InvalidFormatException("AccountNotFoundException - Akun " + nomor + " tidak ditemukan");
                    }
                    case 4 -> {
                        if (input.length != 2) throw new InvalidFormatException("Format tidak valid untuk cek saldo");
                        String nomor = input[1];
                        boolean found = false;
                        for (int i = 0; i < accountCount; i++) {
                            if (accounts[i] != null && accounts[i].getNumber().equals(nomor)) {
                                found = true;
                                accounts[i].checkBalance();
                                break;
                            }
                        }
                        if (!found) throw new InvalidFormatException("AccountNotFoundException - Akun " + nomor + " tidak ditemukan");
                    }
                    case 5 -> {
                        System.out.println("Program selesai!");
                        return;
                    }
                    default -> throw new InvalidFormatException("Kode operasi tidak valid");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
