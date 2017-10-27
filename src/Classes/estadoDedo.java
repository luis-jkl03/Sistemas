package Classes;


import Interfaces.LecPant;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.event.DPFPSensorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPSensorEvent;
import com.digitalpersona.onetouch.capture.event.DPFPSensorListener;
import javax.swing.SwingUtilities;


public class estadoDedo{
    
    //public DPFPCapture Lector1 = DPFPGlobal.getCaptureFactory().createCapture();
    
    public estadoDedo(){
    
        getInicializar();
    }

    private void getInicializar() {
        LecPant.Lector.addSensorListener(new DPFPSensorAdapter(){
        
    @Override
    public void fingerTouched(DPFPSensorEvent dpfpse) {
           SwingUtilities.invokeLater(new Runnable() {

               @Override
               public void run() {
                   System.out.println("El dedo se a colocado");
               }
           });
    }

    @Override
    public void fingerGone(DPFPSensorEvent dpfpse) {
    
        SwingUtilities.invokeLater(new Runnable(){

               @Override
               public void run() {
                   System.out.println("Coloque el dedo");
               }
           });
    }

    @Override
    public void imageAcquired(DPFPSensorEvent dpfpse) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    });
    }
}
