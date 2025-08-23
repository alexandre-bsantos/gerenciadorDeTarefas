package com.projeto.usuario.resources;

import com.projeto.usuario.model.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)

public class UsuarioResource {

    @GET
    public List<PanacheEntityBase> listarUsuarios() {
        return Usuario.listAll();
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public void incluirUsuario(Usuario usuario) {
        usuario.senha = BCrypt.hashpw(usuario.senha, BCrypt.gensalt());
        usuario.persist();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public void atualizarUsuario(@PathParam("id") Long id, Usuario usuario) {
        Usuario novoUsuario = Usuario.findById(id);

        if (novoUsuario != null) {
            novoUsuario.id = usuario.id;
            novoUsuario.email = usuario.email;
            novoUsuario.nome = usuario.nome;
            novoUsuario.senha = usuario.senha;
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void deletarUsuario(@PathParam("id") Long id) {
        Usuario.deleteById(id);
    }

}
