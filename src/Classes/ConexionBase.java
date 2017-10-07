package Classes;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConexionBase {
    static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static String url = "jdbc:sqlserver://localhost:1433;databaseName=CLINICA5NM80";
    static String user = "sele";
    static String pass = "123";
    String gg;
    public ConexionBase(){
        //getConection();
       
    }
    
    public static Connection getConection(){
        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionBase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            PreparedStatement st = con.prepareStatement("select * from HUELLAPACIENTE");
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                System.out.println("1 --> " + rs.getString(1));
                System.out.println("2 --> " + rs.getString(2));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return con;
    }
    
    
}
