package br.com.fiap.resource;

import br.com.fiap.dto.IngressoRequestDto;
import br.com.fiap.dto.IngressoResponseDto;
import br.com.fiap.service.IngressoService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("ingressos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class IngressoResource {

    private IngressoService ingressoService = new IngressoService();

    @POST
    public Response criar(IngressoRequestDto requestDto) {
        try {
            IngressoResponseDto responseDto = ingressoService.criarIngresso(requestDto);
            return Response.status(Response.Status.CREATED).entity(responseDto).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    public Response listar() {
        List<IngressoResponseDto> ingressos = ingressoService.listarIngressos();
        return Response.ok(ingressos).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        try {
            IngressoResponseDto responseDto = ingressoService.buscarIngressoPorId(id);
            return Response.ok(responseDto).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, IngressoRequestDto requestDto) {
        try {
            IngressoResponseDto responseDto = ingressoService.atualizarIngresso(id, requestDto);
            return Response.ok(responseDto).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        try {
            ingressoService.deletarIngresso(id);
            return Response.noContent().build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
