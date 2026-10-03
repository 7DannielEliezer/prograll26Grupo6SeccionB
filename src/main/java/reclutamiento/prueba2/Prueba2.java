/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package reclutamiento.prueba2;

/**
 *
 * @author PGutierrez
 */
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import reclutamiento.prueba2.controller.RolJpaController;
import reclutamiento.prueba2.controller.UsuarioJpaController;
import reclutamiento.prueba2.model.Rol;
import reclutamiento.prueba2.model.Usuario;
import reclutamiento.prueba2.view.FrmLogin;

public class Prueba2 {

    public static void main(String[] args) {
        try {
            javax.swing.UIManager.setLookAndFeel(
                    javax.swing.UIManager.getSystemLookAndFeelClassName()
            );
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(Prueba2.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }

        // Verificar y sembrar usuario por defecto si no existe ninguno
        inicializarUsuarioAdministrador();

        // Iniciar la ventana de Login
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FrmLogin().setVisible(true);
            }
        });
    }

    private static void inicializarUsuarioAdministrador() {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("Prueba2PU");
            RolJpaController rolController = new RolJpaController(emf);
            UsuarioJpaController usuarioController = new UsuarioJpaController(emf);

            // 1. Crear Rol por defecto si no existen roles
            List<Rol> roles = rolController.findRolEntities();
            Rol rolAdmin;
            if (roles.isEmpty()) {
                rolAdmin = new Rol();
                rolAdmin.setNombreRol("Administrador");
                rolAdmin.setDescripcion("Acceso total al sistema");
                rolController.create(rolAdmin);
            } else {
                rolAdmin = roles.get(0);
            }

            // 2. Crear Usuario inicial si no existen usuarios
            List<Usuario> usuarios = usuarioController.findUsuarioEntities();
            if (usuarios.isEmpty()) {
                Usuario admin = new Usuario();
                admin.setIdRol(rolAdmin);
                admin.setNombres("Admin");
                admin.setApellidos("Sistema");
                admin.setCorreo("admin@reclutamiento.com");
                admin.setPasswordHash("admin123");
                admin.setEstado(true);
                admin.setFechaCreacion(new Date());

                usuarioController.create(admin);
                System.out.println(">>> USUARIO ADMINISTRADOR CREADO CON ÉXITO <<<");
            }
        } catch (Exception e) {
            System.err.println("Error al inicializar datos por defecto: " + e.getMessage());
        }
    }
}
