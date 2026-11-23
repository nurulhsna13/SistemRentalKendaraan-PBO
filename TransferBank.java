import java.util.Scanner;

public class TransferBank implements Pembayaran {
    private Scanner input;

    public TransferBank(Scanner input) {
        this.input = input;
    }

    @Override
    public boolean bayar(double jumlah) {
        System.out.printf("Total yang harus ditransfer : Rp%,.0f%n", jumlah);
        System.out.print("Nama bank pengirim          : ");
        String bank = input.nextLine().trim();
        System.out.print("Nomor rekening pengirim     : ");
        String rekening = input.nextLine().trim();

        if (bank.isEmpty() || !rekening.matches("\\d{6,}")) {
            System.out.println("Data bank atau rekening tidak valid. Transfer gagal.");
            return false;
        }
        System.out.printf("Transfer dari %s (rek. %s) sebesar Rp%,.0f berhasil diproses.%n",
                bank, rekening, jumlah);
        return true;
    }

    @Override
    public String getNamaMetode() { return "Transfer Bank"; }
}
