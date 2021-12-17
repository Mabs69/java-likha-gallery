
import java.awt.Color;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Keans Erol Austria
 */
public class artistMainMenu extends javax.swing.JFrame {

    ResultSet rs1;
    ResultSet rsA;
    
    public artistMainMenu() {
        initComponents();
        this.setLocationRelativeTo(null);
        jPanel1.setBackground(Color.white);
        ImageIcon myImg = new ImageIcon("logo.png");
        Image img = myImg.getImage().getScaledInstance(jLabel1.getWidth(), jLabel1.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon myPicture = new ImageIcon(img);
        jLabel1.setIcon(myPicture);
        
        Connection con = myConnection.getConnection();
        PreparedStatement ps; 
        ResultSet rs;
        
        try {
            ps = con.prepareStatement("select `pic` from registration where `username` = ?");
            ps.setString(1, Login.currentUsername);
            rs = ps.executeQuery();
            
            if(rs.next()) {
                jLabelProfilePic.setIcon(new uploadFunction().resizePic(null, rs.getBytes("pic"), jLabelProfilePic.getWidth(), jLabelProfilePic.getHeight()));
                jLabelUsername.setText(Login.currentUsername);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(userMainMenu.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabelProfilePic = new javax.swing.JLabel();
        btnMyGallery = new javax.swing.JButton();
        btnArtists = new javax.swing.JButton();
        btnAllGallery = new javax.swing.JButton();
        jLabelUsername = new javax.swing.JLabel();
        jButtonLogout = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Harrington", 1, 48)); // NOI18N
        jLabel2.setText("Main Menu (Artist)");

        jLabelProfilePic.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 24)); // NOI18N
        jLabelProfilePic.setText("PIC");
        jLabelProfilePic.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelProfilePic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelProfilePicMouseClicked(evt);
            }
        });

        btnMyGallery.setFont(new java.awt.Font("Harrington", 1, 24)); // NOI18N
        btnMyGallery.setText("My Gallery");
        btnMyGallery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMyGalleryActionPerformed(evt);
            }
        });

        btnArtists.setFont(new java.awt.Font("Harrington", 1, 24)); // NOI18N
        btnArtists.setText("Artists");
        btnArtists.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArtistsActionPerformed(evt);
            }
        });

        btnAllGallery.setFont(new java.awt.Font("Harrington", 1, 24)); // NOI18N
        btnAllGallery.setText("Gallery");
        btnAllGallery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAllGalleryActionPerformed(evt);
            }
        });

        jLabelUsername.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabelUsername.setText("Username");

        jButtonLogout.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonLogout.setText("Logout");
        jButtonLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLogoutActionPerformed(evt);
            }
        });

        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(217, 217, 217)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabelUsername))
                    .addComponent(jLabelProfilePic, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(281, 281, 281)
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(btnMyGallery, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                .addComponent(btnAllGallery, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addComponent(btnArtists, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(113, 113, 113))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelProfilePic, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelUsername))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(80, 80, 80)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnArtists, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAllGallery, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMyGallery, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(75, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLogoutActionPerformed
        Login.currentUsername = null;
        LoginRegister lr = new LoginRegister();
        lr.setVisible(true);
        lr.pack();
        lr.setLocationRelativeTo(null);
        lr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jButtonLogoutActionPerformed

    private void btnAllGalleryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAllGalleryActionPerformed
        
        if (checkArts()) {
            allArtsView aav = new allArtsView();
            aav.setVisible(true);
            aav.pack();
            aav.setLocationRelativeTo(null);
            aav.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "NO ARTS");
        }
        
    }//GEN-LAST:event_btnAllGalleryActionPerformed

    private boolean checkArts(){
        boolean c = false;
        
        Connection con = myConnection.getConnection(); 
        try {
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rsA = st.executeQuery("SELECT * FROM art");
     
            if(rsA.next()) {
                c = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(allArtsView.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        
        return c;
    }
    
    private void jLabelProfilePicMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelProfilePicMouseClicked
        ArtistProfile a = new ArtistProfile();
        this.dispose();
        this.pack();
        a.setVisible(true);
        a.setLocationRelativeTo(null);
        a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }//GEN-LAST:event_jLabelProfilePicMouseClicked

    private void btnArtistsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArtistsActionPerformed
       
       if (checkArtist()) {
            artistList al = new artistList();
            al.setVisible(true);
            al.pack();
            al.setLocationRelativeTo(null);
            al.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.dispose();
       } else {
           JOptionPane.showMessageDialog(null, "NO ARTIST");
       }
       
    }//GEN-LAST:event_btnArtistsActionPerformed

    private boolean checkArtist() {
        boolean c = false;
        
        Connection con = myConnection.getConnection(); 
        try {
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rsA = st.executeQuery("SELECT * FROM artist");
     
            if(rsA.next()) {
                c = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(allArtsView.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        
        return c;
    }
    
    private void btnMyGalleryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMyGalleryActionPerformed
        Queries q = new Queries();
        rs1 = q.getMyGallery(Login.currentArtistID);
        try {
            if(rs1.next()) 
                myGallery();
            else {
                int c = JOptionPane.showConfirmDialog(null, "You have no Art yet. Add art now?", "Art", JOptionPane.YES_NO_OPTION);
                if (c == JOptionPane.YES_OPTION) 
                    addGallery();
            }
        } catch (SQLException ex) {
            Logger.getLogger(artistMainMenu.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_btnMyGalleryActionPerformed

    public void myGallery() {
        ArtistGalleryView agv = new ArtistGalleryView();
        agv.setVisible(true);
        agv.pack();
        agv.setLocationRelativeTo(null);
        agv.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }
    
    public void addGallery() {
        addGallery al = new addGallery();
        al.setVisible(true);
        al.pack();
        al.setLocationRelativeTo(null);
        al.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }
    
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
            java.util.logging.Logger.getLogger(artistMainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(artistMainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(artistMainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(artistMainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new artistMainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAllGallery;
    private javax.swing.JButton btnArtists;
    private javax.swing.JButton btnMyGallery;
    private javax.swing.JButton jButtonLogout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelProfilePic;
    private javax.swing.JLabel jLabelUsername;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
