package models;

public class Kertas extends BarangATK {

    private String ukuran;

    public Kertas(String kodeBarang, String namaBarang, int stok, String satuan, int stokMinimum, String ukuran) {
        super(kodeBarang, namaBarang, stok, satuan, stokMinimum);
        this.ukuran = ukuran;
    }

    @Override
    public String getKategori() {
        return "Kertas";
    }

    public String getUkuran() {
        return ukuran;
    }

    public void setUkuran(String ukuran) {
        this.ukuran = ukuran;
    }
}
