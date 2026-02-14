package models;

/**
 * Abstract Class BarangATK
 * Menerapkan Abstraksi dan Enkapsulasi
 */
public abstract class BarangATK {

    protected String kodeBarang;
    protected String namaBarang;
    protected int stok;
    protected String satuan;
    protected int stokMinimum;

    // Constructor
    public BarangATK(String kodeBarang, String namaBarang, int stok, String satuan, int stokMinimum) {
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.stok = stok;
        this.satuan = satuan;
        this.stokMinimum = stokMinimum;
    }

    // Getter
    public String getKodeBarang() {
        return kodeBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public int getStok() {
        return stok;
    }

    public String getSatuan() {
        return satuan;
    }

    public int getStokMinimum() {
        return stokMinimum;
    }

    // Setter dengan validasi (Enkapsulasi)
    public void setStok(int stok) {
        if (stok < 0) {
            throw new IllegalArgumentException("Stok tidak boleh negatif!");
        }
        this.stok = stok;
    }

    // Method abstract (Polymorphism)
    public abstract String getKategori();

    @Override
    public String toString() {
        return kodeBarang + " - " + namaBarang + " (" + stok + " " + satuan + ")";
    }
}
