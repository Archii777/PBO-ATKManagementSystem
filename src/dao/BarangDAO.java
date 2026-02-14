package dao;

import database.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object untuk tabel barang
 * PENTING: Tidak return ResultSet langsung (gunakan List<Object[]>)
 */
public class BarangDAO {
    
    /**
     * Mendapatkan semua data barang
     * @return List<Object[]> - setiap Object[] berisi: [kode, nama, kategori, stok, satuan, stokMin]
     */
    public List<Object[]> getAllBarang() {
        List<Object[]> dataList = new ArrayList<>();
        String sql = "SELECT kode_barang, nama_barang, kategori, stok, satuan, stok_minimum FROM barang";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Object[] row = {
                    rs.getString("kode_barang"),
                    rs.getString("nama_barang"),
                    rs.getString("kategori"),
                    rs.getInt("stok"),
                    rs.getString("satuan"),
                    rs.getInt("stok_minimum")
                };
                dataList.add(row);
            }
            
        } catch (SQLException e) {
            System.err.println("Error getAllBarang: " + e.getMessage());
            e.printStackTrace();
        }
        
        return dataList;
    }
    
    /**
     * Menambah barang baru
     * @return true jika berhasil
     */
    public boolean insertBarang(String kode, String nama, String kategori, int stok, String satuan, int stokMin) {
        String sql = "INSERT INTO barang (kode_barang, nama_barang, kategori, stok, satuan, stok_minimum) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, kode);
            pstmt.setString(2, nama);
            pstmt.setString(3, kategori);
            pstmt.setInt(4, stok);
            pstmt.setString(5, satuan);
            pstmt.setInt(6, stokMin);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error insertBarang: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Update data barang
     * @return true jika berhasil
     */
    public boolean updateBarang(String kode, String nama, String kategori, int stok, String satuan, int stokMin) {
        String sql = "UPDATE barang SET nama_barang=?, kategori=?, stok=?, satuan=?, stok_minimum=? WHERE kode_barang=?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, nama);
            pstmt.setString(2, kategori);
            pstmt.setInt(3, stok);
            pstmt.setString(4, satuan);
            pstmt.setInt(5, stokMin);
            pstmt.setString(6, kode);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updateBarang: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Hapus barang
     * @return true jika berhasil
     */
    public boolean deleteBarang(String kode) {
        String sql = "DELETE FROM barang WHERE kode_barang=?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, kode);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleteBarang: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Cek apakah kode barang sudah ada
     */
    public boolean isKodeExists(String kode) {
        String sql = "SELECT COUNT(*) FROM barang WHERE kode_barang=?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, kode);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            
        } catch (SQLException e) {
            System.err.println("Error isKodeExists: " + e.getMessage());
        }
        
        return false;
    }
    
    /**
     * Mendapatkan barang dengan stok menipis (stok <= stok_minimum)
     */
    public List<Object[]> getBarangStokMenipis() {
        List<Object[]> dataList = new ArrayList<>();
        String sql = "SELECT kode_barang, nama_barang, kategori, stok, satuan, stok_minimum FROM barang WHERE stok <= stok_minimum";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Object[] row = {
                    rs.getString("kode_barang"),
                    rs.getString("nama_barang"),
                    rs.getString("kategori"),
                    rs.getInt("stok"),
                    rs.getString("satuan"),
                    rs.getInt("stok_minimum")
                };
                dataList.add(row);
            }
            
        } catch (SQLException e) {
            System.err.println("Error getBarangStokMenipis: " + e.getMessage());
            e.printStackTrace();
        }
        
        return dataList;
    }
}