package Interfaces;

import Classes.ConexionBase;
import Classes.Fechas;
import java.awt.Dialog;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class FormMedicos extends javax.swing.JDialog {

    int totalConsultas;
    
    public FormMedicos(java.awt.Frame parent, boolean modal, Dialog anterior, Vector v) {
        super(parent, modal);
        this.setUndecorated(true);
        initComponents();     
        setLocationRelativeTo(null);
        setResizable(false);
        textExpediente.setText((String) v.get(0));
        textNombre.setText((String) v.get(1));
        
        anterior.dispose();
        llenarLista();
        /*if(totalConsultas == 0){
            scrollPaneLista.setVisible(false);
            //labFecha.setVisible(false);
            //textFecha.setVisible(false);
        }
        else{
            scrollPaneLista.setVisible(true);
        }*/
    }

    private int obtenerConsultas(){
        
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int registros = 0;
        
        try {    
            con = new ConexionBase().getConection();
            pst = con.prepareStatement("SELECT COUNT(*) FROM CONSULTAMED WHERE EXPEDIENTE = " + "?");
            pst.setInt(1, new Integer(textExpediente.getText()));
            rs = pst.executeQuery();
            
            while(rs.next()){
                registros = rs.getInt(1);
            }           
            
        } catch (SQLException ex) {
            Logger.getLogger(FormMedicos.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if(rs != null)
                    rs.close();
                if(pst != null)
                    pst.close();
                if(con != null)
                    con.close();
            } catch (SQLException ex) {
                Logger.getLogger(FormMedicos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return registros;
    }
    
    private void llenarLista(){
        totalConsultas = obtenerConsultas();
        System.out.println("Hay " + totalConsultas + " consultas");
        if(totalConsultas == 0){
            scrollPaneLista.setVisible(false);
            //lbFecha.setVisible(false); 
            //textFecha.setVisible(false);
            //sinConsultas();
        }
        else{
            //scrollPaneLista.setVisible(true);
            //labFecha.setVisible(false);
            //textFecha.setVisible(false);
            //lbSinDatos.setVisible(false);
            //hayConsultas();
            
            scrollPaneLista.setVisible(true);
            //listaConsultas.setVisible(true);
        }
        if(scrollPaneLista.isVisible())
            System.out.println("La lista es visible");
        else
            System.out.println("LA lista no es visible");
    }
    
    private void sinConsultas() {
        componentesPanelMedicos(false);      
    }
    
    private void hayConsultas(){
        String[] consultas = new String[totalConsultas];
        for(int i = 0; i < totalConsultas; i++){
            consultas[i] = "Consulta " + (i + 1);
            System.out.println(consultas[i]);
        }    
        listaConsultas.setListData(consultas);
        scrollPaneLista.setVisible(true);
        
    }
        
    private void componentesPanelMedicos(boolean i){
        lbCintura.setVisible(i);
        textCintura.setVisible(i);
        lbEspecialidad.setVisible(i);
        comboEspecialidad.setVisible(i);
        lbEstatura.setVisible(i);
        textEstatura.setVisible(i);
        lbExpediente.setVisible(i);
        textExpediente.setVisible(i);
        //lbFecha.setVisible(i);
        //textFecha.setVisible(i);
        lbNombre.setVisible(i);
        textNombre.setVisible(i);
        lbKg.setVisible(i);
        textKg.setVisible(i);
        lbGramos.setVisible(i);
        textGramos.setVisible(i);
        lbRecomendaciones.setVisible(i);
        scrollPaneRecomendaciones.setVisible(i);
        lbSinDatos.setVisible(!i);
        btnGuardar.setVisible(i);
    }    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        scrollPaneLista = new javax.swing.JScrollPane();
        listaConsultas = new javax.swing.JList();
        jPanel2 = new javax.swing.JPanel();
        lbExpediente = new javax.swing.JLabel();
        textExpediente = new javax.swing.JTextField();
        textNombre = new javax.swing.JTextField();
        lbNombre = new javax.swing.JLabel();
        lbEspecialidad = new javax.swing.JLabel();
        lbKg = new javax.swing.JLabel();
        textKg = new javax.swing.JTextField();
        lbEstatura = new javax.swing.JLabel();
        textEstatura = new javax.swing.JTextField();
        lbRecomendaciones = new javax.swing.JLabel();
        textCintura = new javax.swing.JTextField();
        lbCintura = new javax.swing.JLabel();
        scrollPaneRecomendaciones = new javax.swing.JScrollPane();
        textRecomendaciones = new javax.swing.JTextArea();
        lbSinDatos = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        lbGramos = new javax.swing.JLabel();
        textGramos = new javax.swing.JTextField();
        comboEspecialidad = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        jLabelTitu = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        listaConsultas.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        listaConsultas.setVisibleRowCount(4);
        scrollPaneLista.setViewportView(listaConsultas);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneLista, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPaneLista, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 220, 230));

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbExpediente.setText("Expediente");
        jPanel2.add(lbExpediente, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        textExpediente.setEditable(false);
        jPanel2.add(textExpediente, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, 130, 30));

        textNombre.setEditable(false);
        jPanel2.add(textNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, 510, 30));

        lbNombre.setText("Nombre");
        jPanel2.add(lbNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 50, -1));

        lbEspecialidad.setText("Especialidad");
        jPanel2.add(lbEspecialidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        lbKg.setText("Peso (Kg)");
        jPanel2.add(lbKg, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 110, -1, -1));
        jPanel2.add(textKg, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 100, 90, 30));

        lbEstatura.setText("Estatura (cm)");
        jPanel2.add(lbEstatura, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 110, -1, -1));
        jPanel2.add(textEstatura, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 100, 90, 30));

        lbRecomendaciones.setText("Recomendaciones");
        jPanel2.add(lbRecomendaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 180, -1, -1));
        jPanel2.add(textCintura, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, 90, 30));

        lbCintura.setText("Cintura (cm)");
        jPanel2.add(lbCintura, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, -1));

        textRecomendaciones.setColumns(20);
        textRecomendaciones.setRows(5);
        scrollPaneRecomendaciones.setViewportView(textRecomendaciones);

        jPanel2.add(scrollPaneRecomendaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 170, 310, 190));

        lbSinDatos.setFont(new java.awt.Font("Verdana", 1, 32)); // NOI18N
        lbSinDatos.setForeground(new java.awt.Color(204, 204, 204));
        lbSinDatos.setText("NO HAY CONSUTAS QUE MOSTRAR AUN");
        jPanel2.add(lbSinDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 730, 30));

        btnGuardar.setText("Guardar datos");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel2.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, 110, 40));

        lbGramos.setText("gramos");
        jPanel2.add(lbGramos, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 110, -1, -1));
        jPanel2.add(textGramos, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 100, 80, 30));

        comboEspecialidad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Pediatria", "Ginecologia", "Cardiologia", "Nutriologia", "Endocrinologia" }));
        jPanel2.add(comboEspecialidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 240, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, 890, 400));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cerrar1.jpg"))); // NOI18N
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 0, 30, 30));

        jLabelTitu.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        jLabelTitu.setForeground(new java.awt.Color(204, 0, 0));
        jLabelTitu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitu.setText("CONSULTAS DEL PACIENTE");
        getContentPane().add(jLabelTitu, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 1010, 40));

        jLabel1.setText("HISTORIAL DE CONSULTAS");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        jLabel2.setText("DATOS MEDICOS");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 90, -1, -1));

        jButton1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton1.setText("+ Nueva consulta");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 410, -1, 40));

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 360, -1, -1));

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 360, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fconsultamed.jpg"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        componentesPanelMedicos(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardarDatos();
        llenarLista();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        scrollPaneLista.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        scrollPaneLista.setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void guardarDatos(){
        Connection con = null;
        PreparedStatement pst = null;
        
        try {
            con = new ConexionBase().getConection();
            pst = con.prepareStatement("INSERT INTO CONSULTAMED VALUES(?,?,?,?,?,?,?,?,?)");
            pst.setInt(1, new Integer(textExpediente.getText()));
            pst.setInt(2, totalConsultas + 1);
            pst.setString(3, textNombre.getText());
            pst.setString(4, comboEspecialidad.getSelectedItem().toString());
            String peso = textKg.getText() + "." + textGramos.getText();
            pst.setFloat(5, new Float(peso));
            pst.setInt(6, new Integer(textEstatura.getText()));
            pst.setInt(7, new Integer(textCintura.getText()));
            pst.setString(8, textRecomendaciones.getText());
            pst.setDate(9, new Fechas().fecha());
            pst.execute();
            
            JOptionPane.showMessageDialog(this, "Datos guardados exitosamente");
            
        } catch (SQLException ex) {
            Logger.getLogger(FormMedicos.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if(pst != null)
                    pst.close();
                if(con != null)
                    con.close();
            } catch (SQLException ex) {
                Logger.getLogger(FormMedicos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox comboEspecialidad;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelTitu;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbCintura;
    private javax.swing.JLabel lbEspecialidad;
    private javax.swing.JLabel lbEstatura;
    private javax.swing.JLabel lbExpediente;
    private javax.swing.JLabel lbGramos;
    private javax.swing.JLabel lbKg;
    private javax.swing.JLabel lbNombre;
    private javax.swing.JLabel lbRecomendaciones;
    private javax.swing.JLabel lbSinDatos;
    private javax.swing.JList listaConsultas;
    private javax.swing.JScrollPane scrollPaneLista;
    private javax.swing.JScrollPane scrollPaneRecomendaciones;
    private javax.swing.JTextField textCintura;
    private javax.swing.JTextField textEstatura;
    private javax.swing.JTextField textExpediente;
    private javax.swing.JTextField textGramos;
    private javax.swing.JTextField textKg;
    private javax.swing.JTextField textNombre;
    private javax.swing.JTextArea textRecomendaciones;
    // End of variables declaration//GEN-END:variables

}
