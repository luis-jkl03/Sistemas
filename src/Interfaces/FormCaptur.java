
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
import java.awt.Frame;
import java.awt.Image;
import static java.awt.SystemColor.menu;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class FormCaptur extends javax.swing.JDialog {

     public FormCaptur(java.awt.Frame parent){
       super(parent, true);
       initComponents();            
       this.setLocationRelativeTo(null);       
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
         
     public JLabel getjLabelTitu() {
        return jLabelTitu;
    }

    public void setjLabelTitu(JLabel jLabelTitu) {
        this.jLabelTitu = jLabelTitu;
    }

   

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public void setBtnGuardar(JButton btnGuardar) {
        this.btnGuardar = btnGuardar;
    }
    
    public JScrollPane getjScrollPane1() {
        return jScroll;
    }

    public void setjScrollPane1(JScrollPane jScrollPane1) {
        this.jScroll = jScrollPane1;
    }

    public JTextArea getTextEstados() {
        return textEstados;
    }

    public void setTextEstados(JTextArea textEstados) {
        this.textEstados = textEstados;
    }

    public JTextField getTextExp() {
        return textExp;
    }

    public void setTextExp(JTextField textExp) {
        this.textExp = textExp;
    }

    public JTextField getTextNombre() {
        return textNombre;
    }

    public void setTextNombre(JTextField textNombre) {
        this.textNombre = textNombre;
    }

    public JLabel getJlabelLTitulo() {
        return jlabelLTitulo;
    }

    public void setJlabelLTitulo(JLabel jlabelLTitulo) {
        this.jlabelLTitulo = jlabelLTitulo;
    }

    public JLabel getLabHuella() {
        return labHuella;
    }

    public void setLabHuella(JLabel labHuella) {
        this.labHuella = labHuella;
    }

    public DPFPCapture getCapturer() {
        return capturer;
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
                                    lbEdoLector.setText("El lector esta conectado");
                                    
				}});
			}
			@Override public void readerDisconnected(final DPFPReaderStatusEvent e) {
				SwingUtilities.invokeLater(new Runnable() {	public void run() {
					//makeReport("El lector esta desconectado");
                                    colorEstadoLector.setBackground(Color.RED);
                                    lbEdoLector.setText("El lector esta desconectado");
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


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbEdoLector = new javax.swing.JLabel();
        jLabelTitu = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        labHuella = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        textExp = new javax.swing.JTextField();
        textNombre = new javax.swing.JTextField();
        colorEstadoLector = new javax.swing.JTextField();
        jScroll = new javax.swing.JScrollPane();
        textEstados = new javax.swing.JTextArea();
        jlabelLTitulo = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbEdoLector.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        getContentPane().add(lbEdoLector, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 355, 300, 20));

        jLabelTitu.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        jLabelTitu.setForeground(new java.awt.Color(204, 0, 0));
        getContentPane().add(jLabelTitu, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 490, 40));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Expediente ");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, 90, 20));

        labHuella.setBackground(new java.awt.Color(255, 255, 255));
        labHuella.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        getContentPane().add(labHuella, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 150, 240));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Paciente");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, -1, -1));

        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, -1, -1));

        textExp.setEditable(false);
        textExp.setToolTipText("");
        getContentPane().add(textExp, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 110, 100, 30));

        textNombre.setEditable(false);
        textNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textNombreActionPerformed(evt);
            }
        });
        getContentPane().add(textNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 150, 270, 30));
        getContentPane().add(colorEstadoLector, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 40, 30));

        jScroll.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        textEstados.setColumns(20);
        textEstados.setRows(5);
        textEstados.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScroll.setViewportView(textEstados);

        getContentPane().add(jScroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 470, 580, 100));

        jlabelLTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondoClinihuella.jpg"))); // NOI18N
        getContentPane().add(jlabelLTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 580));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textNombreActionPerformed

    }//GEN-LAST:event_textNombreActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

    }//GEN-LAST:event_btnGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JTextField colorEstadoLector;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelTitu;
    private javax.swing.JScrollPane jScroll;
    private javax.swing.JLabel jlabelLTitulo;
    private javax.swing.JLabel labHuella;
    private javax.swing.JLabel lbEdoLector;
    private javax.swing.JTextArea textEstados;
    private javax.swing.JTextField textExp;
    private javax.swing.JTextField textNombre;
    // End of variables declaration//GEN-END:variables
}
