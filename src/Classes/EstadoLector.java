package Classes;


import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusEvent;
import javax.swing.SwingUtilities;


public class EstadoLector {
    
    //public DPFPCapture Lector = DPFPGlobal.getCaptureFactory().createCapture();
    
    public EstadoLector(){
        
        inicializar();
    }

    private void inicializar() {
                LecPant.Lector.addReaderStatusListener(new DPFPReaderStatusAdapter(){
    @Override public void readerConnected(final DPFPReaderStatusEvent e){
      SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            System.out.println("El sensor de huella dactilar se encuentra Activado");
        }
      });
    }
    @Override public void readerDisconnected(final DPFPReaderStatusEvent e){
      SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            System.out.println("El sensor de huella dactilar se encuentra Desactivado");
        }
      });
    }
  });
    }
}
