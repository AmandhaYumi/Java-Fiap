package br.com.fiap.resource;

import br.com.fiap.dto.SessaoRequestDto;
import br.com.fiap.dto.SessaoResponseDto;
import br.com.fiap.service.SessaoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/sessoes")
public class SessaoResource {
    private SessaoService service = new SessaoService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SessaoResponseDto> listarSessoes() {
        return service.listar();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public SessaoResponseDto buscarPorId(@PathParam("id") Long id) {
        return service.buscarPorId(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarSessao(SessaoRequestDto dto) {
        service.cadastrar(dto);
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alterarSessao(@PathParam("id") Long id, SessaoResponseDto dto) {
        dto.setId(id);
        service.alterar(dto);
        return Response.status(Response.Status.OK).entity(dto).build();
    }

    @DELETE
    @Path("/{id}")
    public Response excluirSessao(@PathParam("id") Long id) {
        service.excluir(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
