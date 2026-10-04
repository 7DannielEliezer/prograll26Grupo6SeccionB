/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package reclutamiento.prueba2.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import reclutamiento.prueba2.controller.EvaluacionJpaController;
import reclutamiento.prueba2.controller.PostulacionJpaController;
import reclutamiento.prueba2.controller.UsuarioJpaController;
import reclutamiento.prueba2.model.Evaluacion;
import reclutamiento.prueba2.model.Postulacion;
import reclutamiento.prueba2.model.Usuario;

/**
 *
 * @author PGutierrez
 */
public class FrmEvaluacion extends javax.swing.JFrame {

    private Usuario usuarioLogueado;
    private final EvaluacionJpaController evaluacionController;
    private final PostulacionJpaController postulacionController;
    private final UsuarioJpaController usuarioController;
    private DefaultTableModel modeloTabla;
    private Integer idSeleccionado = null;
    private final SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public FrmEvaluacion() {
        initComponents();
        this.setLocationRelativeTo(null);

        // Inicialización de Persistence Manager y Controllers JPA
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Prueba2PU");
        evaluacionController = new EvaluacionJpaController(emf);
        postulacionController = new PostulacionJpaController(emf);
        usuarioController = new UsuarioJpaController(emf);

        configurarTabla();
        cargarComboPostulaciones();
        cargarComboTipoEvaluacion();
        cargarDatosTabla();
    }

    public FrmEvaluacion(Usuario usuarioLogueado) {
        this();
        this.usuarioLogueado = usuarioLogueado;
        validarPermisos();
    }

    private void validarPermisos() {
        if (usuarioLogueado != null && usuarioLogueado.getIdRol() != null) {
            String nombreRol = usuarioLogueado.getIdRol().getNombreRol().trim();
            if (nombreRol.equalsIgnoreCase("Reclutador Junior") || nombreRol.contains("Junior")) {
                btnGuardar.setEnabled(false);
                btnActualizar.setEnabled(false);
                btnEliminar.setEnabled(false);
            }
        }
    }

    private void configurarTabla() {
        modeloTabla = new DefaultTableModel(
                new Object[]{"ID", "Postulación", "Tipo", "Fecha", "Resultado", "Comentarios", "Evaluador", "Estado"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblEvaluaciones.setModel(modeloTabla);
    }

    private void cargarComboTipoEvaluacion() {
        cmbTipoEvaluacion.setModel(new DefaultComboBoxModel<>(
                new String[]{"Técnica", "Psicométrica", "Entrevista RRHH", "Entrevista Técnica", "Médica"}
        ));
    }

    private void cargarComboPostulaciones() {
        DefaultComboBoxModel<Postulacion> modelCombo = new DefaultComboBoxModel<>();
        List<Postulacion> lista = postulacionController.findPostulacionEntities();
        for (Postulacion p : lista) {
            modelCombo.addElement(p);
        }
        cmbPostulacion.setModel(modelCombo);
    }

    public void cargarDatosTabla() {
        modeloTabla.setRowCount(0);
        List<Evaluacion> lista = evaluacionController.findEvaluacionEntities();

        for (Evaluacion e : lista) {
            String fechaTexto = e.getFechaEvaluacion() != null
                    ? formatoFecha.format(e.getFechaEvaluacion())
                    : "N/A";

            String postulacionTexto = e.getIdPostulacion() != null
                    ? "Postulación #" + e.getIdPostulacion().getIdPostulacion()
                    : "N/A";

            String usuarioTexto = e.getIdEvaluador() != null
                    ? (e.getIdEvaluador().getNombres() + " " + e.getIdEvaluador().getApellidos())
                    : "N/A";

            String estadoTexto = (e.getEstado() != null && e.getEstado()) ? "Activo" : "Inactivo";

            modeloTabla.addRow(new Object[]{
                e.getIdEvaluacion(),
                postulacionTexto,
                e.getTipoEvaluacion(),
                fechaTexto,
                e.getResultado(),
                e.getComentarios(),
                usuarioTexto,
                estadoTexto
            });
        }
    }

    private void limpiarFormulario() {
        idSeleccionado = null;
        if (cmbPostulacion.getItemCount() > 0) {
            cmbPostulacion.setSelectedIndex(0);
        }
        if (cmbTipoEvaluacion.getItemCount() > 0) {
            cmbTipoEvaluacion.setSelectedIndex(0);
        }
        txtResultado.setText("");
        txtComentarios.setText("");
        cmbEstado.setSelectedItem("Activo");
        tblEvaluaciones.clearSelection();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cmbTipoEvaluacion = new javax.swing.JComboBox<>();
        txtResultado = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtComentarios = new javax.swing.JTextArea();
        cmbEstado = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblEvaluaciones = new javax.swing.JTable();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        cmbPostulacion = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("INGRESO DATOS PARA EVALUACIONES");

        jLabel2.setText("Postulacion");

        jLabel3.setText("Tipo Evaluacion");

        jLabel4.setText("Resultado");

        jLabel5.setText("Comentarios");

        jLabel6.setText("Estado");

        cmbTipoEvaluacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Entrevista", "Tecnica", "Medica", "Psicometrica" }));

        txtComentarios.setColumns(20);
        txtComentarios.setRows(5);
        jScrollPane1.setViewportView(txtComentarios);

        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "aplicado", "en evaluacion", "seleccionado", "rechazado" }));

        tblEvaluaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblEvaluaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEvaluacionesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblEvaluaciones);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addGap(96, 96, 96)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbTipoEvaluacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtResultado)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(cmbPostulacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(51, 51, 51))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(289, 289, 289))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnGuardar)
                        .addGap(88, 88, 88)
                        .addComponent(btnActualizar)
                        .addGap(81, 81, 81)
                        .addComponent(btnEliminar)
                        .addGap(86, 86, 86)
                        .addComponent(btnLimpiar)
                        .addGap(69, 69, 69)
                        .addComponent(btnRegresar)
                        .addGap(67, 67, 67))))
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 802, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel1)
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(cmbPostulacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmbTipoEvaluacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtResultado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnActualizar)
                    .addComponent(btnEliminar)
                    .addComponent(btnLimpiar)
                    .addComponent(btnRegresar))
                .addGap(39, 39, 39)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            Postulacion postSel = (Postulacion) cmbPostulacion.getSelectedItem();

            if (postSel == null) {
                JOptionPane.showMessageDialog(this, "Seleccione una postulación.", "Atención", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (cmbTipoEvaluacion.getSelectedItem() == null || txtResultado.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete el tipo de evaluación y el resultado.", "Atención", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Determinar evaluador asignado (usuario logueado o el primero en la BD)
            Usuario evaluadorAsignado = usuarioLogueado;
            if (evaluadorAsignado == null) {
                List<Usuario> usuarios = usuarioController.findUsuarioEntities();
                if (!usuarios.isEmpty()) {
                    evaluadorAsignado = usuarios.get(0);
                } else {
                    JOptionPane.showMessageDialog(this, "No hay usuarios evaluadores registrados.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            Evaluacion e = new Evaluacion();
            e.setIdPostulacion(postSel);
            e.setTipoEvaluacion(cmbTipoEvaluacion.getSelectedItem().toString());
            e.setFechaEvaluacion(new Date());
            e.setResultado(txtResultado.getText().trim());
            e.setComentarios(txtComentarios.getText().trim());
            e.setEstado(cmbEstado.getSelectedItem().toString().equalsIgnoreCase("Activo"));
            e.setIdEvaluador(evaluadorAsignado);

            evaluacionController.create(e);

            JOptionPane.showMessageDialog(this, "Evaluación registrada con éxito.");
            cargarDatosTabla();
            limpiarFormulario();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar evaluación: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        if (idSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un registro de la tabla primero.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Evaluacion e = evaluacionController.findEvaluacion(idSeleccionado);
            if (e != null) {
                e.setIdPostulacion((Postulacion) cmbPostulacion.getSelectedItem());
                e.setTipoEvaluacion(cmbTipoEvaluacion.getSelectedItem().toString());
                e.setResultado(txtResultado.getText().trim());
                e.setComentarios(txtComentarios.getText().trim());
                e.setEstado(cmbEstado.getSelectedItem().toString().equalsIgnoreCase("Activo"));

                evaluacionController.edit(e);

                JOptionPane.showMessageDialog(this, "Evaluación actualizada correctamente.");
                cargarDatosTabla();
                limpiarFormulario();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (idSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un registro de la tabla primero.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Eliminar la evaluación seleccionada?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                evaluacionController.destroy(idSeleccionado);

                JOptionPane.showMessageDialog(this, "Evaluación eliminada.");
                cargarDatosTabla();
                limpiarFormulario();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarFormulario();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        FrmMenu menu = new FrmMenu();
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void tblEvaluacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEvaluacionesMouseClicked
        int fila = tblEvaluaciones.getSelectedRow();
        if (fila >= 0) {
            idSeleccionado = (Integer) modeloTabla.getValueAt(fila, 0);

            String postTabla = modeloTabla.getValueAt(fila, 1) != null ? modeloTabla.getValueAt(fila, 1).toString() : "";
            for (int i = 0; i < cmbPostulacion.getItemCount(); i++) {
                Postulacion p = cmbPostulacion.getItemAt(i);
                if (p != null && ("Postulación #" + p.getIdPostulacion()).equalsIgnoreCase(postTabla)) {
                    cmbPostulacion.setSelectedIndex(i);
                    break;
                }
            }

            cmbTipoEvaluacion.setSelectedItem(modeloTabla.getValueAt(fila, 2) != null ? modeloTabla.getValueAt(fila, 2).toString() : "");
            txtResultado.setText(modeloTabla.getValueAt(fila, 4) != null ? modeloTabla.getValueAt(fila, 4).toString() : "");
            txtComentarios.setText(modeloTabla.getValueAt(fila, 5) != null ? modeloTabla.getValueAt(fila, 5).toString() : "");

            Object estadoVal = modeloTabla.getValueAt(fila, 7);
            cmbEstado.setSelectedItem(estadoVal != null ? estadoVal.toString() : "Activo");
        }
    }//GEN-LAST:event_tblEvaluacionesMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        FrmMenu menu = new FrmMenu();
        menu.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmEvaluacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmEvaluacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmEvaluacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmEvaluacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmEvaluacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JComboBox<reclutamiento.prueba2.model.Postulacion> cmbPostulacion;
    private javax.swing.JComboBox<String> cmbTipoEvaluacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblEvaluaciones;
    private javax.swing.JTextArea txtComentarios;
    private javax.swing.JTextField txtResultado;
    // End of variables declaration//GEN-END:variables
}
