package models;

/**
 * Class AdminGA - turunan dari Anggota dengan hak akses penuh
 */
public class AdminGA extends Anggota {
    
    // Constructor
    public AdminGA(String username, String password) {
        super(username, password, "Admin");
    }
    
    /**
     * Method khusus untuk admin - validasi hak akses
     */
    public boolean hasFullAccess() {
        return "Admin".equals(getRole());
    }
}