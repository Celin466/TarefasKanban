package com.example.TarefasKanban.model;

import com.example.TarefasKanban.enums.PrioridadeDaTarefa;
import com.example.TarefasKanban.enums.StatusDaTarefa;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String titulo;
    private String descricao;
    private Date dataCriacao;
    private Date prazo;
    private StatusDaTarefa status;
    private PrioridadeDaTarefa prioridade;

    @PrePersist
    protected void onCreate() {
        this.dataCriacao = new Date();
    }

    public long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public Date getPrazo() {
        return prazo;
    }

    public StatusDaTarefa getStatus() {
        return status;
    }

    public PrioridadeDaTarefa getPrioridade() {
        return prioridade;
    }

    public void setStatus(StatusDaTarefa status) {
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public void setPrazo(Date prazo) {
        this.prazo = prazo;
    }

    public void setPrioridade(PrioridadeDaTarefa prioridade) {
        this.prioridade = prioridade;
    }



}


