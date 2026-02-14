package gui;

import dao.BarangDAO;
import utils.ValidationUtils;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * Form Manajemen Barang ATK (CRUD)
 * PENTING: Komponen GUI sudah dibuat di NetBeans GUI Builder
 */
public class ManajemenBarangForm extends javax.swing.JFrame {

    private BarangDAO barangDAO;
    private DefaultTableModel tableModel;
    
    /**
     * Creates new form ManajemenBarangJFrameForm
     */
    public ManajemenBarangForm() {
        initComponents(); // AUTO-GENERATED
        setLocationRelativeTo(null);
        setTitle("Manajemen Barang ATK");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Inisialisasi DAO
        barangDAO = new BarangDAO();
        
        // Setup table
        setupTable();
        
        // Load data awal
        loadTable();
        
        // Setup combo box kategori
        setupComboBoxKategori();
    }
    
    /**
     * Setup struktur tabel
     */
    private void setupTable() {
        // Definisi kolom
        String[] columns = {"Kode Barang", "Nama Barang", "Kategori", "Stok", "Satuan", "Stok Min"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tidak bisa edit langsung di tabel
            }
        };
        
        tblBarang.setModel(tableModel);
        
        // Set lebar kolom
        tblBarang.getColumnModel().getColumn(0).setPreferredWidth(100);
        tblBarang.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblBarang.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblBarang.getColumnModel().getColumn(3).setPreferredWidth(60);
        tblBarang.getColumnModel().getColumn(4).setPreferredWidth(60);
        tblBarang.getColumnModel().getColumn(5).setPreferredWidth(80);
        
        // Add selection listener
        tblBarang.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                populateFormFromTable();
            }
        });
    }
    
    /**
     * Setup combo box kategori
     */
    private void setupComboBoxKategori() {
        cmbKategori.removeAllItems();
        cmbKategori.addItem("Kertas");
        cmbKategori.addItem("Pulpen");
        cmbKategori.addItem("Spidol");
        cmbKategori.addItem("Tinta Printer");
    }
    
    /**
     * Load data dari database ke tabel
     */
    private void loadTable() {
        // Clear table
        tableModel.setRowCount(0);
        
        // Get data dari DAO
        List<Object[]> dataList = barangDAO.getAllBarang();
        
        // Populate table
        for (Object[] row : dataList) {
            tableModel.addRow(row);
        }
        
        // Update status
        System.out.println("✓ Data loaded: " + dataList.size() + " barang");
    }
    
    /**
     * Populate form dari baris tabel yang dipilih
     */
    private void populateFormFromTable() {
        int selectedRow = tblBarang.getSelectedRow();
        
        if (selectedRow >= 0) {
            txtKode.setText(tableModel.getValueAt(selectedRow, 0).toString());
            txtNama.setText(tableModel.getValueAt(selectedRow, 1).toString());
            cmbKategori.setSelectedItem(tableModel.getValueAt(selectedRow, 2).toString());
            txtStok.setText(tableModel.getValueAt(selectedRow, 3).toString());
            txtSatuan.setText(tableModel.getValueAt(selectedRow, 4).toString());
            txtStokMin.setText(tableModel.getValueAt(selectedRow, 5).toString());
            
            // Disable kode saat edit
            txtKode.setEnabled(false);
        }
    }
    
    /**
     * Clear semua input field
     */
    private void clearForm() {
        txtKode.setText("");
        txtNama.setText("");
        txtStok.setText("");
        txtSatuan.setText("");
        txtStokMin.setText("");
        cmbKategori.setSelectedIndex(0);
        
        txtKode.setEnabled(true);
        txtKode.requestFocus();
        
        tblBarang.clearSelection();
    }
    
    /**
     * Validasi input form
     */
    private boolean validateInput() {
        // Validasi field wajib
        if (!ValidationUtils.validateRequired(txtKode.getText(), "Kode Barang")) {
            txtKode.requestFocus();
            return false;
        }
        
        if (!ValidationUtils.validateRequired(txtNama.getText(), "Nama Barang")) {
            txtNama.requestFocus();
            return false;
        }
        
        if (!ValidationUtils.validateRequired(txtStok.getText(), "Stok")) {
            txtStok.requestFocus();
            return false;
        }
        
        if (!ValidationUtils.validateRequired(txtSatuan.getText(), "Satuan")) {
            txtSatuan.requestFocus();
            return false;
        }
        
        if (!ValidationUtils.validateRequired(txtStokMin.getText(), "Stok Minimum")) {
            txtStokMin.requestFocus();
            return false;
        }
        
        // Validasi angka
        if (!ValidationUtils.validateNumeric(txtStok.getText(), "Stok")) {
            txtStok.requestFocus();
            return false;
        }
        
        if (!ValidationUtils.validateNumeric(txtStokMin.getText(), "Stok Minimum")) {
            txtStokMin.requestFocus();
            return false;
        }
        
        // Validasi positif
        int stok = Integer.parseInt(txtStok.getText());
        int stokMin = Integer.parseInt(txtStokMin.getText());
        
        if (!ValidationUtils.validatePositive(stok, "Stok")) {
            return false;
        }
        
        if (!ValidationUtils.validatePositive(stokMin, "Stok Minimum")) {
            return false;
        }
        
        return true;
    }
    
    /**
     * TAMBAH BARANG
     * PANGGIL dari btnTambahActionPerformed
     */
    private void tambahBarang() {
        // Validasi input
        if (!validateInput()) {
            return;
        }
        
        // Ambil data dari form
        String kode = txtKode.getText().trim();
        String nama = txtNama.getText().trim();
        String kategori = cmbKategori.getSelectedItem().toString();
        int stok = Integer.parseInt(txtStok.getText().trim());
        String satuan = txtSatuan.getText().trim();
        int stokMin = Integer.parseInt(txtStokMin.getText().trim());
        
        // Cek kode sudah ada atau belum
        if (barangDAO.isKodeExists(kode)) {
            ValidationUtils.showError("Kode barang '" + kode + "' sudah ada!");
            txtKode.requestFocus();
            return;
        }
        
        // Insert ke database
        boolean success = barangDAO.insertBarang(kode, nama, kategori, stok, satuan, stokMin);
        
        if (success) {
            ValidationUtils.showSuccess("Data barang berhasil ditambahkan!");
            clearForm();
            loadTable();
        } else {
            ValidationUtils.showError("Gagal menambahkan data barang!");
        }
    }
    
    /**
     * UPDATE BARANG
     * PANGGIL dari btnUpdateActionPerformed
     */
    private void updateBarang() {
        // Validasi ada data yang dipilih
        if (tblBarang.getSelectedRow() < 0) {
            ValidationUtils.showWarning("Pilih data yang akan diupdate dari tabel!");
            return;
        }
        
        // Validasi input
        if (!validateInput()) {
            return;
        }
        
        // Konfirmasi
        if (!ValidationUtils.confirmAction("Apakah Anda yakin ingin mengupdate data ini?")) {
            return;
        }
        
        // Ambil data dari form
        String kode = txtKode.getText().trim();
        String nama = txtNama.getText().trim();
        String kategori = cmbKategori.getSelectedItem().toString();
        int stok = Integer.parseInt(txtStok.getText().trim());
        String satuan = txtSatuan.getText().trim();
        int stokMin = Integer.parseInt(txtStokMin.getText().trim());
        
        // Update ke database
        boolean success = barangDAO.updateBarang(kode, nama, kategori, stok, satuan, stokMin);
        
        if (success) {
            ValidationUtils.showSuccess("Data barang berhasil diupdate!");
            clearForm();
            loadTable();
        } else {
            ValidationUtils.showError("Gagal mengupdate data barang!");
        }
    }
    
    /**
     * HAPUS BARANG
     * PANGGIL dari btnHapusActionPerformed
     */
    private void hapusBarang() {
        // Validasi ada data yang dipilih
        if (tblBarang.getSelectedRow() < 0) {
            ValidationUtils.showWarning("Pilih data yang akan dihapus dari tabel!");
            return;
        }
        
        // Ambil kode barang
        String kode = txtKode.getText().trim();
        String nama = txtNama.getText().trim();
        
        // Konfirmasi
        String message = String.format("Apakah Anda yakin ingin menghapus:\n%s - %s?", kode, nama);
        if (!ValidationUtils.confirmAction(message)) {
            return;
        }
        
        // Hapus dari database
        boolean success = barangDAO.deleteBarang(kode);
        
        if (success) {
            ValidationUtils.showSuccess("Data barang berhasil dihapus!");
            clearForm();
            loadTable();
        } else {
            ValidationUtils.showError("Gagal menghapus data barang!");
        }
    }
    
    /**
     * REFRESH TABLE
     * PANGGIL dari btnRefreshActionPerformed
     */
    private void refreshTable() {
        clearForm();
        loadTable();
        ValidationUtils.showSuccess("Data berhasil direfresh!");
    }

    /**
     * INSTRUKSI UNTUK NETBEANS GUI BUILDER:
     * 
     * Set event handler untuk button:
     * 
     * btnTambah → actionPerformed:
     *     private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {
     *         tambahBarang();
     *     }
     * 
     * btnUpdate → actionPerformed:
     *     private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {
     *         updateBarang();
     *     }
     * 
     * btnHapus → actionPerformed:
     *     private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {
     *         hapusBarang();
     *     }
     * 
     * btnRefresh → actionPerformed:
     *     private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {
     *         refreshTable();
     *     }
     */

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
        // AUTO-GENERATED BY NETBEANS
        // Komponen yang WAJIB ada (sesuai screenshot):
        // - txtKode (JTextField)
        // - txtNama (JTextField)
        // - txtStok (JTextField)
        // - txtSatuan (JTextField)
        // - txtStokMin (JTextField)
        // - cmbKategori (JComboBox)
        // - btnTambah (JButton)
        // - btnUpdate (JButton)
        // - btnHapus (JButton)
        // - btnRefresh (JButton)
        // - tblBarang (JTable) di dalam jScrollPane1
    }// </editor-fold>                        

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ManajemenBarangJFrameForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new ManajemenBarangJFrameForm().setVisible(true);
        });
    }

    // Variables declaration - AUTO-GENERATED                     
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JComboBox<String> cmbKategori;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblBarang;
    private javax.swing.JTextField txtKode;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtStok;
    private javax.swing.JTextField txtSatuan;
    private javax.swing.JTextField txtStokMin;
    // End of variables declaration                   
}