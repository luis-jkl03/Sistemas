
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.event.DPFPImageQualityAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPImageQualityEvent;
import com.digitalpersona.onetouch.capture.event.DPFPImageQualityListener;
import com.digitalpersona.onetouch.ui.swing.DPFPEnrollmentControl;
import com.digitalpersona.onetouch.ui.swing.DPFPVerificationControl;
import com.sun.glass.ui.InvokeLaterDispatcher;
import java.io.Serializable;
import javax.swing.SwingUtilities;



public class imagenQuality{
    
    public imagenQuality(){
        getInicializar();
    }

    private void getInicializar() {
        
        LecPant.Lector.addImageQualityListener(new DPFPImageQualityAdapter(){
            
           @Override
            public void onImageQuality(DPFPImageQualityEvent dpfpq) {

        SwingUtilities.invokeLater(new Runnable() {

               @Override
               public void run() {
                   System.out.println("Imagen de mala calidad");
               }
           });
    }        
        });
    }
}
