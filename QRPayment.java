import java.util.Scanner;

public class QRPayment implements Pembayaran {
    private static int nomorQR = 1;
    private Scanner input;

    public QRPayment(Scanner input) {
        this.input = input;
    }

    @Override
    public boolean bayar(double jumlah) {
        String kodeQR = String.format("QR-%04d", nomorQR++);
        System.out.printf("Kode QR   : %s%n", kodeQR);
        System.out.printf("Nominal   : Rp%,.0f%n", jumlah);
        System.out.print("Sudah scan dan bayar? (y/n): ");
        String jawab = input.nextLine().trim();

        if (!jawab.equalsIgnoreCase("y")) {
            System.out.println("Pembayaran QR belum dikonfirmasi. Pembayaran gagal.");
            return false;
        }
        System.out.printf("Pembayaran QR %s sebesar Rp%,.0f berhasil diproses.%n", kodeQR, jumlah);
        return true;
    }

    @Override
    public String getNamaMetode() { return "QR Payment"; }
}
