package database;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

/**
 * Kelas untuk inisialisasi database (auto create tables)
 */
public class DatabaseInitializer {
    
    /**
     * Membuat tabel-tabel yang diperlukan jika belum ada
     */
    public static void initializeTables() {
        Connection conn = DatabaseConnection.getConnection();
        
        if (conn == null) {
            System.err.println("✗ Tidak dapat inisialisasi tabel - koneksi gagal");
            return;
        }
        
        try (Statement stmt = conn.createStatement()) {
            
            // 1. Buat tabel barang
            String createBarangTable = """
                CREATE TABLE IF NOT EXISTS barang (
                    kode_barang VARCHAR(20) PRIMARY KEY,
                    nama_barang VARCHAR(100) NOT NULL,
                    kategori VARCHAR(50) NOT NULL,
                    stok INT NOT NULL DEFAULT 0,
                    satuan VARCHAR(20) NOT NULL,
                    stok_minimum INT NOT NULL DEFAULT 10
                )
                """;
            stmt.executeUpdate(createBarangTable);
            System.out.println("✓ Tabel 'barang' siap");
            
            // 2. Buat tabel users (untuk login)
            String createUsersTable = """
                CREATE TABLE IF NOT EXISTS users (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    username VARCHAR(50) UNIQUE NOT NULL,
                    password VARCHAR(100) NOT NULL,
                    role VARCHAR(20) NOT NULL
                )
                """;
            stmt.executeUpdate(createUsersTable);
            System.out.println("✓ Tabel 'users' siap");
            
            // 3. Insert default admin jika belum ada
            String insertDefaultAdmin = """
                INSERT IGNORE INTO users (username, password, role) 
                VALUES ('admin', 'admin123', 'Admin')
                """;
            stmt.executeUpdate(insertDefaultAdmin);
            System.out.println("✓ Default admin created (username: admin, password: admin123)");
            
            // 4. Insert sample data barang jika tabel kosong
            String checkData = "SELECT COUNT(*) FROM barang";
            var rs = stmt.executeQuery(checkData);
            rs.next();
            if (rs.getInt(1) == 0) {
                insertSampleData(stmt);
            }
            
            System.out.println("✓ Inisialisasi database selesai!");
            
        } catch (SQLException e) {
            System.err.println("✗ Error saat inisialisasi tabel");
            e.printStackTrace();
        }
    }
    
    /**
     * Insert sample data untuk testing
     */
    private static void insertSampleData(Statement stmt) throws SQLException {
        String[] sampleData = {
            "INSERT INTO barang VALUES ('BRG001', 'Kertas HVS A4 80gr', 'Kertas', 100, 'Rim', 20)",
            "INSERT INTO barang VALUES ('BRG002', 'Pulpen Standard Hitam', 'Pulpen', 50, 'Pcs', 15)",
            "INSERT INTO barang VALUES ('BRG003', 'Spidol Whiteboard Biru', 'Spidol', 30, 'Pcs', 10)",
            "INSERT INTO barang VALUES ('BRG004', 'Tinta Printer Canon Black', 'Tinta Printer', 5, 'Botol', 3)"
        };
        
        for (String sql : sampleData) {
            stmt.executeUpdate(sql);
        }
        System.out.println("✓ Sample data barang berhasil ditambahkan");
    }
}