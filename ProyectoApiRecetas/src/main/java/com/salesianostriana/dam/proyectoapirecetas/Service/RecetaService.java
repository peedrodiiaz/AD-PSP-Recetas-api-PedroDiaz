package com.salesianostriana.dam.proyectoapirecetas.Service;

import com.salesianostriana.dam.proyectoapirecetas.Dto.AniadirIngredienteDto;
import com.salesianostriana.dam.proyectoapirecetas.Dto.EditRecetaDto;
import com.salesianostriana.dam.proyectoapirecetas.Error.*;
import com.salesianostriana.dam.proyectoapirecetas.Model.Categoria;
import com.salesianostriana.dam.proyectoapirecetas.Model.Ingrediente;
import com.salesianostriana.dam.proyectoapirecetas.Model.Receta;
import com.salesianostriana.dam.proyectoapirecetas.Model.RecetaIngrediente;
import com.salesianostriana.dam.proyectoapirecetas.Repository.CategoriaRepository;
import com.salesianostriana.dam.proyectoapirecetas.Repository.IngredienteRepository;
import com.salesianostriana.dam.proyectoapirecetas.Repository.RecetaIngredienteRepository;
import com.salesianostriana.dam.proyectoapirecetas.Repository.RecetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecetaService {

    private final RecetaRepository recetaRepository;
    private final CategoriaRepository categoriaRepository;
    private final IngredienteRepository ingredienteRepository;
    private final RecetaIngredienteRepository recetaIngredienteRepository;

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


        Categoria categoria = categoriaRepository.findById(cmd.categoriaId())
                .orElseThrow(() -> new CategoriaInvalidaException(cmd.categoriaId()));

        if (cmd.tiempoPreparacion()<=0)
            throw new TiempoInvalidoException();
        boolean noHacer= lista.stream()
                .anyMatch(receta ->
                        receta.getName().equalsIgnoreCase(cmd.name())
                );
        if (noHacer)
            throw new NombreDuplicadoException();

        Receta receta= Receta.builder()
                .name(cmd.name())
                .dificultad(cmd.dificultad())
                .categoria(categoria)
                .tiempoPreparacion(cmd.tiempoPreparacion())
                .build();

        return recetaRepository.save(receta);
    }


    public Receta edit (EditRecetaDto cmd,Long id){
        List<Receta>lista = recetaRepository.findAll();
        Categoria categoria = categoriaRepository.findById(cmd.categoriaId()).
                orElseThrow(
                        CategoriaNotFoundException::new
                );

        if (cmd.tiempoPreparacion()<=0)
            throw new TiempoInvalidoException();
        boolean noHacer= lista.stream()
                .anyMatch(receta ->
                        !receta.getId().equals(id)&&
                                receta.getName().equalsIgnoreCase(cmd.name())
                );
        if (noHacer)
            throw new NombreDuplicadoException();



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

    public RecetaIngrediente anadirIngrediente(Long recetaId, AniadirIngredienteDto cmd) {
        Receta receta = recetaRepository.findById(recetaId)
                .orElseThrow(() -> new EntidadNoEncontradaException("Receta no encontrada con id: " + recetaId));

        Ingrediente ingrediente = ingredienteRepository.findById(cmd.ingredienteId())
                .orElseThrow(() -> new EntidadNoEncontradaException(cmd.ingredienteId()));

        if (recetaIngredienteRepository.existsByRecetaIdAndIngredienteId(recetaId, cmd.ingredienteId())) {
            throw new IngredienteYaAnadidoException();
        }

        RecetaIngrediente ri = RecetaIngrediente.builder()
                .receta(receta)
                .ingrediente(ingrediente)
                .cantidad(cmd.cantidad())
                .unidad(cmd.unidad())
                .build();

        return recetaIngredienteRepository.save(ri);
    }








}
