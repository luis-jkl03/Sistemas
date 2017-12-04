
package Classes;

import Interfaces.FormCaptura;
import Interfaces.FormMedicos;

import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.digitalpersona.onetouch.verification.DPFPVerification;
import com.digitalpersona.onetouch.verification.DPFPVerificationResult;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Verificacion extends FormCaptura {

    Frame menu;
    Dialog verif;
    
    public Verificacion(Frame parent)
    {
        super(parent);
        menu = parent;
        verif = this;
    }

    private DPFPVerification verificator = DPFPGlobal.getVerificationFactory().createVerification();

	@Override protected void init()
	{
		super.init();
		this.setTitle("Control de acceso");
                getjLabelTitu().setText("Busqueda de paciente");    
                getBtnGuardar().setText("Continuar");
                getBtnGuardar().setEnabled(false);
                getjScrollPane1().setVisible(false);
                getBtnGuardar().addActionListener(new ActionListener()                                
                {                
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new FormMedicos(menu, true, verif, llenarVector()).setVisible(true);
                    }
                });
	}

	@Override protected void process(DPFPSample sample) {
		super.process(sample);
                obtenerDatos(sample);                	
        }

    private void obtenerDatos(DPFPSample sample) {
        Connection con= null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        try{
            DPFPFeatureSet features = extractFeatures(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);
            
             con= new ConexionBase().getConection();
             ps = con.prepareStatement("SELECT EXPEDIENTE, NOM_PACIENTE, FOT_HUELLA FROM HUELLAPACIENTE");
             rs=ps.executeQuery();
             boolean encontrado = false;
            while(rs.next())
            {                
                InputStream is = rs.getBinaryStream(3); 
                
                    byte[] data = new byte[is.available()];
                                is.read(data);
				is.close();
				DPFPTemplate t = DPFPGlobal.getTemplateFactory().createTemplate();
				t.deserialize(data);                                
                                
		// Verificar la huella
		if (features != null)
		{
			DPFPVerificationResult result = 
                        verificator.verify(features, t);
			if (result.isVerified()){
                                getTextExp().setText(rs.getString(1));
                                getTextNombre().setText(rs.getString(2));                                
                                getBtnGuardar().setEnabled(true);
                                encontrado = true;
                                getBtnGuardar().setEnabled(true);
                                break;
                        }
		}                                                                      
            }
            if(encontrado == false){
                getCapturer().stopCapture();
                getTextExp().setText("");
                getTextNombre().setText("");
                getBtnGuardar().setEnabled(false);
                int op = JOptionPane.showConfirmDialog(this, "No se ha encontrado la huella, ¿Desea intentar nuevamente?", 
                        "Aviso", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                if(op == JOptionPane.YES_OPTION || op == JOptionPane.CLOSED_OPTION){
                    getLbHuella().setIcon(null);
                    getCapturer().startCapture();
                }
                else if(op == JOptionPane.NO_OPTION){
                    this.hide();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Verificacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Verificacion.class.getName()).log(Level.SEVERE, null, ex);
        }finally{

        }
    }
    
    private Vector llenarVector(){
        Vector vector = new Vector();
        vector.add(getTextExp().getText());
        vector.add(getTextNombre().getText());
        return vector;
    }
}
