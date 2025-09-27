package com.projeto.categoria.resources;

import com.projeto.categoria.model.Categoria;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/categorias")
@Produces(MediaType.APPLICATION_JSON)
public class CategoriaResource {

    @GET
    public List<PanacheEntityBase> listarCategorias(){
        return Categoria.listAll();
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public void incluirCategorias (Categoria categoria) {
        categoria.persist();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public void atualizarCategoria (@PathParam("id") Long id, Categoria categoria) {
        Categoria novaCategoria = Categoria.findById(id);

        if (novaCategoria != null) {
            novaCategoria.id = categoria.id;
            novaCategoria.nome = categoria.nome;
            novaCategoria.descricao = categoria.descricao;
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void deletarCategoria (@PathParam("id") Long id) {
        Categoria.deleteById(id);
    }
}
