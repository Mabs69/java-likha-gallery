
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
    
    public boolean userLogin(String u, String p) {
        Connection con = myConnection.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        
        PreparedStatement ps1;
        ResultSet rs1;
        
        boolean user = false;
        
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
                
                Login.currentUserID = getCurrentUserID(u);
                Login.currentArtistID = getCurrentArtistID(Login.currentUserID);

                if (rs1.next()){
                    artistMainMenu a = new artistMainMenu();
                    a.setVisible(true);
                    a.pack();
                    a.setLocationRelativeTo(null);
                    a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    user = true;
                } else { 
                    userMainMenu mm = new userMainMenu();
                    mm.setVisible(true);
                    mm.pack();
                    mm.setLocationRelativeTo(null);
                    mm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    user = true;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid credentials.");
                user = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return user;
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
    
    public String getUserType(String un) {
        String t = null;
        Connection con = myConnection.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        
        try {
            ps = con.prepareStatement("SELECT `user_type` FROM registration WHERE `username` = ?");
            ps.setString(1, un);
            
            rs = ps.executeQuery();
            
            if(rs.next()) {
                t = rs.getString("user_type");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return t;
    }
    
    public int getCurrentUserID(String un) {
        int u = 0;
        Connection con = myConnection.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        
        try {
            ps = con.prepareStatement("SELECT `user_id` FROM registration WHERE `username` = ?");
            ps.setString(1, un);
            
            rs = ps.executeQuery();
            
            if(rs.next()) {
                u = rs.getInt(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return u;
    }
    
    public int getCurrentArtistID(int uid) {
        int u = 0;
        Connection con = myConnection.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        
        try {
            ps = con.prepareStatement("SELECT artist_id FROM artist WHERE user_id = ?");
            ps.setInt(1, uid);
            
            rs = ps.executeQuery();
            
            if(rs.next()) {
                u = rs.getInt(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return u;
    }
    
    public void updateUserProfile(String f, String l, byte[] i, String u) {
        Connection con = myConnection.getConnection();
        PreparedStatement ps;
        
        try {
            ps = con.prepareStatement("UPDATE registration SET fname = ?, lname = ?, pic = ? WHERE username = ?");
            ps.setString(1, f);
            ps.setString(2, l);
            ps.setBytes(3, i);
            ps.setString(4, u);
            
            if(ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "Profile Updated");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
    }
    
    public void updateUserProfile2(String f, String l, String u) {
        Connection con = myConnection.getConnection();
        PreparedStatement ps;
        
        try {
            ps = con.prepareStatement("UPDATE registration SET fname = ?, lname = ? WHERE username = ?");
            ps.setString(1, f);
            ps.setString(2, l);
            ps.setString(3, u);
            
            if(ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "Profile Updated");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
    }
    
    public boolean verifyCurrentPassword(String u, String p) {
        Connection con = myConnection.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        
        boolean pass = false;
        
        try {
            ps = con.prepareStatement("SELECT `password` FROM registration WHERE `username` = ? and `password` = ?");
            ps.setString(1, u);
            ps.setString(2, p);
            rs = ps.executeQuery();
            
            if(rs.next())
                pass = true;
            else
                JOptionPane.showMessageDialog(null, "Invalid Current Password");
        } catch (SQLException ex) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
         return pass;
    }
    
    public void updatePassword(String u, String p) {
        Connection con = myConnection.getConnection();
        PreparedStatement ps;
        
        try {
            ps = con.prepareStatement("UPDATE registration SET password = ? WHERE `username` = ?");
            ps.setString(1, p);
            ps.setString(2, u);
            if(ps.executeUpdate() != 0)
                JOptionPane.showMessageDialog(null, "Password Updated");
        } catch (SQLException ex) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        
    }
    
    public byte[] getProfilePicture(int u) {
        byte[] path = null;
        Connection con = myConnection.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        
        try {
            ps = con.prepareStatement("SELECT `pic` FROM registration WHERE `user_id` = ?");
            ps.setInt(1, u);
            
            rs = ps.executeQuery();
            
            if(rs.next()) {
                path = rs.getBytes("pic");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return path;
    }
    
    public ResultSet getMyGallery(int aid) {
        Connection con = myConnection.getConnection();
        ResultSet rs = null;
        
        try {
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery("SELECT * FROM art WHERE artist_id = "+aid); 
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        return rs;
    }
    
    public void addGallery(String n, String d, byte[] img, int aid) {
        Connection con = myConnection.getConnection();
        PreparedStatement ps;
        
        try {
            ps = con.prepareStatement("INSERT INTO art (artist_id, art_name, art_desc, art_img) VALUES (?,?,?,?)");
            ps.setInt(1, aid);
            ps.setString(2, n);
            ps.setString(3, d);
            ps.setBytes(4, img);
            
            if(ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "Art Added to Your Gallery.");
                artistMainMenu a = new artistMainMenu();
                a.setVisible(true);
                a.pack();
                a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                a.setLocationRelativeTo(null);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editArt1(String n, String d, byte[] i, int aid, String cn) {
        Connection con = myConnection.getConnection();
        PreparedStatement ps;
        
        try {
            ps = con.prepareStatement("UPDATE art SET art_name = ?, art_desc = ?, art_img = ? WHERE artist_id = ? and art_name = ?");
            ps.setString(1, n);
            ps.setString(2, d);
            ps.setBytes(3, i);
            ps.setInt(4, aid);
            ps.setString(5, cn);
            
            if(ps.executeUpdate() != 0) 
                JOptionPane.showMessageDialog(null, "Art Name: "+cn+" Successfully Updated");          
        } catch (SQLException ex) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editArt2 (String n, String d, int aid, String cn) {
        Connection con = myConnection.getConnection();
        PreparedStatement ps;
        
        try {
            ps = con.prepareStatement("UPDATE art SET art_name = ?, art_desc = ? WHERE artist_id = ? and art_name = ?");
            ps.setString(1, n);
            ps.setString(2, d);
            ps.setInt(3, aid);
            ps.setString(4, cn);
            
            if(ps.executeUpdate() != 0) 
                JOptionPane.showMessageDialog(null, "Art Name: "+cn+" Successfully Updated");          
        } catch (SQLException ex) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteArt(String n, int aid) {
        Connection con = myConnection.getConnection();
        PreparedStatement ps;
        
        try {
            ps = con.prepareStatement("DELETE FROM art WHERE art_name = ? and artist_id = ?");
            ps.setString(1, n);
            ps.setInt(2, aid);
            
            if(ps.executeUpdate() != 0)
                JOptionPane.showMessageDialog(null, "Art: "+n+" is deleted");               
        } catch (SQLException ex) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList ArtistList(){
    
    ArrayList artistList = new ArrayList();

    Connection con = myConnection.getConnection();    
    Statement st;
    ResultSet rs;
    try{
        st = con.createStatement();        
        rs = st.executeQuery("SELECT a.artist_id, r.fname, r.lname FROM registration r, artist a WHERE a.user_id = r.user_id");

        while(rs.next())
        {
            String full = rs.getInt("artist_id")+" - "+rs.getString("fname") + " " + rs.getString("lname");
            artistList.add(full);
        }
        
    }catch(Exception e)
    {
        System.out.println(e.getMessage());
    }
    return artistList;
}    
}
