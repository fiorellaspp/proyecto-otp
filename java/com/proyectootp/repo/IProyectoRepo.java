package com.proyectootp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.proyectootp.model.Proyecto;

public interface IProyectoRepo extends JpaRepository<Proyecto, Integer> {
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM detalle_proyecto WHERE id_proyecto = :id", nativeQuery = true)
    void borraDetalleProyecto(@Param("id") Integer id);
}
