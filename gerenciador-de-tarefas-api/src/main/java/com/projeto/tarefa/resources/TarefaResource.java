package com.projeto.tarefa.resources;

import com.projeto.tarefa.model.Tarefa;
import com.projeto.usuario.model.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/tarefas")
@Produces(MediaType.APPLICATION_JSON)
public class TarefaResource {

    @GET
    public List<PanacheEntityBase> listarUsuarios() {
        return Tarefa.listAll();
    }

}
