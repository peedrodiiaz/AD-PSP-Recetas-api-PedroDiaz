package com.salesianostriana.dam.proyectoapirecetas.Service;

import com.salesianostriana.dam.proyectoapirecetas.Dto.EditCategoriaDto;
import com.salesianostriana.dam.proyectoapirecetas.Error.CategoriaNotFoundException;
import com.salesianostriana.dam.proyectoapirecetas.Error.NombreDuplicadoException;
import com.salesianostriana.dam.proyectoapirecetas.Model.Categoria;
import com.salesianostriana.dam.proyectoapirecetas.Repository.CategoriaRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;


    public List<Categoria> getAll(){
        List<Categoria> lista = categoriaRepository.findAll();

        if (lista.isEmpty()){
            throw  new CategoriaNotFoundException();
        }
        return  lista;

    }

    public Categoria findById(Long id){
        return categoriaRepository.findById(id).orElseThrow(
                ()-> new CategoriaNotFoundException(id));

    }

    public Categoria save (EditCategoriaDto cmd){
        List<Categoria>lista= categoriaRepository.findAll();
        boolean noHacer= lista.stream().anyMatch(c->
            c.getNombre().equalsIgnoreCase(cmd.nombre())
        );
        if (noHacer)
            throw  new NombreDuplicadoException();


        return categoriaRepository.save(
                Categoria.builder()
                        .nombre(cmd.nombre())
                        .descripcion(cmd.descripcion())
                        .build()
        );

    }

    public Categoria edit(EditCategoriaDto cmd, Long id) {
        return categoriaRepository.findById(id)
                .map(c -> {
                    if (c.getNombre().equalsIgnoreCase(cmd.nombre())) {
                        List<Categoria> lista = categoriaRepository.findAll();
                        boolean existe = lista.stream().anyMatch(cat ->
                                cat.getNombre().equalsIgnoreCase(cmd.nombre())
                        );
                        if (existe) {
                            throw new NombreDuplicadoException();
                        }
                    }
                    c.setNombre(cmd.nombre());
                    c.setDescripcion(cmd.descripcion());
                    return categoriaRepository.save(c);
                })
                .orElseThrow(() -> new CategoriaNotFoundException(id));
    }


    public void delete(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new CategoriaNotFoundException(id);
        }
        categoriaRepository.deleteById(id);
    }



}
