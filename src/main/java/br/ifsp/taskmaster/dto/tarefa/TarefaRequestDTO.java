package br.ifsp.taskmaster.dto.tarefa;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema; // <-- Importar
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para criar ou atualizar completamente uma tarefa")
public class TarefaRequestDTO {

    @Schema(description = "Título da tarefa.", example = "Implementar a camada de segurança", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "O titulo da tarefa nao pode estar vazio")
    private String titulo;

    @Schema(description = "Descrição detalhada da tarefa.", example = "Usar Spring Security com JWT para proteger os endpoints.", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "A descricao da tarefa nao pode estar vazia")
    private String descricao;

    @Schema(description = "Categoria para agrupar a tarefa.", example = "Desenvolvimento", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "A categoria da tarefa nao pode estar vazia")
    private String categoria;

    @Schema(description = "Data limite para a conclusão da tarefa no formato dd/MM/yyyy.", example = "20/12/2025", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "A data limite da tarefa não pode estar vazia")
    @FutureOrPresent(message = "A data limite não pode ser no passado")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataLimite;
}