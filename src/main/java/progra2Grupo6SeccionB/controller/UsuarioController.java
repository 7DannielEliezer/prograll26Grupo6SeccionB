/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progra2Grupo6SeccionB.controller;

/**
 *
 * @author PGutierrez
 */

import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Date;
import java.util.List;
import progra2Grupo6SeccionB.model.Usuario;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioController {

    // 1. Listar todos los usuarios (GET http://localhost:8080/usuarios)
    @GET
    public List<Usuario> listarTodos() {
        return Usuario.listAll();
    }

    // 2. Buscar usuario por ID (GET http://localhost:8080/usuarios/1)
    @GET
    @Path("/{id}")
    public Response obtenerPorId(@PathParam("id") Integer id) {
        Usuario usuario = Usuario.findById(id);
        if (usuario == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(usuario).build();
    }

    // 3. Crear nuevo usuario (POST http://localhost:8080/usuarios)
    @POST
    @Transactional
    public Response crear(Usuario usuario) {
        if (usuario.getFechaCreacion() == null) {
            usuario.setFechaCreacion(new Date());
        }
        if (usuario.getEstado() == null) {
            usuario.setEstado("Activo");
        }
        usuario.persist();
        return Response.status(Response.Status.CREATED).entity(usuario).build();
    }

    // 4. Actualizar usuario (PUT http://localhost:8080/usuarios/1)
    @PUT
    @Path("/{id}")
    @Transactional
    public Response actualizar(@PathParam("id") Integer id, Usuario datos) {
        Usuario usuario = Usuario.findById(id);
        if (usuario == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        usuario.setFullname(datos.getFullname());
        usuario.setNombres(datos.getNombres());
        usuario.setApellidos(datos.getApellidos());
        usuario.setCorreo(datos.getCorreo());
        usuario.setEstado(datos.getEstado());
        if (datos.getContrasena() != null && !datos.getContrasena().isBlank()) {
            usuario.setContrasena(datos.getContrasena());
        }
        if (datos.getIdRol() != null) {
            usuario.setIdRol(datos.getIdRol());
        }
        return Response.ok(usuario).build();
    }

    // 5. Eliminar usuario (DELETE http://localhost:8080/usuarios/1)
    @DELETE
    @Path("/{id}")
    @Transactional
    public Response eliminar(@PathParam("id") Integer id) {
        boolean eliminado = Usuario.deleteById(id);
        if (!eliminado) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }

    // 6. Endpoint de autenticación / Login simple (POST http://localhost:8080/usuarios/login)
    @POST
    @Path("/login")
    public Response login(Usuario credenciales) {
        Usuario usuario = Usuario.find("correo = ?1 and contrasena = ?2", 
                credenciales.getCorreo(), credenciales.getContrasena()).firstResult();
        
        if (usuario == null) {
            return Response.status(Response.Status.UNAUTHORIZED)
                           .entity("{\"mensaje\": \"Credenciales incorrectas\"}").build();
        }
        return Response.ok(usuario).build();
    }
}
