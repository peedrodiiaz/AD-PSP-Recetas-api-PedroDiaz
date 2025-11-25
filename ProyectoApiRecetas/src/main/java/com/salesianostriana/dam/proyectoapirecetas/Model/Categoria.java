package com.salesianostriana.dam.proyectoapirecetas.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Categoria {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String descripcion;

}