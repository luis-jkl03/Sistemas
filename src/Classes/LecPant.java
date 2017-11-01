package Interfaces;

import Classes.ConexionBase;
import Classes.EstadoLector;
import Classes.datosHuella;
import Classes.estadoDedo;
import Classes.imagenQuality;
import com.digitalpersona.onetouch.*;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LecPant extends javax.swing.JFrame{

    //private DPFPCapture Lector = DPFPGlobal.getCaptureFactory().createCapture();
    int g;
   public static DPFPCapture Lector = DPFPGlobal.getCaptureFactory().createCapture();    
    
   public EstadoLector estLector;
   public estadoDedo estDedo;
   public imagenQuality imQuality;
   public datosHuella datHuellas;
   public static int expediente;
   
   public ConexionBase conBase=new ConexionBase();
    
    public LecPant(Vector vector) {
  
        initComponents();       
        estLector = new EstadoLector();
        
        estDedo = new estadoDedo();
        
        imQuality = new imagenQuality();
        
        datHuellas=new datosHuella(labHuella,textExp,vector);
        
        this.expediente = Integer.parseInt(vector.get(0).toString());
        textExp.setText(""+expediente);
        textNombre.setText(vector.get(1).toString());
      
        Lector.startCapture();
        textExp.setEditable(false);
        textNombre.setEditable(false);
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(700, 600));
        setSize(new java.awt.Dimension(700, 500));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labHuella.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        getContentPane().add(labHuella, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 150, 180));

        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, -1, -1));

        jLabel1.setText("Expediente ");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 40, 75, 35));

        textExp.setToolTipText("");
        getContentPane().add(textExp, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, 100, 30));

        jLabel2.setText("Paciente");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, -1, -1));
        getContentPane().add(textNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 100, 270, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
        String nexp;
        nexp=textExp.getText();
        System.out.println("este es "+nexp);
        /*try {
           Connection con= ConexionBase.getConection();
              File archivo = new File ("C:\\Huellas\\JPG");
              FileReader fr = new FileReader (archivo);
              BufferedReader br = new BufferedReader(fr);
              } catch (Exception ex) {
           Logger.getLogger(LecPant.class.getName()).log(Level.SEVERE, null, ex);
       }*/
/*String linea = br.readLine();
            PreparedStatement ps = con.prepareStatement("insert into PERPACIENTE values (?,?,?,?)");
           ps.setBinaryStream(1,stream, WIDTH);*/
       datHuellas.insertarTablas();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    /*public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        /*try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LecPant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LecPant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LecPant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LecPant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }*/
        //</editor-fold>

        /* Create and display the form */
        /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LecPant().setVisible(true);
            }
        });
    } */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel labHuella;
    private javax.swing.JTextField textExp;
    private javax.swing.JTextField textNombre;
    // End of variables declaration//GEN-END:variables


}
