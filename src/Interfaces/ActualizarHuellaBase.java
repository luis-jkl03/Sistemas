
package Interfaces;

import com.digitalpersona.onetouch.DPFPCaptureFeedback;
import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.event.DPFPDataAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;
import com.digitalpersona.onetouch.capture.event.DPFPImageQualityAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPImageQualityEvent;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusEvent;
import com.digitalpersona.onetouch.capture.event.DPFPSensorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPSensorEvent;
import com.digitalpersona.onetouch.processing.DPFPEnrollment;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import java.awt.Color;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class ActualizarHuellaBase extends javax.swing.JPanel {

    JPanel anteriorPanel;
    public ActualizarHuellaBase(JPanel anterior) {
        super(null);
        initComponents();
        anteriorPanel = anterior;
        //this.setLocationRelativeTo(null);       
        this.addComponentListener(new ComponentAdapter() {
                 @Override public void componentShown(ComponentEvent e) {
                         init();
                         start();
                 }
                 @Override public void componentHidden(ComponentEvent e) {
                        stop();
                 }
         });
    }

     private DPFPCapture capturer = DPFPGlobal.getCaptureFactory().createCapture();
     private DPFPEnrollment enroller = DPFPGlobal.getEnrollmentFactory().createEnrollment();
     
    protected void init()
	{
		capturer.addDataListener(new DPFPDataAdapter() {
			@Override public void dataAcquired(final DPFPDataEvent e) {
				SwingUtilities.invokeLater(new Runnable() {	public void run() {
					makeReport("La imagen fue capturada");
					setPrompt("Escanear de nuevo");
					process(e.getSample());
				}});
			}
		});
		capturer.addReaderStatusListener(new DPFPReaderStatusAdapter() {
			@Override public void readerConnected(final DPFPReaderStatusEvent e) {
				SwingUtilities.invokeLater(new Runnable() {	public void run() {
		 			//makeReport("El lector esta conectado");
                                    //colorEstadoLector.setBackground(Color.GREEN);
                                    //lbEdoLector.setText("El lector esta conectado");
                                    
				}});
			}
			@Override public void readerDisconnected(final DPFPReaderStatusEvent e) {
				SwingUtilities.invokeLater(new Runnable() {	public void run() {
					//makeReport("El lector esta desconectado");
                                    //colorEstadoLector.setBackground(Color.RED);
                                    //lbEdoLector.setText("El lector esta desconectado");
				}});
			}
		});
		capturer.addSensorListener(new DPFPSensorAdapter() {
			@Override public void fingerTouched(final DPFPSensorEvent e) {
				SwingUtilities.invokeLater(new Runnable() {	public void run() {
					makeReport("El lector fue tocado");
				}});
			}
			@Override public void fingerGone(final DPFPSensorEvent e) {
				SwingUtilities.invokeLater(new Runnable() {	public void run() {
					makeReport("El dedo fue removido del lector");
				}});
			}
		});
		capturer.addImageQualityListener(new DPFPImageQualityAdapter() {
			@Override public void onImageQuality(final DPFPImageQualityEvent e) {
				SwingUtilities.invokeLater(new Runnable() {	public void run() {
					if (e.getFeedback().equals(DPFPCaptureFeedback.CAPTURE_FEEDBACK_GOOD))
						makeReport("La calidad de la imagen es buena");
					else
						makeReport("La calidad de la imagen es baja, capturar de nuevo");
				}});
			}
		});
	}

	protected void process(DPFPSample sample)
	{
		// Draw fingerprint sample image.
		drawPicture(convertSampleToBitmap(sample));
                
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
                                        Icon img = new ImageIcon(this.getClass().getResource("/Imagenes/paloman.png"));
					JOptionPane.showMessageDialog(this, "La huella fue tomada con exito","Captura Huella", JOptionPane.PLAIN_MESSAGE,img);
					//getBtnGuardar().setEnabled(true);
                                        
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

	protected void start()
	{
		capturer.startCapture();
		setPrompt("Se ha empezado a usar el lector");
	}

	protected void stop()
	{
		capturer.stopCapture();
	}

	public void setStatus(String string) {
		
            System.out.println(string);
	}
	public void setPrompt(String string) {
		System.out.println(string);
	}
	public void makeReport(String string) {
		          System.out.println(string);
	}
	
	public void drawPicture(Image image) {
		lbHuellaActualizar.setIcon(new ImageIcon(
			image.getScaledInstance(lbHuellaActualizar.getWidth(), lbHuellaActualizar.getHeight(), Image.SCALE_DEFAULT)));
	}
	
	protected Image convertSampleToBitmap(DPFPSample sample) {
		return DPFPGlobal.getSampleConversionFactory().createImage(sample);
	}

	protected DPFPFeatureSet extractFeatures(DPFPSample sample, DPFPDataPurpose purpose)
	{
		DPFPFeatureExtraction extractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
		try {
			return extractor.createFeatureSet(sample, purpose);
		} catch (DPFPImageQualityException e) {
			return null;
		}
	}
        
        private void updateStatus()
	{
		// Show number of samples needed.
		setStatus(String.format("Fingerprint samples needed: %1$s", enroller.getFeaturesNeeded()));
            //getTextEstados().setText(String.format("Huellas requeridas para toma: %1$s", enroller.getFeaturesNeeded()));
	}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lbHuellaActualizar = new javax.swing.JLabel();
        lbHuellaActual = new javax.swing.JLabel();
        btnActualizar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("ACTUALIZAR HUELLA");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 280, 30));

        lbHuellaActualizar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        add(lbHuellaActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 210, 220));

        lbHuellaActual.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        add(lbHuellaActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 90, 210, 220));

        btnActualizar.setText("Actualizar");
        btnActualizar.setEnabled(false);
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 170, -1, -1));

        jButton1.setText("Regresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 370, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbHuellaActual;
    private javax.swing.JLabel lbHuellaActualizar;
    // End of variables declaration//GEN-END:variables
}
