package Dashboard;

import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */


/**
 *
 * @author Acer
 */
public class Medications extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Medications.class.getName());

    /**
     * Creates new form Medications
     */
    
     class PetItem {
        int id;
        String name;

        PetItem(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
    
     
    // VALIDATE IF STRING SHOULD BE NUMBER
private boolean isNumeric(String value) {
    if (value == null || value.isEmpty()) return false;
    try {
        Double.parseDouble(value);
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
}

// VALIDATE DATE (YYYY-MM-DD)
private boolean isValidDate(String date) {
    if (date == null || date.isEmpty()) return true; // allow empty
    try {
        java.time.LocalDate.parse(date);
        return true;
    } catch (Exception e) {
        return false;
    }
}



     
    private DefaultTableModel tableModel;
    // add these near your other fields (e.g. after `private DefaultTableModel tableModel;`)
    private final java.util.Map<String, Integer> petNameToId = new java.util.HashMap<>();
    private final java.util.Map<Integer, String> petIdToName = new java.util.HashMap<>();

    public Medications() {
        initComponents();
        this.setLocationRelativeTo(null);
        initTable();
        loadPets();
        loadMedications();
        addTableListener();
        

    }
 
     private void initTable() {
    tableModel = new DefaultTableModel(
        new String[]{"pet_id", "pet_name", "med_name", "dosage", "frequency", "start_date", "end_date"}, 0
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    tbl_medications.setModel(tableModel);

    // hide pet_id only
    tbl_medications.getColumnModel().getColumn(0).setMinWidth(0);
    tbl_medications.getColumnModel().getColumn(0).setMaxWidth(0);
}

    private void loadPets() {
    petComboBox.removeAllItems();
    petNameToId.clear();
    petIdToName.clear();

    try (Connection con = myfurfamily_db.DatabaseConnection.getConnection();
         Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT pet_id, pet_name FROM tbl_pets")) {

        while (rs.next()) {
            int id = rs.getInt("pet_id");
            String name = rs.getString("pet_name");

            petComboBox.addItem(name);
            petNameToId.put(name, id);
            petIdToName.put(id, name);
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error loading pets: " + e.getMessage());
    }
}

     
   
  
   private void loadMedications() {
    tableModel.setRowCount(0);

    try (Connection con = myfurfamily_db.DatabaseConnection.getConnection();
         Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery(
            "SELECT m.pet_id, p.pet_name, m.med_name, m.dosage, m.frequency, m.start_date, m.end_date " +
            "FROM tbl_medications m " +
            "JOIN tbl_pets p ON m.pet_id = p.pet_id")) {

        while (rs.next()) {
            tableModel.addRow(new Object[]{
                rs.getInt("pet_id"),
                rs.getString("pet_name"),
                rs.getString("med_name"),
                rs.getString("dosage"),
                rs.getString("frequency"),
                rs.getString("start_date"),
                rs.getString("end_date")
            });
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error loading medications: " + e.getMessage());
    }
}



    

    
    private void clearFields() {
        medname_txtbox.setText("");
        species.setText("");
        breed.setText("");
        start_datetxtbox.setText("");
        end_datetxtbox.setText("");
       
        if (petComboBox.getItemCount() > 0) petComboBox.setSelectedIndex(0);
    }

    private void addTableListener() {
    tbl_medications.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            int row = tbl_medications.getSelectedRow();
            if (row < 0) return;

            // get petId from the table model (column 0 in model)
            int petId = (int) tableModel.getValueAt(row, 0);
String petName = (String) tableModel.getValueAt(row, 1);

petComboBox.setSelectedItem(petName);

medname_txtbox.setText((String) tableModel.getValueAt(row, 2));
species.setText((String) tableModel.getValueAt(row, 3));
breed.setText((String) tableModel.getValueAt(row, 4));
start_datetxtbox.setText((String) tableModel.getValueAt(row, 5));
end_datetxtbox.setText((String) tableModel.getValueAt(row, 6));

            
        }
    });
}



               
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        Medications = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        logout_btn = new javax.swing.JButton();
        back_btn = new javax.swing.JButton();
        MedicationsForm = new javax.swing.JPanel();
        mednametxt = new javax.swing.JLabel();
        medname_txtbox = new javax.swing.JTextField();
        dosagetxt = new javax.swing.JLabel();
        species = new javax.swing.JTextField();
        frequencytxt = new javax.swing.JLabel();
        add_pet = new javax.swing.JButton();
        update_pet = new javax.swing.JButton();
        delete_pet = new javax.swing.JButton();
        breed = new javax.swing.JTextField();
        start_datetxt = new javax.swing.JLabel();
        start_datetxtbox = new javax.swing.JTextField();
        end_datetxtbox = new javax.swing.JTextField();
        end_datetxt = new javax.swing.JLabel();
        petComboBox = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_medications = new javax.swing.JTable();

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

        MedicationsForm.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        mednametxt.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        mednametxt.setText("Medicine Name:");

        dosagetxt.setText("Dosage:");

        frequencytxt.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        frequencytxt.setText("Frequency:");

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

        start_datetxt.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        start_datetxt.setText("Medication Start:");

        end_datetxt.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        end_datetxt.setText("Medication End:");

        petComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout MedicationsFormLayout = new javax.swing.GroupLayout(MedicationsForm);
        MedicationsForm.setLayout(MedicationsFormLayout);
        MedicationsFormLayout.setHorizontalGroup(
            MedicationsFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MedicationsFormLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(MedicationsFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MedicationsFormLayout.createSequentialGroup()
                        .addComponent(start_datetxtbox, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(end_datetxtbox, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(MedicationsFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(mednametxt)
                        .addGroup(MedicationsFormLayout.createSequentialGroup()
                            .addComponent(add_pet, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(update_pet, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(delete_pet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(medname_txtbox, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(MedicationsFormLayout.createSequentialGroup()
                            .addGroup(MedicationsFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(species, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(dosagetxt)
                                .addComponent(start_datetxt))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(MedicationsFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(end_datetxt)
                                .addComponent(frequencytxt)
                                .addComponent(breed, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(petComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        MedicationsFormLayout.setVerticalGroup(
            MedicationsFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MedicationsFormLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(petComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mednametxt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(medname_txtbox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(MedicationsFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dosagetxt)
                    .addComponent(frequencytxt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MedicationsFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(species, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(breed, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(MedicationsFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(start_datetxt)
                    .addComponent(end_datetxt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MedicationsFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(start_datetxtbox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(end_datetxtbox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(133, 133, 133)
                .addGroup(MedicationsFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add_pet, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(update_pet, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(delete_pet, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        tbl_medications.setBackground(java.awt.SystemColor.controlShadow);
        tbl_medications.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        tbl_medications.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "pet_id", "pet_name", "med_name", "dosage", "frequency", "start_date", "end_date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbl_medications);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(logout_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(back_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(MedicationsForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
                    .addComponent(MedicationsForm, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout MedicationsLayout = new javax.swing.GroupLayout(Medications);
        Medications.setLayout(MedicationsLayout);
        MedicationsLayout.setHorizontalGroup(
            MedicationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 911, Short.MAX_VALUE)
            .addGroup(MedicationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 911, Short.MAX_VALUE))
        );
        MedicationsLayout.setVerticalGroup(
            MedicationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
            .addGroup(MedicationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(MedicationsLayout.createSequentialGroup()
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        getContentPane().add(Medications, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void delete_petActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_petActionPerformed

     

    int row = tbl_medications.getSelectedRow();
    if (row < 0) {
        JOptionPane.showMessageDialog(this, "Please select a record to delete.");
        return;
    }

    int petId = (int) tableModel.getValueAt(row, 0);
    String medName = (String) tableModel.getValueAt(row, 2);

    int confirm = JOptionPane.showConfirmDialog(
        this,
        "Are you sure you want to delete this medication?",
        "Confirm Delete",
        JOptionPane.YES_NO_OPTION
    );

    if (confirm != JOptionPane.YES_OPTION) return;

    try (Connection con = myfurfamily_db.DatabaseConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(
             "DELETE FROM tbl_medications WHERE pet_id=? AND med_name=?")) {

        ps.setInt(1, petId);
        ps.setString(2, medName);

        ps.executeUpdate();
        JOptionPane.showMessageDialog(this, "Medication deleted!");
        // After successfully deleting pet record
        

        loadMedications();
        clearFields();

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error deleting record: " + e.getMessage());
    }

        // TODO add your handling code here:
    }//GEN-LAST:event_delete_petActionPerformed

    private void update_petActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_petActionPerformed

        

    int row = tbl_medications.getSelectedRow();
    if (row < 0) {
        JOptionPane.showMessageDialog(this, "Please select a record to update.");
        return;
    }
     

    int oldPetId = (int) tableModel.getValueAt(row, 0);
    String oldMedName = (String) tableModel.getValueAt(row, 2);

    String newMedName = medname_txtbox.getText().trim();
    String dosage = species.getText().trim();
    String frequency = breed.getText().trim();
    String startDate = start_datetxtbox.getText().trim();
    String endDate = end_datetxtbox.getText().trim();
    if (newMedName.isEmpty()) {
        
    JOptionPane.showMessageDialog(this, "Medicine name cannot be empty.");
    return;
}

if (!isNumeric(dosage)) {
    JOptionPane.showMessageDialog(this, "Invalid dosage. Please enter a number.");
    return;
}

if (!isValidDate(startDate)) {
    JOptionPane.showMessageDialog(this, "Invalid start date! Use YYYY-MM-DD.");
    return;
}

if (!isValidDate(endDate)) {
    JOptionPane.showMessageDialog(this, "Invalid end date! Use YYYY-MM-DD.");
    return;
}

    String selectedPetName = (String) petComboBox.getSelectedItem();
    Integer newPetId = petNameToId.get(selectedPetName);

    try (Connection con = myfurfamily_db.DatabaseConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(
            "UPDATE tbl_medications SET pet_id=?, med_name=?, dosage=?, frequency=?, start_date=?, end_date=? " +
            "WHERE pet_id=? AND med_name=?")) {

        ps.setInt(1, newPetId);
        ps.setString(2, newMedName);
        ps.setString(3, dosage);
        ps.setString(4, frequency);
        ps.setString(5, startDate.isEmpty() ? null : startDate);
        ps.setString(6, endDate.isEmpty() ? null : endDate);

        ps.setInt(7, oldPetId);
        ps.setString(8, oldMedName);

        ps.executeUpdate();
        JOptionPane.showMessageDialog(this, "Record updated!");

        loadMedications();
        clearFields();

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error updating record: " + e.getMessage());
    }


            // TODO add your handling code here:
    }//GEN-LAST:event_update_petActionPerformed

    private void add_petActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_petActionPerformed
     
      

    String selectedPetName = (String) petComboBox.getSelectedItem();
    if (selectedPetName == null || selectedPetName.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please select a pet.");
        return;
    }

    Integer petId = petNameToId.get(selectedPetName);

    String medName = medname_txtbox.getText().trim();
    String dosage = species.getText().trim();
    String frequency = breed.getText().trim();
    String startDate = start_datetxtbox.getText().trim();
    String endDate = end_datetxtbox.getText().trim();

    if (medName.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Medicine name cannot be empty.");
        return;
    }
    if (!isNumeric(dosage)) {
    JOptionPane.showMessageDialog(this, "Invalid dosage. Please enter a number.");
    return;
}

// CHECK DATE FORMAT
if (!isValidDate(startDate)) {
    JOptionPane.showMessageDialog(this, "Invalid start date! Use YYYY-MM-DD.");
    return;
}

if (!isValidDate(endDate)) {
    JOptionPane.showMessageDialog(this, "Invalid end date! Use YYYY-MM-DD.");
    return;
}

    try (Connection con = myfurfamily_db.DatabaseConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(
            "INSERT INTO tbl_medications (pet_id, med_name, dosage, frequency, start_date, end_date) " +
            "VALUES (?, ?, ?, ?, ?, ?)")) {

        ps.setInt(1, petId);
        ps.setString(2, medName);
        ps.setString(3, dosage);
        ps.setString(4, frequency);
        ps.setString(5, startDate.isEmpty() ? null : startDate);
        ps.setString(6, endDate.isEmpty() ? null : endDate);

        ps.executeUpdate();
        JOptionPane.showMessageDialog(this, "Medication added!");

        loadMedications();
        clearFields();

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error adding record: " + e.getMessage());
    }


        // TODO add your handling code here:
    }//GEN-LAST:event_add_petActionPerformed

    private void back_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back_btnActionPerformed
    new Dashboard().setVisible(true); // replace DashboardForm with your actual dashboard class
    this.dispose();   // TODO add your handling code here:
    }//GEN-LAST:event_back_btnActionPerformed

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
    }    // TODO add your handling code here:
    }//GEN-LAST:event_logout_btnActionPerformed
     

              
            
        
    

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Medications().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Medications;
    private javax.swing.JPanel MedicationsForm;
    private javax.swing.JButton add_pet;
    private javax.swing.JButton back_btn;
    private javax.swing.JTextField breed;
    private javax.swing.JButton delete_pet;
    private javax.swing.JLabel dosagetxt;
    private javax.swing.JLabel end_datetxt;
    private javax.swing.JTextField end_datetxtbox;
    private javax.swing.JLabel frequencytxt;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton logout_btn;
    private javax.swing.JTextField medname_txtbox;
    private javax.swing.JLabel mednametxt;
    private javax.swing.JComboBox<String> petComboBox;
    private javax.swing.JTextField species;
    private javax.swing.JLabel start_datetxt;
    private javax.swing.JTextField start_datetxtbox;
    private javax.swing.JTable tbl_medications;
    private javax.swing.JButton update_pet;
    // End of variables declaration//GEN-END:variables
}
