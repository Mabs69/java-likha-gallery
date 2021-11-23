
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Francis
 */
public class userMainMenu extends javax.swing.JFrame {

    /**
     * Creates new form userMainMenu
     */
    public userMainMenu() {
        initComponents();
        this.setLocationRelativeTo(null);

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

        jLabelProfilePic = new javax.swing.JLabel();
        btnArtists = new javax.swing.JButton();
        btnGallery = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabelUsername = new javax.swing.JLabel();
        jButtonLogout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(650, 370));
        getContentPane().setLayout(null);

        jLabelProfilePic.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 24)); // NOI18N
        jLabelProfilePic.setText("PIC");
        jLabelProfilePic.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelProfilePic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelProfilePicMouseClicked(evt);
            }
        });
        getContentPane().add(jLabelProfilePic);
        jLabelProfilePic.setBounds(560, 10, 60, 50);

        btnArtists.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        btnArtists.setText("Artists");
        btnArtists.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArtistsActionPerformed(evt);
            }
        });
        getContentPane().add(btnArtists);
        btnArtists.setBounds(340, 158, 270, 110);

        btnGallery.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        btnGallery.setText("Gallery");
        btnGallery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGalleryActionPerformed(evt);
            }
        });
        getContentPane().add(btnGallery);
        btnGallery.setBounds(20, 160, 270, 110);

        jLabel2.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 24)); // NOI18N
        jLabel2.setText("Main Menu (User)");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(220, 70, 207, 40);

        jLabelUsername.setText("jLabel1");
        getContentPane().add(jLabelUsername);
        jLabelUsername.setBounds(480, 30, 60, 14);

        jButtonLogout.setText("Logout");
        jButtonLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLogoutActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonLogout);
        jButtonLogout.setBounds(10, 10, 70, 23);

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

    private void jLabelProfilePicMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelProfilePicMouseClicked
        userProfileView up = new userProfileView();
        up.setVisible(true);
        up.pack();
        up.setLocationRelativeTo(null);
        up.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jLabelProfilePicMouseClicked

    private void btnGalleryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGalleryActionPerformed
        allArtsView aav = new allArtsView();
        aav.setVisible(true);
        aav.pack();
        aav.setLocationRelativeTo(null);
        aav.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_btnGalleryActionPerformed

    private void btnArtistsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArtistsActionPerformed
       artistList al = new artistList();
       al.setVisible(true);
       al.pack();
       al.setLocationRelativeTo(null);
       al.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.dispose();
    }//GEN-LAST:event_btnArtistsActionPerformed

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
            java.util.logging.Logger.getLogger(userMainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(userMainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(userMainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(userMainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new userMainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnArtists;
    private javax.swing.JButton btnGallery;
    private javax.swing.JButton jButtonLogout;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelProfilePic;
    private javax.swing.JLabel jLabelUsername;
    // End of variables declaration//GEN-END:variables
}
