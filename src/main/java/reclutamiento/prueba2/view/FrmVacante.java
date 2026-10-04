/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package reclutamiento.prueba2.view;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import reclutamiento.prueba2.controller.EmpresaJpaController;
import reclutamiento.prueba2.controller.UsuarioJpaController;
import reclutamiento.prueba2.controller.VacanteJpaController;
import reclutamiento.prueba2.model.Empresa;
import reclutamiento.prueba2.model.Usuario;
import reclutamiento.prueba2.model.Vacante;

/**
 *
 * @author PGutierrez
 */
public class FrmVacante extends javax.swing.JFrame {

    private final VacanteJpaController vacanteController;
    private final EmpresaJpaController empresaController;
    private final UsuarioJpaController usuarioController;
    private DefaultTableModel modeloTabla;
    private Integer idSeleccionado = null;
    private final SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
    private Usuario usuarioLogueado;

    public FrmVacante() {
        initComponents();
        this.setLocationRelativeTo(null);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Prueba2PU");
        vacanteController = new VacanteJpaController(emf);
        empresaController = new EmpresaJpaController(emf);
        usuarioController = new UsuarioJpaController(emf);

        configurarTabla();
        cargarEmpresas();
        cargarComboEstado();
        cargarDatosTabla();
    }

    public FrmVacante(Usuario usuarioLogueado) {
        this();
        this.usuarioLogueado = usuarioLogueado;
        validarPermisos();
    }

    private void validarPermisos() {
        if (usuarioLogueado != null && usuarioLogueado.getIdRol() != null) {
            String rol = usuarioLogueado.getIdRol().getNombreRol().trim();

            // Deshabilita acciones restrictivas según el rol del sistema
            if (rol.equalsIgnoreCase("Reclutador Junior")) {
                btnActualizar.setEnabled(false);
                btnEliminar.setEnabled(false);
            }
        }
    }

    private void configurarTabla() {
        modeloTabla = new DefaultTableModel(
                new Object[]{"ID", "Empresa", "Título Vacante", "Descripción", "Sueldo", "Estado", "Fecha Pub."}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblVacantes.setModel(modeloTabla);
    }

    private void cargarEmpresas() {
        DefaultComboBoxModel<Empresa> modelCombo = new DefaultComboBoxModel<>();
        List<Empresa> lista = empresaController.findEmpresaEntities();
        for (Empresa emp : lista) {
            modelCombo.addElement(emp);
        }
        cmbEmpresa.setModel(modelCombo);
    }

    private void cargarComboEstado() {
        cmbEstado.setModel(new DefaultComboBoxModel<>(new String[]{"Activa", "Inactiva", "Cerrada"}));
    }

    public void cargarDatosTabla() {
        modeloTabla.setRowCount(0);
        List<Vacante> lista = vacanteController.findVacanteEntities();

        for (Vacante v : lista) {
            String fechaTexto = v.getFechaPublicacion() != null
                    ? formatoFecha.format(v.getFechaPublicacion())
                    : "N/A";

            modeloTabla.addRow(new Object[]{
                v.getIdVacante(),
                v.getIdEmpresa() != null ? v.getIdEmpresa().getNombreEmpresa() : "N/A",
                v.getTituloVacante(),
                v.getDescripcion(),
                v.getSueldoOfrecido(),
                v.getEstadoVacante(),
                fechaTexto
            });
        }
    }

    private void limpiarFormulario() {
        idSeleccionado = null;
        if (cmbEmpresa.getItemCount() > 0) {
            cmbEmpresa.setSelectedIndex(0);
        }
        txtTituloVacante.setText("");
        txtDescripcion.setText("");
        txtSueldoOfrecido.setText("");
        cmbEstado.setSelectedItem("Activa");
        tblVacantes.clearSelection();
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
        cmbEmpresa = new javax.swing.JComboBox<>();
        txtTituloVacante = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        txtSueldoOfrecido = new javax.swing.JTextField();
        cmbEstado = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblVacantes = new javax.swing.JTable();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("INGRESO DATOS PARA VACANTES");

        jLabel2.setText("Empresa");

        jLabel3.setText("Titulo Vacante");

        jLabel4.setText("Descripcion");

        jLabel5.setText("Sueldo Ofrecido");

        jLabel6.setText("Estado");

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtDescripcion);

        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo", "Cerrado" }));
        cmbEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEstadoActionPerformed(evt);
            }
        });

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

        tblVacantes.setModel(new javax.swing.table.DefaultTableModel(
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
        tblVacantes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVacantesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblVacantes);

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(315, 315, 315)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbEmpresa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTituloVacante)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
                            .addComponent(txtSueldoOfrecido)
                            .addComponent(cmbEstado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(btnGuardar)
                        .addGap(77, 77, 77)
                        .addComponent(btnActualizar)
                        .addGap(70, 70, 70)
                        .addComponent(btnEliminar)
                        .addGap(68, 68, 68)
                        .addComponent(btnLimpiar)
                        .addGap(56, 56, 56)
                        .addComponent(btnRegresar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 745, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel1)
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTituloVacante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtSueldoOfrecido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnActualizar)
                    .addComponent(btnEliminar)
                    .addComponent(btnLimpiar)
                    .addComponent(btnRegresar))
                .addGap(38, 38, 38)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbEstadoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            Empresa empresaSel = (Empresa) cmbEmpresa.getSelectedItem();
            if (empresaSel == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una empresa válida.", "Atención", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (txtTituloVacante.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "El título de la vacante es obligatorio.", "Atención", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Usuario usuarioCreador = this.usuarioLogueado;
            if (usuarioCreador == null) {
                usuarioCreador = usuarioController.findUsuario(1);
                if (usuarioCreador == null) {
                    List<Usuario> usuarios = usuarioController.findUsuarioEntities();
                    if (!usuarios.isEmpty()) {
                        usuarioCreador = usuarios.get(0);
                    } else {
                        JOptionPane.showMessageDialog(this, "No existe un usuario creador en el sistema.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }

            Vacante v = new Vacante();
            v.setIdEmpresa(empresaSel);
            v.setIdUsuarioCreador(usuarioCreador);
            v.setTituloVacante(txtTituloVacante.getText().trim());
            v.setDescripcion(txtDescripcion.getText().trim());

            String sueldoTxt = txtSueldoOfrecido.getText().trim();
            if (!sueldoTxt.isEmpty()) {
                v.setSueldoOfrecido(new BigDecimal(sueldoTxt));
            } else {
                v.setSueldoOfrecido(BigDecimal.ZERO);
            }

            v.setEstadoVacante(cmbEstado.getSelectedItem().toString());
            v.setFechaPublicacion(new Date());

            vacanteController.create(v);

            JOptionPane.showMessageDialog(this, "Vacante creada exitosamente.");
            cargarDatosTabla();
            limpiarFormulario();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un sueldo numérico válido (ej. 3500.00).", "Atención", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar la vacante: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void tblVacantesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVacantesMouseClicked
        int fila = tblVacantes.getSelectedRow();
        if (fila >= 0) {
            idSeleccionado = (Integer) modeloTabla.getValueAt(fila, 0);

            String nombreEmpresaTabla = modeloTabla.getValueAt(fila, 1).toString();
            for (int i = 0; i < cmbEmpresa.getItemCount(); i++) {
                Empresa e = cmbEmpresa.getItemAt(i);
                if (e != null && e.getNombreEmpresa().equalsIgnoreCase(nombreEmpresaTabla)) {
                    cmbEmpresa.setSelectedIndex(i);
                    break;
                }
            }

            txtTituloVacante.setText(modeloTabla.getValueAt(fila, 2) != null ? modeloTabla.getValueAt(fila, 2).toString() : "");
            txtDescripcion.setText(modeloTabla.getValueAt(fila, 3) != null ? modeloTabla.getValueAt(fila, 3).toString() : "");
            txtSueldoOfrecido.setText(modeloTabla.getValueAt(fila, 4) != null ? modeloTabla.getValueAt(fila, 4).toString() : "");
            cmbEstado.setSelectedItem(modeloTabla.getValueAt(fila, 5) != null ? modeloTabla.getValueAt(fila, 5).toString() : "Activa");
        }
    }//GEN-LAST:event_tblVacantesMouseClicked

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        if (idSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Seleccione una vacante de la tabla primero.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Vacante v = vacanteController.findVacante(idSeleccionado);
            if (v != null) {
                if (txtTituloVacante.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "El título de la vacante es obligatorio.", "Atención", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                v.setIdEmpresa((Empresa) cmbEmpresa.getSelectedItem());
                v.setTituloVacante(txtTituloVacante.getText().trim());
                v.setDescripcion(txtDescripcion.getText().trim());

                String sueldoTxt = txtSueldoOfrecido.getText().trim();
                if (!sueldoTxt.isEmpty()) {
                    v.setSueldoOfrecido(new BigDecimal(sueldoTxt));
                }

                v.setEstadoVacante(cmbEstado.getSelectedItem().toString());

                vacanteController.edit(v);

                JOptionPane.showMessageDialog(this, "Vacante actualizada exitosamente.");
                cargarDatosTabla();
                limpiarFormulario();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un sueldo numérico válido.", "Atención", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar la vacante: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (idSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Seleccione una vacante de la tabla primero.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Desea eliminar la vacante seleccionada?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                vacanteController.destroy(idSeleccionado);
                JOptionPane.showMessageDialog(this, "Vacante eliminada exitosamente.");
                cargarDatosTabla();
                limpiarFormulario();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar la vacante: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(FrmVacante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmVacante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmVacante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmVacante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmVacante().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<reclutamiento.prueba2.model.Empresa> cmbEmpresa;
    private javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblVacantes;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtSueldoOfrecido;
    private javax.swing.JTextField txtTituloVacante;
    // End of variables declaration//GEN-END:variables
}
