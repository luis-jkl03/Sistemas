
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
import java.awt.Image;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import javax.swing.SwingUtilities;
import javax.swing.text.StyleConstants;


public class datosHuella{
    
       private DPFPEnrollment Reclutador = DPFPGlobal.getEnrollmentFactory().createEnrollment(); 
       public DPFPFeatureSet featureSetInscripcion;
        JLabel eti;
    //   public LecPant pant = new LecPant();
       
     
    
    public datosHuella(){
        
      //  getInicializar(); 
    }
    
    public datosHuella(JLabel labHuella){
        
        eti=labHuella;
          getInicializar();
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
                       
                       GuardarHuella(CrearImagenHuella(e.getSample()));
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
        if(featureSetInscripcion != null){
                Reclutador.addFeatures(featureSetInscripcion);        
        EstadoHuellas();
        switch(Reclutador.getTemplateStatus()){
            
            case TEMPLATE_STATUS_READY:
                JOptionPane.showMessageDialog(null, "La huella se tomo con exito");
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
  return DPFPGlobal.getSampleConversionFactory().createImage(muestra);
}
     public void  GuardarHuella(Image huella){
         eti.setIcon(
         new ImageIcon(         
         huella.getScaledInstance(eti.getWidth(),eti.getHeight(),Image.SCALE_DEFAULT)
            )
         );
              
     }
     
     public void crearFhuella ()
     {
         File folder= new File("C:\\");
         
         
         folder.mkdirs();
         
         
     }
}
