package dao;

import database.DatabaseConnection;
import models.Anggota;
import models.AdminGA;
import java.sql.*;

/**
 * Data Access Object untuk tabel users (login)
 */
public class UserDAO {
    
    /**
     * Validasi login user
     * @return Anggota object jika berhasil, null jika gagal
     */
    public Anggota validateLogin(String username, String password) {
        String sql = "SELECT username, password, role FROM users WHERE username=? AND password=?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                String role = rs.getString("role");
                
                // Polymorphism: return AdminGA atau Anggota
                if ("Admin".equals(role)) {
                    return new AdminGA(username, password);
                } else {
                    return new Anggota(username, password, role);
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error validateLogin: " + e.getMessage());
            e.printStackTrace();
        }
        
        return null; // Login gagal
    }
    
    /**
     * Cek apakah username sudah ada
     */
    public boolean isUsernameExists(String username) {
        String sql = "SELECT COUNT(*) FROM users WHERE username=?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            
        } catch (SQLException e) {
            System.err.println("Error isUsernameExists: " + e.getMessage());
        }
        
        return false;
    }
}