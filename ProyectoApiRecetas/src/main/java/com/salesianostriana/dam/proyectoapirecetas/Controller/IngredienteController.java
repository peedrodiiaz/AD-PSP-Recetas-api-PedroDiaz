package com.salesianostriana.dam.proyectoapirecetas.Controller;

import com.salesianostriana.dam.proyectoapirecetas.Dto.EditIngredienteDto;
import com.salesianostriana.dam.proyectoapirecetas.Dto.ResponseIngredienteDto;
import com.salesianostriana.dam.proyectoapirecetas.Service.IngredienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredientes")
@RequiredArgsConstructor
public class IngredienteController {

    private final IngredienteService ingredienteService;


    @GetMapping
    public List<ResponseIngredienteDto> getAll() {
        return ingredienteService.getAll().stream()
                .map(ResponseIngredienteDto::of)
                .toList();
    }


    @PostMapping
    public ResponseEntity<ResponseIngredienteDto> create( @RequestBody EditIngredienteDto cmd) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseIngredienteDto.of(ingredienteService.save(cmd))
        );
    }
}
