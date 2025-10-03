package br.ifsp.taskmaster.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.ifsp.taskmaster.dto.ErrorResponse;
import br.ifsp.taskmaster.dto.tarefa.TarefaPatchDTO;
import br.ifsp.taskmaster.dto.tarefa.TarefaRequestDTO;
import br.ifsp.taskmaster.dto.tarefa.TarefaResponseDTO;
import br.ifsp.taskmaster.service.TarefaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
@Tag(name = "Tarefas" , description = "Endpoints para Gerenciamento de Tarefas")
public class TarefaController {

    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @Operation(summary = "Cria uma nova tarefa", description = "Registra uma nova tarefa no sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Tarefa criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<TarefaResponseDTO> criar(@RequestBody @Valid TarefaRequestDTO requestDTO) {
        TarefaResponseDTO responseDTO = tarefaService.criarTarefa(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
    
    @Operation(summary = "Lista todas as tarefas", description = "Retorna uma lista paginada de tarefas, com opção de filtro por categoria.")
    @ApiResponse(responseCode = "200", description = "Lista de tarefas retornada com sucesso")
    @GetMapping
    public ResponseEntity<Page<TarefaResponseDTO>> listar(
            @Parameter(description = "Categoria para filtrar as tarefas (opcional)") @RequestParam(required = false) String categoria,
            Pageable pageable) {
        Page<TarefaResponseDTO> tarefas = tarefaService.listarTarefas(categoria, pageable);
        return ResponseEntity.ok(tarefas);
    }

    @Operation(summary = "Busca uma tarefa por ID", description = "Retorna os detalhes de uma tarefa específica a partir do seu ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tarefa encontrada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Tarefa não encontrada",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<TarefaResponseDTO> buscarPorId(@Parameter(description = "ID da tarefa a ser buscada") @PathVariable Long id) {
        TarefaResponseDTO responseDTO = tarefaService.buscarTarefaPorId(id);
        return ResponseEntity.ok(responseDTO);
    }

    @Operation(summary = "Atualiza uma tarefa por completo (PUT)", description = "Substitui todos os dados de uma tarefa existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "404", description = "Tarefa não encontrada",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<TarefaResponseDTO> atualizarCompleto(
            @Parameter(description = "ID da tarefa a ser atualizada") @PathVariable Long id, 
            @RequestBody @Valid TarefaRequestDTO requestDTO) {
        TarefaResponseDTO responseDTO = tarefaService.atualizarTarefaCompleta(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }
    
    @Operation(summary = "Atualiza uma tarefa parcialmente (PATCH)", description = "Altera um ou mais campos de uma tarefa existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "404", description = "Tarefa não encontrada",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PatchMapping("/{id}")
    public ResponseEntity<TarefaResponseDTO> atualizarParcial(
            @Parameter(description = "ID da tarefa a ser atualizada") @PathVariable Long id, 
            @RequestBody TarefaPatchDTO patchDTO) {
        TarefaResponseDTO responseDTO = tarefaService.atualizarTarefaParcial(id, patchDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @Operation(summary = "Exclui uma tarefa", description = "Remove uma tarefa permanentemente do sistema a partir do seu ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Tarefa excluída com sucesso"),
        @ApiResponse(responseCode = "404", description = "Tarefa não encontrada",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@Parameter(description = "ID da tarefa a ser excluída") @PathVariable Long id) {
        tarefaService.deletarTarefa(id);
        return ResponseEntity.noContent().build();
    }
}