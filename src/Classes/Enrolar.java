
package Classes;

import Interfaces.FormCaptura;
import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.processing.DPFPEnrollment;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Enrolar extends FormCaptura {    
    
    Vector vector;
    Properties p;
    public Enrolar(Vector vector)
    {
        //super(vector);
        this.vector = vector;
        getTextExp().setText((String) vector.get(0));
        getTextNombre().setText((String) vector.get(1));
        
        p = new Properties();
        try {
            p.load(new FileReader("src/Classes/globales.properties"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Enrolar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Enrolar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    private DPFPEnrollment enroller = DPFPGlobal.getEnrollmentFactory().createEnrollment();

	
	@Override protected void init()
	{
		super.init();
		this.setTitle("Captura de Huella");
                getBtnAccion().setText("Guardar datos");
                getBtnAccion().setToolTipText("Guardar los datos en base");
                getBtnAccion().setEnabled(false);
                getBtnAccion().addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        guardarDatosPersonales();
                        guardarDatosHuella();
                    }
                });
                
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
                                        Icon img = new ImageIcon(this.getClass().getResource("/Imagenes/paloman.png"));
					JOptionPane.showMessageDialog(this, "La huella fue tomada con exito","Captura Huella", JOptionPane.PLAIN_MESSAGE,img);
					getBtnAccion().setEnabled(true);
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
		//setStatus(String.format("Fingerprint samples needed: %1$s", enroller.getFeaturesNeeded()));
            getTextEstados().setText(String.format("Huellas requeridas para toma: %1$s", enroller.getFeaturesNeeded()));
	}
        
        private void guardarDatosPersonales() {
            Connection con = null;
            PreparedStatement pst = null;
            
            try {
                con = ConexionBase.getConection();
                pst = con.prepareStatement("INSERT INTO PERPACIENTE VALUES(?,?,?,?,?,?)");
                pst.setString(1, (String) vector.get(1));
                pst.setInt(2, Integer.parseInt((String) vector.get(2)));
                pst.setString(3, (String) vector.get(3));
                pst.setString(4, (String) vector.get(4));
                pst.setString(5, (String) vector.get(5));
                pst.setString(6, (String) vector.get(6));
                pst.execute();
                
                
                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Ocurrio un error al guardar los datos");
            }finally{
                if(pst != null)
                    try {
                        pst.close();
                if(con != null)
                    ConexionBase.close(con);
                } catch (SQLException ex) {
                    Logger.getLogger(Enrolar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                
        }

        private void guardarDatosHuella() {
            Connection con = null;
            PreparedStatement pst = null;
            File file = null;
            
            try {
                con = ConexionBase.getConection();
                file = getFileFpt();
                FileInputStream stream = new FileInputStream(file);
                pst = con.prepareStatement("INSERT INTO HUELLAPACIENTE VALUES(?,?,?,?)");
                pst.setString(1, (String) vector.get(0));
                pst.setString(2, (String) vector.get(1));
                pst.setString(3, "1");
                pst.setBinaryStream(4, stream, file.length());
                pst.execute();
                
                JOptionPane.showMessageDialog(this, "Datos guardados correctamente");
                this.dispose();
                
            } catch (FileNotFoundException ex) {
            Logger.getLogger(Enrolar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Ocurrio un error al guardar los datos");
        } finally{
                if(pst != null)
                    try {
                        pst.close();
                if(con != null)
                    ConexionBase.close(con);
                
                } catch (SQLException ex) {
                    Logger.getLogger(Enrolar.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
            if(file != null)
                    file.delete();
}

    private File getFileFpt() {
        File dir = new File(p.getProperty("rutaImagenesFPT"));
        if(!dir.exists()){
            dir.mkdirs();
        }
        String path = p.getProperty("rutaImagenesFPT") + "//";
        File file = new File(path + "h-" + (String) vector.get(0) + ".fpt");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Enrolar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(file);
            stream.write(enroller.getTemplate().serialize());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Enrolar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Enrolar.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
                try {
                    if(stream != null)
                        stream.close();
            } catch (IOException ex) {
                Logger.getLogger(Enrolar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        return file;
        }
    }
