package Classes;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class ConexionBase {
    String driver;
    String url;
    String user;
    String pass;
    Properties p;
    
    public ConexionBase(){
        p = new Properties();
        try {            
            p.load(new FileReader("src\\Classes\\globales.properties"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConexionBase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConexionBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        url = "jdbc:sqlserver://localhost:1433;databaseName="+p.getProperty("databaseName");
        user = p.getProperty("user");
        pass = p.getProperty("pass");
    }
    
    public Connection getConection(){
        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionBase.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Ocurrio un error con la base de datos, intente de nuevo", "Aviso", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBase.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Ocurrio un error con la base de datos, intente de nuevo", "Aviso", JOptionPane.ERROR_MESSAGE);
        }      
        return con;
    }
    
    public static void close(Connection con){
        if(con != null){
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConexionBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
