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
import reclutamiento.prueba2.controller.BitacoraPostulacionJpaController;
import reclutamiento.prueba2.controller.PostulacionJpaController;
import reclutamiento.prueba2.controller.UsuarioJpaController;
import reclutamiento.prueba2.model.BitacoraPostulacion;
import reclutamiento.prueba2.model.Postulacion;
import reclutamiento.prueba2.model.Usuario;

/**
 *
 * @author PGutierrez
 */
public class FrmBitacoraPostulacion extends javax.swing.JFrame {

    private Usuario usuarioLogueado;
    private final BitacoraPostulacionJpaController bitacoraController;
    private final PostulacionJpaController postulacionController;
    private final UsuarioJpaController usuarioController;
    private DefaultTableModel modeloTabla;
    private Integer idSeleccionado = null;
    private final SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public FrmBitacoraPostulacion() {
        initComponents();
        this.setLocationRelativeTo(null);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Prueba2PU");
        bitacoraController = new BitacoraPostulacionJpaController(emf);
        postulacionController = new PostulacionJpaController(emf);
        usuarioController = new UsuarioJpaController(emf);

        configurarTabla();
        cargarComboPostulaciones();
        cargarComboUsuarios();
        cargarComboEstados();
        cargarDatosTabla();
    }

    // Constructor que preserva la sesión
    public FrmBitacoraPostulacion(Usuario usuarioLogueado) {
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
                new Object[]{"ID", "Postulación", "Estado Anterior", "Estado Nuevo", "Observación", "Fecha Cambio", "Usuario"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblBitacora.setModel(modeloTabla);
    }

    private void cargarComboPostulaciones() {
        DefaultComboBoxModel<Postulacion> modelCombo = new DefaultComboBoxModel<>();
        List<Postulacion> lista = postulacionController.findPostulacionEntities();
        for (Postulacion p : lista) {
            modelCombo.addElement(p);
        }
        cmbPostulacion.setModel(modelCombo);
    }

    private void cargarComboUsuarios() {
        DefaultComboBoxModel<Usuario> modelCombo = new DefaultComboBoxModel<>();
        List<Usuario> lista = usuarioController.findUsuarioEntities();
        for (Usuario u : lista) {
            modelCombo.addElement(u);
        }
        cmbUsuario.setModel(modelCombo);
    }

    private void cargarComboEstados() {
        String[] estados = new String[]{"Postulado", "En Revisión", "Evaluación Técnica", "Entrevistado", "Aceptado", "Rechazado", "Cancelado"};
        cmbEstadoAnterior.setModel(new DefaultComboBoxModel<>(estados));
        cmbEstadoNuevo.setModel(new DefaultComboBoxModel<>(estados));
    }

    public void cargarDatosTabla() {
        modeloTabla.setRowCount(0);
        List<BitacoraPostulacion> lista = bitacoraController.findBitacoraPostulacionEntities();

        for (BitacoraPostulacion b : lista) {
            String fechaTexto = b.getFechaCambio() != null
                    ? formatoFecha.format(b.getFechaCambio())
                    : "N/A";

            String postulacionTexto = b.getIdPostulacion() != null
                    ? "Postulación #" + b.getIdPostulacion().getIdPostulacion()
                    : "N/A";

            String usuarioTexto = b.getIdUsuario() != null
                    ? (b.getIdUsuario().getNombres() + " " + b.getIdUsuario().getApellidos())
                    : "N/A";

            modeloTabla.addRow(new Object[]{
                b.getIdBitacora(),
                postulacionTexto,
                b.getEstadoAnterior(),
                b.getEstadoNuevo(),
                b.getObservacion(),
                fechaTexto,
                usuarioTexto
            });
        }
    }

    private void limpiarFormulario() {
        idSeleccionado = null;
        if (cmbPostulacion.getItemCount() > 0) {
            cmbPostulacion.setSelectedIndex(0);
        }
        if (cmbUsuario.getItemCount() > 0) {
            cmbUsuario.setSelectedIndex(0);
        }
        if (cmbEstadoAnterior.getItemCount() > 0) {
            cmbEstadoAnterior.setSelectedIndex(0);
        }
        if (cmbEstadoNuevo.getItemCount() > 0) {
            cmbEstadoNuevo.setSelectedIndex(0);
        }
        txtObservacion.setText("");
        tblBitacora.clearSelection();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cmbPostulacion = new javax.swing.JComboBox<>();
        cmbUsuario = new javax.swing.JComboBox<>();
        cmbEstadoAnterior = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cmbEstadoNuevo = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObservacion = new javax.swing.JTextArea();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBitacora = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("HISTORIAL Y BITACORA DE POSTULACIONES");

        jLabel2.setText("Postulacion");

        jLabel3.setText("Usuario");

        jLabel4.setText("Estado Anterior");

        jLabel5.setText("Estado");

        jLabel6.setText("Observacion");

        txtObservacion.setColumns(20);
        txtObservacion.setRows(5);
        jScrollPane1.setViewportView(txtObservacion);

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

        tblBitacora.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblBitacora);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(65, 65, 65)
                        .addComponent(cmbPostulacion, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbEstadoAnterior, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbUsuario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbEstadoNuevo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(btnGuardar)
                .addGap(74, 74, 74)
                .addComponent(btnActualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEliminar)
                .addGap(89, 89, 89)
                .addComponent(btnLimpiar)
                .addGap(74, 74, 74)
                .addComponent(btnRegresar)
                .addGap(47, 47, 47))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 807, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(281, 281, 281))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLabel1)
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbPostulacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbEstadoAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmbEstadoNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnActualizar)
                    .addComponent(btnEliminar)
                    .addComponent(btnLimpiar)
                    .addComponent(btnRegresar))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            Postulacion postSel = (Postulacion) cmbPostulacion.getSelectedItem();
            Usuario usSel = (Usuario) cmbUsuario.getSelectedItem();

            if (postSel == null || usSel == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una postulación y un usuario.", "Atención", JOptionPane.WARNING_MESSAGE);
                return;
            }

            BitacoraPostulacion b = new BitacoraPostulacion();
            b.setIdPostulacion(postSel);
            b.setIdUsuario(usSel);
            b.setEstadoAnterior(cmbEstadoAnterior.getSelectedItem().toString());
            b.setEstadoNuevo(cmbEstadoNuevo.getSelectedItem().toString());
            b.setObservacion(txtObservacion.getText().trim());
            b.setFechaCambio(new Date());

            bitacoraController.create(b);

            JOptionPane.showMessageDialog(this, "Registro de bitácora guardado exitosamente.");
            cargarDatosTabla();
            limpiarFormulario();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar registro en la bitácora: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        if (idSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un registro de la tabla primero.");
            return;
        }

        try {
            BitacoraPostulacion b = bitacoraController.findBitacoraPostulacion(idSeleccionado);
            if (b != null) {
                b.setIdPostulacion((Postulacion) cmbPostulacion.getSelectedItem());
                b.setIdUsuario((Usuario) cmbUsuario.getSelectedItem());
                b.setEstadoAnterior(cmbEstadoAnterior.getSelectedItem().toString());
                b.setEstadoNuevo(cmbEstadoNuevo.getSelectedItem().toString());
                b.setObservacion(txtObservacion.getText().trim());

                bitacoraController.edit(b);

                JOptionPane.showMessageDialog(this, "Bitácora actualizada correctamente.");
                cargarDatosTabla();
                limpiarFormulario();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar la bitácora: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (idSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un registro de la tabla primero.");
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de eliminar este registro de bitácora?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                bitacoraController.destroy(idSeleccionado);

                JOptionPane.showMessageDialog(this, "Registro eliminado.");
                cargarDatosTabla();
                limpiarFormulario();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar el registro: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        /*FrmMenu menu = new FrmMenu();
        menu.setVisible(true);*/
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(FrmBitacoraPostulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmBitacoraPostulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmBitacoraPostulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmBitacoraPostulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmBitacoraPostulacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cmbEstadoAnterior;
    private javax.swing.JComboBox<String> cmbEstadoNuevo;
    private javax.swing.JComboBox<reclutamiento.prueba2.model.Postulacion> cmbPostulacion;
    private javax.swing.JComboBox<reclutamiento.prueba2.model.Usuario> cmbUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblBitacora;
    private javax.swing.JTextArea txtObservacion;
    // End of variables declaration//GEN-END:variables
}
