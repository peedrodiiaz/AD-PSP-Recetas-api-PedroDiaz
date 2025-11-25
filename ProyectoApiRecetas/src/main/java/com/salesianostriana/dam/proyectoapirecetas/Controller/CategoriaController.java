package com.salesianostriana.dam.proyectoapirecetas.Controller;

import com.salesianostriana.dam.proyectoapirecetas.Dto.EditCategoriaDto;
import com.salesianostriana.dam.proyectoapirecetas.Dto.GetCategoriaDto;
import com.salesianostriana.dam.proyectoapirecetas.Service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("Categorias/")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;


    @GetMapping
    public List<GetCategoriaDto> getAll (){
        return categoriaService.getAll().stream()
                .map(GetCategoriaDto::of).toList();
    }
    @GetMapping("/{id}")
    public GetCategoriaDto getById (@PathVariable Long id){
        return GetCategoriaDto.of(categoriaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<GetCategoriaDto> create(
            @RequestBody EditCategoriaDto cmd
            ){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                GetCategoriaDto.of(categoriaService.save(cmd))
        );
    }


    @PutMapping("/{id}")
    public GetCategoriaDto edit(@PathVariable Long id, EditCategoriaDto cmd){
        return GetCategoriaDto.of(categoriaService.edit(cmd,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id){
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
























}
