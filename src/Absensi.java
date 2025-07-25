import java.sql.*;  
import javax.swing.*;  
import javax.swing.table.*;  
import java.text.SimpleDateFormat;  
import com.toedter.calendar.JDateChooser;  
import java.util.Date;
import java.text.ParseException;

/**
 *
 * @author ASUS
 */
public class Absensi extends javax.swing.JFrame {
    public JDateChooser tgl; // Deklarasi variabel tgl
    
    private int getAutoIdAbsensi() {
        int id = 0;
        try {
            Connection conn = absensi.Koneksi.koneksiDB();
            Statement st = conn.createStatement();

            String sql = "SELECT MAX(id_absen) AS max_id FROM absen_pegawai";
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                id = rs.getInt("max_id");
            }

            rs.close();
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat mendapatkan ID otomatis: " + e.getMessage());
        }
        return id + 1; 
    }
    
    private void clearFields() {
        //idAbsensi.setText("");
        idAbsensi.setText(String.valueOf(getAutoIdAbsensi()));
        NamaPegawai.setSelectedIndex(0);
        idPegawai.setText("");
        status.setSelectedIndex(0);
        noHp.setText("");
        kalender.setDate(null);
        gender.setSelectedIndex(0);
    }
        
    public void tampil_data() {
        DefaultTableModel tabel = new DefaultTableModel();
        tabel.addColumn("Id Absensi");
        tabel.addColumn("Id Pegawai");
        tabel.addColumn("Nama Pegawai");
        tabel.addColumn("Jabatan");
        tabel.addColumn("Tanggal");
        tabel.addColumn("Status");
        tabel.addColumn("Gender");
        tabel.addColumn("No HP");

        try {
            java.sql.Connection conn = (java.sql.Connection) absensi.Koneksi.koneksiDB();
            String sql = "SELECT * FROM absen_pegawai";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String id_pegawai = rs.getString("id_pegawai");
                String sqlPegawai = "SELECT * FROM pegawai WHERE id_pegawai = ?";
                java.sql.PreparedStatement p = conn.prepareStatement(sqlPegawai);
                p.setString(1, id_pegawai);
                ResultSet r = p.executeQuery();  

                String nama_pegawai = "";
                String jabatan = "";
                String gender = "";
                String no_hp = "";

                if (r.next()) {
                    nama_pegawai = r.getString("nama_pegawai");
                    jabatan = r.getString("jabatan");
                    gender = r.getString("gender");
                    no_hp = r.getString("no_hp");
                } else {
                    JOptionPane.showMessageDialog(this, "Menu tidak ditemukan untuk ID Pegawai: " + id_pegawai);
                    continue;  
                }

                tabel.addRow(new Object[]{
                    rs.getString("id_absen"), 
                    rs.getString("id_pegawai"), 
                    nama_pegawai,
                    jabatan,
                    rs.getString("tanggal"), 
                    rs.getString("status"), 
                    gender,
                    no_hp,
                });
            }

            jTable.setModel(tabel);
            clearFields();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
   
    
    private void pegawaiCombo(){
        try {
            Connection c = absensi.Koneksi.koneksiDB();
            Statement s = c.createStatement();
            String sql = "SELECT * FROM pegawai";
            ResultSet r = s.executeQuery(sql);
            
            NamaPegawai.removeAllItems(); 
            while(r.next()){
                NamaPegawai.addItem(r.getString("nama_pegawai"));
            }
        } catch(SQLException e){
            System.out.println("Error Memuat Pegawai"+ e.getMessage());
        }
    }
    
    


    public Absensi() {
        initComponents(); 
        tgl = new JDateChooser(); 
        add(tgl); 
        tampil_data();
        clearFields();
        pegawaiCombo();
        getAutoIdAbsensi();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        idAbsensi = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tb_simpan = new javax.swing.JButton();
        tb_edit = new javax.swing.JButton();
        tb_hapus = new javax.swing.JButton();
        tb_batal = new javax.swing.JButton();
        tb_cari = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        cari = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        status = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        kalender = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        gender = new javax.swing.JComboBox<>();
        tanggal = new javax.swing.JLabel();
        noHp = new javax.swing.JTextField();
        NamaPegawai = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        idPegawai = new javax.swing.JTextField();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setBackground(java.awt.SystemColor.controlHighlight);
        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 48)); // NOI18N
        jLabel1.setText("ABSENSI PEGAWAI");

        jLabel2.setText("Id Absensi");

        idAbsensi.setBackground(new java.awt.Color(232, 232, 232));
        idAbsensi.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        idAbsensi.setDoubleBuffered(true);
        idAbsensi.setEnabled(false);
        idAbsensi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idAbsensiActionPerformed(evt);
            }
        });

        jLabel3.setText("Id Pegawai ");

        jLabel4.setText("Status");

        tb_simpan.setBackground(new java.awt.Color(165, 222, 255));
        tb_simpan.setText("Simpan");
        tb_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb_simpanActionPerformed(evt);
            }
        });

        tb_edit.setBackground(new java.awt.Color(165, 221, 255));
        tb_edit.setText("Edit");
        tb_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb_editActionPerformed(evt);
            }
        });

        tb_hapus.setBackground(new java.awt.Color(165, 222, 255));
        tb_hapus.setText("Hapus");
        tb_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb_hapusActionPerformed(evt);
            }
        });

        tb_batal.setBackground(new java.awt.Color(165, 222, 255));
        tb_batal.setText("Batal");
        tb_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb_batalActionPerformed(evt);
            }
        });

        tb_cari.setText("Cari");
        tb_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tb_cariActionPerformed(evt);
            }
        });

        jLabel6.setText("Cari");

        cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariActionPerformed(evt);
            }
        });

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMouseClicked(evt);
            }
        });
        jTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable);

        status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hadir", "Cuti", "Sakit", "Alpa" }));
        status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusActionPerformed(evt);
            }
        });

        jLabel8.setText("No. Hp");

        kalender.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                kalenderPropertyChange(evt);
            }
        });

        jLabel9.setText("Gender");

        gender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laki-Laki", "Perempuan" }));

        tanggal.setText("Tanggal");

        noHp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noHpActionPerformed(evt);
            }
        });

        NamaPegawai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {""}));
        NamaPegawai.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                NamaPegawaiItemStateChanged(evt);
            }
        });

        jLabel5.setText("Nama");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(idPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(41, 41, 41)
                                    .addComponent(gender, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(34, 34, 34)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(kalender, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(noHp, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(NamaPegawai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(idAbsensi))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tb_simpan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tb_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(tb_hapus)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tb_batal)))
                        .addGap(132, 132, 132)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tb_cari))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(274, 274, 274)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 665, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(862, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(idAbsensi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(tb_cari)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NamaPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(idPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(noHp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(kalender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tanggal)
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(gender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tb_simpan)
                            .addComponent(tb_edit)
                            .addComponent(tb_hapus)
                            .addComponent(tb_batal)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(184, 184, 184)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(273, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void idAbsensiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idAbsensiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idAbsensiActionPerformed

    private void tb_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_simpanActionPerformed
                                          
        try {
            String idabsensi = idAbsensi.getText();
            String namaPegawai = (String) NamaPegawai.getSelectedItem();
            String IdPegawai = idPegawai.getText();
            String statusSelected = (String) status.getSelectedItem();
            java.sql.Date tanggal = new java.sql.Date(kalender.getDate().getTime());

            if (idabsensi.isEmpty() || namaPegawai == null || statusSelected == null) {
                JOptionPane.showMessageDialog(null, "Data tidak boleh kosong");
                return;
            }

            String sqlInsert = "INSERT INTO absen_pegawai (id_absen, id_pegawai, status, tanggal) VALUES (?, ?, ?, ?)";
            java.sql.Connection conn = absensi.Koneksi.koneksiDB();
            java.sql.PreparedStatement pstInsert = conn.prepareStatement(sqlInsert);

            pstInsert.setString(1, idabsensi);
            pstInsert.setString(2, IdPegawai);
            pstInsert.setString(3, statusSelected);
            pstInsert.setDate(4, tanggal);

            pstInsert.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan");

            // Jika status adalah "Hadir", tambahkan jumlah absen
            if (statusSelected.equalsIgnoreCase("Hadir")) {
                String sqlUpdate = "UPDATE pegawai SET total_absen = total_absen + 1 WHERE id_pegawai = ?";
                java.sql.PreparedStatement pstUpdate = conn.prepareStatement(sqlUpdate);

                pstUpdate.setString(1, IdPegawai);

                int rowsUpdated = pstUpdate.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "Jumlah absen berhasil diperbarui");
                } else {
                    JOptionPane.showMessageDialog(null, "Pegawai dengan ID " + IdPegawai + " tidak ditemukan");
                }

                pstUpdate.close();
            }

            tampil_data();

            pstInsert.close();
            pegawaiCombo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + e.getMessage());
            e.printStackTrace();
            clearFields();
        }
    }//GEN-LAST:event_tb_simpanActionPerformed

    private void tb_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_cariActionPerformed

    String keyword = cari.getText(); // Assuming searchTextField is already defined

    DefaultTableModel tabel = new DefaultTableModel();
    tabel.addColumn("Id Absensi");
    tabel.addColumn("Id Pegawai");
    tabel.addColumn("Nama Pegawai");
    tabel.addColumn("Jabatan");
    tabel.addColumn("Tanggal");
    tabel.addColumn("Status");
    tabel.addColumn("Gender");
    tabel.addColumn("No HP");

    try {
        java.sql.Connection conn = absensi.Koneksi.koneksiDB();
        String sql = "SELECT ap.*, p.nama_pegawai, p.jabatan, p.gender, p.no_hp " +
                     "FROM absen_pegawai ap " +
                     "JOIN pegawai p ON ap.id_pegawai = p.id_pegawai " +
                     "WHERE ap.id_absen LIKE ? OR " +
                     "ap.id_pegawai LIKE ? OR " +
                     "p.nama_pegawai LIKE ? OR " +
                     "p.jabatan LIKE ? OR " +
                     "p.gender LIKE ? OR " +
                     "ap.status LIKE ? OR " +
                     "p.no_hp LIKE ? OR " + // Include no_hp
                     "ap.tanggal LIKE ?"; // Include tanggal in the search
        
        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
        String searchPattern = "%" + keyword + "%";
        for (int i = 1; i <= 8; i++) { // Updated to 8 to account for tanggal
            pst.setString(i, searchPattern);
        }

        java.sql.ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            tabel.addRow(new Object[]{
                rs.getString("id_absen"), 
                rs.getString("id_pegawai"), 
                rs.getString("nama_pegawai"),
                rs.getString("jabatan"),
                rs.getString("tanggal"), // Ensure tanggal is included in the row
                rs.getString("status"),
                rs.getString("gender"),
                rs.getString("no_hp"),
            });
        }

        jTable.setModel(tabel);
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
    }//GEN-LAST:event_tb_cariActionPerformed

    private void statusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statusActionPerformed

    private void kalenderPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_kalenderPropertyChange
        if (kalender.getDate() != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String tgl_lahir = format.format(kalender.getDate());
            System.out.println("Tanggal: " + kalender); 
        }
    }//GEN-LAST:event_kalenderPropertyChange

    private void tb_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_editActionPerformed
                                        
        try {
            String idabsensi = idAbsensi.getText();  
            String namaPegawai = (String) NamaPegawai.getSelectedItem();
            String idpegawai = (String) idPegawai.getText();
            String statusSelected = (String) status.getSelectedItem();  
            java.sql.Date tanggal = new java.sql.Date(kalender.getDate().getTime());  

            java.sql.Connection conn = absensi.Koneksi.koneksiDB();

            String sqlCheck = "SELECT id_pegawai, status FROM absen_pegawai WHERE id_absen = ?";
            java.sql.PreparedStatement pstCheck = conn.prepareStatement(sqlCheck);
            pstCheck.setString(1, idabsensi);
            java.sql.ResultSet rs = pstCheck.executeQuery();

            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "ID Absensi tidak valid. Edit gagal.");
                return;
            }

            String currentStatus = rs.getString("status");
            String currentIdPegawai = rs.getString("id_pegawai");

            String sqlUpdate = "UPDATE absen_pegawai SET id_pegawai = ?, status = ?, tanggal = ? WHERE id_absen = ?";
            java.sql.PreparedStatement pstUpdate = conn.prepareStatement(sqlUpdate);

            pstUpdate.setString(1, idpegawai);
            pstUpdate.setString(2, statusSelected);
            pstUpdate.setDate(3, tanggal);
            pstUpdate.setString(4, idabsensi);
            pstUpdate.executeUpdate();

            // Update jumlah absensi jika status berubah
            if (!currentStatus.equalsIgnoreCase(statusSelected)) {
                if ("Hadir".equalsIgnoreCase(statusSelected)) {
                    String sqlIncrement = "UPDATE pegawai SET total_absen = total_absen + 1 WHERE id_pegawai = ?";
                    java.sql.PreparedStatement pstIncrement = conn.prepareStatement(sqlIncrement);
                    pstIncrement.setString(1, idpegawai);
                    pstIncrement.executeUpdate();
                    pstIncrement.close();
                } else if ("Hadir".equalsIgnoreCase(currentStatus)) {
                    String sqlDecrement = "UPDATE pegawai SET total_absen = total_absen - 1 WHERE id_pegawai = ?";
                    java.sql.PreparedStatement pstDecrement = conn.prepareStatement(sqlDecrement);
                    pstDecrement.setString(1, currentIdPegawai);
                    pstDecrement.executeUpdate();
                    pstDecrement.close();
                }
            }

            JOptionPane.showMessageDialog(null, "Data berhasil diedit");
            tampil_data();

            pstCheck.close();
            pstUpdate.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Proses Edit data Gagal");
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_tb_editActionPerformed

    private void tb_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_hapusActionPerformed

        try {
            java.sql.Connection conn = absensi.Koneksi.koneksiDB();

            String idabsensi = idAbsensi.getText().trim();
            if (idabsensi.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nomor tidak boleh kosong!");
                return;
            }

            // Query untuk mendapatkan status sebelum data dihapus
            String sqlGetStatus = "SELECT id_pegawai, status FROM absen_pegawai WHERE id_absen = ?";
            java.sql.PreparedStatement pstGetStatus = conn.prepareStatement(sqlGetStatus);
            pstGetStatus.setString(1, idabsensi);

            java.sql.ResultSet rs = pstGetStatus.executeQuery();
            String idPegawai = null;
            String status = null;

            if (rs.next()) {
                idPegawai = rs.getString("id_pegawai");
                status = rs.getString("status");
            } else {
                JOptionPane.showMessageDialog(null, "Data tidak ditemukan");
                return;
            }

            String sqlDelete = "DELETE FROM absen_pegawai WHERE id_absen = ?";
            java.sql.PreparedStatement pstDelete = conn.prepareStatement(sqlDelete);
            pstDelete.setString(1, idabsensi);

            int rowsAffected = pstDelete.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus");

                // Jika status adalah "Hadir", kurangi jumlah absen
                if ("Hadir".equalsIgnoreCase(status)) {
                    String sqlUpdate = "UPDATE pegawai SET total_absen = total_absen - 1 WHERE id_pegawai = ?";
                    java.sql.PreparedStatement pstUpdate = conn.prepareStatement(sqlUpdate);
                    pstUpdate.setString(1, idPegawai);

                    int rowsUpdated = pstUpdate.executeUpdate();
                    if (rowsUpdated > 0) {
                        JOptionPane.showMessageDialog(null, "Jumlah absen berhasil diperbarui");
                    } else {
                        JOptionPane.showMessageDialog(null, "Pegawai dengan ID " + idPegawai + " tidak ditemukan");
                    }
                    pstUpdate.close();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Data tidak ditemukan");
            }

            tampil_data();

            pstGetStatus.close();
            pstDelete.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Proses penghapusan gagal");
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }

    }//GEN-LAST:event_tb_hapusActionPerformed

    private void tb_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_batalActionPerformed
        clearFields();      
    }//GEN-LAST:event_tb_batalActionPerformed

    private void noHpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noHpActionPerformed

    }//GEN-LAST:event_noHpActionPerformed

    private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed
      
    }//GEN-LAST:event_cariActionPerformed

    private void jTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableKeyReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTableKeyReleased

    private void jTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMouseClicked
        // TODO add your handling code here:
        int selectedRow = jTable.getSelectedRow();
        if (selectedRow != -1){
            String id_absensi = jTable.getValueAt(selectedRow, 0).toString();
            String id_pegawai = jTable.getValueAt(selectedRow, 1).toString();
            String status_pegawai = jTable.getValueAt(selectedRow, 5).toString();
            String no_hp = jTable.getValueAt(selectedRow, 7).toString();
            String nama = jTable.getValueAt(selectedRow, 2).toString();
            String tanggal_absensi = jTable.getValueAt(selectedRow, 4).toString();
            String gender_pegawai = jTable.getValueAt(selectedRow, 6).toString();
            
            
            SimpleDateFormat sdfTanggal = new SimpleDateFormat("yyyy-MM-dd"); 
            
            try {
                Date date = sdfTanggal.parse(tanggal_absensi);

                kalender.setDate(date);

            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, "Format tanggal tidak sesuai!");
                e.printStackTrace();
            }
            
            idAbsensi.setText(id_absensi);
            NamaPegawai.setSelectedItem(nama);
            idPegawai.setText(id_pegawai);
            status.setSelectedItem(status_pegawai);
            noHp.setText(no_hp);
            gender.setSelectedItem(gender_pegawai);
        }
    }//GEN-LAST:event_jTableMouseClicked

    private void NamaPegawaiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_NamaPegawaiItemStateChanged
        // TODO add your handling code here:
        
        String Nama_Pegawai = (String) NamaPegawai.getSelectedItem();
        
        if (Nama_Pegawai == null || Nama_Pegawai.trim().isEmpty()) {
            System.out.println("Nama Pegawai belum dipilih.");
            return; 
        }
        
        try {
            Connection c = absensi.Koneksi.koneksiDB();
            Statement s = c.createStatement();
            String sql = "SELECT * FROM pegawai WHERE nama_pegawai = ?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, Nama_Pegawai);  
            ResultSet r = pst.executeQuery();
            if (r.next()) {
                idPegawai.setText(r.getString("id_pegawai"));
                noHp.setText(r.getString("no_hp"));
                gender.setSelectedItem(r.getString("gender"));
            } else {
                System.out.println("Pegawai dengan ID " + Nama_Pegawai + " tidak ditemukan (load nama pegawai).");
            }
        } catch(SQLException e){
            System.out.println("Error Memuat Pegawai" + e.getMessage());
        }
        
    }//GEN-LAST:event_NamaPegawaiItemStateChanged
    

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // Membuat objek Absensi dan menampilkannya
                new Absensi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> NamaPegawai;
    private javax.swing.JTextField cari;
    private javax.swing.JComboBox<String> gender;
    private javax.swing.JTextField idAbsensi;
    private javax.swing.JTextField idPegawai;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private com.toedter.calendar.JDateChooser kalender;
    private javax.swing.JTextField noHp;
    private javax.swing.JComboBox<String> status;
    private javax.swing.JLabel tanggal;
    private javax.swing.JButton tb_batal;
    private javax.swing.JButton tb_cari;
    private javax.swing.JButton tb_edit;
    private javax.swing.JButton tb_hapus;
    private javax.swing.JButton tb_simpan;
    // End of variables declaration//GEN-END:variables

}