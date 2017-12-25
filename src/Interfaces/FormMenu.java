
package Interfaces;

import Classes.Enrolar;
import Classes.Verificacion;
import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FormMenu extends javax.swing.JFrame {

     String usuario;     
     
    public FormMenu(){
        initComponents();
        new FormLogin().setVisible(true);
    }
    public FormMenu(String usuario) {
        this.setUndecorated(true);
        initComponents();
        this.setLocationRelativeTo(null); 
        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/Imagenes/iconoFrame.png")));     
        this.usuario = usuario;
        lbUsuario.setText(this.usuario);
    }
          
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BtnCerrar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        lbUsuario = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        lbRegistro = new javax.swing.JLabel();
        lbConsulta = new javax.swing.JLabel();
        lbActualizar = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MENU PRINCIPAL");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cerrar1.jpg"))); // NOI18N
        BtnCerrar.setContentAreaFilled(false);
        BtnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCerrarActionPerformed(evt);
            }
        });
        getContentPane().add(BtnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 0, 30, 30));

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel2.setText("Usuario: ");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 50, -1, -1));

        lbUsuario.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        getContentPane().add(lbUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 52, 210, 20));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconoCerrar.png"))); // NOI18N
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 2, 25, 27));

        lbRegistro.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        lbRegistro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconoRegistro.jpg"))); // NOI18N
        lbRegistro.setText("Registrar paciente");
        lbRegistro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbRegistro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbRegistroMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbRegistroMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbRegistroMouseExited(evt);
            }
        });
        getContentPane().add(lbRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 180, 350, -1));

        lbConsulta.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        lbConsulta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconoConsulta.jpg"))); // NOI18N
        lbConsulta.setText("Consulta de paciente");
        lbConsulta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbConsulta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbConsultaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbConsultaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbConsultaMouseExited(evt);
            }
        });
        getContentPane().add(lbConsulta, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 300, 370, -1));

        lbActualizar.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        lbActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iconoHuella.jpg"))); // NOI18N
        lbActualizar.setText("Actualizar huellas");
        lbActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbActualizarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbActualizarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbActualizarMouseExited(evt);
            }
        });
        getContentPane().add(lbActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 430, 340, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fomenu.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCerrarActionPerformed
       int op = JOptionPane.showConfirmDialog(this, "Se cerrara la sesion, ¿desea continuar?","Atención",
                JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
        if(op == JOptionPane.YES_OPTION){
            new FormLogin().setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_BtnCerrarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        setExtendedState(JFrame.ICONIFIED);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void lbRegistroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbRegistroMouseEntered
        lbRegistro.setIcon(new ImageIcon(this.getClass().getResource("/Imagenes/iconoRegistroG.jpg")));
    }//GEN-LAST:event_lbRegistroMouseEntered

    private void lbRegistroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbRegistroMouseExited
        lbRegistro.setIcon(new ImageIcon(this.getClass().getResource("/Imagenes/iconoRegistro.jpg")));
    }//GEN-LAST:event_lbRegistroMouseExited

    private void lbConsultaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbConsultaMouseEntered
        lbConsulta.setIcon(new ImageIcon(this.getClass().getResource("/Imagenes/iconoConsultaG.jpg")));
    }//GEN-LAST:event_lbConsultaMouseEntered

    private void lbConsultaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbConsultaMouseExited
        lbConsulta.setIcon(new ImageIcon(this.getClass().getResource("/Imagenes/iconoConsulta.jpg")));
    }//GEN-LAST:event_lbConsultaMouseExited

    private void lbActualizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbActualizarMouseEntered
        lbActualizar.setIcon(new ImageIcon(this.getClass().getResource("/Imagenes/iconoHuellaG.jpg")));
    }//GEN-LAST:event_lbActualizarMouseEntered

    private void lbActualizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbActualizarMouseExited
        lbActualizar.setIcon(new ImageIcon(this.getClass().getResource("/Imagenes/iconoHuella.jpg")));
    }//GEN-LAST:event_lbActualizarMouseExited

    private void lbConsultaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbConsultaMouseClicked
        new Verificacion(this).setVisible(true);
    }//GEN-LAST:event_lbConsultaMouseClicked

    private void lbRegistroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbRegistroMouseClicked
        new FormPersonales(this).setVisible(true);
    }//GEN-LAST:event_lbRegistroMouseClicked

    private void lbActualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbActualizarMouseClicked
        new FormBuscarDatos(this).setVisible(true);
    }//GEN-LAST:event_lbActualizarMouseClicked
    
   
    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(FormMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormMenu().setVisible(false);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCerrar;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lbActualizar;
    private javax.swing.JLabel lbConsulta;
    private javax.swing.JLabel lbRegistro;
    private javax.swing.JLabel lbUsuario;
    // End of variables declaration//GEN-END:variables
}
