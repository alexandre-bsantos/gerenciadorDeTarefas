package com.projeto.login.resources;

import com.projeto.login.dto.LoginRequestDTO;
import com.projeto.login.response.LoginResponseDTO;
import com.projeto.usuario.model.Usuario;
import io.smallrye.jwt.build.Jwt;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.mindrot.jbcrypt.BCrypt;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginResource {

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(LoginRequestDTO login) {
        Usuario usuario = Usuario.find("email", login.email).firstResult();

        if (usuario == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        if (!BCrypt.checkpw(login.senha, usuario.senha)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        String token = Jwt.issuer("gerenciadorDeTarefas")
                .upn(usuario.email)
                .claim("id", usuario.id)
                .claim("nome", usuario.nome)
                .sign();

        return Response.ok(new LoginResponseDTO(token)).build();
    }
}
