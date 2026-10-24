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
import java.util.List;
import progra2Grupo6SeccionB.model.Candidato;

@Path("/candidatos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CandidatoController {

    // 1. Obtener todos los candidatos (GET http://localhost:8080/candidatos)
    @GET
    public List<Candidato> listarTodos() {
        return Candidato.listAll();
    }

    // 2. Buscar candidato por ID (GET http://localhost:8080/candidatos/1)
    @GET
    @Path("/{id}")
    public Response obtenerPorId(@PathParam("id") Integer id) {
        Candidato candidato = Candidato.findById(id);
        if (candidato == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(candidato).build();
    }

    // 3. Crear nuevo candidato (POST http://localhost:8080/candidatos)
    @POST
    @Transactional
    public Response crear(Candidato candidato) {
        candidato.persist();
        return Response.status(Response.Status.CREATED).entity(candidato).build();
    }

    // 4. Actualizar candidato (PUT http://localhost:8080/candidatos/1)
    @PUT
    @Path("/{id}")
    @Transactional
    public Response actualizar(@PathParam("id") Integer id, Candidato datosNuevos) {
        Candidato candidato = Candidato.findById(id);
        if (candidato == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        candidato.setNombres(datosNuevos.getNombres());
        candidato.setApellidos(datosNuevos.getApellidos());
        candidato.setDpi(datosNuevos.getDpi());
        candidato.setCorreo(datosNuevos.getCorreo());
        candidato.setTelefono(datosNuevos.getTelefono());
        candidato.setEstado(datosNuevos.getEstado());
        return Response.ok(candidato).build();
    }

    // 5. Eliminar candidato (DELETE http://localhost:8080/candidatos/1)
    @DELETE
    @Path("/{id}")
    @Transactional
    public Response eliminar(@PathParam("id") Integer id) {
        boolean eliminado = Candidato.deleteById(id);
        if (!eliminado) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }
}
