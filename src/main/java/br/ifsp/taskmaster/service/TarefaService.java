package br.ifsp.taskmaster.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.ifsp.taskmaster.dto.tarefa.TarefaPatchDTO;
import br.ifsp.taskmaster.dto.tarefa.TarefaRequestDTO;
import br.ifsp.taskmaster.dto.tarefa.TarefaResponseDTO;

public interface TarefaService {

    TarefaResponseDTO criarTarefa(TarefaRequestDTO requestDTO);
    Page<TarefaResponseDTO> listarTarefas(String categoria, Pageable pageable);
    TarefaResponseDTO buscarTarefaPorId(Long id);
    TarefaResponseDTO atualizarTarefaCompleta(Long id, TarefaRequestDTO requestDTO); 
    //eu fiz essa opcao por habito, mas se a pessoa quer att tudo, mais facil criar outra n?
    TarefaResponseDTO atualizarTarefaParcial(Long id, TarefaPatchDTO patchDTO);
    void deletarTarefa(Long id);
}