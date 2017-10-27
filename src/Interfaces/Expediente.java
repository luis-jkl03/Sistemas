package Interfaces;


import Classes.ConexionBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Expediente extends javax.swing.JFrame {

    LecPant frHuella;
    ConexionBase conexion= new ConexionBase();
    
    public Expediente() {
        initComponents();
            asignarExpediente();        
            textExp.setEditable(false);
        
    }

    public void asignarExpediente()
    {Connection con = ConexionBase.getConection();
        try {
            PreparedStatement st= con.prepareStatement("select max(EXPEDIENTE) FROM PERPACIENTE");
            ResultSet rs = st.executeQuery();
                while (rs.next())     
                {
                    if(rs.getString(1) == null)
                        textExp.setText("1");
                        
                    else{
                    System.out.println(rs.getString(1));
                    int exp = new Integer(rs.getString(1)) + 1;
                     textExp.setText(""+exp);
                    }
                }          
                
                 
                    } catch (SQLException ex) {
            Logger.getLogger(Expediente.class.getName()).log(Level.SEVERE, null, ex);
        }finally
        {
        try {
            if(!con.isClosed())
                
                con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Expediente.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textExp = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        textNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        textEdad = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        comboSexo = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        textDireccion = new javax.swing.JTextField();
        textMunDel = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        textCP = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnSiguiente = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textExp.setToolTipText("");
        getContentPane().add(textExp, new org.netbeans.lib.awtextra.AbsoluteConstraints(117, 61, 144, 32));

        jLabel1.setText("Expediente ");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 60, 75, 35));
        getContentPane().add(textNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(117, 123, 335, 30));

        jLabel2.setText("Nombre");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 131, -1, -1));
        getContentPane().add(textEdad, new org.netbeans.lib.awtextra.AbsoluteConstraints(117, 184, 50, 23));

        jLabel3.setText("Edad");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 184, -1, -1));

        jLabel4.setText("Sexo");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(219, 188, -1, -1));

        comboSexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "M", "F" }));
        getContentPane().add(comboSexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(269, 185, -1, -1));

        jLabel5.setText("Direccion");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 248, -1, -1));
        getContentPane().add(textDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, 510, 30));
        getContentPane().add(textMunDel, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 310, 160, 30));

        jLabel6.setText("Municipio / Delegacion");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, -1, -1));
        getContentPane().add(textCP, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 320, 60, 20));

        jLabel7.setText("C.P");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 320, -1, -1));

        btnSiguiente.setText("Siguiente");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });
        getContentPane().add(btnSiguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 450, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        Vector vector = new Vector();
        vector.add(textExp.getText());
        vector.add(textNombre.getText());
        vector.add(textEdad.getText());
        vector.add(comboSexo.getSelectedItem().toString());
        vector.add(textDireccion.getText());
        vector.add(textMunDel.getText());
        vector.add(textCP.getText());
        
        this.setVisible(false);
        frHuella = new LecPant(vector);
        frHuella.setVisible(true);
    }//GEN-LAST:event_btnSiguienteActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Expediente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Expediente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Expediente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Expediente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Expediente().setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JComboBox comboSexo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField textCP;
    private javax.swing.JTextField textDireccion;
    private javax.swing.JTextField textEdad;
    private javax.swing.JTextField textExp;
    private javax.swing.JTextField textMunDel;
    private javax.swing.JTextField textNombre;
    // End of variables declaration//GEN-END:variables
}
