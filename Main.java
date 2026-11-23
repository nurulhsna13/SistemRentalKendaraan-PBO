import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final Scanner input = new Scanner(System.in);
    private static final ArrayList<Kendaraan> daftarKendaraan = new ArrayList<>();
    private static final ArrayList<Pelanggan> daftarPelanggan = new ArrayList<>();
    private static final ArrayList<Penyewaan> daftarPenyewaan = new ArrayList<>();
    private static int nomorSewa = 1;

    public static void main(String[] args) {
        isiDataAwal();

        int pilihan;
        do {
            System.out.println("\n==================================");
            System.out.println("     SISTEM RENTAL KENDARAAN");
            System.out.println("==================================");
            System.out.println("1. Tampilkan Data Pelanggan");
            System.out.println("2. Tampilkan Data Kendaraan");
            System.out.println("3. Sewa Kendaraan");
            System.out.println("4. Tampilkan Data Sewa");
            System.out.println("5. Kembalikan Kendaraan");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");

            pilihan = bacaAngka();

            switch (pilihan) {
                case 1 -> tampilkanPelanggan();
                case 2 -> tampilkanKendaraan();
                case 3 -> sewaKendaraan();
                case 4 -> tampilkanPenyewaan();
                case 5 -> kembalikanKendaraan();
                case 0 -> System.out.println("Terima kasih telah menggunakan sistem.");
                default -> System.out.println("Pilihan tidak tersedia.");
            }
        } while (pilihan != 0);
    }

    private static void isiDataAwal() {
        daftarKendaraan.add(new Mobil("M001", "Toyota Avanza", 2022, 350000, 4));
        daftarKendaraan.add(new Mobil("M002", "Honda Brio", 2021, 300000, 4));
        daftarKendaraan.add(new Motor("T001", "Honda Vario", 2023, 100000, "Matic"));
        daftarKendaraan.add(new Motor("T002", "Yamaha NMAX", 2022, 150000, "Matic"));

        daftarPelanggan.add(new Pelanggan("P001", "Andi", "Pekanbaru", "081234567890"));
        daftarPelanggan.add(new Pelanggan("P002", "Siti", "Pekanbaru", "081298765432"));
    }

    private static int bacaAngka() {
        while (!input.hasNextInt()) {
            System.out.print("Masukkan angka yang benar: ");
            input.next();
        }
        int angka = input.nextInt();
        input.nextLine();
        return angka;
    }

    private static void tampilkanPelanggan() {
        System.out.println("\n--- DATA PELANGGAN ---");
        for (Pelanggan pelanggan : daftarPelanggan) {
            pelanggan.tampilkanData();
            System.out.println("----------------------");
        }
    }

    private static void tampilkanKendaraan() {
        System.out.println("\n--- DATA KENDARAAN ---");
        for (Kendaraan kendaraan : daftarKendaraan) {
            kendaraan.tampilkanData();
            System.out.println("----------------------");
        }
    }

    private static void tampilkanPenyewaan() {
        System.out.println("\n--- DATA SEWA ---");
        if (daftarPenyewaan.isEmpty()) {
            System.out.println("Belum ada transaksi penyewaan.");
            return;
        }
        for (Penyewaan penyewaan : daftarPenyewaan) {
            penyewaan.tampilkanData();
            System.out.println("----------------------");
        }
    }

    private static void sewaKendaraan() {
        System.out.println("\n--- PROSES SEWA KENDARAAN ---");
        tampilkanPelanggan();
        System.out.print("Masukkan ID pelanggan: ");
        String idPelanggan = input.nextLine();
        Pelanggan pelanggan = cariPelanggan(idPelanggan);

        if (pelanggan == null) {
            System.out.println("Pelanggan tidak ditemukan.");
            return;
        }

        tampilkanKendaraan();
        System.out.print("Masukkan kode kendaraan: ");
        String kode = input.nextLine();
        Kendaraan kendaraan = cariKendaraan(kode);

        if (kendaraan == null) {
            System.out.println("Kendaraan tidak ditemukan.");
            return;
        }
        if (!kendaraan.getStatus().equals("TERSEDIA")) {
            System.out.println("Kendaraan sedang tidak tersedia.");
            return;
        }

        System.out.print("Lama sewa (hari): ");
        int lama = bacaAngka();
        if (lama <= 0) {
            System.out.println("Lama sewa harus lebih dari 0 hari.");
            return;
        }

        Penyewaan transaksi = new Penyewaan(
                String.format("S%03d", nomorSewa), pelanggan, kendaraan, lama);

        System.out.println("\nPilih metode pembayaran:");
        System.out.println("1. Tunai");
        System.out.println("2. Transfer Bank");
        System.out.println("3. QR Payment");
        System.out.print("Pilihan: ");
        int metode = bacaAngka();

        Pembayaran pembayaran;
        switch (metode) {
            case 1 -> pembayaran = new Tunai(input);
            case 2 -> pembayaran = new TransferBank(input);
            case 3 -> pembayaran = new QRPayment(input);
            default -> {
                kendaraan.setStatus("TERSEDIA");
                System.out.println("Metode pembayaran tidak tersedia. Penyewaan dibatalkan.");
                return;
            }
        }

        if (transaksi.prosesPembayaran(pembayaran)) {
            nomorSewa++;
            daftarPenyewaan.add(transaksi);
            System.out.println("Penyewaan berhasil dicatat.");
            transaksi.tampilkanData();
        } else {
            kendaraan.setStatus("TERSEDIA");
            System.out.println("Pembayaran gagal. Penyewaan dibatalkan.");
        }
    }

    private static void kembalikanKendaraan() {
        System.out.println("\n--- PENGEMBALIAN KENDARAAN ---");
        tampilkanPenyewaan();
        System.out.print("Masukkan kode sewa yang akan diselesaikan: ");
        String kode = input.nextLine();

        for (Penyewaan penyewaan : daftarPenyewaan) {
            if (penyewaan.getKodeSewa().equalsIgnoreCase(kode)
                    && penyewaan.getStatusSewa().equals("AKTIF")) {
                penyewaan.kembalikanKendaraan();
                System.out.println("Kendaraan berhasil dikembalikan.");
                return;
            }
        }
        System.out.println("Transaksi aktif tidak ditemukan.");
    }

    private static Pelanggan cariPelanggan(String id) {
        for (Pelanggan pelanggan : daftarPelanggan) {
            if (pelanggan.getIdPelanggan().equalsIgnoreCase(id)) return pelanggan;
        }
        return null;
    }

    private static Kendaraan cariKendaraan(String kode) {
        for (Kendaraan kendaraan : daftarKendaraan) {
            if (kendaraan.getKodeKendaraan().equalsIgnoreCase(kode)) return kendaraan;
        }
        return null;
    }
}
