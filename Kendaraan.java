public abstract class Kendaraan {
    private String kodeKendaraan;
    private String merk;
    private int tahun;
    private double hargaSewaPerHari;
    private String status;

    public Kendaraan(String kodeKendaraan, String merk, int tahun, double hargaSewaPerHari) {
        this.kodeKendaraan = kodeKendaraan;
        this.merk = merk;
        this.tahun = tahun;
        this.hargaSewaPerHari = hargaSewaPerHari;
        this.status = "TERSEDIA";
    }

    public String getKodeKendaraan() { return kodeKendaraan; }
    public String getMerk() { return merk; }
    public int getTahun() { return tahun; }
    public double getHargaSewaPerHari() { return hargaSewaPerHari; }
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public abstract String getJenisKendaraan();
    public abstract String getDetailKendaraan();

    public void tampilkanData() {
        System.out.println("Kode          : " + kodeKendaraan);
        System.out.println("Jenis         : " + getJenisKendaraan());
        System.out.println("Merk          : " + merk);
        System.out.println("Tahun         : " + tahun);
        System.out.printf("Harga/Hari    : Rp%,.0f%n", hargaSewaPerHari);
        System.out.println("Detail        : " + getDetailKendaraan());
        System.out.println("Status        : " + status);
    }
}
