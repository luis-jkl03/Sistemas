
package Interfaces;

import Classes.Actualizacion;
import Classes.ConexionBase;
import java.awt.Frame;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class ActualizarHuella extends javax.swing.JDialog {

    Frame parent;
    public ActualizarHuella(Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.parent = parent;
        setLocationRelativeTo(null);
        scrollPaneLista.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        scrollPaneLista = new javax.swing.JScrollPane();
        lista = new javax.swing.JList();
        textBuscar = new javax.swing.JTextField();
        radioNombre = new javax.swing.JRadioButton();
        radioExp = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        textExpediente = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        textNombre = new javax.swing.JTextField();
        btnContinuar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Busqueda");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 70, 32));

        scrollPaneLista.setPreferredSize(new java.awt.Dimension(1, 128));

        scrollPaneLista.setViewportView(lista);

        getContentPane().add(scrollPaneLista, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 65, 299, 110));

        textBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textBuscarKeyReleased(evt);
            }
        });
        getContentPane().add(textBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 299, -1));

        radioNombre.setText("Nombre");
        radioNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioNombreActionPerformed(evt);
            }
        });
        getContentPane().add(radioNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 40, -1, -1));

        radioExp.setSelected(true);
        radioExp.setText("Expediente");
        radioExp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioExpActionPerformed(evt);
            }
        });
        getContentPane().add(radioExp, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 40, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Expediente");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 70, 32));

        textExpediente.setEditable(false);
        getContentPane().add(textExpediente, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 299, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Nombre");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 70, 32));

        textNombre.setEditable(false);
        getContentPane().add(textNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 299, -1));

        btnContinuar.setText("Continuar");
        btnContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinuarActionPerformed(evt);
            }
        });
        getContentPane().add(btnContinuar, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 110, 110, 30));

        btnBuscar.setText("Busqueda");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 40, 110, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void radioExpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioExpActionPerformed
        if(radioNombre.isSelected()){
            radioNombre.setSelected(false);
        }
            
    }//GEN-LAST:event_radioExpActionPerformed

    private void radioNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioNombreActionPerformed
        if(radioExp.isSelected()){
            radioExp.setSelected(false);
        }
    }//GEN-LAST:event_radioNombreActionPerformed

    private void textBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textBuscarKeyReleased
        if(radioNombre.isSelected()){
            char c = evt.getKeyChar();
            if(c < 'A' || c > 'Z'){
                evt.consume();
            }
            textBuscar.setText(textBuscar.getText().toUpperCase());
            Connection con = null;
            PreparedStatement pst = null;
            ResultSet rs = null;
            Vector nombres = new Vector();
            boolean encontrado = false;

            try {    
            con = ConexionBase.getConection();
            pst = con.prepareStatement("SELECT NOM_PACIENTE FROM PERPACIENTE WHERE NOM_PACIENTE LIKE '" +
                    textBuscar.getText() +"%'");
            rs = pst.executeQuery();
            while(rs.next()){
                encontrado = true;
                nombres.add(rs.getString(1));
            }

            if(encontrado){
                scrollPaneLista.setVisible(true);
                lista.setListData(nombres);
            }
            if(textBuscar.getText().isEmpty() || encontrado == false){
                scrollPaneLista.setVisible(false);
            }
            } catch (SQLException ex) {
                Logger.getLogger(ActualizarHuella.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    if(rs != null)
                        rs.close();
                    if(pst != null)
                        pst.close();
                    if(con != null)
                        con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ActualizarHuella.class.getName()).log(Level.SEVERE, null, ex);
                }
            }            
        }
    }//GEN-LAST:event_textBuscarKeyReleased

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed

            textExpediente.setText("");
            textNombre.setText("");
            if(textBuscar.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Ingrese un valor de busqueda", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String filtro = "";
            if(radioExp.isSelected()){
                filtro = "WHERE EXPEDIENTE" + " = " + textBuscar.getText();
            }
            else{
                filtro = "WHERE NOM_PACIENTE" + " = " + "'"+ textBuscar.getText()+"'";
            }
            Connection con = null;
            PreparedStatement pst = null;
            ResultSet rs = null;
            try {
                con = ConexionBase.getConection();
                pst = con.prepareStatement("SELECT EXPEDIENTE, NOM_PACIENTE FROM PERPACIENTE " + filtro);
                rs = pst.executeQuery();
                boolean encontrado = false;
                while(rs.next()){
                    encontrado = true;
                    textExpediente.setText(rs.getString(1));
                    textNombre.setText(rs.getString(2));
                }
                
                if(!encontrado){
                    JOptionPane.showMessageDialog(this, "No se encontro ningun resultado", "Fallo en busqueda", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
        } catch (SQLException ex) {
            Logger.getLogger(ActualizarHuella.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
                try {
                    if(rs != null)
                        rs.close();
                    if(pst != null)
                        pst.close();
                    if(con != null)
                        con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ActualizarHuella.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarActionPerformed
        //ActualizarHuellaBase base = new ActualizarHuellaBase((JPanel) this.getContentPane());
        //this.setContentPane(base);
        //this.repaint();
        //pack();
        //setLocationRelativeTo(null);
        Actualizacion actualizar = new Actualizacion(parent, new Integer(textExpediente.getText()));
        actualizar.getTextExp().setText(textExpediente.getText());
        actualizar.getTextNombre().setText(textNombre.getText());
        actualizar.setVisible(true);
        //this.setContentPane(actualizar.getContentPane());
        //this.repaint();
        //pack();
        //actualizar.setVisible(true);
        //setLocationRelativeTo(null);
    }//GEN-LAST:event_btnContinuarActionPerformed

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
            java.util.logging.Logger.getLogger(ActualizarHuella.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ActualizarHuella.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ActualizarHuella.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ActualizarHuella.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ActualizarHuella dialog = new ActualizarHuella(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnContinuar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList lista;
    private javax.swing.JRadioButton radioExp;
    private javax.swing.JRadioButton radioNombre;
    private javax.swing.JScrollPane scrollPaneLista;
    private javax.swing.JTextField textBuscar;
    private javax.swing.JTextField textExpediente;
    private javax.swing.JTextField textNombre;
    // End of variables declaration//GEN-END:variables
}
