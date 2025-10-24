package br.com.fiap.resource;

import br.com.fiap.dto.SalaRequestDto;
import br.com.fiap.dto.SalaResponseDto;
import br.com.fiap.service.SalaService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/salas")
public class SalaResource {
    private SalaService service = new SalaService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SalaResponseDto> listarSalas() {
        return service.listar();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public SalaResponseDto buscarPorId(@PathParam("id") Long id) {
        return service.buscarPorId(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarSala(SalaRequestDto dto) {
        service.cadastrar(dto);
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alterarSala(@PathParam("id") Long id, SalaRequestDto dto) {
        dto.setId(id);
        service.alterar(dto);
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    @DELETE
    @Path("/{id}")
    public Response excluirSala(@PathParam("id") Long id) {
        service.excluir(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
