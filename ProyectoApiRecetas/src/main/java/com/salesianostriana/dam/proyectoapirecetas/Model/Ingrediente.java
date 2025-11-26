package com.salesianostriana.dam.proyectoapirecetas.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Ingrediente {
    @Id@GeneratedValue
    private Long id;
    private String nombre;
    @OneToMany(mappedBy = "ingrediente")
    private List<RecetaIngrediente> recetaIngredientes ;




}
