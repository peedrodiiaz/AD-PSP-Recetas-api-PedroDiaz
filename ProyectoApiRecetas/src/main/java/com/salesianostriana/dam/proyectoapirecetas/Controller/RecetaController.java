package com.salesianostriana.dam.proyectoapirecetas.Controller;

import com.salesianostriana.dam.proyectoapirecetas.Dto.AniadirIngredienteDto;
import com.salesianostriana.dam.proyectoapirecetas.Dto.EditRecetaDto;
import com.salesianostriana.dam.proyectoapirecetas.Dto.IngredienteEnRecetaDto;
import com.salesianostriana.dam.proyectoapirecetas.Dto.ResponseRecetaDto;
import com.salesianostriana.dam.proyectoapirecetas.Model.RecetaIngrediente;
import com.salesianostriana.dam.proyectoapirecetas.Service.RecetaService;
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
@RequestMapping("/recetas")
@RequiredArgsConstructor
@Tag(name = "Recetas", description = "API de gestión de recetas")
public class RecetaController {

    private final RecetaService recetaService;

    @Operation(
            summary = "Obtener todas las recetas",
            description = "Permite obtener un listado completo de todas las recetas con sus ingredientes"
    )
    @ApiResponse(description = "Listado de todas las recetas",
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ResponseRecetaDto.class))
            )
    )
    @GetMapping
    public List<ResponseRecetaDto> getAll(){
        return recetaService.getAll().stream()
                .map(ResponseRecetaDto::of)
                .toList();
    }

    @Operation(
            summary = "Obtener una receta por ID",
            description = "Obtiene los detalles de una receta específica incluyendo la lista de sus ingredientes con cantidad y unidad"
    )
    @ApiResponse(description = "Información detallada de la receta con sus ingredientes",
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResponseRecetaDto.class),
                    examples = {
                            @ExampleObject("""
                                    {
                                        "id": 1,
                                        "name": "Arroz con pollo",
                                        "tiempoPreparacion": 45,
                                        "dificultad": "MEDIA",
                                        "categoriaName": "Platos principales",
                                        "ingredientes": [
                                            {
                                                "ingredienteNombre": "Arroz",
                                                "cantidad": "200",
                                                "unidad": "gramos"
                                            },
                                            {
                                                "ingredienteNombre": "Pollo",
                                                "cantidad": "500",
                                                "unidad": "gramos"
                                            }
                                        ]
                                    }
                                """)
                    }
            )
    )
    @GetMapping("/{id}")
    public ResponseRecetaDto getById(@PathVariable Long id){
        return ResponseRecetaDto.of(recetaService.findById(id));
    }

    @Operation(
            summary = "Crear una nueva receta",
            description = "Crea una nueva receta. Necesitamos el ID de una categoría existente"
    )
    @ApiResponse(description = "Receta creada exitosamente",
            responseCode = "201",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResponseRecetaDto.class),
                    examples = {
                            @ExampleObject("""
                                    {
                                        "id": 1,
                                        "name": "Paella valenciana",
                                        "tiempoPreparacion": 60,
                                        "dificultad": "DIFICIL",
                                        "categoriaName": "Platos principales",
                                        "ingredientes": []
                                    }
                                """)
                    }
            )
    )
    @PostMapping
    public ResponseEntity<ResponseRecetaDto> create (
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de la receta a crear. El categoriaId debe ser de una categoría existente",
                    required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EditRecetaDto.class),
                            examples = @ExampleObject("""
                                    {
                                        "name": "Paella valenciana",
                                        "tiempoPreparacion": 60,
                                        "dificultad": "DIFICIL",
                                        "categoriaId": 1
                                    }
                                """)
                    )
            )
            @RequestBody EditRecetaDto cmd){

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseRecetaDto.of(recetaService.save(cmd))
        );
    }

    @Operation(
            summary = "Editar una receta existente",
            description = "Modifica los datos de una receta existente. Necesita el ID de una categoría existente"
    )
    @ApiResponse(description = "Receta editada exitosamente",
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResponseRecetaDto.class)
            )
    )
    @PutMapping("/{id}")
    public ResponseRecetaDto edit (
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos actualizados de la receta",
                    required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EditRecetaDto.class),
                            examples = @ExampleObject("""
                                    {
                                        "name": "Paella valenciana mejorada",
                                        "tiempoPreparacion": 50,
                                        "dificultad": "MEDIA",
                                        "categoriaId": 1
                                    }
                                """)
                    )
            )
            @RequestBody EditRecetaDto cmd){
        return ResponseRecetaDto.of(recetaService.edit(cmd, id));
    }

    @Operation(
            summary = "Eliminar una receta",
            description = "Elimina una receta existente por su ID"
    )
    @ApiResponse(description = "Receta eliminada exitosamente",
            responseCode = "204",
            content = @Content(schema = @Schema(implementation = Void.class)))
    @DeleteMapping("/{id}")
    public ResponseEntity<?>delete(@PathVariable Long id){
        recetaService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @Operation(
            summary = "Añadir ingrediente a una receta (Relación M:M)",
            description = """
                    Añade un ingrediente a una receta específica.
                    El DTO AniadirIngredienteDto permite especificar:
                    - ingredienteId, cantidad, unidad.
                    Se almacena en la tabla de enlace RecetaIngrediente,
                haciendo que cada receta tenga sus ingredientes
                """
    )
    @ApiResponse(description = "Ingrediente añadido exitosamente a la receta",
            responseCode = "201",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = IngredienteEnRecetaDto.class),
                    examples = {
                            @ExampleObject("""
                                    {
                                        "ingredienteNombre": "Arroz",
                                        "cantidad": "200",
                                        "unidad": "gramos"
                                    }
                                """)
                    }
            )
    )
    @PostMapping("/{recetaId}/ingredientes")
    public ResponseEntity<IngredienteEnRecetaDto> anadirIngrediente(
            @PathVariable Long recetaId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = """
                            DTO para añadir ingrediente a receta con cantidad y unidad.
                            La tabla de enlace RecetaIngrediente almacena:
                            - La relación entre Receta e Ingrediente
                            - Los datos adicionales: cantidad y unidad
                            
                            Ejemplo: Para añadir 200 gramos de arroz a una receta
                            """,
                    required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AniadirIngredienteDto.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Ingrediente sólido",
                                            description = "Ejemplo de ingrediente medido en gramos",
                                            value = """
                                                    {
                                                        "ingredienteId": 1,
                                                        "cantidad": 200,
                                                        "unidad": "gramos"
                                                    }
                                                    """
                                    ),
                                    @ExampleObject(
                                            name = "Ingrediente líquido",
                                            description = "Ejemplo de ingrediente medido en mililitros",
                                            value = """
                                                    {
                                                        "ingredienteId": 2,
                                                        "cantidad": 500,
                                                        "unidad": "mililitros"
                                                    }
                                                    """
                                    ),
                                    @ExampleObject(
                                            name = "Ingrediente por unidades",
                                            description = "Ejemplo de ingrediente contado por unidades",
                                            value = """
                                                    {
                                                        "ingredienteId": 3,
                                                        "cantidad": 3,
                                                        "unidad": "unidades"
                                                    }
                                                    """
                                    )
                            }
                    )
            )
            @RequestBody AniadirIngredienteDto cmd) {
                        RecetaIngrediente ri = recetaService.anadirIngrediente(recetaId, cmd);
                        return ResponseEntity.status(HttpStatus.CREATED).body(IngredienteEnRecetaDto.of(ri));
    }




}
