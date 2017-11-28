
package Interfaces;

import Classes.Enrolar;
import Classes.Verificacion;

public class FormMenu extends javax.swing.JFrame {

     FormPersonales exp = null;
     Enrolar enro=null;
     FormCaptura cap=null;
     
    public FormMenu() {
        this.setUndecorated(true);
        initComponents();
        this.setLocationRelativeTo(null);                
    }
          
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BtnRegistro = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        BtnConsulta = new javax.swing.JButton();
        BtnCerrar = new javax.swing.JButton();
        jLabelTitu = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MENU PRINCIPAL");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnRegistro.setFont(new java.awt.Font("Arial Black", 0, 13)); // NOI18N
        BtnRegistro.setForeground(new java.awt.Color(204, 0, 0));
        BtnRegistro.setText("Registrar Paciente");
        BtnRegistro.setContentAreaFilled(false);
        BtnRegistro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRegistroActionPerformed(evt);
            }
        });
        getContentPane().add(BtnRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 180, 40));

        btnActualizar.setFont(new java.awt.Font("Arial Black", 0, 13)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(204, 0, 0));
        btnActualizar.setText("Actualizar Huellas");
        btnActualizar.setContentAreaFilled(false);
        btnActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        getContentPane().add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, -1, -1));

        BtnConsulta.setFont(new java.awt.Font("Arial Black", 0, 13)); // NOI18N
        BtnConsulta.setForeground(new java.awt.Color(204, 0, 0));
        BtnConsulta.setText("Consulta Paciente");
        BtnConsulta.setContentAreaFilled(false);
        BtnConsulta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnConsultaActionPerformed(evt);
            }
        });
        getContentPane().add(BtnConsulta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, -1, -1));

        BtnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cerrar1.jpg"))); // NOI18N
        BtnCerrar.setContentAreaFilled(false);
        BtnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCerrarActionPerformed(evt);
            }
        });
        getContentPane().add(BtnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 0, 30, 30));

        jLabelTitu.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabelTitu.setForeground(new java.awt.Color(204, 0, 0));
        jLabelTitu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitu.setText("MENU PRINCIPAL");
        getContentPane().add(jLabelTitu, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 750, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fmenu.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegistroActionPerformed
      exp = new FormPersonales(this,true);
      exp.setVisible(true);
    }//GEN-LAST:event_BtnRegistroActionPerformed

    private void BtnConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnConsultaActionPerformed
        new Verificacion(this).setVisible(true);
    }//GEN-LAST:event_BtnConsultaActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        new FormBuscarDatos(this, true).setVisible(true);
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void BtnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCerrarActionPerformed
       this.dispose();
    }//GEN-LAST:event_BtnCerrarActionPerformed
    
   
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
                new FormMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCerrar;
    private javax.swing.JButton BtnConsulta;
    private javax.swing.JButton BtnRegistro;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelTitu;
    // End of variables declaration//GEN-END:variables
}
