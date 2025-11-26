package com.salesianostriana.dam.proyectoapirecetas.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Receta {

    @Id@GeneratedValue
    private Long id;
    private String name;
    private int tiempoPreparacion;
    private Dificultad dificultad;
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecetaIngrediente> recetaIngredientes;


}
