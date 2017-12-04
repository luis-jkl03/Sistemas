package Interfaces;

import Classes.ConexionBase;
import Classes.Fechas;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

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
        lbVacioEsp.setVisible(false);
        llenarLista();
        dateChooser.setFocusable(false);
        anterior.dispose();
    }
    
    private String[] comboBoxOpciones(){
        String[] opciones = {"","Pediatria","Ginecologia","Cardiologia","Nutriologia","Endocrinologia"};
        return opciones;
    }
    
    private void llenarLista(){
        
        totalConsultas = obtenerConsultas();
        if(totalConsultas == 0){
            scrollPaneLista.setVisible(false);
            lbSinDatos.setText("NO HAY CONSULTAS PARA MOSTRAR AUN");
            lbSinDatos.setVisible(true);
            lbProxCon.setVisible(false);
            lbFechaCons.setVisible(false);
            dateChooser.setVisible(false);
            //labFecha.setVisible(false);
            //textFecha.setVisible(false);
        }
        else{
            hayConsultas();
            scrollPaneLista.setVisible(true);
            if(totalConsultas == 1)
                lbSinDatos.setText(totalConsultas + " CONSULTA EN EL HISTORIAL");
            else
                lbSinDatos.setText(totalConsultas + " CONSULTAS EN EL HISTORIAL");
            lbSinDatos.setVisible(true);
        }
        lbProxCon.setVisible(false);
        btnGuardar.setVisible(false);
        btnCancelar.setVisible(false);
        lbFechaCons.setVisible(false);
        dateChooser.setMinSelectableDate(new Date());
        dateChooser.setVisible(false);
        componentesPanelMedicos(false); 
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
    
    private void hayConsultas(){
        String[] consultas = new String[totalConsultas];
        for(int i = 0; i < totalConsultas; i++){
            consultas[i] = "Consulta " + (i + 1);
            System.out.println(consultas[i]);
        }    
        lista.setListData(consultas);
        //lista.setSize(lista.getWidth(), totalConsultas * 25);
        //scrollPane1.setSize(scrollPane1.getWidth(), lista.getHeight());
        
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
        lbNombre.setVisible(i);
        textNombre.setVisible(i);
        lbPeso.setVisible(i);
        lbKg.setVisible(i);
        textKg.setVisible(i);
        lbGramos.setVisible(i);
        textGramos.setVisible(i);
        lbRecomendaciones.setVisible(i);
        scrollPaneRecomendaciones.setVisible(i);
        //btnGuardar.setVisible(i);
        //btnCancelar.setVisible(i);
    }    
    
    private void habilitarCampos(boolean i){
        textCintura.setEditable(i);
        //comboEspecialidad.setEditable(i);
        textEstatura.setEditable(i);
        textExpediente.setEditable(i);
        textNombre.setEditable(i);
        textKg.setEditable(i);
        textGramos.setEditable(i);
        textRecomendaciones.setEditable(i);
        Date f= new Date();
        //dateChooser.setEnabled(i);
        
        //scrollPaneRecomendaciones.setEnabled(i);
        //lista.setEnabled(!i);
        //lista.sets;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        scrollPaneLista = new javax.swing.JScrollPane();
        lista = new javax.swing.JList();
        jPanel2 = new javax.swing.JPanel();
        lbExpediente = new javax.swing.JLabel();
        textExpediente = new javax.swing.JTextField();
        textNombre = new javax.swing.JTextField();
        lbNombre = new javax.swing.JLabel();
        lbEspecialidad = new javax.swing.JLabel();
        lbPeso = new javax.swing.JLabel();
        textKg = new javax.swing.JTextField();
        lbEstatura = new javax.swing.JLabel();
        textEstatura = new javax.swing.JTextField();
        lbRecomendaciones = new javax.swing.JLabel();
        textCintura = new javax.swing.JTextField();
        lbCintura = new javax.swing.JLabel();
        scrollPaneRecomendaciones = new javax.swing.JScrollPane();
        textRecomendaciones = new javax.swing.JTextArea();
        lbSinDatos = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        lbGramos = new javax.swing.JLabel();
        textGramos = new javax.swing.JTextField();
        comboEspecialidad = new javax.swing.JComboBox();
        lbFechaCons = new javax.swing.JLabel();
        lbProxCon = new javax.swing.JLabel();
        lbKg = new javax.swing.JLabel();
        dateChooser = new com.toedter.calendar.JDateChooser();
        lbSoloHora = new javax.swing.JLabel();
        lbSoloFecha = new javax.swing.JLabel();
        lbVacioEsp = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabelTitu = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnNuevaConsulta = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 255, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        scrollPaneLista.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        scrollPaneLista.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        scrollPaneLista.setEnabled(false);

        lista.setBorder(new javax.swing.border.MatteBorder(null));
        lista.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        lista.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        lista.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listaValueChanged(evt);
            }
        });
        scrollPaneLista.setViewportView(lista);

        jPanel1.add(scrollPaneLista, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 200, 210));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 220, 230));

        jPanel2.setBackground(new java.awt.Color(204, 255, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbExpediente.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbExpediente.setText("Expediente");
        jPanel2.add(lbExpediente, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        textExpediente.setEditable(false);
        textExpediente.setBackground(new java.awt.Color(153, 153, 153));
        textExpediente.setFont(new java.awt.Font("Arial Black", 0, 13)); // NOI18N
        jPanel2.add(textExpediente, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 120, 30));

        textNombre.setEditable(false);
        textNombre.setBackground(new java.awt.Color(153, 153, 153));
        textNombre.setFont(new java.awt.Font("Arial Black", 0, 13)); // NOI18N
        jPanel2.add(textNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, 510, 30));

        lbNombre.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbNombre.setText("Nombre");
        jPanel2.add(lbNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, 70, -1));

        lbEspecialidad.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbEspecialidad.setText("Especialidad");
        jPanel2.add(lbEspecialidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        lbPeso.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbPeso.setText("Peso:");
        jPanel2.add(lbPeso, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 110, -1, -1));

        textKg.setFont(new java.awt.Font("Arial Black", 0, 13)); // NOI18N
        textKg.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 255), null));
        textKg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textKgKeyTyped(evt);
            }
        });
        jPanel2.add(textKg, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 100, 90, 30));

        lbEstatura.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbEstatura.setText("Estatura (cm)");
        jPanel2.add(lbEstatura, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 110, -1, -1));

        textEstatura.setFont(new java.awt.Font("Arial Black", 0, 13)); // NOI18N
        textEstatura.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 255), null));
        textEstatura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textEstaturaKeyTyped(evt);
            }
        });
        jPanel2.add(textEstatura, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 100, 90, 30));

        lbRecomendaciones.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbRecomendaciones.setText("Resumen de consulta");
        jPanel2.add(lbRecomendaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 180, -1, -1));

        textCintura.setFont(new java.awt.Font("Arial Black", 0, 13)); // NOI18N
        textCintura.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 255), null));
        textCintura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textCinturaKeyTyped(evt);
            }
        });
        jPanel2.add(textCintura, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, 90, 30));

        lbCintura.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbCintura.setText("Cintura (cm)");
        jPanel2.add(lbCintura, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        scrollPaneRecomendaciones.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 255), null));

        textRecomendaciones.setColumns(20);
        textRecomendaciones.setFont(new java.awt.Font("Arial Black", 0, 13)); // NOI18N
        textRecomendaciones.setRows(5);
        textRecomendaciones.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textRecomendacionesKeyTyped(evt);
            }
        });
        scrollPaneRecomendaciones.setViewportView(textRecomendaciones);

        jPanel2.add(scrollPaneRecomendaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 170, 310, 190));

        lbSinDatos.setFont(new java.awt.Font("Verdana", 1, 32)); // NOI18N
        lbSinDatos.setForeground(new java.awt.Color(102, 102, 102));
        lbSinDatos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel2.add(lbSinDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 830, 30));

        btnCancelar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel2.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 340, 110, 40));

        btnGuardar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnGuardar.setText("Guardar datos");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel2.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 340, 140, 40));

        lbGramos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbGramos.setText("(gr)");
        jPanel2.add(lbGramos, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 110, -1, -1));

        textGramos.setFont(new java.awt.Font("Arial Black", 0, 13)); // NOI18N
        textGramos.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 255), null));
        textGramos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textGramosKeyTyped(evt);
            }
        });
        jPanel2.add(textGramos, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 100, 80, 30));

        comboEspecialidad.setFont(new java.awt.Font("Arial Black", 0, 13)); // NOI18N
        comboEspecialidad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Pediatria", "Ginecologia", "Cardiologia", "Nutriologia", "Endocrinologia" }));
        jPanel2.add(comboEspecialidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 210, 30));

        lbFechaCons.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbFechaCons.setText("Fecha de consulta:");
        jPanel2.add(lbFechaCons, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, -1, -1));

        lbProxCon.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbProxCon.setText("Proxima consulta");
        jPanel2.add(lbProxCon, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, -1, -1));

        lbKg.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbKg.setText("(Kg)");
        jPanel2.add(lbKg, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 110, -1, -1));

        dateChooser.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 255), null));
        dateChooser.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        dateChooser.setMaxSelectableDate(new java.util.Date(253370790079000L));
        dateChooser.setMinSelectableDate(new java.util.Date(-62135744321000L));
        jPanel2.add(dateChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, 150, 30));

        lbSoloHora.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel2.add(lbSoloHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 10, 110, 20));

        lbSoloFecha.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jPanel2.add(lbSoloFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 10, 110, 20));

        lbVacioEsp.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        lbVacioEsp.setForeground(new java.awt.Color(255, 51, 51));
        lbVacioEsp.setText("*");
        jPanel2.add(lbVacioEsp, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, -1, 30));

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

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("HISTORIAL DE CONSULTAS");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("DATOS MEDICOS");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 90, -1, -1));

        btnNuevaConsulta.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnNuevaConsulta.setText("+ Nueva consulta");
        btnNuevaConsulta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevaConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaConsultaActionPerformed(evt);
            }
        });
        getContentPane().add(btnNuevaConsulta, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 410, -1, 40));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fconsultamed.jpg"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevaConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaConsultaActionPerformed
        btnNuevaConsulta.setEnabled(false);
        componentesPanelMedicos(true);
        lbSinDatos.setVisible(false);
        lista.setEnabled(false);
        btnGuardar.setVisible(true);
        btnCancelar.setVisible(true);
        lbProxCon.setVisible(true);
        Date f = new Date();
        dateChooser.setMinSelectableDate(new Date(f.getYear(), f.getMonth(), f.getDay()+4));
        dateChooser.setMaxSelectableDate(new Date(1999, 11, 31));
        dateChooser.setVisible(true);
        lbFechaCons.setVisible(false);
        lbSoloFecha.setVisible(false);
        lbSoloHora.setVisible(false);
        lista.setSelectionBackground(Color.WHITE);
        limpiarCampos();
        habilitarCampos(true);
    }//GEN-LAST:event_btnNuevaConsultaActionPerformed

    private void limpiarCampos(){
        textCintura.setText("");
        textEstatura.setText("");
        textGramos.setText("");
        textKg.setText("");
        textRecomendaciones.setText("");
        comboEspecialidad.setModel(new DefaultComboBoxModel(comboBoxOpciones()));
        comboEspecialidad.setSelectedIndex(0);
        dateChooser.setDate(null);
    }
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int op = JOptionPane.showConfirmDialog(this, "La información no se ha guardado, ¿desea continuar?","Atención",
                JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
        if(op == JOptionPane.YES_OPTION)
            this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(!camposNulos())
            return;
        int op = JOptionPane.showConfirmDialog(this, "¿Esta a punto de guardar la información, desea continuar?", "Guardar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(op == JOptionPane.NO_OPTION) return;
        guardarDatos();
        llenarLista();
        componentesPanelMedicos(false);
        lista.setEnabled(true);
        btnNuevaConsulta.setEnabled(true);
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        //System.out.println(new Fechas().fechaConDate(dateChooser.getDate().getTime()));
        int op = JOptionPane.showConfirmDialog(this, "Esta a punto de cancelar la consulta y los datos no se guardaran, "
                + "¿Desea continuar?", "Aviso", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if(op == JOptionPane.YES_OPTION){
            componentesPanelMedicos(false);
            lista.setEnabled(true);
            btnCancelar.setVisible(false);
            btnGuardar.setVisible(false);
            btnNuevaConsulta.setEnabled(true);
            lbProxCon.setVisible(false);
            dateChooser.setVisible(false);
            lbVacioEsp.setVisible(false);
            javax.swing.border.Border blueBord = BorderFactory.createEtchedBorder(BevelBorder.LOWERED, new Color(102,102,255), null);
            textKg.setBorder(blueBord);
            textGramos.setBorder(blueBord);
            textEstatura.setBorder(blueBord);
            textCintura.setBorder(blueBord);
            lbVacioEsp.setVisible(false);
            scrollPaneRecomendaciones.setBorder(blueBord);
            dateChooser.setBorder(blueBord);
            
            llenarLista();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void listaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_listaFocusGained
        lista.setSelectionBackground(Color.YELLOW);
    }//GEN-LAST:event_listaFocusGained

    private void listaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_listaFocusLost
        //lista.setSelectionBackground(Color.GRAY);
    }//GEN-LAST:event_listaFocusLost

    private void listaValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listaValueChanged
        if(lista.getSelectedIndex() >= 0 && lista.isEnabled()){
            String txtLista = lista.getSelectedValue().toString();
            System.out.println("texto --> " + txtLista);
            String numero = txtLista.substring(9, txtLista.length());
            //System.out.println("Numero --> " + numero);
            obtenerHistorial(new Integer(numero));
            
            componentesPanelMedicos(true);
            lbFechaCons.setVisible(true);
            lbProxCon.setVisible(true);
            dateChooser.setVisible(true);
            lbSoloFecha.setVisible(true);
            lbSoloHora.setVisible(true);
            lbSinDatos.setVisible(false);
            habilitarCampos(false);
        }
    }//GEN-LAST:event_listaValueChanged

    private void textKgKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textKgKeyTyped
        soloNumeros(evt);
        restringir(evt, 3);
    }//GEN-LAST:event_textKgKeyTyped

    private void textGramosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textGramosKeyTyped
        soloNumeros(evt);
        restringir(evt, 3);
    }//GEN-LAST:event_textGramosKeyTyped

    private void textEstaturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textEstaturaKeyTyped
        soloNumeros(evt);
        restringir(evt, 3);
    }//GEN-LAST:event_textEstaturaKeyTyped

    private void textCinturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textCinturaKeyTyped
        soloNumeros(evt);
        restringir(evt, 3);
    }//GEN-LAST:event_textCinturaKeyTyped

    private void listaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaMouseClicked
        if(lista.getSelectedIndex() >= 0 && lista.isEnabled()){
            String txtLista = lista.getSelectedValue().toString();
            System.out.println("texto --> " + txtLista);
            String numero = txtLista.substring(9, txtLista.length());
            //System.out.println("Numero --> " + numero);
            obtenerHistorial(new Integer(numero));
            
            componentesPanelMedicos(true);
            lbFechaCons.setVisible(true);
            lbProxCon.setVisible(true);
            dateChooser.setVisible(true);
            lbSoloFecha.setVisible(true);
            lbSoloHora.setVisible(true);
            lbSinDatos.setVisible(false);
            habilitarCampos(false);
        }
    }//GEN-LAST:event_listaMouseClicked

    private void textRecomendacionesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textRecomendacionesKeyTyped
        if(((JTextArea)evt.getComponent()).getText().length() >= 500){
            evt.consume();
        }
    }//GEN-LAST:event_textRecomendacionesKeyTyped

    private void restringir(KeyEvent evt, int tam) {
        if(((JTextField)evt.getComponent()).getText().length() >= tam){
            evt.consume();
        }
    }
    
    private void soloNumeros(java.awt.event.KeyEvent e){
        char c = e.getKeyChar();
        if(c < '0' || c > '9'){
            e.consume();
        }
    }
    
    private void obtenerHistorial(int n){
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        try {
            con = new ConexionBase().getConection();
            pst = con.prepareStatement("SELECT * FROM CONSULTAMED WHERE EXPEDIENTE = " + new Integer(textExpediente.getText())
                    + " AND NO_CONS = " + n);
            rs = pst.executeQuery();
            
            comboEspecialidad.removeAllItems();
            while(rs.next()){
                System.out.println(rs.getString(1));
                System.out.println(rs.getString(2));
                System.out.println(rs.getString(3));
                System.out.println(rs.getString(4));
                System.out.println(rs.getString(5));
                System.out.println(rs.getString(6));
                System.out.println(rs.getString(7));
                System.out.println(rs.getString(8));
                System.out.println(rs.getDate(9));
                comboEspecialidad.addItem(rs.getString(4));
                comboEspecialidad.setSelectedItem(rs.getString(4));
                String peso = "" + rs.getFloat(5);
                int punto = peso.indexOf('.');
                textKg.setText(peso.substring(0, punto));
                textGramos.setText(peso.substring(punto + 1, peso.length()));
                textEstatura.setText(rs.getString(6));
                textCintura.setText(rs.getString(7));
                textRecomendaciones.setText(rs.getString(8));
                lbSoloFecha.setText(""+rs.getDate(9));
                lbSoloHora.setText(""+rs.getTime(9) + " Hrs.");
                dateChooser.setDate(rs.getDate(10));
                dateChooser.setMinSelectableDate(rs.getDate(10));
                dateChooser.setMaxSelectableDate(rs.getDate(10));
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
                JOptionPane.showMessageDialog(this, "Ocurrio un error al solicitar la consulta, intente nuevamente", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void guardarDatos(){
        Connection con = null;
        PreparedStatement pst = null;
        
        try {
            con = new ConexionBase().getConection();
            pst = con.prepareStatement("INSERT INTO CONSULTAMED VALUES(?,?,?,?,?,?,?,?,?,?)");
            pst.setInt(1, new Integer(textExpediente.getText()));
            pst.setInt(2, totalConsultas + 1);
            pst.setString(3, textNombre.getText());
            pst.setString(4, comboEspecialidad.getSelectedItem().toString());
            String peso = textKg.getText() + "." + textGramos.getText();
            pst.setFloat(5, new Float(peso));
            pst.setInt(6, new Integer(textEstatura.getText()));
            pst.setInt(7, new Integer(textCintura.getText()));
            pst.setString(8, textRecomendaciones.getText());   
            Date ahora = new Date();
            //SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
            //java.util.Date miObjetoJavaUtilDate = ahora;
            Timestamp timestamp = new Timestamp(ahora.getTime());
            pst.setTimestamp(9,timestamp);
            //java.util.Date ahora = new Date();
            //formateador = new SimpleDateFormat("dd-MM-yyyy");
            //pst.setString(10, formateador.format(dateChooser.getDate()));
            pst.setTimestamp(10, new Timestamp(dateChooser.getDate().getTime()));
            pst.execute();
            
            JOptionPane.showMessageDialog(this, "Datos guardados exitosamente");
            
        } catch (SQLException ex) {
            Logger.getLogger(FormMedicos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(this, "Uno o mas campos se encuentran vacios");
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
    
    private boolean camposNulos(){
        javax.swing.border.Border redBord = BorderFactory.createEtchedBorder(BevelBorder.LOWERED, Color.RED, null);
        javax.swing.border.Border blueBord = BorderFactory.createEtchedBorder(BevelBorder.LOWERED, new Color(102,102,255), null);

        if(textKg.getText().isEmpty()) textKg.setBorder(redBord);
        else textKg.setBorder(blueBord);
        if(textGramos.getText().isEmpty()) textGramos.setBorder(redBord);
        else textGramos.setBorder(blueBord);
        if(textEstatura.getText().isEmpty()) textEstatura.setBorder(redBord);
        else textEstatura.setBorder(blueBord);
        if(textCintura.getText().isEmpty()) textCintura.setBorder(redBord);
        else textCintura.setBorder(blueBord);
        if(comboEspecialidad.getSelectedIndex() == 0) lbVacioEsp.setVisible(true);
        else lbVacioEsp.setVisible(false);
        if(textRecomendaciones.getText().isEmpty()) scrollPaneRecomendaciones.setBorder(redBord);
        else scrollPaneRecomendaciones.setBorder(blueBord);
        if(dateChooser.getDate() == null) dateChooser.setBorder(redBord);
        else dateChooser.setBorder(blueBord);
        if(textKg.getText().isEmpty()||textGramos.getText().isEmpty()||
           textEstatura.getText().isEmpty()||comboEspecialidad.getSelectedIndex()==0||textCintura.getText().isEmpty()||
                textRecomendaciones.getText().isEmpty()){
            JOptionPane.showMessageDialog(this,"Favor de llenar todos los campos","Atención",JOptionPane.WARNING_MESSAGE);  
            return false;
                             
        }
        try {
            Date date = dateChooser.getDate();
            if(date == null){
                System.out.println(dateChooser.getDate());
                JOptionPane.showMessageDialog(this, "La fecha de proxima cita esta vacio o contiene caracteres no permitidos","Atención",JOptionPane.ERROR_MESSAGE);
                return false;
            }
            else if(date.before(dateChooser.getMinSelectableDate())){
                SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
                JOptionPane.showMessageDialog(this, "La fecha de proxima cita no debe ser menor de " + formateador.format(dateChooser.getMinSelectableDate()),
                                              "Atención",JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (Exception e) {
        }
        return true;
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
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevaConsulta;
    private javax.swing.JComboBox comboEspecialidad;
    private com.toedter.calendar.JDateChooser dateChooser;
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
    private javax.swing.JLabel lbFechaCons;
    private javax.swing.JLabel lbGramos;
    private javax.swing.JLabel lbKg;
    private javax.swing.JLabel lbNombre;
    private javax.swing.JLabel lbPeso;
    private javax.swing.JLabel lbProxCon;
    private javax.swing.JLabel lbRecomendaciones;
    private javax.swing.JLabel lbSinDatos;
    private javax.swing.JLabel lbSoloFecha;
    private javax.swing.JLabel lbSoloHora;
    private javax.swing.JLabel lbVacioEsp;
    private javax.swing.JList lista;
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
