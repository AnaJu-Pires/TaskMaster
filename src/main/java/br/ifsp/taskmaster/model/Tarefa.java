package br.ifsp.taskmaster.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O titulo da tarefa não pode estar vazio")
    private String titulo;

    @NotBlank(message = "A descrição da tarefa não pode estar vazia")
    private String descricao;

    @NotBlank(message = "A categoria da tarefa não pode estar vazia")
    private String categoria;

    @NotNull(message = "A data limite da tarefa não pode estar vazia")
    @FutureOrPresent(message = "A data limite não pode ser no passado")
    private LocalDate dataLimite;

    @NotNull(message = "O status não pode ser nulo")
    @Enumerated(EnumType.STRING)
    private StatusTarefa status;

}