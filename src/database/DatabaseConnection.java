package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Kelas untuk mengelola koneksi database MariaDB
 */
public class DatabaseConnection {
    
    // Konfigurasi database
    private static final String URL = "jdbc:mariadb://localhost:3306/db_atk";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Laragon default (kosong)
    
    private static Connection connection = null;
    
    /**
     * Mendapatkan koneksi database (Singleton pattern)
     * @return Connection object
     */
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                // Load MariaDB driver
                Class.forName("org.mariadb.jdbc.Driver");
                
                // Buat koneksi baru
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✓ Koneksi database berhasil!");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("✗ Driver MariaDB tidak ditemukan!");
            System.err.println("Pastikan mariadb-java-client ada di Classpath (bukan Modulepath)");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("✗ Gagal koneksi ke database!");
            System.err.println("Pastikan Laragon sudah running dan database 'db_atk' sudah dibuat");
            e.printStackTrace();
        }
        return connection;
    }
    
    /**
     * Menutup koneksi database
     */
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("✓ Koneksi database ditutup");
            }
        } catch (SQLException e) {
            System.err.println("✗ Error saat menutup koneksi");
            e.printStackTrace();
        }
    }
    
    /**
     * Test koneksi database
     */
    public static void testConnection() {
        Connection conn = getConnection();
        if (conn != null) {
            System.out.println("✓ Test koneksi berhasil!");
            System.out.println("Database: " + URL);
        } else {
            System.out.println("✗ Test koneksi gagal!");
        }
    }
}