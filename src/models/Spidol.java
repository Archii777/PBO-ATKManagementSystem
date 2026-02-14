package models;

/**
 * Class Spidol - turunan dari BarangATK
 * Menerapkan Inheritance dan Polymorphism
 */
public class Spidol extends BarangATK {
    
    // Constructor
    public Spidol(String kodeBarang, String namaBarang, int stok, String satuan, int stokMinimum) {
        super(kodeBarang, namaBarang, stok, satuan, stokMinimum);
    }
    
    // Implementasi abstract method (Polymorphism)
    @Override
    public String getKategori() {
        return "Spidol";
    }
}