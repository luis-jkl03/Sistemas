
package Interfaces;

import Classes.ConexionBase;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class FormLogin extends javax.swing.JFrame {
    
    String usuario;

    public FormLogin() {
        setUndecorated(true);
        initComponents(); 
        setLocationRelativeTo(null);        
        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/Imagenes/iconoFrame.png")));        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAceptar = new javax.swing.JButton();
        textPass = new javax.swing.JPasswordField();
        textUsuario = new javax.swing.JTextField();
        BtnCerrar = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(600, 500));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAceptar.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ingreson.png"))); // NOI18N
        btnAceptar.setBorder(null);
        btnAceptar.setBorderPainted(false);
        btnAceptar.setContentAreaFilled(false);
        btnAceptar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 317, -1, 50));

        textPass.setFont(new java.awt.Font("Arial Black", 1, 13)); // NOI18N
        textPass.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 255), null));
        textPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textPassKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textPassKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textPassKeyTyped(evt);
            }
        });
        getContentPane().add(textPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 260, 200, 30));

        textUsuario.setFont(new java.awt.Font("Arial Black", 1, 13)); // NOI18N
        textUsuario.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 255), null));
        textUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textUsuarioKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textUsuarioKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textUsuarioKeyTyped(evt);
            }
        });
        getContentPane().add(textUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 220, 200, 30));

        BtnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cerrar1.jpg"))); // NOI18N
        BtnCerrar.setContentAreaFilled(false);
        BtnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCerrarActionPerformed(evt);
            }
        });
        getContentPane().add(BtnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 0, 30, 30));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconoCerrar.png"))); // NOI18N
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 0, 25, 27));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/nlogin.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 400));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        camposNulos();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void textUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textUsuarioKeyTyped
        restringir(evt, 30);
    }//GEN-LAST:event_textUsuarioKeyTyped

    private void textPassKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textPassKeyTyped
        restringir(evt, 30);
    }//GEN-LAST:event_textPassKeyTyped

    private void textUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textUsuarioKeyReleased
        ctrlArriba(evt);
    }//GEN-LAST:event_textUsuarioKeyReleased

    private void textPassKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textPassKeyReleased
        ctrlArriba(evt);
    }//GEN-LAST:event_textPassKeyReleased

    private void textUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textUsuarioKeyPressed
        restringirTeclas(evt);
    }//GEN-LAST:event_textUsuarioKeyPressed

    private void textPassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textPassKeyPressed
        restringirTeclas(evt);
    }//GEN-LAST:event_textPassKeyPressed

    private void BtnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCerrarActionPerformed
        int op = JOptionPane.showConfirmDialog(this, "Esta a punto de salir del sistema, ¿desea continuar?","Atención",
            JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
        if(op == JOptionPane.YES_OPTION)
            System.exit(0);
    }//GEN-LAST:event_BtnCerrarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        setExtendedState(JFrame.ICONIFIED);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void camposNulos()
    {
        javax.swing.border.Border redBord = BorderFactory.createEtchedBorder(BevelBorder.LOWERED, Color.RED, null);
        javax.swing.border.Border blueBord = BorderFactory.createEtchedBorder(BevelBorder.LOWERED, new Color(102,102,255), null);

        if(textUsuario.getText().isEmpty()) textUsuario.setBorder(redBord);
        else textUsuario.setBorder(blueBord);
        if(textPass.getText().isEmpty()) textPass.setBorder(redBord);
        else textPass.setBorder(blueBord);
        
        if(textUsuario.getText().isEmpty()||textPass.getText().isEmpty()){    
            JOptionPane.showMessageDialog(this,"Favor de llenar todos los campos","Atención",JOptionPane.WARNING_MESSAGE);  
            return;
        }
        else if(textUsuario.getText().length() < 6){
            JOptionPane.showMessageDialog(this,"El usuario debe tener minimo 8 digitos","Atención",JOptionPane.ERROR_MESSAGE);  
            return;
        }
        else if(textPass.getText().length() < 8){
            JOptionPane.showMessageDialog(this,"La contraseña debe tener minimo 8 digitos","Atención",JOptionPane.ERROR_MESSAGE);  
            return;
        }
        else if(!validarDatos()){
            JOptionPane.showMessageDialog(this,"Usuario o contraseña incorrectos","Atención",JOptionPane.ERROR_MESSAGE);  
            return;
        }
        else {
            new FormMenu(usuario).setVisible(true);
            this.dispose();
        }
    }
    
    private boolean validarDatos(){
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean validado = false;
        try {
            con = new ConexionBase().getConection();
            pst = con.prepareStatement("SELECT USUARIO,PASS FROM ADMPERSONAL");
            rs = pst.executeQuery();
            
            while(rs.next()){
                if(rs.getString(1).equals(textUsuario.getText()) && rs.getString(2).equals(textPass.getText())){
                    validado = true;
                    usuario = textUsuario.getText();
                    break;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FormLogin.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if(rs != null)
                    rs.close();
                if(pst != null)
                    pst.close();
                if(con != null)
                    con.close();
            } catch (SQLException ex) {
                Logger.getLogger(FormLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return validado;
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
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCerrar;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField textPass;
    private javax.swing.JTextField textUsuario;
    // End of variables declaration//GEN-END:variables
}
