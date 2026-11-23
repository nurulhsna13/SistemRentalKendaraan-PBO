public class Mobil extends Kendaraan {
    private int jumlahPintu;

    public Mobil(String kodeKendaraan, String merk, int tahun,
                 double hargaSewaPerHari, int jumlahPintu) {
        super(kodeKendaraan, merk, tahun, hargaSewaPerHari);
        this.jumlahPintu = jumlahPintu;
    }

    public int getJumlahPintu() { return jumlahPintu; }

    @Override
    public String getJenisKendaraan() { return "Mobil"; }

    @Override
    public String getDetailKendaraan() {
        return jumlahPintu + " pintu";
    }
}
