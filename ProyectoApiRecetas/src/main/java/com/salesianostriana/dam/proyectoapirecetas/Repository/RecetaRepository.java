package com.salesianostriana.dam.proyectoapirecetas.Repository;

import com.salesianostriana.dam.proyectoapirecetas.Model.Receta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecetaRepository extends JpaRepository<Receta,Long> {
}
