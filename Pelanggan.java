public class Pelanggan {
    private String idPelanggan;
    private String nama;
    private String alamat;
    private String nomorTelepon;

    public Pelanggan(String idPelanggan, String nama, String alamat, String nomorTelepon) {
        this.idPelanggan = idPelanggan;
        this.nama = nama;
        this.alamat = alamat;
        this.nomorTelepon = nomorTelepon;
    }

    public String getIdPelanggan() { return idPelanggan; }
    public String getNama() { return nama; }
    public String getAlamat() { return alamat; }
    public String getNomorTelepon() { return nomorTelepon; }

    public void tampilkanData() {
        System.out.println("ID Pelanggan  : " + idPelanggan);
        System.out.println("Nama          : " + nama);
        System.out.println("Alamat        : " + alamat);
        System.out.println("Nomor Telepon : " + nomorTelepon);
    }
}
