package com.salesianostriana.dam.proyectoapirecetas.Service;

import com.salesianostriana.dam.proyectoapirecetas.Dto.EditIngredienteDto;
import com.salesianostriana.dam.proyectoapirecetas.Error.NombreDuplicadoException;
import com.salesianostriana.dam.proyectoapirecetas.Model.Ingrediente;
import com.salesianostriana.dam.proyectoapirecetas.Repository.IngredienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredienteService {

    private final IngredienteRepository ingredienteRepository;



    public List<Ingrediente> getAll(){
        return ingredienteRepository.findAll();
    }
    public Ingrediente save(EditIngredienteDto cmd) {
        // Validar nombre duplicado
        List<Ingrediente> lista = ingredienteRepository.findAll();
        boolean existe = lista.stream().anyMatch(i ->
                i.getNombre().equalsIgnoreCase(cmd.nombre())
        );
        if (existe) {
            throw new NombreDuplicadoException("Ya existe un ingrediente con ese nombre");
        }
        return ingredienteRepository.save(
                Ingrediente.builder()
                        .nombre(cmd.nombre())
                        .build()
        );

    }


}
