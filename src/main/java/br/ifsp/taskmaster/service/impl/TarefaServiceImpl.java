package br.ifsp.taskmaster.service.impl;

import br.ifsp.taskmaster.service.TarefaService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.ifsp.taskmaster.dto.tarefa.TarefaPatchDTO;
import br.ifsp.taskmaster.dto.tarefa.TarefaRequestDTO;
import br.ifsp.taskmaster.dto.tarefa.TarefaResponseDTO;
import br.ifsp.taskmaster.exception.BusinessRuleException;
import br.ifsp.taskmaster.exception.ResourceNotFoundException;
import br.ifsp.taskmaster.model.StatusTarefa;
import br.ifsp.taskmaster.model.Tarefa;
import br.ifsp.taskmaster.repository.TarefaRepository;

import java.time.LocalDate;

@Service
public class TarefaServiceImpl implements TarefaService {

    private final TarefaRepository tarefaRepository;
    public TarefaServiceImpl(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    @Override
    public TarefaResponseDTO criarTarefa(TarefaRequestDTO requestDTO) {
        if (requestDTO.getDataLimite().isBefore(LocalDate.now())) {
            throw new BusinessRuleException("A data limite não pode ser uma data no passado.");
        }
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(requestDTO.getTitulo());
        tarefa.setDescricao(requestDTO.getDescricao());
        tarefa.setCategoria(requestDTO.getCategoria());
        tarefa.setDataLimite(requestDTO.getDataLimite());
        tarefa.setStatus(StatusTarefa.PENDENTE);

        Tarefa tarefaSalva = tarefaRepository.save(tarefa);
        return toResponseDTO(tarefaSalva);
    }

    @Override
    public Page<TarefaResponseDTO> listarTarefas(String categoria, Pageable pageable) {
        Page<Tarefa> tarefasPage;
        if (categoria != null && !categoria.isBlank()) {
            tarefasPage = tarefaRepository.findByCategoria(categoria, pageable);
        } else {
            tarefasPage = tarefaRepository.findAll(pageable);
        }
        return tarefasPage.map(this::toResponseDTO);
    }

    @Override
    public TarefaResponseDTO buscarTarefaPorId(Long id) {
        Tarefa tarefa = findTarefaById(id);
        return toResponseDTO(tarefa);
    }

    @Override
    public TarefaResponseDTO atualizarTarefaCompleta(Long id, TarefaRequestDTO requestDTO) {
        Tarefa tarefaExistente = findTarefaById(id);
        tarefaExistente.setTitulo(requestDTO.getTitulo());
        tarefaExistente.setDescricao(requestDTO.getDescricao());
        tarefaExistente.setCategoria(requestDTO.getCategoria());
        tarefaExistente.setDataLimite(requestDTO.getDataLimite());
        Tarefa tarefaAtualizada = tarefaRepository.save(tarefaExistente);
        return toResponseDTO(tarefaAtualizada);
    }
    
    @Override
    public TarefaResponseDTO atualizarTarefaParcial(Long id, TarefaPatchDTO patchDTO) {
        Tarefa tarefaExistente = findTarefaById(id);
        if (patchDTO.getTitulo() != null) {
            tarefaExistente.setTitulo(patchDTO.getTitulo());
        }
        if (patchDTO.getDescricao() != null) {
            tarefaExistente.setDescricao(patchDTO.getDescricao());
        }
        if (patchDTO.getDataLimite() != null) {
            tarefaExistente.setDataLimite(patchDTO.getDataLimite());
        }
        if (patchDTO.getCategoria() != null) {
            tarefaExistente.setCategoria(patchDTO.getCategoria());
        }
        if (patchDTO.getStatus() != null) {
            tarefaExistente.setStatus(patchDTO.getStatus());
        }

        Tarefa tarefaAtualizada = tarefaRepository.save(tarefaExistente);
        return toResponseDTO(tarefaAtualizada);
    }

    @Override
    public void deletarTarefa(Long id) {
        if (!tarefaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Tarefa com ID " + id + " não encontrada para exclusão.");
        }
        tarefaRepository.deleteById(id);
    }

    private Tarefa findTarefaById(Long id) {
        return tarefaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa com ID " + id + " não encontrada."));
    }

    private TarefaResponseDTO toResponseDTO(Tarefa tarefa) {
        return new TarefaResponseDTO(
            tarefa.getId(),
            tarefa.getTitulo(),
            tarefa.getDescricao(),
            tarefa.getCategoria(),
            tarefa.getDataLimite(),
            tarefa.getStatus()
        );
    }
}