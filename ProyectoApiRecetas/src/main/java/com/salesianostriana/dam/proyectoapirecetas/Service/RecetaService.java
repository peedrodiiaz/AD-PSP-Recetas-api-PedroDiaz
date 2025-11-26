package com.salesianostriana.dam.proyectoapirecetas.Service;

import com.salesianostriana.dam.proyectoapirecetas.Dto.EditRecetaDto;
import com.salesianostriana.dam.proyectoapirecetas.Error.*;
import com.salesianostriana.dam.proyectoapirecetas.Model.Categoria;
import com.salesianostriana.dam.proyectoapirecetas.Model.Receta;
import com.salesianostriana.dam.proyectoapirecetas.Repository.CategoriaRepository;
import com.salesianostriana.dam.proyectoapirecetas.Repository.RecetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecetaService {

    private final RecetaRepository recetaRepository;
    private final CategoriaRepository categoriaRepository;


    public List<Receta> getAll(){

        List<Receta> lista= recetaRepository.findAll();

        if (lista.isEmpty())
            throw  new RecetaNoEncontradaException();

        return lista;
    }

    public Receta findById(Long id){
        return  recetaRepository.findById(id).orElseThrow(
                ()-> new RecetaNoEncontradaException(id)
        );
    }

    public Receta save (EditRecetaDto cmd){
        List<Receta>lista = recetaRepository.findAll();

        boolean noHacer= lista.stream()
                .anyMatch(receta ->
                    receta.getName().equalsIgnoreCase(cmd.name())
                );
        if (cmd.tiempoPreparacion()<=0)
            throw new TiempoInvalidoException();
        if (noHacer)
            throw new NombreDuplicadoException();
        Categoria categoria = categoriaRepository.findById(cmd.categoriaId())
                .orElseThrow(() -> new CategoriaInvalidaException(cmd.categoriaId()));

        return Receta.builder()
                .name(cmd.name())
                .dificultad(cmd.dificultad())
                .categoria(categoria)
                .tiempoPreparacion(cmd.tiempoPreparacion())
                .build();

    }
    // Al crear o editar si se manda una categoría que no existe se manda un error 400
    // Preguntar por el crear

    public Receta edit (EditRecetaDto cmd,Long id){
        List<Receta>lista = recetaRepository.findAll();

        boolean noHacer= lista.stream()
                .anyMatch(receta ->
                        !receta.getId().equals(id)&&
                        receta.getName().equalsIgnoreCase(cmd.name())
                );
        if (cmd.tiempoPreparacion()<=0)
            throw new TiempoInvalidoException();
        if (noHacer)
            throw new NombreDuplicadoException();

        Categoria categoria = categoriaRepository.findById(cmd.categoriaId()).
                orElseThrow(
                        CategoriaNotFoundException::new
                );

        return recetaRepository.findById(id)
                .map(
                        receta -> {
                            receta.setName(cmd.name());
                            receta.setCategoria(categoria);
                            receta.setDificultad(cmd.dificultad());
                            receta.setTiempoPreparacion(cmd.tiempoPreparacion());

                        return recetaRepository.save(receta);
                        })
                .orElseThrow(()-> new RecetaNoEncontradaException(id));

    }

    public void delete(Long id){
        if (!recetaRepository.existsById(id))
            throw  new RecetaNoEncontradaException(id);

        recetaRepository.deleteById(id);
    }










}
