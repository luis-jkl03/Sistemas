
package Interfaces;

import Classes.ConexionBase;
import Classes.Enrolar;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.Border;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;


public class FormPersonales extends javax.swing.JDialog {

    ConexionBase conexion;
    Frame menu;
    public FormPersonales(Frame parent, boolean modal) {
        super(parent, modal);
        this.setUndecorated(true);
        initComponents();
        asignarExpediente(); 
        this.setLocationRelativeTo(null);
        menu = parent;   
        textNombre.requestFocus();
        lbVacioSexo.setVisible(false);
    }

    public void asignarExpediente(){
        Connection con = null;
        try {
            con = new ConexionBase().getConection();
            PreparedStatement st= con.prepareStatement("select max(EXPEDIENTE) FROM PERPACIENTE");
            ResultSet rs = st.executeQuery();
                while (rs.next())     
                {
                    if(rs.getString(1) == null)
                        textExp.setText("1");
                        
                    else{
                    int exp = new Integer(rs.getString(1)) + 1;
                     textExp.setText(""+exp);
                    }
                }                                  
        } catch (SQLException ex) {
            Logger.getLogger(FormPersonales.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if(con != null)                
                    con.close();
                } catch (SQLException ex) {
            Logger.getLogger(FormPersonales.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        textExp = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        textNombre = new javax.swing.JTextField();
        textEdad = new javax.swing.JTextField();
        BtnCerrar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        comboSexo = new javax.swing.JComboBox();
        textDireccion = new javax.swing.JTextField();
        btnSiguiente = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        textCP = new javax.swing.JTextField();
        textMunDel = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        lbVacioSexo = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Gadugi", 1, 28)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 0, 0));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Registro de datos personales del paciente");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 550, -1));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Expediente ");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 125, -1, 20));

        textExp.setEditable(false);
        textExp.setFont(new java.awt.Font("Arial Black", 1, 13)); // NOI18N
        textExp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textExp.setToolTipText("");
        textExp.setAutoscrolls(false);
        textExp.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 255), null));
        textExp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(textExp, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 80, 32));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Nombre ");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 175, -1, -1));

        textNombre.setFont(new java.awt.Font("Arial Black", 1, 13)); // NOI18N
        textNombre.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 255), null));
        textNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textNombreKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textNombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textNombreKeyTyped(evt);
            }
        });
        getContentPane().add(textNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 420, 30));

        textEdad.setFont(new java.awt.Font("Arial Black", 1, 13)); // NOI18N
        textEdad.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 255), null));
        textEdad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textEdadKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textEdadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textEdadKeyTyped(evt);
            }
        });
        getContentPane().add(textEdad, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, 50, 30));

        BtnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cerrar1.jpg"))); // NOI18N
        BtnCerrar.setContentAreaFilled(false);
        BtnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCerrarActionPerformed(evt);
            }
        });
        getContentPane().add(BtnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 0, 30, 30));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Edad");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 225, -1, -1));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Sexo");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 224, -1, -1));

        comboSexo.setFont(new java.awt.Font("Arial Black", 1, 13)); // NOI18N
        comboSexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "M", "F" }));
        getContentPane().add(comboSexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 220, -1, -1));

        textDireccion.setFont(new java.awt.Font("Arial Black", 1, 13)); // NOI18N
        textDireccion.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 255), null));
        textDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textDireccionKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textDireccionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textDireccionKeyTyped(evt);
            }
        });
        getContentPane().add(textDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 270, 510, 30));

        btnSiguiente.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnSiguiente.setText("Siguiente");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });
        getContentPane().add(btnSiguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 360, -1, 40));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("Direccion");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 275, -1, -1));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setText("C.P");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 328, -1, -1));

        textCP.setBackground(java.awt.SystemColor.info);
        textCP.setFont(new java.awt.Font("Arial Black", 1, 13)); // NOI18N
        textCP.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 255), null));
        textCP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textCPKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textCPKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textCPKeyTyped(evt);
            }
        });
        getContentPane().add(textCP, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 320, 100, 30));

        textMunDel.setFont(new java.awt.Font("Arial Black", 1, 13)); // NOI18N
        textMunDel.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 255), null));
        textMunDel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textMunDelKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textMunDelKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textMunDelKeyTyped(evt);
            }
        });
        getContentPane().add(textMunDel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 320, 280, 30));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("Delegacion / Municipio");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 325, -1, -1));

        lbVacioSexo.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        lbVacioSexo.setForeground(new java.awt.Color(255, 51, 51));
        lbVacioSexo.setText("*");
        getContentPane().add(lbVacioSexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 220, -1, 30));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondoCliniexp.jpg"))); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 470));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textNombreKeyReleased
        ctrlArriba(evt);
        //if(evt.getKeyCode() >= KeyEvent.VK_A && evt.getKeyCode() <= KeyEvent.VK_Z)
            //textNombre.setText(textNombre.getText().toUpperCase());
    }//GEN-LAST:event_textNombreKeyReleased

    private void textDireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textDireccionKeyReleased
        ctrlArriba(evt);
        //textDireccion.setText(textDireccion.getText().toUpperCase());
    }//GEN-LAST:event_textDireccionKeyReleased

    private void textMunDelKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textMunDelKeyReleased
        ctrlArriba(evt);
        //textMunDel.setText(textMunDel.getText().toUpperCase());
    }//GEN-LAST:event_textMunDelKeyReleased

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        camposNulos();
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void textNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textNombreKeyTyped
        soloLetrasYEspacio(evt);
        restringir(evt, 50);
    }//GEN-LAST:event_textNombreKeyTyped

    private void textEdadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textEdadKeyTyped
        restringir(evt, 2);
        soloNumeros(evt);
    }//GEN-LAST:event_textEdadKeyTyped

    private void textDireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textDireccionKeyTyped
        restringir(evt, 60);
    }//GEN-LAST:event_textDireccionKeyTyped

    private void textMunDelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textMunDelKeyTyped
        soloLetrasYEspacio(evt);
        restringir(evt, 20);
    }//GEN-LAST:event_textMunDelKeyTyped

    private void textCPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textCPKeyTyped
        restringir(evt, 5);
        soloNumeros(evt);
    }//GEN-LAST:event_textCPKeyTyped

    private void textNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textNombreKeyPressed
        restringirTeclas(evt);
    }//GEN-LAST:event_textNombreKeyPressed

    private void textEdadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textEdadKeyPressed
        restringirTeclas(evt);
    }//GEN-LAST:event_textEdadKeyPressed

    private void textDireccionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textDireccionKeyPressed
        restringirTeclas(evt);
    }//GEN-LAST:event_textDireccionKeyPressed

    private void textMunDelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textMunDelKeyPressed
        restringirTeclas(evt);
    }//GEN-LAST:event_textMunDelKeyPressed

    private void textCPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textCPKeyPressed
        restringirTeclas(evt);
    }//GEN-LAST:event_textCPKeyPressed

    private void textEdadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textEdadKeyReleased
        ctrlArriba(evt);
    }//GEN-LAST:event_textEdadKeyReleased

    private void textCPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textCPKeyReleased
        ctrlArriba(evt);
    }//GEN-LAST:event_textCPKeyReleased

    private void BtnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_BtnCerrarActionPerformed
 
    private void camposNulos()
    {
        javax.swing.border.Border redBord = BorderFactory.createEtchedBorder(BevelBorder.LOWERED, Color.RED, null);
        javax.swing.border.Border blueBord = BorderFactory.createEtchedBorder(BevelBorder.LOWERED, new Color(102,102,255), null);

        if(textNombre.getText().isEmpty()) textNombre.setBorder(redBord);
        else textNombre.setBorder(blueBord);
        if(textEdad.getText().isEmpty()) textEdad.setBorder(redBord);
        else textEdad.setBorder(blueBord);
        if(textDireccion.getText().isEmpty()) textDireccion.setBorder(redBord);
        else textDireccion.setBorder(blueBord);
        if(textMunDel.getText().isEmpty()) textMunDel.setBorder(redBord);
        else textMunDel.setBorder(blueBord);
        if(textCP.getText().isEmpty()) textCP.setBorder(redBord);
        else textCP.setBorder(blueBord);
        if(comboSexo.getSelectedIndex() == 0) lbVacioSexo.setVisible(true);
        else lbVacioSexo.setVisible(false);
        if(textNombre.getText().isEmpty()||textCP.getText().isEmpty()||textDireccion.getText().isEmpty()||
           textEdad.getText().isEmpty()||comboSexo.getSelectedItem().equals(" ")||textMunDel.getText().isEmpty()){    
            JOptionPane.showMessageDialog(this,"Favor de llenar todos los campos","Atención",JOptionPane.WARNING_MESSAGE);                   
        }
        else {
            Enrolar enrolar = new Enrolar(llenarVector(), menu, this);  
            enrolar.setVisible(true);
        }
    }
    
    private Vector llenarVector(){
        Vector vector = new Vector();
        vector.add(textExp.getText());
        vector.add(textNombre.getText().toUpperCase());
        vector.add(textEdad.getText());
        vector.add(comboSexo.getSelectedItem().toString());
        vector.add(textDireccion.getText().toUpperCase());
        vector.add(textMunDel.getText().toUpperCase());
        vector.add(textCP.getText());
        
        return vector;
    }
    
    private void soloNumeros(java.awt.event.KeyEvent e){
        char c = e.getKeyChar();
        if(c < '0' || c > '9'){
            e.consume();
        }
    }
    
    private void soloLetrasYEspacio(java.awt.event.KeyEvent e){
        char c = e.getKeyChar();
        if((c < 'A' || c > 'Z') && (c < 'a' || c > 'z') && (c < ' ' || c > ' ')){
            e.consume();
        }
    }
    
    private void restringir(KeyEvent evt, int tam) {
        if(((JTextField)evt.getComponent()).getText().length() >= tam){
            evt.consume();
        }
    }
   boolean pres;
    public void restringirTeclas(java.awt.event.KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_CONTROL){
            pres = true;
        }else{
            if(pres && e.getKeyCode()==KeyEvent.VK_V){
                e.consume();
            }
        }
    }
    
    private void ctrlArriba(java.awt.event.KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_CONTROL)
            pres = false;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCerrar;
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
    private javax.swing.JLabel lbVacioSexo;
    private javax.swing.JTextField textCP;
    private javax.swing.JTextField textDireccion;
    private javax.swing.JTextField textEdad;
    private javax.swing.JTextField textExp;
    private javax.swing.JTextField textMunDel;
    private javax.swing.JTextField textNombre;
    // End of variables declaration//GEN-END:variables
}
