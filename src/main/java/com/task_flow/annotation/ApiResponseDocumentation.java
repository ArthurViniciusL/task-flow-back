package com.task_flow.annotation;

import com.task_flow.dto.ErroResponseDTO;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public final class ApiResponseDocumentation {

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Operação realizada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Requisição inválida",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroResponseDTO.class))
            )
    })
    public @interface ApiResponseGet {
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de recursos retornada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Requisição inválida",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroResponseDTO.class))
            )
    })
    public @interface ApiResponseGetList {
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Recurso encontrado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Requisição inválida",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Recurso não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroResponseDTO.class))
            )
    })
    public @interface ApiResponseGetById {
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Recurso criado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Requisição inválida",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroResponseDTO.class))
            )
    })
    public @interface ApiResponsePost {
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Autenticação realizada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Requisição inválida",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Credenciais inválidas",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroResponseDTO.class))
            )
    })
    public @interface ApiResponseLogin {
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Usuário registrado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Requisição inválida",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Usuário já existe",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroResponseDTO.class))
            )
    })
    public @interface ApiResponseRegister {
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Recurso atualizado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Requisição inválida",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Recurso não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroResponseDTO.class))
            )
    })
    public @interface ApiResponsePut {
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Recurso excluído com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Recurso não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErroResponseDTO.class))
            )
    })
    public @interface ApiResponseDelete {
    }

    private ApiResponseDocumentation() {
        throw new UnsupportedOperationException("Classe utilitária não deve ser instanciada");
    }
}