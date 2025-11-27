package com.salesianostriana.dam.proyectoapirecetas.Controller;

import com.salesianostriana.dam.proyectoapirecetas.Dto.EditCategoriaDto;
import com.salesianostriana.dam.proyectoapirecetas.Dto.ResponseCategoriaDto;
import com.salesianostriana.dam.proyectoapirecetas.Service.CategoriaService;
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
@RequestMapping("categorias/")
@RequiredArgsConstructor
@Tag(name = "Categorías", description = "API de gestión de categorías de recetas")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @Operation(
            summary = "Obtener todas las categorías",
            description = "Retorna un listado completo de todas las categorías disponibles"
    )
    @ApiResponse(description = "Listado de categorías",
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ResponseCategoriaDto.class))
            )
    )
    @GetMapping
    public List<ResponseCategoriaDto> getAll (){
        return categoriaService.getAll().stream()
                .map(ResponseCategoriaDto::of).toList();
    }

    @Operation(
            summary = "Obtener una categoría por ID",
            description = "Obtiene los detalles de una categoría específica"
    )
    @ApiResponse(description = "Información detallada de la categoría",
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResponseCategoriaDto.class)
            )
    )
    @GetMapping("/{id}")
    public ResponseCategoriaDto getById (@PathVariable Long id){
        return ResponseCategoriaDto.of(categoriaService.findById(id));
    }

    @Operation(
            summary = "Crear una nueva categoría",
            description = "Crea una nueva categoría de recetas"
    )
    @ApiResponse(description = "Categoría creada exitosamente",
            responseCode = "201",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResponseCategoriaDto.class),
                    examples = {
                            @ExampleObject("""
                                    {
                                        "id": 1,
                                        "nombre": "Postres",
                                        "descripcion": "Dulces y postres para la tarde"
                                    }
                                """)
                    }
            )
    )
    @PostMapping
    public ResponseEntity<ResponseCategoriaDto> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de la categoría a crear",
                    required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EditCategoriaDto.class),
                            examples = @ExampleObject("""
                                    {
                                        "nombre": "Postres",
                                        "descripcion": "Dulces y postres para la tarde"
                                    }
                                """)
                    )
            )
            @RequestBody EditCategoriaDto cmd
            ){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseCategoriaDto.of(categoriaService.save(cmd))
        );
    }

    @Operation(
            summary = "Editar una categoría existente",
            description = "Modifica los datos de una categoría existente"
    )
    @ApiResponse(description = "Categoría editada exitosamente",
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResponseCategoriaDto.class)
            )
    )
    @PutMapping("/{id}")
    public ResponseCategoriaDto edit(
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos actualizados de la categoría",
                    required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EditCategoriaDto.class),
                            examples = @ExampleObject("""
                                    {
                                        "nombre": "Postres caseros",
                                        "descripcion": "Dulces y postres tradicionales y caseros"
                                    }
                                """)
                    )
            )
            @RequestBody EditCategoriaDto cmd){
        return ResponseCategoriaDto.of(categoriaService.edit(cmd,id));

    }

    @Operation(
            summary = "Eliminar una categoría",
            description = "Elimina una categoría existente por su ID"
    )
    @ApiResponse(description = "Categoría eliminada exitosamente",
            responseCode = "204",
            content = @Content(schema = @Schema(implementation = Void.class)))
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id){
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
























}
