package khoaphd.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import khoaphd.dtos.UsersDTO;
import khoaphd.models.UsersDAO;
import khoaphd.models.UsersInProDAO;

/**
 *
 * @author KhoaPHD
 */
public class AdminJFrame extends javax.swing.JFrame {

    /**
     * Creates new form AdminJFrame
     */
    
    private String adminId;
    private UsersDAO userDAO;
    private UsersInProDAO usersInProDAO;
    private DefaultTableModel modelAll;
    private DefaultTableModel modelAdmin;
    private DefaultTableModel modelUser;
    
    public AdminJFrame() {
        initComponents();
    }
    
    public AdminJFrame(String id) {
        initComponents();
        setLocationRelativeTo(null);
        adminId = id;
        setTitle("Admin - " + adminId);
        lbWelcome.setText("Welcome Admin " + adminId);
        lbWelcome.setForeground(Color.RED);
        lbWelcome.setFont(new Font("Tahoma", Font.PLAIN, 20));
        userDAO = new UsersDAO();
        usersInProDAO = new UsersInProDAO();
        modelAll = (DefaultTableModel)tblAll.getModel();
        modelAdmin = (DefaultTableModel)tblAdmin.getModel();
        modelUser = (DefaultTableModel)tblUser.getModel();
        setModelForAll();
        setModelForAdmin();
        setModelForUser();
        setupNewMode();
    }
    
    private void setupNewMode() {
        btnInsert.setEnabled(true);
        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);
        btnResetPass.setEnabled(false);
    }
    
    private void setupEditMode() {
        String id = txtID.getText();
        String role = (String)cbRole.getSelectedItem();
        btnInsert.setEnabled(false);
        if (!id.equalsIgnoreCase(adminId) && role.equalsIgnoreCase("Admin")) {
            btnUpdate.setEnabled(false);
            btnDelete.setEnabled(false);
        } else {
            btnUpdate.setEnabled(true);
            btnDelete.setEnabled(true);
        }
        if (role.equalsIgnoreCase("Admin")) {
            btnResetPass.setEnabled(false);
        } else {
            btnResetPass.setEnabled(true);
        }
    }
    
    private void setModelForAll(){
        modelAll.setRowCount(0);
        Vector<UsersDTO> listAll = new Vector<>();
        try {
            listAll = userDAO.getAllUsersLong();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (UsersDTO dto : listAll) {
            modelAll.addRow(dto.toVectorLong());
        }
    }
    
    private void setModelForAdmin(){
        modelAdmin.setRowCount(0);
        Vector<UsersDTO> listAdmin = new Vector<>();
        try {
            listAdmin = userDAO.getUsersByRole("Admin");
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (UsersDTO dto : listAdmin) {
            modelAdmin.addRow(dto.toVectorLong());
        }
    }
    
    private void setModelForUser(){
        modelUser.setRowCount(0);
        Vector<UsersDTO> listUser = new Vector<>();
        try {
            listUser = userDAO.getUsersByRole("User");
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (UsersDTO dto : listUser) {
            modelUser.addRow(dto.toVectorLong());
        }
    }
    
    private void searchByLikeNameInAll(String nameSearch){
        modelAll.setRowCount(0);
        Vector<UsersDTO> listAll = new Vector<>();
        try {
            listAll = userDAO.getUsersByLikeName(nameSearch);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (UsersDTO dto : listAll) {
            modelAll.addRow(dto.toVectorLong());
        }
    }
    
    private void searchByLikeNameInAdmin(String nameSearch){
        modelAdmin.setRowCount(0);
        Vector<UsersDTO> listAdmin = new Vector<>();
        try {
            listAdmin = userDAO.getUsersByLikeNameAndRole(nameSearch, "Admin");
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (UsersDTO dto : listAdmin) {
            modelAdmin.addRow(dto.toVectorLong());
        }
    }
    
    private void searchByLikeNameInUser(String nameSearch){
        modelUser.setRowCount(0);
        Vector<UsersDTO> listUser = new Vector<>();
        try {
            listUser = userDAO.getUsersByLikeNameAndRole(nameSearch, "User");
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (UsersDTO dto : listUser) {
            modelUser.addRow(dto.toVectorLong());
        }
    }
    
    private boolean validateData() {
        String id = txtID.getText().trim().toUpperCase();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "ID is required");
            txtID.requestFocus();
            return false;
        }
         if (!id.matches("^U\\d{4}$")) {
            JOptionPane.showMessageDialog(this, "ID must have format like U0000");
            txtID.requestFocus();
            return false;
        }
        
        String password = new String(txtPassword.getPassword());
        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Password is required");
            txtPassword.requestFocus();
            return false;
        }
        
        String name = txtUsername.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name is required");
            txtUsername.requestFocus();
            return false;
        }
        
        String email = txtEmail.getText().trim().toLowerCase();
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Email is required");
            txtEmail.requestFocus();
            return false;
        }
        if (!email.matches("^[a-z][a-z0-9_\\-]{4,}@[a-z0-9]{2,}(\\.[a-z0-9]{2,3}){1,2}$")) {
            JOptionPane.showMessageDialog(this, "Email is invalid");
            txtEmail.requestFocus();
            return false;
        }
        
        String phone = txtPhone.getText().trim();
        if (phone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Phone is required");
            txtPhone.requestFocus();
            return false;
        }
        if (!phone.matches("^\\d{10}$")) {
            JOptionPane.showMessageDialog(this, "Phone must contain 10 digits");
            txtPhone.requestFocus();
            return false;
        }
        
        String photoName = txtPhotoName.getText().trim();
        if (photoName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Photo name is required");
            txtPhotoName.requestFocus();
            return false;
        }
        
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tpTables = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAll = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblAdmin = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblUser = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtPhotoName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cbRole = new javax.swing.JComboBox<>();
        btnNew = new javax.swing.JButton();
        btnInsert = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnResetPass = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        lbWelcome = new javax.swing.JLabel();
        btnPromotion = new javax.swing.JButton();
        btnChangePass = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblAll.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblAll.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Username", "Email", "Phone", "Photo Name", "Role"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAll.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblAll.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAllMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblAll);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 685, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
        );

        tpTables.addTab("All", jPanel1);

        tblAdmin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblAdmin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Username", "Email", "Phone", "Photo Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAdminMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblAdmin);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 685, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
        );

        tpTables.addTab("Admin", jPanel2);

        tblUser.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Username", "Email", "Phone", "Photo Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUserMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblUser);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 685, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
        );

        tpTables.addTab("User", jPanel3);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), java.awt.Color.blue)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("User ID:");

        txtID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtID.setPreferredSize(new java.awt.Dimension(6, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Password:");

        txtPassword.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPassword.setPreferredSize(new java.awt.Dimension(6, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Username:");

        txtUsername.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtUsername.setPreferredSize(new java.awt.Dimension(6, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Email:");

        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtEmail.setPreferredSize(new java.awt.Dimension(6, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Phone:");

        txtPhone.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPhone.setPreferredSize(new java.awt.Dimension(6, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Photo Name:");

        txtPhotoName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPhotoName.setPreferredSize(new java.awt.Dimension(6, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Role:");

        cbRole.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "User" }));
        cbRole.setPreferredSize(new java.awt.Dimension(66, 30));

        btnNew.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnInsert.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnInsert.setText("Insert");
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnResetPass.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnResetPass.setText("Reset password");
        btnResetPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetPassActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                                    .addComponent(txtUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(txtID, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtPhone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtPhotoName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbRole, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(btnUpdate)
                                .addGap(173, 173, 173))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(btnResetPass, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                            .addGap(60, 60, 60)
                            .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(40, 40, 40)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtEmail, txtID, txtPassword, txtPhone, txtUsername});

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnDelete, btnInsert, btnNew, btnUpdate});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtPhotoName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cbRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNew)
                    .addComponent(btnInsert))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete))
                .addGap(18, 18, 18)
                .addComponent(btnResetPass)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        txtSearch.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSearch.setPreferredSize(new java.awt.Dimension(6, 30));
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
        });

        btnSearch.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnReset.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Administrator", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), java.awt.Color.blue)); // NOI18N

        lbWelcome.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N

        btnPromotion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnPromotion.setText("View Promotion List");
        btnPromotion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPromotionActionPerformed(evt);
            }
        });

        btnChangePass.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnChangePass.setText("Change password");
        btnChangePass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangePassActionPerformed(evt);
            }
        });

        btnLogout.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbWelcome)
                .addGap(30, 30, 30)
                .addComponent(btnPromotion)
                .addGap(30, 30, 30)
                .addComponent(btnChangePass)
                .addGap(30, 30, 30)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbWelcome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnPromotion)
                        .addComponent(btnLogout)
                        .addComponent(btnChangePass)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tpTables, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(40, 40, 40)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearch)
                            .addComponent(btnReset))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tpTables))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnReset, btnSearch});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblAllMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAllMouseClicked
        int row = tblAll.getSelectedRow();
        String id = (String)tblAll.getValueAt(row, 0);
        UsersDTO dto = null;
        try {
            dto = userDAO.getUserByID(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        txtID.setText(id);
        txtPassword.setText("******");
        txtUsername.setText(dto.getUsername());
        txtEmail.setText(dto.getEmail());
        txtPhone.setText(dto.getPhone());
        txtPhotoName.setText(dto.getPhotoName());
        cbRole.setSelectedItem(dto.getRole());
        txtID.setEditable(false);
        txtPassword.setEditable(false);
        setupEditMode();
    }//GEN-LAST:event_tblAllMouseClicked

    private void tblAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAdminMouseClicked
        int row = tblAdmin.getSelectedRow();
        String id = (String)tblAdmin.getValueAt(row, 0);
        UsersDTO dto = null;
        try {
            dto = userDAO.getUserByID(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        txtID.setText(id);
        txtPassword.setText("******");
        txtUsername.setText(dto.getUsername());
        txtEmail.setText(dto.getEmail());
        txtPhone.setText(dto.getPhone());
        txtPhotoName.setText(dto.getPhotoName());
        cbRole.setSelectedItem(dto.getRole());
        txtID.setEditable(false);
        txtPassword.setEditable(false);
        setupEditMode();
    }//GEN-LAST:event_tblAdminMouseClicked

    private void tblUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUserMouseClicked
        int row = tblUser.getSelectedRow();
        String id = (String)tblUser.getValueAt(row, 0);
        UsersDTO dto = null;
        try {
            dto = userDAO.getUserByID(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        txtID.setText(id);
        txtPassword.setText("******");
        txtUsername.setText(dto.getUsername());
        txtEmail.setText(dto.getEmail());
        txtPhone.setText(dto.getPhone());
        txtPhotoName.setText(dto.getPhotoName());
        cbRole.setSelectedItem(dto.getRole());
        txtID.setEditable(false);
        txtPassword.setEditable(false);
        setupEditMode();
    }//GEN-LAST:event_tblUserMouseClicked

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        txtID.setEditable(true);
        txtID.setText("");
        txtPassword.setEditable(true);
        txtPassword.setText("");
        txtUsername.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        txtPhotoName.setText("");
        cbRole.setSelectedIndex(0);
        txtID.requestFocus();
        setupNewMode();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        if (!validateData()) {
            return;
        }
        int confirmation = JOptionPane.showConfirmDialog(this, "Do you want to insert this user?");
        if (confirmation != JOptionPane.YES_OPTION) {
            return;
        }
        String id = txtID.getText().trim().toUpperCase();
        String password = new String(txtPassword.getPassword());
        String name = txtUsername.getText().trim();
        String email = txtEmail.getText().trim().toLowerCase();
        String phone = txtPhone.getText().trim();
        String photoName = txtPhotoName.getText().trim();
        String role = (String)cbRole.getSelectedItem();
        UsersDTO dto = new UsersDTO(id, password, name, email, phone, photoName, role);
        boolean result = false;
        try {
            result = userDAO.insert(dto);
        } catch (Exception e) {
            if (e.getMessage().contains("duplicate")) {
                JOptionPane.showMessageDialog(this, "ID " + id + " is duplicated");
            } else {
                e.printStackTrace();
            }
        }
        if (result) {
            JOptionPane.showMessageDialog(this, "User ID " + id + " was inserted successfully");
            setModelForAll();
            setModelForAdmin();
            setModelForUser();
        } else {
            JOptionPane.showMessageDialog(this, "Insert user failed");
        }
    }//GEN-LAST:event_btnInsertActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if (!validateData()) {
            return;
        }
        
        String id = txtID.getText();
        int confirmation = JOptionPane.showConfirmDialog(this, "Do you want to update user ID " + id + "?");
        if (confirmation != JOptionPane.YES_OPTION) {
            return;
        }
        
        String name = txtUsername.getText().trim();
        String email = txtEmail.getText().trim().toLowerCase();
        String phone = txtPhone.getText().trim();
        String photoName = txtPhotoName.getText().trim();
        String role = (String)cbRole.getSelectedItem();
        UsersDTO dto = new UsersDTO(id, name, email, phone, photoName, role);
        boolean result = false;
        try {
            result = userDAO.update(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result) {
            JOptionPane.showMessageDialog(this, "User ID " + id + " was updated successfully");
            if (id.equals(adminId) && role.equals("User")) {
                JOptionPane.showMessageDialog(this, "You were set as user role. You are going to be logged out...");
                new LoginJFrame().setVisible(true);
                this.dispose();
            }
            setModelForAll();
            setModelForAdmin();
            setModelForUser();
            setupEditMode();
        } else {
            JOptionPane.showMessageDialog(this, "Update user failed");
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String nameSearch = txtSearch.getText().trim();
        searchByLikeNameInAll(nameSearch);
        searchByLikeNameInAdmin(nameSearch);
        searchByLikeNameInUser(nameSearch);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnSearchActionPerformed(null);
        }
    }//GEN-LAST:event_txtSearchKeyPressed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        txtSearch.setText("");
        setModelForAll();
        setModelForAdmin();
        setModelForUser();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        String id = txtID.getText();
        int confirmation = JOptionPane.showConfirmDialog(this, "Do you want to delete user ID " + id + "?");
        if (confirmation != JOptionPane.YES_OPTION) {
            return;
        }
        
        boolean result = false;
        try {
            usersInProDAO.deleteUserFromAllPromotions(id);
            result = userDAO.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result) {
            JOptionPane.showMessageDialog(this, "User id " + id + " was deleted successfully");
            if (id.equals(adminId)) {
                JOptionPane.showMessageDialog(this, "You were deleted. You are going to be logged out...");
                new LoginJFrame().setVisible(true);
                this.dispose();
            }
            setModelForAll();
            setModelForAdmin();
            setModelForUser();
            setupNewMode();
        } else {
            JOptionPane.showMessageDialog(this, "Delete user failed");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnResetPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetPassActionPerformed
        String id = txtID.getText();
        int confirmation = JOptionPane.showConfirmDialog(this, "Do you want to reset password of user ID " + id + "?");
        if (confirmation != JOptionPane.YES_OPTION) {
            return;
        }
        
        boolean result = false;
        try {
            result = userDAO.resetPass(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result) {
            JOptionPane.showMessageDialog(this, "User id " + id + " password was resetted successfully");
        } else {
            JOptionPane.showMessageDialog(this, "Reset password failed");
        }
    }//GEN-LAST:event_btnResetPassActionPerformed

    private void btnChangePassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangePassActionPerformed
        new ChangePassJFrame(adminId).setVisible(true);
    }//GEN-LAST:event_btnChangePassActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        int confirmation = JOptionPane.showConfirmDialog(this, "Do you want to log out?");
        if (confirmation == JOptionPane.YES_OPTION) {
            new LoginJFrame().setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnPromotionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPromotionActionPerformed
        new PromotionJFrame().setVisible(true);
    }//GEN-LAST:event_btnPromotionActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChangePass;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnPromotion;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnResetPass;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbRole;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbWelcome;
    private javax.swing.JTable tblAdmin;
    private javax.swing.JTable tblAll;
    private javax.swing.JTable tblUser;
    private javax.swing.JTabbedPane tpTables;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtID;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtPhotoName;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
