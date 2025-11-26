package Dashboard;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;
import myfurfamily_db.DatabaseConnection;

public class PetRecords extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PetRecords.class.getName());


    private DefaultTableModel model;


    public PetRecords() {
        initComponents();
        this.setLocationRelativeTo(null);
        
        model = new DefaultTableModel(new Object[]{"Pet ID", "Pet Name", "Species", "Breed", "Age"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Table read-only
            }
        };
        
        tbl_pets.setModel(model);
        tbl_pets.getColumnModel().getColumn(0).setMinWidth(0);
        tbl_pets.getColumnModel().getColumn(0).setMaxWidth(0);
        
        loadPetsFromDB();

        // Table row selection
        tbl_pets.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tbl_pets.getSelectedRow();
                if (row >= 0) {
                    txtPetName.setText(model.getValueAt(row, 1).toString());
                    txtSpecies.setText(model.getValueAt(row, 2).toString());
                    txtBreed.setText(model.getValueAt(row, 3).toString());
                    txtAge.setText(model.getValueAt(row, 4).toString());
                }
            }
        });
    }
    
     private void loadPetsFromDB() {
        model.setRowCount(0);
        String sql = "SELECT * FROM tbl_pets";
        try (Connection con = myfurfamily_db.DatabaseConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("pet_id"),
                        rs.getString("pet_name"),
                        rs.getString("species"),
                        rs.getString("breed"),
                        rs.getInt("age")
                });
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading pets: " + e.getMessage());
        }
    }

      
    private void clearFields() {
        txtPetName.setText("");
        txtSpecies.setText("");
        txtBreed.setText("");
        txtAge.setText("");
        tbl_pets.clearSelection();
    }
      

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PetRecords = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        logout_btn = new javax.swing.JButton();
        back_btn = new javax.swing.JButton();
        PetForm = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtPetName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtSpecies = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtAge = new javax.swing.JTextField();
        add_pet = new javax.swing.JButton();
        update_pet = new javax.swing.JButton();
        delete_pet = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtBreed = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_pets = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        jPanel2.setPreferredSize(new java.awt.Dimension(800, 500));

        logout_btn.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        logout_btn.setText("LOGOUT");
        logout_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logout_btnActionPerformed(evt);
            }
        });

        back_btn.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        back_btn.setText("BACK");
        back_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back_btnActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel1.setText("Pet Name:");

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel2.setText("Species:");

        txtSpecies.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSpeciesActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel3.setText("Breed:");

        txtAge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAgeActionPerformed(evt);
            }
        });

        add_pet.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        add_pet.setText("ADD");
        add_pet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_petActionPerformed(evt);
            }
        });

        update_pet.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        update_pet.setText("UPDATE");
        update_pet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_petActionPerformed(evt);
            }
        });

        delete_pet.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        delete_pet.setText("DELETE");
        delete_pet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_petActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel5.setText("Age:");

        javax.swing.GroupLayout PetFormLayout = new javax.swing.GroupLayout(PetForm);
        PetForm.setLayout(PetFormLayout);
        PetFormLayout.setHorizontalGroup(
            PetFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PetFormLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(PetFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PetFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1)
                        .addGroup(PetFormLayout.createSequentialGroup()
                            .addComponent(add_pet, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(update_pet, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(delete_pet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(txtPetName, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addGroup(PetFormLayout.createSequentialGroup()
                            .addGroup(PetFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtSpecies, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(PetFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(txtBreed, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        PetFormLayout.setVerticalGroup(
            PetFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PetFormLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPetName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PetFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PetFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSpecies, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBreed, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(142, 142, 142)
                .addGroup(PetFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add_pet, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(update_pet, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(delete_pet, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbl_pets.setBackground(java.awt.SystemColor.controlShadow);
        tbl_pets.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        tbl_pets.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "pet_id", "pet_name", "species", "breed", "age"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbl_pets);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(logout_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(back_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PetForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(back_btn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(logout_btn))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(PetForm, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PetRecordsLayout = new javax.swing.GroupLayout(PetRecords);
        PetRecords.setLayout(PetRecordsLayout);
        PetRecordsLayout.setHorizontalGroup(
            PetRecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 911, Short.MAX_VALUE)
            .addGroup(PetRecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 911, Short.MAX_VALUE))
        );
        PetRecordsLayout.setVerticalGroup(
            PetRecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
            .addGroup(PetRecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE))
        );

        getContentPane().add(PetRecords, new java.awt.GridBagConstraints());

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void add_petActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_petActionPerformed
       String name = txtPetName.getText().trim();
    String species = txtSpecies.getText().trim();
    String breed = txtBreed.getText().trim();
    String ageStr = txtAge.getText().trim();

    // Check empty fields
    if (name.isEmpty() || species.isEmpty() || breed.isEmpty() || ageStr.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Fill all fields!");
        return;
    }

    // Validate that name, species, and breed contain only letters and spaces
    if (!name.matches("[a-zA-Z ]+") || !species.matches("[a-zA-Z ]+") || !breed.matches("[a-zA-Z ]+")) {
        JOptionPane.showMessageDialog(this, "Pet Name, Species, and Breed must contain only letters!");
        return;
    }

    try {
        int age = Integer.parseInt(ageStr);
        String sql = "INSERT INTO tbl_pets (pet_name, species, breed, age) VALUES (?, ?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, name);
            pst.setString(2, species);
            pst.setString(3, breed);
            pst.setInt(4, age);
            pst.executeUpdate();
        }

        JOptionPane.showMessageDialog(this, "Pet added successfully!");
        clearFields();
        loadPetsFromDB();

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Age must be a number!");
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error adding pet: " + e.getMessage());
    }

    
    }//GEN-LAST:event_add_petActionPerformed

    private void update_petActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_petActionPerformed
        int row = tbl_pets.getSelectedRow();
        if (row < 0) { JOptionPane.showMessageDialog(this, "Select a pet!"); return; }

        int petId = (int) model.getValueAt(row, 0);
        String name = txtPetName.getText();
        String species = txtSpecies.getText();
        String breed = txtBreed.getText();
        int age = Integer.parseInt(txtAge.getText());

        String sql = "UPDATE tbl_pets SET pet_name=?, species=?, breed=?, age=? WHERE pet_id=?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, name);
            pst.setString(2, species);
            pst.setString(3, breed);
            pst.setInt(4, age);
            pst.setInt(5, petId);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Pet updated!");
            clearFields();
            loadPetsFromDB();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error updating pet: " + e.getMessage());
        }
    
    }//GEN-LAST:event_update_petActionPerformed

    private void delete_petActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_petActionPerformed
      int row = tbl_pets.getSelectedRow();
        if (row < 0) { JOptionPane.showMessageDialog(this, "Select a pet!"); return; }

        int petId = (int) model.getValueAt(row, 0);
        String sql = "DELETE FROM tbl_pets WHERE pet_id=?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, petId);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Pet deleted!");
            clearFields();
            loadPetsFromDB();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error deleting pet: " + e.getMessage());
        }
    

    
    }//GEN-LAST:event_delete_petActionPerformed

    private void txtSpeciesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSpeciesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSpeciesActionPerformed

    private void logout_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logout_btnActionPerformed
    int choice = javax.swing.JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to logout?",
            "Logout",
            javax.swing.JOptionPane.YES_NO_OPTION
    );

    if (choice == javax.swing.JOptionPane.YES_OPTION) {
        // Open LoginForm again
        new Authentication.LoginForm().setVisible(true);

        // Close the Dashboard
        this.dispose();
    }   // TODO add your handling code here:
    }//GEN-LAST:event_logout_btnActionPerformed

    private void back_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back_btnActionPerformed
    new Dashboard().setVisible(true); // replace DashboardForm with your actual dashboard class
    this.dispose(); // Close current PetRecords window
       // TODO add your handling code here:
    }//GEN-LAST:event_back_btnActionPerformed

    private void txtAgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAgeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAgeActionPerformed
   
   public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new PetRecords().setVisible(true));
    }
 
   





    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PetForm;
    private javax.swing.JPanel PetRecords;
    private javax.swing.JButton add_pet;
    private javax.swing.JButton back_btn;
    private javax.swing.JButton delete_pet;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton logout_btn;
    private javax.swing.JTable tbl_pets;
    private javax.swing.JTextField txtAge;
    private javax.swing.JTextField txtBreed;
    private javax.swing.JTextField txtPetName;
    private javax.swing.JTextField txtSpecies;
    private javax.swing.JButton update_pet;
    // End of variables declaration//GEN-END:variables
}
