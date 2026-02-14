package models;

/**
 * Class Pulpen - turunan dari BarangATK
 * Menerapkan Inheritance dan Polymorphism
 */
public class Pulpen extends BarangATK {
    
    // Constructor
    public Pulpen(String kodeBarang, String namaBarang, int stok, String satuan, int stokMinimum) {
        super(kodeBarang, namaBarang, stok, satuan, stokMinimum);
    }
    
    // Implementasi abstract method (Polymorphism)
    @Override
    public String getKategori() {
        return "Pulpen";
    }
}