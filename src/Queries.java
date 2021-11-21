
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Queries {
    
    public void insertRegister(String u, String p, String f, String l, String t, byte[] i) {
        Connection con = myConnection.getConnection();
            PreparedStatement ps;
            PreparedStatement ps2;

            try {
                ps = con.prepareStatement("INSERT INTO registration (`username`, `password`, `fname`, `lname`, `user_type`, `pic`) VALUES (?,?,?,?,?,?)");
                ps.setString(1, u);
                ps.setString(2, p);
                ps.setString(3, f);
                ps.setString(4, l);
                ps.setString(5, t);
                ps.setBytes(6, i);

                if(isUsernameExist(u)) {
                    JOptionPane.showMessageDialog(null, "Username already exists!");
                }
                else {

                    if(ps.executeUpdate() != 0) {
                        JOptionPane.showMessageDialog(null, "Account Created");
                        
                        if (t.equals("user")){
                            ps2 = con.prepareStatement("INSERT INTO visitor (user_id) SELECT user_id FROM registration WHERE username = ?");
                            ps2.setString(1, u);
                            ps2.executeUpdate();
                            
                        }
                        else if (t.equals("artist")){
                            ps2 = con.prepareStatement("INSERT INTO artist (user_id) SELECT user_id FROM registration WHERE username = ?");
                            ps2.setString(1, u);
                            ps2.executeUpdate();
                        } 
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Something Went Wrong");
                    }
                }

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void userLogin(String u, String p) {
        Connection con = myConnection.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        
        PreparedStatement ps1;
        ResultSet rs1;
        
        try {
            ps = con.prepareStatement("SELECT `username`, `password` FROM `registration` WHERE `username` = ? AND `password` = ?");
            ps.setString(1, u);
            ps.setString(2, p);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                ps1 = con.prepareStatement("SELECT `username`, `user_type` FROM `registration` WHERE `username` = ? AND `user_type` = ?");
                ps1.setString(1, u);
                ps1.setString(2, "artist");
                rs1 = ps1.executeQuery();
                
                if (rs1.next()){
                    artistMainMenu a = new artistMainMenu();
                    a.setVisible(true);
                    a.pack();
                    a.setLocationRelativeTo(null);
                    a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } else { 
                    userMainMenu mm = new userMainMenu();
                    mm.setVisible(true);
                    mm.pack();
                    mm.setLocationRelativeTo(null);
                    mm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid credentials.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean isUsernameExist(String un) {
        
        boolean uExist = false;
        Connection con = myConnection.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        
        try {
            ps = con.prepareStatement("SELECT * FROM registration WHERE `username` = ?");
            ps.setString(1, un);
            
            rs = ps.executeQuery();
            
            if(rs.next()) {
                uExist = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return uExist;
    
    }
}
