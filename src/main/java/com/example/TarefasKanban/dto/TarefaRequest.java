package com.example.TarefasKanban.dto;

import com.example.TarefasKanban.enums.PrioridadeDaTarefa;

import java.util.Date;

public class TarefaRequest {
    public String titulo;
    public String descricao;
    public PrioridadeDaTarefa prioridade;
    public Date prazo;
}
