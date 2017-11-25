
package Classes;

import Interfaces.FormCaptur;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Actualizacion extends FormCaptur{
    
    Properties p;
    int expediente;
    public Actualizacion(Frame menu, int expediente){
        super(menu);
        this.expediente = expediente;
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
		this.setTitle("Actualizar huella");
                getjLabelTitu().setText("Actualizar Huella Paciente***");                                    
                getBtnGuardar().setText("Actualizar");
               // getBtnAccion().setToolTipText("Guardar los datos en base");
               getBtnGuardar().setEnabled(false);
               
               
               getBtnGuardar().addActionListener(new ActionListener()                                
                {
                
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //guardarDatosPersonales();
                        actualizarHuella();
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
					getBtnGuardar().setEnabled(true);                                 
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
            getTextEstados().setText(String.format("Huellas requeridas para toma: %1$s", enroller.getFeaturesNeeded()));
	}
        
        private void actualizarHuella() {
            Connection con = null;
            PreparedStatement pst = null;
            File file = null;
            FileInputStream stream = null;
            
            try {
                con = ConexionBase.getConection();
                System.out.println("Estado template --> " + enroller.getTemplateStatus());
                file = getFileFpt();
                stream = new FileInputStream(file);
                String archivo = "";
                /*int c = stream.read();
                while(c != -1){
                    archivo += (char) c;
                    c = stream.read();
                }*/
                //System.out.println(archivo);
                //pst = con.prepareStatement("INSERT INTO HUELLAPACIENTE VALUES(?,?,?,?)");
                //System.out.println("El expediente es de --> " + expediente);
                //System.out.println("Consulta --> " + "UPDATE HUELLAPACIENTE(FOT_HUELLA) SET(?) WHERE EXPEDIENTE = " + expediente);
                pst = con.prepareStatement("UPDATE HUELLAPACIENTE SET FOT_HUELLA = ? WHERE EXPEDIENTE = " + expediente);
                //System.out.println("UPDATE HUELLAPACIENTE SET FOT_HUELLA = '" + archivo + "' WHERE EXPEDIENTE = " + expediente);
                //pst = con.prepareStatement("SELECT FOT_HUELLA FROM HUELLAPACIENTE WHERE EXPEDIENTE = " + expediente);
                //JOptionPane.showMessageDialog(null,"SI PASE");
                pst.setBinaryStream(1, stream, file.length());
                //JOptionPane.showMessageDialog(null,"SI PASE2");
                //ResultSet rs = pst.executeQuery();
                //while(rs.next()){
                   // System.out.println(rs.getBinaryStream(1).);
                //}
                pst.executeUpdate();
                
                JOptionPane.showMessageDialog(this, "Huella actualizada correctamente");                
            } catch (FileNotFoundException ex) {
            Logger.getLogger(Enrolar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Actualizacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Actualizacion.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
                if(pst != null)
                    try {
                        pst.close();
                if(con != null)
                    ConexionBase.close(con);
                if(stream != null)
                    stream.close();
                
                } catch (SQLException ex) {
                    Logger.getLogger(Enrolar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Actualizacion.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
            //if(file != null)
                    //file.delete();
}

    private File getFileFpt() {
        File dir = new File(p.getProperty("rutaImagenesFPT"));
        if(!dir.exists()){
            dir.mkdirs();
        }
        String path = p.getProperty("rutaImagenesFPT") + "//";
        File file = new File(path + "h-" + expediente + ".fpt");
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
