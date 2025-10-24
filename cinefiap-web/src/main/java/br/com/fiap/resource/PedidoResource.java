package br.com.fiap.resource;

import br.com.fiap.dto.PedidoRequestDto;
import br.com.fiap.dto.PedidoResponseDto;
import br.com.fiap.service.PedidoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("pedidos")
public class PedidoResource {

    private PedidoService pedidoService = new PedidoService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodos() {
        List<PedidoResponseDto> pedidos = pedidoService.listarPedidos();
        return Response.ok(pedidos).build();
    }

    @GET
    @Path("/join")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodosComJoin() {
        List<PedidoResponseDto> pedidos = pedidoService.listarPedidosComJoin();
        return Response.ok(pedidos).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorId(@PathParam("id") Long id) {
        PedidoResponseDto pedido = pedidoService.buscarPedidoPorId(id);
        if (pedido != null) {
            return Response.ok(pedido).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response criar(PedidoRequestDto pedidoRequestDto) {
        try {
            PedidoResponseDto pedidoCriado = pedidoService.criarPedido(pedidoRequestDto);
            return Response.status(Response.Status.CREATED).entity(pedidoCriado).build();
        } catch (RuntimeException e) {
            // Adicionar log
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
