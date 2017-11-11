package Interfaces;


import Classes.ConexionBase;
import Classes.Enrolar;
import Interfaces.menuClinica;
import java.awt.Frame;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Expediente extends javax.swing.JDialog{

    ConexionBase conexion= new ConexionBase();
    FormCaptura venCapt = new FormCaptura();
    Vector vector = new Vector();
    
    public Expediente()
    {
        
    }
    public Expediente(JFrame Menu,boolean modal) {
        super(Menu,modal);
            initComponents();            
            asignarExpediente();     
            this.setLocationRelativeTo(null);
           
            
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
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        textCP = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnSiguiente = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textExp.setEditable(false);
        textExp.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        textExp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textExp.setToolTipText("");
        textExp.setAutoscrolls(false);
        textExp.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(textExp, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 80, 32));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Expediente ");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, 20));

        textNombre.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        textNombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                textNombreMouseEntered(evt);
            }
        });
        textNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textNombreKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textNombreKeyReleased(evt);
            }
        });
        getContentPane().add(textNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 335, 30));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Nombre ");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, -1));
        getContentPane().add(textEdad, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 230, 50, 23));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Edad");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, -1, -1));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Sexo");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 230, -1, -1));

        comboSexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "M", "F" }));
        getContentPane().add(comboSexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 230, -1, -1));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("Direccion");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, -1, -1));

        textDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textDireccionKeyReleased(evt);
            }
        });
        getContentPane().add(textDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, 510, 30));

        textMunDel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textMunDelKeyReleased(evt);
            }
        });
        getContentPane().add(textMunDel, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 350, 160, 30));

        jLabel9.setFont(new java.awt.Font("Gadugi", 1, 28)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 0, 0));
        jLabel9.setText("Registro Datos Personales Paciente");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, -1, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("Municipio / Delegacion");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, -1, -1));

        textCP.setBackground(java.awt.SystemColor.info);
        textCP.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(textCP, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 350, 100, 30));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setText("C.P");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 360, -1, -1));

        btnSiguiente.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        btnSiguiente.setText("Siguiente");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });
        getContentPane().add(btnSiguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 450, -1, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondoCliniexp.jpg"))); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 480));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
     
        vector.add(textExp.getText());
        vector.add(textNombre.getText().toUpperCase());
        vector.add(textEdad.getText());
        vector.add(comboSexo.getSelectedItem().toString());
        vector.add(textDireccion.getText().toUpperCase());
        vector.add(textMunDel.getText().toUpperCase());
        vector.add(textCP.getText());
        
        camposNulos();
       
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void textNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textNombreKeyReleased
    textNombre.setText(  textNombre.getText().toUpperCase());    }//GEN-LAST:event_textNombreKeyReleased

    private void textNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textNombreKeyPressed
       //textNombre.setText(  textNombre.getText().toUpperCase());
    }//GEN-LAST:event_textNombreKeyPressed

    private void textMunDelKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textMunDelKeyReleased
        textMunDel.setText(textMunDel.getText().toUpperCase());
    }//GEN-LAST:event_textMunDelKeyReleased

    private void textNombreMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textNombreMouseEntered
      
    }//GEN-LAST:event_textNombreMouseEntered

    private void textDireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textDireccionKeyReleased
        textDireccion.setText(textDireccion.getText().toUpperCase());
    }//GEN-LAST:event_textDireccionKeyReleased
    private void camposNulos()
    {
        if(textNombre.getText().isEmpty()||textCP.getText().isEmpty()||textDireccion.getText().isEmpty()||textEdad.getText().isEmpty()||comboSexo.getSelectedItem().equals(" "))
        {           JOptionPane.showMessageDialog(null,"FAVOR DE LLENAR TODOS LOS CAMPOS");
    
        }
        else 
        {    
             //Enrolar enrolar = new Enrolar(vector);        
        //enrolar.setVisible(true);
        this.dispose();
        }
    }
   
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
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField textCP;
    private javax.swing.JTextField textDireccion;
    private javax.swing.JTextField textEdad;
    private javax.swing.JTextField textExp;
    private javax.swing.JTextField textMunDel;
    private javax.swing.JTextField textNombre;
    // End of variables declaration//GEN-END:variables
}
