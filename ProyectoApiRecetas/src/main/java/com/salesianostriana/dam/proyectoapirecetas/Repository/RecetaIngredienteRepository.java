package com.salesianostriana.dam.proyectoapirecetas.Repository;

import com.salesianostriana.dam.proyectoapirecetas.Model.RecetaIngrediente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecetaIngredienteRepository extends JpaRepository<RecetaIngrediente,Long> {

    boolean existsByRecetaIdAndIngredienteId(Long recetaId, Long ingredienteId);

}
