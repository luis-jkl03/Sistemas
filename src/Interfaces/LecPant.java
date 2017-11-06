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
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class LecPant extends JFrame{
   
   public LecPant(){
       initComponents();
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

    public JLabel getLabHuella() {
        return labHuella;
    }

    public void setLabHuella(JLabel labHuella) {
        this.labHuella = labHuella;
    }
    
    
    
    private DPFPCapture capturer = DPFPGlobal.getCaptureFactory().createCapture();
    
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
                                    colorEstadoLector.setBackground(Color.GREEN);
                                    lbEstadoLector.setText("El lector esta conectado");
				}});
			}
			@Override public void readerDisconnected(final DPFPReaderStatusEvent e) {
				SwingUtilities.invokeLater(new Runnable() {	public void run() {
					//makeReport("El lector esta desconectado");
                                    colorEstadoLector.setBackground(Color.RED);
                                    lbEstadoLector.setText("El lector esta desconectado");
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
		labHuella.setIcon(new ImageIcon(
			image.getScaledInstance(labHuella.getWidth(), labHuella.getHeight(), Image.SCALE_DEFAULT)));
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labHuella = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        textExp = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        textNombre = new javax.swing.JTextField();
        textEdoLector = new javax.swing.JTextField();
        colorEstadoLector = new javax.swing.JTextField();
        lbEstadoLector = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(700, 600));
        setSize(new java.awt.Dimension(700, 500));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labHuella.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        getContentPane().add(labHuella, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 150, 240));

        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, -1, -1));

        jLabel1.setText("Expediente ");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 40, 75, 35));

        textExp.setToolTipText("");
        getContentPane().add(textExp, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, 100, 30));

        jLabel2.setText("Paciente");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 150, -1, -1));
        getContentPane().add(textNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 150, 270, 30));
        getContentPane().add(textEdoLector, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 200, -1));
        getContentPane().add(colorEstadoLector, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 40, 30));

        lbEstadoLector.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(lbEstadoLector, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 345, 300, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       jButton1.setText("kjidfj");
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField colorEstadoLector;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel labHuella;
    private javax.swing.JLabel lbEstadoLector;
    private javax.swing.JTextField textEdoLector;
    private javax.swing.JTextField textExp;
    private javax.swing.JTextField textNombre;
    // End of variables declaration//GEN-END:variables


}
