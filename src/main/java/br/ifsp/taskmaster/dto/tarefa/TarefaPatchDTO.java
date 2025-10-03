package br.ifsp.taskmaster.dto.tarefa;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.ifsp.taskmaster.model.StatusTarefa;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "DTO para atualização parcial de uma tarefa. Apenas os campos fornecidos serão alterados.")
public class TarefaPatchDTO {

    @Schema(description = "Novo título para a tarefa.", example = "Título revisado e atualizado")
    private String titulo;

    @Schema(description = "Nova descrição detalhada para a tarefa.", example = "A descrição foi corrigida.")
    private String descricao;

    @Schema(description = "Nova data limite para a tarefa, no formato dd/MM/yyyy.", example = "25/12/2025")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataLimite;

    @Schema(description = "Nova categoria para a tarefa.", example = "Estudo")
    private String categoria;

    @Schema(description = "Novo status para a tarefa.", example = "EM_ANDAMENTO")
    private StatusTarefa status;
}