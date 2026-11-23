public class Penyewaan {
    private String kodeSewa;
    private Pelanggan pelanggan;
    private Kendaraan kendaraan;
    private int lamaSewa;
    private double totalBiaya;
    private String statusSewa;
    private String metodeBayar;

    public Penyewaan(String kodeSewa, Pelanggan pelanggan, Kendaraan kendaraan, int lamaSewa) {
        this.kodeSewa = kodeSewa;
        this.pelanggan = pelanggan;
        this.kendaraan = kendaraan;
        this.lamaSewa = lamaSewa;
        this.totalBiaya = kendaraan.getHargaSewaPerHari() * lamaSewa;
        this.statusSewa = "AKTIF";
        this.metodeBayar = "-";
        kendaraan.setStatus("DISEWA");
    }

    public String getKodeSewa() { return kodeSewa; }
    public double getTotalBiaya() { return totalBiaya; }
    public String getStatusSewa() { return statusSewa; }
    public String getMetodeBayar() { return metodeBayar; }
    public Kendaraan getKendaraan() { return kendaraan; }

    public boolean prosesPembayaran(Pembayaran metodePembayaran) {
        boolean berhasil = metodePembayaran.bayar(totalBiaya);
        if (berhasil) {
            metodeBayar = metodePembayaran.getNamaMetode();
        }
        return berhasil;
    }

    public void kembalikanKendaraan() {
        kendaraan.setStatus("TERSEDIA");
        statusSewa = "SELESAI";
    }

    public void tampilkanData() {
        System.out.println("Kode Sewa     : " + kodeSewa);
        System.out.println("Pelanggan     : " + pelanggan.getNama());
        System.out.println("Kendaraan     : " + kendaraan.getJenisKendaraan()
                + " - " + kendaraan.getMerk() + " (" + kendaraan.getKodeKendaraan() + ")");
        System.out.println("Lama Sewa     : " + lamaSewa + " hari");
        System.out.printf("Total Biaya   : Rp%,.0f%n", totalBiaya);
        System.out.println("Metode Bayar  : " + metodeBayar);
        System.out.println("Status Sewa   : " + statusSewa);
    }
}
