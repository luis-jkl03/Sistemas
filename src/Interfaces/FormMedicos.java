package Interfaces;

import Classes.ConexionBase;
import java.awt.Dialog;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FormMedicos extends javax.swing.JDialog {

    public FormMedicos(java.awt.Frame parent, boolean modal, Dialog anterior, Vector v) {
        super(parent, modal);
        this.setUndecorated(true);
        initComponents();     
        setLocationRelativeTo(null);
        setResizable(false);
        textExpediente.setText((String) v.get(0));
        textNombre.setText((String) v.get(1));
        llenarLista();
        anterior.dispose();
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
            
            while(rs.next())
                registros = rs.getInt(1);           
            
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
        int numeroConsultas = obtenerConsultas();
        if(numeroConsultas == 0){
            sinConsultas();
            return;
        }
        lbSinDatos.setVisible(false);
        String[] consultas = new String[numeroConsultas];
        for(int i = 0; i < consultas.length; i++){
            consultas[i] = "Consulta " + (i + 1);
        }
        
        listaConsultas.setListData(consultas);
    }
    
    private void sinConsultas() {
        componentesPanelMedicos(false);
        
    }
        
    private void componentesPanelMedicos(boolean i){
        lbCintura.setVisible(i);
        textCintura.setVisible(i);
        lbEspecialidad.setVisible(i);
        textEspecialidad.setVisible(i);
        lbEstatura.setVisible(i);
        textEstatura.setVisible(i);
        lbExpediente.setVisible(i);
        textExpediente.setVisible(i);
        lbFecha.setVisible(i);
        textFecha.setVisible(i);
        lbNombre.setVisible(i);
        textNombre.setVisible(i);
        lbPeso.setVisible(i);
        textPeso.setVisible(i);
        lbRecomendaciones.setVisible(i);
        scrollPaneRecomendaciones.setVisible(i);
        lbSinDatos.setVisible(!i);
        scrollPaneLista.setVisible(i);
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
        textEspecialidad = new javax.swing.JTextField();
        lbPeso = new javax.swing.JLabel();
        textPeso = new javax.swing.JTextField();
        lbEstatura = new javax.swing.JLabel();
        textEstatura = new javax.swing.JTextField();
        lbRecomendaciones = new javax.swing.JLabel();
        textCintura = new javax.swing.JTextField();
        lbCintura = new javax.swing.JLabel();
        scrollPaneRecomendaciones = new javax.swing.JScrollPane();
        textRecomendaciones = new javax.swing.JTextArea();
        lbFecha = new javax.swing.JLabel();
        textFecha = new javax.swing.JTextField();
        lbSinDatos = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabelTitu = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        listaConsultas.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        listaConsultas.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Consulta 1", "Consulta 2", "Consulta 3", "Consulta 4", "Consulta 5", "Consulta 6" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
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
        jPanel2.add(textNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, 390, 30));

        lbNombre.setText("Nombre");
        jPanel2.add(lbNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 50, -1));

        lbEspecialidad.setText("Especialidad");
        jPanel2.add(lbEspecialidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));
        jPanel2.add(textEspecialidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 280, 30));

        lbPeso.setText("Peso");
        jPanel2.add(lbPeso, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 110, -1, -1));
        jPanel2.add(textPeso, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 100, 90, 30));

        lbEstatura.setText("Estatura");
        jPanel2.add(lbEstatura, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 110, -1, -1));
        jPanel2.add(textEstatura, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 100, 90, 30));

        lbRecomendaciones.setText("Recomendaciones");
        jPanel2.add(lbRecomendaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 180, -1, -1));
        jPanel2.add(textCintura, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, 90, 30));

        lbCintura.setText("Cintura");
        jPanel2.add(lbCintura, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, -1, -1));

        textRecomendaciones.setColumns(20);
        textRecomendaciones.setRows(5);
        scrollPaneRecomendaciones.setViewportView(textRecomendaciones);

        jPanel2.add(scrollPaneRecomendaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 170, 310, 190));

        lbFecha.setText("Fecha de consulta");
        jPanel2.add(lbFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, -1, -1));

        textFecha.setEditable(false);
        jPanel2.add(textFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 250, 90, 30));

        lbSinDatos.setFont(new java.awt.Font("Verdana", 1, 32)); // NOI18N
        lbSinDatos.setForeground(new java.awt.Color(204, 204, 204));
        lbSinDatos.setText("NO HAY CONSUTAS QUE MOSTRAR AUN");
        jPanel2.add(lbSinDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 730, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, 760, 400));

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JLabel lbFecha;
    private javax.swing.JLabel lbNombre;
    private javax.swing.JLabel lbPeso;
    private javax.swing.JLabel lbRecomendaciones;
    private javax.swing.JLabel lbSinDatos;
    private javax.swing.JList listaConsultas;
    private javax.swing.JScrollPane scrollPaneLista;
    private javax.swing.JScrollPane scrollPaneRecomendaciones;
    private javax.swing.JTextField textCintura;
    private javax.swing.JTextField textEspecialidad;
    private javax.swing.JTextField textEstatura;
    private javax.swing.JTextField textExpediente;
    private javax.swing.JTextField textFecha;
    private javax.swing.JTextField textNombre;
    private javax.swing.JTextField textPeso;
    private javax.swing.JTextArea textRecomendaciones;
    // End of variables declaration//GEN-END:variables

}
