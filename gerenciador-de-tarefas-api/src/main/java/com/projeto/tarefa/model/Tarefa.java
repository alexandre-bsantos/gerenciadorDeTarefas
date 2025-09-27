package com.projeto.tarefa.model;

import com.projeto.categoria.model.Categoria;
import com.projeto.enums.Situacao;
import com.projeto.usuario.model.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Tarefa extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String titulo;

    public String descricao;

    public Situacao situacao;

    public Date dataCriacao;

    public Date dataVencimento;

    @ManyToOne
    @JoinColumn(name = "id")
    public Categoria categoriaId;

    @ManyToOne
    @JoinColumn(name = "id")
    public Usuario usuarioId;

    public Categoria getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Categoria categoriaId) {
        this.categoriaId = categoriaId;
    }
}
