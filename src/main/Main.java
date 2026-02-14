package main;

import database.DatabaseConnection;
import database.DatabaseInitializer;
import gui.LoginJFrameForm;
import javax.swing.UIManager;

/**
 * Main class - Entry point aplikasi
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("  SISTEM MANAJEMEN ATK");
        System.out.println("  Universitas XYZ - PBO Final Project");
        System.out.println("========================================\n");
        
        // 1. Test koneksi database
        System.out.println("[1/3] Testing database connection...");
        DatabaseConnection.testConnection();
        System.out.println();
        
        // 2. Inisialisasi database (create tables)
        System.out.println("[2/3] Initializing database tables...");
        DatabaseInitializer.initializeTables();
        System.out.println();
        
        // 3. Jalankan aplikasi GUI
        System.out.println("[3/3] Launching GUI application...");
        launchGUI();
        
        System.out.println("\n========================================");
        System.out.println("  Application started successfully!");
        System.out.println("========================================\n");
    }
    
    /**
     * Launch GUI dengan Look and Feel
     */
    private static void launchGUI() {
        try {
            // Set Nimbus Look and Feel
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    System.out.println("✓ Look and Feel: Nimbus");
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("! Failed to set Look and Feel, using default");
            e.printStackTrace();
        }
        
        // Launch Login Form
        java.awt.EventQueue.invokeLater(() -> {
            LoginJFrameForm loginForm = new LoginJFrameForm();
            loginForm.setVisible(true);
            System.out.println("✓ Login form displayed");
        });
    }
}