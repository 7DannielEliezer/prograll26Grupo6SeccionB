
package gt.edu.umg.controller;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import paquete.bd.reclutadora.Candidatos;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/candidatos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class candidatoController {

    @Inject
    EntityManager em;

    // Consultar todos los candidatos
    @GET
    public List<CandidatoDTO> listarCandidatos() {
        List<Candidatos> candidatos = em.createNamedQuery(
            "Candidatos.findAll", Candidatos.class
        ).getResultList();

        return candidatos.stream()
            .map(CandidatoDTO::new)
            .collect(Collectors.toList());
    }

    // Guardar un candidato
    @POST
    @Transactional
    public Response saveNewCandidato(Candidatos nuevoCandidato) {
        if (nuevoCandidato == null
                || nuevoCandidato.getNombres() == null
                || nuevoCandidato.getApellidos() == null
                || nuevoCandidato.getDpi() == null
                || nuevoCandidato.getCorreo() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity("{\"mensaje\":\"Completa los campos obligatorios\"}")
                .build();
        }

        nuevoCandidato.setCandidatoID(null);
        em.persist(nuevoCandidato);
        em.flush();

        return Response.status(Response.Status.CREATED)
            .entity(new CandidatoDTO(nuevoCandidato))
            .build();
    }

    // Actualizar un candidato existente
    @PUT
    @Path("/{id}")
    @Transactional
    public Response actualizarCandidato(
            @PathParam("id") Integer id,
            Candidatos datos) {

        Candidatos existente = em.find(Candidatos.class, id);

        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND)
                .entity("{\"mensaje\":\"Candidato no encontrado\"}")
                .build();
        }

        if (datos == null
                || datos.getNombres() == null
                || datos.getApellidos() == null
                || datos.getDpi() == null
                || datos.getCorreo() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        existente.setNombres(datos.getNombres());
        existente.setApellidos(datos.getApellidos());
        existente.setDpi(datos.getDpi());
        existente.setCorreo(datos.getCorreo());
        existente.setTelefono(datos.getTelefono());

        return Response.ok(new CandidatoDTO(existente)).build();
    }
    // Eliminar un candidato
@DELETE
@Path("/{id}")
@Transactional
public Response eliminarCandidato(@PathParam("id") Integer id) {

    Candidatos candidato = em.find(Candidatos.class, id);

    if (candidato == null) {
        return Response.status(Response.Status.NOT_FOUND)
            .entity("{\"mensaje\":\"Candidato no encontrado\"}")
            .build();
    }

    em.remove(candidato);

    return Response.ok()
        .entity("{\"mensaje\":\"Candidato eliminado correctamente\"}")
        .build();
}
    
    
    
    
    
    

    // DTO: devuelve solo los campos que necesita la página
    public static class CandidatoDTO {
        public Integer candidatoID;
        public String nombres;
        public String apellidos;
        public String dpi;
        public String correo;
        public String telefono;

        public CandidatoDTO() {
        }

        public CandidatoDTO(Candidatos c) {
            candidatoID = c.getCandidatoID();
            nombres = c.getNombres();
            apellidos = c.getApellidos();
            dpi = c.getDpi();
            correo = c.getCorreo();
            telefono = c.getTelefono();
        }
    }
}
