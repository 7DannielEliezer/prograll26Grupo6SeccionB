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
import progra2Grupo6SeccionB.model.Vacante;

@Path("/vacantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VacanteController {

    // 1. Listar todas las vacantes (GET http://localhost:8080/vacantes)
    @GET
    public List<Vacante> listarTodas() {
        return Vacante.listAll();
    }

    // 2. Buscar vacante por ID (GET http://localhost:8080/vacantes/1)
    @GET
    @Path("/{id}")
    public Response obtenerPorId(@PathParam("id") Integer id) {
        Vacante vacante = Vacante.findById(id);
        if (vacante == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(vacante).build();
    }

    // 3. Crear nueva vacante (POST http://localhost:8080/vacantes)
    @POST
    @Transactional
    public Response crear(Vacante vacante) {
        if (vacante.getFechaCreacion() == null) {
            vacante.setFechaCreacion(new Date());
        }
        if (vacante.getEstado() == null) {
            vacante.setEstado("Abierta");
        }
        vacante.persist();
        return Response.status(Response.Status.CREATED).entity(vacante).build();
    }

    // 4. Actualizar vacante (PUT http://localhost:8080/vacantes/1)
    @PUT
    @Path("/{id}")
    @Transactional
    public Response actualizar(@PathParam("id") Integer id, Vacante datos) {
        Vacante vacante = Vacante.findById(id);
        if (vacante == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        vacante.setTituloVacante(datos.getTituloVacante());
        vacante.setDescripcion(datos.getDescripcion());
        vacante.setSueldoOfrecido(datos.getSueldoOfrecido());
        vacante.setEstado(datos.getEstado());
        if (datos.getIdEmpresa() != null) {
            vacante.setIdEmpresa(datos.getIdEmpresa());
        }
        return Response.ok(vacante).build();
    }

    // 5. Eliminar vacante (DELETE http://localhost:8080/vacantes/1)
    @DELETE
    @Path("/{id}")
    @Transactional
    public Response eliminar(@PathParam("id") Integer id) {
        boolean eliminado = Vacante.deleteById(id);
        if (!eliminado) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }
}
