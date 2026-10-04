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
import reclutamiento.prueba2.controller.CandidatoJpaController;
import reclutamiento.prueba2.controller.PostulacionJpaController;
import reclutamiento.prueba2.controller.VacanteJpaController;
import reclutamiento.prueba2.model.Candidato;
import reclutamiento.prueba2.model.Postulacion;
import reclutamiento.prueba2.model.Usuario;
import reclutamiento.prueba2.model.Vacante;

/**
 *
 * @author PGutierrez
 */
public class FrmPostulacion extends javax.swing.JFrame {

    private final PostulacionJpaController postulacionController;
    private final VacanteJpaController vacanteController;
    private final CandidatoJpaController candidatoController;
    private DefaultTableModel modeloTabla;
    private Integer idSeleccionado = null;
    private final SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
    private Usuario usuarioLogueado;

    public FrmPostulacion() {
        initComponents();
        this.setLocationRelativeTo(null);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Prueba2PU");
        postulacionController = new PostulacionJpaController(emf);
        vacanteController = new VacanteJpaController(emf);
        candidatoController = new CandidatoJpaController(emf);

        configurarTabla();
        cargarComboVacantes();
        cargarComboCandidatos();
        cargarComboEstados();
        cargarDatosTabla();
    }

    /**
     * Constructor sobrecargado para recibir el usuario logueado
     */
    public FrmPostulacion(Usuario usuarioLogueado) {
        this();
        this.usuarioLogueado = usuarioLogueado;
        validarPermisos();
    }

    /**
     * Restringe las acciones según el rol del usuario conectado
     */
    private void validarPermisos() {
        if (usuarioLogueado != null && usuarioLogueado.getIdRol() != null) {
            String rol = usuarioLogueado.getIdRol().getNombreRol().trim();

            if (rol.equalsIgnoreCase("Reclutador Junior")) {
                btnActualizar.setEnabled(false);
                btnEliminar.setEnabled(false);
            }
        }
    }

    private void configurarTabla() {
        modeloTabla = new DefaultTableModel(
                new Object[]{"ID", "Vacante", "Candidato", "Fecha Postulacion", "Estado"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblPostulaciones.setModel(modeloTabla);
    }

    private void cargarComboVacantes() {
        DefaultComboBoxModel<Vacante> modelCombo = new DefaultComboBoxModel<>();
        List<Vacante> vacantes = vacanteController.findVacanteEntities();
        for (Vacante v : vacantes) {
            modelCombo.addElement(v);
        }
        cmbVacante.setModel((DefaultComboBoxModel) modelCombo);
    }

    private void cargarComboCandidatos() {
        DefaultComboBoxModel<Candidato> modelCombo = new DefaultComboBoxModel<>();
        List<Candidato> candidatos = candidatoController.findCandidatoEntities();
        for (Candidato c : candidatos) {
            modelCombo.addElement(c);
        }
        cmbCandidato.setModel((DefaultComboBoxModel) modelCombo);
    }

    private void cargarComboEstados() {
        cmbEstadoCandidato.setModel(new DefaultComboBoxModel<>(
                new String[]{"aplicado", "en evaluacion", "seleccionado", "rechazado"}
        ));
    }

    public void cargarDatosTabla() {
        modeloTabla.setRowCount(0);
        List<Postulacion> lista = postulacionController.findPostulacionEntities();

        for (Postulacion p : lista) {
            String fechaTexto = p.getFechaPostulacion() != null
                    ? formatoFecha.format(p.getFechaPostulacion())
                    : "N/A";

            modeloTabla.addRow(new Object[]{
                p.getIdPostulacion(),
                p.getIdVacante() != null ? p.getIdVacante().getTituloVacante() : "N/A",
                p.getIdCandidato() != null ? (p.getIdCandidato().getNombres() + " " + p.getIdCandidato().getApellidos()) : "N/A",
                fechaTexto,
                p.getEstadoCandidato()
            });
        }
    }

    private void limpiarFormulario() {
        idSeleccionado = null;
        if (cmbVacante.getItemCount() > 0) {
            cmbVacante.setSelectedIndex(0);
        }
        if (cmbCandidato.getItemCount() > 0) {
            cmbCandidato.setSelectedIndex(0);
        }
        cmbEstadoCandidato.setSelectedItem("aplicado");
        tblPostulaciones.clearSelection();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbVacante = new javax.swing.JComboBox<>();
        cmbCandidato = new javax.swing.JComboBox<>();
        cmbEstadoCandidato = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPostulaciones = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        cmbEstadoCandidato.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "aplicado", "en evaluacion", "seleccionado", "rechazado" }));
        cmbEstadoCandidato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEstadoCandidatoActionPerformed(evt);
            }
        });

        jLabel1.setText("Vacante");

        jLabel2.setText("Candidato");

        jLabel3.setText("Estado Candidato");

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

        tblPostulaciones.setModel(new javax.swing.table.DefaultTableModel(
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
        tblPostulaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPostulacionesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPostulaciones);

        jLabel4.setText("INGRESO DATOS DE POSTULACION");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(272, 272, 272))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(btnGuardar)
                        .addGap(91, 91, 91)
                        .addComponent(btnActualizar)
                        .addGap(69, 69, 69)
                        .addComponent(btnEliminar)
                        .addGap(71, 71, 71)
                        .addComponent(btnLimpiar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
                        .addComponent(btnRegresar)
                        .addGap(38, 38, 38))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(48, 48, 48)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmbCandidato, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbEstadoCandidato, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(99, 99, 99)
                                .addComponent(cmbVacante, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 762, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel4)
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmbVacante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(67, 67, 67)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbCandidato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbEstadoCandidato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnActualizar)
                    .addComponent(btnEliminar)
                    .addComponent(btnLimpiar)
                    .addComponent(btnRegresar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            Vacante vacanteSel = (Vacante) cmbVacante.getSelectedItem();
            Candidato candidatoSel = (Candidato) cmbCandidato.getSelectedItem();

            if (vacanteSel == null || candidatoSel == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una vacante y un candidato.", "Atención", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Postulacion p = new Postulacion();
            p.setIdVacante(vacanteSel);
            p.setIdCandidato(candidatoSel);
            p.setFechaPostulacion(new Date());
            if (cmbEstadoCandidato.getSelectedItem() != null) {
                p.setEstadoCandidato(cmbEstadoCandidato.getSelectedItem().toString().toLowerCase().trim());
            } else {
                p.setEstadoCandidato("aplicado");
            }

            postulacionController.create(p);

            JOptionPane.showMessageDialog(this, "Postulación registrada exitosamente.");
            cargarDatosTabla();
            limpiarFormulario();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar la postulación: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void tblPostulacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPostulacionesMouseClicked
        int fila = tblPostulaciones.getSelectedRow();
        if (fila >= 0) {
            idSeleccionado = (Integer) modeloTabla.getValueAt(fila, 0);

            // Seleccionar Vacante en Combo
            String tituloVacanteTabla = modeloTabla.getValueAt(fila, 1).toString();
            for (int i = 0; i < cmbVacante.getItemCount(); i++) {
                Vacante v = cmbVacante.getItemAt(i);
                if (v != null && v.getTituloVacante().equalsIgnoreCase(tituloVacanteTabla)) {
                    cmbVacante.setSelectedIndex(i);
                    break;
                }
            }

            // Seleccionar Candidato en Combo
            String nombreCandidatoTabla = modeloTabla.getValueAt(fila, 2).toString();
            for (int i = 0; i < cmbCandidato.getItemCount(); i++) {
                Candidato c = cmbCandidato.getItemAt(i);
                if (c != null) {
                    String nombreCompleto = c.getNombres() + " " + c.getApellidos();
                    if (nombreCompleto.equalsIgnoreCase(nombreCandidatoTabla)) {
                        cmbCandidato.setSelectedIndex(i);
                        break;
                    }
                }
            }

            cmbEstadoCandidato.setSelectedItem(modeloTabla.getValueAt(fila, 4) != null ? modeloTabla.getValueAt(fila, 4).toString() : "En Proceso");
        }
    }//GEN-LAST:event_tblPostulacionesMouseClicked

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        if (idSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Seleccione una postulación de la tabla primero.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Postulacion p = postulacionController.findPostulacion(idSeleccionado);
            if (p != null) {
                p.setIdVacante((Vacante) cmbVacante.getSelectedItem());
                p.setIdCandidato((Candidato) cmbCandidato.getSelectedItem());
                p.setEstadoCandidato(cmbEstadoCandidato.getSelectedItem().toString());

                postulacionController.edit(p);

                JOptionPane.showMessageDialog(this, "Postulación actualizada exitosamente.");
                cargarDatosTabla();
                limpiarFormulario();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar la postulación: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (idSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Seleccione una postulación de la tabla primero.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Desea eliminar la postulación seleccionada?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                postulacionController.destroy(idSeleccionado);
                JOptionPane.showMessageDialog(this, "Postulación eliminada exitosamente.");
                cargarDatosTabla();
                limpiarFormulario();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar la postulación: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        FrmMenu menu = new FrmMenu();
        menu.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    private void cmbEstadoCandidatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEstadoCandidatoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbEstadoCandidatoActionPerformed

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
            java.util.logging.Logger.getLogger(FrmPostulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPostulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPostulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPostulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPostulacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<reclutamiento.prueba2.model.Candidato> cmbCandidato;
    private javax.swing.JComboBox<String> cmbEstadoCandidato;
    private javax.swing.JComboBox<reclutamiento.prueba2.model.Vacante> cmbVacante;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPostulaciones;
    // End of variables declaration//GEN-END:variables
}
