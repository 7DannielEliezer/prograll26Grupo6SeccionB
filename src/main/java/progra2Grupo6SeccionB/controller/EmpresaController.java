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
import progra2Grupo6SeccionB.model.Empresa;

@Path("/empresas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmpresaController {

    // 1. Listar todas las empresas (GET http://localhost:8080/empresas)
    @GET
    public List<Empresa> listarTodas() {
        return Empresa.listAll();
    }

    // 2. Buscar empresa por ID (GET http://localhost:8080/empresas/1)
    @GET
    @Path("/{id}")
    public Response obtenerPorId(@PathParam("id") Integer id) {
        Empresa empresa = Empresa.findById(id);
        if (empresa == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(empresa).build();
    }

    // 3. Crear nueva empresa (POST http://localhost:8080/empresas)
    @POST
    @Transactional
    public Response crear(Empresa empresa) {
        if (empresa.getFechaRegistro() == null) {
            empresa.setFechaRegistro(new Date());
        }
        if (empresa.getEstado() == null) {
            empresa.setEstado("Activo");
        }
        empresa.persist();
        return Response.status(Response.Status.CREATED).entity(empresa).build();
    }

    // 4. Actualizar datos de empresa (PUT http://localhost:8080/empresas/1)
    @PUT
    @Path("/{id}")
    @Transactional
    public Response actualizar(@PathParam("id") Integer id, Empresa datos) {
        Empresa empresa = Empresa.findById(id);
        if (empresa == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        empresa.setNombreEmpresa(datos.getNombreEmpresa());
        empresa.setNit(datos.getNit());
        empresa.setTelefono(datos.getTelefono());
        empresa.setCorreo(datos.getCorreo());
        empresa.setDireccion(datos.getDireccion());
        empresa.setEstado(datos.getEstado());
        return Response.ok(empresa).build();
    }

    // 5. Eliminar empresa (DELETE http://localhost:8080/empresas/1)
    @DELETE
    @Path("/{id}")
    @Transactional
    public Response eliminar(@PathParam("id") Integer id) {
        boolean eliminado = Empresa.deleteById(id);
        if (!eliminado) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }
}