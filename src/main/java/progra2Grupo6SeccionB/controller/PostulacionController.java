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
import progra2Grupo6SeccionB.model.Postulacion;

@Path("/postulaciones")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PostulacionController {

    // 1. Listar todas las postulaciones (GET http://localhost:8080/postulaciones)
    @GET
    public List<Postulacion> listarTodas() {
        return Postulacion.listAll();
    }

    // 2. Buscar postulación por ID (GET http://localhost:8080/postulaciones/1)
    @GET
    @Path("/{id}")
    public Response obtenerPorId(@PathParam("id") Integer id) {
        Postulacion postulacion = Postulacion.findById(id);
        if (postulacion == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(postulacion).build();
    }

    // 3. Crear postulación / Postular candidato (POST http://localhost:8080/postulaciones)
    @POST
    @Transactional
    public Response crear(Postulacion postulacion) {
        if (postulacion.getFechaPostulacion() == null) {
            postulacion.setFechaPostulacion(new Date());
        }
        if (postulacion.getEstadoCandidato() == null) {
            postulacion.setEstadoCandidato("En Proceso");
        }
        postulacion.persist();
        return Response.status(Response.Status.CREATED).entity(postulacion).build();
    }

    // 4. Cambiar estado de postulación (PUT http://localhost:8080/postulaciones/1)
    @PUT
    @Path("/{id}")
    @Transactional
    public Response actualizarEstado(@PathParam("id") Integer id, Postulacion datos) {
        Postulacion postulacion = Postulacion.findById(id);
        if (postulacion == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        postulacion.setEstadoCandidato(datos.getEstadoCandidato());
        return Response.ok(postulacion).build();
    }

    // 5. Eliminar postulación (DELETE http://localhost:8080/postulaciones/1)
    @DELETE
    @Path("/{id}")
    @Transactional
    public Response eliminar(@PathParam("id") Integer id) {
        boolean eliminado = Postulacion.deleteById(id);
        if (!eliminado) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }
}
