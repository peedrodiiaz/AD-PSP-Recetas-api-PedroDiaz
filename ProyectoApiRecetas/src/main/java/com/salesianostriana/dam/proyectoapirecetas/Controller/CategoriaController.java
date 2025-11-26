package com.salesianostriana.dam.proyectoapirecetas.Controller;

import com.salesianostriana.dam.proyectoapirecetas.Dto.EditCategoriaDto;
import com.salesianostriana.dam.proyectoapirecetas.Dto.ResponseCategoriaDto;
import com.salesianostriana.dam.proyectoapirecetas.Service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Categorias/")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;


    @GetMapping
    public List<ResponseCategoriaDto> getAll (){
        return categoriaService.getAll().stream()
                .map(ResponseCategoriaDto::of).toList();
    }
    @GetMapping("/{id}")
    public ResponseCategoriaDto getById (@PathVariable Long id){
        return ResponseCategoriaDto.of(categoriaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ResponseCategoriaDto> create(
            @RequestBody EditCategoriaDto cmd
            ){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseCategoriaDto.of(categoriaService.save(cmd))
        );
    }


    @PutMapping("/{id}")
    public ResponseCategoriaDto edit(@PathVariable Long id, EditCategoriaDto cmd){
        return ResponseCategoriaDto.of(categoriaService.edit(cmd,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id){
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
























}
