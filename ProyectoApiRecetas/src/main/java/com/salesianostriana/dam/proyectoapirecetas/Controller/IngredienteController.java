package com.salesianostriana.dam.proyectoapirecetas.Controller;

import com.salesianostriana.dam.proyectoapirecetas.Dto.EditIngredienteDto;
import com.salesianostriana.dam.proyectoapirecetas.Dto.ResponseIngredienteDto;
import com.salesianostriana.dam.proyectoapirecetas.Service.IngredienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredientes")
@RequiredArgsConstructor
@Tag(name = "Ingredientes", description = "API de gestión de ingredientes (CRUD básico: Crear y Listar)")
public class IngredienteController {

    private final IngredienteService ingredienteService;

    @Operation(
            summary = "Obtener todos los ingredientes",
            description = "Retorna un listado completo de todos los ingredientes disponibles"
    )
    @ApiResponse(description = "Listado de ingredientes",
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ResponseIngredienteDto.class)),
                    examples = {
                            @ExampleObject("""
                                    [
                                        {
                                            "id": 1,
                                            "nombre": "Arroz"
                                        },
                                        {
                                            "id": 2,
                                            "nombre": "Pollo"
                                        },
                                        {
                                            "id": 3,
                                            "nombre": "Tomate"
                                        }
                                    ]
                                """)
                    }
            )
    )
    @GetMapping
    public List<ResponseIngredienteDto> getAll() {
        return ingredienteService.getAll().stream()
                .map(ResponseIngredienteDto::of)
                .toList();
    }

    @Operation(
            summary = "Crear un nuevo ingrediente",
            description = "Crea un nuevo ingrediente que posteriormente podrá ser añadido a recetas con cantidad y unidad específicas"
    )
    @ApiResponse(description = "Ingrediente creado exitosamente",
            responseCode = "201",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResponseIngredienteDto.class),
                    examples = {
                            @ExampleObject("""
                                    {
                                        "id": 1,
                                        "nombre": "Arroz"
                                    }
                                """)
                    }
            )
    )
    @PostMapping
    public ResponseEntity<ResponseIngredienteDto> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del ingrediente a crear",
                    required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EditIngredienteDto.class),
                            examples = @ExampleObject("""
                                    {
                                        "nombre": "Arroz"
                                    }
                                """)
                    )
            )
            @RequestBody EditIngredienteDto cmd) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseIngredienteDto.of(ingredienteService.save(cmd))
        );
    }
}
