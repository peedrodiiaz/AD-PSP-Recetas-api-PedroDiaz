package com.salesianostriana.dam.proyectoapirecetas.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecetaIngrediente {

    @Id @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "receta_id")
    private Receta receta;
    @ManyToOne
    @JoinColumn(name = "ingrediente_id")
    private Ingrediente ingrediente;
    private double cantidad;
    private String unidad;

}
