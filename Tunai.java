import java.util.Scanner;

public class Tunai implements Pembayaran {
    private Scanner input;

    public Tunai(Scanner input) {
        this.input = input;
    }

    @Override
    public boolean bayar(double jumlah) {
        System.out.printf("Total yang harus dibayar : Rp%,.0f%n", jumlah);
        System.out.print("Uang diterima            : Rp");
        double uang;
        try {
            uang = Double.parseDouble(input.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Jumlah uang tidak valid. Pembayaran tunai gagal.");
            return false;
        }
        if (uang < jumlah) {
            System.out.printf("Uang kurang Rp%,.0f. Pembayaran tunai gagal.%n", jumlah - uang);
            return false;
        }
        System.out.printf("Kembalian                : Rp%,.0f%n", uang - jumlah);
        System.out.println("Pembayaran tunai berhasil diproses.");
        return true;
    }

    @Override
    public String getNamaMetode() { return "Tunai"; }
}
