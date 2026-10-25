/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.umg.controller;



import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import paquete.bd.reclutadora.Empresas;



@Path("/empresas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class empresasController {

    @Inject
    EntityManager em;

    // Listar todas las empresas
    @GET
    public List<EmpresaDTO> listarEmpresas() {
        List<Empresas> empresas = em.createNamedQuery(
            "Empresas.findAll", Empresas.class
        ).getResultList();

        return empresas.stream()
            .map(EmpresaDTO::new)
            .collect(Collectors.toList());
    }

    // Registrar una empresa
    @POST
    @Transactional
    public Response guardarEmpresa(Empresas nuevaEmpresa) {

        if (nuevaEmpresa == null
                || nuevaEmpresa.getNombreEmpresa() == null
                || nuevaEmpresa.getNombreEmpresa().trim().isEmpty()
                || nuevaEmpresa.getNit() == null
                || nuevaEmpresa.getNit().trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity("{\"mensaje\":\"El nombre y el NIT son obligatorios\"}")
                .build();
        }

        String nit = nuevaEmpresa.getNit().trim();

        Long duplicados = em.createQuery(
            "SELECT COUNT(e) FROM Empresas e WHERE e.nit = :nit",
            Long.class
        ).setParameter("nit", nit).getSingleResult();

        if (duplicados > 0) {
            return Response.status(Response.Status.CONFLICT)
                .entity("{\"mensaje\":\"Ya existe una empresa con ese NIT\"}")
                .build();
        }

        nuevaEmpresa.setEmpresaID(null);
        nuevaEmpresa.setNombreEmpresa(
            nuevaEmpresa.getNombreEmpresa().trim()
        );
        nuevaEmpresa.setNit(nit);

        em.persist(nuevaEmpresa);
        em.flush();

        return Response.status(Response.Status.CREATED)
            .entity(new EmpresaDTO(nuevaEmpresa))
            .build();
    }

    // Actualizar una empresa existente
    @PUT
    @Path("/{id}")
    @Transactional
    public Response actualizarEmpresa(
            @PathParam("id") Integer id,
            Empresas datos) {

        if (datos == null
                || datos.getNombreEmpresa() == null
                || datos.getNombreEmpresa().trim().isEmpty()
                || datos.getNit() == null
                || datos.getNit().trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity("{\"mensaje\":\"El nombre y el NIT son obligatorios\"}")
                .build();
        }

        Empresas existente = em.find(Empresas.class, id);

        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND)
                .entity("{\"mensaje\":\"Empresa no encontrada\"}")
                .build();
        }

        String nit = datos.getNit().trim();

        Long duplicados = em.createQuery(
            "SELECT COUNT(e) FROM Empresas e "
            + "WHERE e.nit = :nit AND e.empresaID <> :id",
            Long.class
        )
        .setParameter("nit", nit)
        .setParameter("id", id)
        .getSingleResult();

        if (duplicados > 0) {
            return Response.status(Response.Status.CONFLICT)
                .entity("{\"mensaje\":\"Otra empresa ya utiliza ese NIT\"}")
                .build();
        }

        existente.setNombreEmpresa(datos.getNombreEmpresa().trim());
        existente.setNit(nit);
        existente.setTelefono(datos.getTelefono());
        existente.setCorreo(datos.getCorreo());

        return Response.ok(new EmpresaDTO(existente)).build();
    }
    
    // Eliminar una empresa
@DELETE
@Path("/{id}")
@Transactional
public Response eliminarEmpresa(@PathParam("id") Integer id) {

    
    
    
    
    Empresas empresa = em.find(Empresas.class, id);

    if (empresa == null) {
        return Response.status(Response.Status.NOT_FOUND)
            .entity("{\"mensaje\":\"Empresa no encontrada\"}")
            .build();
    }

    em.remove(empresa);

    return Response.ok()
        .entity("{\"mensaje\":\"Empresa eliminada correctamente\"}")
        .build();
}
    
    
    

    // Respuesta JSON sin incluir la lista de vacantes
    public static class EmpresaDTO {
        public Integer empresaID;
        public String nombreEmpresa;
        public String nit;
        public String telefono;
        public String correo;

        public EmpresaDTO() {
        }

        public EmpresaDTO(Empresas empresa) {
            this.empresaID = empresa.getEmpresaID();
            this.nombreEmpresa = empresa.getNombreEmpresa();
            this.nit = empresa.getNit();
            this.telefono = empresa.getTelefono();
            this.correo = empresa.getCorreo();
        }
    }
}
