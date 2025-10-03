package br.ifsp.taskmaster.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ifsp.taskmaster.model.Tarefa;


@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    
    Page<Tarefa> findByCategoria(String categoria, Pageable pageable);
}