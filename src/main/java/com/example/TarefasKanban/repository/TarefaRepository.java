package com.example.TarefasKanban.repository;

import com.example.TarefasKanban.enums.PrioridadeDaTarefa;
import com.example.TarefasKanban.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}
