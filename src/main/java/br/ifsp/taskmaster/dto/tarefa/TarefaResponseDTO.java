package br.ifsp.taskmaster.dto.tarefa;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import br.ifsp.taskmaster.model.StatusTarefa;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para representar os dados de uma tarefa retornada pela API")
public class TarefaResponseDTO {

    @Schema(description = "ID único da tarefa gerado pelo sistema", example = "1", readOnly = true) // <-- MELHORIA AQUI
    private Long id;

    @Schema(description = "Título da tarefa", example = "Exercícios de Java")
    private String titulo;

    @Schema(description = "Descrição da tarefa", example = "CRUD completo de um mercado virtual")
    private String descricao;

    @Schema(description = "Categoria da tarefa", example = "Desenvolvimento")
    private String categoria;

    @Schema(description = "Data limite da tarefa no formato dd/MM/yyyy", example = "30/12/2025")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataLimite;

    @Schema(description = "Status atual da tarefa", example = "PENDENTE")
    private StatusTarefa status;
}