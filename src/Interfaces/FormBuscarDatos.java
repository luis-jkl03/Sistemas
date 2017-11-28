
package Interfaces;

import Classes.Actualizacion;
import Classes.ConexionBase;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class FormBuscarDatos extends javax.swing.JDialog {

    Frame parent;
    Vector nombres;
    int tam;
    boolean pres;
    public FormBuscarDatos(Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.parent = parent;
        setLocationRelativeTo(null);
        setResizable(false);
        scrollPaneLista.setVisible(false);
        nombres = rellenarNombres();
        tam = 8;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        textBuscar = new javax.swing.JTextField();
        radioNombre = new javax.swing.JRadioButton();
        radioExp = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        scrollPaneLista = new javax.swing.JScrollPane();
        lista = new javax.swing.JList();
        textExpediente = new javax.swing.JTextField();
        textNombre = new javax.swing.JTextField();
        btnContinuar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Busqueda");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 80, 32));

        textBuscar.setFont(new java.awt.Font("Arial Black", 1, 13)); // NOI18N
        textBuscar.setToolTipText("Busqueda por expediente solo permite escribir numeros");
        textBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textBuscarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textBuscarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textBuscarKeyTyped(evt);
            }
        });
        getContentPane().add(textBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 299, 30));

        radioNombre.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        radioNombre.setText("Nombre");
        radioNombre.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        radioNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioNombreActionPerformed(evt);
            }
        });
        getContentPane().add(radioNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 130, -1, -1));

        radioExp.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        radioExp.setSelected(true);
        radioExp.setText("Expediente");
        radioExp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        radioExp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioExpActionPerformed(evt);
            }
        });
        getContentPane().add(radioExp, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 130, -1, -1));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Expediente");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 80, 32));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Nombre");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 60, 32));

        scrollPaneLista.setPreferredSize(new java.awt.Dimension(1, 128));

        lista.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lista.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lista.setVisibleRowCount(5);
        lista.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                listaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                listaFocusLost(evt);
            }
        });
        lista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaMouseClicked(evt);
            }
        });
        lista.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                listaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                listaKeyReleased(evt);
            }
        });
        scrollPaneLista.setViewportView(lista);

        getContentPane().add(scrollPaneLista, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 300, -1));

        textExpediente.setEditable(false);
        textExpediente.setBackground(new java.awt.Color(204, 204, 255));
        textExpediente.setFont(new java.awt.Font("Arial Black", 1, 13)); // NOI18N
        textExpediente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textExpediente.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 255), null));
        textExpediente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(textExpediente, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 100, 30));

        textNombre.setEditable(false);
        textNombre.setBackground(new java.awt.Color(204, 204, 255));
        textNombre.setFont(new java.awt.Font("Arial Black", 1, 13)); // NOI18N
        textNombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textNombre.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 255), null));
        textNombre.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().add(textNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 299, 30));

        btnContinuar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnContinuar.setText("Continuar");
        btnContinuar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnContinuar.setEnabled(false);
        btnContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinuarActionPerformed(evt);
            }
        });
        getContentPane().add(btnContinuar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 180, 110, 30));

        btnBuscar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 180, 110, 30));

        jLabel9.setFont(new java.awt.Font("Gadugi", 1, 28)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 0, 0));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Busqueda de datos");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 670, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Buscar por:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 90, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void radioExpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioExpActionPerformed
       radioExp.setSelected(true);
       textBuscar.setToolTipText("Busqueda por expediente solo permite escribir numeros");
       tam = 8;
        if(radioNombre.isSelected()){
            radioNombre.setSelected(false);
            textBuscar.setText("");
            textExpediente.setText("");
            textNombre.setText("");
            btnContinuar.setEnabled(false);
            scrollPaneLista.setVisible(false);
        }
            
    }//GEN-LAST:event_radioExpActionPerformed

    private void radioNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioNombreActionPerformed
       radioNombre.setSelected(true);
       textBuscar.setToolTipText("Busqueda por nombre solo permite escribir letras");
       tam = 50;
        if(radioExp.isSelected()){
            radioExp.setSelected(false);
            textBuscar.setText("");
            textExpediente.setText("");
            textNombre.setText("");
            btnContinuar.setEnabled(false);
            scrollPaneLista.setVisible(false);
        }
    }//GEN-LAST:event_radioNombreActionPerformed

    private void textBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textBuscarKeyReleased

        if(evt.getKeyCode() == KeyEvent.VK_CONTROL) pres = false;
        if(abajo || arriba){
            arriba = false;
            abajo = false;
            return;
        }
        if(evt.getKeyCode() == KeyEvent.VK_DOWN && scrollPaneLista.isVisible()){
            lista.requestFocus();
            lista.setSelectedIndex(0);
            return;
        }
        if(evt.getKeyCode() == KeyEvent.VK_UP && scrollPaneLista.isVisible()){
            lista.requestFocus();            
            scrollPaneLista.getVerticalScrollBar().setValue(scrollPaneLista.getVerticalScrollBar().getMaximum());
            lista.setSelectedIndex(lista.getModel().getSize()-1);
            return;
        }
        
        if(radioNombre.isSelected()){
            textBuscar.setText(textBuscar.getText().toUpperCase());
            
            Vector listaFiltro = new Vector(); 
            for(Object v : nombres){
                String nombre = (String) v;
                if(nombre.startsWith(textBuscar.getText())){
                    listaFiltro.add(v);
                }
            }

            if(listaFiltro.size() > 0){                
                lista.setListData(listaFiltro);
                int rows = 0;
                if(listaFiltro.size() >= 4){
                    rows = 4;
                }
                else{
                    rows = listaFiltro.size();
                }
                lista.setSize(textBuscar.getWidth(),25*rows);
                scrollPaneLista.setSize(textBuscar.getWidth(), lista.getHeight());
                
                scrollPaneLista.setVisible(true);
            }
            if(textBuscar.getText().isEmpty() || listaFiltro.size() == 0){
                scrollPaneLista.setVisible(false);
            }    

        }
    }//GEN-LAST:event_textBuscarKeyReleased

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed

            textExpediente.setText("");
            textNombre.setText("");
            if(textBuscar.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Ingrese un valor de busqueda", "Aviso", JOptionPane.WARNING_MESSAGE);
                btnContinuar.setEnabled(false);
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
                con = new ConexionBase().getConection();
                pst = con.prepareStatement("SELECT EXPEDIENTE, NOM_PACIENTE FROM PERPACIENTE " + filtro);
                rs = pst.executeQuery();
                boolean encontrado = false;
                while(rs.next()){
                    encontrado = true;
                    textExpediente.setText(rs.getString(1));
                    textNombre.setText(rs.getString(2));
                }
                
                if(!encontrado){
                    btnContinuar.setEnabled(false);
                    JOptionPane.showMessageDialog(this, "No se encontro ningun resultado", "Fallo en busqueda", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                btnContinuar.setEnabled(true);
                
        } catch (SQLException ex) {
            Logger.getLogger(FormBuscarDatos.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
                try {
                    if(rs != null)
                        rs.close();
                    if(pst != null)
                        pst.close();
                    if(con != null)
                        con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(FormBuscarDatos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarActionPerformed

        Actualizacion actualizar = new Actualizacion(parent, new Integer(textExpediente.getText()), this);
        actualizar.getTextExp().setText(textExpediente.getText());
        actualizar.getTextNombre().setText(textNombre.getText());
        actualizar.setVisible(true);
        textBuscar.setText("");
        textExpediente.setText("");
        textNombre.setText("");
        radioExp.setSelected(true);
        radioNombre.setSelected(false);
        btnContinuar.setEnabled(false);
    }//GEN-LAST:event_btnContinuarActionPerformed

    private void listaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_listaKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER && scrollPaneLista.isVisible()){
            textBuscar.setText((String) lista.getSelectedValue());
            scrollPaneLista.setVisible(false);
        }
    }//GEN-LAST:event_listaKeyReleased

    private void listaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_listaKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_UP && lista.getSelectedIndex() == 0){
            arriba = true;
            textBuscar.requestFocus();
        }
        if(evt.getKeyCode() == KeyEvent.VK_DOWN && lista.getSelectedIndex() == lista.getModel().getSize() -1){
            //scrollPaneLista.setVisible(false);
            abajo = true;
            textBuscar.requestFocus();
            scrollPaneLista.getVerticalScrollBar().setValue(scrollPaneLista.getVerticalScrollBar().getMinimum());
        }
    }//GEN-LAST:event_listaKeyPressed

    private void textBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textBuscarKeyTyped
        if(((JTextField)evt.getComponent()).getText().length() >= tam){
            evt.consume();
        }
        char c = evt.getKeyChar();
        if(radioExp.isSelected()){
            if(c < '0' || c > '9')
                evt.consume();
        }  
        if(radioNombre.isSelected()){
            if((c < 'A' || c > 'Z') && (c < 'a' || c > 'z') && (c < ' ' || c > ' '))
                evt.consume();
        } 
    }//GEN-LAST:event_textBuscarKeyTyped

    private void listaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_listaFocusGained
        lista.setSelectionBackground(Color.YELLOW);
    }//GEN-LAST:event_listaFocusGained

    private void listaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_listaFocusLost
        lista.setSelectionBackground(Color.WHITE);
    }//GEN-LAST:event_listaFocusLost

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        scrollPaneLista.setVisible(false);
    }//GEN-LAST:event_formMouseClicked

    private void listaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaMouseClicked
        if(lista.getSelectedIndex() >= 0){
            textBuscar.setText((String) lista.getSelectedValue());
            scrollPaneLista.setVisible(false);
        }
    }//GEN-LAST:event_listaMouseClicked

    private void textBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textBuscarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_CONTROL){
            pres = true;
        }else{
            if(pres && evt.getKeyCode()==KeyEvent.VK_V){
                evt.consume();
            }
        }
    }//GEN-LAST:event_textBuscarKeyPressed

    public Vector rellenarNombres(){
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Vector nombres = new Vector();
        try {    
        con = new ConexionBase().getConection();
        pst = con.prepareStatement("SELECT NOM_PACIENTE FROM PERPACIENTE");
        rs = pst.executeQuery();
        while(rs.next()){
            nombres.add(rs.getString(1).toUpperCase());
        }

        } catch (SQLException ex) {
            Logger.getLogger(FormBuscarDatos.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if(rs != null)
                    rs.close();
                if(pst != null)
                    pst.close();
                if(con != null)
                    con.close();
            } catch (SQLException ex) {
                Logger.getLogger(FormBuscarDatos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
        return nombres;
    }
    
    boolean abajo = false;
    boolean arriba = false;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnContinuar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList lista;
    private javax.swing.JRadioButton radioExp;
    private javax.swing.JRadioButton radioNombre;
    private javax.swing.JScrollPane scrollPaneLista;
    private javax.swing.JTextField textBuscar;
    private javax.swing.JTextField textExpediente;
    private javax.swing.JTextField textNombre;
    // End of variables declaration//GEN-END:variables
}
