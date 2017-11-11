
package Classes;

import Interfaces.FormCaptur;

import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.verification.DPFPVerification;
import com.digitalpersona.onetouch.verification.DPFPVerificationResult;
import java.awt.Frame;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sun.misc.IOUtils;


public class Verificacion extends FormCaptur {
    Properties p;
    Vector h;
    public Verificacion()
    {
         p = new Properties();
         h=new Vector();
        try {
            p.load(new FileReader("src/Classes/globales.properties"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Enrolar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Enrolar.class.getName()).log(Level.SEVERE, null, ex);
        }  
        //obtenerTemplate();
    }

    private DPFPVerification verificator = DPFPGlobal.getVerificationFactory().createVerification();

	@Override protected void init()
	{
		super.init();
		this.setTitle("CONTROL DE ACCESO");
                getBtnGuardar().setText("Ingresar");
                getBtnGuardar().setEnabled(false);
                getjScrollPane1().setVisible(false);
		//updateStatus(0);
	}

	@Override protected void process(DPFPSample sample) {
		super.process(sample);
                obtenerExpediente(sample);                	
        }
	
	private void updateStatus(int FAR)
	{
		// Show "False accept rate" value
		setStatus(String.format("False Accept Rate (FAR) = %1$s", FAR));
	}

    private void obtenerTemplate() {
        
        Connection con= null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
             con= ConexionBase.getConection();
             ps = con.prepareStatement("SELECT FOT_HUELLA FROM HUELLAPACIENTE");
             rs=ps.executeQuery();
            while(rs.next())
            {
                h.add(rs.getBinaryStream(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Verificacion.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if(rs !=null)
                    rs.close();
                if(ps !=null)
                    ps.close();
                if (con !=null)
                    con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Verificacion.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("En proccess");
            }
            
            
        }
    }

    private String obtenerExpediente(DPFPSample sample) {
        Connection con= null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        String exp = "0";
        try{
            DPFPFeatureSet features = extractFeatures(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);
            
             con= ConexionBase.getConection();
             ps = con.prepareStatement("SELECT EXPEDIENTE, NOM_PACIENTE, FOT_HUELLA FROM HUELLAPACIENTE");
             rs=ps.executeQuery();
            while(rs.next())
            {
                
                InputStream is = rs.getBinaryStream(3); 
                
                    byte[] data = new byte[is.available()];
                                is.read(data);
				is.close();
				DPFPTemplate t = DPFPGlobal.getTemplateFactory().createTemplate();
				t.deserialize(data);
                                
                                

		// Check quality of the sample and start verification if it's good
		if (features != null)
		{
			// Compare the feature set with our template
			DPFPVerificationResult result = 
				verificator.verify(features, t);
			updateStatus(result.getFalseAcceptRate());
			if (result.isVerified()){
				//makeReport("The fingerprint was VERIFIED.");
                                exp = "si";
                                getTextExp().setText(rs.getString(1));
                                getTextNombre().setText(rs.getString(2));                                
                                getBtnGuardar().setEnabled(true);
                                break;
                        }
                        
			else
				JOptionPane.showConfirmDialog(rootPane,"LA HUELLA COLOCADA NO SE ENCUENTRA, DESEA INTENTAR NUEVAMENTE?");
                                break;
		}
                                
                                
                                
                                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Verificacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Verificacion.class.getName()).log(Level.SEVERE, null, ex);
        }finally{

        }
        return exp;
    }
    






}
