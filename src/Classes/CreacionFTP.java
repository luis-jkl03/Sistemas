
package Classes;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jdk.internal.util.xml.impl.Input;
import jdk.jfr.events.FileWriteEvent;


/**
 *
 * @author XaVi
 */
public class CreacionFTP {
    static String formato = "fpt";
    
    
    public static void main(String []args){
        FileWriter fw = null;
        FileOutputStream stream = null;
        try {
            Properties p = new Properties();
            p.load(new FileReader("src/Classes/globales.properties"));
            
            File dir = new File(p.getProperty("rutaImagenesFPT"));
            if(!dir.exists()){
                dir.mkdirs();
            }
            //mConsole(dir.toString()+"\\gg.fpt");
            String path = dir.toString() + "\\";
            String expediente = "500";
            File file = new File(path + "h-" + expediente + "."+formato);
            mConsole(file.toString());
            if(!file.exists()){
                fw = new FileWriter(file);
                mConsole("Se abre");
            }
            //File f = new File("gow3.jpg");
            //InputStream is = new FileInputStream(f);
            //mConsole(""+f.length());
            byte []b = {'T', 'E'};
            stream = new FileOutputStream(file);
            //for(int i = 0; i < f.length(); i++){
                stream.write(b);
           // }
        } catch (IOException ex) {
            Logger.getLogger(CreacionFTP.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if(fw != null)
                    fw.close();
                stream.close();
            } catch (IOException ex) {
                Logger.getLogger(CreacionFTP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void mConsole(String message){
        System.out.println(message);
    }
    
    public static void mPane(String message){
        JOptionPane.showMessageDialog(null, message);
    }
}
