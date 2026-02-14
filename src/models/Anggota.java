package models;

/**
 * Class Anggota - representasi pengguna sistem
 */
public class Anggota {
    
    private String username;
    private String password;
    private String role;
    
    // Constructor
    public Anggota(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    
    // Getter dan Setter
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    /**
     * Method untuk validasi login
     */
    public boolean validatePassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }
    
    @Override
    public String toString() {
        return String.format("%s (%s)", username, role);
    }
}