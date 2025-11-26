package com.salesianostriana.dam.proyectoapirecetas.Controller;

import com.salesianostriana.dam.proyectoapirecetas.Dto.AniadirIngredienteDto;
import com.salesianostriana.dam.proyectoapirecetas.Dto.EditRecetaDto;
import com.salesianostriana.dam.proyectoapirecetas.Dto.IngredienteEnRecetaDto;
import com.salesianostriana.dam.proyectoapirecetas.Dto.ResponseRecetaDto;
import com.salesianostriana.dam.proyectoapirecetas.Model.RecetaIngrediente;
import com.salesianostriana.dam.proyectoapirecetas.Service.RecetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recetas")
@RequiredArgsConstructor
public class RecetaController {

    private final RecetaService recetaService;

    @GetMapping
    public List<ResponseRecetaDto> getAll(){
        return recetaService.getAll().stream()
                .map(ResponseRecetaDto::of)
                .toList();
    }

    @GetMapping ("/{id}")
    public ResponseRecetaDto getById(Long id){
        return ResponseRecetaDto.of(recetaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ResponseRecetaDto> create (@RequestBody EditRecetaDto cmd){

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseRecetaDto.of(recetaService.save(cmd))
        );
    }

    @PutMapping("/{id}")
    public ResponseRecetaDto edit (@PathVariable Long id, EditRecetaDto cmd){
        return ResponseRecetaDto.of(recetaService.edit(cmd, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>delete(@PathVariable Long id){
        recetaService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/{recetaId}/ingredientes")
    public ResponseEntity<IngredienteEnRecetaDto> anadirIngrediente(@PathVariable Long recetaId,
                    @RequestBody AniadirIngredienteDto cmd) {
                        RecetaIngrediente ri = recetaService.anadirIngrediente(recetaId, cmd);
                        return ResponseEntity.status(HttpStatus.CREATED).body(IngredienteEnRecetaDto.of(ri));
    }




}
