package Classes;


import Interfaces.Expediente;
import Interfaces.LecPant;
import com.digitalpersona.onetouch.DPFPData;
import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.capture.event.DPFPDataAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;
import com.digitalpersona.onetouch.capture.event.DPFPDataListener;
import com.digitalpersona.onetouch.processing.DPFPEnrollment;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.StyleConstants;


public class datosHuella{
    
       private DPFPEnrollment Reclutador = DPFPGlobal.getEnrollmentFactory().createEnrollment(); 
       public DPFPFeatureSet featureSetInscripcion;
       JLabel eti;
       JTextField expHu;
       Properties p;
       Vector vec;
       File fichero;
       String formato;
    //   public LecPant pant = new LecPant();
       
     
    
    public datosHuella(){
        
      //  getInicializar(); 
    }
    
    public datosHuella(JLabel labHuella, JTextField expHuella,Vector vecpant){
        
        eti=labHuella;
        expHu=expHuella;
        vec = vecpant;
        getInicializar();        
        p = new Properties();
        try {
            p.load(new FileReader("src/Classes/globales.properties"));
            //System.out.println(p.getProperty("rutaImagenesJPG"));
        } catch (IOException ex) {
            Logger.getLogger(datosHuella.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void getInicializar() {
        LecPant.Lector.addDataListener(new DPFPDataAdapter(){
        
             @Override
    public void dataAcquired(DPFPDataEvent e) {

        SwingUtilities.invokeLater(new Runnable() {

               @Override
               public void run() {
                   try {
                       System.out.println("Imagen Capturada");
                       procesoImagen(e.getSample());
                       
                       GuardarHuellaEnPath(CrearImagenHuella(e.getSample()), "fpt");
                   } catch (DPFPImageQualityException ex) {
                       JOptionPane.showMessageDialog(null, "COLOQUE LA HUELLA CORRETAMENTE, POR FAVOR", null, ERROR_MESSAGE);
                       Reclutador.clear();
                   }
               }
           });
    
    }   
        });
    }
    
    private void procesoImagen(DPFPSample muestra) throws DPFPImageQualityException,IllegalStateException{
        featureSetInscripcion = extraerCaracteristicas(muestra, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);
        File archivo;
       System.out.println("otsss: "+featureSetInscripcion);
       
        if(featureSetInscripcion != null){
                Reclutador.addFeatures(featureSetInscripcion);      
                
        EstadoHuellas();
        switch(Reclutador.getTemplateStatus()){
            
            case TEMPLATE_STATUS_READY:
                
                String desktop;
                desktop="RECLUUU"+Reclutador.getTemplate();
                System.out.println(desktop);
                JOptionPane.showMessageDialog(null, "La huella se tomo con exito");
                
                
                //escribirImagenEnBD(fichero.getAbsolutePath(),LecPant.expediente + formato, "INSERT INTO HUELLAPACIENTE(EXPEDIENTE,NOM_PACIENTE,NO_HUELLA,FOT_HUELLA)" + " VALUES (?,?,?,?)");
                Reclutador.clear();
                break;
                
            case TEMPLATE_STATUS_INSUFFICIENT:
                break;
            case TEMPLATE_STATUS_FAILED:
                //JOptionPane.showMessageDialog(null,"Ocurrio un error capture nuevamente la huella", null, ERROR_MESSAGE);
                //Reclutador.clear();
                break;
        }
        }
    }
    
      public void EstadoHuellas(){
      System.out.println("Muestra de huellas necesarias para guardar plantilla: " + Reclutador.getFeaturesNeeded());
    }
      
      public DPFPFeatureSet extraerCaracteristicas(DPFPSample muestra, DPFPDataPurpose motivo) throws DPFPImageQualityException{
        DPFPFeatureExtraction extractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
            return extractor.createFeatureSet(muestra, motivo);
}
    public Image CrearImagenHuella(DPFPSample muestra){
        //System.out.println("4 veces");
  return DPFPGlobal.getSampleConversionFactory().createImage(muestra);
}
     public void  GuardarHuellaEnPath(Image huella, String formato){
         
        eti.setIcon(new ImageIcon(huella.getScaledInstance(eti.getWidth(),eti.getHeight(),Image.SCALE_DEFAULT)));
		// Get icon from label
        ImageIcon icon = (ImageIcon) eti.getIcon();
                
                // Copy image
                BufferedImage image = new BufferedImage(icon.getIconWidth(),
                        icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
                Graphics2D g2 = image.createGraphics();
                g2.drawImage(icon.getImage(), 0, 0, icon.getImageObserver());
                g2.dispose();
                
                File fichero = null;
                if(formato.equals("jpg")){
                    fichero = new File(p.getProperty("rutaImagenesJPG"));
                }
                else if(formato.equals("bmp")){
                    fichero = new File(p.getProperty("rutaImagenesBMP"));
                }
                else if(formato.equals("fpt")){
                    fichero = new File(p.getProperty("rutaImagenesFPT"));
                }
                
                System.out.println(fichero.getAbsolutePath());
           try {
               fichero.mkdirs();
               // Write images
               fichero = new File(fichero.getAbsolutePath() +LecPant.expediente+"."+ formato);
              // bool= fichero.renameTo();
               System.out.println("--> " + fichero);
               ImageIO.write(image, formato, fichero);
               this.fichero = fichero;
               this.formato = formato;
           } catch (IOException ex) {
               Logger.getLogger(datosHuella.class.getName()).log(Level.SEVERE, null, ex);
           }              
     }
     
    public boolean escribirImagenEnBD(String dirArchivo, String nomArchivo, String sentenciaSQL) { 
        boolean rpta=false; 
        Connection con = ConexionBase.getConection();
        try {
       // insertarPersonales();
        File fichero = new File(dirArchivo); 
        FileInputStream streamEntrada = new FileInputStream(fichero); 
        
            System.out.println(fichero);
            System.out.println(streamEntrada);
        
        PreparedStatement pstmt = con.prepareStatement(sentenciaSQL);
        pstmt.setInt(1, Integer.parseInt(vec.get(0).toString())); 
        pstmt.setString(2, vec.get(1).toString()); 
        pstmt.setInt(3, 1); 
        //Imagen a guardar 
        pstmt.setBinaryStream(4, streamEntrada, (int)fichero.length());
        pstmt.executeUpdate(); 
        pstmt.close(); 
        streamEntrada.close(); 
        rpta=true; 
        JOptionPane.showMessageDialog(null, "Datos ingresados exitosamente");
        } 
        catch(Exception e) { 
        e.printStackTrace(); 
        }finally{
            try {
                if(!con.isClosed()){
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(datosHuella.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        return rpta; 
    }
    
    private void insertarPersonales(){
          Connection con= ConexionBase.getConection();                        
        try {
           
            PreparedStatement st= con.prepareStatement("insert into PERPACIENTE values(?,?,?,?,?,?)");
            st.setString(1,vec.get(1).toString());
            st.setInt(2, Integer.parseInt(vec.get(2).toString()));
            st.setString(3, vec.get(3).toString());
            st.setString(4, vec.get(4).toString());
            st.setString(5, vec.get(5).toString());
            st.setString(6, vec.get(6).toString());
            st.execute();
            //JOptionPane.showMessageDialog(null, "Datos insertados correctamente");
            //asignarExpediente();
            //bloquear(true);
            //JOptionPane.showMessageDialog(, "POR FAVOR CAPTURE LA HUELLA DEL PACIENTE");
            
        } catch (SQLException ex) {
            //Logger.getLogger(Expediente.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Ocurrio un error, verifique los datos e intente de nuevo");
        }finally{
             try {
                 if(!con.isClosed())
                     
                     con.close();
             } catch (SQLException ex) {
                 Logger.getLogger(Expediente.class.getName()).log(Level.SEVERE, null, ex);
             }
                    
        }  
    }
    
    public void insertarTablas(){
        // escribirImagenEnBD(fichero.getAbsolutePath(),LecPant.expediente + formato, "INSERT INTO HUELLAPACIENTE(EXPEDIENTE,NOM_PACIENTE,NO_HUELLA,FOT_HUELLA)" + " VALUES (?,?,?,?)");

    }
}
