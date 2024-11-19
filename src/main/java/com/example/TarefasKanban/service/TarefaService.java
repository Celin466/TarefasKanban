package com.example.TarefasKanban.service;

import com.example.TarefasKanban.dto.TarefaRequest;
import com.example.TarefasKanban.enums.PrioridadeDaTarefa;
import com.example.TarefasKanban.enums.StatusDaTarefa;
import com.example.TarefasKanban.model.Tarefa;
import com.example.TarefasKanban.repository.TarefaRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TarefaService {
    private final TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public Map<StatusDaTarefa, List<Tarefa>> listarTodos() {
        List<Tarefa> tarefas =   tarefaRepository.findAll(Sort.by("prioridade"));
        return tarefas.stream().collect(Collectors.groupingBy(Tarefa::getStatus));
    }

    public List<Tarefa> relatorioDeTarefasAtrasadas(){
        List<Tarefa> tarefas = tarefaRepository.findAll();

        return tarefas.stream()
                .filter(tarefa -> tarefa.getPrazo().before(new Date()) && !tarefa.getStatus().equals(StatusDaTarefa.Concluido))
                .collect(Collectors.toList());
    }

    public Tarefa criarTarefa(@RequestBody Tarefa tarefa) {
        tarefa.setStatus(StatusDaTarefa.A_Fazer);
        return tarefaRepository.save(tarefa);
    }

    public String moverTarefa(Long id) {
        Optional<Tarefa> tarefaEscolhida = tarefaRepository.findById(id);
        if (tarefaEscolhida.isPresent()) {
            Tarefa tarefaExistente = tarefaEscolhida.get();

            if(tarefaExistente.getStatus() == StatusDaTarefa.A_Fazer) {
                tarefaExistente.setStatus(StatusDaTarefa.Em_Progresso);
                salvar(tarefaExistente);
                return "tarefa movida para em progresso";
            }else{
                tarefaExistente.setStatus(StatusDaTarefa.Concluido);
                salvar(tarefaExistente);
                return "tarefa movida para concluído";
            }

        }
        return "Registro não encontrado";
    }

    public Tarefa editarTarefa(@RequestBody TarefaRequest tarefa, Long id) {
        Optional<Tarefa> tarefaEscolhida = tarefaRepository.findById(id);
        if (tarefaEscolhida.isPresent()) {
            Tarefa tarefaExistente = tarefaEscolhida.get();

            if(tarefa.descricao != null && tarefa.descricao.length() > 0)
                tarefaExistente.setDescricao(tarefa.descricao);

            if(tarefa.titulo != null && tarefa.titulo.length() > 0)
                tarefaExistente.setTitulo(tarefa.titulo);

            if(tarefa.prioridade != null)
                tarefaExistente.setPrioridade(tarefa.prioridade);

            if(tarefa.prazo != null)
                tarefaExistente.setPrazo(tarefa.prazo);

            return tarefaRepository.save(tarefaExistente);
        }
        return new Tarefa();
    }

    public String excluirTarefa(Long id) {
        Optional<Tarefa> tarefaEscolhida = tarefaRepository.findById(id);
        if (tarefaEscolhida.isPresent()) {
            Tarefa tarefaExistente = tarefaEscolhida.get();
            tarefaRepository.deleteById(id);
            return "Registro exclúido com sucesso!";
        }
        return "Registro não encontrado";
    }

    public List<Tarefa> filtrarTarefas(PrioridadeDaTarefa prioridade, Date dateLimit) {
        List<Tarefa> tarefas = tarefaRepository.findAll();
        List<Tarefa> tarefasFiltradas = new ArrayList<>();

        for (Tarefa tarefa : tarefas) {
            boolean prioridadeMatch = (prioridade == null || tarefa.getPrioridade().equals(prioridade));
            boolean dataMatch = (dateLimit == null || tarefa.getPrazo().equals(dateLimit));
            if (prioridadeMatch && dataMatch) {
                tarefasFiltradas.add(tarefa);
            }
        }

        return tarefasFiltradas;
    }

    public void salvar(Tarefa tarefa) {
        tarefaRepository.save(tarefa);
    }
}
