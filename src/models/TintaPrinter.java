package models;

/**
 * Class TintaPrinter - turunan dari BarangATK
 * Menerapkan Inheritance dan Polymorphism
 */
public class TintaPrinter extends BarangATK {
    
    // Constructor
    public TintaPrinter(String kodeBarang, String namaBarang, int stok, String satuan, int stokMinimum) {
        super(kodeBarang, namaBarang, stok, satuan, stokMinimum);
    }
    
    // Implementasi abstract method (Polymorphism)
    @Override
    public String getKategori() {
        return "Tinta Printer";
    }
}