
package Classes;

import Interfaces.FormCaptura;
import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.processing.DPFPEnrollment;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
    Dialog personales;
    
    
    
    public Enrolar(Vector vector, Frame menu, Dialog personales)
    {
        
        super(menu);
        //init();
        this.vector = vector;        
        getTextExp().setText((String) vector.get(0));
        getTextNombre().setText((String) vector.get(1));
        this.personales = personales;
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
                getjLabelTitu().setText("Registro de huella digital");                                    
                getBtnGuardar().setText("Guardar");
               getBtnGuardar().setEnabled(false);                              
               getBtnGuardar().addActionListener(new ActionListener()                                
                {                
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
		DPFPFeatureSet features = extractFeatures(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);
		// Revisar calidad de la huella
		if (features != null) try
		{
			enroller.addFeatures(features);		// Añadir caracteristicas a template
                        
                }
		catch (DPFPImageQualityException ex) { }
		finally {
			updateStatus();
			switch(enroller.getTemplateStatus())
			{                            
				case TEMPLATE_STATUS_READY:	// Exito en captura y detener lector                                 
					stop();
                                        Icon img = new ImageIcon(this.getClass().getResource("/Imagenes/paloman.png"));
					JOptionPane.showMessageDialog(this, "La huella fue tomada con exito","Captura Huella", JOptionPane.PLAIN_MESSAGE,img);
					getBtnGuardar().setEnabled(true);
                                        
                                        break;

				case TEMPLATE_STATUS_FAILED:	// Falla en captura
					enroller.clear();
					stop();
                                        JOptionPane.showMessageDialog(this, "Ocurrio un error al tomar la huella, intentelo nuevamente", "Fallo en captura", JOptionPane.ERROR_MESSAGE);
					updateStatus();
                                        start();
			}
		}
	}
	
	private void updateStatus()
	{
		//Actualizar estado
            getTextEstados().setText(String.format("Huellas requeridas para toma: %1$s", enroller.getFeaturesNeeded()));
	}
        
        private void guardarDatosPersonales() {
            Connection con = null;
            PreparedStatement pst = null;
            
            try {
                con = new ConexionBase().getConection();
                pst = con.prepareStatement("INSERT INTO PERPACIENTE VALUES(?,?,?,?,?,?,?)");
                pst.setString(1, (String) vector.get(1));
                pst.setInt(2, Integer.parseInt((String) vector.get(2)));
                pst.setString(3, (String) vector.get(3));
                pst.setString(4, (String) vector.get(4));
                pst.setString(5, (String) vector.get(5));
                pst.setString(6, (String) vector.get(6));
                pst.setDate(7, new Fechas().fecha());
                pst.execute();         
                                
            } catch (SQLException e) {
                Logger.getLogger(ConexionBase.class.getName()).log(Level.SEVERE, null, e);
                //JOptionPane.showMessageDialog(this, "Ocurrio un error al guardar los datos personales");
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
                con = new ConexionBase().getConection();
                file = getFileFpt();
                FileInputStream stream = new FileInputStream(file);
                pst = con.prepareStatement("INSERT INTO HUELLAPACIENTE VALUES(?,?,?,?,?)");
                pst.setString(1, (String) vector.get(0));
                pst.setString(2, (String) vector.get(1));
                pst.setString(3, "1");
                pst.setBinaryStream(4, stream, file.length());
                pst.setDate(5, new Fechas().fecha());
                pst.execute();
                
                JOptionPane.showMessageDialog(this, "Datos guardados correctamente");
                this.dispose();
                personales.dispose();
                
            } catch (FileNotFoundException ex) {
            Logger.getLogger(Enrolar.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Ocurrio un error al registrar, verificar la informacion","Aviso",JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Ocurrio un error al registrar, verificar la informacion","Aviso",JOptionPane.ERROR_MESSAGE);
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
    
    
    
