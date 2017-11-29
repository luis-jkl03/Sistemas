
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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class FormCaptura extends javax.swing.JDialog {

     public FormCaptura(java.awt.Frame parent){
       super(parent, true);
       initComponents();            
       this.setLocationRelativeTo(null);  
         setResizable(false);
        this.addComponentListener(new ComponentAdapter() {
                 @Override public void componentShown(ComponentEvent e) {
                         init();
                         start();
                 }
                 @Override public void componentHidden(ComponentEvent e) {
                        stop();
                 }
         });
        //pack();
         setSize(jlabelLTitulo.getWidth(), jlabelLTitulo.getHeight());
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

    public JLabel getLbHuella() {
        return lbHuella;
    }

    public void setLabHuella(JLabel labHuella) {
        this.lbHuella = labHuella;
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
					process(e.getSample());
				}});
			}
		});
		capturer.addReaderStatusListener(new DPFPReaderStatusAdapter() {
			@Override public void readerConnected(final DPFPReaderStatusEvent e) {
				SwingUtilities.invokeLater(new Runnable() {	public void run() {
		 			//makeReport("El lector esta conectado");
                                    colorEstadoLector.setBackground(Color.GREEN);
                                    lbEdoLector.setText("Lector conectado");
                                    
				}});
			}
			@Override public void readerDisconnected(final DPFPReaderStatusEvent e) {
				SwingUtilities.invokeLater(new Runnable() {	public void run() {
					//makeReport("El lector esta desconectado");
                                    colorEstadoLector.setBackground(Color.RED);
                                    lbEdoLector.setText("Lector desconectado");
				}});
			}
		});
		capturer.addSensorListener(new DPFPSensorAdapter() {
			@Override public void fingerTouched(final DPFPSensorEvent e) {
				SwingUtilities.invokeLater(new Runnable() {	public void run() {
                                    
				}});
			}
			@Override public void fingerGone(final DPFPSensorEvent e) {
				SwingUtilities.invokeLater(new Runnable() {	public void run() {
                                    
				}});
			}
		});
		capturer.addImageQualityListener(new DPFPImageQualityAdapter() {
			@Override public void onImageQuality(final DPFPImageQualityEvent e) {
				SwingUtilities.invokeLater(new Runnable() {	public void run() {
					if (e.getFeedback().equals(DPFPCaptureFeedback.CAPTURE_FEEDBACK_GOOD))
                                            System.out.println("La calidad de la imagen es buena");
					else
                                            System.out.println("La calidad de la imagen es baja, capturar de nuevo");
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
		//setPrompt("Se ha empezado a usar el lector");
	}

	protected void stop()
	{
		capturer.stopCapture();
	}
	
	public void drawPicture(Image image) {
		lbHuella.setIcon(new ImageIcon(
			image.getScaledInstance(lbHuella.getWidth(), lbHuella.getHeight(), Image.SCALE_DEFAULT)));
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

        lbEdoLector = new javax.swing.JLabel();
        jLabelTitu = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lbHuella = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        textExp = new javax.swing.JTextField();
        textNombre = new javax.swing.JTextField();
        colorEstadoLector = new javax.swing.JTextField();
        jScroll = new javax.swing.JScrollPane();
        textEstados = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jlabelLTitulo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbEdoLector.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        getContentPane().add(lbEdoLector, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 265, 240, 20));

        jLabelTitu.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        jLabelTitu.setForeground(new java.awt.Color(204, 0, 0));
        jLabelTitu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabelTitu, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 490, 40));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Expediente ");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, 90, 20));

        lbHuella.setBackground(new java.awt.Color(255, 255, 255));
        lbHuella.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbHuella.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbHuella.setText("COLOCAR HUELLA");
        lbHuella.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255), 3));
        getContentPane().add(lbHuella, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 150, 240));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Paciente");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, -1, -1));

        btnGuardar.setFont(new java.awt.Font("Calisto MT", 0, 18)); // NOI18N
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 340, -1, -1));

        textExp.setEditable(false);
        textExp.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        textExp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textExp.setText("1");
        textExp.setToolTipText("");
        textExp.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 255), null));
        textExp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(textExp, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 110, 100, 30));

        textNombre.setEditable(false);
        textNombre.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        textNombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textNombre.setText("Luis Javier");
        textNombre.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 255), null));
        textNombre.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        textNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textNombreActionPerformed(evt);
            }
        });
        getContentPane().add(textNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 150, 270, 30));

        colorEstadoLector.setEditable(false);
        colorEstadoLector.setFocusable(false);
        getContentPane().add(colorEstadoLector, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 260, 40, 30));

        jScroll.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScroll.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        textEstados.setEditable(false);
        textEstados.setColumns(20);
        textEstados.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        textEstados.setRows(5);
        textEstados.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScroll.setViewportView(textEstados);

        getContentPane().add(jScroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 330, 30));

        jButton1.setText("Habilitar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 263, -1, 40));

        jlabelLTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondoClinihuella.jpg"))); // NOI18N
        getContentPane().add(jlabelLTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 610, 430));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textNombreActionPerformed

    }//GEN-LAST:event_textNombreActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        textExp.setText("1");
        textNombre.setText("LUIS JAVIER");
        btnGuardar.setEnabled(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    @Override
    public void dispose(){
        super.dispose();
        stop();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JTextField colorEstadoLector;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelTitu;
    private javax.swing.JScrollPane jScroll;
    private javax.swing.JLabel jlabelLTitulo;
    private javax.swing.JLabel lbEdoLector;
    private javax.swing.JLabel lbHuella;
    private javax.swing.JTextArea textEstados;
    private javax.swing.JTextField textExp;
    private javax.swing.JTextField textNombre;
    // End of variables declaration//GEN-END:variables
}
