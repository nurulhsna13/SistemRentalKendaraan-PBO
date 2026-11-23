public class Motor extends Kendaraan {
    private String tipeMotor;

    public Motor(String kodeKendaraan, String merk, int tahun,
                 double hargaSewaPerHari, String tipeMotor) {
        super(kodeKendaraan, merk, tahun, hargaSewaPerHari);
        this.tipeMotor = tipeMotor;
    }

    public String getTipeMotor() { return tipeMotor; }

    @Override
    public String getJenisKendaraan() { return "Motor"; }

    @Override
    public String getDetailKendaraan() {
        return "Tipe " + tipeMotor;
    }
}
