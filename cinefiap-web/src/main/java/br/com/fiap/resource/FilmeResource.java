package br.com.fiap.resource;

import br.com.fiap.dto.FilmeDto;
import br.com.fiap.service.FilmeService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/filmes")
public class FilmeResource {
    private FilmeService service = new FilmeService();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public FilmeDto buscarPorId(@PathParam("id") Long id){
        return service.buscarPorId(id);
    }


}
