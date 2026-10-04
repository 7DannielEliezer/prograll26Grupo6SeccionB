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
import reclutamiento.prueba2.controller.ContratacionJpaController;
import reclutamiento.prueba2.controller.EmpresaJpaController;
import reclutamiento.prueba2.controller.FacturaJpaController;
import reclutamiento.prueba2.model.Contratacion;
import reclutamiento.prueba2.model.Empresa;
import reclutamiento.prueba2.model.Factura;
import reclutamiento.prueba2.model.Usuario;
import reclutamiento.prueba2.view.FrmMenu;

/**
 *
 * @author PGutierrez
 */
public class FrmFactura extends javax.swing.JFrame {

    private Usuario usuarioLogueado;
    private final FacturaJpaController facturaController;
    private final EmpresaJpaController empresaController;
    private final ContratacionJpaController contratacionController;
    private DefaultTableModel modeloTabla;
    private Integer idSeleccionado = null;
    private final SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    /**
     * Creates new form FrmFactura
     */
    public FrmFactura() {
        initComponents();
        this.setLocationRelativeTo(null);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Prueba2PU");
        facturaController = new FacturaJpaController(emf);
        empresaController = new EmpresaJpaController(emf);
        contratacionController = new ContratacionJpaController(emf);

        configurarTabla();
        cargarComboEmpresas();
        cargarComboContrataciones();
        cargarComboEstados();
        cargarDatosTabla();
    }

    // Constructor para mantener la sesión activa
    public FrmFactura(Usuario usuarioLogueado) {
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
                new Object[]{"ID", "Empresa", "Contratación", "No. Factura", "Monto Total", "Fecha Emisión", "Estado"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblFacturas.setModel(modeloTabla);
    }

    private void cargarComboEmpresas() {
        DefaultComboBoxModel<Empresa> modelCombo = new DefaultComboBoxModel<>();
        List<Empresa> empresas = empresaController.findEmpresaEntities();
        for (Empresa e : empresas) {
            modelCombo.addElement(e);
        }
        cmbEmpresa.setModel(modelCombo);
    }

    private void cargarComboContrataciones() {
        DefaultComboBoxModel<Contratacion> modelCombo = new DefaultComboBoxModel<>();
        List<Contratacion> contrataciones = contratacionController.findContratacionEntities();
        for (Contratacion c : contrataciones) {
            modelCombo.addElement(c);
        }
        cmbContratacion.setModel(modelCombo);
    }

    private void cargarComboEstados() {
        cmbEstadoFactura.setModel(new DefaultComboBoxModel<>(
                new String[]{"Pendiente", "Pagada", "Anulada"}
        ));
    }

    public void cargarDatosTabla() {
        modeloTabla.setRowCount(0);
        List<Factura> lista = facturaController.findFacturaEntities();

        for (Factura f : lista) {
            String fechaTexto = f.getFechaEmision() != null
                    ? formatoFecha.format(f.getFechaEmision())
                    : "N/A";

            modeloTabla.addRow(new Object[]{
                f.getIdFactura(),
                f.getIdEmpresa() != null ? f.getIdEmpresa().getNombreEmpresa() : "N/A",
                f.getIdContratacion() != null ? f.getIdContratacion().toString() : "N/A",
                f.getNumeroFactura(),
                f.getMontoTotal(),
                fechaTexto,
                f.getEstadoFactura()
            });
        }
    }

    private void limpiarFormulario() {
        idSeleccionado = null;
        if (cmbEmpresa.getItemCount() > 0) {
            cmbEmpresa.setSelectedIndex(0);
        }
        if (cmbContratacion.getItemCount() > 0) {
            cmbContratacion.setSelectedIndex(0);
        }
        txtNumeroFactura.setText("");
        txtMontoTotal.setText("");
        cmbEstadoFactura.setSelectedItem("Pendiente");
        tblFacturas.clearSelection();
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cmbEmpresa = new javax.swing.JComboBox<>();
        cmbContratacion = new javax.swing.JComboBox<>();
        txtNumeroFactura = new javax.swing.JTextField();
        txtMontoTotal = new javax.swing.JTextField();
        cmbEstadoFactura = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFacturas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("Empresa");

        jLabel2.setText("Contratacion");

        jLabel3.setText("Numero Factura");

        jLabel4.setText("Monto Total");

        jLabel5.setText("Estado Factura");

        jLabel6.setText("INGRESO DE DATOS PARA FACTURACIONES");

        cmbEstadoFactura.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pendiente", "Pagada", "Anulada" }));

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

        tblFacturas.setModel(new javax.swing.table.DefaultTableModel(
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
        tblFacturas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFacturasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblFacturas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(298, 298, 298)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addComponent(btnGuardar)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(88, 88, 88)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmbEmpresa, 0, 505, Short.MAX_VALUE)
                                    .addComponent(cmbContratacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbEstadoFactura, 0, 505, Short.MAX_VALUE)
                                    .addComponent(txtMontoTotal)
                                    .addComponent(txtNumeroFactura)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addComponent(btnActualizar)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 813, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnEliminar)
                        .addGap(92, 92, 92)
                        .addComponent(btnLimpiar)
                        .addGap(88, 88, 88)
                        .addComponent(btnRegresar)
                        .addGap(57, 57, 57))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel6)
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbContratacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNumeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtMontoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmbEstadoFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnEliminar)
                    .addComponent(btnLimpiar)
                    .addComponent(btnRegresar)
                    .addComponent(btnActualizar))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            Empresa empresaSel = (Empresa) cmbEmpresa.getSelectedItem();
            Contratacion contratacionSel = (Contratacion) cmbContratacion.getSelectedItem();

            if (empresaSel == null || contratacionSel == null) {
                JOptionPane.showMessageDialog(this, "Seleccione la empresa y la contratación.", "Atención", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (txtNumeroFactura.getText().trim().isEmpty() || txtMontoTotal.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete el número de factura y el monto total.", "Atención", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Factura f = new Factura();
            f.setIdEmpresa(empresaSel);
            f.setIdContratacion(contratacionSel);
            f.setNumeroFactura(txtNumeroFactura.getText().trim());
            f.setMontoTotal(new BigDecimal(txtMontoTotal.getText().trim()));
            f.setFechaEmision(new Date());
            f.setEstadoFactura(cmbEstadoFactura.getSelectedItem().toString());

            facturaController.create(f);

            JOptionPane.showMessageDialog(this, "Factura registrada exitosamente.");
            cargarDatosTabla();
            limpiarFormulario();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un monto numérico válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void tblFacturasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFacturasMouseClicked
        int fila = tblFacturas.getSelectedRow();
        if (fila >= 0) {
            idSeleccionado = (Integer) modeloTabla.getValueAt(fila, 0);

            // Seleccionar Empresa
            String empresaTabla = modeloTabla.getValueAt(fila, 1).toString();
            for (int i = 0; i < cmbEmpresa.getItemCount(); i++) {
                Empresa e = cmbEmpresa.getItemAt(i);
                if (e != null && e.getNombreEmpresa().equalsIgnoreCase(empresaTabla)) {
                    cmbEmpresa.setSelectedIndex(i);
                    break;
                }
            }

            // Seleccionar Contratación
            String contratacionTabla = modeloTabla.getValueAt(fila, 2).toString();
            for (int i = 0; i < cmbContratacion.getItemCount(); i++) {
                Contratacion c = cmbContratacion.getItemAt(i);
                if (c != null && c.toString().equalsIgnoreCase(contratacionTabla)) {
                    cmbContratacion.setSelectedIndex(i);
                    break;
                }
            }

            txtNumeroFactura.setText(modeloTabla.getValueAt(fila, 3) != null ? modeloTabla.getValueAt(fila, 3).toString() : "");
            txtMontoTotal.setText(modeloTabla.getValueAt(fila, 4) != null ? modeloTabla.getValueAt(fila, 4).toString() : "");
            cmbEstadoFactura.setSelectedItem(modeloTabla.getValueAt(fila, 6) != null ? modeloTabla.getValueAt(fila, 6).toString() : "Pendiente");
        }
    }//GEN-LAST:event_tblFacturasMouseClicked

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        if (idSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un registro de la tabla primero.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Factura f = facturaController.findFactura(idSeleccionado);
            if (f != null) {
                f.setIdEmpresa((Empresa) cmbEmpresa.getSelectedItem());
                f.setIdContratacion((Contratacion) cmbContratacion.getSelectedItem());
                f.setNumeroFactura(txtNumeroFactura.getText().trim());
                f.setMontoTotal(new BigDecimal(txtMontoTotal.getText().trim()));
                f.setEstadoFactura(cmbEstadoFactura.getSelectedItem().toString());

                facturaController.edit(f);

                JOptionPane.showMessageDialog(this, "Factura actualizada exitosamente.");
                cargarDatosTabla();
                limpiarFormulario();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un monto numérico válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (idSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un registro de la tabla primero.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Eliminar la factura seleccionada?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                facturaController.destroy(idSeleccionado);
                JOptionPane.showMessageDialog(this, "Factura eliminada correctamente.");
                cargarDatosTabla();
                limpiarFormulario();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar la factura: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
            java.util.logging.Logger.getLogger(FrmFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmFactura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmFactura().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<reclutamiento.prueba2.model.Contratacion> cmbContratacion;
    private javax.swing.JComboBox<reclutamiento.prueba2.model.Empresa> cmbEmpresa;
    private javax.swing.JComboBox<String> cmbEstadoFactura;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblFacturas;
    private javax.swing.JTextField txtMontoTotal;
    private javax.swing.JTextField txtNumeroFactura;
    // End of variables declaration//GEN-END:variables
}
