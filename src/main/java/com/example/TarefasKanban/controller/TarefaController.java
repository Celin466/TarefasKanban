package com.example.TarefasKanban.controller;

import com.example.TarefasKanban.dto.TarefaRequest;
import com.example.TarefasKanban.enums.PrioridadeDaTarefa;
import com.example.TarefasKanban.enums.StatusDaTarefa;
import com.example.TarefasKanban.model.Tarefa;
import com.example.TarefasKanban.service.TarefaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tasks")
public class TarefaController {
    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @GetMapping
    public Map<StatusDaTarefa, List<Tarefa>> listarTodos() {
        return tarefaService.listarTodos();
    }

    @PostMapping
    public Tarefa createProduct(@RequestBody Tarefa tarefa) {
        return tarefaService.criarTarefa(tarefa);
    }

    @PutMapping("/{id}/move")
    public String moverTarefa(@PathVariable Long id) {
        return tarefaService.moverTarefa(id);
    }

    @PutMapping("/{id}")
    public Tarefa atualizarTarefa(@PathVariable Long id, @RequestBody TarefaRequest tarefaRequest) {
        return tarefaService.editarTarefa(tarefaRequest, id);
    }

    @DeleteMapping("/{id}")
    public String deletarTarefa(@PathVariable Long id) {
        return tarefaService.excluirTarefa(id);
    }

    @GetMapping("/filtrar")
    public List<Tarefa> filtrarTarefas(
            @RequestParam(required = false) PrioridadeDaTarefa prioridade,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateLimit) {
        return tarefaService.filtrarTarefas(prioridade, dateLimit);
    }

    @GetMapping("/tarefasAtrasadas")
    public List<Tarefa> tarefasAtrasadas(){
        return tarefaService.relatorioDeTarefasAtrasadas();
    }
}
