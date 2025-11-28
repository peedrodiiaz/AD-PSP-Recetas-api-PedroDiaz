package com.salesianostriana.dam.proyectoapirecetas.Model;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Categoria {
    @Id @GeneratedValue
    private Long id;
    private String nombre;
    private String descripcion;
    @OneToMany(mappedBy = "categoria",fetch = FetchType.EAGER)
    private Set<Receta>recetas;
}