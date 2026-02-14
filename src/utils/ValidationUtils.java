package utils;

import javax.swing.JOptionPane;

/**
 * Utility class untuk validasi input
 */
public class ValidationUtils {
    
    /**
     * Validasi apakah string kosong atau null
     */
    public static boolean isEmpty(String text) {
        return text == null || text.trim().isEmpty();
    }
    
    /**
     * Validasi apakah string adalah angka
     */
    public static boolean isNumeric(String text) {
        if (isEmpty(text)) {
            return false;
        }
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    /**
     * Validasi input wajib diisi
     */
    public static boolean validateRequired(String value, String fieldName) {
        if (isEmpty(value)) {
            showError(fieldName + " tidak boleh kosong!");
            return false;
        }
        return true;
    }
    
    /**
     * Validasi input harus angka
     */
    public static boolean validateNumeric(String value, String fieldName) {
        if (!isNumeric(value)) {
            showError(fieldName + " harus berupa angka!");
            return false;
        }
        return true;
    }
    
    /**
     * Validasi angka harus positif
     */
    public static boolean validatePositive(int value, String fieldName) {
        if (value < 0) {
            showError(fieldName + " tidak boleh negatif!");
            return false;
        }
        return true;
    }
    
    /**
     * Tampilkan pesan error
     */
    public static void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Validasi Error", JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Tampilkan pesan sukses
     */
    public static void showSuccess(String message) {
        JOptionPane.showMessageDialog(null, message, "Sukses", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Tampilkan pesan warning
     */
    public static void showWarning(String message) {
        JOptionPane.showMessageDialog(null, message, "Peringatan", JOptionPane.WARNING_MESSAGE);
    }
    
    /**
     * Konfirmasi aksi
     */
    public static boolean confirmAction(String message) {
        int result = JOptionPane.showConfirmDialog(null, message, "Konfirmasi", 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return result == JOptionPane.YES_OPTION;
    }
}