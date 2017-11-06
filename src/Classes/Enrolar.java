
package Classes;

import Interfaces.LecPant;
import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.processing.DPFPEnrollment;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import java.awt.Frame;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Enrolar extends LecPant {    
    
    public Enrolar()
    {
        //super();
        
    }
    private DPFPEnrollment enroller = DPFPGlobal.getEnrollmentFactory().createEnrollment();

	
	@Override protected void init()
	{
		super.init();
		this.setTitle("Fingerprint Enrollment");
		updateStatus();
	}

	@Override protected void process(DPFPSample sample) {
		super.process(sample);
		// Process the sample and create a feature set for the enrollment purpose.
		DPFPFeatureSet features = extractFeatures(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);

		// Check quality of the sample and add to enroller if it's good
		if (features != null) try
		{
			makeReport("The fingerprint feature set was created.");
			enroller.addFeatures(features);		// Add feature set to template.
                        
                }
		catch (DPFPImageQualityException ex) { }
		finally {
			updateStatus();
			// Check if template has been created.
			switch(enroller.getTemplateStatus())
			{
				case TEMPLATE_STATUS_READY:	// report success and stop capturing
					stop();
                                        ImageIcon icon = new ImageIcon(this.getClass().getResource("src//Imagenes//paloma2.png"));
					JOptionPane.showMessageDialog(this, "La huella fue tomada con exito","Mensaje", JOptionPane.PLAIN_MESSAGE, icon);
					break;

				case TEMPLATE_STATUS_FAILED:	// report failure and restart capturing
					enroller.clear();
					stop();
                                        JOptionPane.showMessageDialog(this, "Ocurrio un error al tomar la huella, intentelo nuevamente", "Fallo en captura", JOptionPane.ERROR_MESSAGE);
					updateStatus();
                                        start();
					break;
			}
		}
	}
	
	private void updateStatus()
	{
		// Show number of samples needed.
		setStatus(String.format("Fingerprint samples needed: %1$s", enroller.getFeaturesNeeded()));
	}
}
